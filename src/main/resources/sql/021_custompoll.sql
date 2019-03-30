insert into Obj_types (object_type_id, name) values
(19,'CustomPoll');

insert into attributes (attr_id, attr_name,attr_type) values
(1097,'pollOfMeeting', 0),
(1098,'customPollName', 0),
(1099,'isOpened', 0),
(1100,'optionsInPoll', 0),
(1105,'creatorOfPoll', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'pollOfMeeting'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'customPollName'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'isOpened'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'optionsInPoll'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'CustomPoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'creatorOfPoll'));

