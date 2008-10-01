package org.kuali.rice.kim.service;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kew.user.UserService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.AuthorizationService;
import org.kuali.rice.kim.service.GroupService;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kim.service.IdentityService;
import org.kuali.rice.kim.service.PersonService;

/**
 * Service locator for KIM.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public final class KIMServiceLocator {

	private static final Logger LOG = Logger.getLogger(KIMServiceLocator.class);

    public static final String KIM_IDENTITY_MANAGEMENT_SERVICE = "kimIdentityManagementService";
	public static final String KIM_IDENTITY_SERVICE = "kimIdentityService";
	public static final String KIM_GROUP_SERVICE = "kimGroupService";
	public static final String KIM_ROLE_SERVICE = "kimRoleService";
	public static final String KIM_AUTHORIZATION_SERVICE = "kimAuthorizationService";
	public static final String KIM_PERSON_SERVICE = "kimPersonService";
	public static final String KIM_AUTHENTICATION_SERVICE = "kimAuthenticationService";
	public static final String KIM_PERMISSION_SERVICE = "kimPermissionService";
	public static final String KIM_RESPONSIBILITY_SERVICE = "kimResponsibilityService";
    
	// The temporary workflow shim (v2)
	public static final String KIM_USER_SERVICE = "kimUserService";
    
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
    
    public static GroupService getGroupService() {
    	return (GroupService)getService(KIM_GROUP_SERVICE);
    }

    public static RoleService getRoleService() {
    	return (RoleService)getService(KIM_ROLE_SERVICE);
    }

    public static AuthorizationService getAuthorizationService() {
    	return (AuthorizationService)getService(KIM_AUTHORIZATION_SERVICE);
    }

    public static PermissionService getPermissionService() {
    	return (PermissionService)getService(KIM_PERMISSION_SERVICE);
    }

    public static ResponsibilityService getResponsibilityService() {
    	return (ResponsibilityService)getService(KIM_RESPONSIBILITY_SERVICE);
    }
    
    
    public static AuthenticationService getAuthenticationService() {
    	return (AuthenticationService)getService(KIM_AUTHENTICATION_SERVICE);
    }

    public static UserService getUserService() {
    	return (UserService)getService(KIM_USER_SERVICE);
    }
    
    @SuppressWarnings("unchecked")
	public static PersonService<Person> getPersonService() {
    	return (PersonService<Person>)getService(KIM_PERSON_SERVICE);
    }
}