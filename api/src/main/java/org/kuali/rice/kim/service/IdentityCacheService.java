/*
 * Copyright 2007-2009 The Kuali Foundation
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
package org.kuali.rice.kim.service;

import org.kuali.rice.kim.bo.entity.dto.KimEntityDefaultInfo;

/**
 * 
 * This service implements a cache of KimEntityDefaultInfo. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public interface IdentityCacheService {

	/**
	 * Gets the KimEntityDefaultInfo from the cache for the given entityId.
	 */
    KimEntityDefaultInfo getEntityDefaultInfoFromPersistentCache( String entityId );

	/**
	 * Gets the KimEntityDefaultInfo from the cache for the entity with the given principalId.
	 */
    KimEntityDefaultInfo getEntityDefaultInfoFromPersistentCacheByPrincipalId( String principalId );

	/**
	 * Gets the KimEntityDefaultInfo from the cache for the entity with the given principalName.
	 */
	KimEntityDefaultInfo getEntityDefaultInfoFromPersistentCacheByPrincipalName( String principalName );
	
	/**
	 * Saves the given KimEntityDefaultInfo into the cache.
	 */
	void saveDefaultInfoToCache( KimEntityDefaultInfo entity );
	
}
