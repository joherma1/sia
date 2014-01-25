create table users (
    id          integer primary key,
    username    varchar(255) unique not null,
    password    varchar(255) not null,
    enabled     boolean not null default 't'
);

insert into users values (1, 'admin', '1234', 't');
insert into users values (2, 'cysun', 'abcd', 't');

create table authorities (
    username    varchar(255) not null references users(username),
    authority   varchar(255)
);

insert into authorities values('admin', 'ROLE_ADMIN');
insert into authorities values('admin', 'ROLE_USER');
insert into authorities values('cysun', 'ROLE_USER');
