insert into Obj_types (object_type_id, name) values
    (6, 'role');

insert into Objects values (generate_id(), 6, 'ROLE_ADMIN');
insert into Objects values (generate_id(), 6, 'ROLE_USER');