insert into Obj_types (object_type_id, name) values
(7,'message');

insert into attributes (attr_id, attr_name,attr_type) values
(1031,'content', 0),
(1032,'timestamp', 0),
(1033,'from_chat', 0),
(1034,'sender', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'message'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'content'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'message'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'timestamp'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'message'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'from_chat'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'message'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'sender'));