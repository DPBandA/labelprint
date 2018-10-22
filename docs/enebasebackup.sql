-- MySQL dump 10.13  Distrib 5.7.23, for Linux (i686)
--
-- Host: localhost    Database: enerbase
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

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
-- Table structure for table `energyconsumptionandefficiency`
--

DROP TABLE IF EXISTS `energyconsumptionandefficiency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `energyconsumptionandefficiency` (
  `ID` bigint(20) NOT NULL,
  `AVCUFTCOEFFICIENT` double DEFAULT NULL,
  `AVLCOEFFICIENT` double DEFAULT NULL,
  `CONSUMPTIONCONSTANT` double DEFAULT NULL,
  `ITEMNO` varchar(255) DEFAULT NULL,
  `MINCEER` double DEFAULT NULL,
  `MINMEER` double DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PRODUCTCLASS` varchar(255) DEFAULT NULL,
  `PRODUCTRATEDFREQUENCY` varchar(255) DEFAULT NULL,
  `PRODUCTTYPE` varchar(255) DEFAULT NULL,
  `PRODUCTTYPEDETAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energyconsumptionandefficiency`
--

LOCK TABLES `energyconsumptionandefficiency` WRITE;
/*!40000 ALTER TABLE `energyconsumptionandefficiency` DISABLE KEYS */;
INSERT INTO `energyconsumptionandefficiency` VALUES (1,0,0,0,'',0,0,'Fridge - 2323 232ui32i i23u2i 3i2ui3u i2u3i2i3u2iui32iu3i2u iu2iu3i 2ui32i3i 2323 yyytt tioeori eorieoiro roeior oerioeir oeoreo iroeiroeiorioe iroeiorieo roe ??','n/a','','Refrigerator','frigeeee ewewewe '),(2,0,0,0,'',0,0,'Freezer -  kjkwjkew ejkejwke wkejkwje kwjkejwkekw kjk kkjk kjk kjk kwewe ','n/a','','Freezer','frigeee 2'),(3,0,0,0,'',0,0,'Aircon -  p09909ew0e9w0e90w9e0w 090ew9e0 9w0e90w9e0 w90e9w0e90w 0ewe','4000 - 5999','','Room Air-conditioner','frieeeii  3'),(4,NULL,NULL,NULL,'5A',NULL,NULL,'AC - kjkjek wjekjwkejk wkejwkej kwjekjwkejkwjek jwkejkwkejwkejwe','6000 - 9000',NULL,'Room Air-conditioner','ac oioeioreoroeorererrere');
/*!40000 ALTER TABLE `energyconsumptionandefficiency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energylabel`
--

DROP TABLE IF EXISTS `energylabel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `energylabel` (
  `ID` bigint(20) NOT NULL,
  `ANNUALCONSUMPTION` varchar(255) DEFAULT NULL,
  `BRAND` varchar(255) DEFAULT NULL,
  `CAPACITY` varchar(255) DEFAULT NULL,
  `COSTPERKWH` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `DEFROST` varchar(255) DEFAULT NULL,
  `DISTRIBUTOR` varchar(255) DEFAULT NULL,
  `JOBNUMBER` varchar(255) DEFAULT NULL,
  `LABELNAME` varchar(255) DEFAULT NULL,
  `MANUFACTURER` varchar(255) DEFAULT NULL,
  `MODEL` varchar(255) DEFAULT NULL,
  `OPERATINGCOST` varchar(255) DEFAULT NULL,
  `STANDARD` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `VALIDITY` varchar(255) DEFAULT NULL,
  `COOLINGCAPACITY` varchar(255) DEFAULT NULL,
  `HEATINGCAPACITY` varchar(255) DEFAULT NULL,
  `RATEDFREQUENCY` varchar(10) DEFAULT NULL,
  `RATEDVOLTAGE` varchar(10) DEFAULT NULL,
  `AEER` varchar(255) DEFAULT NULL,
  `ACOP` varchar(255) DEFAULT NULL,
  `ENERGYCONSUMPTIONANDEFFICIENCY_ID` bigint(20) DEFAULT NULL,
  `FRESHFOODCOMPARTMENTVOL` varchar(255) DEFAULT NULL,
  `FREEZERCOMPARTMENTVOL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `energylabel_ENERGYCONSUMPTIONANDEFFICIENCY_ID_idx` (`ENERGYCONSUMPTIONANDEFFICIENCY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energylabel`
--

LOCK TABLES `energylabel` WRITE;
/*!40000 ALTER TABLE `energylabel` DISABLE KEYS */;
INSERT INTO `energylabel` VALUES (1,'500','That brand','0.530','45','USA','Automatic','Unicomer Ja. Ltd.','21/2018/0001','Test label 1','D P Bennett & Assoc.','Mod:ABCDEFG','100,000','JS 178','Freezer','2019','3.5','2.5','50/60','115','0.0','0.0',1,'0.0 L','0.0 L'),(51,'wr','wr','rwrwr','wrwrr','rw','rwr','wr','rwrwrrw','rwrwr','wrwr','what a model??','rwrwr','178','Basic Refrigerator','2018',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,'tet','ewew','ewe','etet','rrte','we','wew','wewew','we','ew','e','tete','178','Basic Refrigerator','2018',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(151,'last','last','BigInteger','last','last???','Manual','BigInteger','','The last datab','last','last????','459866','178','Basic Refrigerator','2018',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(201,'-0-0-0','BRND','0.896','41.23','United States of America','Manual','Unicomer Jamaica Ltd.','21/2018','Label for Unicomer Jamaica Limited','DPB&A','11234er','34,500','JS 178','Basic Refrigerator','2018',NULL,NULL,'60','120',NULL,NULL,1,NULL,NULL),(251,'65','565','','565','5656','','','','trrrr','56565655','65','888865pl','178','Refrigerator','2018',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(301,'Seen','Seen','Seen','v','Seen','Automatic','Seen','Seen','Seen','Seen','Seen','Seen','178','Refrigerator','2018',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(351,'78','','78','','','Automatic','','jk','6','','new  mod','23','178','Refrigerator','2018','778','7','','',NULL,NULL,NULL,NULL,NULL),(401,'0.0','gfgff','0.0','0.0','gvh76ttrgtt','Automatic','89fr55t554','53dtff','9666','677hgf','f865434','0.0','178','Refrigerator','2018','0.0','0.0','','','0.0','0.0',NULL,NULL,NULL),(451,'7888888.99999','','5','','','Automatic','','','Valid label name','','koiu','90.9','178','Refrigerator','2018','7','8','','','','',NULL,NULL,NULL),(501,'0.0','io','0.0','0.0','io','Automatic','io','ioio','oioi','oio','io','0.0','178','Refrigerator','2018','0.0','0.0','','','0.0','0.0',NULL,NULL,NULL),(551,'0.0','','0.0','0.0','','Automatic','','','Test again','','Test again 2','100,000','JS 178','Refrigerator','2018','0.0','0.0','50','115','8,0.0','0.0',1,NULL,NULL);
/*!40000 ALTER TABLE `energylabel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'enerbase'
--

--
-- Dumping routines for database 'enerbase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-20  9:52:55
