insert into objects values (-1, 1, 'Alice Smith'),
                           (-2, 1, 'Bob Wilde'),
                           (-3, 1, 'Davy Jones'),
                           (-1000, 1, 'Administrator');
insert into refs values (-1, 1006, -2),
                        (-1, 1006, -3),
                        (-2, 1006, -1),
                        (-3, 1006, -1);
insert into params values (-1, 1001, 'Alice'),
                          (-1, 1002, 'Smith'),
                          (-1, 1003, 'alice'),
                          (-1, 1004, 'alice@example.com'),
                          (-1, 1036, 'http://localhost:8000/images/alice-avatar1.png'),
                          (-1, 1037, 'Hi, my name is Alice!');
insert into params values (-1000, 1001, 'Super'),
                          (-1000, 1002, 'User'),
                          (-1000, 1003, 'admin'),
                          (-1000, 1004, 'admin@example.com'),
                          (-1000, 1036, 'http://localhost:8000/images/admin.png');
insert into params values (-2, 1001, 'Bob'),
                          (-2, 1002, 'Wilde'),
                          (-2, 1003, 'bob'),
                          (-2, 1004, 'bob@example.com');
insert into params values (-3, 1001, 'Davy'),
                          (-3, 1002, 'Jones'),
                          (-3, 1003, 'davy'),
                          (-3, 1004, 'davy@example.com');

insert into objects values (-4, 2, 'schedule of Alice'),
                           (-5, 3, 'birthday party'),
                           (-6, 3, 'painting class');
insert into refs values (-4, 1010, -1),
                        (-5, 1017, -4),
                        (-6, 1017, -4);
insert into params values (-4, 1008, 'My schedule. Yay!!!'),
                          (-4, 1009, '0'),

                          (-5, 1008, 'b-day of Ma'),
                          (-5, 1011, '02-02-2019 00:00:00'),
                          (-5, 1012, '02-02-2019 23:59:59'),
                          (-5, 1013, '100'),
                          (-5, 1014, 'party'),
                          (-5, 1016, 'true'),

                          (-6, 1008, 'my painting class'),
                          (-6, 1011, '15-01-2019 09:00:00'),
                          (-6, 1012, '15-01-2019 11:00:00'),
                          (-6, 1013, '80'),
                          (-6, 1014, 'class'),
                          (-6, 1015, '604800000');

--chat
-- insert into objects values (-7, 6, 'First chat' ),
--                            (-8, 6, 'Second chat' );
-- insert into refs values (-7, 1030, -1),
--                         (-7, 1030, -2),
--                         (-8, 1030, -1);
-- insert into params values (-7, 1029, 'First chat!'),
--                           (-8, 1029, 'Second chat!');

--location
-- insert into objects values (-14, 5, 'place1' ),
--                            (-15, 5, 'place2' );
-- insert into params values (-14, 1027, 'Place1 !'),
--                           (-14, 1026, '51.6754966'),
--                           (-14, 1025, '39.2088823'),
--                           (-15, 1025, '39.254592'),
--                           (-15, 1026, '51.685036'),
--                           (-15, 1027, 'Place 2 !');

--meeting
-- insert into objects values (-9, 9, 'First meeting' ),
--                            (-10, 9, 'Second meeting' );
-- insert into refs values (-9, 1043, -1),
--                         (-10, 1043, -1),
--                         (-9, 1044, -7),
--                         (-9, 1043, -2),
--                         (-10, 1043, -1),
--                         (-9, 1044, -7),
--                         (-10, 1044, -8),
--                         (-9, 1045, -14);
-- insert into params values (-9, 1039, 'First meeting!'),
--                           (-10, 1039, 'Second meeting!'),
--                           (-9, 1040, 'Lets eat'),
--                           (-9, 1041, '20-04-2019 00:00:00'),
--                           (-9, 1042, 'new'),
--                           (-10, 1040, 'We need to talk'),
--                           (-10, 1041, '27-05-2019 00:00:00'),
--                           (-10, 1042, 'new'),
--                           (-9, 1076, 2),
--                           (-10, 1076, 1);

--participant(10)
-- insert into objects values (-11, 10, 'participant 1' ),
--                            (-12, 10, 'participant  2' ),
--                            (-13, 10, 'participant  3' );
-- insert into refs values (-11, 1048, -9),
--                         (-12, 1048, -10),
--                         (-13, 1048, -9),
--                         (-11, 1046, -1),
--                         (-12, 1046, -1),
--                         (-13, 1046, -2),
--                         (-11, 1047, -14),
--                         (-12, 1047, -15);

