insert into Obj_types (object_type_id, name) values
(6,'chat');

insert into attributes (attr_id, attr_name,attr_type) values
(1029,'chat_name', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'chat_name'));

