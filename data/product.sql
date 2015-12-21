DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(20) NOT NULL COMMENT 'Gopher Groceries SKU number',
  `name` varchar(45) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `price` decimal(5,2) NOT NULL DEFAULT '0.00',
  `inventory` int(11) DEFAULT NULL,
  `popular` varchar(1) DEFAULT 'N' COMMENT 'y or Y = YES\nn or N or null = no\n',
  `magefile` varchar(45) NOT NULL DEFAULT 'resources/images/photonotavailable.jpg' COMMENT 'file location of the product image relative to context root',
  `category` varchar(45) NOT NULL DEFAULT 'none' COMMENT 'The Category this product is in. ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='This is the main product table for gophergroceries';


LOCK TABLES gophergroceries.product WRITE;
INSERT INTO gophergroceries.product(id,sku, name, description, price,inventory,popular,magefile,category)  
VALUES (1,'SK01','White Bread','Enriched White Bread',1.00,NULL,'Y','resources/images/photonotavailable.jpg','bread'),
(2,'SK02','Brown Bread','Brown Wonder Bread',1.00,NULL,'Y','resources/images/brownbread.jpg','bread'),
(3,'SK03','White Buns - 6 cnt','White Dinner Buns',1.00,NULL,'Y','resources/images/photonotavailable.jpg','buns'),
(4,'SK04','Brown Dinner Buns - CNT 6','Brown Dinner Rolls - 6 pack',2.00,NULL,'N','resources/images/photonotavailable.jpg','buns'),
(5,'SK05','White Buns - 12 CNT','White Dinner Buns. This is a really long description just to see how it works on a single really long line. &#13; This is the 2nd line I think.',3.00,NULL,'Y','resources/images/photonotavailable.jpg','buns'),
(6,'SK06','Coors Light','MMMMmmmmm Beeeeerrrr. ',10.00,NULL,'Y','resources/images/photonotavailable.jpg','lightbeer');
UNLOCK TABLES;