insert into Obj_types (object_type_id, name) values
(12,'billItem');

insert into attributes (attr_id, attr_name) values
(1053,'itemTitle'),
(1054,'itemAmount'),
(1055,'itemCurrentAmount'),
(1056,'itemStatus'),
(1057,'price'),
(1058,'itemBill');

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'billItem'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'itemTitle'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'billItem'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'itemAmount'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'billItem'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'itemCurrentAmount'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'billItem'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'itemStatus'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'billItem'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'price'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'billItem'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'itemBill'));
