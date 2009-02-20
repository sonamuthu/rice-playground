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
package org.kuali.rice.kim.service.support.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.bo.role.KimRole;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.KimRoleTypeService;

/**
 * This is a description of what this class does - jonathan don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class KimRoleTypeServiceBase extends KimTypeServiceBase implements KimRoleTypeService {

	public static final String ROLE_DOCUMENT_TYPE_NAME = "IdentityManagementRoleDocument";
	
	public String getWorkflowDocumentTypeName() {
		return ROLE_DOCUMENT_TYPE_NAME;
	}

	/**
	 * Performs a simple check that the qualifier on the role matches the qualification.
	 * Extra qualification attributes are ignored.
	 * 
	 * @see KimRoleTypeService#doesRoleQualifierMatchQualification(AttributeSet, AttributeSet)
	 */
	public boolean doesRoleQualifierMatchQualification(AttributeSet qualification, AttributeSet roleQualifier) {
		return performMatch(translateInputAttributeSet(qualification), roleQualifier);
	}
	
	/**
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#doRoleQualifiersMatchQualification(AttributeSet, List)
	 */
	public List<RoleMembershipInfo> doRoleQualifiersMatchQualification(AttributeSet qualification, List<RoleMembershipInfo> roleMemberList) {
		AttributeSet translatedQualification = translateInputAttributeSet(qualification);
		List<RoleMembershipInfo> matchingMemberships = new ArrayList<RoleMembershipInfo>();
		for ( RoleMembershipInfo rmi : roleMemberList ) {
			if ( performMatch( translatedQualification, rmi.getQualifier() ) ) {
				matchingMemberships.add( rmi );
			}
		}
		return matchingMemberships;
	}

	/**
	 * Return an empty list since this method should not be called by the role service for this service type.
	 * Subclasses which are application role types should override this method.
	 * 
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#getRoleMembersFromApplicationRole(String, String, AttributeSet)
	 */
	public List<RoleMembershipInfo> getRoleMembersFromApplicationRole(String namespaceCode, String roleName, AttributeSet qualification) {
		if ( !isApplicationRoleType() ) {
			throw new UnsupportedOperationException( this.getClass().getName() + " is not an application role." );
		} else {
			throw new UnsupportedOperationException( this.getClass().getName() + " is an application role type but has not overridden this method." );
		}
	}

	/**
	 * This simple initial implementation just calls  
	 * {@link #getRoleMembersFromApplicationRole(String, String, AttributeSet)} and checks the results.
	 * 
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#hasApplicationRole(java.lang.String, java.util.List, java.lang.String, java.lang.String, org.kuali.rice.kim.bo.types.dto.AttributeSet)
	 */
	public boolean hasApplicationRole(String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification) {
		if ( !isApplicationRoleType() ) {
			throw new UnsupportedOperationException( this.getClass().getName() + " is not an application role." );
		}
		// if principal ID given, check if it is in the list generated from the getPrincipalIdsFromApplicationRole method
		if ( StringUtils.isNotBlank( principalId ) ) {
		    List<RoleMembershipInfo> members = getRoleMembersFromApplicationRole(namespaceCode, roleName, qualification);
		    for ( RoleMembershipInfo rm : members ) {
		    	if ( StringUtils.isBlank( rm.getMemberId() ) ) {
		    		continue;
		    	}
		        if ( rm.getMemberTypeCode().equals( KimRole.PRINCIPAL_MEMBER_TYPE ) ) {
		            if ( rm.getMemberId().equals( principalId ) ) {
		                return true;
		            }
		        } else { // groups
		            if ( groupIds != null 
		                    && groupIds.contains(rm.getMemberId())) {
		                return true;
		            }
		        }
			}
		}
		return false;
	}
	
	/**
	 * Default to not being an application role type.  Always returns false.
	 * 
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#isApplicationRoleType()
	 */
	public boolean isApplicationRoleType() {
		return false;
	}
		
	/**
	/**
	 * No conversion performed.  Simply returns the passed in Map.
	 * 
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#convertQualificationAttributesToRequired(AttributeSet)
	 */
	public AttributeSet convertQualificationAttributesToRequired(
			AttributeSet qualificationAttributes) {
		return qualificationAttributes;
	}

	/**
	 * This base implementation simply returns the passed in AttributeSet.
	 * 
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#convertQualificationForMemberRoles(String, String, String, String, AttributeSet)
	 */
	public AttributeSet convertQualificationForMemberRoles(String namespaceCode, String roleName, String memberRoleNamespaceCode, String memberRoleName, AttributeSet qualification) {
		return qualification;
	}
	
	/**
	 * Base implementation: no sorting.  Just returns the input list.
	 * 
	 * @see org.kuali.rice.kim.service.support.KimRoleTypeService#sortRoleMembers(java.util.List)
	 */
	public List<RoleMembershipInfo> sortRoleMembers(List<RoleMembershipInfo> roleMembers) {
		return roleMembers;
	}
}
