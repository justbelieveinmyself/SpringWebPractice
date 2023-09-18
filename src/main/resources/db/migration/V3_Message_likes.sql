create table if not exists message_likes (
    message_id bigint not null,
    user_id bigint not null)
    engine=InnoDB;

alter table message_likes
    add constraint like_message_id_fk
    foreign key (message_id) references users (id);

alter table message_likes
    add constraint like_user_id_fk
    foreign key (user_id) references messages (id);