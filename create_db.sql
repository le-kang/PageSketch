/**
 * Author:  Le Kang
 * Created: Aug 9, 2016
 */

drop table stars;
drop table page_versions;
drop table pages;
drop table users;

-- Table for user details
create table users (
    username varchar(30) not null primary key,
    password varchar(255) not null,
    full_name varchar(255) not null,
    email varchar(255) not null,
    group_name varchar(10) default 'Users' not null,
    bio varchar(255),
    created_at timestamp default current_timestamp not null
);

-- Table for page details
create table pages (
    id varchar(36) not null primary key,
    page_name varchar(255) not null,
    description varchar(255),
    current_version int not null,
    author varchar(30) references users(username) not null,
    published boolean not null
);

-- Table for page versions
create table page_versions (
    id varchar(36) not null references pages(id),
    version int not null,
    code varchar(32672),
    created_at timestamp default current_timestamp not null,
    primary key (id, version)
);

-- Table for stared pages by user
create table stars (
    username varchar(30) not null references users(username),
    page_id varchar(36) not null references pages(id),
    created_at timestamp default current_timestamp not null,
    primary key (username, page_id)
);
