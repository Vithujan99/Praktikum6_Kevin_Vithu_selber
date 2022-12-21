create table klaeff
(
    id serial primary key
);

create table userinfo(
    id serial primary key,
    image varchar(1000)
);

create table userKlaeff
(
    klaeffid integer primary key references klaeff (id),
    id integer
);

create table content
(
    klaeff integer primary key references klaeff (id),
    content text
);

create table username
(
    userinfoid integer primary key references userinfo(id),
    name varchar(200)
);


