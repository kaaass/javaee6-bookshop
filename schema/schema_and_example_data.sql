-- MariaDB dump 10.19  Distrib 10.5.10-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: sample
-- ------------------------------------------------------
-- Server version	5.7.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category_name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK302BCFE305FE8B8` (`parent_id`),
  CONSTRAINT `FK302BCFE305FE8B8` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('2c9e80837a12bdcf017a13abaeff001b','2021-06-16 07:14:16','前后端技术','ff8081816e6aae62016e6ab98df10002'),('2c9e80837a12bdcf017a13b59f74001e','2021-06-16 07:25:07','操作系统','ff8081816e6aae62016e6ab98df10002'),('2c9e80837a12bdcf017a13be199b0025','2021-06-16 07:34:23','轻小说',NULL),('426f2929a642420a8ceb57f6d0d0c795','2021-06-16 05:09:35','编程语言','ff8081816e6aae62016e6ab98df10002'),('e95ce24a02694ebcaf997672bb361997','2021-06-16 05:05:28','网络安全','ff8081816e6aae62016e6ab98df10002'),('ff8081816e6aae62016e6ab98df10002','2019-11-15 00:22:55','计算机科学类',NULL),('ff8081816e6aae62016e6abbefde0005','2019-11-15 00:25:31','哲学类',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `id` varchar(255) NOT NULL,
  `order_item_count` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `price` float DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2D110D64AD27101D` (`order_id`),
  KEY `FK2D110D6471C53BBD` (`product_id`),
  KEY `FK2D110D6412494A86` (`uid`),
  CONSTRAINT `FK2D110D64AD27101D` FOREIGN KEY (`order_id`) REFERENCES `table_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `storage_id` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKED8DCCEF3584FB15` (`storage_id`),
  KEY `FKED8DCCEF10A1E3E4` (`category_id`),
  KEY `FKED8DCCEF181305CC` (`thumbnail`),
  CONSTRAINT `FKED8DCCEF10A1E3E4` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKED8DCCEF3584FB15` FOREIGN KEY (`storage_id`) REFERENCES `product_storage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('2c9e80837a12bdcf017a133619c70007','2021-06-16 05:05:50','2021-06-20 16:20:46','IDA Pro权威指南(第2版)',89,'e95ce24a02694ebcaf997672bb361997','2c9e80837a12bdcf017a133619c70008','https://img.alicdn.com/imgextra/i1/2145487409/O1CN01A0U3Ur24bN8ZJtTcN_!!2145487409-0-picasso.jpg_430x430q90.jpg','石华耀；段桂菊','9787115273680','2015-03-01 00:00:00'),('2c9e80837a12bdcf017a13a37da6000f','2021-06-16 07:05:19','2021-06-16 07:05:19','CPU自制入门',99,'2c9e80837a12bdcf017a13b59f74001e','2c9e80837a12bdcf017a13a37da60010','https://img.alicdn.com/imgextra/i3/2145487409/O1CN01CSIBhR24bMzhEZIJR_!!0-item_pic.jpg_430x430q90.jpg','水头一寿','9787115338181','2020-04-01 00:00:00'),('2c9e80837a12bdcf017a13ac062c001c','2021-06-16 07:14:38','2021-06-16 07:14:38','深入浅出Vue.js',79,'2c9e80837a12bdcf017a13abaeff001b','2c9e80837a12bdcf017a13ac062c001d','https://img.alicdn.com/imgextra/i1/1049653664/O1CN01x8YGYy1cw9rysg6sW_!!1049653664-0-picasso.jpg_430x430q90.jpg','刘博文','9787115509055','2019-03-01 00:00:00'),('2c9e80837a12bdcf017a13b5c4aa001f','2021-06-16 07:25:17','2021-06-16 07:25:17','一个64位操作系统的设计与实现',139,'2c9e80837a12bdcf017a13b59f74001e','2c9e80837a12bdcf017a13b5c4aa0020','https://img.alicdn.com/imgextra/i1/1049653664/TB12RClpfuSBuNkHFqDXXXfhVXa_!!0-item_pic.jpg_430x430q90.jpg','田宇','9787115475251','2018-05-01 00:00:00'),('2c9e80837a12bdcf017a13ba6ca00021','2021-06-16 07:30:22','2021-06-16 07:30:22','新一代垃圾回收器ZGC设计与实现',89,'426f2929a642420a8ceb57f6d0d0c795','2c9e80837a12bdcf017a13ba6ca00022','	https://img.alicdn.com/imgextra/i1/1599634638/O1CN01zKVJ4C1k8FVV664ES_!!1599634638.jpg_430x430q90.jpg','彭成寒','9787111633655','2019-09-01 00:00:00'),('2c9e80837a12bdcf017a13bca2490023','2021-06-16 07:32:47','2021-06-16 07:32:47','白帽子讲Web安全',69,'e95ce24a02694ebcaf997672bb361997','2c9e80837a12bdcf017a13bca2490024','https://img.alicdn.com/imgextra/i1/2373133145/TB2wSyQJQKWBuNjy1zjXXcOypXa_!!2373133145.jpg_430x430q90.jpg','吴翰清','9787121234101','2012-03-01 00:00:00'),('2c9e80837a12bdcf017a13bf6d2d0026','2021-06-16 07:35:50','2021-06-20 15:08:53','刀剑神域17.18.19小说套装3册',109,'2c9e80837a12bdcf017a13be199b0025','2c9e80837a12bdcf017a13bf6d2d0027','https://img.alicdn.com/imgextra/i4/612746299/O1CN01DpqEHk1wOzVANzeqy_!!0-item_pic.jpg_430x430q90.jpg','川原砾','9787534057267','2016-10-01 00:00:00'),('2c9e80837a12bdcf017a13c354160028','2021-06-16 07:40:06','2021-06-20 15:10:12','夏目友人帐',32.8,'2c9e80837a12bdcf017a13be199b0025','2c9e80837a12bdcf017a13c354160029','https://img.alicdn.com/imgextra/i3/859515618/O1CN01axjmNk1rN5cHJnSkK_!!859515618.jpg_430x430q90.jpg','村井贞之','9787550017771','2021-06-10 00:00:00'),('ff8081816e6aae62016e6aba99fa0003','2019-11-15 00:24:04','2021-06-16 03:12:40','The Little Schemer：递归与函数式的奥妙',65,'426f2929a642420a8ceb57f6d0d0c795','ff8081816e6aae62016e6aba99fa0004','	https://img.alicdn.com/imgextra/i4/1624826013/TB1LbxxgfNZWeJjSZFpXXXjBFXa_!!0-item_pic.jpg_430x430q90.jpg','丹尼尔 P. 弗里德曼；马提亚 费雷森','9787121317255','2017-07-01 00:00:00'),('ff8081816e6aae62016e6abce23c0007','2019-11-15 00:26:33','2021-06-16 03:12:40','存在与虚无 修订译本（精装）',65.9,'ff8081816e6aae62016e6abbefde0005','ff8081816e6aae62016e6abce23c0008','https://img.alicdn.com/imgextra/i1/1049653664/O1CN01wTkt421cw9g0tu22F_!!2-item_pic.png_430x430q90.jpg','萨特','9787108050984','2014-09-01 00:00:00');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_storage`
--

DROP TABLE IF EXISTS `product_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_storage` (
  `id` varchar(255) NOT NULL,
  `rest` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_storage`
