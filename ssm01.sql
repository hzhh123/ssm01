/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.1.49-community : Database - ssm01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm01` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm01`;

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `type` varchar(25) DEFAULT NULL,
  `jsEvent` varchar(64) DEFAULT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `sortCode` varchar(30) DEFAULT NULL,
  `isPublic` tinyint(1) DEFAULT '0',
  `isEnable` tinyint(1) DEFAULT '1',
  `isEdit` tinyint(1) DEFAULT '1',
  `remark` text,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `is_edit` bit(1) DEFAULT NULL,
  `is_enable` bit(1) DEFAULT NULL,
  `is_public` bit(1) DEFAULT NULL,
  `js_event` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `sort_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`parentId`,`code`,`name`,`url`,`type`,`jsEvent`,`icon`,`sortCode`,`isPublic`,`isEnable`,`isEdit`,`remark`,`createtime`,`modifytime`,`is_edit`,`is_enable`,`is_public`,`js_event`,`parent_id`,`sort_code`) values (1,NULL,'111','11','11','1',NULL,'111',NULL,0,1,1,'',NULL,NULL,'','','\0','11',NULL,'11'),(2,NULL,'55','66','','1',NULL,'',NULL,0,1,1,'',NULL,NULL,'\0','\0','\0','',NULL,'4');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `state` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`state`,`createtime`,`updatetime`) values (181,'aa','123','1','2017-07-29 17:46:01','2017-07-29 18:10:57'),(182,'aaa','123','1','2017-07-29 18:11:18','2017-07-29 18:14:52'),(183,'xxx','xxx','1','2017-07-29 18:12:14','2017-07-29 18:14:11');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
