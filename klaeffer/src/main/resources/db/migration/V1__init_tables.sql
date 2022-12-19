create table user
(
    klaeff integer  references klaeff (id),
    userinfoid integer  references userinfo(id),
    id integer primary key
);

create table klaeff
(
    id serial primary key
);

create table content
(
    klaeff integer primary key references klaeff (id),
    content text
);
create table userinfo(
                         id serial primary key,
                         image varchar(1000)
);

-- create table user(
--     userinfoid integer primary key references userinfo(id),
--     id integer
-- );

create table username
(
    userinfoid integer primary key references userinfo(id),
    name varchar(200)
);


