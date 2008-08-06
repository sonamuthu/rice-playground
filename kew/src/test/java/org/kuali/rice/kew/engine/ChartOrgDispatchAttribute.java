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
package org.kuali.rice.kew.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.exception.KEWUserNotFoundException;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.routetemplate.AbstractRoleAttribute;
import org.kuali.rice.kew.routetemplate.ResolvedQualifiedRole;
import org.kuali.rice.kew.routetemplate.Role;
import org.kuali.rice.kew.util.XmlHelper;
import org.kuali.rice.kew.workgroup.GroupNameId;


public class ChartOrgDispatchAttribute extends AbstractRoleAttribute {

    public static final String DISPATCH_ROLE = "DISPATCH";
    private String workgroupName;
    
    public ChartOrgDispatchAttribute() {}
    
    public ChartOrgDispatchAttribute(String workgroupName) {
        this.workgroupName = workgroupName;
    }

    public List getRoleNames() {
        return Arrays.asList(new Role[] { new Role(getClass(), DISPATCH_ROLE, DISPATCH_ROLE) });
    }

    public List getQualifiedRoleNames(String roleName, DocumentContent documentContent) throws KEWUserNotFoundException {
        return parseWorkgroups(documentContent);
    }

    public ResolvedQualifiedRole resolveQualifiedRole(RouteContext routeContext, String roleName, String qualifiedRole) throws KEWUserNotFoundException {
        List recipients = new ArrayList();
        recipients.add(new GroupNameId(qualifiedRole));
        return new ResolvedQualifiedRole(roleName, recipients);
    }

    public String getDocContent() {
        return "<"+DISPATCH_ROLE+">"+workgroupName+"</"+DISPATCH_ROLE+">";
    }
    
    private List parseWorkgroups(DocumentContent documentContent) {
        Document document = XmlHelper.buildJDocument(documentContent.getDocument());
        Vector dispatchElements = XmlHelper.findElements(document.getRootElement(), DISPATCH_ROLE);
        List workgroupNames = new ArrayList();
        for (Iterator iterator = dispatchElements.iterator(); iterator.hasNext();) {
            Element element = (Element) iterator.next();
            workgroupNames.add(element.getText());
        }
        return workgroupNames;
    }
    
    
    
    

}
