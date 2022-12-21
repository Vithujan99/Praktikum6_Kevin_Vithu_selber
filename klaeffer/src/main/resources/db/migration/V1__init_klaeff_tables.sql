create table klaeff(
    id serial primary key,
    content text
);
-- create table content(
--     klaeff integer primary key references klaeff(id),
--     content text
-- );
create table user_info(
    id serial primary key,
    name varchar(300),
    image varchar(500)
);

-- create table user_name(
--     user_info integer primary key references user_info(id),
--     name text
--  );
create table klaeff_user(
    user_info integer primary key references user_info(id),
    klaeff integer references klaeff(id),
    id integer
);