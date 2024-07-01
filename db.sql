CREATE TABLE products (
    code INT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    category VARCHAR(255),
    price DOUBLE,
    stock INT
);

INSERT INTO `products`
VALUES (102,'Jabón','Limpieza','0.89','30'),
(103,'Mesa','Hogar','125','4'),
(104,'Televisión','Hogar','350','10'),
(105,'Huevos','Alimentación','2.2','30'),
(106,'fregona','Limpieza','4.5','6'),
(107,'Detergente','Limpieza','8.7','12');