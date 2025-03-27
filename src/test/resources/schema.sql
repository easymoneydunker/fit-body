DROP TABLE IF EXISTS eating_meals CASCADE;
DROP TABLE IF EXISTS meal CASCADE;
DROP TABLE IF EXISTS eating CASCADE;
DROP TABLE IF EXISTS daily_report CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name             VARCHAR(255),
    email            VARCHAR(255),
    age              INTEGER                                             NOT NULL,
    weight           INTEGER                                             NOT NULL,
    height           INTEGER                                             NOT NULL,
    calories_per_day INTEGER                                             NOT NULL,
    goal             VARCHAR(255)
);

CREATE TABLE daily_report
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    user_id             BIGINT                                              NOT NULL,
    "date"              DATE                                                NOT NULL,
    total_proteins      INT,
    total_fats          INT,
    total_carbohydrates INT,
    total_calories      INT,
    caloric_target      INT,
    CONSTRAINT FK_DAILY_REPORT_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE eating
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    "date"          DATE                                                NOT NULL,
    user_id         BIGINT                                              NOT NULL,
    daily_report_id BIGINT,
    CONSTRAINT FK_EATING_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FK_EATING_ON_DAILY_REPORT FOREIGN KEY (daily_report_id) REFERENCES daily_report (id) ON DELETE CASCADE
);

CREATE TABLE meal
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name          VARCHAR(255)                                        NOT NULL,
    calories      INTEGER                                             NOT NULL,
    proteins      INTEGER                                             NOT NULL,
    fats          INTEGER                                             NOT NULL,
    carbohydrates INTEGER                                             NOT NULL
);

CREATE TABLE eating_meals
(
    eating_id BIGINT NOT NULL,
    meal_id   BIGINT NOT NULL,
    PRIMARY KEY (eating_id, meal_id),
    CONSTRAINT FK_EATING_MEALS_EATING FOREIGN KEY (eating_id) REFERENCES eating (id) ON DELETE CASCADE,
    CONSTRAINT FK_EATING_MEALS_MEAL FOREIGN KEY (meal_id) REFERENCES meal (id) ON DELETE CASCADE
);
