insert into Obj_types (object_type_id, name) values
    (1, 'user');

insert into Attributes (attr_id, attr_name) values
    (1001, 'first_name'),
    (1002, 'last_name'),
    (1003, 'login'),
    (1004, 'email'),
    (1005, 'password'),
    (1006, 'friend'),
    (1007, 'last_visit'),
    (1028, 'avatar'),
    (1029, 'about_me'),
    (1030, 'role');

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'first_name';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'last_name';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'login';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'email';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'password';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'friend';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'last_visit';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'avatar';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'about_me';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'role';