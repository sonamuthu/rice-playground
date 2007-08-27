/*
 * Copyright 2005-2007 The Kuali Foundation.
 *
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.workflow.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.rice.config.Config;
import org.kuali.rice.core.Core;
import org.kuali.rice.lifecycle.BaseLifecycle;
import org.kuali.rice.lifecycle.Lifecycle;
import org.kuali.rice.resourceloader.GlobalResourceLoader;
import org.kuali.rice.resourceloader.ResourceLoader;
import org.kuali.rice.test.ClearDatabaseLifecycle;
import org.kuali.rice.test.RiceTestCase;
import org.kuali.rice.web.jetty.JettyServer;
import org.mortbay.jetty.webapp.WebAppClassLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.batch.FileXmlDocCollection;
import edu.iu.uis.eden.batch.XmlDoc;
import edu.iu.uis.eden.batch.XmlDocCollection;
import edu.iu.uis.eden.exception.WorkflowRuntimeException;
import edu.iu.uis.eden.test.SQLDataLoader;
import edu.iu.uis.eden.test.TestUtilities;

/**
 * Useful superclass for all Workflow test cases. Handles setup of test
 * utilities and a test environment. Configures the Spring test environment
 * providing a template method for custom context files in test mode. Also
 * provides a template method for running custom transactional setUp. Tear down
 * handles automatic tear down of objects created inside the test environment.
 */
public abstract class WorkflowTestCase extends RiceTestCase {

    private static final String MODULE_NAME = "kew";

    @Override
    protected List<String> getConfigLocations() {
	return Arrays.asList(new String[]{"classpath:META-INF/kew-test-config.xml"});
    }

    @Override
    protected String getDerbySQLFileLocation() {
	return null;
    }

    @Override
    protected String getModuleName() {
	return MODULE_NAME;
    }

    @Override
    public void setUp() throws Exception {
	System.setProperty(EdenConstants.BOOTSTRAP_SPRING_FILE, "org/kuali/workflow/resources/TestKewSpringBeans.xml");
	super.setUp();
	loadTestDataInternal();
    }

