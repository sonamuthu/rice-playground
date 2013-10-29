/**
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.sampleu.admin;

import org.kuali.rice.testtools.common.Failable;
import org.kuali.rice.testtools.selenium.AutomatedFunctionalTestUtils;
import org.kuali.rice.testtools.selenium.WebDriverUtil;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public abstract class ConfigViewConfiguredPropertiesAftBase extends AdminTmplMthdAftNavBase {

    /**
     * ITUtil.PORTAL+"?channelTitle=Configuration%20Viewer&channelUrl="+WebDriverUtil.getBaseUrlString()+
     * "/ksb/ConfigViewer.do"+
     */
    public static final String BOOKMARK_URL = AutomatedFunctionalTestUtils.PORTAL+"?channelTitle=Configuration%20Viewer&channelUrl="+ WebDriverUtil
            .getBaseUrlString()+"/ksb/ConfigViewer.do";
            

    /**
     * {@inheritDoc}
     * Configuration Viewer
     * @return
     */
    @Override
    protected String getLinkLocator() {
        return "Configuration Viewer";
    }

    public void testConfigViewConfiguredPropertiesBookmark(Failable failable) throws Exception {
        testConfigViewConfiguredProperties();
        passed();
    }

    public void testConfigViewConfiguredPropertiesNav(Failable failable) throws Exception {
        testConfigViewConfiguredProperties();
        passed();
    }    
    
    public void testConfigViewConfiguredProperties() throws Exception {
        selectFrameIframePortlet();
        waitAndClickByLinkText("Refresh Page");
    }
}