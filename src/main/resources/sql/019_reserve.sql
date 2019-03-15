insert into Obj_types (object_type_id, name) values
(18,'reserve');

insert into attributes (attr_id, attr_name,attr_type) values
(1085,'reserveChat', 0),
(1086,'reserveUser', 0),
(1087,'reserveMessage', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'reserve'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'reserveChat'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'reserve'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'reserveUser'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'reserve'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'reserveMessage'));