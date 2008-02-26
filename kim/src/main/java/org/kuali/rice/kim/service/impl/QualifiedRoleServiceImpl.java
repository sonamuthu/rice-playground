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
package org.kuali.rice.kim.service.impl;

import java.util.List;
import java.util.Map;

import org.kuali.rice.kim.dto.EntityDTO;
import org.kuali.rice.kim.dto.GroupDTO;
import org.kuali.rice.kim.dto.PersonDTO;
import org.kuali.rice.kim.dto.PrincipalDTO;
import org.kuali.rice.kim.dto.RoleDTO;
import org.kuali.rice.kim.service.QualifiedRoleService;

/**
 * This is the default KIM QualifiedRoleService implementation that is provided by Rice.  This will mature over time as the KIM 
 * component is developed.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class QualifiedRoleServiceImpl implements QualifiedRoleService {

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getGroupNames(java.lang.String, java.util.Map)
     */
    public List<String> getGroupNames(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getGroups(java.lang.String, java.util.Map)
     */
    public List<GroupDTO> getGroups(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getPersonIds(java.lang.String, java.util.Map)
     */
    public List<Long> getPersonIds(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getPersons(java.lang.String, java.util.Map)
     */
    public List<PersonDTO> getPersons(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getPrincipalNames(java.lang.String, java.util.Map)
     */
    public List<String> getPrincipalNames(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getPrincipals(java.lang.String, java.util.Map)
     */
    public List<PrincipalDTO> getPrincipals(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getRoleNames(java.util.Map)
     */
    public List<String> getRoleNames(Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getRoles(java.util.Map)
     */
    public List<RoleDTO> getRoles(Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getEntityIds(java.lang.String, java.util.Map)
     */
    public List<Long> getEntityIds(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kim.service.QualifiedRoleService#getEntitys(java.lang.String, java.util.Map)
     */
    public List<EntityDTO> getEntitys(String roleName, Map<String, String> qualifiedRoleAttributes) {
        // TODO ag266 - THIS METHOD NEEDS JAVADOCS
        return null;
    }
    
    
}
