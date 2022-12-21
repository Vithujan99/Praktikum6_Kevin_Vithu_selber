create table klaeff(
    id serial primary key,
    content text
);
-- create table content(
--     klaeff integer primary key references klaeff(id),
--     content text
-- );
create table klaeff_user(
    klaeff integer primary key references klaeff(id),
    id integer
);