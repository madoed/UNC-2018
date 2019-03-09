insert into Obj_types (object_type_id, name) values
(10,'participant');

insert into attributes (attr_id, attr_name,attr_type) values
(1046,'meetingParticipant', 0),
(1047,'suggestedLocations', 0),
(1048,'participantOfMeeting', 0),
(1078,'statusOfConfirmation', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'participant'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingParticipant'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'participant'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'suggestedLocations'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'participant'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'participantOfMeeting'));


insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'participant'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'statusOfConfirmation'));
