
DROP TABLE IF EXISTS `tb_area`;

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

LOCK TABLES `tb_area` WRITE;
INSERT INTO `tb_area` VALUES (3,'成都','东苑',12,'2017-06-04 19:12:58','2017-06-04 19:12:58'),(4,'重庆','南苑',10,'2017-06-04 19:13:09','2017-06-04 19:13:09');
UNLOCK TABLES;










DROP TABLE IF EXISTS `tb_product_category`;
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

LOCK TABLES `tb_product_category` WRITE;
INSERT INTO `tb_product_category` VALUES (9,'食品','所有食品的母类',100,NULL,NULL,0),(10,'酒水','对应饮料与各种酒类',100,NULL,NULL,0),(11,'调料','对应各种调味料品',100,NULL,NULL,0),(12,'干杂','对应各种干杂货',100,NULL,NULL,0),(13,'零食百货','对应各种零食',100,NULL,NULL,0),(14,'生鲜','对应各种生鲜产品',100,NULL,NULL,0),(15,'百货','对应其他品类百货',100,NULL,NULL,0);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tb_brand`;

CREATE TABLE `tb_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(100) NOT NULL,
  `brand_desc` varchar(500) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `brand_category_id` int(11) DEFAULT '0',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_brand` WRITE;
INSERT INTO `tb_brand` VALUES (30,'娃哈哈','饮料品牌',100,NULL,NULL,10),(31,'康师傅','方便面品牌',100,NULL,NULL,9),(32,'茅台','酒类品牌',100,NULL,NULL,10);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tb_label`;

