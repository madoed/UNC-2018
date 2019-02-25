insert into Obj_types (object_type_id, name) values
(13,'itemAmount');

insert into attributes (attr_id, attr_name) values
(1059,'billItemAmount'),
(1060,'itemAmountCheck'),
(1061,'amountInCheck');

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'itemAmount'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'billItemAmount'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'itemAmount'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'itemAmountCheck'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'itemAmount'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'amountInCheck'));
