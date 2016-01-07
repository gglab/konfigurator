/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  marr
 * Created: 2016-01-06
 */

drop database konfigurator
GO

create DATABASE konfigurator
GO

use konfigurator;
GO

CREATE TABLE products 
(
	id INT CONSTRAINT product_PK NOT NULL IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE,
	standardPrice MONEY NOT NULL,
	isActive BIT NOT NULL DEFAULT 1
);
GO

CREATE TABLE options
(
	id INT CONSTRAINT option_FK NOT NULL IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	isDefault BIT NOT NULL,
	price MONEY NOT NULL,
	isActive BIT NOT NULL DEFAULT 1
);
GO

CREATE TABLE groups
(
	id INT CONSTRAINT group_PK NOT NULL IDENTITY(1,1) PRIMARY KEY,
	groupName VARCHAR (30) NOT NULL UNIQUE
);
GO

CREATE TABLE productToOptionLink
(
	product2option INT CONSTRAINT p2o_PK NOT NULL IDENTITY(1,1) PRIMARY KEY,
	productID INT CONSTRAINT p2o_product_FK FOREIGN KEY REFERENCES products (id),
	optionID INT CONSTRAINT p2o_option_FK FOREIGN KEY REFERENCES options (id),
	groupID INT CONSTRAINT p2o_group_FK FOREIGN KEY REFERENCES groups (id) 
);
GO

CREATE TABLE rules
(
	id INT CONSTRAINT rule_FK NOT NULL IDENTITY(1,1) PRIMARY KEY,
	productID INT CONSTRAINT r_product_FK FOREIGN KEY REFERENCES products (id) NOT NULL,
	option1ID INT CONSTRAINT r_option_FK1 FOREIGN KEY REFERENCES options (id) NOT NULL,
	option2ID INT CONSTRAINT r_option_FK2 FOREIGN KEY REFERENCES options (id) NOT NULL,
	CHECK (option1ID <> option2ID)
);
GO

INSERT INTO products VALUES ('stół', 150, 1)

INSERT INTO groups VALUES ('wysokość')
INSERT INTO groups VALUES ('materiał')

INSERT INTO options VALUES ('130cm', 1, 120, 1)
INSERT INTO options VALUES ('120cm', 0, 110, 1)
INSERT INTO options VALUES ('100cm', 0, 100, 1)
INSERT INTO options VALUES ('dębina', 0, 200, 1)
INSERT INTO options VALUES ('jesion', 1, 180, 1)
INSERT INTO options VALUES ('100cm', 0, 100, 1)

SELECT * FROM products
SELECT * FROM options
SELECT * FROM groups
SELECT * FROM productToOptionLink

INSERT INTO productToOptionLink VALUES (1,1,1)
INSERT INTO productToOptionLink VALUES (1,2,1)
INSERT INTO productToOptionLink VALUES (1,3,1)
INSERT INTO productToOptionLink VALUES (1,4,2)
INSERT INTO productToOptionLink VALUES (1,5,2)
INSERT INTO productToOptionLink VALUES (2,6,2)


INSERT INTO rules VALUES (1,3,4)
INSERT INTO rules VALUES (1,2,4)