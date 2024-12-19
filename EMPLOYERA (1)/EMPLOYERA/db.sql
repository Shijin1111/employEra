/*
SQLyog Community v13.1.2 (64 bit)
MySQL - 5.5.20-log : Database - employera
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`employera` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `employera`;

/*Table structure for table `bid` */

DROP TABLE IF EXISTS `bid`;

CREATE TABLE `bid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobid` int(11) DEFAULT NULL,
  `workerid` int(11) DEFAULT NULL,
  `bidamount` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `bid` */

insert  into `bid`(`id`,`jobid`,`workerid`,`bidamount`,`status`) values 
(1,1,4,5000,'accepted'),
(2,20,4,4000,'pending'),
(3,21,4,2250,'rejected'),
(4,21,8,2000,'pending'),
(5,21,4,1999,'pending'),
(6,21,6,2005,'pending'),
(7,22,19,750,'accepted'),
(8,22,4,700,'pending'),
(9,22,19,0,'pending'),
(10,23,4,900,'pending'),
(11,24,19,1400,'accepted'),
(12,24,4,1250,'pending'),
(13,22,4,700,'pending'),
(14,20,4,0,'pending'),
(15,18,4,389,'pending'),
(16,18,4,389,'pending');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FROM_ID` int(11) DEFAULT NULL,
  `TO_ID` int(11) DEFAULT NULL,
  `CHAT` varchar(50) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`ID`,`FROM_ID`,`TO_ID`,`CHAT`,`DATE`) values 
(1,1,8,'shd','2023-02-08'),
(2,8,1,'HII','2023-02-08'),
(3,1,6,'hai','2023-02-08'),
(4,1,8,'hii','2023-02-08'),
(5,4,20,'fghjk','2023-02-08'),
(6,4,1,'ok','2023-02-08'),
(7,4,20,'hi','2023-02-08'),
(8,4,20,'hello','2023-02-08'),
(9,4,20,'hai monu','2023-02-11');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `COMPLAINT` varchar(50) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `FEEDBACK` varchar(50) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`ID`,`LID`,`FEEDBACK`,`DATE`) values 
(1,1,'good','2022-09-20'),
(2,1,'srt','2022-12-21'),
(3,1,'gg','2022-12-21'),
(4,1,'gg','2022-12-21'),
(5,1,'gg','2022-12-21'),
(6,1,'gj','2022-12-21'),
(7,1,'?btbyv','2022-12-21'),
(8,1,'helo','2022-12-21'),
(9,4,'hai','2022-12-21'),
(10,1,'dhhd','2023-01-02'),
(11,4,'bb','2023-01-02'),
(12,1,'nfjd','2023-01-09'),
(13,1,'hh','2023-01-09'),
(14,1,'bxhs','2023-01-09'),
(15,4,'nxjx','2023-01-09'),
(16,1,'hdj','2023-01-09'),
(17,4,'ysheue','2023-01-09'),
(18,5,'ambhuttan shupera','2023-01-31'),
(19,20,'adipoli','2023-02-03'),
(20,4,'','2023-02-16'),
(21,4,'hhj','2023-02-16');

/*Table structure for table `jobdetails` */

DROP TABLE IF EXISTS `jobdetails`;

CREATE TABLE `jobdetails` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `WORK` varchar(50) DEFAULT NULL,
  `DETAILS` varchar(50) DEFAULT NULL,
  `PLACE` varchar(50) DEFAULT NULL,
  `POST` varchar(50) DEFAULT NULL,
  `PIN` int(11) DEFAULT NULL,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

/*Data for the table `jobdetails` */

insert  into `jobdetails`(`ID`,`LID`,`WORK`,`DETAILS`,`PLACE`,`POST`,`PIN`,`AMOUNT`,`date`,`time`) values 
(1,1,'Painiting','dfghjkl','kannur','kannur city',678900,10000,NULL,NULL),
(2,2,'Carpentry','dfghjk','kozhikode','city',678543,5000,NULL,NULL),
(18,1,'cleaner','gsjsks','kuthuparamba','74848',0,5000,NULL,NULL),
(20,1,'gdjska','bshakal','hsjsks','848749',0,5000,NULL,NULL),
(21,1,'cleaning ','hdjwkakak','kottayam','737383',0,2500,NULL,NULL),
(22,20,'plumbing','kitchen sink issues ','eruvatty','748392',0,800,NULL,NULL),
(23,20,'painting ','house painting','Thalassery','693738',0,1000,NULL,NULL),
(24,20,'design ','cvhnmk','cvhnm','83773',0,1500,NULL,NULL),
(25,1,'','','','',0,0,NULL,NULL),
(26,1,'dnd','sj','dnn','12',0,12,'2023-02-16','10:00:00 pm'),
(27,1,'','','','',0,0,'0000-00-00','00:00:00'),
(28,1,'vsvd','hwha','ahsh','2636',0,2233,'0000-00-00','00:00:00'),
(29,1,'','','','',0,0,'1000-12-12','00:00:00'),
(30,1,'','','','',0,0,'0000-00-00','00:00:00'),
(31,1,'jdj','bs','SHIJIN P','P',0,0,'','');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `LID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `TYPE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`LID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`LID`,`USERNAME`,`PASSWORD`,`TYPE`) values 
(1,'amal','amal','user'),
(2,'ADMIN','123','admin'),
(3,'amar','amar123','user'),
(4,'abhi','abhi','worker'),
(5,'','','user'),
(6,'raj','raj','worker'),
(7,'sidu','sidu','reject'),
(8,'amaru','amaru','worker'),
(9,'','','user'),
(10,'','','user'),
(11,'znnx','xnnxn','user'),
(12,'sidu','sidu','reject'),
(13,'raj','raj','reject'),
(14,'jay','jay','reject'),
(15,'abu','abu','reject'),
(16,'ab','ab','reject'),
(17,'abc','abc','worker'),
(18,'suni','suni','worker'),
(19,'jishnu','jishnu','worker'),
(20,'nand','nand','user'),
(21,'shijin','shijin','worker'),
(22,'','','pending');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `RATING` varchar(20) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`ID`,`LID`,`RATING`,`DATE`) values 
(1,4,'4.0','2023-01-20'),
(2,19,'4.5','2023-02-03');

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FROM_ID` int(11) DEFAULT NULL,
  `TO_ID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `report` */

