-- User Base Initiation
INSERT INTO users (name, user_type) VALUES ('TestUser1', 1);
INSERT INTO users (name, user_type) VALUES ('TestUser2', 0);
INSERT INTO users (name, user_type) VALUES ('TestUser3', 2);

-- Car Base Initiation
INSERT INTO cars (name) VALUES ('TestCar1');
INSERT INTO cars (name) VALUES ('TestCar2');
INSERT INTO cars (name) VALUES ('TestCar3');
INSERT INTO cars (name) VALUES ('TestCar4');

-- Engine Base Initiation
INSERT INTO engine (name, electrical, cilinders, fuel) VALUES ('TestEngine1', 0, 8, 0);
INSERT INTO engine (name, electrical, cilinders, fuel) VALUES ('TestEngine2', 0, 8, 0);
INSERT INTO engine (name, electrical, cilinders, fuel) VALUES ('TestEngine3', 0, 8, 0);

-- Engine Config Base Initiation
INSERT INTO engine_configuration (config_name, engine_main_sync_time, demon_use, eco_use, standard_use) VALUES ('TestEngineConfig1', 1000, 0, 0, 1);
INSERT INTO engine_configuration (config_name, engine_main_sync_time, demon_use, eco_use, standard_use) VALUES ('TestEngineConfig2', 1000, 0, 0, 1);
INSERT INTO engine_configuration (config_name, engine_main_sync_time, demon_use, eco_use, standard_use) VALUES ('TestEngineConfig3', 1000, 0, 0, 1);

-- Car User Relation
UPDATE cars SET user_id = 1 WHERE id = 1;
UPDATE cars SET user_id = 1 WHERE id = 2;
UPDATE cars SET user_id = 3 WHERE id = 3;

-- Car Engine Relation
UPDATE cars SET engine_id = 1 WHERE id = 1;
UPDATE cars SET engine_id = 2 WHERE id = 2;
UPDATE cars SET engine_id = 3 WHERE id = 3;

-- Engine Config Engine Relation
UPDATE engine SET engine_config_id = 1 WHERE id = 1;
UPDATE engine SET engine_config_id = 2 WHERE id = 2;
UPDATE engine SET engine_config_id = 3 WHERE id = 3;
