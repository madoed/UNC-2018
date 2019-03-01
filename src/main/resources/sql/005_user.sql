insert into Obj_types (object_type_id, name) values
    (1, 'user');

insert into Attributes (attr_id, attr_name) values
    (1001, 'first_name'),
    (1002, 'last_name'),
    (1003, 'username'),
    (1004, 'email'),
    (1005, 'keycloak_id'),
    (1006, 'friend'),
    (1036, 'avatar_url'),
    (1037, 'about_me');

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
where Obj_types.name like 'user' and attr_name like 'username';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'email';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'friend';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'keycloak_id';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'avatar_url';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'about_me';