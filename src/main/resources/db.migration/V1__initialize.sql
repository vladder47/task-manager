create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  created_at          timestamp default current_timestamp,
  updated_at          timestamp default current_timestamp,
  primary key (id)
);
insert into users (username, password, email)
values
('Bob Johnson', '123', 'bob_johnson@gmail.com'),
('John Johnson', '321', 'john_johnson@gmail.com');

create table projects (
  id                    bigserial,
  title                 varchar(30) not null,
  leader_id             bigint not null,
  created_at          timestamp default current_timestamp,
  updated_at          timestamp default current_timestamp,
  primary key (id),
  foreign key (leader_id) references users (id)
);

CREATE TABLE users_projects (
  user_id               bigint not null,
  project_id            bigint not null,
  primary key (user_id, project_id),
  foreign key (user_id) references users (id),
  foreign key (project_id) references projects (id)
);

create table tasks (
  id                    bigserial,
  title                 varchar(30) not null,
  description           varchar(5000),
  status                varchar(30),
  priority              varchar(30),
  leader_id             bigint not null,
  project_id            bigint not null,
  deadline              date not null,
  created_at          timestamp default current_timestamp,
  updated_at          timestamp default current_timestamp,
  primary key (id),
  foreign key (leader_id) references users (id),
  foreign key (project_id) references projects (id)
);

CREATE TABLE users_tasks (
  user_id               bigint not null,
  task_id               bigint not null,
  primary key (user_id, task_id),
  foreign key (user_id) references users (id),
  foreign key (task_id) references tasks (id)
);

create table commentary (
  id                    bigserial,
  text                  varchar(255) not null,
  user_id               bigint not null,
  task_id               bigint not null,
  parent                bigint not null,
  created_at            timestamp default current_timestamp,
  updated_at            timestamp default current_timestamp,
  primary key (id),
  foreign key (user_id) references users (id),
  foreign key (task_id) references tasks (id)
);