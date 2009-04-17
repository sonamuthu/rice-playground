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
package org.kuali.rice.kns.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.util.ClassLoaderUtils;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.bo.ParameterDetailType;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.document.TransactionalDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KualiModuleService;
import org.kuali.rice.kns.service.ModuleService;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterEvaluator;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.ParameterConstants.COMPONENT;
import org.kuali.rice.kns.service.ParameterConstants.NAMESPACE;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KNSUtils;

/**
 * This is a description of what this class does - tom don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public abstract class ParameterServiceBase implements ParameterService {
	protected DataDictionaryService dataDictionaryService;
	protected KualiModuleService kualiModuleService;

	/**
	 * This constructs a ...
	 * 
	 */
	public ParameterServiceBase() {
		super();
	}

	/**
	 * @see org.kuali.kfs.sys.service.ParameterService#parameterExists(java.lang.Class componentClass, java.lang.String parameterName)
	 */
	public boolean parameterExists(Class componentClass, String parameterName) {
	    return retrieveParameter(getNamespace(componentClass), getDetailType(componentClass), parameterName) != null;
	}

	/**
	 * This method provides a convenient way to access the value of indicator parameters with Y/N values. Y is translated to true
	 * and N is translated to false.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @return boolean value of Yes/No indicator parameter
	 */
	public boolean getIndicatorParameter(Class componentClass, String parameterName) {
	    return "Y".equals(getParameter(componentClass, parameterName).getParameterValue());
	}

	/**
	 * @see org.kuali.kfs.sys.service.ParameterService#getParameterValue(java.lang.Class componentClass, java.lang.String parameterName)
	 */
	public String getParameterValue(Class componentClass, String parameterName) {
	    return getParameter(componentClass, parameterName).getParameterValue();
	}

	/**
	 * This will look for constrainingValue=<value to return> within the parameter text and return that if it is found. Otherwise,
	 * it will return null. Note, that if constrainingValue=value1,value2... (commas specific to the ParameterServiceImpl
	 * implementation) is found it will still return null, because calling this method states the assumption that there is only one
	 * value within the parameter text that corresponds to the constraining value.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @param constrainingValue
	 * @return derived value String or null
	 */
	public String getParameterValue(Class componentClass, String parameterName, String constrainingValue) {
	    List<String> parameterValues = getParameterValues(componentClass, parameterName, constrainingValue);
	    if (parameterValues.size() == 1) {
	        return parameterValues.get(0);
	    }
	    return null;
	}

	/**
	 * This method can be used to parse the value of a parameter by splitting on a semi-colon.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @return parsed List of String parameter values
	 */
	public List<String> getParameterValues(Class componentClass, String parameterName) {
	    return Collections.unmodifiableList( getParameterValues(getParameter(componentClass, parameterName)) );
	}

	/**
	 * This method looks for constrainingValue=<some text> within the parameter text and splits that text on a comma to generate
	 * the List to return.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @param constrainingValue
	 * @return derived values List<String> or an empty list if no values are found
	 */
	public List<String> getParameterValues(Class componentClass, String parameterName, String constrainingValue) {
	    return Collections.unmodifiableList( getParameterValues(getParameter(componentClass, parameterName), constrainingValue) );
	}

	/**
	 * This method will return an instance of the parameterEvaluator bean defined in Spring, initialized with the Parameter
	 * corresponding to the specified componentClass and parameterName and the values of the Parameter.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @return ParameterEvaluator instance initialized with the Parameter corresponding to the specified componentClass and
	 *         parameterName and the values of the Parameter
	 */
	public ParameterEvaluator getParameterEvaluator(Class componentClass, String parameterName) {
	    return getParameterEvaluator(getParameter(componentClass, parameterName));
	}

	/**
	 * This method will return an instance of the parameterEvaluator bean defined in Spring, initialized with the Parameter
	 * corresponding to the specified componentClass and parameterName, the values of the Parameter, the knowledge of whether the
	 * values are allowed or denied, and the constrainedValue.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @return ParameterEvaluator instance initialized with the Parameter corresponding to the specified componentClass and
	 *         parameterName, the values of the Parameter, the knowledge of whether the values are allowed or denied, and the
	 *         constrainedValue
	 */
	public ParameterEvaluator getParameterEvaluator(Class componentClass, String parameterName, String constrainedValue) {
	    return getParameterEvaluator(getParameter(componentClass, parameterName), constrainedValue);
	}

	/**
	 * This method will return an instance of the parameterEvaluator bean defined in Spring, initialized with the Parameter
	 * corresponding to the specified componentClass and parameterName, the values of the Parameter that correspond to the specified
	 * constrainingValue, the knowledge of whether the values are allowed or denied, and the constrainedValue.
	 * 
	 * @param componentClass
	 * @param parameterName
	 * @return ParameterEvaluator instance initialized with the Parameter corresponding to the specified componentClass and
	 *         parameterName, the values of the Parameter that correspond to the specified constrainingValue, the knowledge of
	 *         whether the values are allowed or denied, and the constrainedValue
	 */
	public ParameterEvaluator getParameterEvaluator(Class componentClass, String parameterName, String constrainingValue,
			String constrainedValue) {
			    return getParameterEvaluator(getParameter(componentClass, parameterName), constrainingValue, constrainedValue);
			}

	/**
	 * This method will return an instance of the parameterEvaluator bean defined in Spring, initialized with the Parameter
	 * corresponding to the specified componentClass and allowParameterName or to the specified componentClass and denyParameterName
	 * (depending on which restricts based on the constraining value) or an instance of AlwaysSucceedParameterEvaluatorImpl if
	 * neither restricts, the values of the Parameter that correspond to the specified constrainingValue, the knowledge of whether
	 * the values are allowed or denied, and the constrainedValue.
	 * 
	 * @param componentClass
	 * @param allowParameterName
	 * @param denyParameterName
	 * @param constrainingValue
	 * @param constrainedValue
	 * @return AlwaysSucceedParameterEvaluatorImpl or ParameterEvaluator instance initialized with the Parameter that corresponds to
	 *         the constrainingValue restriction, the values of the Parameter that correspond to the specified constrainingValue,
	 *         the knowledge of whether the values are allowed or denied, and the constrainedValue
	 */
	public ParameterEvaluator getParameterEvaluator(Class componentClass, String allowParameterName, String denyParameterName,
			String constrainingValue, String constrainedValue) {
			    Parameter allowParameter = getParameter(componentClass, allowParameterName);
			    Parameter denyParameter = getParameter(componentClass, denyParameterName);
			    if (!getParameterValues(allowParameter, constrainingValue).isEmpty() && !getParameterValues(denyParameter, constrainingValue).isEmpty()) {
			        throw new IllegalArgumentException("The getParameterEvaluator(Class componentClass, String allowParameterName, String denyParameterName, String constrainingValue, String constrainedValue) method of ParameterServiceImpl does not facilitate evaluation of combination allow and deny parameters that both have values for the constraining value: " + allowParameterName + " / " + denyParameterName + " / " + constrainingValue);
			    }
			    if (getParameterValues(allowParameter, constrainingValue).isEmpty() && getParameterValues(denyParameter, constrainingValue).isEmpty()) {
			        return AlwaysSucceedParameterEvaluatorImpl.getInstance();
			    }
			    return getParameterEvaluator(getParameterValues(denyParameter, constrainingValue).isEmpty() ? allowParameter : denyParameter, constrainingValue, constrainedValue);
			}


	public String getNamespace(Class documentOrStepClass) {
	    if (documentOrStepClass == null) {
	        throw new IllegalArgumentException("The getNamespace method of ParameterServiceImpl requires non-null documentOrStepClass");
	    }
	    if (documentOrStepClass.isAnnotationPresent(NAMESPACE.class)) {
	        return ((NAMESPACE) documentOrStepClass.getAnnotation(NAMESPACE.class)).namespace();
	    }
	    ModuleService moduleService = kualiModuleService.getResponsibleModuleService(documentOrStepClass);
	    if (moduleService != null) {
	        return moduleService.getModuleConfiguration().getNamespaceCode();
	    }
	    if (documentOrStepClass.getName().startsWith("org.kuali.rice.kns")) {
	        return ParameterConstants.NERVOUS_SYSTEM_NAMESPACE;
	    }
	    if (documentOrStepClass.getName().startsWith("org.kuali.rice.kew")) {
	        return "KR-WKFLW";
	    }
	    if (documentOrStepClass.getName().startsWith("org.kuali.rice.kim")) {
	        return "KR-IDM";
	    }
	    throw new IllegalArgumentException("Unable to determine the namespace for documentOrStepClass " + documentOrStepClass.getName() );
	}

	public String getDetailType(Class documentOrStepClass) {
	    if (documentOrStepClass == null) {
	        throw new IllegalArgumentException("The getDetailType method of ParameterServiceImpl requires non-null documentOrStepClass");
	    }
	    else if (documentOrStepClass.isAnnotationPresent(COMPONENT.class)) {
	        return ((COMPONENT) documentOrStepClass.getAnnotation(COMPONENT.class)).component();
	    }
	    else if (TransactionalDocument.class.isAssignableFrom(documentOrStepClass)) {
	        return documentOrStepClass.getSimpleName().replace("Document", "");
	    }
	    else if (BusinessObject.class.isAssignableFrom(documentOrStepClass) ) {
	        return documentOrStepClass.getSimpleName();
	    }
	    else {
	    	// TODO: Warning!  Kludge!  Hack!  Will be replaced!  KULRICE-2921
	    	Class c = getStepClass();
	    	if ( c != null && c.isAssignableFrom(documentOrStepClass) ) {
	    		return documentOrStepClass.getSimpleName();	    		
	    	}
	    }
	    throw new IllegalArgumentException("The getDetailType method of ParameterServiceImpl requires a TransactionalDocument or BusinessObject class.  Was:" + documentOrStepClass.getName());
	}

	private Class getStepClass() {
		try {
			ClassLoader cl = ClassLoaderUtils.getDefaultClassLoader();
			return Class.forName("org.kuali.kfs.sys.batch.Step", true, cl);
		}
		catch ( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected String getDetailTypeName(Class documentOrStepClass) {
	    if (documentOrStepClass == null) {
	        throw new IllegalArgumentException("The getDetailTypeName method of ParameterServiceImpl requires non-null documentOrStepClass");
	    }
	    if (documentOrStepClass.isAnnotationPresent(COMPONENT.class)) {
	        BusinessObjectEntry boe = dataDictionaryService.getDataDictionary().getBusinessObjectEntry(documentOrStepClass.getName());
	        if (boe != null) {
	            return boe.getObjectLabel();
	        }
	        else {
	            return ((COMPONENT) documentOrStepClass.getAnnotation(COMPONENT.class)).component();
	        }
	    }
	    if (TransactionalDocument.class.isAssignableFrom(documentOrStepClass)) {
	        return dataDictionaryService.getDocumentLabelByClass(documentOrStepClass);
	    }
	    else if (BusinessObject.class.isAssignableFrom(documentOrStepClass) ) {
	        BusinessObjectEntry boe = dataDictionaryService.getDataDictionary().getBusinessObjectEntry(documentOrStepClass.getName());
	        if (boe != null) {
	            return boe.getObjectLabel();
	        }
	        else {
	            return KNSUtils.getBusinessTitleForClass(documentOrStepClass);
	        }
	    }
	    throw new IllegalArgumentException("The getDetailTypeName method of ParameterServiceImpl requires TransactionalDocument, BusinessObject, or Step class. Was: " + documentOrStepClass.getName() );
	}

	protected ParameterEvaluatorImpl getParameterEvaluator(Parameter parameter) {
	    ParameterEvaluatorImpl parameterEvaluator = new ParameterEvaluatorImpl();
	    parameterEvaluator.setParameter(parameter);
	    parameterEvaluator.setConstraintIsAllow(constraintIsAllow(parameter));
	    parameterEvaluator.setValues(getParameterValues(parameter));
	    return parameterEvaluator;
	}

	protected ParameterEvaluatorImpl getParameterEvaluator(Parameter parameter, String constrainedValue) {
	    ParameterEvaluatorImpl parameterEvaluator = getParameterEvaluator(parameter);
	    parameterEvaluator.setConstrainedValue(constrainedValue);
	    return parameterEvaluator;
	}

	protected ParameterEvaluatorImpl getParameterEvaluator(Parameter parameter, String constrainingValue,
			String constrainedValue) {
			    ParameterEvaluatorImpl parameterEvaluator = getParameterEvaluator(parameter, constrainedValue);
			    parameterEvaluator.setValues(getParameterValues(parameter, constrainingValue));
			    return parameterEvaluator;
			}

	protected ParameterDetailType getParameterDetailType(Class documentOrStepClass) {
	    String detailTypeString = getDetailType(documentOrStepClass);
	    String detailTypeName = getDetailTypeName(documentOrStepClass);
	    ParameterDetailType detailType = new ParameterDetailType(getNamespace(documentOrStepClass), detailTypeString, (detailTypeName == null) ? detailTypeString : detailTypeName);
	    detailType.refreshNonUpdateableReferences();
	    return detailType;
	}

	protected List<String> getParameterValues(Parameter parameter, String constrainingValue) {
	    List<String> constraintValuePairs = getParameterValues(parameter);
	    for (String pair : constraintValuePairs) {
	        if (StringUtils.equals(constrainingValue, StringUtils.substringBefore(pair, "="))) {
	            return Arrays.asList(StringUtils.substringAfter(pair, "=").split(","));
	        }
	    }
	    return Collections.EMPTY_LIST;
	}

	private List<String> getParameterValues(Parameter parameter) {
	    if (parameter == null || StringUtils.isBlank(parameter.getParameterValue())) {
	        return Collections.EMPTY_LIST;
	    }
	    return Arrays.asList(parameter.getParameterValue().split(";"));
	}

    private ThreadLocal<Map<String,Parameter>> parameterCache = new ThreadLocal<Map<String,Parameter>>();

    /**
     * @see org.kuali.kfs.sys.service.ParameterService#clearCache()
     */
    public void clearCache() {
        parameterCache.set(null);
    }
 
    protected Parameter getParameter(Class componentClass, String parameterName) {
        String key = componentClass.toString() + ":" + parameterName;
        Parameter parameter = null;
        if (parameterCache.get() == null) {
            parameterCache.set(new HashMap<String,Parameter>());
        }
        else {
            parameter = parameterCache.get().get(key);
            if (parameter != null) {
                return parameter;
            }
        }
        parameter = getParameter(getNamespace(componentClass), getDetailType(componentClass), parameterName);
        if (parameter == null) {
            throw new IllegalArgumentException("The getParameter method of ParameterServiceImpl requires a componentClass and parameterName that correspond to an existing parameter.  Was passed: " + componentClass.getName() + "/" + parameterName);
        }
        parameterCache.get().put(key, parameter);
        return parameter;
    }
    
	protected Parameter getParameter(String namespaceCode, String detailTypeCode, String parameterName) {
	    if (StringUtils.isBlank(namespaceCode) || StringUtils.isBlank(detailTypeCode) || StringUtils.isBlank(parameterName)) {
	        throw new IllegalArgumentException("The getParameter method of KualiConfigurationServiceImpl requires a non-blank namespaceCode, parameterDetailTypeCode, and parameterName: " + namespaceCode + " / " + detailTypeCode + " / " + parameterName);
	    }
	    Parameter param = retrieveParameter(namespaceCode, detailTypeCode, parameterName);
	    if (param == null) {
	        throw new IllegalArgumentException("The getParameter method of KualiConfigurationServiceImpl was unable to find parameter: " + namespaceCode + " / " + detailTypeCode + " / " + parameterName);
	    }
	    return param;
	}

	private boolean constraintIsAllow(Parameter parameter) {
	    return KNSConstants.APC_ALLOWED_OPERATOR.equals(parameter.getParameterConstraintCode());
	}

	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
	    this.dataDictionaryService = dataDictionaryService;
	}

	public void setKualiModuleService(KualiModuleService kualiModuleService) {
	    this.kualiModuleService = kualiModuleService;
	}
}