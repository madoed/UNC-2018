insert into Obj_types (name) values
    ('schedule'),
    ('event');

insert into Attributes (attr_type, attr_name) values
    (0, 'name'),
    (0, 'description'),
    (0, 'privacy_setting'),
    (0, 'user'),
    (0, 'start_date'),
    (0, 'end_date'),
    (0, 'priority'),
    (0, 'event_type'),
    (0, 'recursion'),
    (0, 'reminder'),
    (0, 'schedule'),
    (0, 'meeting');

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