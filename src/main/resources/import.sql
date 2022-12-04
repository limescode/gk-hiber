CREATE SCHEMA IF NOT EXISTS shop;

ALTER TABLE IF EXISTS shop.orders DROP CONSTRAINT IF EXISTS fk_customer_id;
ALTER TABLE IF EXISTS shop.orders DROP CONSTRAINT IF EXISTS fk_product_id;
DROP TABLE IF EXISTS shop.orders;

DROP TABLE IF EXISTS shop.customers;
CREATE TABLE IF NOT EXISTS shop.customers (id bigserial NOT NULL PRIMARY KEY,name VARCHAR(256) NOT NULL);
INSERT INTO shop.customers (name) VALUES ('Justin'), ('Brando'), ('Sabrina');

DROP TABLE IF EXISTS shop.products;
CREATE TABLE IF NOT EXISTS shop.products (id bigserial NOT NULL PRIMARY KEY,title VARCHAR(256)  NOT NULL,price int NOT NULL);
INSERT INTO shop.products (title, price) VALUES ('Mouse', 10), ('Headphones', 20), ('Keyboard', 30);

CREATE TABLE IF NOT EXISTS shop.orders (id bigserial NOT NULL PRIMARY KEY, customer_id bigint NOT NULL, product_id bigint NOT NULL, price int NOT NULL, quantity int, created timestamp NOT NULL, CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES shop.customers (id) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES shop.products (id) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO shop.orders (customer_id, product_id, price, quantity, created) VALUES (1, 1, 10, 1, '2022-12-04'), (1, 2, 20, 2, '2022-12-04'), (2, 1, 10, 3, '2022-12-03'), (2, 3, 30, 1, '2022-12-05')