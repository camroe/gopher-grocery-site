CREATE DATABASE  IF NOT EXISTS `gophergroceries` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gophergroceries`;
-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: gophergroceries
-- ------------------------------------------------------
-- Server version	5.6.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'cam','user');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `urlAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (95,'Baking','#'),(96,'Baking & Cooking Needs','#'),(97,'Beer/Wine/Spirits','#'),(98,'Beverages','#'),(99,'Breakfast & Cereal','#'),(100,'Canned Goods and Soups','#'),(101,'Chips/Snacks','#'),(102,'Condiments and Sauces','#'),(103,'Dairy','#'),(104,'Deli','#'),(105,'Frozen','#'),(106,'Fruits and Vegetables','#'),(107,'Grain, Pasta and Beans','#'),(108,'Meat and Seafood','#');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_authorities`
--

DROP TABLE IF EXISTS `group_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL COMMENT 'This is the foreign key to the groups table ‘id’.',
  `authority` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_authorities`
--

LOCK TABLES `group_authorities` WRITE;
/*!40000 ALTER TABLE `group_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `group_id` int(11) NOT NULL COMMENT 'This is the Foreign key to the groups table ‘id’. ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (112),(112);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderlines`
--

DROP TABLE IF EXISTS `orderlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderlines` (
  `id` int(11) NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_FK` int(11) DEFAULT NULL,
  `product_FK` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfb70lru6v8kqiusmku9jltb2y` (`order_FK`),
  KEY `FK1lim7utdoes71moae1xrxt4n4` (`product_FK`),
  CONSTRAINT `FK1lim7utdoes71moae1xrxt4n4` FOREIGN KEY (`product_FK`) REFERENCES `product` (`id`),
  CONSTRAINT `FKfb70lru6v8kqiusmku9jltb2y` FOREIGN KEY (`order_FK`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlines`
--

LOCK TABLES `orderlines` WRITE;
/*!40000 ALTER TABLE `orderlines` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderlines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `confirmationid` varchar(255) DEFAULT NULL,
  `creationdate` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `paypaynumber` varchar(255) DEFAULT NULL,
  `sessionid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `magefile` varchar(255) DEFAULT NULL,
  `inventory` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `popular` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'bread','Enriched White Bread','resources/images/errorguy.jpg',NULL,'White Bread','Y',1.00,'SK01'),(2,'bread','Brown Wonder Bread','resources/images/brownbread.jpg',NULL,'Brown Bread','Y',1.00,'SK02'),(3,'buns','White Dinner Buns','resources/images/errorguy.jpg',NULL,'White Buns - 6 cnt','Y',1.00,'SK03'),(4,'buns','Brown Dinner Rolls - 6 pack','resources/images/errorguy.jpg',NULL,'Brown Dinner Buns - CNT 6','N',2.00,'SK04'),(5,'buns','White Dinner Buns. This is a really long description just to see how it works on a single really long line. &#13; This is the 2nd line I think.','resources/images/errorguy.jpg',NULL,'White Buns - 12 CNT','Y',3.00,'SK05'),(6,'lightbeer','MMMMmmmmm Beeeeerrrr. ','resources/images/errorguy.jpg',NULL,'Coors Light','Y',10.00,'SK06');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `urlAddress` varchar(255) DEFAULT NULL,
  `category_FK` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjw6gb7ieg805xnufowqp951u8` (`category_FK`),
  CONSTRAINT `FKjw6gb7ieg805xnufowqp951u8` FOREIGN KEY (`category_FK`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (96,'Baking Mixes','bakingmixes',96),(97,'Baking Morsels, Bars & Cocoa','bakingmorsels',96),(98,'Dough','dough',96),(99,'Bread','bread',95),(100,'Buns','buns',95),(101,'Coffee & Filters','coffee',98),(102,'Hot Cocoa and Milk Mixes','cocoa',98),(103,'Juice','juice',98),(104,'Mixers','mixers',98),(105,'Seitzer & Tonic','tonic',98),(106,'Smoothies','smoothies',98),(107,'Soft Drinks','softdrinks',98),(108,'Tea','tea',98),(109,'Sports & Energy Drinks','sports',98),(110,'Water','water',98),(111,'Bagels','bagels',95),(112,'Bakery Bread','bakerybread',95),(113,'Desserts & Pastries','desserts',95),(114,'English Muffins','english',95),(115,'Muffins / Coffee Cakes','muffins',95),(116,'Pitas, Tortillas, Taco Shells & Wraps','pita',95),(117,'Pizza Crusts','pizzacrusts',95),(118,'Light Beer','lightbeer',97);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `enabled` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'cam','cam','camroe@gmail.com','8018091844','yes');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-17 12:11:55
