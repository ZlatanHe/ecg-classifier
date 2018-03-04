-- MySQL dump 10.13  Distrib 5.7.21, for osx10.13 (x86_64)
--
-- Host: localhost    Database: ecg
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Current Database: `ecg`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ecg` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ecg`;

--
-- Table structure for table `heartbeat_log`
--

DROP TABLE IF EXISTS `heartbeat_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heartbeat_log` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(64) NOT NULL,
  `patient_name` varchar(32) NOT NULL,
  `heartbeat_type` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_patient_id` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heartbeat_log`
--

LOCK TABLES `heartbeat_log` WRITE;
/*!40000 ALTER TABLE `heartbeat_log` DISABLE KEYS */;
INSERT INTO `heartbeat_log` VALUES (1,'550bb16ba58e46759bc5df4f78409fec','Austin Rivers','FUSION_BEAT','2018-03-03 23:01:01'),(2,'550bb16ba58e46759bc5df4f78409fec','Austin Rivers','SUPRA_VENTRICULAR_ECTOPIC_BEAT','2018-03-04 18:48:47'),(3,'550bb16ba58e46759bc5df4f78409fec','Austin Rivers','SUPRA_VENTRICULAR_ECTOPIC_BEAT','2018-03-04 18:50:31');
/*!40000 ALTER TABLE `heartbeat_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(64) NOT NULL,
  `patient_name` varchar(32) NOT NULL,
  `gender` char(2) NOT NULL COMMENT '0-male 1-female',
  `age` int(3) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_patient_id` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'550bb16ba58e46759bc5df4f78409fec','Austin Rivers','0',39,'2018-03-01 00:00:00');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-04 19:23:04
