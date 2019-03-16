insert into Obj_types (object_type_id, name) values
(6,'chat');

insert into attributes (attr_id, attr_name,attr_type) values
(1029,'chatName', 0),
(1030,'subscribers', 0),
(1088,'chatType', 0),
(1089,'lastMessage', 0),
(1090,'lastSender', 0),
(1091,'lastUpdate', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'chatName'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'subscribers'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'chatType'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lastMessage'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lastSender'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'chat'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lastUpdate'));