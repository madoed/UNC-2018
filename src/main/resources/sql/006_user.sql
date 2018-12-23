insert into Obj_types (object_type_id, name) values
    (1, 'user');

insert into Attributes (attr_id, attr_name) values
    (1013, 'login'),
    (1014, 'email'),
    (1015, 'password'),
    (1016, 'friend'),
    (1017, 'last_visit');

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'name';

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