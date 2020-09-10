create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into users (username, password, email)
values
('Bob Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('John Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

create table projects (
    id                    bigserial primary key,
    title                 varchar(30) not null,
    leader_id             bigint not null references users(id),
    deadline              date,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

CREATE TABLE projects_users
(
    project_id bigint not null references projects(id),
    user_id    bigint not null references users(id),
    primary key (user_id, project_id)
);

INSERT INTO projects_users (project_id, user_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2);

create table tasks
(
    id          bigserial primary key,
    title       varchar(30) not null,
    description varchar(5000),
    status      varchar(30),
    priority    varchar(30),
    leader_id   bigserial   not null references users (id),
    project_id  bigserial   not null references projects (id),
    deadline    date        not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

CREATE TABLE users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    foreign key (user_id) references users (id),
    foreign key (task_id) references tasks (id)
);

create table commentary
(
    id         bigserial,
    text       varchar(255) not null,
    user_id    bigint       not null,
    task_id    bigint       not null,
    parent     bigint       not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (task_id) references tasks (id)
);

insert into users (username, password, email)
values ('Bob Johnson', '123', 'bob_johnson@gmail.com'),
       ('user', '123', 'user@gmail.com'),
       ('John Johnson', '321', 'john_johnson@gmail.com'),
       ('Vladislav', 'qwerty', 'vladisdrozdov@gmail.com'),
       ('Andrey', 'qwerty', 'asdfghrewq@gmail.com');

INSERT INTO projects(title, leader_id)
VALUES ('Project 1', 1),
       ('Project 2', 2);

INSERT INTO projects_users (user_id, project_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (4, 1),
       (5, 2);

INSERT INTO tasks (title, description, status, priority, leader_id, project_id, deadline)
VALUES ('Task 1', 'Description 1', 'CREATED', 'PLANNING', 1, 1, '2020-09-05'),
       ('Task 2', 'Description 2', 'CREATED', 'PLANNING', 1, 1, '2020-09-05'),
       ('Task 3', 'Description 3', 'CREATED', 'PLANNING', 1, 1, '2020-09-05'),
       ('Task 4', 'Description 4', 'CREATED', 'PLANNING', 1, 1, '2020-09-05'),
       ('Task 5', 'Description 5', 'CREATED', 'PLANNING', 2, 2, '2020-09-05'),
       ('Task 6', 'Description 6', 'CREATED', 'PLANNING', 2, 2, '2020-09-05'),
       ('Task 7', 'Description 7', 'CREATED', 'PLANNING', 2, 2, '2020-09-05'),
       ('Task 8', 'Description 8', 'CREATED', 'PLANNING', 2, 2, '2020-09-05'),
       ('Task 9', 'Description 9', 'CREATED', 'PLANNING', 2, 2, '2020-09-05'),
       ('Task 10', 'Description 10', 'CREATED', 'VERYLOW', 1, 1, '2020-09-05'),
       ('Task 11', 'Description 11', 'INPROGRESS', 'VERYLOW', 1, 1, '2020-09-05'),
       ('Task 12', 'Description 12', 'INPROGRESS', 'VERYLOW', 1, 1, '2020-09-05'),
       ('Task 13', 'Description 13', 'INPROGRESS', 'VERYLOW', 1, 1, '2020-09-05'),
       ('Task 14', 'Description 14', 'CHECKING', 'VERYLOW', 2, 2, '2020-09-05'),
       ('Task 15', 'Description 15', 'CHECKING', 'HIGH', 2, 2, '2020-09-05'),
       ('Task 16', 'Description 16', 'CHECKING', 'HIGH', 2, 2, '2020-09-05'),
       ('Task 17', 'Description 17', 'CHECKING', 'HIGH', 2, 2, '2020-09-05');

CREATE TABLE files (id bigserial primary key,
                    filename text not null,
                    created_at timestamp default current_timestamp,
                    updated_at timestamp default current_timestamp
);

INSERT INTO files (filename)
VALUES  ('README.md'),
        ('fileTest.txt');

INSERT INTO users_tasks (user_id, task_id)
VALUES (1, 1),
       (1, 2);

INSERT INTO commentary (text, user_id, task_id, parent)
VALUES ('В течение дня будет готово!', 1, 1, 0),
       ('Эта же задача уже была решена?', 2, 1, 0),
       ('Нашел новый подход к реализации этой идеи', 1, 1, 0),
       ('Сделано!', 2, 1, 0),
       ('Отличный результат!', 2, 1, 0);
