DROP TABLE `orderline`;
DROP TABLE `orders`;
DROP TABLE `items`;
DROP TABLE `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemname` varchar(100) NOT NULL,
  `itemprice` double NOT NULL,
  PRIMARY KEY (`id`)
); 


CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `custid` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`custid`) REFERENCES `customers` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `orderline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderid` int DEFAULT NULL,
  `itemid` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`itemid`) REFERENCES `items` (`id`) ON DELETE CASCADE
);

