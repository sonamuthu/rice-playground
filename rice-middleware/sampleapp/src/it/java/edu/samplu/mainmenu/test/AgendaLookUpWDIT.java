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
package edu.samplu.mainmenu.test;

import org.junit.Test;

import edu.samplu.common.ITUtil;
import edu.samplu.common.WebDriverLegacyITBase;

/**
 * tests that user 'admin' can display the Agenda lookup screen, search,
 * initiate an Agenda maintenance document via a copy action on the search results and
 * finally cancel the maintenance document
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class AgendaLookUpWDIT extends WebDriverLegacyITBase {
    public static final String TEST_URL = ITUtil.PORTAL + "?channelTitle=Agenda%20Lookup&channelUrl="
            + ITUtil.getBaseUrlString() + ITUtil.KRAD_LOOKUP_METHOD
            + "org.kuali.rice.krms.impl.repository.AgendaBo"
            + ITUtil.SHOW_MAINTENANCE_LINKS
            + "&returnLocation=" + ITUtil.PORTAL_URL + ITUtil.HIDE_RETURN_LINK;
 
    @Override
    public String getTestUrl() {
        return TEST_URL;
    }

   @Test
    public void agendaLookupAssertions() throws Exception{
        super.agendaLookupAssertions();
        passed();
    }
}
