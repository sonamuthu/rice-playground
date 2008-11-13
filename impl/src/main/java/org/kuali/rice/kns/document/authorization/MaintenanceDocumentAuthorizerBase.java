/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.rice.kns.document.authorization;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.rice.kim.bo.FieldAttributeSecurity;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.IdentityManagementService;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kim.util.DocumentAttributeSecurityUtils;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.datadictionary.AttributeSecurity;
import org.kuali.rice.kns.datadictionary.MaintainableFieldDefinition;
import org.kuali.rice.kns.datadictionary.MaintainableItemDefinition;
import org.kuali.rice.kns.datadictionary.MaintainableSectionDefinition;
import org.kuali.rice.kns.datadictionary.MaintenanceDocumentEntry;
import org.kuali.rice.kns.datadictionary.mask.MaskFormatter;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class MaintenanceDocumentAuthorizerBase extends DocumentAuthorizerBase implements MaintenanceDocumentAuthorizer {

	private static IdentityManagementService identityManagementService;
	private static MaintenanceDocumentDictionaryService  maintenanceDocumentDictionaryService;

    /**
     * @see org.kuali.rice.kns.authorization.MaintenanceDocumentAuthorizer#getFieldAuthorizations(org.kuali.rice.kns.document.MaintenanceDocument,
     *      org.kuali.rice.kns.bo.user.KualiUser)
     */
    public MaintenanceDocumentAuthorizations getFieldAuthorizations(MaintenanceDocument document, Person user) {
        // by default, there are no restrictions, only if this method is
        // overridden by a subclass that adds restrictions
    	//return new MaintenanceDocumentAuthorizations();
    	
    	MaintenanceDocumentAuthorizations auths = new MaintenanceDocumentAuthorizations();
    	KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
    	String documentType = document.getDocumentHeader().getWorkflowDocument().getDocumentType();
    	String docStatus = workflowDocument.getRouteHeader().getDocRouteStatus();      	 
    	String documentNumber = document.getDocumentNumber();
    	String routeNodeNames = workflowDocument.getCurrentRouteNodeNames();
    	
    	MaintenanceDocumentEntry objectEntry = getMaintenanceDocumentDictionaryService().getMaintenanceDocumentEntry(documentType);
    	Map<String, FieldAttributeSecurity> restrictionFields = DocumentAttributeSecurityUtils.getRestrictionMaintainableFields(objectEntry);
    	
    	Set keys = restrictionFields.keySet();    
    	Iterator keyIter = keys.iterator();
        while (keyIter.hasNext()) { 
           String fullFieldName = (String) keyIter.next(); 
           FieldAttributeSecurity fieldAttributeSecurity = (FieldAttributeSecurity) restrictionFields.get(fullFieldName);
           String fieldName = fieldAttributeSecurity.getAttributeName();
           
           //TODO:Should use ParameterService.getDetailType to get the componentName
           String componentName = fieldAttributeSecurity.getBusinessObjectClass().getSimpleName();
           //TODO: Should use ParameterService getNameSpace to get name space
           //Should be nameSpaceCode in krim_perm_templ_t table
           String nameSpaceCode = "KR-KNS";
           
           AttributeSecurity maintainableFieldAttributeSecurity = (AttributeSecurity) fieldAttributeSecurity.getMaintainableFieldAttributeSecurity();
           AttributeSecurity  businessObjectAttributeSecurity = (AttributeSecurity) fieldAttributeSecurity.getBusinessObjectAttributeSecurity();
           
           AttributeSet permissionDetails = new AttributeSet();
       	   permissionDetails.put(KimConstants.KIM_ATTRIB_ROUTE_STATUS_CODE, docStatus);
       	   permissionDetails.put(KimConstants.KIM_ATTRIB_DOCUMENT_TYPE_NAME, documentType);
       	   permissionDetails.put(KimConstants.KIM_ATTRIB_DOCUMENT_NUMBER, documentNumber);
    	   permissionDetails.put(KimConstants.KIM_ATTRIB_ROUTE_NODE_NAME, routeNodeNames);
    	   permissionDetails.put(KimConstants.KIM_ATTRIB_PROPERTY_NAME, fieldName);
    	   permissionDetails.put(KimConstants.KIM_ATTRIB_NAMESPACE_CODE, nameSpaceCode);
    	   
    	   if(businessObjectAttributeSecurity != null && businessObjectAttributeSecurity.isReadOnly()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, componentName);
    		   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_EDIT_PROPERTY, permissionDetails, null)){
    			   auths.addReadonlyAuthField(fullFieldName);
    		   }
    	   }
    	   
    	   if(maintainableFieldAttributeSecurity != null && maintainableFieldAttributeSecurity.isReadOnly()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, documentType);
    		   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_EDIT_PROPERTY, permissionDetails, null)){
    			   auths.addReadonlyAuthField(fullFieldName);
    		   }
    	   }
    	   
    	   if(businessObjectAttributeSecurity != null && businessObjectAttributeSecurity.isPartialMask()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, componentName);
    		   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_PARTIALLY_UNMASK_PROPERTY, permissionDetails, null)){
    			   MaskFormatter partialMaskFormatter = businessObjectAttributeSecurity.getPartialMaskFormatter();
    			   auths.addPartiallyMaskedAuthField(fullFieldName, partialMaskFormatter);
    		   }
    	   }
    	   
    	   if(maintainableFieldAttributeSecurity != null  && maintainableFieldAttributeSecurity.isPartialMask()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, documentType);
			   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_PARTIALLY_UNMASK_PROPERTY, permissionDetails, null)){
				   MaskFormatter partialMaskFormatter = maintainableFieldAttributeSecurity.getPartialMaskFormatter();
				   auths.addPartiallyMaskedAuthField(fullFieldName, partialMaskFormatter);
			   }
		   }
    	   
    	   if(businessObjectAttributeSecurity != null && businessObjectAttributeSecurity.isMask()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, componentName);
    		   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_UNMASK_PROPERTY, permissionDetails, null)){
    		       MaskFormatter maskFormatter = businessObjectAttributeSecurity.getMaskFormatter();
    			   auths.addMaskedAuthField(fullFieldName, maskFormatter);
    		   }
    	   }
    	   
    	   if(maintainableFieldAttributeSecurity != null  && maintainableFieldAttributeSecurity.isMask()){  
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, documentType);
			   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_UNMASK_PROPERTY, permissionDetails, null)){
				   MaskFormatter maskFormatter = maintainableFieldAttributeSecurity.getMaskFormatter();
				   auths.addMaskedAuthField(fullFieldName, maskFormatter);
			   }
		   }
    	
    	   if(businessObjectAttributeSecurity != null && businessObjectAttributeSecurity.isHide()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, componentName);
    		   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_VIEW_PROPERTY, permissionDetails, null)){
    			   auths.addHiddenAuthField(fullFieldName);	  
    		   }
    	   }   

    	   if(maintainableFieldAttributeSecurity != null  && maintainableFieldAttributeSecurity.isHide()){
    		   permissionDetails.put(KimConstants.KIM_ATTRIB_COMPONENT_NAME, documentType);
			   if(!getIdentityManagementService().isAuthorizedByTemplateName(user.getPrincipalId(), nameSpaceCode, KimConstants.PERMISSION_VIEW_PROPERTY, permissionDetails, null)){
				   auths.addHiddenAuthField(fullFieldName);	  
			   }
		   }
  
        }    	
    	return auths; 
    }

    /**
     * 
     * @see org.kuali.rice.kns.authorization.DocumentAuthorizer#getDocumentActionFlags(org.kuali.rice.kns.document.Document,
     *      org.kuali.rice.kns.bo.user.KualiUser)
     */
    public DocumentActionFlags getDocumentActionFlags(Document document, Person user) {

        // run the super, let the common flags be set
        MaintenanceDocumentActionFlags docActionFlags = new MaintenanceDocumentActionFlags(super.getDocumentActionFlags(document, user));

        // run the fieldAuthorizations
        MaintenanceDocument maintDoc = (MaintenanceDocument) document;
        MaintenanceDocumentAuthorizations docAuths = getFieldAuthorizations(maintDoc, user);

        // if there are any field restrictions for this user, then we need to turn off the
        // ability to BlanketApprove, as this person doesnt have access to all the fields, so
        // they certainly cant blanket approve it.
        if (docAuths.hasAnyFieldRestrictions()) {
            docActionFlags.setCanBlanketApprove(false);
        }

        KualiWorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();

        // if a user can't initiate a document of this type then they can't copy one, either
        if (!canCopy(workflowDocument.getDocumentType(), user)) {
            docActionFlags.setCanCopy(false);
        }
        else {
            docActionFlags.setCanCopy(document.getAllowsCopy() && (!workflowDocument.stateIsInitiated() && !workflowDocument.stateIsEnroute() && !workflowDocument.stateIsException() && !workflowDocument.stateIsSaved()));
        }

        return docActionFlags;
    }
    
 
    /**
     * @see org.kuali.rice.kns.authorization.DocumentAuthorizer#getEditMode(org.kuali.rice.kns.document.Document,
     *      org.kuali.rice.kns.bo.user.KualiUser)
     */
    public Map getEditMode(Document document, Person user) {

        // if this is not a MaintenanceDocument, then fail loudly, something is badly wrong
        if (!MaintenanceDocument.class.isAssignableFrom(document.getClass())) {
            throw new IllegalArgumentException("A document was passed into MaintenanceDocumentAuthorizerBase.getEditMode() " + "that is not a MaintenanceDocument descendent.  Processing cannot continue.");
        }

        Map editMode = new HashMap();
        
        // cast the document as a MaintenanceDocument, and get a handle on the workflowDocument
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;
        KualiWorkflowDocument workflowDocument = maintenanceDocument.getDocumentHeader().getWorkflowDocument();

        // default to view-only, as a safety precaution
        String editModeKey = AuthorizationConstants.MaintenanceEditMode.VIEW_ONLY;

        // if the document is cancelled, then its view only
        if (workflowDocument.stateIsCanceled()) {
            editModeKey = AuthorizationConstants.MaintenanceEditMode.VIEW_ONLY;
        }

        // if the document is being edited, then its full entry, or if the current user is
        // the system supervisor
        else if (workflowDocument.stateIsInitiated() || workflowDocument.stateIsSaved()) {
            if (workflowDocument.userIsInitiator(user)) {
                editModeKey = AuthorizationConstants.MaintenanceEditMode.FULL_ENTRY;
                
                // initiators of documents for new records can view these fields for the documents while they're sitll under the control
                // of the initiators.  If they are always allowed access to the document, then they would be able to view the changes that
                // were made during routing, which would be a bad idea, as the router may have edited sensitive information enroute
                if (isDocumentForCreatingNewEntry(maintenanceDocument)) {
                    addAllMaintDocDefinedEditModesToMap(editMode, maintenanceDocument);
                }
            }
        }

        // if the document is in routing, then we have some special rules
        else if (workflowDocument.stateIsEnroute()) {

            // the person who has the approval request in their Actiong List
            // should be able to modify the document
            if (workflowDocument.isApprovalRequested()) {
                editModeKey = AuthorizationConstants.MaintenanceEditMode.APPROVER_EDIT_ENTRY;
            }
        }

        // save the editmode
        editMode.put(editModeKey, "TRUE");
        return editMode;
    }

    protected void addAllMaintDocDefinedEditModesToMap(Map editModes, MaintenanceDocument maintDoc) {
        String docTypeName = maintDoc.getDocumentHeader().getWorkflowDocument().getDocumentType();
        List<MaintainableSectionDefinition> sectionDefinitions = KNSServiceLocator.getMaintenanceDocumentDictionaryService().getMaintainableSections(docTypeName);
        
        for ( MaintainableSectionDefinition sectionDefinition : sectionDefinitions ) {
            for ( MaintainableItemDefinition itemDefinition : sectionDefinition.getMaintainableItems() ) {
                if (itemDefinition instanceof MaintainableFieldDefinition) {
                    /*String displayEditMode = ((MaintainableFieldDefinition) itemDefinition).getDisplayEditMode();
                    if (StringUtils.isNotBlank(displayEditMode)) {
                        editModes.put(displayEditMode, "TRUE");
                    }*/
                }
                // TODO: what about MaintainableCollectionDefinition?
            }
        }
    }
    
    /**
     * This method returns whether this document is creating a new entry in the maintenible/underlying table
     * 
     * This method is useful to determine whether all the field-based edit modes should be enabled, which is 
     * useful in determining which fields are encrypted
     * 
     * This method considers that Constants.MAINTENANCE_NEWWITHEXISTING_ACTION is not a new document because 
     * there is uncertainity how documents with this action will actually be implemented
     * 
     * @param maintDoc
     * @param user
     * @return
     */
    protected boolean isDocumentForCreatingNewEntry(MaintenanceDocument maintDoc) {
        // the rule is as follows: if the maint doc represents a new record AND the user is the same user who initiated the maintenance doc
        // if the user check is not added, then it would be pointless to do any encryption since I can just pull up a document to view the encrypted values
        
        // A maint doc is new when the new maintainable maintenance flag is set to either Constants.MAINTENANCE_NEW_ACTION or Constants.MAINTENANCE_COPY_ACTION
        String maintAction = maintDoc.getNewMaintainableObject().getMaintenanceAction();
        return (KNSConstants.MAINTENANCE_NEW_ACTION.equals(maintAction) || KNSConstants.MAINTENANCE_COPY_ACTION.equals(maintAction));
    }
    
	/**
	 * @return the identityManagementService
	 */
	public static IdentityManagementService getIdentityManagementService() {
		
		if (identityManagementService == null ) {
			identityManagementService = KIMServiceLocator.getIdentityManagementService();
		}
		return identityManagementService;
	}
    
	
	/**
	 * @return the maintenanceDocumentDictionaryService
	 */
	public static MaintenanceDocumentDictionaryService getMaintenanceDocumentDictionaryService() {
		
		if (maintenanceDocumentDictionaryService == null ) {
			maintenanceDocumentDictionaryService = KNSServiceLocator.getMaintenanceDocumentDictionaryService();
		}
		return maintenanceDocumentDictionaryService;
	}

    
}

