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
package org.kuali.rice.kim.document.authorization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.bo.impl.KimAttributes;
import org.kuali.rice.kim.bo.ui.PersonDocumentGroup;
import org.kuali.rice.kim.bo.ui.PersonDocumentRole;
import org.kuali.rice.kim.document.IdentityManagementPersonDocument;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;

/**
 * This is a description of what this class does - shyu don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class IdentityManagementPersonDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {

	@Override
	protected void addPermissionDetails(BusinessObject businessObject,
			Map<String, String> attributes) {
		super.addPermissionDetails(businessObject, attributes);
		attributes.put(KimAttributes.ENTITY_TYPE_CODE, KimConstants.EntityTypes.PERSON);
	}
	
	public Set<String> getReadOnlyEntityPropertyNames(Document document, Person user, Set<String> securePotentiallyReadOnlyEntityPropertyNames) {
		Set<String> readOnlyEntityPropertyNames = new HashSet<String>();
		for (String securePotentiallyReadOnlyEntityPropertyName : securePotentiallyReadOnlyEntityPropertyNames) {
			Map<String,String> collectionOrFieldLevelPermissionDetails = new HashMap<String,String>();
			collectionOrFieldLevelPermissionDetails.put(KimAttributes.PROPERTY_NAME, securePotentiallyReadOnlyEntityPropertyName);
			if (!isAuthorizedByTemplate(document, KimConstants.NAMESPACE_CODE, KimConstants.PermissionTemplateNames.MODIFY_ENTITY, user.getPrincipalId(), collectionOrFieldLevelPermissionDetails, null)) {
				readOnlyEntityPropertyNames.add(securePotentiallyReadOnlyEntityPropertyName);
			}
		}
		return readOnlyEntityPropertyNames;
	}
	
	public Map<String,Set<String>> getUnpopulateableGroups(Document document, Person user) {
		Map<String,Set<String>> unpopulateableGroups = new HashMap<String,Set<String>>();
		for (PersonDocumentGroup personDocumentGroup : ((IdentityManagementPersonDocument)document).getGroups()) {
			Map<String,String> collectionOrFieldLevelPermissionDetails = new HashMap<String,String>();
			collectionOrFieldLevelPermissionDetails.put(KimAttributes.NAMESPACE_CODE, personDocumentGroup.getNamespaceCode());
			collectionOrFieldLevelPermissionDetails.put(KimAttributes.GROUP_NAME, personDocumentGroup.getGroupName());
			if (!isAuthorizedByTemplate(document, KimConstants.NAMESPACE_CODE, KimConstants.PermissionTemplateNames.POPULATE_GROUP, user.getPrincipalId(), collectionOrFieldLevelPermissionDetails, null)) {
				if (!unpopulateableGroups.containsKey(personDocumentGroup.getNamespaceCode())) {
					unpopulateableGroups.put(personDocumentGroup.getNamespaceCode(), new HashSet<String>());
				}
				unpopulateableGroups.get(personDocumentGroup.getNamespaceCode()).add(personDocumentGroup.getGroupName());
			}
		}
		return unpopulateableGroups;
	}
	
	public Map<String,Set<String>> getUnassignableRoles(Document document, Person user) {
		Map<String,Set<String>> unassignableRoles = new HashMap<String,Set<String>>();
		for (PersonDocumentRole personDocumentRole : ((IdentityManagementPersonDocument)document).getRoles()) {
			Map<String,String> collectionOrFieldLevelPermissionDetails = new HashMap<String,String>();
			collectionOrFieldLevelPermissionDetails.put(KimAttributes.NAMESPACE_CODE, personDocumentRole.getNamespaceCode());
			collectionOrFieldLevelPermissionDetails.put(KimAttributes.ROLE_NAME, personDocumentRole.getRoleName());
			if (!isAuthorizedByTemplate(document, KimConstants.NAMESPACE_CODE, KimConstants.PermissionTemplateNames.ASSIGN_ROLE, user.getPrincipalId(), collectionOrFieldLevelPermissionDetails, null)) {
				if (!unassignableRoles.containsKey(personDocumentRole.getNamespaceCode())) {
					unassignableRoles.put(personDocumentRole.getNamespaceCode(), new HashSet<String>());
				}
				unassignableRoles.get(personDocumentRole.getNamespaceCode()).add(personDocumentRole.getRoleName());
			}
		}
		return unassignableRoles;
	}
}
