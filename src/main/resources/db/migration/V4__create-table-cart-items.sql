create table cart_items(

    id bigint not null auto_increment,
    cart_id bigint not null,
    product_id bigint not null,
    amount int not null,
    subtotal float not null,

    primary key(id),
    constraint fk_cart_items_cart_id foreign key(cart_id) references carts(id),
    constraint fk_cart_items_product_id foreign key(product_id) references products(id)

);