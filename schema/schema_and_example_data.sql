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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` varchar(255) NOT NULL,
  `comment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDC17DDF4FB2A3B8D` (`uid`),
  CONSTRAINT `FKDC17DDF4FB2A3B8D` FOREIGN KEY (`uid`) REFERENCES `user_auth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES ('2c9c80837a0e7bd5017a0f1c8a760003','2021-06-15 09:59:26','真是一本好书！','202106150001','ff8081816e6aae62016e6aba99fa0003',5,'2c9c80837a0e7bd5017a0f05bfac0000'),('2c9f80837a14a1d4017a14cb9a9d0009','2021-06-16 12:28:45','不错的书～','202106160005','2c9e80837a12bdcf017a133619c70007',5,'2c9f80837a14a1d4017a14c6010c0006');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `id` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uploader_uid` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES ('00000000000000000000000000000000','image','2021-06-17 10:53:57',NULL,'/web/img/avatar.png'),('2c9e80837a12bdcf017a132f29380004','image','2021-06-16 04:58:15','2c9c80837a0e7bd5017a0f05bfac0000','https://img.alicdn.com/imgextra/i1/2145487409/O1CN01A0U3Ur24bN8ZJtTcN_!!2145487409-0-picasso.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a0a64c000d','image','2021-06-16 07:02:13','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i3/2145487409/O1CN01CSIBhR24bMzhEZIJR_!!0-item_pic.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a50e960011','image','2021-06-16 07:07:02','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i1/1049653664/O1CN01x8YGYy1cw9rysg6sW_!!1049653664-0-picasso.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a5753d0012','image','2021-06-16 07:07:28','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i1/1049653664/TB12RClpfuSBuNkHFqDXXXfhVXa_!!0-item_pic.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a5ccc80013','image','2021-06-16 07:07:50','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i1/1599634638/O1CN01zKVJ4C1k8FVV664ES_!!1599634638.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a685800014','image','2021-06-16 07:08:38','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i1/2373133145/TB2wSyQJQKWBuNjy1zjXXcOypXa_!!2373133145.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a6efb40015','image','2021-06-16 07:09:05','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i4/612746299/O1CN01DpqEHk1wOzVANzeqy_!!0-item_pic.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a72b0c0016','image','2021-06-16 07:09:20','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i3/612746299/O1CN015lB2BY1wOzQcwSPJW_!!612746299-0-lubanu-s.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a764670017','image','2021-06-16 07:09:35','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i1/612746299/O1CN01lqPabK1wOzQVZ0yTw_!!612746299-0-lubanu-s.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a7ac740018','image','2021-06-16 07:09:53','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i4/612746299/O1CN01F0DNiv1wOzQYY57qq_!!612746299-0-lubanu-s.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a831060019','image','2021-06-16 07:10:27','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i3/859515618/O1CN01axjmNk1rN5cHJnSkK_!!859515618.jpg_430x430q90.jpg'),('2c9e80837a12bdcf017a13a8774a001a','image','2021-06-16 07:10:45','2c9b80837a0e4d60017a0e4dd40b0000','https://img.alicdn.com/imgextra/i4/859515618/O1CN01hYzsV21rN5hnFXUD7_!!859515618-0-lubanu-s.jpg_430x430q90.jpg'),('ff8081816dd40e03016dd410408e0000','image','2019-10-16 18:14:57','2c9b80837a0e4d60017a0e4dd4100001','/web/upload/admin-300x300.jpg'),('ff8081816df87780016df877a9260000','image','2019-10-23 19:54:14','2c9b80837a0e4d60017a0e4dd4100001','/web/upload/c496da1abdb74036bd395b44d522b4d8.jpg'),('ff8081816df8789a016df87963b20000','image','2019-10-23 19:56:08','2c9b80837a0e4d60017a0e4dd4100001','/web/upload/8322d4d863e74fecb559b416ab33beb2.jpg'),('ff8081816df88375016df883d81f0000','image','2019-10-23 20:07:33','2c9b80837a0e4d60017a0e4dd4100001','/web/upload/a68e4c0b17ea4e3ba0c69968250cece7.jpg'),('ff8081816e6aae62016e6ab87b580000','image','2019-11-15 00:21:45','2c9b80837a0e4d60017a0e4dd4100001','https://img.alicdn.com/imgextra/i4/1624826013/TB1LbxxgfNZWeJjSZFpXXXjBFXa_!!0-item_pic.jpg_430x430q90.jpg'),('ff8081816e6aae62016e6abcbd140006','image','2019-11-15 00:26:24','2c9b80837a0e4d60017a0e4dd4100001','https://img.alicdn.com/imgextra/i1/1049653664/O1CN01wTkt421cw9g0tu22F_!!2-item_pic.png_430x430q90.jpg');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
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
  `uid` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2D110D6487A01476` (`order_id`),
  KEY `FK2D110D64920E8DD6` (`product_id`),
  KEY `FK2D110D64FB2A3B8D` (`uid`),
  CONSTRAINT `FK2D110D6487A01476` FOREIGN KEY (`order_id`) REFERENCES `table_order` (`id`),
  CONSTRAINT `FK2D110D64920E8DD6` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK2D110D64FB2A3B8D` FOREIGN KEY (`uid`) REFERENCES `user_auth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES ('2c9880837a194e9b017a19af2c1c0009',10,'2021-06-17 11:15:48',872,'2c9c80837a0e7bd5017a0f05bfac0000','202106170001','2c9e80837a12bdcf017a13bf6d2d0026'),('2c9b80837a1a780d017a1aa2635e0000',1,'2021-06-17 15:41:27',109,'2c9c80837a0e7bd5017a0f05bfac0000','202106170002','2c9e80837a12bdcf017a13bf6d2d0026'),('2c9b80837a1a780d017a1aa9799d0001',4,'2021-06-17 15:49:12',436,'2c9c80837a0e7bd5017a0f05bfac0000','202106170003','2c9e80837a12bdcf017a13bf6d2d0026'),('2c9b80837a1a780d017a1aabece00002',1,'2021-06-17 15:51:52',109,'2c9c80837a0e7bd5017a0f05bfac0000','202106170005','2c9e80837a12bdcf017a13bf6d2d0026'),('2c9c80837a0e7bd5017a0f1727290002',1,'2021-06-15 09:53:33',65,'2c9c80837a0e7bd5017a0f05bfac0000','202106150001','ff8081816e6aae62016e6aba99fa0003'),('2c9f80837a14a1d4017a14c9cf380008',2,'2021-06-16 12:26:48',178,'2c9f80837a14a1d4017a14c6010c0006','202106160005','2c9e80837a12bdcf017a133619c70007');
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
  `buy_limit` int(11) DEFAULT '-1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `index_order` int(11) DEFAULT '-1',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mail_price` float DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `start_sell_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category_id` varchar(255) DEFAULT NULL,
  `storage_id` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `thumbnail` (`thumbnail`),
  KEY `FKED8DCCEF3584FB15` (`storage_id`),
  KEY `FKED8DCCEF10A1E3E4` (`category_id`),
  KEY `FKED8DCCEF181305CC` (`thumbnail`),
  CONSTRAINT `FKED8DCCEF10A1E3E4` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKED8DCCEF181305CC` FOREIGN KEY (`thumbnail`) REFERENCES `media` (`id`),
  CONSTRAINT `FKED8DCCEF3584FB15` FOREIGN KEY (`storage_id`) REFERENCES `product_storage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('2c9e80837a12bdcf017a133619c70007',-1,'2021-06-16 05:05:50',3,'2021-06-16 12:26:48',0,'IDA Pro权威指南(第2版)',89,'2021-06-10 00:00:00','e95ce24a02694ebcaf997672bb361997','2c9e80837a12bdcf017a133619c70008','2c9e80837a12bdcf017a132f29380004','石华耀；段桂菊','9787115273680','2015-03-01 00:00:00'),('2c9e80837a12bdcf017a13a37da6000f',5,'2021-06-16 07:05:19',4,'2021-06-16 07:05:19',5,'CPU自制入门',99,'2021-06-10 00:00:00','2c9e80837a12bdcf017a13b59f74001e','2c9e80837a12bdcf017a13a37da60010','2c9e80837a12bdcf017a13a0a64c000d','水头一寿','9787115338181','2020-04-01 00:00:00'),('2c9e80837a12bdcf017a13ac062c001c',-1,'2021-06-16 07:14:38',-1,'2021-06-16 07:14:38',0,'深入浅出Vue.js',79,'2021-06-10 00:00:00','2c9e80837a12bdcf017a13abaeff001b','2c9e80837a12bdcf017a13ac062c001d','2c9e80837a12bdcf017a13a50e960011','刘博文','9787115509055','2019-03-01 00:00:00'),('2c9e80837a12bdcf017a13b5c4aa001f',-1,'2021-06-16 07:25:17',-1,'2021-06-16 07:25:17',0,'一个64位操作系统的设计与实现',139,'2021-06-10 00:00:00','2c9e80837a12bdcf017a13b59f74001e','2c9e80837a12bdcf017a13b5c4aa0020','2c9e80837a12bdcf017a13a5753d0012','田宇','9787115475251','2018-05-01 00:00:00'),('2c9e80837a12bdcf017a13ba6ca00021',-1,'2021-06-16 07:30:22',-1,'2021-06-16 07:30:22',0,'新一代垃圾回收器ZGC设计与实现',89,'2021-06-10 00:00:00','426f2929a642420a8ceb57f6d0d0c795','2c9e80837a12bdcf017a13ba6ca00022','2c9e80837a12bdcf017a13a5ccc80013','彭成寒','9787111633655','2019-09-01 00:00:00'),('2c9e80837a12bdcf017a13bca2490023',10,'2021-06-16 07:32:47',-1,'2021-06-16 07:32:47',5,'白帽子讲Web安全',69,'2021-06-10 00:00:00','e95ce24a02694ebcaf997672bb361997','2c9e80837a12bdcf017a13bca2490024','2c9e80837a12bdcf017a13a685800014','吴翰清','9787121234101','2012-03-01 00:00:00'),('2c9e80837a12bdcf017a13bf6d2d0026',-1,'2021-06-16 07:35:50',0,'2021-06-17 15:51:52',10,'刀剑神域17.18.19小说套装3册',109,'2021-06-10 00:00:00','2c9e80837a12bdcf017a13be199b0025','2c9e80837a12bdcf017a13bf6d2d0027','2c9e80837a12bdcf017a13a6efb40015','川原砾','9787534057267','2016-10-01 00:00:00'),('2c9e80837a12bdcf017a13c354160028',-1,'2021-06-16 07:40:06',1,'2021-06-16 12:11:06',0,'夏目友人帐',32.8,'2021-06-30 00:00:00','2c9e80837a12bdcf017a13be199b0025','2c9e80837a12bdcf017a13c354160029','2c9e80837a12bdcf017a13a831060019','村井贞之','9787550017771','2021-06-10 00:00:00'),('ff8081816e6aae62016e6aba99fa0003',-1,'2019-11-15 00:24:04',1,'2021-06-16 03:12:40',5,'The Little Schemer：递归与函数式的奥妙',65,'2021-06-10 00:00:00','426f2929a642420a8ceb57f6d0d0c795','ff8081816e6aae62016e6aba99fa0004','ff8081816e6aae62016e6ab87b580000','丹尼尔 P. 弗里德曼；马提亚 费雷森','9787121317255','2017-07-01 00:00:00'),('ff8081816e6aae62016e6abce23c0007',-1,'2019-11-15 00:26:33',2,'2021-06-16 03:12:40',0,'存在与虚无 修订译本（精装）',65.9,'2021-06-10 00:00:00','ff8081816e6aae62016e6abbefde0005','ff8081816e6aae62016e6abce23c0008','ff8081816e6aae62016e6abcbd140006','萨特','9787108050984','2014-09-01 00:00:00');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_metadata`
--

DROP TABLE IF EXISTS `product_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_metadata` (
  `id` varchar(255) NOT NULL,
  `meta_key` varchar(255) DEFAULT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_id` varchar(255) DEFAULT NULL,
  `meta_value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_metadata`
