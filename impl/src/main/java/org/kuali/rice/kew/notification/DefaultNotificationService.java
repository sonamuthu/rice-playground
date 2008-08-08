/*
 * Copyright 2005-2007 The Kuali Foundation.
 * 
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
package org.kuali.rice.kew.notification;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.ComparatorUtils;
import org.kuali.rice.kew.KEWServiceLocator;
import org.kuali.rice.kew.actionitem.ActionItem;
import org.kuali.rice.kew.actionitem.ActionItemComparator;
import org.kuali.rice.kew.doctype.DocumentType;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.exception.KEWUserNotFoundException;
import org.kuali.rice.kew.exception.WorkflowRuntimeException;
import org.kuali.rice.kew.mail.service.ActionListImmediateEmailReminderService;
import org.kuali.rice.kew.messaging.MessageServiceNames;
import org.kuali.rice.kew.preferences.Preferences;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.user.WorkflowUser;
import org.kuali.rice.kew.user.WorkflowUserId;
import org.kuali.rice.kew.util.KEWConstants;


/**
 * The default implementation of the NotificationService.
 *   
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class DefaultNotificationService implements NotificationService {

	protected final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(getClass());
	
	private static final Comparator notificationPriorityComparator = ComparatorUtils.reversedComparator(new ActionItemComparator());
	
	/**
	 * Queues up immediate email processors for ActionItem notification.  Prioritizes the list of
	 * Action Items passed in and attempts to not send out multiple emails to the same user.
	 */
	public void notify(List<ActionItem> actionItems) {
		// sort the list of action items using the same comparator as the Action List
		Collections.sort(actionItems, notificationPriorityComparator);
		Set sentNotifications = new HashSet();
		for (Iterator iterator = actionItems.iterator(); iterator.hasNext();) {
			ActionItem actionItem = (ActionItem) iterator.next();
			if (!sentNotifications.contains(actionItem.getWorkflowId()) && shouldNotify(actionItem)) {
				sentNotifications.add(actionItem.getWorkflowId());
				sendNotification(actionItem);
			}
		}
	}

	/**
	 * Sends a notification
	 * @param actionItem the action item
	 */
	protected void sendNotification(ActionItem actionItem) {
        ActionListImmediateEmailReminderService immediateEmailService = MessageServiceNames.getImmediateEmailService();
        immediateEmailService.sendReminder(actionItem, RouteContext.getCurrentRouteContext().isDoNotSendApproveNotificationEmails());
	}

	protected boolean shouldNotify(ActionItem actionItem) {
		try {
			WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(new WorkflowUserId(actionItem.getWorkflowId()));
			Preferences preferences = KEWServiceLocator.getPreferencesService().getPreferences(user);
			boolean sendEmail = false;
			if (KEWConstants.EMAIL_RMNDR_IMMEDIATE.equals(preferences.getEmailNotification())) {
				if (KEWConstants.DELEGATION_PRIMARY.equals(actionItem.getDelegationType())) {
					sendEmail = KEWConstants.PREFERENCES_YES_VAL.equals(preferences.getNotifyPrimaryDelegation());
				} else if (KEWConstants.DELEGATION_SECONDARY.equals(actionItem.getDelegationType())) {
					sendEmail = KEWConstants.PREFERENCES_YES_VAL.equals(preferences.getNotifySecondaryDelegation());
				} else {
					sendEmail = true;
				}
			}
			// don't send notification if this action item came from a SAVE action and the NOTIFY_ON_SAVE policy is not set
			if (sendEmail && isItemOriginatingFromSave(actionItem) && !shouldNotifyOnSave(actionItem)) {
				sendEmail = false;
			}
			return sendEmail;
		} catch (KEWUserNotFoundException e) {
			throw new WorkflowRuntimeException("Error loading user with workflow id " + actionItem.getWorkflowId() + " for notification.", e);
		}
	}

	/**
	 * Returns true if the ActionItem doesn't represent a request generated from a "SAVE" action or, if it does,
	 * returns true if the document type policy
	 */
	protected boolean isItemOriginatingFromSave(ActionItem actionItem) {
		return actionItem.getResponsibilityId() != null && actionItem.getResponsibilityId().equals(KEWConstants.SAVED_REQUEST_RESPONSIBILITY_ID);
	}
	
	protected boolean shouldNotifyOnSave(ActionItem actionItem) {
		DocumentRouteHeaderValue document = KEWServiceLocator.getRouteHeaderService().getRouteHeader(actionItem.getRouteHeaderId());
		DocumentType documentType = KEWServiceLocator.getDocumentTypeService().findById(document.getDocumentTypeId());
		return documentType.getNotifyOnSavePolicy().getPolicyValue().booleanValue();
	}

    public void removeNotification(List<ActionItem> actionItems) {
        // nothing
    }	
}