<#--
    ~ Copyright 2006-2012 The Kuali Foundation
    ~
    ~ Licensed under the Educational Community License, Version 2.0 (the "License");
    ~ you may not use this file except in compliance with the License.
    ~ You may obtain a copy of the License at
    ~
    ~ http://www.opensource.org/licenses/ecl2.php
    ~
    ~ Unless required by applicable law or agreed to in writing, software
    ~ distributed under the License is distributed on an "AS IS" BASIS,
    ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    ~ See the License for the specific language governing permissions and
    ~ limitations under the License.
    -->

<#--
   Group of HTML Checkbox Inputs

 -->
<fieldset aria-labelledby="${field.id}_label" class="${control.fieldsetClassesAsString}"
          data-type="CheckboxSet" id="${field.id}_fieldset">
    <legend style="display: none">${field.label}</legend>
    <form:checkboxes id="${control.id}" path="${field.bindingInfo.bindingPath}" disabled="${control.disabled}"
                     items="${control.options}" itemValue="key" itemLabel="value"
                     cssClass="${control.styleClassesAsString}" delimiter="${control.delimiter}"
                     tabindex="${control.tabIndex}"/>
</fieldset>


<fieldset class="ui-fieldset" id="${args.htmlid}-fieldset-ui">


<#assign attributes='id="${control.id}" label="${control.checkboxLabel}" size="${control.size!}"
cssClass="${control.styleClassesAsString!}" value="${control.value}"
tabindex="${control.tabIndex!}"  ${element.simpleDataAttributes!}'/>

<#if control.disabled>
    <#assign attributes='${attributes} disabled="true"'/>
</#if>

<#if control.style?has_content>
    <#assign attributes='${attributes} cssStyle="${control.style}"'/>
</#if>

<@spring.formInput path="KualiForm.${field.bindingInfo.bindingPath}" attributes="${attributes}"/>