--

LOCK TABLES `product_metadata` WRITE;
/*!40000 ALTER TABLE `product_metadata` DISABLE KEYS */;
INSERT INTO `product_metadata` VALUES ('2c9880837a194e9b017a19aeeec30008','discount','2021-06-17 11:15:32','2c9e80837a12bdcf017a13bf6d2d0026','0.8'),('2c9e80837a12bdcf017a142caca8002a','discount','2021-06-16 09:35:09','2c9e80837a12bdcf017a13c354160028','0.5'),('2c9e80837a12bdcf017a142e6213002b','images','2021-06-16 09:37:18','2c9e80837a12bdcf017a13c354160028','2c9e80837a12bdcf017a13a8774a001a'),('2c9e80837a12bdcf017a142fae75002c','images','2021-06-16 09:38:27','2c9e80837a12bdcf017a13bf6d2d0026','2c9e80837a12bdcf017a13a72b0c0016,2c9e80837a12bdcf017a13a764670017,2c9e80837a12bdcf017a13a7ac740018'),('2c9e80837a12bdcf017a1433fdd9002e','detail','2021-06-16 09:47:01','2c9e80837a12bdcf017a13bf6d2d0026','<img src=\"https://img.alicdn.com/imgextra/i4/612746299/O1CN01AfPVNS1wOzQdfgocY_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">\n<img src=\"https://img.alicdn.com/imgextra/i4/612746299/O1CN01QWZbTV1wOzQVYzpi4_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">\n<img src=\"https://img.alicdn.com/imgextra/i4/612746299/O1CN015UAsSq1wOzQWxVsTz_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">\n<img src=\"https://img.alicdn.com/imgextra/i1/612746299/O1CN011mayQC1wOzQVZ06Le_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">\n<img src=\"https://img.alicdn.com/imgextra/i3/612746299/O1CN018gp5TC1wOzQYY32tf_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">\n<img src=\"https://img.alicdn.com/imgextra/i4/612746299/O1CN01RWIMMr1wOzQabSy2F_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">\n<img src=\"https://img.alicdn.com/imgextra/i2/612746299/O1CN01LXBztN1wOzQQqH3d4_!!612746299.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\" width=\"100%\">'),('2c9e80837a12bdcf017a143b9bc5002f','detail','2021-06-16 09:56:20','2c9e80837a12bdcf017a13c354160028','<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 基本信息 </div> <div style=\"line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 5.0px;\"> <table border=\"0\" cellpadding=\"5\" cellspacing=\"10\" style=\"font-size: 12.0px;\">  <tbody><tr> <td width=\"82\"> <strong>商品名称：</strong> </td> <td width=\"277\"> 夏目友人帐 </td> <td> <strong>开本：</strong> </td> <td width=\"169\"> 32开 </td> </tr> <tr> <td> <strong>作者：</strong> </td> <td> 村井贞之 </td> <td> <strong>页数：</strong> </td> <td data-spm-anchor-id=\"a220o.1000855.0.i0.475b5673pkoAts\"> &nbsp; </td> </tr> <tr> <td> <strong>定价：</strong> </td> <td> 32.8 </td> <td> <strong>出版时间：</strong> </td> <td> <span style=\"color: #444444;font-family: tahoma   arial   宋体;font-size: 12.0px;line-height: 16.0px;white-space: nowrap;\">2016-08-01</span> </td> </tr> <tr> <td> <strong>ISBN号：</strong> </td> <td> <span style=\"color: #666666;font-family: tahoma   arial   宋体;font-size: 12.0px;line-height: normal;white-space: nowrap;\">9787550017771</span> </td> <td> <strong>印刷时间：</strong> </td> <td> <span style=\"color: #444444;font-family: tahoma   arial   宋体;font-size: 12.0px;line-height: 16.0px;white-space: nowrap;\">2016-08-01</span> </td> </tr> <tr> <td> <strong>出版社：</strong> </td> <td> 百花洲文艺出版社 </td> <td> <strong>版次印次：</strong> </td> <td> 1次1次 </td> </tr>  </tbody></table> </div> </div>\n<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 编辑** </div> <div style=\"padding-bottom: 15.0px;line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;padding-right: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 15.0px;\"> <p>★高人气动漫《夏目友人帐》初次小说化。夏目、猫老师等原班人马崭新回归！那个温柔了岁月的人，在这里等你。一旦被人所爱，一旦爱过，就永远不会忘记。</p> <p>★正版授权中文简体小说*次放送，带来一场文字的治愈盛宴！</p> <p>★感动无数读者的温暖神作，无论身在何方，你总能被温柔对待。</p> <p>★超精美双封面惊喜加倍。</p> <p>★中二病译者二次元**呈现。</p> </div> </div>\n<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 内容** </div> <div style=\"padding-bottom: 15.0px;line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;padding-right: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 15.0px;\"> <p align=\"center\"><span>1.</span><span>古董店主人</span><span>遗留</span><span>的奇妙信件究竟有什么古怪</span><span>？</span></p> <p align=\"center\"><span>文字竟然长在了夏目的眼睛里</span><span>！</span></p> <p align=\"center\"><span>2.课堂上传来的笛音只有她和夏目能听见，却成为她年少的心结</span><span>，</span></p> <p align=\"center\"><span>直到那个吹着笛子的妖怪再次出现</span><span>……</span></p> <p align=\"center\"><span>3.人与鬼神的爱恋要怎样终场？</span></p> <p align=\"center\"><span>操纵</span><span>梦境的妖怪是否也能左右真实的情感呢</span><span>?</span></p> <p align=\"center\">&nbsp;</p> <p align=\"center\"><span>参与动画</span><span>《夏目友人帐</span><span>叁</span><span>·</span><span>肆</span><span>》</span><span>创作的</span><span>脚本师村井贞之</span><span>，所撰写的三篇原创故事。</span></p> <p align=\"center\"><span>众</span><span>所期待的**本《夏目友人帐》小说！</span></p> <p align=\"center\"><span>曾经治愈过无数人的漫画，现在用文字的形式重新呈现。</span></p> <p align=\"center\"><span>愿你们被这个世界温柔相待，愿你目之所及，心之所向满满都是爱。</span></p> <p align=\"center\"><span>谨以这本书送给我们曾经一起追番的那些美好时光。</span></p> <p align=\"center\"><span>那个温柔了岁月的人，带着猫老师，和他的伙伴们，一起回来啦！</span><b></b></p> <p align=\"center\">&nbsp;</p> </div> </div>\n<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 作者简介 </div> <div style=\"padding-bottom: 15.0px;line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;padding-right: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 15.0px;\"> <p>村井贞之，日本脚本家，出生于奈良县，1993年其作品在第六届富士电视台青年脚本家大赏中获奖，之后以SF(科幻)、幻想风格的作品为中心广泛的活跃着。</p> <p>作品展示：</p> <p>著作：《河童杀人》《幻影死神》《蒸汽男孩》。</p> <p>电影类：《迷之转校生》《幻影死神》《虫师》等。</p> <p>OV：《 厕所里的花子·消失少女的秘密》《厕所里的花子·恐怖校舍》。</p> <p>电视剧：《心中的S》《木耀日怪谈·怪奇俱乐部》《暗夜天使》《奥特曼戴拿》等。</p> <p>TV动画：《星际牛仔》《亚历山大战记》《铁臂阿童木》《无头骑士异闻录》《六翼天使之声》等。</p> <p>剧场版动画：《亚历山大战记剧场版》《PERFECT BLUE未麻之部屋剧场版动画》《千年**》。</p> <p>OVA：《异型特攻学园》。</p> <p>&nbsp;</p> </div> </div>\n<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 目录 </div> <div style=\"padding-bottom: 15.0px;line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;padding-right: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 15.0px;\"> <p>花灯堂奇谭 &nbsp; &nbsp; &nbsp; &nbsp;001</p> <p>妖之音 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;153</p> <p>妖的梦路 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;193</p> <p>后记 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;237</p> </div> </div>\n<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 媒体评论 </div> <div style=\"padding-bottom: 15.0px;line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;padding-right: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 15.0px;\"> <p>有幸长期连载的《夏目友人帐》出版小说了。承蒙村井先生写出这些让人心脏怦怦跳地沉浸其中、读完时却又留下一抹奇妙哀愁的故事，我高兴到在地上滚来滚去。我一边拜读过送来的小说，一边深切感受到有人以自己创作的漫画为本写出新故事，原来是这么快乐的感受。</p> <p>——绿川幸</p> <p>&nbsp;</p> <p>如果遇到不愉快的事情，我选择去看夏目友人帐，因为总有一种被温暖包围，让我无所畏惧这些不安和糟糕的世界。</p> <p>——百度贴吧读者</p> </div> </div>\n<div style=\"border-bottom: #ededed 1.0px solid;border-left: #ededed 1.0px solid;margin: 0.0px auto 20.0px;overflow: hidden;border-top: #ededed 1.0px solid;border-right: #ededed 1.0px solid;\"> <div style=\"line-height: 25.0px;margin: 0.0px auto;padding-left: 15.0px;height: 25.0px;color: #333333;font-size: 14.0px;font-weight: bold;\"> 在线试读部分章节 </div> <div style=\"padding-bottom: 15.0px;line-height: 20.0px;margin: 0.0px auto;padding-left: 15.0px;padding-right: 15.0px;color: #666666;font-size: 12.0px;border-top: #ededed 1.0px dashed;padding-top: 15.0px;\"> <p>&nbsp;从小时候起，我就不时会看到一些奇怪的东西，似乎那些别人看不见的，便是所谓的妖怪之流。</p> <p>比如在斑马线等红灯的时候，我忽然看向对面，会发现那里站着一个陌生的年轻女人，但她的脸是绿色的，头发长至脚踝，正用一双因充血而通红的眼睛瞪着我。又或者是在放学回家的路上，我和同学走着走着就会看见路边某处民宅的墙壁上有一张脸——那是一张比一般人大了整整三倍的男人的脸，正用它不带任何表情的眼睛目送着经过此地的小学生。</p> <p>过了很久我才明白，一直以来那些东西只有我能看见。明明已经绿灯了，我还胆怯地不敢过马路，叔叔就会在信号灯下牵着我的手并且训斥我；每当我指着什么都没有的墙壁，坚持说那里有一张很大的脸时，同学就认为我在说谎。三番五次地发生这种情况后，我才觉得不对劲。看来这个世界上，除了谁都看得见的普通的人和物，还存在着只有自己才能看见的“异形”这种东西。</p> <p>起初我以为，对别人来说同样存在着“只有自己才能看见的东西”，只是这种事他们从不对外人提及罢了。后来才知道，世界上——至少对那时的我而言还十分狭小的这个世界，那种奇怪的东西的确只有我一个人能看见。当我悟出这点的时候，又恐惧又震惊，并想极力隐瞒。</p> <p>然而，不管我怎样谨慎地隐瞒，看得见的东西就是看得见，而且它们中的大多数还会突然出现，我看得实在是太清楚了，有些家伙甚至和普通人类没什么两样。由于父母很早过世，我一直辗转于亲戚家，却常常引发各种矛盾：像是漫无目的地指着一个方向忽然大喊，在没人的房间里和谁嘀嘀咕咕地讲话，若是遇到这样的孩子，任谁都会觉得毛骨悚然吧。每次搬家，*初那些很是友好的同学，都因为“那家伙是说谎星人啊”渐渐疏远我。没办法，是我不好。这样想着，我便尽量不和任何人扯上关系，静等时日过去。</p> <p>总有**，我会再也看不见那些东西。</p> <p>年幼时的我就是这样，一边不断祈求着，一边任时间流逝，*不必奢谈与谁交心了。</p> <p>被现在的家人收养后，慢慢地我也能和他人结下深厚的“纠葛”。当时藤原家的滋叔叔和塔子阿姨听说亲戚们轮番推托，便专程来领走了我。他们是心地善良的好人，而我不过是他们的远亲。在这座小镇上，我也和妖怪建立了深厚的“纠葛”，这大概是一些小小的偶然与必然几经重合又共同作用的结果，至今我依然这么认为。我随身携带着偶然从祖母那里继承的遗物，而伺机夺取它的妖怪为此前来袭击我。逃到神社后，我不小心打破了结界，钻出来的妖怪恰好和玲子祖母相识，它至今仍旧做着我的保镖，真身是只雪白美丽如狼一般的强大妖怪，平时基本上以又圆又肥的招财猫形象出现——据本人说这不过是它附身后的容器——于是就这样，它作为藤原家饲养的宠物猫和我一起生活，我叫它猫咪老师。</p> <p>玲子祖母似乎和我一样，属于“看得见”的那类人。拥有强大灵力的玲子向她遇到的妖怪们逐个发起挑战，欺负并击败它们后，会让它们在纸上写下其名然后收藏起来，以此作为它们臣服于她的证明。这便是契约书“友人帐”，被持有者召唤名字的妖怪**无法反抗主人，持有者也因此获得了支配众多妖怪的力量。自从我继承了祖母的遗物友人帐后，妖怪们便络绎不*地找上门来，有的想抢走友人帐，有的只是希望我把名字还给它们。猫咪老师和我约定，等我死后，友人帐就归它所有，作为交换，在此期间它会担任我的保镖。可以说友人帐是我和猫咪老师的“缘”之基石，细细想来诸如此类的缘分的种子似乎随时随地散落得到处都是。我们偶尔是远亲，偶尔又成了同班同学，偶尔还会在路上闲聊——也许人与人之间的缘分，就是把这些偶然和必然串连在一起，用心倾听，或由自己领悟到的东西中衍生出来的，当然，这套理论是我从后面即将提到的某人那儿现学现卖的。</p> <p>我便是在这座小镇上，与人和妖怪不断积累着一点一滴的“缘分”，并在有生以来终于明白，人和人也是如此构筑关系的。有时我会想，也许别人在*年幼的时候就已经这样做了，也许我和曾经邂逅的人们也拥有同样的牵绊吧！其实只要用心观察，这个世界上各个角落都散落着机缘。</p> <p>总而言之，现在我终于开始和人结缘，如同刚学走路的幼儿，时而胆怯迷茫，却也不急不缓……</p> <p>傍晚，从七辻屋回家的路上遇到了多轨。多轨和我同校，是隔壁五班的女孩，也是我在这座小镇上结识的重要朋友之一。</p> <p>“你好，夏目。啊……”</p> <p>她和猫咪老师四目相对的下个瞬间——</p> <p>“啊——是小猫咪！”</p> <p>多轨一边大叫着，一边紧紧抱住了猫咪老师。</p> <p>七辻屋是猫咪老师中意的豆包店。因为**我给它买了店里的新品，红豆馅里和有熬好的艾草，想要早点回家吃上豆包的猫咪老师就差没催我了。可惜此刻它被困在多轨的胸前，口齿不清地喊道：“喂，住手！快放开我，你这个——”</p> <p>正苦苦挣扎时——</p> <p>“啊，对不起，我真是……”</p> <p>多轨赶紧放开猫咪老师，把它还给了我。</p> <p>无论是我“看得见”一事，还是猫咪老师是妖怪一事，多轨都心知肚明。</p> <p>初遇多轨时，她穿着件朴素的外套，帽子压得很低，帽檐挡住了眼睛，尽量让自己不惹人注目——也尽量避免别人对自己打招呼，如此小心翼翼地走着。后来我才知道，那是因为当时她正独自和一个妖怪战斗。可那时，对此一无所知的我不小心叫了她一声，多轨也惊讶地回叫了我的名字，以此为契机，我也卷入了此事，并渐渐对多轨有了些了解。我发现，其实她和这个年纪的普通女孩一样，喜欢聊天，尤其喜欢萌萌的东西。</p> <p>“多轨，你现在要回家了吗？”看着穿着学校**、拎着书包的多轨，我问。</p> <p>“嗯，在学校图书室查了些东西，没想到这么晚了。”</p> <p>“查东西？”</p> <p>“嗯，查了些。”</p> <p>“话说你带了什么在身上啊？”从刚才起就不停用鼻子嗅着什么的猫咪老师忽然问道，“有妖怪的味道哦。”</p> <p>猫咪老师把鼻子凑向多轨的书包。</p> <p>“啊，说不定是这个。”</p> <p>多轨像是想到了什么似的，从书包里掏出一个比普通信封大些的白色信封。</p> <p>“嗯啊，就是这个呢。”</p> <p>我紧紧盯着多轨手中的信封，并未发现有何怪异之处。</p> <p>“信封里藏着什么妖怪吗？猫咪老师。”</p> <p>“不好说呢。也许是因为长时间待在妖怪身边，感染了一些气息。不过，也只是些许能被我察觉的微弱妖气而已。”</p> <p>“多轨，能给我看看吗？”</p> <p>“啊，好的。”</p> <p>白色信封已用裁纸刀漂亮地拆开了，里面有一张信纸和另一个茶色的信封。难怪白色信封会比一般信封大些。我取出茶色信封，它并没有封住，上面的封口被精心折叠了起来。</p> <p>“这是？”</p> <p>“那是寄给我爷爷的一封信。”</p> <p>“寄给你爷爷的？”</p> <p>多轨的爷爷憧憬妖怪，一生都在探寻它们。继承了爷爷慎一郎先生的遗物的多轨，也因为那件遗物卷入了和妖怪们有关的各种事件。</p> <p>“事出有因，所以现在寄来了，就写在那张信纸上。”说着，多轨指了指白色信封里那张崭新的信笺。</p> <p>“装在一起的那个旧信封是十多年前写的了。至于为何没有投寄而存放至今，是因为*近，信的主人——”多轨欲言又止，改用恭敬的语气说，“写这封信的那位似乎去世了，她的孙女发现了这个，特意寄到了我家。”</p> <p>“原来是这样啊。信的内容你读过了？”</p> <p>“嗯，不过看不太懂。”</p> <p>“啊？”</p> <p>“就像以前的人写的那种，笔画都绕在一起的字。”</p> <p>“啊，是草书体吧？”</p> <p>“就是那种感觉的字，因为我看不懂，就想去图书室查查读音，结果又觉得和草书体不太一样……”</p> <p>“是这样啊。”</p> <p>我不知不觉就想看看里面写了什么，又赶紧住了手，担心要是从这里忽然蹿出什么，说不定会对多轨造成伤害。</p> <p>“夏目，不要多管闲事了，快点回家吧。”</p> <p>“在说什么啊，明明是猫咪老师自己说有妖怪的气味啊！”</p> <p>“我要早点回家吃豆包了。你这么在意的话，不如把信封带回去，过后再好好研究一番如何？”</p> <p>“呃？啊啊，是哦……多轨，这个可以借我带回家看看吗？”</p> <p>既然这上面有妖怪的气息，说什么也不能让多轨就这么带回去。</p> <p>“啊，好的。那封信，要是能够看懂的话，我也想试着读一读，因为是寄给爷爷的，我比较在意里面写了些什么。不过，如果是和妖怪有关，也许还是夏目你们比较看得懂。”</p> <p>这个世界上，存在着妖怪专用的文字，友人帐就是用这种文字写成的，说不定这封信里的文字也一样。</p> <p>“要是你看懂了，可不可以告诉我里面写了什么？”</p> <p>“我明白。放心吧！”</p> <p>“好了，夏目，事情交代清楚了就赶快回家吧。”</p> <p>在猫咪老师的催促下，我和多轨道了别，往藤原家走去。</p> <p>“猫咪老师，刚才你是不想让多轨涉险，才说了那样的话吧？”</p> <p>“哈？我为什么要在意那种事情？就算蹿出个什么妖怪，只要有我在就不用担心。在你们遇到危险之前，我一定会揍扁那家伙。”</p> <p>“也不是不可能啦，我只是说万一。”</p> <p>一边吃着豆包，猫咪老师一边从鼻孔里哼了一声。</p> <p>我拿出信封，检查了一下内里。万幸这只是一封沾染了妖怪气息的古老信件，在我把白色信封带回来的时候基本上就确定了。私自拆阅他人信件这种事，老实说我有点心虚，但既然多轨把它给了我，那么我读一读也没什么吧。再说，本来应该阅读这封信的人已经过世了。</p> </div> </div>'),('4843a8deeee94ae5a8cefe37bc103ab2','discount','2021-06-15 14:59:42','ff8081816e6aae62016e6aba99fa0003','0.80'),('ff8081816de7a9db016de7a9e9dc0000','test','2020-06-16 18:03:26','ff8081816ddfbcd8016ddfd1f8250004','testVal'),('ff8081816de7a9db016de7a9e9dc0001','discount','2019-10-22 00:39:15','ff8081816de7ce74016de7d962bc0001','0.80'),('ff8081816de7a9db016de7a9e9dc0002','detail','2019-11-06 20:18:33','ff8081816de7ce74016de7d962bc0001','<p>一二三四五，华为真靠谱。</p><p>二三四五六，华为真优秀。</p><p>三四五六七，谷歌哭唧唧。</p><p>四五六七八，大嘴笑开花。</p>'),('ff8081816de7a9db016de7a9e9dc0003','detail','2019-11-06 20:20:30','ff8081816ddfbcd8016ddfd1f8250004','<p>这个笔是真的强！纯路人，有一说一。</p>'),('ff8081816de7a9db016de7a9e9dc0004','detail','2019-11-06 20:20:30','ff8081816de7c3f6016de7cb938d0000','<p>这套tql，真的好用。</p>'),('ff8081816de7a9db016de7a9e9dc0005','discount','2019-11-08 11:02:11','ff8081816ddfbcd8016ddfd1f8250004','0.95'),('ff8081816de7a9db016de7a9e9dc0006','images','2020-06-18 18:41:16','ff8081816de7ce74016de7d962bc0001','ff80808172c6f8fd0172c705fb860000,ff80808172c6f8fd0172c70628eb0001'),('ff8081816de7a9db016de7a9e9dc0007','images','2020-06-18 18:54:18','ff8081816ddfbcd8016ddfd1f8250004','ff80808172c6f8fd0172c70fa59a0003,ff80808172c6f8fd0172c70fdbed0004,ff80808172c6f8fd0172c71003230005'),('ff8081816de7a9db016de7a9e9dc0008','images','2020-06-18 19:42:47','ff8081816de7c3f6016de7cb938d0000','ff80808172c6f8fd0172c72f084a0006,ff80808172c6f8fd0172c72f35850007');
/*!40000 ALTER TABLE `product_metadata` ENABLE KEYS */;
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
INSERT INTO `product_storage` VALUES ('2c9e80837a12bdcf017a133619c70008',98),('2c9e80837a12bdcf017a13a37da60010',50),('2c9e80837a12bdcf017a13ac062c001d',200),('2c9e80837a12bdcf017a13b5c4aa0020',60),('2c9e80837a12bdcf017a13ba6ca00022',140),('2c9e80837a12bdcf017a13bca2490024',40),('2c9e80837a12bdcf017a13bf6d2d0027',84),('2c9e80837a12bdcf017a13c354160029',193),('ff8081816e6aae62016e6aba99fa0004',92),('ff8081816e6aae62016e6abce23c0008',145);
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
  `mail_price` float DEFAULT NULL,
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
  KEY `FK23E9939DB10BDD6B` (`address_id`),
  CONSTRAINT `FK23E9939DB10BDD6B` FOREIGN KEY (`address_id`) REFERENCES `user_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_order`
