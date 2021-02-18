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
  `itemprice` float NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `custid` int NOT NULL,
  `itemid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `custid` (`custid`),
  KEY `itemid` (`itemid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`custid`) REFERENCES `customers` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`itemid`) REFERENCES `items` (`id`)
);

