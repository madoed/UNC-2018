insert into Obj_types (object_type_id, name) values
(11,'bill');

insert into attributes (attr_id, attr_name) values
(1049,'billOwner'),
(1050,'billOfMeeting'),
(1051,'dateOfBill'),
(1052,'billStatus'),
(1064,'billCommonAmount'),
(1077,'billTotalSum');

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'bill'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'billOwner'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'bill'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'billOfMeeting'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'bill'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'dateOfBill'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'bill'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'billStatus'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'bill'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'billCommonAmount'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'bill'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'billTotalSum'));
