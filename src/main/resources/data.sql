-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: tecsoexamendb
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `balance` decimal(10,2) NOT NULL,
  `idholder` int(11) NOT NULL,
  `creation_timestamp` timestamp NULL DEFAULT NULL,
  `modification_timestamp` timestamp NULL DEFAULT NULL,
  `version_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_UNIQUE` (`number`),
  KEY `fk_titular_cuenta_idx` (`idholder`),
  CONSTRAINT `account_fk1` FOREIGN KEY (`idholder`) REFERENCES `accountholder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'101010','USD',2900.00,7,NULL,NULL,2),(3,'787888','EUR',3000.00,9,NULL,NULL,0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountholder`
--

DROP TABLE IF EXISTS `accountholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accountholder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rut` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `creation_timestamp` timestamp NULL DEFAULT NULL,
  `modification_timestamp` timestamp NULL DEFAULT NULL,
  `version_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rut_UNIQUE` (`rut`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountholder`
--

LOCK TABLES `accountholder` WRITE;
/*!40000 ALTER TABLE `accountholder` DISABLE KEYS */;
INSERT INTO `accountholder` VALUES (7,'10514458853','L',NULL,NULL,3),(9,'1075554444444','P',NULL,NULL,2);
/*!40000 ALTER TABLE `accountholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legalperson`
--

DROP TABLE IF EXISTS `legalperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `legalperson` (
  `id` int(11) NOT NULL,
  `businessname` varchar(100) NOT NULL,
  `founded` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `legalperson_fk1` FOREIGN KEY (`id`) REFERENCES `accountholder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legalperson`
--

LOCK TABLES `legalperson` WRITE;
/*!40000 ALTER TABLE `legalperson` DISABLE KEYS */;
INSERT INTO `legalperson` VALUES (7,'Dario Jose',1995);
/*!40000 ALTER TABLE `legalperson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movement`
--

DROP TABLE IF EXISTS `movement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idaccount` int(11) NOT NULL,
  `eventdate` timestamp NOT NULL,
  `typemov` varchar(2) NOT NULL,
  `description` varchar(200) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `creation_timestamp` timestamp NULL DEFAULT NULL,
  `modification_timestamp` timestamp NULL DEFAULT NULL,
  `version_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `movement_fk1_idx` (`idaccount`),
  CONSTRAINT `movement_fk1` FOREIGN KEY (`idaccount`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movement`
--

LOCK TABLES `movement` WRITE;
/*!40000 ALTER TABLE `movement` DISABLE KEYS */;
INSERT INTO `movement` VALUES (1,1,'2019-07-03 03:57:01','D','sdfsdfdsfsd',100.00,NULL,NULL,0);
/*!40000 ALTER TABLE `movement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physicalperson`
--

DROP TABLE IF EXISTS `physicalperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `physicalperson` (
  `id` int(11) NOT NULL,
  `firstname` varchar(80) NOT NULL,
  `lastname` varchar(250) NOT NULL,
  `identification` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `physicalperson_fk1` FOREIGN KEY (`id`) REFERENCES `accountholder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physicalperson`
--

LOCK TABLES `physicalperson` WRITE;
/*!40000 ALTER TABLE `physicalperson` DISABLE KEYS */;
INSERT INTO `physicalperson` VALUES (9,'Juan','Polo','10755578');
/*!40000 ALTER TABLE `physicalperson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `creation_timestamp` timestamp NULL DEFAULT NULL,
  `modification_timestamp` timestamp NULL DEFAULT NULL,
  `version_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'dario','$2a$10$XwoZUwMHGl5RTRuNUOKYq.2RsMjjnbr/B8k2jM4MXm2gsAgS7IKje',NULL,NULL,0),(2,'juan','$2a$10$4p/Glue7ag7oV7B2fBpzsuclyqYQlwKsKhW9iBWUkOM2lHd03xDS6',NULL,NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tecsoexamendb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03  8:56:42
