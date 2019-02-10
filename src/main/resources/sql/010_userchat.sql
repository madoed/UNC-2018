insert into Obj_types (object_type_id, name) values
(9,'userchat');

insert into attributes (attr_id, attr_name,attr_type) values
(1034,'chat', 0),
(1035,'subscriber', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'userchat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'chat'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'userchat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'subscriber'));