--

LOCK TABLES `table_order` WRITE;
/*!40000 ALTER TABLE `table_order` DISABLE KEYS */;
INSERT INTO `table_order` VALUES ('202106150001','2021-06-15 09:53:33','143546546','2021-06-15 09:58:08','2021-06-15 09:59:27',5,'2021-06-15 09:56:05',70,NULL,NULL,'2167b1cbf4e4475bb36bed21041302b5','COMMENTED','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050002'),('202106160004','2021-06-16 12:12:31',NULL,NULL,NULL,0,NULL,0,'商品还未开卖！',NULL,'3d288074e5b34dee8d160889815870a1','ERROR','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050002'),('202106160005','2021-06-16 12:26:48','sb','2021-06-16 12:27:23','2021-06-16 12:28:46',0,'2021-06-16 12:27:05',142.4,NULL,NULL,'3f6176fd0f4741d388f36e8c80cf6633','COMMENTED','2c9f80837a14a1d4017a14c6010c0006','2c9f80837a14a1d4017a14c6010c0006'),('202106170001','2021-06-17 11:15:48','12344567','2021-06-17 11:16:26',NULL,10,'2021-06-17 11:16:06',882,NULL,NULL,'c08f7635f0bb4a5ca736a53dfefb0d23','DELIVERED','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050002'),('202106170002','2021-06-17 15:41:27',NULL,NULL,NULL,10,'2021-06-17 15:41:35',109,NULL,NULL,'9cc95c7191d241f9a893f49ddb0ae22b','PAID','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050003'),('202106170003','2021-06-17 15:49:12',NULL,NULL,NULL,10,'2021-06-17 15:49:19',436,NULL,NULL,'0bbff237108248eeb067e9c1ad51df40','PAID','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050002'),('202106170004','2021-06-17 15:51:26',NULL,NULL,NULL,0,NULL,0,'商品还未开卖！',NULL,'6a6571586c8b4403878f1d946700e3b4','ERROR','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050002'),('202106170005','2021-06-17 15:51:52',NULL,NULL,NULL,10,'2021-06-17 15:51:59',119,NULL,NULL,'c0e1edd0967744f78695c742ceae885e','PAID','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816e4566bc016e46d578050002');
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
INSERT INTO `user_address` VALUES ('2c9f80837a14a1d4017a14c575030004','吉林省长春市双德乡',1,'吉林大学大学城','2019-11-14 23:55:23','130000','KAAAsS','19855609598','2c9f80837a14a1d4017a14c575030004'),('2c9f80837a14a1d4017a14c6010c0006','吉林省长春市双德乡',1,'吉林大学大学城','2019-11-14 23:55:23','130000','KAAAsS','13500966296','2c9f80837a14a1d4017a14c6010c0006'),('ff8081816e4566bc016e46d578050002','吉林省长春市双德乡',1,'吉林大学前卫南区北苑一公寓','2019-11-14 23:55:23','130000','KAAAsS','15658610920','2c9c80837a0e7bd5017a0f05bfac0000'),('ff8081816e4566bc016e46d578050003','吉林省长春市双德乡',0,'吉林大学大学城','2019-11-14 23:55:23','130000','KAAAsS','15658610920','2c9c80837a0e7bd5017a0f05bfac0000');
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
INSERT INTO `user_auth` VALUES ('2c9b80837a0e4d60017a0e4dd40b0000','19c44f99-2f17-45ac-98bd-fe60f981cc3d','2021-06-17 11:09:34','123456','admin','2021-06-15 06:13:39','ADMIN'),('2c9c80837a0e7bd5017a0f05bfac0000','5ced89dc-0dae-4807-9189-b03720fb2e71','2021-06-17 11:11:23','Pass123456','15658610920','2021-06-15 09:34:32','USER'),('2c9f80837a14a1d4017a14c575030004','c06902a3-2639-4c21-aff8-4cf0e98cfe78','2021-06-16 12:22:20','Pass123456','19855609598','2021-06-16 12:22:02','USER'),('2c9f80837a14a1d4017a14c6010c0006','15b48dc5-22ea-46a7-bd16-1c5208df1476','2021-06-16 12:22:56','Pass123456','13500966296','2021-06-16 12:22:38','USER');
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` varchar(255) NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `wechat` text,
  `uid` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `FK1437D8A2FB2A3B8D` (`uid`),
  KEY `FK1437D8A274F77639` (`avatar`),
  CONSTRAINT `FK1437D8A274F77639` FOREIGN KEY (`avatar`) REFERENCES `media` (`id`),
  CONSTRAINT `FK1437D8A2FB2A3B8D` FOREIGN KEY (`uid`) REFERENCES `user_auth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('2c9b80837a0e4d60017a0e4dd4100001','2021-06-15 06:13:39','KAAAsS','2c9b80837a0e4d60017a0e4dd40b0000','ff8081816dd40e03016dd410408e0000'),('2c9c80837a0e7bd5017a0f05bfdc0001','2021-06-15 09:34:32','kaaass','2c9c80837a0e7bd5017a0f05bfac0000','ff8081816df87780016df877a9260000'),('2c9f80837a14a1d4017a14c575050005','2021-06-16 12:22:02',NULL,'2c9f80837a14a1d4017a14c575030004','00000000000000000000000000000000'),('2c9f80837a14a1d4017a14c6010e0007','2021-06-16 12:22:38',NULL,'2c9f80837a14a1d4017a14c6010c0006','00000000000000000000000000000000');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-18  0:48:29
