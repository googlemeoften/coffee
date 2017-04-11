/*
SQLyog Ultimate v8.32 
MySQL - 5.5.51 : Database - coffee
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`coffee` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `coffee`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(32) DEFAULT NULL COMMENT '职员名字',
  `admin_contact` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `admin_work` int(11) DEFAULT NULL COMMENT '0:boss,1:经理,2:值班人',
  `admin_level` int(11) DEFAULT NULL COMMENT '级别和能够查看的信息相关',
  `admin_salary` int(11) DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`item_id`,`order_id`,`product_id`,`amount`,`unit_price`,`total_price`) values (1,1,3,5,999,4995),(2,1,4,3,444,1332),(3,1,5,5,2222,11110),(4,2,3,2,999,1998),(5,2,4,1,444,444),(6,3,1,5,555,2775),(7,3,6,1,3323,3323),(8,4,5,1,2222,2222);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `extend_id` varchar(64) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `transaction_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0:未付款，1付款未做，2付款已做',
  `note` varchar(128) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`order_id`,`extend_id`,`total_price`,`transaction_time`,`status`,`note`,`user_id`) values (1,'2017032931731777',17437,'2017-03-29 19:24:43',2,NULL,1),(2,'2017033059364658',2442,'2017-03-30 12:22:47',2,NULL,1),(3,'2017033114263328',6098,'2017-03-31 11:56:23',1,NULL,1),(4,'2017041165807042',2222,'2017-04-11 15:44:35',0,NULL,1);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) DEFAULT NULL,
  `product_original_price` int(11) DEFAULT NULL,
  `product_discount_price` int(11) DEFAULT NULL,
  `product_photo` varchar(128) DEFAULT NULL,
  `product_type` int(11) DEFAULT NULL,
  `onsale` int(11) DEFAULT NULL COMMENT '0:咖啡，1:饮品,2:美食',
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`product_id`,`product_name`,`product_original_price`,`product_discount_price`,`product_photo`,`product_type`,`onsale`,`description`) values (1,'蛋挞',777,555,'photo/coffee/coffee.jpg',2,1,NULL),(2,'蜂蜜柚子茶',1000,900,'photo/coffee/coffee.jpg',1,1,NULL),(3,'蜂蜜柚子茶',999,777,'photo/coffee/coffee.jpg',1,0,NULL),(4,'菊花茶',777,444,'photo/coffee/coffee.jpg',1,1,NULL),(5,'拿铁咖啡',3333,2222,'photo/coffee/coffee.jpg',0,1,NULL),(6,'魔卡咖啡',4444,3323,'photo/coffee/coffee.jpg',0,1,NULL),(10,'铁观音茶',9999,8888,'image/2017033099502222.jpg',1,1,NULL),(11,'魔卡咖啡',9999,8888,'image/2017033094158588.jpg',0,1,NULL),(12,'魔卡咖啡',9999,8888,'image/2017033023708038.jpg',0,1,NULL),(13,'魔卡咖啡',9999,8888,'image/2017033049055284.jpg',0,1,NULL),(14,'魔卡咖啡',11111,9999,'image/2017033095773057.jpg',0,1,NULL),(15,'超级魔卡',9999,8888,'image/2017033083126837.jpg',0,1,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`phone`,`level`) values (1,'dave','123','15823212346',1),(2,'dave123','123456','15823212340',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/**
使用的是SQLYog到出
 */
