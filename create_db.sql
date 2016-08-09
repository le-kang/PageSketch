/**
 * Author:  Le Kang
 * Created: Aug 9, 2016
 */

-- Table for user details
create table users (
    username varchar(30) not null primary key,
    password varchar(30) not null,
    email varchar(255) not null,
    created_at timestamp not null
);

-- Table for page details
create table pages (
    id varchar(36) not null primary key,
    page_name varchar(255) not null,
    current_version int not null,
    created_by varchar(30) references users(username),
    published boolean not null
)

-- Table for page versions
create table page_versions (
    id varchar(36) not null primary key references pages(id),
    version int not null,
    code varchar(32672) not null,
    created_at timestamp not null
)

-- Table for stared pages by user
create table stars (
    username varchar(30) not null references users(username),
    page_id varchar(36) not null references pages(id),
    primary key (username, page_id)
)

-- Table for users' following activity
create table followings (
    username varchar(30) not null references users(username),
    followed_user varchar(30) not null references users(username),
    primary key (username, followed_user)
)
