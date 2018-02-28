---
-- Example Schema using MySQL database
--

DROP TABLE IF EXISTS USE_CASE_3, USE_CASE_2, USE_CASE_1, PROJECT, BOOK, AUTHOR, USER_ROLE, ROLE, PASSPORT, USER_;

CREATE SEQUENCE user_id
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;
CREATE TABLE USER_ (
  id                       INT PRIMARY KEY       DEFAULT nextval('user_id'),
  login                    VARCHAR(100) NOT NULL,
  password                 VARCHAR(100) NOT NULL,
  email                    VARCHAR(100),
  is_enabled               BOOL         NOT NULL DEFAULT TRUE,
  civility                 CHAR(2)               DEFAULT 'MS',
  country_code             VARCHAR(6)            DEFAULT '+33',
  first_name               VARCHAR(100),
  last_name                VARCHAR(100),
  -- audit (detected by celerio by convention)
  creation_date            TIMESTAMP             DEFAULT NOW(),
  creation_author          VARCHAR(200),
  last_modification_date   TIMESTAMP             DEFAULT NOW(),
  last_modification_author VARCHAR(200),
  -- optimistic lock (detected by celerio by convention)
  version                  INT                   DEFAULT 0
  --     constraint user_unique_1 unique (login)
);

CREATE SEQUENCE PASSPORT_id
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;

CREATE TABLE PASSPORT (
  id              INT PRIMARY KEY  DEFAULT nextval('PASSPORT_id'),
  passport_number VARCHAR(100) NOT NULL,
  expiration_date DATE,
  holder_id       INT UNIQUE   NOT NULL  REFERENCES USER_ (id)
);
CREATE SEQUENCE ROLE_id
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;

CREATE TABLE ROLE (
  id        INT PRIMARY KEY  DEFAULT nextval('ROLE_id'),
  role_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE USER_ROLE (
  user_id INT NOT NULL REFERENCES USER_ (id),
  role_id INT NOT NULL REFERENCES ROLE (id),
  PRIMARY KEY (user_id, role_id)
);


INSERT INTO USER_ (id, login, password, email, is_enabled, version)
VALUES (-1, 'admin', 'admin', 'admin@example.com', TRUE, 1);

INSERT INTO ROLE (id, role_name) VALUES (-1, 'ROLE_ADMIN');
INSERT INTO ROLE (id, role_name) VALUES (-2, 'ROLE_USER');
INSERT INTO ROLE (id, role_name) VALUES (-3, 'ROLE_MONITORING');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (-1, -1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (-1, -2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (-1, -3);

-- APP_
CREATE SEQUENCE APP_ID
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;
CREATE TABLE APP_ (
  id            INT PRIMARY KEY  DEFAULT nextval('APP_ID'),
  category_     VARCHAR(50),
  template_path VARCHAR(500)
);
CREATE SEQUENCE APP_WIDGET_ID
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;
CREATE TABLE APP_WIDGET (
  id     INT PRIMARY KEY  DEFAULT nextval('APP_WIDGET_ID'),
  APP_ID INT NOT NULL REFERENCES APP_ (id),
  type   INT NOT NULL,
  RULE   VARCHAR(4000)
);
-- CONFIG_
CREATE SEQUENCE CONFIG_ID
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;
CREATE TABLE CONFIG_ (
  id      INT PRIMARY KEY  DEFAULT nextval('CONFIG_ID'),
  APP_ID  INT NOT NULL REFERENCES APP_ (id),
  user_id INT REFERENCES USER_ (id)
);
--  WIDGET_CONFIG
CREATE SEQUENCE WIDGET_CONFIG_ID
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;
CREATE TABLE WIDGET_CONFIG (
  id         INT PRIMARY KEY DEFAULT nextval('WIDGET_CONFIG_ID'),
  config_id  INT REFERENCES CONFIG_ (id),
  inputValue VARCHAR(4000)
);
--  ARTIFACT
CREATE SEQUENCE ARTIFACT_ID
  INCREMENT BY 1
  MINVALUE 1
  NO MAXVALUE
  START WITH 1;
CREATE TABLE ARTIFACT (
  id          INT PRIMARY KEY  DEFAULT nextval('ARTIFACT_ID'),
  APP_ID      INT NOT NULL REFERENCES APP_ (id),
  user_id     INT NOT NULL REFERENCES USER_ (id),
  CONFIG_ID   INT NOT NULL REFERENCES CONFIG_ (id),
  output_path VARCHAR(500)
);


