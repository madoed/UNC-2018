insert into Obj_types (object_type_id, name) values
(4,'card');

insert into attributes (attr_id, attr_name,attr_type)
values ( 1019,'name_surname', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1020,'card_full_number', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1021,'last_four_numbers', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1022,'data_of_expire', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1023,'code_word', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1024,'owner', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'name_surname'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'data_of_expire'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'last_four_numbers'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'card_full_number'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'code_word'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'card'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'owner'));