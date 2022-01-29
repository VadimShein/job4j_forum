create table if not exists authorities (
    id serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

create table if not exists users (
    id serial primary key,
    username VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);