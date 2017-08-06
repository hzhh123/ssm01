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

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `_order` varchar(255) DEFAULT NULL,
  `isheader` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`parentid`,`name`,`url`,`icon`,`_order`,`isheader`,`state`) values (1,4,'用户管理','/user','','1','0','1'),(2,4,'角色管理','/role','','223','0','1'),(3,4,'权限管理','/permission','','5','0','1'),(4,5,'权限系统','','','1','0','1'),(5,0,'主菜单',NULL,NULL,NULL,'1','1');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_enable` varchar(255) DEFAULT NULL,
  `is_public` varchar(255) DEFAULT NULL,
  `js_event` varchar(255) DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `sort_code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `classname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`code`,`createtime`,`icon`,`is_enable`,`is_public`,`js_event`,`modifytime`,`name`,`parent_id`,`remark`,`sort_code`,`type`,`url`,`classname`) values (1,'33',NULL,'','1','0','',NULL,'333',NULL,'','33','0','/permission',NULL),(2,'44',NULL,'','1','0','',NULL,'444',1,'','44','0','/permission/list',NULL),(3,'55',NULL,'','1','0','',NULL,'55',1,'','55','0','/permission/get',NULL),(4,'556',NULL,'fa fa-edit','1','0','btn_edit()',NULL,'修改',1,'','66','1','/permission/update','layui-btn-warm'),(5,'77',NULL,'fa fa-plus','1','0','btn_add()',NULL,'新增',1,'','65','1','/permission/save','layui-btn-normal'),(7,'889',NULL,'','0','1','',NULL,'999',NULL,'','999','1','/role',NULL),(8,'ff',NULL,'','1','1','',NULL,'ff',7,'','111','1','/role/list',NULL),(9,'wwe',NULL,NULL,'1','1',NULL,NULL,'we',7,NULL,'12','1','/role/save',NULL),(10,'44t',NULL,NULL,'0','1',NULL,NULL,'rt',7,NULL,'222','1','/role/update',NULL),(13,'a111',NULL,'','1','1','btn_del()',NULL,'删除',1,'','67','1','/permission/deleteBatch','layui-btn-danger'),(14,'eer',NULL,'','1','1','',NULL,'rtt',7,'','123','1','/role/get',NULL),(15,'xx',NULL,NULL,'1','1',NULL,NULL,'rr',NULL,NULL,'54','0','/menu',NULL),(16,'dd',NULL,NULL,'1','1',NULL,NULL,'cc',15,NULL,'33','0','/menu/list',NULL),(18,'sd',NULL,'','1','1','btn_add()',NULL,'菜单新增',15,'','1','1','/menu/save',''),(19,'fgg',NULL,'','1','1','btn_edit()',NULL,'菜单编辑',15,'','34','1','/menu/update',''),(20,'tt',NULL,NULL,'1','1',NULL,NULL,NULL,15,NULL,'4','0','/menu/get',NULL),(21,'112',NULL,'','1','1','',NULL,'查询所有的菜单',15,'','45','2','/menu/findAll',''),(22,'D23',NULL,'','1','1','',NULL,'获取菜单',NULL,'','145','0','/menu/getMenus',''),(23,'D21',NULL,NULL,'1','1',NULL,NULL,NULL,15,NULL,'222','0','/menu/getForlist',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `_desc` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`_desc`,`rolename`,`state`) values (1,'ccc','cc','1'),(2,'xx','xx','1'),(5,'ccc','cc','1'),(6,'xdf','xsd','1'),(7,'vvv','tyy','1'),(8,'rr',NULL,'1');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `menuid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  KEY `FK_g5obsisnntn49xc363wab6ohj` (`roleid`),
  KEY `FK_3u46bn6jo7ld1d76tj89oea56` (`menuid`),
  CONSTRAINT `FK_3u46bn6jo7ld1d76tj89oea56` FOREIGN KEY (`menuid`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK_g5obsisnntn49xc363wab6ohj` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`menuid`,`roleid`) values (1,1),(2,2),(3,1),(4,1),(5,2);

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `roleid` int(11) NOT NULL,
  `permissionid` int(11) NOT NULL,
  KEY `FK_7foqbw56b7xaycgl08wwa6y56` (`permissionid`),
  KEY `FK_m3s0f4uve5lswqg5d9oojsxge` (`roleid`),
  CONSTRAINT `FK_m3s0f4uve5lswqg5d9oojsxge` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_7foqbw56b7xaycgl08wwa6y56` FOREIGN KEY (`permissionid`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`roleid`,`permissionid`) values (1,1),(1,2),(2,3),(2,4),(1,5),(1,7),(1,8),(1,9),(1,10),(1,13),(1,14);

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
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`state`,`createtime`,`updatetime`) values (1,'aa','123','1','2017-07-29 18:11:18','2017-08-01 13:36:13'),(183,'xxxt','xxx','1','2017-07-29 18:12:14','2017-08-01 16:29:45'),(184,'eee','111','0',NULL,NULL),(185,'eee','111','1',NULL,NULL),(186,'eee','111','1',NULL,NULL),(187,'eee','111','0',NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `roleid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  KEY `FK_jwv62hhuqojjk7pot7kaex3e1` (`userid`),
  KEY `FK_jpkvqqgndeonhr2alguthv64e` (`roleid`),
  CONSTRAINT `FK_jpkvqqgndeonhr2alguthv64e` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_jwv62hhuqojjk7pot7kaex3e1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`roleid`,`userid`) values (1,1),(2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
