insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users(username, password, enabled, authority_id) values ('user',
    '$2a$10$FlhPDYAKP39cqj09V8X4/eNsjsa5aIsO4aZZa9MFze0389OV/aAqy', true, 1);

insert into posts(id, name, description, created) values (1, 'Discussion 1', 'Description 1', now());

insert into messages(author, text, created) values('user', 'Comment 1', now());
insert into messages(author, text, created) values('user', 'Comment 2', now());
insert into posts_messages(post_id, messages_id) values(1, 1);
insert into posts_messages(post_id, messages_id) values(1, 2);

