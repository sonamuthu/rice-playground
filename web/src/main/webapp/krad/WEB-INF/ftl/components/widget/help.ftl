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
<@macro uif-help widget>

    <#-- only render external help if a Url is specified -->
    <#if empty_widget.externalHelpUrl?has_content >
        <@krad.template component=widget.helpAction />
    </#if>

</@macro>