--

LOCK TABLES `product_storage` WRITE;
/*!40000 ALTER TABLE `product_storage` DISABLE KEYS */;
INSERT INTO `product_storage` VALUES ('2c9e80837a12bdcf017a133619c70008',96),('2c9e80837a12bdcf017a13a37da60010',48),('2c9e80837a12bdcf017a13ac062c001d',200),('2c9e80837a12bdcf017a13b5c4aa0020',60),('2c9e80837a12bdcf017a13ba6ca00022',140),('2c9e80837a12bdcf017a13bca2490024',40),('2c9e80837a12bdcf017a13bf6d2d0027',86),('2c9e80837a12bdcf017a13c354160029',191),('ff8081816e6aae62016e6aba99fa0004',92),('ff8081816e6aae62016e6abce23c0008',129);
/*!40000 ALTER TABLE `product_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_order`
--

DROP TABLE IF EXISTS `table_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_order` (
  `id` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deliver_code` varchar(255) DEFAULT NULL,
  `deliver_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `price` float DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `refund_time` datetime DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `request_id` (`request_id`),
  KEY `FK23E9939D585B3FD2` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_order`
--

LOCK TABLES `table_order` WRITE;
/*!40000 ALTER TABLE `table_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `table_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_address` (
  `id` varchar(255) NOT NULL,
  `area` text,
  `default_address` tinyint(1) DEFAULT NULL,
  `detail_address` text,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mail_code` text,
  `name` text,
  `phone` text,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16873380FB2A3B8D` (`uid`),
  CONSTRAINT `FK16873380FB2A3B8D` FOREIGN KEY (`uid`) REFERENCES `user_auth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_auth` (
  `id` varchar(255) NOT NULL,
  `auth_token` varchar(255) DEFAULT NULL,
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-21  0:28:53
