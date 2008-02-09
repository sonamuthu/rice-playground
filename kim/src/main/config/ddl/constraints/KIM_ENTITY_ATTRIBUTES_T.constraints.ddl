ALTER TABLE KIM_ENTITY_ATTRIBUTES_T 
ADD CONSTRAINT KIM_ENTITY_ATTRIBS_UK1 UNIQUE
(
ENTITY_ID,
SPONSOR_NAMESPACE_ID,
ATTRIBUTE_NAME
) ENABLE
/
ALTER TABLE KIM_ENTITY_ATTRIBUTES_T
ADD CONSTRAINT KIM_ENTITY_ATTRIBS_FK1 FOREIGN KEY
(
ENTITY_ID
)
REFERENCES KIM_ENTITYS_T
(
ID
) ENABLE
/
ALTER TABLE KIM_ENTITY_ATTRIBUTES_T
ADD CONSTRAINT KIM_ENTITY_ATTRIBS_FK2 FOREIGN KEY
(
SPONSOR_NAMESPACE_ID
)
REFERENCES KIM_NAMESPACES_T
(
ID
) ENABLE
/