insert  into `report`(`ID`,`FROM_ID`,`TO_ID`,`DATE`,`reason`) values 
(1,4,0,'0000-00-00','2023-01-02'),
(2,4,0,'0000-00-00','2023-01-02'),
(3,4,0,'0000-00-00','2023-01-02'),
(4,4,0,'2023-01-02','1'),
(5,4,0,'2023-01-02','1'),
(6,4,0,'2023-01-02','1'),
(7,4,0,'2023-01-02','3'),
(8,4,0,'2023-01-02','3'),
(9,4,0,'2023-01-02','3'),
(10,4,3,'2023-01-02','jrj'),
(11,4,1,'2023-01-09','192.168.51.67'),
(12,4,1,'2023-01-09','192.168.51.67'),
(13,4,3,'2023-01-09','hsjsoe'),
(14,4,20,'2023-02-03','baad'),
(15,4,1,'2023-02-16','jdjs'),
(16,4,10,'2023-02-16',''),
(17,4,3,'2023-02-16','jdjdk');

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `WORK_ID` int(11) DEFAULT NULL,
  `WORKER_ID` int(11) DEFAULT NULL,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `request` */

insert  into `request`(`ID`,`WORK_ID`,`WORKER_ID`,`AMOUNT`,`STATUS`) values 
(1,1,2,500,'accepted');

/*Table structure for table `skill` */

DROP TABLE IF EXISTS `skill`;

CREATE TABLE `skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) NOT NULL,
  `skills` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`,`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `skill` */

insert  into `skill`(`id`,`lid`,`skills`) values 
(1,0,'uygiu'),
(2,4,'jfjf'),
(3,4,'jffjf'),
(4,4,'ggg'),
(5,4,'');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `FIRSTNAME` varchar(50) DEFAULT NULL,
  `LASTNAME` varchar(50) DEFAULT NULL,
  `GENDER` varchar(20) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `PLACE` varchar(50) DEFAULT NULL,
  `POST` varchar(50) DEFAULT NULL,
  `PIN` int(11) DEFAULT NULL,
  `E-MAIL` varchar(50) DEFAULT NULL,
  `PHONE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`LID`,`FIRSTNAME`,`LASTNAME`,`GENDER`,`DOB`,`PLACE`,`POST`,`PIN`,`E-MAIL`,`PHONE`) values 
(1,1,'amar','nath','   male  ','0000-00-00','panoor','hdjd',526277,'dhjsj@gmail.com',3783),
(2,3,'amar','djh','   male  ','0000-00-00','hjfj','jdjd',7833,'dhhsj@gmail.com',6738),
(3,5,'','','  female','0000-00-00','','',0,'huj',0),
(4,9,'','','  female','0000-00-00','','',0,'',0),
(5,10,'','','  female','0000-00-00','','',0,'',0),
(6,11,'djjd','ndjd','   male  ','0000-00-00','ndn','nd',0,'fjjd',0),
(7,20,'abhinand','Mp','   male  ','0000-00-00','kannur','kannur',477483,'abi@123gmail.com',478392999);

/*Table structure for table `worker` */

DROP TABLE IF EXISTS `worker`;

CREATE TABLE `worker` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `FIRSTNAME` varchar(50) DEFAULT NULL,
  `LASTNAME` varchar(50) DEFAULT NULL,
  `GENDER` varchar(20) DEFAULT NULL,
  `PLACE` varchar(50) DEFAULT NULL,
  `POST` varchar(50) DEFAULT NULL,
  `PIN` int(11) DEFAULT NULL,
  `PHONE` bigint(20) DEFAULT NULL,
  `E-MAIL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `worker` */

insert  into `worker`(`ID`,`LID`,`FIRSTNAME`,`LASTNAME`,`GENDER`,`PLACE`,`POST`,`PIN`,`PHONE`,`E-MAIL`) values 
(8,4,'dhhsh','hxjdj','male','jdjdj','nfndn',3344,0,'38838'),
(9,6,'arun','dff','male','jdjdj','nfndn',3344,0,'38838'),
(10,7,'sd','aded','male','jdjdj','nfndn',3344,0,'38838'),
(14,8,'Amarnath','sp','male','panoor','panoor',763678,74849393,'74849393'),
(15,12,'sidarth ','kkk','male','sghsjsj','jJajsksk',639392,74849393,'74749292'),
(16,13,'hsjslka','hahskal','female','Bbsja','zbbzja',737373,0,'6363929283'),
(17,14,'sghsks','hshsjs','female','fsgsys','sgsvsn',673663,0,'63728393'),
(18,15,'bh','bb','male','bb','bb',0,0,'bbn'),
(19,16,'cf','bb','male','nbn','nb',0,0,'bb'),
(20,17,'hh','nnh','male','bbb','bb',0,77,'nn'),
(21,18,'suni','kp','male','oolampara','melekav',647383,73839292,'tyresuni@gmail.com'),
(22,19,'jishnu','n','male','thaliparamba','eruvatty',670581,703473379,'jishnu123@gmail.com'),
(23,21,'shijin','n','male','bx','bxb',0,83833,'mma'),
(24,22,'hdh','njj','male','nn','nn',0,0,'nn');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
