drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
  `id` int NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `itemprice` double NOT NULL,
  PRIMARY KEY (`id`)
); 


CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL,
  `custid` int NOT NULL,
  `itemid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `custid` (`custid`),
  KEY `itemid` (`itemid`),
  FOREIGN KEY (`custid`) REFERENCES `customers` (`id`),
  FOREIGN KEY (`itemid`) REFERENCES `items` (`id`)
);

CREATE TABLE `orderline` (
  `id` int NOT NULL,
  `orderid` int DEFAULT NULL,
  `itemid` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderid` (`orderid`),
  KEY `itemid` (`itemid`),
  FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`),
  FOREIGN KEY (`itemid`) REFERENCES `items` (`id`)
);

