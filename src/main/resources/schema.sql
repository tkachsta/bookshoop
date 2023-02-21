DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;

CREATE TABLE  authors(
                       id_author INT,
                       `first_name` VARCHAR(50) NOT NULL,
                       `last_name` VARCHAR(50) NOT NULL

);
CREATE TABLE  books(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       id_author INT NOT NULL,
                       title VARCHAR(250) NOT NULL,
                       priceOld  VARCHAR(250) DEFAULT NULL,
                       price VARCHAR(250) DEFAULT NULL,
                       discount INT DEFAULT 0,
                       is_bestseller boolean default false
);