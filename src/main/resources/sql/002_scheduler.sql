insert into Obj_types (object_type_id, name) values
    (2, 'schedule'),
    (3, 'event');

insert into Attributes (attr_id, attr_name) values
    (1001, 'name'),
    (1002, 'description'),
    (1003, 'privacy_setting'),
    (1004, 'user'),
    (1005, 'start_date'),
    (1006, 'end_date'),
    (1007, 'priority'),
    (1008, 'event_type'),
    (1009, 'recursion'),
    (1010, 'reminder'),
    (1011, 'schedule'),
    (1012, 'meeting');

insert into Obj_attributes (object_type_id, attr_id)
select object_type_id, attr_id
from Obj_types, Attributes
where Obj_types.name like 'schedule' and attr_name like 'name';

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
where Obj_types.name like 'event' and attr_name like 'name';

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