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
(1076,'amountOfParticipants', 0),
(1079,'timeOfMeeting', 0),
(1082,'pollForDateOpen', 0),
(1081,'pollForPlaceOpen', 0),
(1094,'meetingType', 0),
(1095,'recursiveInfo', 0),
(1096,'recursiveUpdate', 0);

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

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'timeOfMeeting'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'pollForDateOpen'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'pollForPlaceOpen'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
       (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'avatar_url'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
       (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'meetingType'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
       (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'recursiveInfo'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'meeting'),
       (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'recursiveUpdate'));
