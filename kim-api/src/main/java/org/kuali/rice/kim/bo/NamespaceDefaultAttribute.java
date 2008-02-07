/* Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.kim.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;

public class NamespaceDefaultAttribute extends PersistableBusinessObjectBase {
    	private static final long serialVersionUID = -8332284694172302250L;
	private Long id;
	private Long namespaceId;
	private Long attributeTypeId;
	private String attributeName;
	private String description;
	private boolean required;
	private boolean active;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Long getAttributeTypeId() {
		return attributeTypeId;
	}

	public void setAttributeTypeId(Long attributeTypeId) {
		this.attributeTypeId = attributeTypeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> propMap = new LinkedHashMap<String, Object>();
        propMap.put("id", getId());
        propMap.put("namespaceId", getNamespaceId());
        propMap.put("attributeTypeId", getAttributeTypeId());
        propMap.put("attributeName", getAttributeName());
        propMap.put("value", getDescription());
        propMap.put("required", getRequired());
        propMap.put("active", getActive());
        return propMap;
	}

	public void refresh() {
		// not going to add unless needed
	}

	public Long getNamespaceId() {
	    return this.namespaceId;
	}

	public void setNamespaceId(Long namespaceId) {
	    this.namespaceId = namespaceId;
	}

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
