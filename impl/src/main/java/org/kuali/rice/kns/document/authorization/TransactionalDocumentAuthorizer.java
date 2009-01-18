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

import java.util.Map;
import java.util.Set;

import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kns.document.Document;


/**
 * Extension to DocumentAuthorizer interface which adds transactional-document-specific methods.
 */
public interface TransactionalDocumentAuthorizer extends DocumentAuthorizer {	
	/**
     * @param document
     * @return Map of operations that allow to take on that document.
     */
    public Set<String> getEditModes(Document document, Person user, Set<String> editModes);
}
