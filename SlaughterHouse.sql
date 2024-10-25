CREATE SCHEMA MeatHouse;
SET search_path TO MeatHouse;

CREATE TABLE Animals (

    animal_id SERIAL PRIMARY KEY,
    weight DECIMAL(10, 2) NOT NULL,
    arrival_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);


CREATE TABLE Animal_parts (
    part_id SERIAL PRIMARY KEY,
    animal_id INT REFERENCES animals(animal_id) ON DELETE CASCADE,
    weight DECIMAL(10, 2) NOT NULL,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE Trays (
    tray_id SERIAL PRIMARY KEY,
    max_capacity DECIMAL(10, 2) NOT NULL,
    current_weight DECIMAL(10, 2) DEFAULT 0,
    part_type VARCHAR(100) NOT NULL
);

CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE partsTrays(
    part_id INT references animal_parts(part_id) ON DELETE CASCADE,
    tray_id INT references trays(tray_id) ON DELETE CASCADE,

 PRIMARY KEY (part_id, tray_id)
);


CREATE TABLE traysProducts(
    tray_id INT references trays(tray_id) ON DELETE CASCADE,
    product_id INT references products(product_id) ON DELETE CASCADE,

 PRIMARY KEY (product_id, tray_id)
);

INSERT INTO animals (weight, arrival_date, status)
VALUES (300.50, '2024-01-10', 'arrived'),
       (250.00, '2024-01-12', 'processed'),
       (275.30, '2024-01-15', 'arrived');

INSERT INTO trays (max_capacity, current_weight, part_type)
VALUES (100.00, 25.50, 'leg'),
       (100.00, 19.25, 'rib');

INSERT INTO animal_parts (animal_id, weight, type)
VALUES (1, 15.00, 'leg'),
       (1, 10.50, 'rib'),
       (2, 12.00, 'leg'),
       (3, 14.25, 'leg'),
       (3, 8.75, 'rib');


INSERT INTO products (type)
VALUES ('half-animal'),
       ('legs-package'),
       ('ribs-package');

INSERT INTO partstrays (part_id, tray_id)
VALUES (1, 1),
        (3, 1),
        (2,2);

INSERT INTO traysproducts (product_id, tray_id)
VALUES (2, 1),
        (3,2);

SELECT * FROM MeatHouse.Animals;

SELECT * FROM MeatHouse.Animal_parts;

SELECT * FROM MeatHouse.Trays;

SELECT * FROM MeatHouse.Products;



