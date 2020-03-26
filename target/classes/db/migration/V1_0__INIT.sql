CREATE TABLE customer (
    customer_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY  (customer_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE shipment (
    shipment_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(45) NOT NULL,
    customer_id SMALLINT UNSIGNED,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    PRIMARY KEY  (shipment_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tracking (
    tracking_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    shipment_id SMALLINT UNSIGNED,
    status ENUM ('initial', 'shipped', 'delivered', 'cancelled', 'returned'),
    FOREIGN KEY (shipment_id) REFERENCES shipment (shipment_id),
    PRIMARY KEY  (tracking_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO customer (customer_id, name)
VALUES (1,'Mustermann'),
       (2, 'Petrov'),
       (3, 'Ivanova'),
       (5, 'Sokolov');

INSERT INTO shipment (shipment_id, title, customer_id)
VALUES (1, 'Packet', 1),
       (2, 'ExpressPacket', 5),
       (3, 'Packet', 3),
       (4, 'Packet', 5);

INSERT INTO tracking (tracking_id, shipment_id, status)
VALUES (1, 4, 'initial'),
       (2, 3, 'shipped'),
       (3, 1, 'delivered'),
       (4, 1, 'cancelled'),
       (5, 2, 'returned');
