
DROP TABLE IF EXISTS `tb_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_area` (
  `area_id` int(5) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `area_desc` varchar(1000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `tb_area` WRITE;
/*!40000 ALTER TABLE `tb_area` DISABLE KEYS */;
INSERT INTO `tb_area` VALUES (3,'成都','东苑',12,'2017-06-04 19:12:58','2017-06-04 19:12:58'),(4,'重庆','南苑',10,'2017-06-04 19:13:09','2017-06-04 19:13:09');
/*!40000 ALTER TABLE `tb_area` ENABLE KEYS */;
UNLOCK TABLES;










DROP TABLE IF EXISTS `tb_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL,
  `product_category_desc` varchar(500) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `product_category_parent_id` int(11) DEFAULT '0',
  PRIMARY KEY (`product_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_category`
--

LOCK TABLES `tb_product_category` WRITE;
/*!40000 ALTER TABLE `tb_product_category` DISABLE KEYS */;
INSERT INTO `tb_product_category` VALUES (9,'食品','所有食品的母类',100,NULL,NULL,0),(10,'酒水','对应饮料与各种酒类',100,NULL,NULL,0),(11,'调料','对应各种调味料品',100,NULL,NULL,0),(12,'干杂','对应各种干杂货',100,NULL,NULL,0),(13,'零食百货','对应各种零食',100,NULL,NULL,0),(14,'生鲜','对应各种生鲜产品',100,NULL,NULL,0),(15,'百货','对应其他品类百货',100,NULL,NULL,0);
/*!40000 ALTER TABLE `tb_product_category` ENABLE KEYS */;
UNLOCK TABLES;