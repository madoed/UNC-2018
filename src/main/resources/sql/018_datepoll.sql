insert into Obj_types (object_type_id, name) values
(17,'DatePoll');

insert into attributes (attr_id, attr_name,attr_type) values
(1072,'oneDateOfMeeting', 0),
(1073,'suggesterOfDate', 0),
(1074,'voicesForDate', 0),
(1075,'dateOption', 0),
(1083,'percentageForDate', 0);

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'DatePoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'dateOfMeeting'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'DatePoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'suggesterOfDate'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'DatePoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'voicesForDate'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'DatePoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'dateOption'));

insert into Obj_attributes (object_type_id, attr_id)
VALUES((select object_type_id from Obj_types where name like 'DatePoll'),
        (select attr_id from ATTRIBUTES where ATTRIBUTES.ATTR_NAME like 'percentageForDate'));