ALTER TABLE KIM_ROLES_GROUPS_T
ADD CONSTRAINT KIM_ROLES_GROUPS_FK1 FOREIGN KEY
(
GROUP_ID
)
REFERENCES KIM_GROUPS_T
(
ID
) ENABLE
/
ALTER TABLE KIM_ROLES_GROUPS_T
ADD CONSTRAINT KIM_ROLES_GROUPS_FK2 FOREIGN KEY
(
ROLE_ID
)
REFERENCES KIM_ROLES_T
(
ID
) ENABLE
/