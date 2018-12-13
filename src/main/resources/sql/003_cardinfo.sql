insert into Obj_types (name) values
('Credit_card_info');

insert into objects (object_type_id, object_name)
values ((select object_type_id from Obj_types where name like 'Credit_card_info'),
          'card');

insert into objects (object_type_id, object_name)
values ((select object_type_id from obj_types where name like 'Credit_card_info'),
          'encripted_card');

insert into attributes (attr_name,attr_type)
values ( 'name_surname', 0);

insert into attributes (attr_name,attr_type)
values ('date', 0);

insert into attributes (attr_name,attr_type)
values ('4_last_numbers', 0);

insert into attributes (attr_name,attr_type)
values ('encripted_number', 0);

insert into attributes (attr_name,attr_type)
values ('code_word', 0);


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