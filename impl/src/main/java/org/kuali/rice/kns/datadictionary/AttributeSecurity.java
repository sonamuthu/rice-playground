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
package org.kuali.rice.kns.datadictionary;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.kew.attribute.Attribute;
import org.kuali.rice.kns.datadictionary.exception.AttributeValidationException;
import org.kuali.rice.kns.datadictionary.mask.MaskFormatter;

/**
 * This is a description of what this class does - mpham don't forget to fill
 * this in.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 * 
 */
public class AttributeSecurity extends DataDictionaryDefinitionBase {

	boolean readOnly = false;
	boolean hide = false;
	boolean mask = false;
	boolean partialMask = false;
	MaskFormatter partialMaskFormatter;
	MaskFormatter maskFormatter;

	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return this.readOnly;
	}

	/**
	 * @param readOnly
	 *            the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * @return the hide
	 */
	public boolean isHide() {
		return this.hide;
	}

	/**
	 * @param hide
	 *            the hide to set
	 */
	public void setHide(boolean hide) {
		this.hide = hide;
	}

	/**
	 * @return the mask
	 */
	public boolean isMask() {
		return this.mask;
	}

	/**
	 * @param mask
	 *            the mask to set
	 */
	public void setMask(boolean mask) {
		this.mask = mask;
	}

	/**
	 * @return the partialMask
	 */
	public boolean isPartialMask() {
		return this.partialMask;
	}

	/**
	 * @param partialMask
	 *            the partialMask to set
	 */
	public void setPartialMask(boolean partialMask) {
		this.partialMask = partialMask;
	}

	/**
	 * @return the maskFormatter
	 */
	public MaskFormatter getMaskFormatter() {
		return this.maskFormatter;
	}

	/**
	 * @param maskFormatter
	 *            the maskFormatter to set
	 */
	public void setMaskFormatter(MaskFormatter maskFormatter) {
		this.maskFormatter = maskFormatter;
	}

	/**
	 * @return the partialMaskFormatter
	 */
	public MaskFormatter getPartialMaskFormatter() {
		return this.partialMaskFormatter;
	}

	/**
	 * @param partialMaskFormatter
	 *            the partialMaskFormatter to set
	 */
	public void setPartialMaskFormatter(MaskFormatter partialMaskFormatter) {
		this.partialMaskFormatter = partialMaskFormatter;
	}

	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.kns.datadictionary.DataDictionaryDefinition#completeValidation(java.lang.Class,
	 *      java.lang.Class)
	 */
	public void completeValidation(Class rootBusinessObjectClass,
			Class otherBusinessObjectClass) {

		if (mask && maskFormatter == null) {
			throw new AttributeValidationException("MaskFormatter is required");
		}
		if (partialMask && partialMaskFormatter == null) {
			throw new AttributeValidationException(
					"PartialMaskFormatter is required");
		}
	}

	public String getDisplayMaskValue(Object value){
		String displayMaskValue = "";
        if(isMask())
        	displayMaskValue = getMaskFormatter().maskValue(value);
        else if(isPartialMask())
        	displayMaskValue = getPartialMaskFormatter().maskValue(value);
        return displayMaskValue;
	}
	
	public boolean shouldBeEncrypted(){
		return isMask() || isPartialMask();
	}
	
}
