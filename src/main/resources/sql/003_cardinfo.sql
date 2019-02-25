insert into Obj_types (object_type_id, name) values
(4,'card');

insert into attributes (attr_id, attr_name,attr_type)
values ( 1019,'nameSurname', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1021,'lastFourNumbers', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1022,'dataOfExpire', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1024,'owner', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1066,'mainCard', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'nameSurname'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'dataOfExpire'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lastFourNumbers'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'owner'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'mainCard'));