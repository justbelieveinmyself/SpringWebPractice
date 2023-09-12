create table if not exists user_subscriptions (
    subscriber_id bigint not null,
    channel_id bigint not null,
    primary key (subscriber_id, channel_id))
    engine=InnoDB;

create table if not exists persistent_logins (
    username varchar(100) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null)
    engine=InnoDB;

alter table user_subscriptions
    add constraint subscription_user_fk
    foreign key (channel_id) references users (id);

alter table user_subscriptions
    add constraint subscriber_user_fk
    foreign key (subscriber_id) references users (id);

--INSERT IGNORE INTO users (id, active, time, email, password, username)
--    VALUES (1, true, NOW(), 'seakme.vadim11@mail.ru', '$2a$08$TlFg0VuU6vy62wQG1JKRe.B1PD1clJ4fpReceKRg/6qrXHDwqTx4u', 'admin');
--
--INSERT IGNORE INTO user_role (user_id, roles)
--    VALUES (1, 'USER'), (1, 'ADMIN');