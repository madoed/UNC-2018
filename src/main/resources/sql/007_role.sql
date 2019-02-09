insert into Obj_types (object_type_id, name) values
    (8, 'role');

insert into Objects values (generate_id(), 8, 'ROLE_ADMIN');
insert into Objects values (generate_id(), 8, 'ROLE_USER');