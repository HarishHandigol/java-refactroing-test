DROP TABLE USERS;

CREATE TABLE USERS
(
    id serial primary key,
    name varchar(100),
    email varchar(250),
    roles varchar[],
    UNIQUE (email)
);

INSERT INTO USERS VALUES (1,'Bill','xyz@yahoo.com','{Manager, admin}');