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
package org.kuali.rice.kim.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.rice.kim.bo.entity.impl.KimPrincipalImpl;
import org.kuali.rice.kim.bo.group.impl.GroupAttributeDataImpl;
import org.kuali.rice.kim.bo.group.impl.GroupMemberImpl;
import org.kuali.rice.kim.bo.group.impl.KimGroupImpl;
import org.kuali.rice.kim.dao.KimGroupDao;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * This is a description of what this class does - shyu don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class KimGroupDaoOjb extends PlatformAwareDaoBaseOjb implements KimGroupDao {
	
    public List<KimGroupImpl> getGroups(Map<String,String> fieldValues, String kimTypeId) {
        Criteria crit = new Criteria();
        BusinessObjectEntry boEntry = KNSServiceLocator.getDataDictionaryService().getDataDictionary().getBusinessObjectEntry("org.kuali.rice.kim.bo.group.impl.KimGroupImpl");
        List lookupNames = boEntry.getLookupDefinition().getLookupFieldNames();
        for (Entry<String, String> entry : fieldValues.entrySet()) {
        	if (StringUtils.isNotBlank(entry.getValue())) {
        		if (entry.getKey().contains(".")) {
        	        Criteria subCrit = new Criteria();
        			subCrit.addEqualTo("attributeValue",entry.getValue());
        			subCrit.addEqualTo("kimAttributeId",entry.getKey().substring(entry.getKey().indexOf(".")+1, entry.getKey().length()));
        			subCrit.addEqualTo("kimTypeId", kimTypeId);
        			ReportQueryByCriteria subQuery = QueryFactory.newReportQuery(GroupAttributeDataImpl.class, subCrit);
        			crit.addExists(subQuery);
        		} else {
        			if (lookupNames.contains(entry.getKey())) {
        				crit.addEqualTo(entry.getKey(), entry.getValue());
        			} else {
        				if (entry.getKey().equals("principalName")) {
                	        Criteria subCrit = new Criteria();
                			String principalName = entry.getValue().replace('*', '%');
                			subCrit.addLike("principalName","%" + principalName + "%");
                	        subCrit.addEqualToField("principalId", Criteria.PARENT_QUERY_PREFIX + "memberId");
                			ReportQueryByCriteria subQuery = QueryFactory.newReportQuery(KimPrincipalImpl.class, subCrit);
                	        Criteria memberSubCrit = new Criteria();
                	        memberSubCrit.addEqualToField("groupId", Criteria.PARENT_QUERY_PREFIX + "groupId");
                	        memberSubCrit.addExists(subQuery);
                			ReportQueryByCriteria memberSubQuery = QueryFactory.newReportQuery(GroupMemberImpl.class, memberSubCrit);
                			crit.addExists(memberSubQuery);        					
        				}
        			}
        		}
        	}
        }
        Query q = QueryFactory.newQuery(KimGroupImpl.class, crit);
        
        return (List)getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }

}
