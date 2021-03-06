create sequence global_id_sequence;

create or replace function generate_id(out result bigint) as $$
begin
  result := nextval('global_id_sequence') + 10000;
end;
$$ language plpgsql;

create table attributes
(
  attr_id   bigint primary key default generate_id(),
  attr_name varchar(20) unique not null,
  attr_type int  not null default 0
);

create table obj_attributes
(
  object_type_id bigint not null,
  attr_id        bigint not null
);

create table obj_types
(
  object_type_id bigint primary key default generate_id(),
  name           varchar(20) unique not null
);

create table objects
(
  object_id      bigint primary key default generate_id(),
  object_type_id bigint not null,
  object_name    varchar(70)
);

create table params
(
  object_id bigint not null,
  attr_id   bigint not null,
  value     varchar(4000)
);

create table refs
(
  object_id bigint not null,
  attr_id   bigint not null,
  reference bigint not null
);

alter table obj_attributes
  add constraint obj_attributes_attributes foreign key (attr_id)
    references attributes (attr_id)
      on delete cascade;

alter table obj_attributes
  add constraint obj_attributes_obj_types foreign key (object_type_id)
    references obj_types (object_type_id)
      on delete cascade;

alter table objects
  add constraint obj_types_objects foreign key (object_type_id)
    references obj_types (object_type_id)
      on delete cascade;

alter table refs
  add constraint objects_references foreign key (reference)
    references objects (object_id)
      on delete cascade;

alter table params
  add constraint params_attributes foreign key (attr_id)
    references attributes (attr_id)
      on delete cascade;

alter table params
  add constraint params_objects foreign key (object_id)
    references objects (object_id)
      on delete cascade;

alter table refs
  add constraint references_attributes foreign key (attr_id)
    references attributes (attr_id)
      on delete cascade;

alter table refs
  add constraint references_objects foreign key (object_id)
    references objects (object_id)
      on delete cascade;