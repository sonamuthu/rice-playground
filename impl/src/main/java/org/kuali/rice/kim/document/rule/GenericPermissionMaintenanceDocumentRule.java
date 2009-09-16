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
package org.kuali.rice.kim.document.rule;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.bo.impl.GenericPermission;
import org.kuali.rice.kim.bo.role.dto.KimPermissionTemplateInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.bo.types.dto.KimTypeInfo;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kim.service.support.KimPermissionTypeService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This is a description of what this class does - kellerj don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class GenericPermissionMaintenanceDocumentRule extends
		MaintenanceDocumentRuleBase {
	protected static final String DETAIL_VALUES_PROPERTY = "detailValues";
	protected static final String ERROR_MESSAGE_PREFIX = "error.document.kim.genericpermission.";
	protected static final String ERROR_MISSING_TEMPLATE = ERROR_MESSAGE_PREFIX + "missingtemplate";
	protected static final String ERROR_UNKNOWN_ATTRIBUTE = ERROR_MESSAGE_PREFIX + "unknownattribute";
	protected static final String ERROR_ATTRIBUTE_VALIDATION = ERROR_MESSAGE_PREFIX + "attributevalidation";
	
	@Override
	protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
		boolean rulesPassed = true;
		try {
			GenericPermission perm = (GenericPermission)getNewBo();
			// detailValues
			// get the type from the template for validation
			KimPermissionTemplateInfo template = KIMServiceLocator.getPermissionService().getPermissionTemplate( perm.getTemplateId() );
			if ( template == null ) {
				GlobalVariables.getMessageMap().addToErrorPath( MAINTAINABLE_ERROR_PATH );
				GlobalVariables.getMessageMap().putError( DETAIL_VALUES_PROPERTY, ERROR_MISSING_TEMPLATE, perm.getTemplateId() );
				GlobalVariables.getMessageMap().removeFromErrorPath( MAINTAINABLE_ERROR_PATH );
				rulesPassed = false;
			} else {
				KimTypeInfo kimType = KIMServiceLocator.getTypeInfoService().getKimType( template.getKimTypeId() );
				AttributeSet details = perm.getDetails();
				// check that add passed attributes are defined
				for ( String attributeName : details.keySet() ) {
					if ( kimType.getAttributeDefinitionByName(attributeName) == null ) {
						GlobalVariables.getMessageMap().addToErrorPath( MAINTAINABLE_ERROR_PATH );
						GlobalVariables.getMessageMap().putError( DETAIL_VALUES_PROPERTY, ERROR_UNKNOWN_ATTRIBUTE, attributeName, template.getNamespaceCode(), template.getName() );
						GlobalVariables.getMessageMap().removeFromErrorPath( MAINTAINABLE_ERROR_PATH );
						rulesPassed = false;
					}
				}
				// if all attributes are known, pass to the service for validation
				if ( !GlobalVariables.getMessageMap().hasErrors() ) {
					KimPermissionTypeService service = getPermissionTypeService( kimType.getKimTypeServiceName() );
					if ( service != null ) {
						AttributeSet validationErrors = service.validateAttributes( kimType.getKimTypeId(), details);
						if ( validationErrors != null && !validationErrors.isEmpty() ) {
							for ( String attributeName : validationErrors.keySet() ) {
								GlobalVariables.getMessageMap().addToErrorPath( MAINTAINABLE_ERROR_PATH );
								GlobalVariables.getMessageMap().putError( DETAIL_VALUES_PROPERTY, ERROR_ATTRIBUTE_VALIDATION, attributeName, validationErrors.get(attributeName) );
								GlobalVariables.getMessageMap().removeFromErrorPath( MAINTAINABLE_ERROR_PATH );
							}
							rulesPassed = false;
						}
					}
				}
			}
			// check each permission name against the type
		} catch ( RuntimeException ex ) {
			LOG.error( "Error in processCustomRouteDocumentBusinessRules()", ex );
			throw ex;
		}
		return rulesPassed;
	}

    protected KimPermissionTypeService getPermissionTypeService( String serviceName ) {
    	if ( StringUtils.isBlank( serviceName ) ) {
    		return null;
    	}
    	try {
	    	Object service = KIMServiceLocator.getService( serviceName );
	    	// if we have a service name, it must exist
	    	if ( service == null ) {
				LOG.warn("null returned for permission type service for service name: " + serviceName);
	    	} else {
		    	// whatever we retrieved must be of the correct type
		    	if ( !(service instanceof KimPermissionTypeService)  ) {
		    		LOG.warn( "Service " + serviceName + " was not a KimPermissionTypeService.  Was: " + service.getClass().getName() );
		    		service = null;
		    	}
	    	}
	    	return (KimPermissionTypeService)service;
    	} catch( Exception ex ) {
    		LOG.error( "Error retrieving service: " + serviceName + " from the KIMServiceLocator.", ex );
    	}
    	return null;
    }

}
