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
package org.kuali.rice.kew.test.services;

import org.kuali.rice.kew.exception.KEWUserNotFoundException;
import org.kuali.rice.kew.user.UserId;
import org.kuali.rice.kew.user.WorkflowUser;


public interface EchoService {

	public String echo(String string);
	
	public WorkflowUser getUser(UserId userId) throws KEWUserNotFoundException;
	
	public String trueEcho(String string);
}
