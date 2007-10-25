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
package edu.iu.uis.eden.clientapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.rice.definition.DataDefinition;
import org.kuali.rice.definition.ObjectDefinition;
import org.kuali.rice.resourceloader.ObjectDefinitionResolver;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.clientapp.vo.UserIdVO;
import edu.iu.uis.eden.engine.node.BranchState;
import edu.iu.uis.eden.exception.WorkflowRuntimeException;
import edu.iu.uis.eden.routeheader.DocumentRouteHeaderValue;
import edu.iu.uis.eden.user.WorkflowUser;
import edu.iu.uis.eden.workgroup.Workgroup;

/**
 * Manages document state in relation to users seeing future requests for a particular document.  
 * Construct the object with a document and a user and ask it questions in relation to future requests.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class FutureRequestDocumentStateManager {
    
    private static final Logger LOG = Logger.getLogger(FutureRequestDocumentStateManager.class);
    
    private boolean receiveFutureRequests;
    private boolean doNotReceiveFutureRequests;
    private boolean clearFutureRequestState;
    
    private List<WorkflowUser> workgroupItemsToActivate = new ArrayList<WorkflowUser>();
    private List<WorkflowUser> workgroupItemsToNotActivate = new ArrayList<WorkflowUser>();
    private List<WorkflowUser> workgroupItemsWithDefaultActivation = new ArrayList<WorkflowUser>();
    
    public static final String FUTURE_REQUESTS_VAR_KEY = BranchState.VARIABLE_PREFIX + EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY;
    public static final String DEACTIVATED_REQUESTS_VARY_KEY = BranchState.VARIABLE_PREFIX + EdenConstants.DEACTIVATED_FUTURE_REQUESTS_BRANCH_STATE_KEY;
    
    public FutureRequestDocumentStateManager (DocumentRouteHeaderValue document, WorkflowUser user) {
	for (BranchState state : document.getRootBranchState()) {
	    if (isStateForUser(state, user)) {
		if (isReceiveFutureRequests(state)) {
		    this.receiveFutureRequests = true;
		} else if (isDoNotReceiveFutureRequests(state)) {
		    this.doNotReceiveFutureRequests = true;
		} else if (isClearFutureRequests(state)) {
		    this.clearFutureRequestState = true;
		    this.receiveFutureRequests = false;
		    this.doNotReceiveFutureRequests = false;
		    break;
		}
	    }
	}
	if (this.isClearFutureRequestState()) {
	    this.clearStateFromDocument(document);
	}
    }
    
    public FutureRequestDocumentStateManager (DocumentRouteHeaderValue document, Workgroup workgroup) {
	List<WorkflowUser> users = workgroup.getUsers(); 
	for (WorkflowUser user : users) {
	    FutureRequestDocumentStateManager requestStateMngr = new FutureRequestDocumentStateManager(document, user);
	    if (requestStateMngr.isReceiveFutureRequests()) {
		this.workgroupItemsToActivate.add(user);
		this.receiveFutureRequests = true;
	    } else if (requestStateMngr.isDoNotReceiveFutureRequests()) {
		this.workgroupItemsToNotActivate.add(user);
		this.doNotReceiveFutureRequests = true;
	    } else {
		this.workgroupItemsWithDefaultActivation.add(user);
	    }
	}
    }
    
    protected void clearStateFromDocument(DocumentRouteHeaderValue document) {
	for (BranchState state : document.getRootBranchState()) {
	    if (state.getKey().contains(FUTURE_REQUESTS_VAR_KEY)) {
		String values[] = state.getKey().split(",");
		state.setKey(DEACTIVATED_REQUESTS_VARY_KEY + "," + values[1] + "," + values[2] + "," + new Date().toString());
	    }
	}
	KEWServiceLocator.getRouteHeaderService().saveRouteHeader(document);
    }
    
    protected boolean isStateForUser(BranchState state, WorkflowUser user) {
	
	String[] values = state.getKey().split(",");
	if (values.length < 3 || ! values[0].contains(FUTURE_REQUESTS_VAR_KEY)) {
	    return false;
	}
	
	try {
	    ObjectDefinition ojbDef = new ObjectDefinition(values[1]);
	    ojbDef.addConstructorParameter(new DataDefinition(values[2], String.class));
	    UserIdVO userId = (UserIdVO) ObjectDefinitionResolver.createObject(ojbDef, this.getClass().getClassLoader(), false);
	    WorkflowUser stateUser = KEWServiceLocator.getUserService().getWorkflowUser(userId);
	    if (stateUser.getWorkflowId().equals(user.getWorkflowId())) {
		return true;
	    }
	} catch (Exception e) {
	    LOG.error("Unable to construct user object from recieveFutureRequests branch state", e);
	    throw new WorkflowRuntimeException(e);
	}
	
	return false;
    }
    
    protected boolean isReceiveFutureRequests(BranchState state) {
	return state.getValue().equals(EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE);
    }
    
    
    protected boolean isDoNotReceiveFutureRequests(BranchState state) {
	return state.getValue().equals(EdenConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE);
    }
    
    protected boolean isClearFutureRequests(BranchState state) {
	return state.getValue().equals(EdenConstants.CLEAR_FUTURE_REQUESTS_BRANCH_STATE_VALUE);
    }

    public boolean isClearFutureRequestState() {
        return this.clearFutureRequestState;
    }

    public boolean isDoNotReceiveFutureRequests() {
        return this.doNotReceiveFutureRequests;
    }

    public boolean isReceiveFutureRequests() {
        return this.receiveFutureRequests;
    }
    
    public static String getFutureRequestsKey(UserIdVO userId) {
	return EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY + "," + userId.getClass().getName() + "," + userId.toString() + "," + new Date().toString() + ", " + Math.random();
    }
    
    public static String getReceiveFutureRequestsValue() {
	return EdenConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE;
    }
    
    public static String getDoNotReceiveFutureRequestsValue() {
	return EdenConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE;
    }
    
    public static String getClearFutureRequestsValue() {
	return EdenConstants.CLEAR_FUTURE_REQUESTS_BRANCH_STATE_VALUE;
    }

    public List<WorkflowUser> getWorkgroupItemsToActivate() {
        return this.workgroupItemsToActivate;
    }

    public List<WorkflowUser> getWorkgroupItemsToNotActivate() {
        return this.workgroupItemsToNotActivate;
    }

    public List<WorkflowUser> getWorkgroupItemsWithDefaultActivation() {
        return this.workgroupItemsWithDefaultActivation;
    }

    
}