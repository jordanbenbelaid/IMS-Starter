INSERT INTO `customers` (`id`, `first_name`, `surname`) VALUES (1, 'jordan', 'harrison');
INSERT INTO `items` (`id`, `itemname`, `itemprice`) VALUES (1, 'crisps', 3.5);
INSERT INTO `orders` (`id`, `custid`) VALUES (1, 1);
INSERT INTO `orderline` (`id`, `orderid`, `itemid`, `quantity`, `price`) VALUES (1, 1, 1, 1, null);
