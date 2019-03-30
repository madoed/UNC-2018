insert into Obj_types (object_type_id, name) values
(20,'CustomPollOption');

insert into attributes (attr_id, attr_name,attr_type) values
(1101,'suggesterOfOption', 0),
(1102,'voicesForOption', 0),
(1103,'option', 0),
(1104,'percentageForOption', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPollOption'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'suggesterOfOption'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPollOption'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'voicesForOption'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPollOption'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'option'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPollOption'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'percentageForOption'));
