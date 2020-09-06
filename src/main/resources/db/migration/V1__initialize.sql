create table users (
  id                    bigserial primary key,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  created_at          timestamp default current_timestamp,
  updated_at          timestamp default current_timestamp
);
insert into users (username, password, email)
values
('Bob Johnson', '123', 'bob_johnson@gmail.com'),
('user', '123', 'user@gmail.com'),
('John Johnson', '321', 'john_johnson@gmail.com');

create table projects (
    id                    bigserial primary key,
    title                 varchar(30) not null,
    leader_id             bigserial not null references users(id),
--     users                 bigserial,
--     tasks                 bigint,
    deadline              timestamp without time zone,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);
INSERT INTO projects (title, leader_id, deadline)
VALUES
('Project 1', 1, '2020-09-30'),
('Project 2', 3, '2010-08-22');

CREATE TABLE projects_users (
    id                  bigserial,
    project_id            bigserial not null references projects(id),
    user_id               bigserial not null references users(id),
  primary key (user_id, project_id)
--   foreign key (user_id) references users (id),
--   foreign key (project_id) references projects (id)
);

INSERT INTO projects_users (project_id, user_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2);

create table tasks (
  id                    bigserial primary key,
  title                 varchar(30) not null,
  description           varchar(5000),
  status                varchar(30),
  priority              varchar(30),
  leader_id             bigserial not null references users(id),
  project_id            bigserial not null references projects(id),
  deadline              date not null,
  created_at          timestamp default current_timestamp,
  updated_at          timestamp default current_timestamp
);

CREATE TABLE users_tasks (
  user_id               bigserial not null,
  task_id               bigserial not null,
  primary key (user_id, task_id),
  foreign key (user_id) references users (id),
  foreign key (task_id) references tasks (id)
);

create table commentary (
  id                    bigserial,
  text                  varchar(255) not null,
  user_id               bigserial not null,
  task_id               bigserial not null,
  parent                bigserial not null,
  created_at            timestamp default current_timestamp,
  updated_at            timestamp default current_timestamp,
  primary key (id),
  foreign key (user_id) references users (id),
  foreign key (task_id) references tasks (id)
);