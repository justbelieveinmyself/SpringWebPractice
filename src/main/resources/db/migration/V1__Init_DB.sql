create table messages (
    id bigint not null auto_increment,
    user_id bigint,
    filename varchar(255),
    tag varchar(255),
    text varchar(2048) not null,
    primary key (id))
    engine=InnoDB;

create table user_role (
    user_id bigint not null,
    roles enum ('ADMIN','USER'))
    engine=InnoDB;

create table users (
    active bit not null,
    id bigint not null auto_increment,
    time datetime(6),
    activation_code varchar(255),
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id))
    engine=InnoDB;

create table persistent_logins (
  username varchar(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null)
  engine=InnoDB;

alter table messages
    add constraint message_user_fk
    foreign key (user_id) references users (id);

alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references users (id);

INSERT IGNORE INTO users (id, active, time, email, password, username) values (1, true, NOW(), 'seakme.vadim11@mail.ru', '$2a$08$TlFg0VuU6vy62wQG1JKRe.B1PD1clJ4fpReceKRg/6qrXHDwqTx4u', 'admin');
INSERT IGNORE INTO user_role (user_id, roles) values (1, 'USER'), (1, 'ADMIN');
