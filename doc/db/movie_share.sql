-- MySQL dump 10.13  Distrib 5.7.27, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_share
-- ------------------------------------------------------
-- Server version	5.7.27-log

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_name` varchar(100) NOT NULL COMMENT '类别名',
  `category_des` varchar(200) NOT NULL COMMENT '描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`category_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='视频类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1000,'动漫','动漫','2020-01-02 20:59:17','lidihao','2020-01-02 20:59:23','lidihao',0),(1001,'科技','科技','2020-01-02 20:59:46','lidihao','2020-01-02 20:59:51','lidihao',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名',
  `menu_eng` varchar(100) NOT NULL COMMENT '菜单英文名',
  `menu_des` varchar(200) NOT NULL COMMENT '菜单描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `menu_url` varchar(200) NOT NULL DEFAULT '' COMMENT '菜单url',
  `parent_menu_id` int(11) NOT NULL DEFAULT '-1' COMMENT '父级菜单，顶级菜单的parent_menu_id',
  `has_child` bit(1) DEFAULT b'0' COMMENT '是否有子菜单',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`menu_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1000,'视频类型','video-type','视频分类',1,'',0,_binary '','2019-12-29 19:45:41','lidihao','2019-12-29 19:45:49','lidihao',_binary '\0'),(1001,'动漫','video-type-animate','动漫类型视频',2,'/video/category-detail?type=animate',1000,_binary '\0','2019-12-29 19:47:24','lidihao','2019-12-29 19:47:32','lidihao',_binary '\0'),(1002,'视频管理','video-manager','',3,'',0,_binary '','2019-12-29 21:33:40','lidihao','2019-12-29 21:33:48','lidihao',_binary '\0'),(1003,'视频上传','video-manager-upload','视频上传页面',4,'/VideoManager/upload',1002,_binary '\0','2019-12-29 21:35:21','lidihao','2019-12-29 21:35:26','lidihao',_binary '\0'),(1004,'个人中心','person-center','个人中心',5,'',0,_binary '','2019-12-29 21:36:40','lidihao','2019-12-29 21:36:48','lidihao',_binary '\0'),(1005,'个人空间','person-space','个人空间',6,'/user/person-space',1004,_binary '\0','2019-12-29 21:37:45','lidihao','2019-12-29 21:37:52','lidihao',_binary '\0'),(1006,'后台管理','system-manager','后台管理',7,'',0,_binary '','2020-01-03 22:54:08','lidihao','2020-01-03 22:54:13','lidihao',_binary '\0'),(1007,'视频审核','video-apply','视频审核',8,'/system/video-apply',1006,_binary '\0','2020-01-03 22:55:23','lidihao','2020-01-03 22:55:30','lidihao',_binary '\0');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(100) NOT NULL COMMENT '名字',
  `permission_des` varchar(200) NOT NULL COMMENT '描述',
  `permission_action` varchar(200) NOT NULL COMMENT '权限实体',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`permission_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需要权限校验的url表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `picture_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `file_name` varchar(255) NOT NULL COMMENT '图片名称',
  `height` int(11) NOT NULL COMMENT '图片高度',
  `size` bigint(20) NOT NULL COMMENT '图片大小',
  `url` varchar(255) NOT NULL COMMENT '图片地址',
  `width` int(11) NOT NULL COMMENT '图片宽度',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`picture_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1090 DEFAULT CHARSET=utf8 COMMENT='图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1000,'微信图片_20191016102740.jpg',1440,387499,'/image/0111103348e24271b1b249db6f7eb3602161560649051625625.jpg',1080,NULL,NULL,NULL,NULL,0),(1001,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d4bca09499224ecbbd78e89197b6ec1f9185164596198678892.jpg',1080,NULL,NULL,NULL,NULL,0),(1002,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/4bbb6ef16eb2466981f6a3cd4d92a3628746552568449549982.jpg',1080,NULL,NULL,NULL,NULL,0),(1003,'微信图片_20191016102740.jpg',1440,387499,'/image/f376e1abd34d415ebc5cf65d2290e9672764425024612853898.jpg',1080,NULL,NULL,NULL,NULL,0),(1004,'微信图片_20191016102740.jpg',1440,387499,'/image/6b15d1cdd23b4c9ba98048d1faacd94a4698219006760022724.jpg',1080,NULL,NULL,NULL,NULL,0),(1005,'微信图片_20191016102740.jpg',1440,387499,'/image/465da83e5956423daae470e9b9ca0f807610886874813292253.jpg',1080,NULL,NULL,NULL,NULL,0),(1006,'微信图片_20191016102740.jpg',1440,387499,'/image/b5ffcab5d5a0406987616ee769a2c33e6396305785406619857.jpg',1080,NULL,NULL,NULL,NULL,0),(1007,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/4e16b0f5952347e6bb36da2c659c28267011919115221071421.jpg',1080,NULL,NULL,NULL,NULL,0),(1008,'微信图片_20191016102740.jpg',1440,387499,'/image/185c6bea7b1445ada716e91c5ff237f33490378263670339893.jpg',1080,NULL,NULL,NULL,NULL,0),(1009,'微信图片_20191016102740.jpg',1440,387499,'/image/ea4810cdccc84ae19d28dc9a6d8cf69b2972119835353997720.jpg',1080,NULL,NULL,NULL,NULL,0),(1010,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/064ce41a555c4278a32134ca9a49045d6919823815506232421.jpg',1080,NULL,NULL,NULL,NULL,0),(1011,'微信图片_20191016102740.jpg',1440,387499,'/image/23dbdef0897249f99fdd7a006b420f178177976706422951470.jpg',1080,NULL,NULL,NULL,NULL,0),(1012,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/7a631d02124a46898ae64f25d40e61364094078256942193288.jpg',1080,NULL,NULL,NULL,NULL,0),(1013,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/73cd91f829954854ae03ad351695007f2776688447815482140.jpg',1080,NULL,NULL,NULL,NULL,0),(1014,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/a93d06dd3250456187984b91b626c6d85161338799662798617.jpg',1080,NULL,NULL,NULL,NULL,0),(1015,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/bc44d2b845064b9ab4d9ba2287dd316c6862361278434534631.jpg',1080,NULL,NULL,NULL,NULL,0),(1016,'微信图片_20191016102740.jpg',1440,387499,'/image/4d3e17cb8764420b8c48f1b327f3881e4564335474125944556.jpg',1080,NULL,NULL,NULL,NULL,0),(1017,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/db62c8bac47146cea29cb06ea284e1aa736466219899157033.jpg',1080,NULL,NULL,NULL,NULL,0),(1018,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/7d3051fde188460ea6859bdd0b80d6912022039001010380384.jpg',1080,NULL,NULL,NULL,NULL,0),(1019,'微信图片_20191016102740.jpg',1440,387499,'/image/a958576cbe1545fba3348d98ce9312913731477420829371783.jpg',1080,NULL,NULL,NULL,NULL,0),(1020,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d0aee5d189714a7c9d25ff0a40aa2ac73304680871567309553.jpg',1080,NULL,NULL,NULL,NULL,0),(1021,'微信图片_20191016102740.jpg',1440,387499,'/image/a6e7227810a7485b8247b8319b8dcf542464536192606119382.jpg',1080,NULL,NULL,NULL,NULL,0),(1022,'微信图片_20191016102740.jpg',1440,387499,'/image/c45c8cc8fa85418a86aec5963c5b06d45303321461036117681.jpg',1080,NULL,NULL,NULL,NULL,0),(1023,'微信图片_20191016102740.jpg',1440,387499,'/image/0dd9495b5b344781abad6f67b942e36e6818674899712298886.jpg',1080,NULL,NULL,NULL,NULL,0),(1024,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/1628aae82fe84c4a93ec8963a666e0a67679494084594973633.jpg',1080,NULL,NULL,NULL,NULL,0),(1025,'微信图片_20191016102740.jpg',1440,387499,'/image/89d035da842d45faa2d2d94ea7567ba65405060965441568532.jpg',1080,NULL,NULL,NULL,NULL,0),(1026,'微信图片_20191016102809.jpg',809,178640,'/image/cdf3446f758c48a89f4770bfb7b878d78681517260677853835.jpg',1080,NULL,NULL,NULL,NULL,0),(1027,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d4df3176cf474136acf4d5a1afe0a3b93707278606045739279.jpg',1080,NULL,NULL,NULL,NULL,0),(1028,'微信图片_20191016102809.jpg',809,178640,'/image/9869ef9c28864e52b8db7f8e81a3621a8393588435231606022.jpg',1080,NULL,NULL,NULL,NULL,0),(1029,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/73518f27aa65464db9015ac5685e57404957701982895564291.jpg',1080,NULL,NULL,NULL,NULL,0),(1030,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d895c1af445f444cbff05cec8602bdbc5385715252885059728.jpg',1080,NULL,NULL,NULL,NULL,0),(1031,'微信图片_20191016102809.jpg',809,178640,'/image/f2b878ddc2b44962a61957be77b9e5538137477115762453049.jpg',1080,NULL,NULL,NULL,NULL,0),(1032,'微信图片_20191016102809.jpg',809,178640,'/image/32263e546e3b42038a456675129a2e8b3365032386642489426.jpg',1080,NULL,NULL,NULL,NULL,0),(1033,'微信图片_20191016102809.jpg',809,178640,'/image/ba2a87488cf5460080e6cf17094edb351757011106572173749.jpg',1080,NULL,NULL,NULL,NULL,0),(1034,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/9fb231393b474d658559443a494e337e6786923612763393290.jpg',1080,NULL,NULL,NULL,NULL,0),(1035,'微信图片_20191016102809.jpg',809,178640,'/image/59346c7102994c058dbd8cf06a3b77e47660659379730501871.jpg',1080,NULL,NULL,NULL,NULL,0),(1036,'微信图片_20191016102740.jpg',1440,387499,'/image/0b67efa0a7dc4e72ac60e145df6407ae6559581968856274755.jpg',1080,NULL,NULL,NULL,NULL,0),(1037,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/e8788d25faca4d979cb8198962e6ce345393372695155843341.jpg',1080,NULL,NULL,NULL,NULL,0),(1038,'微信图片_20191016102740.jpg',1440,387499,'/image/0775bd3e7153434da294aa982d1a3e2b6809935471128139295.jpg',1080,NULL,NULL,NULL,NULL,0),(1039,'微信图片_20191016102809.jpg',809,178640,'/image/1983e176d6f14d31adc03dc44b84c3c73687969325085367984.jpg',1080,NULL,NULL,NULL,NULL,0),(1040,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/8c82e7bad12b4a0fac0e8256a082d0bd8448098879841720521.jpg',1080,NULL,NULL,NULL,NULL,0),(1041,'微信图片_20191016102740.jpg',1440,387499,'/image/2f91bf1e8a174301854fe4b7595b0910510536475862945210.jpg',1080,NULL,NULL,NULL,NULL,0),(1042,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/c740c13175264f94836212b2f9ac65fe2752372771503369338.jpg',1080,NULL,NULL,NULL,NULL,0),(1043,'微信图片_20191016102740.jpg',1440,387499,'/image/973bfe16e2ba41128c43b5735405a0987400666223526465846.jpg',1080,NULL,NULL,NULL,NULL,0),(1044,'微信图片_20191016102809.jpg',809,178640,'/image/494bbcd94d7d4275a6bbab555c4f9bd66347819540350109301.jpg',1080,NULL,NULL,NULL,NULL,0),(1045,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/54bd40fdc0e44777a5e61f265ae621096311941792447327305.jpg',1080,NULL,NULL,NULL,NULL,0),(1046,'微信图片_20191016102809.jpg',809,178640,'/image/39d8cc3bcf6f42a0a9442469ada734d9249768420457867835.jpg',1080,NULL,NULL,NULL,NULL,0),(1047,'微信图片_20191016102740.jpg',1440,387499,'/image/9aaaaad1668a4e07adacac40ea319b328510566147823355716.jpg',1080,NULL,NULL,NULL,NULL,0),(1048,'微信图片_20191016102740.jpg',1440,387499,'/image/828c41549e35430f992b8a6e6440b41f661037105785032546.jpg',1080,NULL,NULL,NULL,NULL,0),(1049,'微信图片_20191016102809.jpg',809,178640,'/image/3ef303d91c1548fe8f1172bb56339f647110894656080195489.jpg',1080,NULL,NULL,NULL,NULL,0),(1050,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/27e61e7a6257439cb5317f5709e22df57835237874801709060.jpg',1080,NULL,NULL,NULL,NULL,0),(1051,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/30ca069070b742d1ba9e3f21ddd7720b5409399089488759696.jpg',1080,NULL,NULL,NULL,NULL,0),(1052,'微信图片_20191016102809.jpg',809,178640,'/image/5cf74ef96784469ba61bbf81befc5f245699945137654248552.jpg',1080,NULL,NULL,NULL,NULL,0),(1053,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/bd4de32b794645d5b6aed45346f8c20d8441657881967027596.jpg',1080,NULL,NULL,NULL,NULL,0),(1054,'微信图片_20191016102740.jpg',1440,387499,'/image/96cb9f2974e44280b612f031ee5adc2b3916633062526333017.jpg',1080,NULL,NULL,NULL,NULL,0),(1055,'微信图片_20191016102740.jpg',1440,387499,'/image/58f504e4924043b58db2229c4a2400466297006474217564895.jpg',1080,NULL,NULL,NULL,NULL,0),(1056,'微信图片_20191016102740.jpg',1440,387499,'/image/df0bb9632b2b4f12b77c3a58a6724f3e1613157098230008322.jpg',1080,NULL,NULL,NULL,NULL,0),(1057,'微信图片_20191016102740.jpg',1440,387499,'/image/7d5af389543e4431aa506b259de183a27165132270005969238.jpg',1080,NULL,NULL,NULL,NULL,0),(1058,'微信图片_20191016102809.jpg',809,178640,'/image/0abcfb777acd459e8199d0594e196a222021521429621294189.jpg',1080,NULL,NULL,NULL,NULL,0),(1059,'微信图片_20191016102740.jpg',1440,387499,'/image/8f62a2c0b5dc47128fff73ca9bf2c9f21924100492041327173.jpg',1080,NULL,NULL,NULL,NULL,0),(1060,'微信图片_20191016102740.jpg',1440,387499,'/image/b0da10d831fd48a2834f0d0c7c8b18797111864285923478514.jpg',1080,NULL,NULL,NULL,NULL,0),(1061,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/56fec0ed20054880a97ac1e8eac418ef3411384869349425551.jpg',1080,NULL,NULL,NULL,NULL,0),(1062,'微信图片_20191016102740.jpg',1440,387499,'/image/e49264f4a3f8414bb21c47c319a9f9847435618068576445996.jpg',1080,NULL,NULL,NULL,NULL,0),(1063,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/a08e73c96dbf4dadbccba4b7797e254f4538106742471746184.jpg',1080,NULL,NULL,NULL,NULL,0),(1064,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/91c5de9f18284284b11f24e6d23191b47095841129167810924.jpg',1080,NULL,NULL,NULL,NULL,0),(1065,'微信图片_20191016102740.jpg',1440,387499,'/image/cdef778b54254013a7dab9902d0afdbb2894714349729571263.jpg',1080,NULL,NULL,NULL,NULL,0),(1066,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/84f31f756a80409394f05632241e16792409763899686167042.jpg',1080,NULL,NULL,NULL,NULL,0),(1067,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/500981fd91184549af9f00c3a218278e2887049003738332607.jpg',1080,NULL,NULL,NULL,NULL,0),(1068,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/b1345b86fa6541faab11cb7056d98e8f2795699270942652488.jpg',1080,NULL,NULL,NULL,NULL,0),(1069,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/5fcce7e380a048c9abfdda1df32b21b04747645836071839437.jpg',1080,NULL,NULL,NULL,NULL,0),(1070,'微信图片_20191016102740.jpg',1440,387499,'/image/f4b95f49139f45adb8cf8f91c28936791703886288443252664.jpg',1080,NULL,NULL,NULL,NULL,0),(1071,'微信图片_20191016102740.jpg',1440,387499,'/image/b14f19563ee64ba4a772b97a0fa79a511992066070216530893.jpg',1080,NULL,NULL,NULL,NULL,0),(1072,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d7aebb221c3a49e9b027d98b8d8ec4d59029050213623636539.jpg',1080,NULL,NULL,NULL,NULL,0),(1073,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/f116a61675d346eeb11148245037999b4765215632617001781.jpg',1080,NULL,NULL,NULL,NULL,0),(1074,'微信图片_20191016102740.jpg',1440,387499,'/image/c4ff5701b62d49c28cc84c67d8e7ba979164590358798826929.jpg',1080,NULL,NULL,NULL,NULL,0),(1075,'微信图片_20191016102740.jpg',1440,387499,'/image/2966db7aa78a4178b64eb9c54b3128863064998340744185483.jpg',1080,NULL,NULL,NULL,NULL,0),(1076,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/1cf8b39eed2d4a8dadc10c8773b6f75f7445478306188568587.jpg',1080,NULL,NULL,NULL,NULL,0),(1077,'微信图片_20191016102740.jpg',1440,387499,'/image/dec9f72de708426b9cb5f502b4c720937918650486003952345.jpg',1080,NULL,NULL,NULL,NULL,0),(1078,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/f8ca25653742461db4c9014bef4197eb8078841554367196121.jpg',1080,NULL,NULL,NULL,NULL,0),(1079,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/498be69570104155a83808f3483cc2435075539363816450082.jpg',1080,NULL,NULL,NULL,NULL,0),(1080,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/dafcbbf862824d8c8f7fc73f049836186739168021665072098.jpg',1080,NULL,NULL,NULL,NULL,0),(1081,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/258034de3b03426eade24c5c2cb28432408196058578558667.jpg',1080,NULL,NULL,NULL,NULL,0),(1082,'微信图片_20191016102740.jpg',1440,387499,'/image/a5ee5cb8603148d68007f6fab4c55e9c6672521542099753682.jpg',1080,NULL,NULL,NULL,NULL,0),(1083,'微信图片_20191016102809.jpg',809,178640,'/image/728604e0f51a4ab49e74e6b0a004add11255469456553150678.jpg',1080,NULL,NULL,NULL,NULL,0),(1084,'微信图片_20191016102740.jpg',1440,387499,'/image/dcf713f65ed449968ba0c17e71e002395105393428452170797.jpg',1080,NULL,NULL,NULL,NULL,0),(1085,'m200103ko3ngni170ah2ui4khn3a51kq_0005.jpg',1080,213906,'/image/b02aa00e84bc4bd5a66a4921b4e412467012234305785142196.jpg',1920,NULL,NULL,NULL,NULL,0),(1086,'m200103ko3ngni170ah2ui4khn3a51kq_0005 (2).jpg',1055,58644,'/image/2b6e3dab4b844ab78d8a8d89efb140e66388984739650019410.jpg',687,NULL,NULL,NULL,NULL,0),(1087,'abcde.jpg',335,59955,'/image/25a62b0655b04062991e1122456755da3815099716010705783.jpg',569,NULL,NULL,NULL,NULL,0),(1088,'abcde.jpg',335,59955,'/image/ec888d805b5e434e92d89198e4048bd58637720224038232730.jpg',569,NULL,NULL,NULL,NULL,0),(1089,'abcde.jpg',335,59955,'/image/383626eb7a884cbcab5fbea4014c98e28137721211018841973.jpg',569,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_des` varchar(100) NOT NULL COMMENT '角色描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`role_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1000,'visitor','未登录,游客','2019-12-29 19:31:01','lidihao','2019-12-29 19:31:13','lidihao',_binary '\0'),(1001,'admin','管理员，复制系统的管理','2019-12-29 19:35:55','lidihao','2019-12-29 19:36:03','lidihao',_binary '\0'),(1002,'user','普通用户','2019-12-29 19:39:52','lidihao','2019-12-29 19:40:07','lidihao',_binary '\0');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT 'role_id',
  `menu_id` int(11) NOT NULL COMMENT 'menu_id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`role_menu_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8 COMMENT='角色-菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1000,1000,1000,'2019-12-29 19:49:05','lidihao','2019-12-29 19:49:10','lidihao',_binary '\0'),(1001,1000,1001,'2019-12-29 19:50:47','lidihao','2019-12-29 19:50:54','lidihao',_binary '\0'),(1002,1002,1000,'2019-12-29 21:38:57','lidihao','2019-12-29 21:39:03','lidihao',_binary '\0'),(1003,1002,1001,'2019-12-29 21:40:49','lidihao','2019-12-29 21:41:09','lidihao',_binary '\0'),(1004,1002,1002,'2019-12-29 21:40:51','lidihao','2019-12-29 21:41:11','lidihao',_binary '\0'),(1005,1002,1003,'2019-12-29 21:40:51','lidihao','2019-12-29 21:41:12','lidihao',_binary '\0'),(1006,1002,1004,'2019-12-29 21:40:52','lidihao','2019-12-29 21:41:12','lidihao',_binary '\0'),(1007,1002,1005,'2019-12-29 21:41:43','lidihao','2019-12-29 21:41:48','lidihao',_binary '\0'),(1008,1001,1000,'2020-01-03 22:57:00','lidihao','2020-01-03 22:57:05','lidihao',_binary '\0'),(1009,1001,1001,'2020-01-03 22:57:30','lidihao','2020-01-03 22:57:37','lidihao',_binary '\0'),(1010,1001,1002,'2020-01-03 22:58:02','lidihao','2020-01-03 22:58:07','lidihao',_binary '\0'),(1011,1001,1003,'2020-01-03 22:58:30','lidihao','2020-01-03 22:58:33','lidihao',_binary '\0'),(1012,1001,1004,'2020-01-03 22:59:03','lidihao','2020-01-03 22:59:05','lidihao',_binary '\0'),(1013,1001,1005,'2020-01-03 22:59:04','lidihao','2020-01-03 22:59:06','lidihao',_binary '\0'),(1014,1001,1006,'2020-01-03 23:00:00','lidihao','2020-01-03 23:00:02','lidihao',_binary '\0'),(1015,1001,1007,'2020-01-03 23:00:01','lidihao','2020-01-03 23:00:04','lidihao',_binary '\0');
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `role_permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT 'role_id',
  `permission_id` int(11) NOT NULL COMMENT 'permission_id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`role_permission_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='role-url表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_name` varchar(100) NOT NULL COMMENT '标签名称',
  `tag_desc` varchar(200) NOT NULL COMMENT '标签描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`tag_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='视频标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1000,'linux','linux','2020-01-02 21:00:13','lidihao','2020-01-02 21:00:19','liidhao',0),(1001,'猛男必看','猛男必看','2020-01-02 21:00:48','lidihao','2020-01-02 21:00:54','lidihao',0);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_video_approval`
--

DROP TABLE IF EXISTS `tag_video_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag_video_approval` (
  `tag_video_approval` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `video_approval_id` int(11) NOT NULL COMMENT '视频审批id',
  PRIMARY KEY (`tag_video_approval`)
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8 COMMENT='标签与视频申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_video_approval`
--

