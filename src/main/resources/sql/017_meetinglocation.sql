insert into Obj_types (object_type_id, name) values
(16,'MeetingLocation');

insert into attributes (attr_id, attr_name,attr_type) values
(1068,'oneLocation', 0),
(1069,'locationOfMeeting', 0),
(1070,'suggesterOfLocation', 0),
(1071,'voicesForLocation', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'MeetingLocation'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingLocation'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'MeetingLocation'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'locationOfMeeting'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'MeetingLocation'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'suggesterOfLocation'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'MeetingLocation'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'voicesForLocation'));
