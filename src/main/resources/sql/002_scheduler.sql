insert into Obj_types (object_type_id, name) values
    (2, 'schedule'),
    (3, 'event');

insert into Attributes (attr_id, attr_name) values
    (1008, 'description'),
    (1009, 'privacy_setting'),
    (1010, 'user'),
    (1011, 'start_date'),
    (1012, 'end_date'),
    (1013, 'priority'),
    (1014, 'event_type'),
    (1015, 'recursion'),
    (1016, 'reminder'),
    (1017, 'schedule'),
    (1018, 'meeting');

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'schedule' and attr_name like 'description';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'schedule' and attr_name like 'privacy_setting';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'schedule' and attr_name like 'user';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'description';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'start_date';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'end_date';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'priority';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'event_type';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'recursion';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'reminder';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'schedule';

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'event' and attr_name like 'meeting';