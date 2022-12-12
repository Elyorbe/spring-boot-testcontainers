DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);