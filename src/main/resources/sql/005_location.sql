insert into Obj_types (name) values
('location');

insert into attributes (attr_name,attr_type) values
('lng', 0),
('lat', 0),
('placeName', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'location'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lng'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'location'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'lat'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'location'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'placeName'));