    /**
     * Initiates loading of test data within a transaction.
     */
    protected void loadTestDataInternal() throws Exception {
        getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(final TransactionStatus status) {
        	try {
        	    setUpTransactionInternal();
        	} catch (Exception e) {
        	    throw new WrappedTransactionRuntimeException(e);
        	}
            }
        });
    }


    protected void setUpTransactionInternal() throws Exception {
        final long t1 = System.currentTimeMillis();

        loadDefaultTestData();

        final long t2 = System.currentTimeMillis();
        report("Time to load default test data: " + (t2 - t1));

        loadTestData();

        final long t3 = System.currentTimeMillis();
        report("Time to load test-specific test data: " + (t3 - t2));

        setUpTransaction();

        final long t4 = System.currentTimeMillis();
        report("Time to run test-specific setup: " + (t4 - t3));
    }

    /**
     * Default implementation does nothing.  Subclasses should override this method if they want to perform setup
     * work inside of a database transaction.
     */
    protected void setUpTransaction() throws Exception {
	// override
    }

    /**
     * @Override
     */
    protected void loadTestData() throws Exception {
        // override this to load your own test data
    }

    protected TransactionTemplate getTransactionTemplate() {
	return TestUtilities.getTransactionTemplate();
    }

    private class WrappedTransactionRuntimeException extends RuntimeException {

        private static final long serialVersionUID = 3097909333572509600L;

        public WrappedTransactionRuntimeException(Exception e) {
            super(e);
        }
    }





    @Override
    protected List<Lifecycle> getPerTestLifecycles() {
	List<Lifecycle> lifecycles = super.getPerTestLifecycles();
	lifecycles.add(new ClearCacheLifecycle());
	return lifecycles;
    }

    @Override
    protected List<Lifecycle> getSuiteLifecycles() {
	List<Lifecycle> lifecycles = super.getSuiteLifecycles();
	// we want to only clear out the quartz tables one time, therefore we want to pass this lifecycle the
	// opposite of what is passed to the clear database lifecycle that runs on every test execution
	lifecycles.add(new ClearDatabaseLifecycle(getTablesNotToClear(), getTablesToClear()));
	JettyServer server = new JettyServer(9912, "/en-test", "/../kns/src/test/webapp/en");
	lifecycles.add(server);
	lifecycles.add(new InitializeGRL());
	lifecycles.add(new SuiteDataLoadLifecycle());
	return lifecycles;
    }

    private class SuiteDataLoadLifecycle extends BaseLifecycle {
	@Override
	public void start() throws Exception {
	    new SQLDataLoader("OneTimeDefaultTestData.sql", WorkflowTestCase.class).runSql();
	    super.start();
	}
    }

    private class InitializeGRL extends BaseLifecycle {
	@Override
	public void start() throws Exception {
	    Map<ClassLoader, Config> configs = Core.getCONFIGS();
	    for (Map.Entry<ClassLoader, Config> configEntry : configs.entrySet()) {
		if (configEntry.getKey() instanceof WebAppClassLoader) {
		    ResourceLoader rl = GlobalResourceLoader.getResourceLoader(configEntry.getKey());
		    if (rl == null) {
			fail("didn't find resource loader for workflow test harness web app");
		    }
		    GlobalResourceLoader.addResourceLoader(rl);
		    configs.put(Thread.currentThread().getContextClassLoader(), configEntry.getValue());
		}
	    }
	    super.start();
	}

    }

    public class ClearCacheLifecycle extends BaseLifecycle {

	@Override
	public void stop() throws Exception {
	    KEWServiceLocator.getCacheAdministrator().flushAll();
	    super.stop();
	}

    }

    /**
     * Returns the List of tables that should be cleared on every test run.
     */
    @Override
    protected List<String> getTablesToClear() {
	List<String> tablesToClear = new ArrayList<String>();
	tablesToClear.add("EN_.*");
	return tablesToClear;
    }


    /**
     * Returns the List of tables that should NOT be cleared on every test run.
     *
     * We only want to clear out the quartz tables one time, otherwise we get weird locking
     * errors if we try to clear out those tables on every run.
     */
    @Override
    protected List<String> getTablesNotToClear() {
	List<String> tablesNotToClear = new ArrayList<String>();
	tablesNotToClear.add("KR_.*");
	return tablesNotToClear;
    }


	/**
	 * By default this loads the "default" data set from the DefaultTestData.sql
	 * and DefaultTestData.xml files. Subclasses can override this to change
	 * this behaviour
	 */
	protected void loadDefaultTestData() throws Exception {
		// at this point this is constants. loading these through xml import is
		// problematic because of cache notification
		// issues in certain low level constants.
		new SQLDataLoader("DefaultTestData.sql", WorkflowTestCase.class).runSql();
		this.loadXmlFile(WorkflowTestCase.class, "DefaultTestData.xml");
	}

	protected void loadXmlFile(String fileName) {
		if (fileName.indexOf('/') < 0) {
			this.loadXmlFile(getClass(), fileName);
		} else {
			loadXmlStream(getClass().getClassLoader().getResourceAsStream(fileName));
		}
	}

	protected void loadXmlFile(Class clazz, String fileName) {
		InputStream xmlFile = TestUtilities.loadResource(clazz, fileName);
		if (xmlFile == null) {
			throw new WorkflowRuntimeException("Didn't find file " + fileName);
		}
		loadXmlStream(xmlFile);
	}

	protected void loadXmlFileFromFileSystem(String fileName) throws IOException {
		loadXmlStream(new FileInputStream(fileName));
	}

	protected void loadXmlStream(InputStream xmlStream) {
		try {
			List<XmlDocCollection> xmlFiles = new ArrayList<XmlDocCollection>();
			XmlDocCollection docCollection = getFileXmlDocCollection(xmlStream, "WorkflowUnitTestTemp");
			xmlFiles.add(docCollection);
			KEWServiceLocator.getXmlIngesterService().ingest(xmlFiles);
			for (Iterator iterator = docCollection.getXmlDocs().iterator(); iterator.hasNext();) {
				XmlDoc doc = (XmlDoc) iterator.next();
				if (!doc.isProcessed()) {
					fail("Failed to ingest xml doc: " + doc.getName());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Caught exception parsing xml file", e);
		}

	}

	protected FileXmlDocCollection getFileXmlDocCollection(InputStream xmlFile, String tempFileName) throws IOException {
		if (xmlFile == null) {
			throw new RuntimeException("Didn't find the xml file " + tempFileName);
		}
		File temp = File.createTempFile(tempFileName, ".xml");
		FileOutputStream fos = new FileOutputStream(temp);
		int data = -1;
		while ((data = xmlFile.read()) != -1) {
			fos.write(data);
		}
		fos.close();
		return new FileXmlDocCollection(temp);
	}
}