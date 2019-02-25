insert into Obj_types (object_type_id, name) values
(15,'encryptedCard');

insert into attributes (attr_id, attr_name) values
(1020,'cardFullNumber'),
(1023,'codeWord'),
(1067,'encryptedCard');

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'encryptedCard'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'cardFullNumber'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'encryptedCard'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'codeWord'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'encryptedCard'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'encryptedCard'));