--bill(11)
-- insert into objects values (-16, 11, 'bill for first meeting' ),
--                            (-17, 11, 'bill for second meeting' );
-- insert into refs values (-16, 1049, -1),
--                         (-17, 1049, -1),
--                         (-16, 1050, -9),
--                         (-17, 1050, -10);
-- insert into params values (-16, 1051, '24-02-2019 00:00:00'),
--                           (-16, 1052, 'empty'),
--                           (-17, 1051, '22-01-2019 00:00:00'),
--                           (-17, 1052, 'empty'),
--                           (-16, 1077, 0),
--                           (-16, 1064, 0),
--                           (-17, 1077, 0),
--                           (-17, 1064, 0);

--check(14)
-- insert into objects values (-18, 14, 'check for participant 1 of meeting 1' ),
--                            (-19, 14, 'check for participant 2 of meeting 1' );
-- insert into refs values (-18, 1062, -11),
--                         (-19, 1062, -12);
-- insert into params values (-18, 1065, 'not payed'),
--                           (-19, 1065, 'not payed'),
--                           (-18, 1063, '300'),
--                           (-19, 1063, '300');
-- --bill item(12)
-- insert into objects values (-20, 12, 'candles' ),
--                            (-21, 12, 'cake' ),
--                            (-22, 12, 'water' ),
--                            (-23, 12, 'balloons'),
--                            (-24, 12, 'lighter' );
-- insert into refs values (-20, 1058, -16),
--                         (-21, 1058, -16),
--                         (-22, 1058, -16),
--                         (-23, 1058, -16),
--                         (-24, 1058, -16);
-- insert into params values (-20, 1053, 'candles'),
--                           (-20, 1054, '2'),
--                           (-20, 1055, '1'),
--                           (-20, 1057, '100'),
--                           (-21, 1053, 'cake'),
--                           (-21, 1054, '3'),
--                           (-21, 1055, '1'),
--                           (-21, 1057, '200'),
--                           (-22, 1053, 'water'),
--                           (-22, 1054, '4'),
--                           (-22, 1055, '2'),
--                           (-22, 1057, '50'),
--                           (-23, 1053, 'balloons'),
--                           (-23, 1054, '10'),
--                           (-23, 1055, '10'),
--                           (-23, 1057, '100'),
--                           (-24, 1053, 'lighter'),
--                           (-24, 1054, '1'),
--                           (-24, 1055, '1'),
--                           (-24, 1057, '100');
--
-- --item amount(13)
-- insert into objects values (-25, 13, 'amount' ),
--                            (-26, 13, 'amount' ),
--                            (-27, 13, 'amount' ),
--                            (-28, 13, 'amount' );
-- insert into refs values (-25, 1059, -20),
--                         (-26, 1059, -21),
--                         (-27, 1059, -21),
--                         (-28, 1059, -22),
--                         (-25, 1060, -18),
--                         (-26, 1060, -18),
--                         (-27, 1060, -19),
--                         (-28, 1060, -19);
-- insert into params values (-25, 1061, '1'),
--                           (-26, 1061, '1'),
--                           (-27, 1061, '1'),
--                           (-28, 1061, '2');

-- --card info(4)
-- insert into objects values (-29, 4, 'card' ),
--                            (-30, 4, 'card' ),
--                            (-31, 4, 'card' ),
--                            (-32, 4, 'card' ),
--                            (-33, 15, 'card' ),
--                            (-34, 15, 'card' ),
--                            (-35, 15, 'card' ),
--                            (-36, 15, 'card' );
-- insert into refs values (-29, 1024, -1),
--                         (-30, 1024, -1),
--                         (-31, 1024, -2),
--                         (-32, 1024, -2),
--                         (-33, 1067, -29),
--                         (-34, 1067, -30),
--                         (-35, 1067, -31),
--                         (-36, 1067, -32);
-- insert into params values (-29, 1019, 'Alice Smith'),
--                           (-33, 1020, 'cipher'),
--                           (-29, 1021, '4567'),
--                           (-29, 1022, '12/20'),
--                           (-33, 1023, 'xfghfg'),--salt
--                           (-29, 1066, '1'),
--                           (-30, 1019, 'Alice Smith'),
--                           (-34, 1020, 'cipher'),
--                           (-30, 1021, '1111'),
--                           (-30, 1022, '12/20'),
--                           (-34, 1023, 'xfghfg'),--salt
--                           (-30, 1066, '0'),
--                           (-31, 1019, 'Bob Wilde'),
--                           (-35, 1020, 'cipher'),
--                           (-31, 1021, '2222'),
--                           (-31, 1022, '12/20'),
--                           (-35, 1023, 'xfghfg'),--salt
--                           (-31, 1066, '1'),
--                           (-32, 1019, 'Bob Wilde'),
--                           (-36, 1020, 'cipher'),
--                           (-32, 1021, '3333'),
--                           (-32, 1022, '12/20'),
--                           (-36, 1023, 'xfghfg');

-- max attr_id: 1092, max obj_type_id: 18