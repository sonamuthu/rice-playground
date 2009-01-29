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

import org.kuali.rice.kim.bo.role.dto.KimPermissionInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.KimPermissionTypeService;

/**
 * This is a description of what this class does - bhargavp don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class KimPermissionTypeServiceBase extends KimTypeServiceBase implements KimPermissionTypeService {

	protected List<String> requiredAttributes = new ArrayList<String>();
	
	/**
	 * @see org.kuali.rice.kim.service.support.KimPermissionTypeService#getMatchingPermissions(AttributeSet, List)
	 */
	public final List<KimPermissionInfo> getMatchingPermissions( AttributeSet requestedDetails, List<KimPermissionInfo> permissionsList ) {
		requestedDetails = translateInputAttributeSet(requestedDetails);
		validateRequiredAttributesAgainstReceived(requiredAttributes, requestedDetails, REQUESTED_DETAILS_RECEIVED_ATTIBUTES_NAME);
		return performPermissionMatches(requestedDetails, permissionsList);
	}

	/**
	 * Internal method for matching permissions.  Override this method to customize the matching behavior.
	 * 
	 * This base implementation uses the {@link #performMatch(AttributeSet, AttributeSet)} method
	 * to perform an exact match on the permission details and return all that are equal.
	 */
	protected List<KimPermissionInfo> performPermissionMatches(AttributeSet requestedDetails, List<KimPermissionInfo> permissionsList) {
		List<KimPermissionInfo> matchingPermissions = new ArrayList<KimPermissionInfo>();
		for (KimPermissionInfo permission : permissionsList) {
			if ( performMatch(requestedDetails, permission.getDetails()) ) {
				matchingPermissions.add( permission );
			}
		}
		return matchingPermissions;
	}
}
