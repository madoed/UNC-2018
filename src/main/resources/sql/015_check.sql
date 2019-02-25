insert into Obj_types (object_type_id, name) values
(14,'check');

insert into attributes (attr_id, attr_name) values
(1062,'checkOwner'),
(1063,'checkAmount'),
(1064,'checkCommonAmount'),
(1065,'checkStatus');

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'check'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'checkOwner'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'check'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'checkAmount'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'check'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'checkStatus'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'check'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'checkCommonAmount'));
