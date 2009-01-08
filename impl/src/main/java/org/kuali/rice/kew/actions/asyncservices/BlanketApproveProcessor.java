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
package org.kuali.rice.kew.actions.asyncservices;

import java.util.Set;

import org.kuali.rice.kew.actions.BlanketApproveAction;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.exception.WorkflowRuntimeException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kim.bo.entity.KimPrincipal;


/**
 * Responsible for invoking the async piece of BlanketApprove
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class BlanketApproveProcessor implements BlanketApproveProcessorService {
	
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BlanketApproveProcessor.class);

	public void doBlanketApproveWork(Long documentId, String principalId, Long actionTakenId, Set<String> nodeNames) {
		KEWServiceLocator.getRouteHeaderService().lockRouteHeader(documentId, true);
		DocumentRouteHeaderValue document = KEWServiceLocator.getRouteHeaderService().getRouteHeader(documentId);
		ActionTakenValue actionTaken = KEWServiceLocator.getActionTakenService().findByActionTakenId(actionTakenId);
		KimPrincipal principal = KEWServiceLocator.getIdentityHelperService().getPrincipal(principalId);
		BlanketApproveAction blanketApprove = new BlanketApproveAction(document, principal, "", nodeNames);
		LOG.debug("Doing blanket approve work document " + document.getRouteHeaderId());
		try {
			blanketApprove.performDeferredBlanketApproveWork(actionTaken);
		} catch (Exception e) {
			throw new WorkflowRuntimeException(e);
		}
		LOG.debug("Work done and document requeued, document " + document.getRouteHeaderId());
	}
}
