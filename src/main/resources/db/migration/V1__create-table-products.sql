create table products(

    id bigint not null auto_increment,
    name varchar(100) not null,
    code bigint not null,
    unit_price float not null,
    unit_type varchar(100) not null,
    tax_percent float not null,

    primary key(id)

);