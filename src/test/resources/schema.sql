CREATE TABLE IF NOT EXISTS users
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255),
    email            VARCHAR(255),
    age              INTEGER      NOT NULL,
    weight           INTEGER      NOT NULL,
    height           INTEGER      NOT NULL,
    calories_per_day INTEGER      NOT NULL,
    goal             VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS eating
(
    id        SERIAL PRIMARY KEY,
    "date"    DATE,
    user_id   INTEGER,
    CONSTRAINT FK_EATING_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS meal
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255),
    calories      INTEGER      NOT NULL,
    proteins      INTEGER      NOT NULL,
    fats          INTEGER      NOT NULL,
    carbohydrates INTEGER      NOT NULL,
    eating_id     INTEGER,
    CONSTRAINT FK_MEAL_ON_EATING FOREIGN KEY (eating_id) REFERENCES eating (id)
);
