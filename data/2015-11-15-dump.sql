CREATE DATABASE  IF NOT EXISTS `gophergroceries` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gophergroceries`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: gophergroceries
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
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
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Pankaj','CEO'),(2,'David','Manager');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequence` VALUES (120),(120);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `creationdate` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `items` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2015-10-31','camroe@gmail.com','There be items here'),(2,'2015-10-31','camroe@gmail.com','There be items here'),(3,'2015-10-31','camroe@gmail.com','There be items here'),(4,'2015-10-31','camroe@gmail.com','There be items here'),(6,'2015-10-31','camroe@gmail.com','There be items here'),(8,'2015-10-31','camroe@gmail.com','There be items here'),(10,'2015-10-31','camroe@gmail.com','There be items here'),(12,'2015-10-31','camroe@gmail.com','There be items here'),(14,'2015-10-31','camroe@gmail.com','There be items here'),(17,'2015-10-31','camroe@gmail.com','There be items here'),(28,'2015-10-31','camroe@gmail.com','There be items here'),(31,'2015-10-31','camroe@gmail.com','There be items here'),(34,'2015-10-31','camroe@gmail.com','There be items here'),(37,'2015-11-01','camroe@gmail.com','There be items here'),(40,'2015-11-01','camroe@gmail.com','There be items here'),(43,'2015-11-01','camroe@gmail.com','There be items here'),(46,'2015-11-01','camroe@gmail.com','There be items here'),(49,'2015-11-01','camroe@gmail.com','There be items here'),(52,'2015-11-01','camroe@gmail.com','There be items here'),(55,'2015-11-01','camroe@gmail.com','There be items here'),(58,'2015-11-01','camroe@gmail.com','There be items here');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `PersonID` int(11) DEFAULT NULL,
  `Lastname` varchar(255) DEFAULT NULL,
  `Firstname` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `POST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) DEFAULT NULL,
  `POST_DATE` date DEFAULT NULL,
  PRIMARY KEY (`POST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'First Post','2015-10-29'),(2,'First Post','2015-10-29'),(3,'First Post','2015-10-29');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(20) NOT NULL COMMENT 'Gopher Groceries SKU number',
  `name` varchar(45) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `price` decimal(5,2) NOT NULL DEFAULT '0.00',
  `inventory` int(11) DEFAULT NULL,
  `popular` varchar(1) DEFAULT 'N' COMMENT 'y or Y = YES\nn or N or null = no\n',
  `magefile` varchar(45) DEFAULT NULL COMMENT 'file location of the product image relative to context root',
  `category` varchar(45) NOT NULL DEFAULT 'none' COMMENT 'The Category this product is in. ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='This is the main product table for gophergroceries';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'SK01','White Bread','Enriched White Bread',1.00,NULL,'Y','resources/images/errorguy.jpg','bread'),(2,'SK02','Brown Bread','Brown Wonder Bread',1.00,NULL,'Y','resources/images/brownbread.jpg','bread'),(3,'SK03','White Buns - 6 cnt','White Dinner Buns',1.00,NULL,'Y','resources/images/errorguy.jpg','buns'),(4,'SK04','Brown Dinner Buns - CNT 6','Brown Dinner Rolls - 6 pack',2.00,NULL,'N','resources/images/errorguy.jpg','buns'),(5,'SK05','White Buns - 12 CNT','White Dinner Buns. This is a really long description just to see how it works on a single really long line. &#13; This is the 2nd line I think.',3.00,NULL,'Y','resources/images/errorguy.jpg','buns'),(6,'SK06','Coors Light','MMMMmmmmm Beeeeerrrr. ',10.00,NULL,'Y','resources/images/errorguy.jpg','lightbeer');
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
  `subcategoryentity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjw6gb7ieg805xnufowqp951u8` (`category_FK`),
  KEY `FKefmotj7usqlyjrg28tja4r0lc` (`subcategoryentity_id`),
  CONSTRAINT `FKefmotj7usqlyjrg28tja4r0lc` FOREIGN KEY (`subcategoryentity_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKjw6gb7ieg805xnufowqp951u8` FOREIGN KEY (`category_FK`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (96,'Baking Mixes','bakingmixes',96,NULL),(97,'Baking Morsels, Bars & Cocoa','bakingmorsels',96,NULL),(98,'Dough','dough',96,NULL),(99,'Bread','bread',95,NULL),(100,'Buns','buns',95,NULL),(101,'Coffee & Filters','coffee',98,NULL),(102,'Hot Cocoa and Milk Mixes','cocoa',98,NULL),(103,'Juice','juice',98,NULL),(104,'Mixers','mixers',98,NULL),(105,'Seitzer & Tonic','tonic',98,NULL),(106,'Smoothies','smoothies',98,NULL),(107,'Soft Drinks','softdrinks',98,NULL),(108,'Tea','tea',98,NULL),(109,'Sports & Energy Drinks','sports',98,NULL),(110,'Water','water',98,NULL),(111,'Bagels','bagels',95,NULL),(112,'Bakery Bread','bakerybread',95,NULL),(113,'Desserts & Pastries','desserts',95,NULL),(114,'English Muffins','english',95,NULL),(115,'Muffins / Coffee Cakes','muffins',95,NULL),(116,'Pitas, Tortillas, Taco Shells & Wraps','pita',95,NULL),(117,'Pizza Crusts','pizzacrusts',95,NULL),(118,'Light Beer','lightbeer',97,NULL);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_role` varchar(45) DEFAULT NULL,
  `enabled` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (1,'roecam@gmail.com','Cam Roe','roeman','ROLE_USER','yes'),(2,'camroe@gmail.com','Cameron Roe','roeman','ROLE_ADMIN','yes');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-14 18:02:27
