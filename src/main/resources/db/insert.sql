insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users(username, password, enabled, authority_id) values ('user',
    '$2a$10$FlhPDYAKP39cqj09V8X4/eNsjsa5aIsO4aZZa9MFze0389OV/aAqy', true, 1);

insert into topics(id, name, description, created) values (1, 'Discussion 1', 'Description 1', '2022-10-02 22:24:48.0');
insert into topics(id, name, description, created) values (2, 'Discussion 2', 'Description 2', '2022-10-03 20:12:37.0');

insert into messages(author, text, created, topic_id) values('user', 'Comment 1', '2022-10-03 22:24:48.0 ', 1);
insert into messages(author, text, created, topic_id) values('user', 'Comment 2', '2022-10-03 23:56:02.0', 1);


