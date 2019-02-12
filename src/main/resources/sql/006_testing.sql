insert into objects values (-1, 1, 'Alice Smith'),
                           (-2, 1, 'Bob Wilde'),
                           (-3, 1, 'Davy Jones');
insert into refs values (-1, 1006, -2),
                        (-1, 1006, -3),
                        (-2, 1006, -1),
                        (-3, 1006, -1);
insert into params values (-1, 1001, 'Alice'),
                          (-1, 1002, 'Smith'),
                          (-1, 1003, 'alice'),
                          (-1, 1004, 'alice@example.com'),
                          (-1, 1005, 'qwerty'),
                          (-1, 1007, '08-01-2019 22:53:54');

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

insert into objects values (-7, 6, 'First chat' ),
                           (-8, 6, 'Second chat' );
insert into refs values (-7, 1030, -1),
                        (-7, 1030, -2),
                        (-8, 1030, -1);
insert into params values (-7, 1029, 'First chat!'),
                          (-8, 1029, 'Second chat!');