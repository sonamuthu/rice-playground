/*
 * Copyright 2005-2007 The Kuali Foundation.
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
package org.kuali.rice.kns.web.struts.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.document.TransactionalDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;


/**
 * This class is the base action form for all transactional documents.
 */
public class KualiTransactionalDocumentFormBase extends KualiDocumentFormBase {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6463383454050206811L;
	protected Map forcedReadOnlyFields;

    /**
     * This constructor sets up empty instances for the dependent objects...
     */
    public KualiTransactionalDocumentFormBase() {
        super();

        // create a blank DocumentActionFlags instance, since form-recreation needs it
        forcedReadOnlyFields = new HashMap();
    }

    /**
     * This method retrieves an instance of the form.
     * 
     * @return
     */
    public TransactionalDocument getTransactionalDocument() {
        return (TransactionalDocument) getDocument();
    }
    

    /**
     * Locates the <code>DictionaryService</code> to discover the type name of the document.
     * 
     * @return
     */
    protected String discoverDocumentTypeName() {
        return ((DataDictionaryService) KNSServiceLocator.getDataDictionaryService()).getDataDictionary().getDocumentEntry(getDocument().getClass().getName()).getDocumentTypeName();
    }

    /**
     * This method formats the given java.sql.Date as MMM d, yyyy.
     * 
     * @param reversalDate
     * 
     * @return String
     */
    protected static String formatReversalDate(java.sql.Date reversalDate) {
        if (reversalDate == null) {
            return "";
        }
        // new for thread safety
        return KNSServiceLocator.getDateTimeService().toString(reversalDate, "MMM d, yyyy");
    }

    /**
     * Gets the forcedReadOnlyFields attribute.
     * 
     * @return Returns the forcedReadOnlyFields.
     */
    public Map getForcedReadOnlyFields() {
        return forcedReadOnlyFields;
    }

    /**
     * Sets the forcedReadOnlyFields attribute value.
     * 
     * @param forcedReadOnlyFields The forcedReadOnlyFields to set.
     */
    public void setForcedReadOnlyFields(Map forcedReadOnlyFields) {
        this.forcedReadOnlyFields = forcedReadOnlyFields;
    }
    
    /**
     * Override reset to reset checkboxes if they are present on the requesting page
     * @see org.kuali.core.web.struts.form.KualiDocumentFormBase#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        // fix for KULRICE-2525
        if (request.getParameter("checkboxToReset") != null) {
            String[] checkboxesToReset = request.getParameterValues("checkboxToReset");
            if(checkboxesToReset != null && checkboxesToReset.length > 0) {
                for (int i = 0; i < checkboxesToReset.length; i++) {
                    String propertyName = (String) checkboxesToReset[i];
                    try {
                        PropertyUtils.setNestedProperty(this, propertyName, false);
                    } catch (Exception e1) {
                        throw new RuntimeException(e1.getMessage(), e1);
                    }
                }
            }
        }
    }


}
