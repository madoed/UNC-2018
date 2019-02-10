insert into Obj_types (object_type_id, name) values
(5,'location');

insert into attributes (attr_id, attr_name,attr_type) values
(1025,'lng', 0),
(1026,'lat', 0),
(1027,'place_name', 0),
(1028,'meetings', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'location'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lng'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'location'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lat'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'location'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'place_name'));