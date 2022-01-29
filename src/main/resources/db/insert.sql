insert into posts(name, description, created) values ('Discussion 1', 'Description 1', now());
insert into messages(author, text, created) values('user', 'Comment 1', now());
insert into messages(author, text, created) values('user', 'Comment 2', now());
insert into posts_messages(post_id, messages_id) values(1, 1);
insert into posts_messages(post_id, messages_id) values(1, 2);