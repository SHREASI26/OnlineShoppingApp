use shopping_db;

-- USER TABLE
-- create table user(
-- 	user_id int auto_increment,
--     full_name varchar(40) not null,
--     email varchar(50) not null,
--     password varchar(30) not null,
--     address varchar(100) not null,
--     phone_number varchar(10) not null,
--     dob date not null,
--     wallet_balance decimal not null,
--     primary key(user_id)
-- )auto_increment = 100;

-- CATEGORY TABLE
-- create table category(
-- 	category_id int not null auto_increment,
--     category_name varchar(30) not null,
--     primary key(category_id)
-- )auto_increment = 1;

-- PRODUCT-IMAGE TABLE
-- create table product_image(
-- 	image_id int not null auto_increment,
--     product_id int,
--     image_url varchar(300),
--     primary key(image_id),
--     foreign key(product_id) references product(product_id)
-- )auto_increment = 1;

-- PRODUCT TABLE
-- create table product(
-- 	product_id int not null auto_increment,
--     product_name varchar(30) not null,
--     description varchar(150) not null,
--     price decimal not null,
--     stock_quantity int,
--     category_id int not null,
--     primary key(product_id),
--     foreign key(category_id) references category(category_id)
-- )auto_increment = 1;


-- CARTS Table
-- create table cart(
-- 	cart_id int not null auto_increment primary Key,
-- 	user_id int not null,
-- 	foreign key(user_id) references user(user_id)
-- );

-- CART ITEMS Table
-- create table cart_item(
--     item_id int auto_increment primary key,
--     cart_id int not null,
--     product_id int not null,
--     quantity int not null,
--     foreign key(cart_id) references cart(cart_id),
--     foreign key(product_id) references product(product_id)
-- );

-- ORDER Table
-- create table orders(
-- 	order_id int auto_increment,
--     user_id int,
--     order_date date not null,
--     total_amount decimal not null,
--     primary key(order_id),
--     foreign key(user_id) references user(user_id)
-- );

-- ORDER-ITEM Table
-- create table order_item(
-- 	order_item_id int auto_increment primary key,
--     order_id int,
--     product_id int,
--     quantity int,
--     item_price decimal not null,
--     foreign key(order_id) references orders(order_id),
--     foreign key(product_id) references product(product_id)
-- );