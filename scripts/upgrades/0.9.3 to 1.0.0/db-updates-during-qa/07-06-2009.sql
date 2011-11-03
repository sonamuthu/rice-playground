--
-- Copyright 2005-2011 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- KULRICE-3287

DELETE FROM KRNS_PARM_T where NMSPC_CD='KR-WKFLW' AND PARM_DTL_TYP_CD='All' AND PARM_NM='APPLICATION_CONTEXT'
/

-- KULRICE-3278

DECLARE

    ref_perm_id VARCHAR2(40);

BEGIN

    SELECT perm_id INTO ref_perm_id FROM krim_perm_attr_data_t WHERE attr_val = 'org.kuali.rice.kew.web.backdoor.AdministrationAction';
    DELETE FROM krim_perm_attr_data_t WHERE perm_id = ref_perm_id;
    DELETE FROM krim_role_perm_t WHERE perm_id = ref_perm_id;
    DELETE FROM krim_perm_t WHERE perm_id = ref_perm_id;

END;
/ 
