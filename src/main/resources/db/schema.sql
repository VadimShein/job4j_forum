create table if not exists posts (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);

create table if not exists messages (
    id serial primary key,
    author varchar(255),
    text text,
    created timestamp without time zone not null default now()
);

create table if not exists posts_messages (
    post_id int references posts(id),
    messages_id int references messages(id)
);