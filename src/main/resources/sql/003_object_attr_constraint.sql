ALTER TABLE Params
ADD CONSTRAINT params_unique_object_attr UNIQUE (object_id, attr_id);