/**
 * Author:  Le Kang
 * Created: Aug 9, 2016
 */

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

-- View for users' activities summary
create view user_activities as
select author as username, case when page_versions.version = 1 THEN 'Created' else 'Updated' end as user_action, page_name, pages.id as page_id, created_at
from page_versions inner join pages on page_versions.id = pages.id 
union
select username, 'Starred' as user_action, page_name, page_id, created_at from stars inner join pages on stars.page_id = pages.id
