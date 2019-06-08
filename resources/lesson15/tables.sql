CREATE TABLE users
(
    id          serial PRIMARY KEY,
    name        VARCHAR(25),
    birthday    Date,
    login_id    int,
    city        varchar(30),
    email       varchar(30),
    description VARCHAR(255)
);

CREATE TYPE role_names as ENUM ('Administration', 'Clients', 'Billing');

CREATE TABLE roles
(
    id          serial PRIMARY KEY,
    name        role_names,
    description VARCHAR(255)
);

CREATE TABLE user_role
(
    id      SERIAL,
    user_id INTEGER REFERENCES users (id),
    role_id INTEGER REFERENCES roles (id),
    PRIMARY KEY (id, user_id, role_id)
);

CREATE TABLE logs
(
    id        SERIAL,
    date      TIMESTAMPTZ,
    log_level VARCHAR(10)  NOT NULL,
    message   VARCHAR(255) NOT NULL,
    exception VARCHAR(255)
)