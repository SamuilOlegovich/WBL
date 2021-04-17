create sequence hibernate_sequence start 1 increment 1;

create table message (
    id int8 not null,
    user_id int8,
    teg varchar(255),
    message varchar(2048) not null,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table player (
    id int8 not null,
    nickName varchar(255) not null,
    email varchar(255),
    password varchar(255) not null,
    activation_code varchar(255),
    active boolean not null,
    wallet varchar(255),
    credits int8,
    primary key (id)
);

alter table if exists messages
    add constraint messages_player_fk
    foreign key (player_id) references player;

alter table if exists player_role
    add constraint player_role_player_fk
    foreign key (player_id) references player;