CREATE TABLE `tb_label` (
  `label_id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(100) NOT NULL,
  `label_desc` varchar(500) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_label` WRITE;
INSERT INTO `tb_label` VALUES (60,'热销商品','该商品属于热销商品',100,NULL,NULL),(61,'优惠商品','该商品属于优惠商品',100,NULL,NULL),(62,'品质商品','该商品属于品牌商品或者质量无忧商品',100,NULL,NULL);
UNLOCK TABLES;




DROP TABLE IF EXISTS `tb_attribute_name`;

CREATE TABLE `tb_attribute_name` (
  `attribute_name_id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(100) NOT NULL,
  `must_chioce` Bool DEFAULT false,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `attribute_name_category_id` int(11) DEFAULT '0',
  PRIMARY KEY (`attribute_name_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_attribute_name` WRITE;
INSERT INTO `tb_attribute_name` VALUES (70,'颜色',false,100,NULL,9),(71,'重量',false,100,NULL,9),(72,'形状',false,100,NULL,9);
UNLOCK TABLES;





DROP TABLE IF EXISTS `tb_attribute_value`;

CREATE TABLE `tb_attribute_value` (
  `attribute_value_id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_value` varchar(100) NOT NULL,
  `attribute_name_id` int(11) NOT NULL ,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`attribute_value_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_attribute_value` WRITE;
INSERT INTO `tb_attribute_value` VALUES (80,'白色',70,100,NULL),(81,'黑色',70,100,NULL),(82,'蓝色',70,100,NULL),(83,'500g',71,100,NULL),(84,'100g',71,100,NULL),(85,'圆形',72,100,NULL),(86,'方形',72,100,NULL);
UNLOCK TABLES;



DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

LOCK TABLES `tb_product_img` WRITE;

INSERT INTO `tb_product_img` VALUES (19,'/upload/images/item/shop/15/20170605233021865310.jpg',NULL,NULL,'2017-06-05 23:30:22',100),
(20,'/upload/images/item/shop/15/20170605233022618071.jpg',NULL,NULL,'2017-06-05 23:30:22',100),
(21,'/upload/images/item/shop/15/20170605233022246642.jpg',NULL,NULL,'2017-06-05 23:30:22',100),
(22,'/upload/images/item/shop/15/20170605234852321010.jpg',NULL,NULL,'2017-06-05 23:48:52',101),
(23,'/upload/images/item/shop/15/20170606084902162950.jpg',NULL,NULL,'2017-06-06 08:49:02',101),
(24,'/upload/images/item/shop/15/20170606085020558290.jpg',NULL,NULL,'2017-06-06 08:50:20',101),
(25,'/upload/images/item/shop/16/20170606085740956160.jpg',NULL,NULL,'2017-06-06 08:57:40',101),
(26,'/upload/images/item/shop/16/20170606090259397060.png',NULL,NULL,'2017-06-06 09:02:59',102),
(27,'/upload/images/item/shop/20/20170606201141425050.jpg',NULL,NULL,'2017-06-06 20:11:42',102),
(28,'/upload/images/item/shop/20/20170606201141387851.jpg',NULL,NULL,'2017-06-06 20:11:42',102),
(29,'/upload/images/item/shop/20/20170606201141503752.png',NULL,NULL,'2017-06-06 20:11:42',103),
(30,'/upload/images/item/shop/20/20170606203630923430.jpg',NULL,NULL,'2017-06-06 20:36:31',103),
(31,'/upload/images/item/shop/20/20170606203631552081.png',NULL,NULL,'2017-06-06 20:36:31',103);

UNLOCK TABLES;







DROP TABLE IF EXISTS `tb_productsku`;

CREATE TABLE `tb_productsku` (
  `productsku_id` int(11) NOT NULL AUTO_INCREMENT,
  `productsku_name` varchar(100) NOT NULL,
  `productsku_attributes` varchar(500) NOT NULL,
  `productsku_category_id` int(11) NOT NULL ,
  `productsku_brand_id` int(11) NOT NULL ,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`productsku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_productsku` WRITE;
INSERT INTO `tb_productsku` VALUES (1000,'iphone xr','81:84:86',9,30,100,NULL,NULL),(1001,'iphone xr','80:84:86',9,30,100,NULL,NULL),(1002,'iphone xr','82:84:86',9,30,100,NULL,NULL);
UNLOCK TABLES;



DROP TABLE IF EXISTS `tb_shop_product`;

CREATE TABLE `tb_shop_product` (
  `shop_product_id` int(11) NOT NULL AUTO_INCREMENT,
  `productsku_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `shop_product_sell_price` varchar(100) NOT NULL,
  `shop_product_original_price` varchar(100) NOT NULL,
  `shop_product_label` varchar(500) NOT NULL,
  `shop_product_description` varchar(500) NOT NULL,
  `shop_product_status` int(2) DEFAULT '0',
  `shop_product_store` int(10) DEFAULT '0',
  `shop_product_sales` int(10) DEFAULT '0',
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`shop_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_shop_product` WRITE;
INSERT INTO `tb_shop_product` VALUES (10000,1000,0,'6650','6999','60:61:62','至臻挚爱，动心之选',0,999,87923,100,NULL,NULL),
(10001,1001,0,'6750','6999','60:61:62','至臻挚爱，动心之选',0,999,80203,100,NULL,NULL),
(10002,1002,0,'6850','6999','60:61:62','至臻挚爱，动心之选',0,999,66233,100,NULL,NULL);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tb_product_comment`;
CREATE TABLE `tb_product_comment` (
  `product_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_product_id` int(11) NOT NULL ,
  `comment_content` varchar(2000) NOT NULL,
  `comment_from` varchar(500) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `parent_comment_id` int(11) DEFAULT '0',
  PRIMARY KEY (`product_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

LOCK TABLES `tb_product_comment` WRITE;

INSERT INTO `tb_product_comment` VALUES (19,10000,'真是非常好用的，感觉飞起来了','hoggen',100,NULL,0),
(20,10000,'很好很nice','第三段',100,NULL,0),
(21,10000,'非常漂亮','实打实',100,NULL,0),
(22,10001,'速度超快','撒',100,NULL,0),
(23,10001,'真心棒','三大',100,NULL,0),
(24,10001,'不错，值得起这个价','大萨达',100,NULL,0),
(25,10002,'物流超快','该如何',100,NULL,0),
(26,10002,'很不错','啊',100,NULL,0);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tb_comment_img`;
CREATE TABLE `tb_comment_img` (
  `comment_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `comment_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`comment_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

LOCK TABLES `tb_comment_img` WRITE;

INSERT INTO `tb_comment_img` VALUES (19,'/upload/images/item/shop/15/20170605233021865310.jpg',NULL,NULL,'2017-06-05 23:30:22',19),
(20,'/upload/images/item/shop/15/20170605233022618071.jpg',NULL,NULL,'2017-06-05 23:30:22',19),
(21,'/upload/images/item/shop/15/20170605233022246642.jpg',NULL,NULL,'2017-06-05 23:30:22',19),
(22,'/upload/images/item/shop/15/20170605234852321010.jpg',NULL,NULL,'2017-06-05 23:48:52',20),
(23,'/upload/images/item/shop/15/20170606084902162950.jpg',NULL,NULL,'2017-06-06 08:49:02',20),
(24,'/upload/images/item/shop/15/20170606085020558290.jpg',NULL,NULL,'2017-06-06 08:50:20',20),
(25,'/upload/images/item/shop/16/20170606085740956160.jpg',NULL,NULL,'2017-06-06 08:57:40',21),
(26,'/upload/images/item/shop/16/20170606090259397060.png',NULL,NULL,'2017-06-06 09:02:59',21),
(27,'/upload/images/item/shop/20/20170606201141425050.jpg',NULL,NULL,'2017-06-06 20:11:42',25),
(28,'/upload/images/item/shop/20/20170606201141387851.jpg',NULL,NULL,'2017-06-06 20:11:42',25),
(29,'/upload/images/item/shop/20/20170606201141503752.png',NULL,NULL,'2017-06-06 20:11:42',25),
(30,'/upload/images/item/shop/20/20170606203630923430.jpg',NULL,NULL,'2017-06-06 20:36:31',26),
(31,'/upload/images/item/shop/20/20170606203631552081.png',NULL,NULL,'2017-06-06 20:36:31',26);

UNLOCK TABLES;




DROP TABLE IF EXISTS `tb_review_product`;

CREATE TABLE `tb_review_product` (
  `shop_product_id` int(11) NOT NULL,
  `productsku_id` int(11) NOT NULL,
  `shop_name` varchar(100) NOT NULL,
  `shop_product_sell_price` varchar(100) NOT NULL,
  `product_brand` varchar(100) NOT NULL,
  `product_category` varchar(100) NOT NULL,
  `shop_product_status` int(2) DEFAULT '0',
  `begin_review_time` datetime DEFAULT NULL,
  `review_time` datetime DEFAULT NULL,
  PRIMARY KEY (`shop_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_review_product` WRITE;
INSERT INTO `tb_review_product` VALUES (10000,1000,'自营','6650','苹果','手机',0,NULL,NULL),
(10001,1001,'自营','6750','苹果','手机',0,NULL,NULL),
(10002,1002,'自营','6850','苹果','手机',0,NULL,NULL);
UNLOCK TABLES;




DROP TABLE IF EXISTS `tb_order_delivery`;

CREATE TABLE `tb_order_delivery` (
  `order_id` int(11) NOT NULL,
  `send_man_id` int(11) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `dimension` varchar(100) NOT NULL,
  `status` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_order_delivery` WRITE;
INSERT INTO `tb_order_delivery` VALUES (1,1,'20.78762','40.231242',0,NULL),
(2,1,'20.78762','40.231242',0,NULL),
(3,1,'20.78762','40.231242',0,NULL);
UNLOCK TABLES;




DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_status` int(2) DEFAULT '0',
  `tracking_number` varchar(100) NOT NULL,
  `pay_type` int(2) DEFAULT '0',
  `pay_status` int(2) DEFAULT '0',
  `terminal` int(2) DEFAULT '0',
  `send_status` int(2) DEFAULT '0',
  `order_price` varchar(100) NOT NULL,
  `client_pay_price` varchar(100) NOT NULL,
  `freight_price` varchar(100) NOT NULL,
  `product_ids` varchar(500) NOT NULL,
  `order_description` varchar(500) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;


LOCK TABLES `tb_order` WRITE;
INSERT INTO `tb_order` VALUES (1,0,1,0,'5675676575675',0,0,0,0,'20','20','5','10000:10001','中午之前送到',NULL,NULL,NULL),
(2,0,1,0,'w123323213',0,0,0,0,'230','230','15','10002:10001','下午之前送到',NULL,NULL,NULL),
(3,0,1,0,'122424431',0,0,0,0,'120','120','25','10000:10002','晚上之前送到',NULL,NULL,NULL);
UNLOCK TABLES;





