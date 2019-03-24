insert into Attributes (attr_id, attr_name) values
(1092, 'is_online'),
(1093, 'last_visit');

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'is_online';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'user' and attr_name like 'last_visit';