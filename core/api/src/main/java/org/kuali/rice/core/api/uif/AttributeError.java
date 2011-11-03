/**
 * Copyright 2005-2011 The Kuali Foundation
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
package org.kuali.rice.core.api.uif;

import java.util.List;

/**
 * Defines a list of one or more errors for an attribute.
 */
public interface AttributeError {

    /**
     * The name of the attribute.  Will never be a blank or null string.
     * @return attribute name
     */
    String getAttributeName();

    /**
     * A list of errors associated with an attribute.  Will never return null or an empty list.
     *
     *
     * @return list of errors.
     */
    List<String> getErrors();

}
