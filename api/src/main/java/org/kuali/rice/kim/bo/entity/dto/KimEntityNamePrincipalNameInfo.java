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
package org.kuali.rice.kim.bo.entity.dto;

import static org.kuali.rice.kim.bo.entity.dto.DtoUtils.unNullify;

import org.kuali.rice.kim.bo.entity.KimEntityNamePrincipalName;

/**
 * DTO to be used for caching default EntityNames with the PrincipalId
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class KimEntityNamePrincipalNameInfo implements KimEntityNamePrincipalName {

	protected KimEntityNameInfo defaultEntityName;
	protected String principalName;
	
	/**
	 * @return the defaultEntityName
	 */
	public KimEntityNameInfo getDefaultEntityName() {
		return unNullify(this.defaultEntityName, KimEntityNameInfo.class);
	}
	/**
	 * @param defaultEntityName the defaultEntityName to set
	 */
	public void setDefaultEntityName(KimEntityNameInfo defaultEntityName) {
		this.defaultEntityName = defaultEntityName;
	}
	/**
	 * @return the principalName
	 */
	public String getPrincipalName() {
		return unNullify(this.principalName);
	}
	/**
	 * @param principalName the principalName to set
	 */
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
}