LOCK TABLES `tag_video_approval` WRITE;
/*!40000 ALTER TABLE `tag_video_approval` DISABLE KEYS */;
INSERT INTO `tag_video_approval` VALUES (1006,1000,1004),(1007,1001,1004),(1008,1000,1005),(1009,1001,1005),(1010,1000,1006),(1011,1001,1006),(1012,1000,1007),(1013,1001,1007),(1014,1000,1008),(1015,1001,1008),(1016,1000,1009),(1017,1001,1009),(1018,1000,1010),(1019,1001,1010),(1020,1000,1011),(1021,1001,1011),(1022,1000,1012),(1023,1001,1012),(1024,1000,1013),(1025,1001,1013),(1026,1000,1014),(1027,1001,1014);
/*!40000 ALTER TABLE `tag_video_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `password` char(128) NOT NULL COMMENT '密码',
  `salt` char(128) NOT NULL COMMENT '盐值',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `introduce` varchar(200) NOT NULL DEFAULT '' COMMENT '简介',
  `avatar_pic_id` int(11) NOT NULL DEFAULT '1000' COMMENT '头像',
  `has_active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活账号',
  `last_password_reset_date` datetime DEFAULT NULL COMMENT '密码修改时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `mail` (`email`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1013 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1012,'lidihao','68f15af1da571718b86a3407b563af5a','ba8412810fa83de8c190b122e1bd19b6','18302026070@163.com','',1000,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT 'user_id',
  `role_id` int(11) NOT NULL COMMENT 'role_id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`user_role_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1000,1012,1001,'2019-12-29 21:43:07','lidihao','2019-12-29 21:43:09','lidihao',_binary '\0');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_approval`
--

DROP TABLE IF EXISTS `video_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_approval` (
  `video_approval_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `upload_user_id` int(11) NOT NULL COMMENT '上传人id',
  `poster_id` int(11) NOT NULL COMMENT '视频posterURL',
  `title` varchar(100) NOT NULL COMMENT '视频题目审批',
  `introduce` varchar(200) NOT NULL COMMENT '视频简介',
  `category_id` int(11) NOT NULL COMMENT '视频类别',
  `video_id` int(11) NOT NULL DEFAULT '-1' COMMENT '对应的视频id',
  `approval_type` int(11) NOT NULL DEFAULT '0' COMMENT '审批状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`video_approval_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8 COMMENT='视频审批';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_approval`
--

LOCK TABLES `video_approval` WRITE;
/*!40000 ALTER TABLE `video_approval` DISABLE KEYS */;
INSERT INTO `video_approval` VALUES (1004,1012,1074,'测试','测试',1000,-1,0,NULL,NULL,NULL,NULL,0),(1005,1012,1075,'测试','cesg',1000,-1,0,NULL,NULL,NULL,NULL,0),(1006,1012,1076,'测试','测试',1000,-1,0,NULL,NULL,NULL,NULL,0),(1007,1012,1078,'测试','测试',1000,-1,0,NULL,NULL,NULL,NULL,0),(1008,1012,1079,'测试','测试',1000,-1,0,NULL,NULL,NULL,NULL,0),(1009,1012,1081,'测试','测试',1001,-1,0,NULL,NULL,NULL,NULL,0),(1010,1012,1082,'测试','彩色',1000,-1,0,NULL,NULL,NULL,NULL,0),(1011,1012,1083,'测试','测试',1000,-1,0,NULL,NULL,NULL,NULL,0),(1012,1012,1084,'测试','测试',1001,-1,0,NULL,NULL,NULL,NULL,0),(1013,1012,1088,'測試','測試',1000,-1,0,'2020-01-04 13:31:29','lidihao','2020-01-04 13:31:29','lidihao',0),(1014,1012,1089,'測試測試彩色獨立開發接口的角度看看積分看了電視劇發d','成都市發動機瘋狂的JFK了',1001,-1,0,'2020-01-04 13:32:43','lidihao','2020-01-04 13:32:43','lidihao',0);
/*!40000 ALTER TABLE `video_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_file`
--

DROP TABLE IF EXISTS `video_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_file` (
  `video_file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uploader_id` int(11) NOT NULL COMMENT '主键',
  `video_apply_id` int(11) DEFAULT '-1' COMMENT '审批Id',
  `size` bigint(20) NOT NULL COMMENT '大小',
  `file_name` varchar(100) NOT NULL COMMENT '名字',
  `approval_type` int(11) NOT NULL DEFAULT '0' COMMENT '审批状态',
  `file_url` varchar(100) NOT NULL COMMENT '视频url',
  `file_type` varchar(50) NOT NULL COMMENT '视频类型',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `sort` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`video_file_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=utf8 COMMENT='上传的文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_file`
--

LOCK TABLES `video_file` WRITE;
/*!40000 ALTER TABLE `video_file` DISABLE KEYS */;
INSERT INTO `video_file` VALUES (1000,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/1ef3e223b0fc404cad4bd14f03b4ab2b1587826880892810079.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1001,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/e7618aa19f6a4cecbc409609f61d71fd2724269583185304697.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1002,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/8583004fafb14fe9a618e581f607ec236192878040708201977.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1003,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/689f74608b5d4923880dc6a0ac707b5a7032569203410733542.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1004,1012,-1,223815661,'1.a.mp4',0,'/video/4f3df523187f4606816231b8d781782f8258342605290404485.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1005,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/c4753a3044c047e185bb7f9dc858bbb74954359801330081265.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1006,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/e011f42669254f3886fa0e2745e77d081943294919079586626.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1007,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/3f239a2b13ac48e2b5982d98b5df192e2764384059170382554.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1008,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/8b36870e950a473aa7027ce06d7a4dc55489640183928177384.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1009,1012,-1,387499,'微信图片_20191016102740.jpg',0,'/video/0d7125f103974dfab848f22c43313c783396575372724347163.jpg','',NULL,NULL,NULL,NULL,0,0),(1010,1012,-1,480657,'16d85b36849549b0fa1a75b69c61d3e.jpg',0,'/video/1095b868bf444e8e88b2f24ea3c1ef9f5753295915833539345.jpg','',NULL,NULL,NULL,NULL,0,0),(1011,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/080154f8e0cc4c6c8326247c3369a3834946086930177294562.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1012,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/f613bcadd64e4a20870eb12ecd9be8af7550786963367099013.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1013,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/a1bd9ad1d8744ffabb052b6e97e300966237502200477972051.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1014,1012,-1,223815661,'1.a.mp4',0,'/video/b1f68e147b9845b88c1b22f42d05f2a55100124627363724370.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1015,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/ea1330815da443e6b708fa55d409c0777817277177807800748.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1016,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/85292ad398284f82849d53f5955da0ec5367443269959680107.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1017,1012,-1,223815661,'1.a.mp4',0,'/video/1fb203eca13348fc99523afd576d5cab85856210027671214.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1018,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/49cf514534414bbe9c5db7d77848f3714266671843317556223.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1019,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/cee265b3edd9451aa3fad399557d23db2621477316481076065.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1020,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/82cee9fa09304d47968a012172ff5b1f7814910189164530899.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1021,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/35e935e6df424ffd9c10c190126dce387901451327354064699.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1022,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/f4338dce78184bd390fa9f7d9aa625895250571426151534991.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1023,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/1ecdc1e06c3346f49d3b151c51a98d474799808623072658639.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1024,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/87b9568712e94ae5907c376b7555772c6127092884663786409.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1025,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/458ac495f2c0446cb23a59c638b1ab2f706093584554824328.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1026,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/addaf18ec18b4384bdac97e22f5779465754537536599553983.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1027,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/a3ccbf443c3549b4b2099de2a49905786541754534528136633.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1028,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/eb7a201ff572458cb3cae93ec76d2aef1146809798014536585.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1029,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/31f59e979ed747cca13d11a58ca898af4260317185341658675.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1030,1012,-1,223815661,'1.a.mp4',0,'/video/69a8227842334b23a86db439dd1d52d48547058453570744568.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1031,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/781c46e8f3154e0dbd9464bb23548bb65735936125792460995.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1032,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/2ebfdcd9df40458faa598cb85dfde7c11560725970274007949.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1033,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/8aa10522c76e44eda64f7e76f93bc8477701514023294956396.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1034,1012,-1,223815661,'1.a.mp4',0,'/video/b264b47a3ee0431db9d3523cc4068f393194260116413661874.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1035,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/ae1151bba0a44346b5acbab5ff59fb658874781817993689348.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1036,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/7d80012d713d4c85b42541363221d611849280981305855708.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1037,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/851dba34d6c64fd1ab046c4d6dabcf765551153661949557800.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1038,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/0f0fcf55baa54a6788fac002a63959c42275892633888169239.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1039,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/fe7e522037d14e88ae7f001e4d15079c6083661958371630385.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1040,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/e752b9dab2a7400cbcba1f1a1de521407154353964515041065.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1041,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/8928f2b99f9346cb862d1bbbf70476a06910197192625605027.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1042,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/c79cb06330714d18a143161c74f811375333336491599641829.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1043,1012,-1,223815661,'1.a.mp4',0,'/video/047f0cb8a9d04e90811816b07291a00d5835860611622849929.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1044,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/756a5748497648e9a598a8edd45e9a945873040986271148833.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1045,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/044b0f691039423ba67ba100e98945455595895272083816832.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1046,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/23d7a5064ea2446ba108726fe4ea4a7b6566736473545946318.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1047,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/9ea43092682e4810be852957569476002735206856418344790.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1048,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/cef927e358944344bf42b72172bd23447429337585358472924.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1049,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/743dd6b4f94643cf8dd6938552113b986602547540406329489.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1050,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/9ab88e340fac46aa824e1d281df879aa4627871418267174897.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1051,1012,-1,223815661,'1.a.mp4',0,'/video/2597150a63904a67a7c5276dd118d3b1321781133396293242.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1052,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/9a6a8df348fa411a92257c9e0aa9a3a44304424734100505339.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1053,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/716dea3228a04aa5b92d9e9efab63ef9420972080046577968.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1054,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/bb6cb70783434a55b7f528a57de13b9a1594564149511123369.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1055,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/99083a1d885c483ab2e6bc9e6c90d2e38073673628060527294.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1056,1012,-1,223815661,'1.a.mp4',0,'/video/29fd2a1530444551af9ef3b1b34d6b435163897819709570435.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1057,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/9e77e8afa9274d28a63e8d4e15e1f9eb6394724409564350299.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1058,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/7f50eb992278419292fd88ae508673ef5400562576928909212.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1059,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/a1330bad2ee14c21806d6930f978e16c3550595012606202725.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1060,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/5ac71fb29d174e90b7182b6a67ccb8802969218890557928781.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1061,1012,-1,223815661,'1.a.mp4',0,'/video/8fb389feed1547af84849831ac2786bd3616348434673175770.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1062,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/2d08f4069e7f4dc9ae260e4547e4aef91174227836607818184.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1063,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/71f1c5eb356f4d62b69e36debdf1bcbf210098701694412870.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1064,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/b7940ab813664ce0a51ca6e3e1ae02e33460576412432257499.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1065,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/0bed13cb13bc4061b504191292508a588420891102156325011.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1066,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/04b1ecb04c8c4444b45a96e85e1685ff5599627203799775999.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1067,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/245796fce5584218883fb84d376582996047036937152958535.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1068,1012,-1,223815661,'1.a.mp4',0,'/video/aa55041d9e1e48fcaf99e3898b1b5e7b3162078238758803012.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1069,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/8a2fa7d4be6340ba8cc996194e2a46d32265112840027703968.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1070,1012,-1,81176001,'5. 路由技术代码演示.mp4',0,'/video/d39b228011af4792a041853e85b002517026582651499754814.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1071,1012,-1,91629834,'6. 安全策略代码演示.mp4',0,'/video/f62b8c0988d540daa0bdaa77a6b36d887890577356854873362.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1072,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/261a8e6ef2e2481bb0d088681037ba8c4739716785960836533.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1073,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/e83c1eadfccd4cd69f1bc9dca4a98c977309250265663629070.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1074,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/748f8fcb3f6d46b690e4d1c41b3e9dcc3791332943381202798.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1075,1012,-1,223815661,'1.a.mp4',0,'/video/ed085d6486054238b1ccfed62bf16b9b8055199175510025218.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1076,1012,-1,202784065,'4. 交换技术代码演示.mp4',0,'/video/82a4c045538a4bb7bb6e0f241fe6cf022347819017852738715.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1077,1012,1004,202784065,'4. 交换技术代码演示.mp4',0,'/video/bef2b660e26c471ca39b7bb21258a36d491103019363527415.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1078,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/c0100b7e53bb45ec8b347b98186d45324126135493623632570.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1079,1012,-1,223815661,'1.a.mp4',0,'/video/f62c163431464e0385e25e344f1546705963581206227237236.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1080,1012,1005,202784065,'4. 交换技术代码演示.mp4',0,'/video/b0d30a525d72456fa79833f0736e5ef09136508748675342365.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,1),(1081,1012,1005,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/2306a2c64e56436698b44237aa49fcc31187253445205894599.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1082,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/e1bb7d72bf424586ad79f84c2452ad604868020504604833762.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1083,1012,-1,223815661,'1.a.mp4',0,'/video/05c582bb87514e8e84f04750fe6c09ec6345596853477639714.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1084,1012,1006,202784065,'4. 交换技术代码演示.mp4',0,'/video/e1dfbbee9d934c818ea9b82869a6e0b41983811656547452969.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1085,1012,1006,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/4e4640ceb3684bc1ab31c4576d8633705336453464833161626.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,3),(1086,1012,1006,91629834,'6. 安全策略代码演示.mp4',0,'/video/74b9772eda5d43cfae3607d9875d9fd06972858268371481740.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,5),(1087,1012,1006,81176001,'5. 路由技术代码演示.mp4',0,'/video/5c33cfdd13fd4d348cfe1de089c0afca7475723313003570982.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,4),(1088,1012,1006,188894392,'3. 设备管理代码演示.mp4',0,'/video/6fc0d6b00c5540e296f01bb365300f183417776839989630584.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,1),(1089,1012,1006,223815661,'1.a.mp4',0,'/video/b00aeeebf8764380b36640a989c021737150788167513894086.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,2),(1090,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/3c43ac02065f4351b00e9a6b9f22628974830294300962880.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1091,1012,1007,188894392,'3. 设备管理代码演示.mp4',0,'/video/7fe12395e60443f1abb73fabaada83b53117095793789102358.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1092,1012,1008,188894392,'3. 设备管理代码演示.mp4',0,'/video/e6bd0eae268843c4bf1cf4d52401bd032181648222460106063.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1093,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/59341e9080b041ae8a38234c432d55849139375366266470150.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1094,1012,1009,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/46a322b78e434a24aa544ac8f3f461547645348439531250845.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1095,1012,1010,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/25cc2a0a0b614f1a9caf5871e59dbf901341046057236986385.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1096,1012,1011,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/7d861a9e87d5425888039f62afd819c28387528013443875639.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1097,1012,1011,188894392,'3. 设备管理代码演示.mp4',0,'/video/9c331aa1b24c4438bdd023d688bea7b31853031031271400063.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,1),(1098,1012,1012,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/1e9d93f4d95d415d8396acedaff212425041855822955999915.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,1),(1099,1012,1012,81176001,'5. 路由技术代码演示.mp4',0,'/video/0cdf64b022334848989cd81ca47bc2ea3166385604685678553.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,3),(1100,1012,1012,91629834,'6. 安全策略代码演示.mp4',0,'/video/5150dd6e7596406dbfedb7dca0119cfb977215794046936700.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,4),(1101,1012,1012,223815661,'1.a.mp4',0,'/video/378e9ed73d894231a9a477260c892a717380021263200308229.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1102,1012,1012,202784065,'4. 交换技术代码演示.mp4',0,'/video/a0eaab602e834acba76f3a6afe2627305405915746823417255.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,2),(1103,1012,-1,129117179,'2. 网络工程实训拓扑搭建.mp4',0,'/video/48dd96080d0847388f832d76b472510c757603619125239008.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1104,1012,-1,223815661,'1.a.mp4',0,'/video/cc8913af786c4c148eb5765c0524a2d33061428878476060153.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1105,1012,-1,188894392,'3. 设备管理代码演示.mp4',0,'/video/8a0729deab484ba2b851275cf5ffdb966991779625042236345.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1106,1012,1013,188894392,'3. 设备管理代码演示.mp4',0,'/video/fca2364b42c24dc889fafe0a86eefb5e2210365970564787080.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1107,1012,1013,81176001,'5. 路由技术代码演示.mp4',0,'/video/932cf5b7688c47f39ef2fbf1814f1ae67700994952760133551.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,2),(1108,1012,1013,202784065,'4. 交换技术代码演示.mp4',0,'/video/be1aa3970d7d4f82b37c2e6a82e6dfb78730094832978869011.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,1),(1109,1012,1014,81176001,'5. 路由技术代码演示.mp4',0,'/video/b2e33a40708d478c8bc76b611f7922801110983036921714132.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,1),(1110,1012,1014,202784065,'4. 交换技术代码演示.mp4',0,'/video/c32004267531427195155e54ccc734837015942175642898921.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,0),(1111,1012,1014,91629834,'6. 安全策略代码演示.mp4',0,'/video/e059910924744040b1391496a1f3fda67722141342719837827.mp4','video/mp4\n',NULL,NULL,NULL,NULL,0,2);
/*!40000 ALTER TABLE `video_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-05 21:28:18
