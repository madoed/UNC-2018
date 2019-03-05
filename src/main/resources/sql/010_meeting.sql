insert into Obj_types (object_type_id, name) values
(9,'meeting');

insert into attributes (attr_id, attr_name,attr_type) values
(1039,'meetingName', 0),
(1040,'meetingDescription', 0),
(1041,'dateOfMeeting', 0),
(1042,'status', 0),
(1043,'boss', 0),
(1044,'meetingChat', 0),
(1045,'meetingLocation', 0),
(1076,'amountOfParticipants', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingName'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingDescription'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'dateOfMeeting'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'status'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'boss'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingChat'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingLocation'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'amountOfParticipants'));