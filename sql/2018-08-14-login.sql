/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.13 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(500) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `authorities` */

insert  into `authorities`(`id`,`authority`,`createTime`) values (1,'/r1','2018-08-13 18:41:21'),(2,'/r2','2018-08-13 18:41:24'),(3,'/r3','2018-08-13 18:41:26'),(4,'/r4','2018-08-13 18:41:30'),(5,'/r5','2018-08-13 18:41:34'),(6,'/r6','2018-08-13 18:41:38'),(7,'/r7','2018-08-13 18:41:42'),(8,'/r8','2018-08-13 18:41:45'),(9,'/r9','2018-08-13 18:41:49'),(10,'/r10','2018-08-13 18:41:55');

/*Table structure for table `role_authorities` */

DROP TABLE IF EXISTS `role_authorities`;

CREATE TABLE `role_authorities` (
  `roleId` bigint(11) NOT NULL,
  `authorityId` bigint(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_authorities` */

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`id`,`role`,`createTime`) values (1,'USER','2018-08-13 16:59:20'),(2,'ADMIN','2018-08-13 16:59:22'),(3,'GUEST','2018-08-13 16:59:42');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `userId` bigint(11) NOT NULL,
  `roleId` bigint(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`userId`,`roleId`,`createTime`) values (1,1,'2018-08-13 17:00:01'),(2,1,'2018-08-13 17:01:41'),(3,2,'2018-08-13 17:01:41'),(4,1,'2018-08-13 17:01:42'),(5,1,'2018-08-13 17:01:42'),(4,2,'2018-08-13 17:03:22');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`mobile`,`enabled`,`createTime`) values (1,'a','$2a$10$xQjpgfaUOgFup8iqPFMHfekZyiUrVenTtPYgKyYESuxWTM.k1weKC','18200000001',1,'2018-08-13 16:57:36'),(2,'b','1','18200000002',1,'2018-08-13 16:57:56'),(3,'admin','1','18200000003',1,'2018-08-13 16:58:22'),(4,'c','1','18200000004',1,'2018-08-13 16:58:41'),(5,'d','1','18200000005',1,'2018-08-13 16:58:49');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
