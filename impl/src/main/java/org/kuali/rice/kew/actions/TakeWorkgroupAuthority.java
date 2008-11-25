/*
 * Copyright 2005-2006 The Kuali Foundation.
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
package org.kuali.rice.kew.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.rice.kew.actionitem.ActionItem;
import org.kuali.rice.kew.actionlist.service.ActionListService;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.exception.KEWUserNotFoundException;
import org.kuali.rice.kew.exception.InvalidActionTakenException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.user.WorkflowUser;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.Utilities;
import org.kuali.rice.kew.workgroup.Workgroup;
import org.kuali.rice.kim.bo.group.KimGroup;


/**
 * Removes all workgroup action items for a document from everyone's action list except the person 
 * who took the workgroup authority
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class TakeWorkgroupAuthority extends ActionTakenEvent {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TakeWorkgroupAuthority.class);
    
    private KimGroup group;
    
    /**
     * @param routeHeader
     * @param user
     */
    public TakeWorkgroupAuthority(DocumentRouteHeaderValue routeHeader, WorkflowUser user) {
        super(KEWConstants.ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY_CD, routeHeader, user);
    }

    /**
     * @param routeHeader
     * @param user
     * @param annotation
     * @param workgroup
     */
    public TakeWorkgroupAuthority(DocumentRouteHeaderValue routeHeader, WorkflowUser user, String annotation, KimGroup group) {
        super(KEWConstants.ACTION_TAKEN_TAKE_WORKGROUP_AUTHORITY_CD, routeHeader, user, annotation);
        this.group = group;
    }

    /* (non-Javadoc)
     * @see org.kuali.rice.kew.actions.ActionTakenEvent#validateActionRules()
     */
    @Override
    public String validateActionRules() throws KEWUserNotFoundException {
        if  ( (group != null) && (!group.isActive())) {
            return (getUser().getAuthenticationUserId() + " not a member of workgroup " + group.getGroupName());
        }
        return "";
    }

    public void recordAction() throws InvalidActionTakenException, KEWUserNotFoundException {
        
        String errorMessage = validateActionRules();
        if (!Utilities.isEmpty(errorMessage)) {
            throw new InvalidActionTakenException(errorMessage);
        }
//        if (! workgroup.hasMember(getUser())) {
//            throw new InvalidActionTakenException(getUser().getAuthenticationUserId() + " not a member of workgroup " + workgroup.getDisplayName());
//        }
        
        List documentRequests = getActionRequestService().findPendingByDoc(getRouteHeaderId());
        List workgroupRequests = new ArrayList();
        for (Iterator iter = documentRequests.iterator(); iter.hasNext();) {
            ActionRequestValue actionRequest = (ActionRequestValue) iter.next();
            if (actionRequest.isGroupRequest() && actionRequest.getGroup().getGroupId().equals(group.getGroupId())) {
                workgroupRequests.add(actionRequest);
            }
        }
        
        ActionTakenValue actionTaken = saveActionTaken(findDelegatorForActionRequests(workgroupRequests));
        notifyActionTaken(actionTaken);
        
        ActionListService actionListService = KEWServiceLocator.getActionListService();
        Collection actionItems = actionListService.findByRouteHeaderId(getRouteHeaderId());
        for (Iterator iter = actionItems.iterator(); iter.hasNext();) {
            ActionItem actionItem = (ActionItem) iter.next();
            //delete all requests for this workgroup on this document not to this user
            if (actionItem.isWorkgroupItem() && actionItem.getGroupId().equals(group.getGroupId()) &&
                    ! actionItem.getWorkflowId().equals(getUser().getWorkflowId())) {
                actionListService.deleteActionItem(actionItem);
            }
        }
    }
}