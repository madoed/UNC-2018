insert into Obj_types (object_type_id, name) values
(4,'Credit_card_info');

insert into attributes (attr_id, attr_name,attr_type)
values ( 1020,'name_surname', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1021,'date', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1022,'4_last_numbers', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1023,'encripted_number', 0);

insert into attributes (attr_id, attr_name,attr_type)
values (1024,'code_word', 0);


insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'Credit_card_info'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'name_surname'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'Credit_card_info'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'date'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'Credit_card_info'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like '4_last_numbers'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'Credit_card_info'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'encripted_number'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'Credit_card_info'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'code_word'));