<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="roleIdx" required="true" %>
<%@ attribute name="mbrIdx" required="true" %>
<c:set var="rolePrncpl" value="${KualiForm.document.roles[roleIdx].rolePrncpls[mbrIdx]}"/>
<c:set var="docRoleRspActionAttributes" value="${DataDictionary.KimDocumentRoleResponsibilityAction.attributes}" />
       <kul:subtab lookedUpCollectionName="roleRspActions" noShowHideButton="true" width="${tableWidth}" subTabTitle="Responsibility Actions">      
			<c:forEach var="roleRspAction" items="${rolePrncpl.roleRspActions}" varStatus="actionStatus">
        				<table cellpadding=0 cellspacing=0 summary="">
                        <tr>
                 <th width="5%" rowspan=20 style=border-style:none>&nbsp;</th>
				<kul:htmlAttributeHeaderCell literalLabel="Name"/>
				<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.actionTypeCode}" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.priorityNumber}"  />
          		<kul:htmlAttributeHeaderCell attributeEntry="${docRoleRspActionAttributes.actionPolicyCode}"  />
                		</tr>
                	<tr>	
						<td>
						<div align="center">
						${roleRspAction.roleResponsibility.kimResponsibility.template.name}
						${roleRspAction.roleResponsibility.kimResponsibility.name}
			            </div>
		        		</td>
						<td>
						<div align="center">
			            <kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].actionTypeCode"  attributeEntry="${docRoleRspActionAttributes.actionTypeCode}" />
			            </div>
		        		</td>
		        		<td>
		        		<div align="center">
			            <kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].priorityNumber"  attributeEntry="${docRoleRspActionAttributes.priorityNumber}" />
		        		</div>
		        		</td>
		        		<td>
		        		<div align="center">
			            <kul:htmlControlAttribute property="document.roles[${roleIdx}].rolePrncpls[${mbrIdx}].roleRspActions[${actionStatus.index}].actionPolicyCode"  attributeEntry="${docRoleRspActionAttributes.actionPolicyCode}" />
		        		</div>
		        		</td>
		        	</tr>
		        		 <tr>
   <!-- need to decide colspan -->
                             <td colspan=7 style="padding:0px; border-style:none; height:22px; background-color:#F6F6F6">&nbsp;</td>
                           </tr>
		        	
		       </table>       
		</c:forEach>
	</kul:subtab>
	