CREATE TABLE attributes (
                          attr_id     NUMBER(6) NOT NULL,
                          attr_name   VARCHAR2(20) NOT NULL,
                          attr_type   NUMBER(1) NOT NULL
);

ALTER TABLE attributes ADD CONSTRAINT attributes_pk PRIMARY KEY ( attr_id );

ALTER TABLE attributes ADD CONSTRAINT attr_name UNIQUE ( attr_name );

CREATE TABLE obj_attributes (
                              object_type_id   NUMBER(6) NOT NULL,
                              attr_id          NUMBER(6) NOT NULL
);

CREATE TABLE obj_types (
                         object_type_id   NUMBER(6) NOT NULL,
                         name             VARCHAR2(20) NOT NULL
);

ALTER TABLE obj_types ADD CONSTRAINT obj_types_pk PRIMARY KEY ( object_type_id );

ALTER TABLE obj_types ADD CONSTRAINT obj_types_ak_1 UNIQUE ( name );

CREATE TABLE objects (
                       object_id        NUMBER(6) NOT NULL,
                       object_type_id   NUMBER(6) NOT NULL,
                       object_name      VARCHAR2(70)
);

ALTER TABLE objects ADD CONSTRAINT objects_pk PRIMARY KEY ( object_id );

CREATE TABLE params (
                      object_id   NUMBER(6) NOT NULL,
                      attr_id     NUMBER(6) NOT NULL,
                      value       VARCHAR2(4000 CHAR),
                      data        BLOB
);

CREATE TABLE references (
                          object_id   NUMBER(6) NOT NULL,
                          attr_id     NUMBER(6) NOT NULL,
                          reference   NUMBER(6) NOT NULL
);

ALTER TABLE obj_attributes
  ADD CONSTRAINT obj_attributes_attributes FOREIGN KEY ( attr_id )
    REFERENCES attributes ( attr_id );

ALTER TABLE obj_attributes
  ADD CONSTRAINT obj_attributes_obj_types FOREIGN KEY ( object_type_id )
    REFERENCES obj_types ( object_type_id );

ALTER TABLE objects
  ADD CONSTRAINT obj_types_objects FOREIGN KEY ( object_type_id )
    REFERENCES obj_types ( object_type_id );

ALTER TABLE references
  ADD CONSTRAINT objects_references FOREIGN KEY ( reference )
    REFERENCES objects ( object_id );

ALTER TABLE params
  ADD CONSTRAINT params_attributes FOREIGN KEY ( attr_id )
    REFERENCES attributes ( attr_id );

ALTER TABLE params
  ADD CONSTRAINT params_objects FOREIGN KEY ( object_id )
    REFERENCES objects ( object_id );

ALTER TABLE references
  ADD CONSTRAINT references_attributes FOREIGN KEY ( attr_id )
    REFERENCES attributes ( attr_id );

ALTER TABLE references
  ADD CONSTRAINT references_objects FOREIGN KEY ( object_id )
    REFERENCES objects ( object_id );



-- Oracle SQL Developer Data Modeler Summary Report:
--
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             13
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
--
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
--
-- REDACTION POLICY                         0
--
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
--
-- ERRORS                                   0
-- WARNINGS                                 0