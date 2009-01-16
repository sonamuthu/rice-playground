/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.kim.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.bo.entity.KimPrincipal;
import org.kuali.rice.kim.bo.group.impl.KimGroupImpl;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * This is a description of what this class does - bhargavp don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public abstract class RoleMemberLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

	private static String PERCENT = "%";
    protected final String TEMPLATE_NAMESPACE_CODE = "template.namespaceCode";
    protected final String TEMPLATE_NAME = "template.name";
    protected final String NAMESPACE_CODE = "namespaceCode";
    protected final String NAME = "name";
    protected final String GROUP_NAME = "groupName";
    protected final String ASSIGNED_TO_PRINCIPAL_NAME = "assignedToPrincipalNameForLookup";
    protected final String ASSIGNED_TO_GROUP_NAMESPACE_CODE = "assignedToGroupNamespaceForLookup";
    protected final String ASSIGNED_TO_GROUP_NAME = "assignedToGroupNameForLookup";
    protected final String ASSIGNED_TO_NAMESPACE_FOR_LOOKUP = "assignedToRoleNamespaceForLookup";
    protected final String ASSIGNED_TO_ROLE_NAME = "assignedToRoleNameForLookup";
    protected final String ATTRIBUTE_DETAIL_VALUE = "attributeDetailValue";
    protected final String ASSIGNED_TO_ROLE_NAMESPACE_CODE = "assignedToRoles.kimRole.namespaceCode";
    protected final String ASSIGNED_TO_ROLE_ROLE_NAME = "assignedToRoles.kimRole.roleName";
    protected final String ASSIGNED_TO_ROLE_MEMBER_ID = "assignedToRoles.kimRole.members.memberId";
    protected final String DETAIL_OBJECTS_ATTRIBUTE_VALUE = "detailObjects.attributeValue";
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String,String> fieldValues) {
    	Map<String, String> searchCriteria = buildSearchCriteria(fieldValues);
    	if(searchCriteria == null)
    		return null;
        return getMemberSearchResults(searchCriteria);
    }

    protected abstract List<? extends BusinessObject> getMemberSearchResults(Map<String, String> searchCriteria);
    
    protected Map<String, String> buildSearchCriteria(Map<String, String> fieldValues){
    	String assignedToPrincipalName = fieldValues.get(ASSIGNED_TO_PRINCIPAL_NAME);
        KimPrincipal principal = null;
        if(StringUtils.isNotEmpty(assignedToPrincipalName)){
        	principal = KIMServiceLocator.getIdentityManagementService().getPrincipalByPrincipalName(assignedToPrincipalName);
        	if(principal==null)
        		return null;
        }
        String assignedToGroupNamespaceCode = fieldValues.get(ASSIGNED_TO_GROUP_NAMESPACE_CODE);
        String assignedToGroupName = fieldValues.get(ASSIGNED_TO_GROUP_NAME);
        List<KimGroupImpl> groupImpls = null;
        if(StringUtils.isNotEmpty(assignedToGroupNamespaceCode) && StringUtils.isEmpty(assignedToGroupName) ||
        		StringUtils.isEmpty(assignedToGroupNamespaceCode) && StringUtils.isNotEmpty(assignedToGroupName) ||
        		StringUtils.isNotEmpty(assignedToGroupNamespaceCode) && StringUtils.isNotEmpty(assignedToGroupName)){
        	Map<String, String> groupSearchCriteria = new HashMap<String, String>();
        	groupSearchCriteria.put(NAMESPACE_CODE, getQueryString(assignedToGroupNamespaceCode));
        	groupSearchCriteria.put(GROUP_NAME, getQueryString(assignedToGroupName));
        	groupImpls = 
        		(List<KimGroupImpl>)KNSServiceLocator.getLookupService().findCollectionBySearchUnbounded(KimGroupImpl.class, groupSearchCriteria);
        	if(groupImpls==null || groupImpls.size()==0)
        		return null;
        }

        String templateNamespaceCode = fieldValues.get(TEMPLATE_NAMESPACE_CODE);
        String templateName = fieldValues.get(TEMPLATE_NAME);
        String namespaceCode = fieldValues.get(NAMESPACE_CODE);
        String name = fieldValues.get(NAME);
        String assignedToRoleNamespaceCode = fieldValues.get(ASSIGNED_TO_NAMESPACE_FOR_LOOKUP);
        String assignedToRoleName = fieldValues.get(ASSIGNED_TO_ROLE_NAME);
        String attributeDetailValue = fieldValues.get(ATTRIBUTE_DETAIL_VALUE);

    	Map<String,String> searchCriteria = new HashMap<String, String>();
    	if(StringUtils.isNotEmpty(templateNamespaceCode))
        	searchCriteria.put(TEMPLATE_NAMESPACE_CODE, PERCENT+templateNamespaceCode+PERCENT);
        if(StringUtils.isNotEmpty(templateName))
        	searchCriteria.put(TEMPLATE_NAME, PERCENT+templateName+PERCENT);
        if(StringUtils.isNotEmpty(namespaceCode))
        	searchCriteria.put(NAMESPACE_CODE, PERCENT+namespaceCode+PERCENT);
        if(StringUtils.isNotEmpty(name))
        	searchCriteria.put(NAME, PERCENT+name+PERCENT);
        if(StringUtils.isNotEmpty(assignedToRoleNamespaceCode))
        	searchCriteria.put(ASSIGNED_TO_ROLE_NAMESPACE_CODE, PERCENT+assignedToRoleNamespaceCode+PERCENT);
        if(StringUtils.isNotEmpty(assignedToRoleName))
        	searchCriteria.put(ASSIGNED_TO_ROLE_ROLE_NAME, PERCENT+assignedToRoleName+PERCENT);
        if(StringUtils.isNotEmpty(attributeDetailValue))
        	searchCriteria.put(DETAIL_OBJECTS_ATTRIBUTE_VALUE, PERCENT+attributeDetailValue+PERCENT);
        
        if(principal!=null)
        	searchCriteria.put(ASSIGNED_TO_ROLE_MEMBER_ID, principal.getPrincipalId());
        if(groupImpls!=null){
        	StringBuffer groupQueryString = new StringBuffer();
        	for(KimGroupImpl group: groupImpls){
        		groupQueryString.append(group.getGroupId()+KimConstants.OR_OPERATOR);
        	}
            if(groupQueryString.toString().endsWith(KimConstants.OR_OPERATOR))
            	groupQueryString.delete(groupQueryString.length()-KimConstants.OR_OPERATOR.length(), groupQueryString.length());
        	searchCriteria.put(ASSIGNED_TO_ROLE_MEMBER_ID, groupQueryString.toString());
        }
        return searchCriteria;
    }
    
    protected String getQueryString(String parameter){
    	if(StringUtils.isEmpty(parameter))
    		return PERCENT;
    	else
    		return PERCENT+parameter+PERCENT;
    }
    
    /**
     * - detail value: 
     * if this is provided a full (template namespace and template name) or namespace must be supplied 
     * - may need to do further restrictions once we see how this performs
     *  
     * @param fieldValues the values of the query
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#validateSearchParameters(java.util.Map)
     */
    @Override
    public void validateSearchParameters(Map fieldValues) {
        super.validateSearchParameters(fieldValues);
/*
        String valueTemplateNamespaceCode = (String) fieldValues.get(TEMPLATE_NAMESPACE_CODE);
        String valueTemplateName = (String) fieldValues.get(TEMPLATE_NAME);
        String name = (String) fieldValues.get(NAME);
        
        if (!((StringUtils.isNotEmpty(valueTemplateNamespaceCode) && StringUtils.isNotEmpty(valueTemplateName)) 
        		|| StringUtils.isNotEmpty(name))){
            throw new ValidationException("For a search to be performed on an attribute detail value, " +
            		"a combination of template namespace and template name, or a namespace must be supplied");
        }
        */
    }

}