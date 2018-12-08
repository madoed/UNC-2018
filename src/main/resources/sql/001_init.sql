CREATE TABLE attributes (
                          attr_id     serial primary key,
                          attr_name   varchar(20) not null unique,
                          attr_type   numeric(1) not null
);

CREATE TABLE obj_types (
                         object_type_id   serial primary key,
                         name             varchar(20) not null unique
);

CREATE TABLE obj_attributes (
                              object_type_id   integer REFERENCES obj_types (object_type_id) not null,
                              attr_id          integer REFERENCES attributes (attr_id) not null
);

CREATE TABLE objects (
                       object_id        serial primary key,
                       object_type_id   integer REFERENCES obj_types (object_type_id) not null,
                       object_name      varchar(70)
);

CREATE TABLE params (
                      object_id   integer REFERENCES obj_types (object_type_id) not null,
                      attr_id     integer REFERENCES attributes (attr_id) not null,
                      value       varchar(4000),
                      data        bytea
);

CREATE TABLE refs (
                          object_id   integer REFERENCES obj_types (object_type_id) not null,
                          attr_id     integer REFERENCES attributes (attr_id) not null,
                          reference   integer REFERENCES obj_types (object_type_id) not null
);