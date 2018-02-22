---
-- Example Schema using MySQL database
--

DROP TABLE IF EXISTS USE_CASE_3, USE_CASE_2, USE_CASE_1, PROJECT, BOOK, AUTHOR, USER_ROLE, ROLE, PASSPORT, USER_;

CREATE SEQUENCE user_id increment by 1 minvalue 1 no maxvalue start with 1;
CREATE TABLE USER_ (
    id                   int   primary key  default nextval('user_id'),
    login                    varchar(100) not null ,
    password                 varchar(100) not null,
    email                    varchar(100),
    is_enabled               bool not null default true,
    civility                 char(2) default 'KM',
    country_code             varchar(6) default '+33',
    first_name               varchar(100),
    last_name                varchar(100),
-- audit (detected by celerio by convention)
    creation_date            timestamp DEFAULT NOW(),
    creation_author          varchar(200),
    last_modification_date   timestamp default NOW(),
    last_modification_author varchar(200),
-- optimistic lock (detected by celerio by convention)
    version                  int default 0
--     constraint user_unique_1 unique (login)
) ;

CREATE SEQUENCE PASSPORT_id increment by 1 minvalue 1 no maxvalue start with 1;

CREATE TABLE PASSPORT (
    id                       int primary key  default nextval('PASSPORT_id'),
    passport_number          varchar(100) not null,
    expiration_date          date,
    holder_id                int unique not null  references USER_(id)
) ;
CREATE SEQUENCE ROLE_id increment by 1 minvalue 1 no maxvalue start with 1;

CREATE TABLE ROLE (
    id              int primary key  default nextval('ROLE_id'),
    role_name       varchar(100) not null UNIQUE
);

CREATE TABLE USER_ROLE (
    user_id     int not null references USER_(id),
    role_id     int not null references ROLE(id),
    primary key (user_id, role_id)
);



INSERT INTO USER_ (id, login, password, email, is_enabled, version) VALUES (-1, 'admin', 'admin', 'admin@example.com', true, 1);

INSERT INTO ROLE (id, role_name) VALUES (-1, 'ROLE_ADMIN');
INSERT INTO ROLE (id, role_name) VALUES (-2, 'ROLE_USER');
INSERT INTO ROLE (id, role_name) VALUES (-3, 'ROLE_MONITORING');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (-1, -1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (-1, -2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (-1, -3);

