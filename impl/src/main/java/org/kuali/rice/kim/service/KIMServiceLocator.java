package org.kuali.rice.kim.service;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.support.KimTypeInternalService;

/**
 * Service locator for KIM.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public final class KIMServiceLocator {

	private static final Logger LOG = Logger.getLogger(KIMServiceLocator.class);

    public static final String KIM_IDENTITY_MANAGEMENT_SERVICE = "kimIdentityManagementService";
    public static final String KIM_ROLE_MANAGEMENT_SERVICE = "kimRoleManagementService";
	public static final String KIM_PERSON_SERVICE = "personService";
	
    public static final String KIM_IDENTITY_SERVICE = "kimIdentityService";
    public static final String KIM_IDENTITY_UPDATE_SERVICE = "kimIdentityUpdateService";
	public static final String KIM_IDENTITY_CACHE_SERVICE = "kimIdentityCacheService";

	public static final String KIM_GROUP_SERVICE = "kimGroupService";
	public static final String KIM_GROUP_UPDATE_SERVICE = "kimGroupUpdateService";
	
	public static final String KIM_ROLE_SERVICE = "kimRoleService";
	public static final String KIM_ROLE_UPDATE_SERVICE = "kimRoleUpdateService";

	public static final String KIM_PERMISSION_SERVICE = "kimPermissionService";
	public static final String KIM_PERMISSION_UPDATE_SERVICE = "kimPermissionUpdateService";

	public static final String KIM_RESPONSIBILITY_SERVICE = "kimResponsibilityService";
	public static final String KIM_RESPONSIBILITY_UPDATE_SERVICE = "kimResponsibilityUpdateService";

	public static final String KIM_AUTHENTICATION_SERVICE = "kimAuthenticationService";
	public static final String KIM_TYPE_INTERNAL_SERVICE = "kimTypeInternalService";
	public static final String KIM_UI_DOCUMENT_SERVICE = "kimUiDocumentService";

	
	public static Object getService(String serviceName) {
		return getBean(serviceName);
	}

	public static Object getBean(String serviceName) {
		if ( LOG.isDebugEnabled() ) {
			LOG.debug("Fetching service " + serviceName);
		}
		return GlobalResourceLoader.getResourceLoader().getService(new QName(serviceName));
	}

    public static IdentityManagementService getIdentityManagementService() {
    	return (IdentityManagementService)getService(KIM_IDENTITY_MANAGEMENT_SERVICE);
    }

    public static IdentityService getIdentityService() {
    	return (IdentityService)getService(KIM_IDENTITY_SERVICE);
    }

    public static IdentityUpdateService getIdentityUpdateService() {
    	return (IdentityUpdateService)getService(KIM_IDENTITY_UPDATE_SERVICE);
    }
    
    public static IdentityCacheService getIdentityCacheService() {
    	return (IdentityCacheService)getService(KIM_IDENTITY_CACHE_SERVICE);
    }

    public static GroupService getGroupService() {
    	return (GroupService)getService(KIM_GROUP_SERVICE);
    }

    public static GroupUpdateService getGroupUpdateService() {
    	return (GroupUpdateService)getService(KIM_GROUP_UPDATE_SERVICE);
    }

    public static RoleService getRoleService() {
    	return (RoleService)getService(KIM_ROLE_SERVICE);
    }

    public static RoleUpdateService getRoleUpdateService() {
    	return (RoleUpdateService)getService(KIM_ROLE_UPDATE_SERVICE);
    }
    
    public static RoleManagementService getRoleManagementService() {
    	return (RoleManagementService)getService(KIM_ROLE_MANAGEMENT_SERVICE);
    }

    public static PermissionService getPermissionService() {
    	return (PermissionService)getService(KIM_PERMISSION_SERVICE);
    }

    public static PermissionUpdateService getPermissionUpdateService() {
    	return (PermissionUpdateService)getService(KIM_PERMISSION_UPDATE_SERVICE);
    }
    
    public static ResponsibilityService getResponsibilityService() {
    	return (ResponsibilityService)getService(KIM_RESPONSIBILITY_SERVICE);
    }

    public static ResponsibilityUpdateService getResponsibilityUpdateService() {
    	return (ResponsibilityUpdateService)getService(KIM_RESPONSIBILITY_UPDATE_SERVICE);
    }
    
    public static KimTypeInternalService getTypeInternalService() {
        return (KimTypeInternalService)getService(KIM_TYPE_INTERNAL_SERVICE);
    }

    public static AuthenticationService getAuthenticationService() {
    	return (AuthenticationService)getService(KIM_AUTHENTICATION_SERVICE);
    }

    public static UiDocumentService getUiDocumentService() {
    	return (UiDocumentService)getService(KIM_UI_DOCUMENT_SERVICE);
    }

    @SuppressWarnings("unchecked")
	public static PersonService getPersonService() {
    	return (PersonService)getService(KIM_PERSON_SERVICE);
    }
}