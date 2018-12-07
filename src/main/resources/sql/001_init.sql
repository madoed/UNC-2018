DROP SCHEMA IF EXISTS meetupdb CASCADE ;
create schema meetupdb;

set search_path to meetupdb;

create sequence global_id_sequence;

create or replace function id_generator(out result bigint) as $$
  declare
  our_epoch bigint := 10000;
seq_id bigint;
now_millis bigint;
-- the id of this db shard, must be set for each
-- schema shard you have - you could pass this as a parameter too
shard_id int := 1;
begin
  select nextval('global_id_sequence') % 1024 into seq_id;

  select floor(extract(epoch from clock_timestamp()) * 1000) into now_millis;
  result := (now_millis - our_epoch) << 23;
  result := result | (shard_id << 10);
  result := result | (seq_id);
end;
$$ language plpgsql;

create table attributes
(
  attr_id   bigint primary key default id_generator(),
  attr_name varchar(20) unique not null,
  attr_type int  not null
);

create table obj_attributes
(
  object_type_id bigint not null,
  attr_id        bigint not null
);

create table obj_types
(
  object_type_id bigint primary key default id_generator(),
  name           varchar(20) unique not null
);

create table objects
(
  object_id      bigint primary key default id_generator(),
  object_type_id bigint not null,
  object_name    varchar(70)
);

create table params
(
  object_id bigint not null,
  attr_id  bigint not null,
  value     varchar(4000)
);

create table refer
(
  object_id bigint not null,
  attr_id   bigint not null,
  reference bigint not null
);

alter table obj_attributes
  add constraint obj_attributes_attributes foreign key (attr_id)
    references attributes (attr_id);

alter table obj_attributes
  add constraint obj_attributes_obj_types foreign key (object_type_id)
    references obj_types (object_type_id);

alter table objects
  add constraint obj_types_objects foreign key (object_type_id)
    references obj_types (object_type_id);

alter table refer
  add constraint objects_references foreign key (reference)
    references objects (object_id);

alter table params
  add constraint params_attributes foreign key (attr_id)
    references attributes (attr_id);

alter table params
  add constraint params_objects foreign key (object_id)
    references objects (object_id);

alter table refer
  add constraint references_attributes foreign key (attr_id)
    references attributes (attr_id);

alter table refer
  add constraint references_objects foreign key (object_id)
    references objects (object_id);
