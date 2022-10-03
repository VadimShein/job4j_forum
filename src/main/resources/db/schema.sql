create table if not exists topics (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);

create table if not exists messages (
    id serial primary key,
    author varchar(255),
    text text,
    created timestamp without time zone not null default now(),
    topic_id int references topicS(id)
);