drop sequence if exists bucket_seq;
create sequence bucket_seq start 1 increment 1;

drop sequence if exists category_seq;
create sequence category_seq start 1 increment 1;

drop sequence if exists order_details_seq;
create sequence order_details_seq start 1 increment 1;

drop sequence if exists order_seq;
create sequence order_seq start 1 increment 1;

drop sequence if exists product_seq;
create sequence product_seq start 1 increment 1;

drop sequence if exists user_seq;
create sequence user_seq start 1 increment 1;

drop table if exists buckets cascade;
create table buckets
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);

drop table if exists buckets_products cascade;
create table buckets_products
(
    bucket_id  int8 not null,
    product_id int8 not null
);

drop table if exists categories cascade;
create table categories
(
    id    int8 not null,
    title varchar(255),
    primary key (id)
);

drop table if exists orders cascade;
create table orders
(
    id      int8 not null,
    address varchar(255),
    created timestamp,
    status  varchar(255),
    sum     numeric(19, 2),
    updated timestamp,
    user_id int8,
    primary key (id)
);

drop table if exists orders_details cascade;
create table orders_details
(
    id         int8 not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    order_id   int8,
    product_id int8,
    details_id int8 not null,
    primary key (id)
);

drop table if exists products cascade;
create table products
(
    id    int8 not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);

drop table if exists products_categories cascade;
create table products_categories
(
    product_id  int8 not null,
    category_id int8 not null
);

drop table if exists users cascade;
create table users
(
    id        int8    not null,
    name  varchar(255),
    password  varchar(255),
    email     varchar(255),
    archive   boolean not null,
    role      varchar(255),
    primary key (id)
);
alter table if exists orders_details
    add constraint orders_details_detail unique (details_id);
alter table if exists buckets
    add constraint buckets_users foreign key (user_id) references users;
alter table if exists buckets_products
    add constraint buckets_products_product foreign key (product_id) references products;
alter table if exists buckets_products
    add constraint buckets_products_bucket foreign key (bucket_id) references buckets;
alter table if exists orders
    add constraint orders_user foreign key (user_id) references users;
alter table if exists orders_details
    add constraint orders_details_order foreign key (order_id) references orders;
alter table if exists orders_details
    add constraint orders_details_product foreign key (product_id) references products;
alter table if exists orders_details
    add constraint orders_details_order_detail foreign key (details_id) references orders_details;
alter table if exists products_categories
    add constraint products_categories_category foreign key (category_id) references categories;
alter table if exists products_categories
    add constraint products_categories_product foreign key (product_id) references products;
