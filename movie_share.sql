-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: movie_share
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_FIRED_TRIGGERS`
--

LOCK TABLES `QRTZ_FIRED_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
INSERT INTO `QRTZ_LOCKS` VALUES ('ClusterQuartz','STATE_ACCESS'),('ClusterQuartz','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('ClusterQuartz','lidihao-Inspiron-34211584538691024',1584538838728,3000);
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

LOCK TABLES `QRTZ_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='视频类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1000,'动漫','动漫','2020-01-02 20:59:17','lidihao','2020-01-02 20:59:23','lidihao',0),(1001,'科技','科技','2020-01-02 20:59:46','lidihao','2020-01-02 20:59:51','lidihao',0),(1002,'动作片','动作片','2020-03-10 17:16:47','lidihao','2020-03-10 17:16:34','lidihao',0),(1003,'电视剧','电视剧','2020-03-10 23:57:21',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_reply`
--

DROP TABLE IF EXISTS `comment_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `reply_content` text NOT NULL COMMENT '评论内容',
  `reply_up` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `reply_down` int(11) NOT NULL DEFAULT '0' COMMENT '踩的人数',
  `reply_user_id` int(11) NOT NULL COMMENT '评论人',
  `video_comment_id` int(11) NOT NULL COMMENT '视频id',
  `reply_to_id` int(11) NOT NULL DEFAULT '-1',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`reply_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1073 DEFAULT CHARSET=utf8 COMMENT='视频评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_reply`
--

LOCK TABLES `comment_reply` WRITE;
/*!40000 ALTER TABLE `comment_reply` DISABLE KEYS */;
INSERT INTO `comment_reply` VALUES (1000,'测试',0,0,1012,1000,-1,NULL,NULL,NULL,NULL,0),(1001,'测试ddfs fdfdfdsf dfdsf d',0,0,1012,1000,-1,'2020-02-28 14:17:02','lidihao','2020-02-28 14:17:02','lidihao',0),(1002,'测试ddfs fdfdfdsf dfdsf ddfd fd f',0,0,1012,1000,-1,'2020-02-28 14:17:14','lidihao','2020-02-28 14:17:14','lidihao',0),(1003,'kjkdfj lkdfjdlk kjkfjkd fisfj lkfjsdl jfjkdddddddddddddddddddddddddddddddddddddddlsssssf',0,0,1012,1000,1001,'2020-02-29 04:04:00','lidihao','2020-02-29 04:04:00','lidihao',0),(1004,'dfis jdkfjksdsjfksdj idfjdkkf\n',0,0,1012,1000,1001,'2020-02-29 04:06:03','lidihao','2020-02-29 04:06:03','lidihao',0),(1005,'dfis jdkfjksdsjfksdj idfjdkkf\ndfdsf dsf ddsf ',0,0,1012,1000,1001,'2020-02-29 04:06:17','lidihao','2020-02-29 04:06:17','lidihao',0),(1006,'dfsdf sdfdf sd',0,0,1012,1000,1002,'2020-02-29 04:07:00','lidihao','2020-02-29 04:07:00','lidihao',0),(1007,'dfsdfsdfsdfddfdccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc',0,0,1012,1000,1003,'2020-02-29 04:07:42','lidihao','2020-02-29 04:07:42','lidihao',0),(1008,'sdsadsadsdsad',0,0,1012,1000,1007,'2020-02-29 04:08:45','lidihao','2020-02-29 04:08:45','lidihao',0),(1009,'rewriueroieurieuriereuirueiurieruuuuuuuuuuuuuuuiruwijdifjeirie',0,0,1012,1000,1005,'2020-02-29 04:11:50','lidihao','2020-02-29 04:11:50','lidihao',0),(1010,'fdfdf',0,0,1012,1000,1009,'2020-02-29 04:12:02','lidihao','2020-02-29 04:12:02','lidihao',0),(1011,'dfdfisdjfk \n',0,0,1012,1000,1010,'2020-02-29 04:17:09','lidihao','2020-02-29 04:17:09','lidihao',0),(1012,'上周，国内比较知名的的资源软件分享论坛——吾爱破解（52pojie）突然宣布要暂时关闭网站进行调整，原因与版权等问题有关，官方称为加强版权保护方面的力度，取缔侵权内容，以保证技术交流氛围，因此关站调整，不过没有给出恢复运营的具体时间。\n\n\n此消息一出便引发众多网友的关注。\n\n微博用户@MrZodiac感叹：吾爱破解论坛的关闭，说实话心里还是挺难受的，这是我浏览频率前三的网站，在里面可以很轻松地找到各种软件的破解版，和之前胖鸟电影被封一样，这标志着一个互联网共享（盗版）时代的结束了，要使用软件不得不去给软件开发公司交钱，要找一部电影只能去忍受爱奇艺们的广告，付费，还有删减阉割......\n\n\n不过昨日下午，吾爱宣布网站又突然宣布恢复运营。\n\n按照公告中说法，吾爱完全关闭了“影视推荐区”，对“原创发布区”“精品软件区”涉版权内容进行移除，还对“脱壳破解区”、“移动安全区”、“动画发布区”等技术版块的技术文章给予保留，但部分帖子进行了“数据脱敏”处理，编辑了部分关键名称。\n\n此次，吾爱破解还宣布增加新规，包括禁止发布任何可能存在商业侵权内容，包括但不限于影视资源、原创或者转载商业软件的破解成品、破解补丁、注册机等。\n\n另外，对于技术交流文章，可以进行“数据脱敏”处理，将关键名称使用×取代，不要涉及商业侵权内容，不要提供成品下载。\n\n对此，网友对该论坛的整改重新上线产生了争议，有人认为其涉及版权内容全部整改，注册机和成品均不能下载，即便复活了也没有什么作用了。不过也有人认为，随着国人版权意识的提高、制度规范的跟进，这类不重视版权的网站论坛应该关停。当然也不乏诸多建议吾爱破解论坛改个名字的网友，你认为呢？',0,0,1012,1000,-1,'2020-02-29 04:26:53','lidihao','2020-02-29 04:26:53','lidihao',0),(1013,'上周，国内比较知名的的资源软件分享论坛——吾爱破解（52pojie）突然宣布要暂时关闭网站进行调整，原因与版权等问题有关，官方称为加强版权保护方面的力度，取缔侵权内容，以保证技术交流氛围，因此关站调整，不过没有给出恢复运营的具体时间。\n\n\n此消息一出便引发众多网友的关注。\n\n微博用户@MrZodiac感叹：吾爱破解论坛的关闭，说实话心里还是挺难受的，这是我浏览频率前三的网站，在里面可以很轻松地找到各种软件的破解版，和之前胖鸟电影被封一样，这标志着一个互联网共享（盗版）时代的结束了，要使用软件不得不去给软件开发公司交钱，要找一部电影只能去忍受爱奇艺们的广告，付费，还有删减阉割......\n\n\n不过昨日下午，吾爱宣布网站又突然宣布恢复运营。\n\n按照公告中说法，吾爱完全关闭了“影视推荐区”，对“原创发布区”“精品软件区”涉版权内容进行移除，还对“脱壳破解区”、“移动安全区”、“动画发布区”等技术版块的技术文章给予保留，但部分帖子进行了“数据脱敏”处理，编辑了部分关键名称。\n\n此次，吾爱破解还宣布增加新规，包括禁止发布任何可能存在商业侵权内容，包括但不限于影视资源、原创或者转载商业软件的破解成品、破解补丁、注册机等。\n\n另外，对于技术交流文章，可以进行“数据脱敏”处理，将关键名称使用×取代，不要涉及商业侵权内容，不要提供成品下载。\n\n对此，网友对该论坛的整改重新上线产生了争议，有人认为其涉及版权内容全部整改，注册机和成品均不能下载，即便复活了也没有什么作用了。不过也有人认为，随着国人版权意识的提高、制度规范的跟进，这类不重视版权的网站论坛应该关停。当然也不乏诸多建议吾爱破解论坛改个名字的网友，你认为呢？\n\n',0,0,1012,1000,1012,'2020-02-29 04:27:40','lidihao','2020-02-29 04:27:40','lidihao',0),(1014,'上周',0,0,1012,1000,1013,'2020-02-29 04:28:11','lidihao','2020-02-29 04:28:11','lidihao',0),(1015,'dfdkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkdfd',0,0,1012,1000,1007,'2020-02-29 04:34:41','lidihao','2020-02-29 04:34:41','lidihao',0),(1016,'dfdsfs ddfs d ',0,0,1012,1003,-1,'2020-02-29 06:30:01','lidihao','2020-02-29 06:30:01','lidihao',0),(1017,'dfdsfs ddfs d dfdsf ',0,0,1012,1003,-1,'2020-02-29 06:30:33','lidihao','2020-02-29 06:30:33','lidihao',0),(1018,'dfdsfs ddfs d dfdsf dfdf',0,0,1012,1003,-1,'2020-02-29 06:30:52','lidihao','2020-02-29 06:30:52','lidihao',0),(1019,'erer e rer ',0,0,1012,1007,-1,'2020-02-29 06:38:31','lidihao','2020-02-29 06:38:31','lidihao',0),(1020,'ererer',0,0,1012,1008,-1,'2020-02-29 06:38:41','lidihao','2020-02-29 06:38:41','lidihao',0),(1021,'erewwrwerw ',0,0,1012,1009,-1,'2020-02-29 06:40:00','lidihao','2020-02-29 06:40:00','lidihao',0),(1022,'erferererere   df',0,0,1012,1008,1020,'2020-02-29 06:41:04','lidihao','2020-02-29 06:41:04','lidihao',0),(1023,'dfdsfdsf ',0,0,1012,1010,-1,'2020-02-29 06:41:10','lidihao','2020-02-29 06:41:10','lidihao',0),(1024,'dfdsfdsf ssdsr',0,0,1012,1010,-1,'2020-02-29 06:41:19','lidihao','2020-02-29 06:41:19','lidihao',0),(1025,'dfsdrewr',0,0,1012,1010,-1,'2020-02-29 06:43:06','lidihao','2020-02-29 06:43:06','lidihao',0),(1026,'fdfd fdsf',0,0,1012,1010,-1,'2020-02-29 06:43:15','lidihao','2020-02-29 06:43:15','lidihao',0),(1027,'dfsdf df4444444',0,0,1012,1010,1024,'2020-02-29 06:45:02','lidihao','2020-02-29 06:45:02','lidihao',0),(1028,'45454trtret rtrt',0,0,1012,1010,1027,'2020-02-29 06:45:15','lidihao','2020-02-29 06:45:15','lidihao',0),(1029,'rtrtrtrt',0,0,1012,1010,1028,'2020-02-29 06:45:25','lidihao','2020-02-29 06:45:25','lidihao',0),(1030,'fdfd fdsfrtrtrt',0,0,1012,1010,-1,'2020-02-29 06:45:29','lidihao','2020-02-29 06:45:29','lidihao',0),(1031,'trtr3',0,0,1012,1010,1029,'2020-02-29 06:45:35','lidihao','2020-02-29 06:45:35','lidihao',0),(1032,'rerer',0,0,1012,1010,-1,'2020-02-29 06:46:53','lidihao','2020-02-29 06:46:53','lidihao',0),(1033,'rerererer',0,0,1012,1010,-1,'2020-02-29 06:46:58','lidihao','2020-02-29 06:46:58','lidihao',0),(1034,'dfsdfdsfsdf',0,0,1012,1013,-1,'2020-02-29 06:47:32','lidihao','2020-02-29 06:47:32','lidihao',0),(1035,'fewrerer',0,0,1012,1015,-1,'2020-02-29 06:51:48','lidihao','2020-02-29 06:51:48','lidihao',0),(1036,'fdsfdfdr3434',0,0,1012,1016,-1,'2020-02-29 06:54:59','lidihao','2020-02-29 06:54:59','lidihao',0),(1037,'343243434',0,0,1012,1018,-1,'2020-02-29 06:55:22','lidihao','2020-02-29 06:55:22','lidihao',0),(1038,'3434342343243434',0,0,1012,1019,-1,'2020-02-29 06:55:46','lidihao','2020-02-29 06:55:46','lidihao',0),(1039,'erer343224234324',0,0,1012,1021,-1,'2020-02-29 06:56:10','lidihao','2020-02-29 06:56:10','lidihao',0),(1040,'rer3432434',0,0,1012,1021,1039,'2020-02-29 06:56:14','lidihao','2020-02-29 06:56:14','lidihao',0),(1041,'dfsdfe4343',0,0,1012,1021,1040,'2020-02-29 06:56:20','lidihao','2020-02-29 06:56:20','lidihao',0),(1042,'45454',0,0,1012,1023,-1,'2020-02-29 07:00:46','lidihao','2020-02-29 07:00:46','lidihao',0),(1043,'dfdsfd fsdfsdfsdffe',0,0,1012,1025,-1,'2020-02-29 07:03:09','lidihao','2020-02-29 07:03:09','lidihao',0),(1044,'sererer',0,0,1012,1025,1043,'2020-02-29 07:16:49','lidihao','2020-02-29 07:16:49','lidihao',0),(1045,'sere',0,0,1012,1025,1044,'2020-02-29 07:16:58','lidihao','2020-02-29 07:16:58','lidihao',0),(1046,'rerer',0,0,1012,1025,1043,'2020-02-29 07:17:05','lidihao','2020-02-29 07:17:05','lidihao',0),(1047,'erer',0,0,1012,1026,-1,'2020-02-29 07:17:14','lidihao','2020-02-29 07:17:14','lidihao',0),(1048,'erere',0,0,1012,1026,1047,'2020-02-29 07:17:17','lidihao','2020-02-29 07:17:17','lidihao',0),(1049,'erer',0,0,1012,1026,1047,'2020-02-29 07:17:27','lidihao','2020-02-29 07:17:27','lidihao',0),(1050,'ererer',0,0,1012,1026,1047,'2020-02-29 07:17:40','lidihao','2020-02-29 07:17:40','lidihao',0),(1051,'9',0,0,1012,1027,-1,'2020-02-29 07:18:37','lidihao','2020-02-29 07:18:37','lidihao',0),(1052,'8',0,0,1012,1027,1051,'2020-02-29 07:19:04','lidihao','2020-02-29 07:19:04','lidihao',0),(1053,'df',0,0,1012,1028,-1,'2020-02-29 07:21:08','lidihao','2020-02-29 07:21:08','lidihao',0),(1054,'dfdf',0,0,1012,1028,1053,'2020-02-29 07:21:18','lidihao','2020-02-29 07:21:18','lidihao',0),(1055,'dfdf',0,0,1012,1028,1054,'2020-02-29 07:21:23','lidihao','2020-02-29 07:21:23','lidihao',0),(1056,'fdf',0,0,1012,1028,1053,'2020-02-29 07:21:29','lidihao','2020-02-29 07:21:29','lidihao',0),(1057,'dfdfdf',0,0,1012,1028,-1,'2020-02-29 07:21:40','lidihao','2020-02-29 07:21:40','lidihao',0),(1058,'dfdfdfdfdf',0,0,1012,1028,-1,'2020-02-29 07:21:45','lidihao','2020-02-29 07:21:45','lidihao',0),(1059,'dfdf',0,0,1012,1028,1058,'2020-02-29 07:21:53','lidihao','2020-02-29 07:21:53','lidihao',0),(1060,'dfdf',0,0,1012,1028,1058,'2020-02-29 07:22:15','lidihao','2020-02-29 07:22:15','lidihao',0),(1061,'dfdf',0,0,1012,1028,1057,'2020-02-29 07:22:26','lidihao','2020-02-29 07:22:26','lidihao',0),(1062,'dfdsf ',0,0,1013,1029,-1,'2020-03-01 14:26:00','lidihao3','2020-03-01 14:26:00','lidihao3',0),(1063,'dfdsf dfdfdf',0,0,1013,1029,-1,'2020-03-01 14:26:11','lidihao3','2020-03-01 14:26:11','lidihao3',0),(1064,'dfdfdf',0,0,1013,1029,1063,'2020-03-01 14:26:25','lidihao3','2020-03-01 14:26:25','lidihao3',0),(1065,'dkkjdfkdf',0,0,1012,1031,-1,'2020-03-08 17:47:44','lidihao','2020-03-08 17:47:44','lidihao',0),(1066,'dkkjdfkdfdsdsd',0,0,1012,1031,-1,'2020-03-08 17:47:53','lidihao','2020-03-08 17:47:53','lidihao',0),(1067,'lidihao4',0,0,1015,1043,-1,'2020-03-09 15:40:48','lidihao4','2020-03-09 15:40:48','lidihao4',0),(1068,'dfdjfkdjf',0,0,1015,1043,1067,'2020-03-09 15:44:37','lidihao4','2020-03-09 15:44:37','lidihao4',0),(1069,'lidihao',0,0,1012,1046,-1,'2020-03-09 20:41:59','lidihao','2020-03-09 20:41:59','lidihao',0),(1070,'嘻嘻',0,0,1015,1047,-1,'2020-03-10 18:10:35','lidihao4','2020-03-10 18:10:35','lidihao4',0),(1071,'憨批',0,0,1012,1045,-1,'2020-03-12 19:12:02','lidihao','2020-03-12 19:12:02','lidihao',0),(1072,'dfdsf',0,0,1012,1051,-1,'2020-03-15 13:13:26','lidihao','2020-03-15 13:13:26','lidihao',0);
/*!40000 ALTER TABLE `comment_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode`
--

DROP TABLE IF EXISTS `episode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `episode` (
  `episode_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `episode_name` varchar(100) NOT NULL COMMENT '剧集名称',
  `episode_url` varchar(100) NOT NULL COMMENT '视频url',
  `poster_id` int(11) DEFAULT NULL,
  `video_file_id` int(11) NOT NULL,
  `episode_index` int(11) NOT NULL COMMENT '剧集的索引',
  `video_id` int(11) NOT NULL COMMENT '属于哪一个视频的剧集',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`episode_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='视频剧集详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode`
--

LOCK TABLES `episode` WRITE;
/*!40000 ALTER TABLE `episode` DISABLE KEYS */;
INSERT INTO `episode` VALUES (1,'VID_20190207_125532.mp4','/video/99179c16ac264a4195a334c7f11212a84719428600086586797.mp4',1097,1003,0,1002,NULL,NULL,NULL,NULL,0),(2,'VID_20190207_125723.mp4','/video/d9bbc7f0ead44b378cb99cc656ac22166335017380459127678.mp4',1095,1001,2,1002,NULL,NULL,NULL,NULL,0),(3,'VID_20190207_130034.mp4','/video/4f389b497bde455da3591a44229b037e4937064999930495541.mp4',1098,1004,3,1002,NULL,NULL,NULL,NULL,0),(4,'VID_20190207_130554.mp4','/video/3e8b1491c2b14f729f2d660ffa188c994635000571410347613.mp4',1099,1005,5,1002,NULL,NULL,NULL,NULL,0),(5,'VID_20190207_130707.mp4','/video/10e66fc1268b434183c91ad84118425a7304397911935318314.mp4',1101,1008,6,1002,NULL,NULL,NULL,NULL,0),(6,'VID_20190207_130727.mp4','/video/8ad3f000164d4cf4b396499fba075eac6212010444927785473.mp4',1102,1007,7,1002,NULL,NULL,NULL,NULL,0),(7,'VID_20190207_125532.mp4','/video/ebefbd4668ca43bbbbe74194ef33d5005075053370173439346.mp4',1157,1051,4,1003,NULL,NULL,NULL,NULL,0),(8,'VID_20190207_125557.mp4','/video/e39f42edf1914983b778ea428ca626747416454967786961287.mp4',1159,1053,5,1003,NULL,NULL,NULL,NULL,0),(9,'VID_20190207_125723.mp4','/video/ccd5d3d6a54c44a8b8e4ab0f5cdb2c0f7491523460478621577.mp4',1158,1052,6,1003,NULL,NULL,NULL,NULL,0),(10,'VID_20190207_130034.mp4','/video/ef5f600fbf614d3992dd0942a306241357644264394753441.mp4',1160,1054,7,1003,NULL,NULL,NULL,NULL,0),(11,'VID_20190207_125557.mp4','/video/40818b000b96468890b2c4eeac3143231398275183464223406.mp4',1122,1023,1,1004,NULL,NULL,NULL,NULL,0),(12,'VID_20190207_125723.mp4','/video/52e326f7c1b04bd6b681202628b9f4fb1980762269386073870.mp4',1119,1024,2,1004,NULL,NULL,NULL,NULL,0),(13,'VID_20190207_130034.mp4','/video/ed322f3ca423446fae52133d441fd2171986915009148206589.mp4',1120,1022,3,1004,NULL,NULL,NULL,NULL,0),(14,'VID_20190207_125532.mp4','/video/666d1181618c4a8ca8bd412bd052f77d7343805485469508635.mp4',1149,1043,3,1004,NULL,NULL,NULL,NULL,0),(15,'VID_20190207_125557.mp4','/video/2d13529bf94e46cb9854d475438554fb4215055440569864708.mp4',1151,1045,4,1004,NULL,NULL,NULL,NULL,0),(16,'VID_20190207_125723.mp4','/video/dcf189f0578540d696754a4903af7c8b3843066954976860564.mp4',1150,1044,5,1004,NULL,NULL,NULL,NULL,0),(17,'VID_20190207_130034.mp4','/video/6efc55a0b4ca477ea5fee935858a3f2f6210840330631794261.mp4',1152,1046,6,1004,NULL,NULL,NULL,NULL,0),(18,'VID_20190207_130542.mp4','/video/fc9d7bfb2f1344d386686c097d5458ce4832869918527251833.mp4',1154,1048,7,1004,NULL,NULL,NULL,NULL,0),(19,'VID_20190207_130554.mp4','/video/10a9540620ad4dceb8a1f9ff37d7e7841212051924521225752.mp4',1153,1047,8,1004,NULL,NULL,NULL,NULL,0),(20,'VID_20190207_130707.mp4','/video/0c4a51f69b6d447a87a6648c1f61fb461747183882728506238.mp4',1155,1049,9,1004,NULL,NULL,NULL,NULL,0),(21,'VID_20190207_130727.mp4','/video/92f376fc28154e2b969ff211777affd08509154011607079249.mp4',1156,1050,10,1004,NULL,NULL,NULL,NULL,0),(22,'VID_20190207_125557.mp4','/video/bec6179a58784b399249889caa0c6c022831408918205399250.mp4',1125,1025,0,1005,NULL,NULL,NULL,NULL,0),(23,'VID_20190207_125723.mp4','/video/c1c02d2cc8b84a299b9f5bdb92a75d6f6185226812503329167.mp4',1126,1027,1,1005,NULL,NULL,NULL,NULL,0),(24,'VID_20190207_130034.mp4','/video/6d1850a9b124404ba0be6b98cb681ed97224517055446610720.mp4',1124,1026,2,1005,NULL,NULL,NULL,NULL,0),(25,'VID_20190207_130542.mp4','/video/1d06e1d03bb54f9c9b859a90d58d3f8c8210058301795740238.mp4',1127,1028,3,1005,NULL,NULL,NULL,NULL,0),(26,'99179c16ac264a4195a334c7f11212a84719428600086586797.mp4','/video/3b6c5804602c4e8192eb39bf655a40ac4324224106763935091.mp4',1188,1062,0,1006,NULL,NULL,NULL,NULL,0),(27,'VID_20190207_125723.mp4','/video/512811a554ae48d58a3e46b47b425c718772921870661899308.mp4',1189,1063,1,1006,NULL,NULL,NULL,NULL,0),(28,'VID_20190207_130727.mp4','/video/93708c5ebd0a4c4387a310e723e823d22791381536355305910.mp4',1187,1061,2,1006,NULL,NULL,NULL,NULL,0),(29,'釜山行.mp4','/video/2f5cb616028c49f7b3962bcb15a1f2782876797546041168523.mp4',1199,1064,0,1007,NULL,NULL,NULL,NULL,0),(30,'[阳光电影-www.ygdy8.com]梨泰院Class-01.mp4','/video/b4aa34bd9142400db6f5a0124762ae6d6508881688486300347.mp4',1204,1066,0,1008,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `episode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_video`
--

DROP TABLE IF EXISTS `favorite_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_video` (
  `favorite_video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `video_id` int(11) NOT NULL COMMENT '视频id',
  `user_id` int(11) NOT NULL COMMENT '收藏人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`favorite_video_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='收藏视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_video`
--

LOCK TABLES `favorite_video` WRITE;
/*!40000 ALTER TABLE `favorite_video` DISABLE KEYS */;
INSERT INTO `favorite_video` VALUES (1000,1006,1012,NULL,NULL,NULL,NULL,_binary '\0'),(1001,1006,1016,NULL,NULL,NULL,NULL,_binary '\0'),(1002,1005,1012,NULL,NULL,NULL,NULL,_binary '\0'),(1003,1008,1012,NULL,NULL,NULL,NULL,_binary '\0');
/*!40000 ALTER TABLE `favorite_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `follow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '粉丝',
  `followed_user_id` int(11) NOT NULL COMMENT '被关注者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`follow_id`),
  KEY `user_id` (`user_id`),
  KEY `follow_id` (`follow_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8 COMMENT='关注表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1003,1015,1012,'2020-03-11 16:36:24','lidihao4','2020-03-11 16:36:24','lidihao4',_binary '\0'),(1004,1016,1012,'2020-03-12 12:34:02','lidihao5','2020-03-12 12:34:02','lidihao5',_binary '\0'),(1005,1014,1012,'2020-03-12 12:35:57','lidihao2','2020-03-12 12:35:57','lidihao2',_binary '\0'),(1007,1012,1016,'2020-03-13 21:03:52','lidihao','2020-03-13 21:03:52','lidihao',_binary '\0'),(1008,1012,1014,'2020-03-13 22:40:47','lidihao','2020-03-13 22:40:47','lidihao',_binary '\0'),(1009,1012,1018,'2020-03-14 16:08:56','lidihao','2020-03-14 16:08:56','lidihao',_binary '\0');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `java_system_taks`
--

DROP TABLE IF EXISTS `java_system_taks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `java_system_taks` (
  `system_task_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_task_name` varchar(200) NOT NULL,
  `system_task_type` varchar(50) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group_name` varchar(200) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group_name` varchar(200) NOT NULL,
  `system_task_desc` varchar(200) NOT NULL,
  `params` varchar(400) NOT NULL,
  `cron` char(20) NOT NULL,
  `job_status` varchar(100) NOT NULL,
  `class_name` varchar(200) NOT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`system_task_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='systemtask';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `java_system_taks`
--

LOCK TABLES `java_system_taks` WRITE;
/*!40000 ALTER TABLE `java_system_taks` DISABLE KEYS */;
INSERT INTO java_system_task VALUES (1000,'test','java_task','test','test','tste','test','test','{}','* * * * * ?','running','com.hao.movieshareback.tasks.FirstJob','2020-03-18 21:06:21','lidihao','2020-03-18 21:06:27','lidihao',_binary '\0');
/*!40000 ALTER TABLE `java_system_taks` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1000,'视频类型','video-type','视频分类',1,'',0,_binary '','2019-12-29 19:45:41','lidihao','2019-12-29 19:45:49','lidihao',_binary '\0'),(1001,'动漫','动漫','动漫',3,'/video/category-detail?type=动漫',1000,_binary '\0','2020-03-18 09:38:28','lidihao','2020-03-18 09:38:38','lidihao',_binary '\0'),(1002,'视频管理','video-manager','',3,'',0,_binary '','2019-12-29 21:33:40','lidihao','2019-12-29 21:33:48','lidihao',_binary '\0'),(1003,'视频上传','video-manager-upload','视频上传页面',4,'/VideoManager/upload',1002,_binary '\0','2019-12-29 21:35:21','lidihao','2019-12-29 21:35:26','lidihao',_binary '\0'),(1004,'个人中心','person-center','个人中心',5,'',0,_binary '','2019-12-29 21:36:40','lidihao','2019-12-29 21:36:48','lidihao',_binary '\0'),(1005,'个人空间','person-space','个人空间',6,'/user/person-space',1004,_binary '\0','2019-12-29 21:37:45','lidihao','2019-12-29 21:37:52','lidihao',_binary '\0'),(1006,'后台管理','system-manager','后台管理',7,'',0,_binary '','2020-01-03 22:54:08','lidihao','2020-01-03 22:54:13','lidihao',_binary '\0'),(1007,'视频审核','video-apply','视频审核',8,'/system/video-apply',1006,_binary '\0','2020-01-03 22:55:23','lidihao','2020-01-03 22:55:30','lidihao',_binary '\0'),(1008,'消息管理','message-manager','消息管理',9,'/message-manager',0,_binary '','2020-03-01 15:03:15','lidihao','2020-03-01 15:03:20','lidihao',_binary '\0'),(1009,'私信','instance-message','私信',10,'/message-manager/instance-message',1008,_binary '\0','2020-03-01 15:07:00','lidihao','2020-03-01 15:07:14','lidihao',_binary '\0'),(1010,'科技','video-type-science','科技',11,'/video/category-detail?type=科技',1000,_binary '\0','2020-03-13 20:26:40','lidihao','2020-03-13 20:26:46','lidihao',_binary '\0'),(1013,'角色管理','role-manager','角色管理',7,'/system/role-manager',1006,_binary '\0','2020-03-18 09:45:15','lidihao','2020-03-18 09:45:15','lidihao',_binary '\0'),(1014,'菜单管理','menu-manager','管理菜单',3,'/system/menu-manager',1006,_binary '\0','2020-03-18 14:45:36','lidihao','2020-03-18 14:45:36','lidihao',_binary '\0'),(1015,'用户信息','user-info','用户个人信息',0,'/user/user-info',1004,_binary '\0','2020-03-18 14:50:07','lidihao','2020-03-18 14:50:07','lidihao',_binary '\0'),(1016,'动作','action','动作片',1,'/video/category-detail?type=动作片',1000,_binary '\0','2020-03-18 14:55:30','lidihao','2020-03-18 14:57:29','lidihao',_binary '\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=1207 DEFAULT CHARSET=utf8 COMMENT='图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1000,'微信图片_20191016102740.jpg',1440,387499,'/image/0111103348e24271b1b249db6f7eb3602161560649051625625.jpg',1080,NULL,NULL,NULL,NULL,0),(1001,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d4bca09499224ecbbd78e89197b6ec1f9185164596198678892.jpg',1080,NULL,NULL,NULL,NULL,0),(1002,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/4bbb6ef16eb2466981f6a3cd4d92a3628746552568449549982.jpg',1080,NULL,NULL,NULL,NULL,0),(1003,'微信图片_20191016102740.jpg',1440,387499,'/image/f376e1abd34d415ebc5cf65d2290e9672764425024612853898.jpg',1080,NULL,NULL,NULL,NULL,0),(1004,'微信图片_20191016102740.jpg',1440,387499,'/image/6b15d1cdd23b4c9ba98048d1faacd94a4698219006760022724.jpg',1080,NULL,NULL,NULL,NULL,0),(1005,'微信图片_20191016102740.jpg',1440,387499,'/image/465da83e5956423daae470e9b9ca0f807610886874813292253.jpg',1080,NULL,NULL,NULL,NULL,0),(1006,'微信图片_20191016102740.jpg',1440,387499,'/image/b5ffcab5d5a0406987616ee769a2c33e6396305785406619857.jpg',1080,NULL,NULL,NULL,NULL,0),(1007,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/4e16b0f5952347e6bb36da2c659c28267011919115221071421.jpg',1080,NULL,NULL,NULL,NULL,0),(1008,'微信图片_20191016102740.jpg',1440,387499,'/image/185c6bea7b1445ada716e91c5ff237f33490378263670339893.jpg',1080,NULL,NULL,NULL,NULL,0),(1009,'微信图片_20191016102740.jpg',1440,387499,'/image/ea4810cdccc84ae19d28dc9a6d8cf69b2972119835353997720.jpg',1080,NULL,NULL,NULL,NULL,0),(1010,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/064ce41a555c4278a32134ca9a49045d6919823815506232421.jpg',1080,NULL,NULL,NULL,NULL,0),(1011,'微信图片_20191016102740.jpg',1440,387499,'/image/23dbdef0897249f99fdd7a006b420f178177976706422951470.jpg',1080,NULL,NULL,NULL,NULL,0),(1012,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/7a631d02124a46898ae64f25d40e61364094078256942193288.jpg',1080,NULL,NULL,NULL,NULL,0),(1013,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/73cd91f829954854ae03ad351695007f2776688447815482140.jpg',1080,NULL,NULL,NULL,NULL,0),(1014,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/a93d06dd3250456187984b91b626c6d85161338799662798617.jpg',1080,NULL,NULL,NULL,NULL,0),(1015,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/bc44d2b845064b9ab4d9ba2287dd316c6862361278434534631.jpg',1080,NULL,NULL,NULL,NULL,0),(1016,'微信图片_20191016102740.jpg',1440,387499,'/image/4d3e17cb8764420b8c48f1b327f3881e4564335474125944556.jpg',1080,NULL,NULL,NULL,NULL,0),(1017,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/db62c8bac47146cea29cb06ea284e1aa736466219899157033.jpg',1080,NULL,NULL,NULL,NULL,0),(1018,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/7d3051fde188460ea6859bdd0b80d6912022039001010380384.jpg',1080,NULL,NULL,NULL,NULL,0),(1019,'微信图片_20191016102740.jpg',1440,387499,'/image/a958576cbe1545fba3348d98ce9312913731477420829371783.jpg',1080,NULL,NULL,NULL,NULL,0),(1020,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d0aee5d189714a7c9d25ff0a40aa2ac73304680871567309553.jpg',1080,NULL,NULL,NULL,NULL,0),(1021,'微信图片_20191016102740.jpg',1440,387499,'/image/a6e7227810a7485b8247b8319b8dcf542464536192606119382.jpg',1080,NULL,NULL,NULL,NULL,0),(1022,'微信图片_20191016102740.jpg',1440,387499,'/image/c45c8cc8fa85418a86aec5963c5b06d45303321461036117681.jpg',1080,NULL,NULL,NULL,NULL,0),(1023,'微信图片_20191016102740.jpg',1440,387499,'/image/0dd9495b5b344781abad6f67b942e36e6818674899712298886.jpg',1080,NULL,NULL,NULL,NULL,0),(1024,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/1628aae82fe84c4a93ec8963a666e0a67679494084594973633.jpg',1080,NULL,NULL,NULL,NULL,0),(1025,'微信图片_20191016102740.jpg',1440,387499,'/image/89d035da842d45faa2d2d94ea7567ba65405060965441568532.jpg',1080,NULL,NULL,NULL,NULL,0),(1026,'微信图片_20191016102809.jpg',809,178640,'/image/cdf3446f758c48a89f4770bfb7b878d78681517260677853835.jpg',1080,NULL,NULL,NULL,NULL,0),(1027,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d4df3176cf474136acf4d5a1afe0a3b93707278606045739279.jpg',1080,NULL,NULL,NULL,NULL,0),(1028,'微信图片_20191016102809.jpg',809,178640,'/image/9869ef9c28864e52b8db7f8e81a3621a8393588435231606022.jpg',1080,NULL,NULL,NULL,NULL,0),(1029,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/73518f27aa65464db9015ac5685e57404957701982895564291.jpg',1080,NULL,NULL,NULL,NULL,0),(1030,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d895c1af445f444cbff05cec8602bdbc5385715252885059728.jpg',1080,NULL,NULL,NULL,NULL,0),(1031,'微信图片_20191016102809.jpg',809,178640,'/image/f2b878ddc2b44962a61957be77b9e5538137477115762453049.jpg',1080,NULL,NULL,NULL,NULL,0),(1032,'微信图片_20191016102809.jpg',809,178640,'/image/32263e546e3b42038a456675129a2e8b3365032386642489426.jpg',1080,NULL,NULL,NULL,NULL,0),(1033,'微信图片_20191016102809.jpg',809,178640,'/image/ba2a87488cf5460080e6cf17094edb351757011106572173749.jpg',1080,NULL,NULL,NULL,NULL,0),(1034,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/9fb231393b474d658559443a494e337e6786923612763393290.jpg',1080,NULL,NULL,NULL,NULL,0),(1035,'微信图片_20191016102809.jpg',809,178640,'/image/59346c7102994c058dbd8cf06a3b77e47660659379730501871.jpg',1080,NULL,NULL,NULL,NULL,0),(1036,'微信图片_20191016102740.jpg',1440,387499,'/image/0b67efa0a7dc4e72ac60e145df6407ae6559581968856274755.jpg',1080,NULL,NULL,NULL,NULL,0),(1037,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/e8788d25faca4d979cb8198962e6ce345393372695155843341.jpg',1080,NULL,NULL,NULL,NULL,0),(1038,'微信图片_20191016102740.jpg',1440,387499,'/image/0775bd3e7153434da294aa982d1a3e2b6809935471128139295.jpg',1080,NULL,NULL,NULL,NULL,0),(1039,'微信图片_20191016102809.jpg',809,178640,'/image/1983e176d6f14d31adc03dc44b84c3c73687969325085367984.jpg',1080,NULL,NULL,NULL,NULL,0),(1040,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/8c82e7bad12b4a0fac0e8256a082d0bd8448098879841720521.jpg',1080,NULL,NULL,NULL,NULL,0),(1041,'微信图片_20191016102740.jpg',1440,387499,'/image/2f91bf1e8a174301854fe4b7595b0910510536475862945210.jpg',1080,NULL,NULL,NULL,NULL,0),(1042,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/c740c13175264f94836212b2f9ac65fe2752372771503369338.jpg',1080,NULL,NULL,NULL,NULL,0),(1043,'微信图片_20191016102740.jpg',1440,387499,'/image/973bfe16e2ba41128c43b5735405a0987400666223526465846.jpg',1080,NULL,NULL,NULL,NULL,0),(1044,'微信图片_20191016102809.jpg',809,178640,'/image/494bbcd94d7d4275a6bbab555c4f9bd66347819540350109301.jpg',1080,NULL,NULL,NULL,NULL,0),(1045,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/54bd40fdc0e44777a5e61f265ae621096311941792447327305.jpg',1080,NULL,NULL,NULL,NULL,0),(1046,'微信图片_20191016102809.jpg',809,178640,'/image/39d8cc3bcf6f42a0a9442469ada734d9249768420457867835.jpg',1080,NULL,NULL,NULL,NULL,0),(1047,'微信图片_20191016102740.jpg',1440,387499,'/image/9aaaaad1668a4e07adacac40ea319b328510566147823355716.jpg',1080,NULL,NULL,NULL,NULL,0),(1048,'微信图片_20191016102740.jpg',1440,387499,'/image/828c41549e35430f992b8a6e6440b41f661037105785032546.jpg',1080,NULL,NULL,NULL,NULL,0),(1049,'微信图片_20191016102809.jpg',809,178640,'/image/3ef303d91c1548fe8f1172bb56339f647110894656080195489.jpg',1080,NULL,NULL,NULL,NULL,0),(1050,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/27e61e7a6257439cb5317f5709e22df57835237874801709060.jpg',1080,NULL,NULL,NULL,NULL,0),(1051,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/30ca069070b742d1ba9e3f21ddd7720b5409399089488759696.jpg',1080,NULL,NULL,NULL,NULL,0),(1052,'微信图片_20191016102809.jpg',809,178640,'/image/5cf74ef96784469ba61bbf81befc5f245699945137654248552.jpg',1080,NULL,NULL,NULL,NULL,0),(1053,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/bd4de32b794645d5b6aed45346f8c20d8441657881967027596.jpg',1080,NULL,NULL,NULL,NULL,0),(1054,'微信图片_20191016102740.jpg',1440,387499,'/image/96cb9f2974e44280b612f031ee5adc2b3916633062526333017.jpg',1080,NULL,NULL,NULL,NULL,0),(1055,'微信图片_20191016102740.jpg',1440,387499,'/image/58f504e4924043b58db2229c4a2400466297006474217564895.jpg',1080,NULL,NULL,NULL,NULL,0),(1056,'微信图片_20191016102740.jpg',1440,387499,'/image/df0bb9632b2b4f12b77c3a58a6724f3e1613157098230008322.jpg',1080,NULL,NULL,NULL,NULL,0),(1057,'微信图片_20191016102740.jpg',1440,387499,'/image/7d5af389543e4431aa506b259de183a27165132270005969238.jpg',1080,NULL,NULL,NULL,NULL,0),(1058,'微信图片_20191016102809.jpg',809,178640,'/image/0abcfb777acd459e8199d0594e196a222021521429621294189.jpg',1080,NULL,NULL,NULL,NULL,0),(1059,'微信图片_20191016102740.jpg',1440,387499,'/image/8f62a2c0b5dc47128fff73ca9bf2c9f21924100492041327173.jpg',1080,NULL,NULL,NULL,NULL,0),(1060,'微信图片_20191016102740.jpg',1440,387499,'/image/b0da10d831fd48a2834f0d0c7c8b18797111864285923478514.jpg',1080,NULL,NULL,NULL,NULL,0),(1061,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/56fec0ed20054880a97ac1e8eac418ef3411384869349425551.jpg',1080,NULL,NULL,NULL,NULL,0),(1062,'微信图片_20191016102740.jpg',1440,387499,'/image/e49264f4a3f8414bb21c47c319a9f9847435618068576445996.jpg',1080,NULL,NULL,NULL,NULL,0),(1063,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/a08e73c96dbf4dadbccba4b7797e254f4538106742471746184.jpg',1080,NULL,NULL,NULL,NULL,0),(1064,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/91c5de9f18284284b11f24e6d23191b47095841129167810924.jpg',1080,NULL,NULL,NULL,NULL,0),(1065,'微信图片_20191016102740.jpg',1440,387499,'/image/cdef778b54254013a7dab9902d0afdbb2894714349729571263.jpg',1080,NULL,NULL,NULL,NULL,0),(1066,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/84f31f756a80409394f05632241e16792409763899686167042.jpg',1080,NULL,NULL,NULL,NULL,0),(1067,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/500981fd91184549af9f00c3a218278e2887049003738332607.jpg',1080,NULL,NULL,NULL,NULL,0),(1068,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/b1345b86fa6541faab11cb7056d98e8f2795699270942652488.jpg',1080,NULL,NULL,NULL,NULL,0),(1069,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/5fcce7e380a048c9abfdda1df32b21b04747645836071839437.jpg',1080,NULL,NULL,NULL,NULL,0),(1070,'微信图片_20191016102740.jpg',1440,387499,'/image/f4b95f49139f45adb8cf8f91c28936791703886288443252664.jpg',1080,NULL,NULL,NULL,NULL,0),(1071,'微信图片_20191016102740.jpg',1440,387499,'/image/b14f19563ee64ba4a772b97a0fa79a511992066070216530893.jpg',1080,NULL,NULL,NULL,NULL,0),(1072,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/d7aebb221c3a49e9b027d98b8d8ec4d59029050213623636539.jpg',1080,NULL,NULL,NULL,NULL,0),(1073,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/f116a61675d346eeb11148245037999b4765215632617001781.jpg',1080,NULL,NULL,NULL,NULL,0),(1074,'微信图片_20191016102740.jpg',1440,387499,'/image/c4ff5701b62d49c28cc84c67d8e7ba979164590358798826929.jpg',1080,NULL,NULL,NULL,NULL,0),(1075,'微信图片_20191016102740.jpg',1440,387499,'/image/2966db7aa78a4178b64eb9c54b3128863064998340744185483.jpg',1080,NULL,NULL,NULL,NULL,0),(1076,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/1cf8b39eed2d4a8dadc10c8773b6f75f7445478306188568587.jpg',1080,NULL,NULL,NULL,NULL,0),(1077,'微信图片_20191016102740.jpg',1440,387499,'/image/dec9f72de708426b9cb5f502b4c720937918650486003952345.jpg',1080,NULL,NULL,NULL,NULL,0),(1078,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/f8ca25653742461db4c9014bef4197eb8078841554367196121.jpg',1080,NULL,NULL,NULL,NULL,0),(1079,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/498be69570104155a83808f3483cc2435075539363816450082.jpg',1080,NULL,NULL,NULL,NULL,0),(1080,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/dafcbbf862824d8c8f7fc73f049836186739168021665072098.jpg',1080,NULL,NULL,NULL,NULL,0),(1081,'16d85b36849549b0fa1a75b69c61d3e.jpg',1440,480657,'/image/258034de3b03426eade24c5c2cb28432408196058578558667.jpg',1080,NULL,NULL,NULL,NULL,0),(1082,'微信图片_20191016102740.jpg',1440,387499,'/image/a5ee5cb8603148d68007f6fab4c55e9c6672521542099753682.jpg',1080,NULL,NULL,NULL,NULL,0),(1083,'微信图片_20191016102809.jpg',809,178640,'/image/728604e0f51a4ab49e74e6b0a004add11255469456553150678.jpg',1080,NULL,NULL,NULL,NULL,0),(1084,'微信图片_20191016102740.jpg',1440,387499,'/image/dcf713f65ed449968ba0c17e71e002395105393428452170797.jpg',1080,NULL,NULL,NULL,NULL,0),(1085,'m200103ko3ngni170ah2ui4khn3a51kq_0005.jpg',1080,213906,'/image/b02aa00e84bc4bd5a66a4921b4e412467012234305785142196.jpg',1920,NULL,NULL,NULL,NULL,0),(1086,'m200103ko3ngni170ah2ui4khn3a51kq_0005 (2).jpg',1055,58644,'/image/2b6e3dab4b844ab78d8a8d89efb140e66388984739650019410.jpg',687,NULL,NULL,NULL,NULL,0),(1087,'abcde.jpg',335,59955,'/image/25a62b0655b04062991e1122456755da3815099716010705783.jpg',569,NULL,NULL,NULL,NULL,0),(1088,'abcde.jpg',335,59955,'/image/ec888d805b5e434e92d89198e4048bd58637720224038232730.jpg',569,NULL,NULL,NULL,NULL,0),(1089,'abcde.jpg',335,59955,'/image/383626eb7a884cbcab5fbea4014c98e28137721211018841973.jpg',569,NULL,NULL,NULL,NULL,0),(1090,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/fc7ace0d59bf48818cb1cec8cd459e076055900877021080083.jpeg',1280,NULL,NULL,NULL,NULL,0),(1091,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/35f4ec210d094cc8a7ba0d3f7b694b575962115945187880407.jpeg',1280,NULL,NULL,NULL,NULL,0),(1093,'7a7dbd1036ae4c28ae7be39cc80452bb.jpg',720,125521,'/image/7a7dbd1036ae4c28ae7be39cc80452bb.jpg',1280,NULL,NULL,NULL,NULL,0),(1094,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/30b26e8ed42d4d65979802c4dbd5f654778971464284988005.jpeg',1280,NULL,NULL,NULL,NULL,0),(1095,'7edd455c6e534ede82321b7586c1fc6a.jpg',720,93196,'/image/7edd455c6e534ede82321b7586c1fc6a.jpg',1280,NULL,NULL,NULL,NULL,0),(1096,'86be081d88574f6d8faac5f805302e7c.jpg',720,125521,'/image/86be081d88574f6d8faac5f805302e7c.jpg',1280,NULL,NULL,NULL,NULL,0),(1097,'e44ef28c67fa451d9a6526ffac5cc1ab.jpg',720,129313,'/image/e44ef28c67fa451d9a6526ffac5cc1ab.jpg',1280,NULL,NULL,NULL,NULL,0),(1098,'d46ae1c832874e15810c15f7bfde4492.jpg',720,24763,'/image/d46ae1c832874e15810c15f7bfde4492.jpg',1280,NULL,NULL,NULL,NULL,0),(1099,'3093e1bdd17746e589ee9edf775bab1a.jpg',720,114102,'/image/3093e1bdd17746e589ee9edf775bab1a.jpg',1280,NULL,NULL,NULL,NULL,0),(1100,'be5ece563c7143d1a9c9d3c2f42cfb39.jpg',1280,92915,'/image/be5ece563c7143d1a9c9d3c2f42cfb39.jpg',720,NULL,NULL,NULL,NULL,0),(1101,'91ff76b3f09d4a8992cdd817b03b300c.jpg',1280,82045,'/image/91ff76b3f09d4a8992cdd817b03b300c.jpg',720,NULL,NULL,NULL,NULL,0),(1102,'3c8a4ff8e1c04766b27182d4886dcb01.jpg',1280,92075,'/image/3c8a4ff8e1c04766b27182d4886dcb01.jpg',720,NULL,NULL,NULL,NULL,0),(1103,'22205e444afa4d9994dcc6ea6814d84f.jpg',720,93196,'/image/22205e444afa4d9994dcc6ea6814d84f.jpg',1280,NULL,NULL,NULL,NULL,0),(1104,'be3651bece0243c79bcabb6cdba78019.jpg',720,129313,'/image/be3651bece0243c79bcabb6cdba78019.jpg',1280,NULL,NULL,NULL,NULL,0),(1105,'d2f9b94b611b4ab19fccb2587016ac6f.jpg',720,125521,'/image/d2f9b94b611b4ab19fccb2587016ac6f.jpg',1280,NULL,NULL,NULL,NULL,0),(1106,'ef4a0093bb5141c19815a1d9562b77e9.jpg',720,24763,'/image/ef4a0093bb5141c19815a1d9562b77e9.jpg',1280,NULL,NULL,NULL,NULL,0),(1107,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/1538a396a17841269895d7e26ab24aaf7184419972519500559.jpeg',1280,NULL,NULL,NULL,NULL,0),(1108,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/0a7e6d6532c44304bb3f5bb72fa1c83447745489401790810.jpeg',1280,NULL,NULL,NULL,NULL,0),(1109,'58fce08024a842b9a4c918d44f10710c.jpg',720,24763,'/image/58fce08024a842b9a4c918d44f10710c.jpg',1280,NULL,NULL,NULL,NULL,0),(1110,'a1687f227b7f4af2a0f00b6b95ccf7af.jpg',720,93196,'/image/a1687f227b7f4af2a0f00b6b95ccf7af.jpg',1280,NULL,NULL,NULL,NULL,0),(1111,'d16e5fb1619d472cb09ddb0ce75abede.jpg',720,125521,'/image/d16e5fb1619d472cb09ddb0ce75abede.jpg',1280,NULL,NULL,NULL,NULL,0),(1112,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/d517ce169ff8446cae9f4667df8076cf8170665835848938002.jpeg',1280,NULL,NULL,NULL,NULL,0),(1113,'fda41706c61049f7a5c3f708ea01a831.jpg',720,125521,'/image/fda41706c61049f7a5c3f708ea01a831.jpg',1280,NULL,NULL,NULL,NULL,0),(1114,'9ce4658d21ae4249b07bcf9a53bc214e.jpg',720,114102,'/image/9ce4658d21ae4249b07bcf9a53bc214e.jpg',1280,NULL,NULL,NULL,NULL,0),(1115,'e558691a5c9b4709b0ed514ef44f9638.jpg',720,24763,'/image/e558691a5c9b4709b0ed514ef44f9638.jpg',1280,NULL,NULL,NULL,NULL,0),(1116,'b5973fe64ce244e2ae05670653a89c5e.jpg',720,93196,'/image/b5973fe64ce244e2ae05670653a89c5e.jpg',1280,NULL,NULL,NULL,NULL,0),(1117,'411e7ff0f2c440dc80ef5bc39d5d71de.jpg',1280,92915,'/image/411e7ff0f2c440dc80ef5bc39d5d71de.jpg',720,NULL,NULL,NULL,NULL,0),(1118,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/f2b16d4e4040428ca56e7e625119f24f7653887338170636952.jpeg',1280,NULL,NULL,NULL,NULL,0),(1119,'8c657b867ce14471bc803cfa54352959.jpg',720,93196,'/image/8c657b867ce14471bc803cfa54352959.jpg',1280,NULL,NULL,NULL,NULL,0),(1120,'3ed82345de4340f6879eeaa2bed0549b.jpg',720,24763,'/image/3ed82345de4340f6879eeaa2bed0549b.jpg',1280,NULL,NULL,NULL,NULL,0),(1121,'c4b6dc4cfffd4476bed7652343103d6e.jpg',720,129313,'/image/c4b6dc4cfffd4476bed7652343103d6e.jpg',1280,NULL,NULL,NULL,NULL,0),(1122,'a0dcd214f87d497e825e81b1a0460682.jpg',720,125521,'/image/a0dcd214f87d497e825e81b1a0460682.jpg',1280,NULL,NULL,NULL,NULL,0),(1123,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/e85b4101a59449b8b7ae1202517a850f3662146160519466536.jpeg',1280,NULL,NULL,NULL,NULL,0),(1124,'968c88f6a6aa4421bdece7b381ca5aff.jpg',720,24763,'/image/968c88f6a6aa4421bdece7b381ca5aff.jpg',1280,NULL,NULL,NULL,NULL,0),(1125,'9c11301364ed4c908e58f4c0f9e731ba.jpg',720,125521,'/image/9c11301364ed4c908e58f4c0f9e731ba.jpg',1280,NULL,NULL,NULL,NULL,0),(1126,'da60d30bff8d4201bfa578bc15ebeb2b.jpg',720,93196,'/image/da60d30bff8d4201bfa578bc15ebeb2b.jpg',1280,NULL,NULL,NULL,NULL,0),(1127,'7defdc6c45ff4f50a4c8dade5fccc87f.jpg',1280,92915,'/image/7defdc6c45ff4f50a4c8dade5fccc87f.jpg',720,NULL,NULL,NULL,NULL,0),(1128,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/0fcf76a1f7b047658e60e8d0ee2ac2bc3780321110708992445.jpeg',1280,NULL,NULL,NULL,NULL,0),(1129,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/2dfaaa9d345d43e7829d7605e59b07841808508503751951148.jpeg',1280,NULL,NULL,NULL,NULL,0),(1130,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/a57b7a73ade0478786eb28904c988c944343600766462347342.jpeg',1280,NULL,NULL,NULL,NULL,0),(1131,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/4024cca4d40548d4994a4d6a9397d5d56497898831217003011.jpeg',1280,NULL,NULL,NULL,NULL,0),(1132,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/7f9c42c35d4b476591dc5fda0cb8854c4630005482618936685.jpeg',1280,NULL,NULL,NULL,NULL,0),(1133,'ca0328f9a2ca87616d72a24d26b55e06.jpeg',720,88952,'/image/e8585b6826d6410994d1a276cbb0853d1970614695496383910.jpeg',1280,NULL,NULL,NULL,NULL,0),(1134,'ab82232b3eb14de29fb9491fa9733792.jpg',720,114102,'/image/ab82232b3eb14de29fb9491fa9733792.jpg',1280,NULL,NULL,NULL,NULL,0),(1135,'d8afec668fed48cc96b2e642feabc092.jpg',720,24763,'/image/d8afec668fed48cc96b2e642feabc092.jpg',1280,NULL,NULL,NULL,NULL,0),(1136,'0511229418f043558948ffe4959ab47d.jpg',1280,92915,'/image/0511229418f043558948ffe4959ab47d.jpg',720,NULL,NULL,NULL,NULL,0),(1137,'1281ed6787324a75bef3999392109414.jpg',720,129313,'/image/1281ed6787324a75bef3999392109414.jpg',1280,NULL,NULL,NULL,NULL,0),(1138,'0688f9ab7064421ba66f37a754915bb9.jpg',720,125521,'/image/0688f9ab7064421ba66f37a754915bb9.jpg',1280,NULL,NULL,NULL,NULL,0),(1139,'fd7a17ee71be4ce8a4e6103f21596d85.jpg',720,93196,'/image/fd7a17ee71be4ce8a4e6103f21596d85.jpg',1280,NULL,NULL,NULL,NULL,0),(1140,'9c205da877b44ad3890be010595594d9.jpg',1280,82045,'/image/9c205da877b44ad3890be010595594d9.jpg',720,NULL,NULL,NULL,NULL,0),(1141,'f3779470f4bf424b806be22f7aa644f6.jpg',1280,92075,'/image/f3779470f4bf424b806be22f7aa644f6.jpg',720,NULL,NULL,NULL,NULL,0),(1142,'6495a3087e394f30a3eacfd8e6d0a191.jpg',720,129313,'/image/6495a3087e394f30a3eacfd8e6d0a191.jpg',1280,NULL,NULL,NULL,NULL,0),(1143,'103eb53384204cb485b4c3b635cb0b94.jpg',720,129313,'/image/103eb53384204cb485b4c3b635cb0b94.jpg',1280,NULL,NULL,NULL,NULL,0),(1144,'a642bcadcbdb4852b5518d0726734336.jpg',720,125521,'/image/a642bcadcbdb4852b5518d0726734336.jpg',1280,NULL,NULL,NULL,NULL,0),(1145,'7fdd7e46691e47d4845ce10e2f66b0ad.jpg',720,93196,'/image/7fdd7e46691e47d4845ce10e2f66b0ad.jpg',1280,NULL,NULL,NULL,NULL,0),(1146,'b8a736413ede4106b16f4c3a4ccc2640.jpg',720,125521,'/image/b8a736413ede4106b16f4c3a4ccc2640.jpg',1280,NULL,NULL,NULL,NULL,0),(1147,'8936075583864d699d142b5ed5e3a189.jpg',720,93196,'/image/8936075583864d699d142b5ed5e3a189.jpg',1280,NULL,NULL,NULL,NULL,0),(1148,'20d4a4a28754424b8f49be2d70f639fb.jpg',720,129313,'/image/20d4a4a28754424b8f49be2d70f639fb.jpg',1280,NULL,NULL,NULL,NULL,0),(1149,'4c6514884a65492cb34f55a264d78c00.jpg',720,129313,'/image/4c6514884a65492cb34f55a264d78c00.jpg',1280,NULL,NULL,NULL,NULL,0),(1150,'0b5138d62a374360b96c04bb30613258.jpg',720,93196,'/image/0b5138d62a374360b96c04bb30613258.jpg',1280,NULL,NULL,NULL,NULL,0),(1151,'d2ec572785514c2d8f5bbf76c60d3ec1.jpg',720,125521,'/image/d2ec572785514c2d8f5bbf76c60d3ec1.jpg',1280,NULL,NULL,NULL,NULL,0),(1152,'d51209c5192a416bafa60618be40e2ed.jpg',720,24763,'/image/d51209c5192a416bafa60618be40e2ed.jpg',1280,NULL,NULL,NULL,NULL,0),(1153,'8e4d7a69631849189a223bdc1c0b76fa.jpg',720,114102,'/image/8e4d7a69631849189a223bdc1c0b76fa.jpg',1280,NULL,NULL,NULL,NULL,0),(1154,'7c484c02bd7246b6bb48f465eafa0a6a.jpg',1280,92915,'/image/7c484c02bd7246b6bb48f465eafa0a6a.jpg',720,NULL,NULL,NULL,NULL,0),(1155,'d0925edddc624583bf5ee6950218a4e4.jpg',1280,82045,'/image/d0925edddc624583bf5ee6950218a4e4.jpg',720,NULL,NULL,NULL,NULL,0),(1156,'3c5558df5bdc4027af89c58a332aa82e.jpg',1280,92075,'/image/3c5558df5bdc4027af89c58a332aa82e.jpg',720,NULL,NULL,NULL,NULL,0),(1157,'be18fede31534d7f9ff43f71867bd6ba.jpg',720,129313,'/image/be18fede31534d7f9ff43f71867bd6ba.jpg',1280,NULL,NULL,NULL,NULL,0),(1158,'f3b0697fadac4f2e9de78bd6f4ccc860.jpg',720,93196,'/image/f3b0697fadac4f2e9de78bd6f4ccc860.jpg',1280,NULL,NULL,NULL,NULL,0),(1159,'65c397afab6c43b4be3674325a56852a.jpg',720,125521,'/image/65c397afab6c43b4be3674325a56852a.jpg',1280,NULL,NULL,NULL,NULL,0),(1160,'d49eeeb66efd46fa92b3d2a2cc11938e.jpg',720,24763,'/image/d49eeeb66efd46fa92b3d2a2cc11938e.jpg',1280,NULL,NULL,NULL,NULL,0),(1161,'bf2079b9713b4c69a6dad01282fc8e52.jpg',1280,82045,'/image/bf2079b9713b4c69a6dad01282fc8e52.jpg',720,NULL,NULL,NULL,NULL,0),(1162,'944c8f762e6546e0a72f9c3326737f0e.jpg',720,125521,'/image/944c8f762e6546e0a72f9c3326737f0e.jpg',1280,NULL,NULL,NULL,NULL,0),(1163,'f12d02621cc04906bb16a297392b78ee.jpg',1280,92915,'/image/f12d02621cc04906bb16a297392b78ee.jpg',720,NULL,NULL,NULL,NULL,0),(1164,'12f03aef30d24db5b76b0f7e5769fb30.jpg',720,24763,'/image/12f03aef30d24db5b76b0f7e5769fb30.jpg',1280,NULL,NULL,NULL,NULL,0),(1165,'cdfebd6ce68243c4bcd62320d9b10423.jpg',720,114102,'/image/cdfebd6ce68243c4bcd62320d9b10423.jpg',1280,NULL,NULL,NULL,NULL,0),(1166,'9594fb54b0ed4c0caae0cab8729b6912.jpg',720,93196,'/image/9594fb54b0ed4c0caae0cab8729b6912.jpg',1280,NULL,NULL,NULL,NULL,0),(1167,'test.jpeg',242,78423,'/image/1835fef9c338443aae1b51f0855abf154073370343968310597.jpeg',969,NULL,NULL,NULL,NULL,0),(1168,'test.jpeg',272,45120,'/image/bf8b4c4104e546deaafccf85c5267f73448318489940034142.jpeg',1088,NULL,NULL,NULL,NULL,0),(1169,'test.jpeg',308,235421,'/image/842d1862821843e3b1dadf6d7ee392823958175675851282941.jpeg',1233,NULL,NULL,NULL,NULL,0),(1170,'test.jpeg',197,94614,'/image/b651c9dcc73b435cbecf197e39e46ea14365577018610246050.jpeg',787,NULL,NULL,NULL,NULL,0),(1171,'test.jpeg',197,94614,'/image/55d642072d3a481fbbc728886f038bc56215522513443935727.jpeg',787,NULL,NULL,NULL,NULL,0),(1172,'test.jpeg',235,134469,'/image/2876df7aae9b4d998dbdeb2e6c3cb4847930525076456168415.jpeg',939,NULL,NULL,NULL,NULL,0),(1173,'test.jpeg',228,122260,'/image/b2678f539f0e4bf597805b5c6fbf80278633583472276538900.jpeg',912,NULL,NULL,NULL,NULL,0),(1174,'test.jpeg',217,110847,'/image/7259e4dc6c37433ba6e33fdb4d43076f4521533730975885179.jpeg',867,NULL,NULL,NULL,NULL,0),(1177,'skin.jpeg',306,251177,'/image/34de2ffdad5b40349a452ae6bca082cc3026269904496217919.jpeg',988,NULL,NULL,NULL,NULL,0),(1185,'skin.jpeg',201,276686,'/image/1488497129414202b708bcb402101bb9913721051093617888.jpeg',1004,NULL,NULL,NULL,NULL,0),(1186,'skin.jpeg',202,197741,'/image/8f85254253aa49c89cdebaca5e89aa5c2322117668241869574.jpeg',1010,NULL,NULL,NULL,NULL,0),(1187,'2344a928c82c4c7db275046ec3e07ae3.jpg',1280,92075,'/image/2344a928c82c4c7db275046ec3e07ae3.jpg',720,NULL,NULL,NULL,NULL,0),(1188,'81642ea1625343c18cd2536a9ae29353.jpg',720,129313,'/image/81642ea1625343c18cd2536a9ae29353.jpg',1280,NULL,NULL,NULL,NULL,0),(1189,'2e7492117eea4eac8171fc4b3803053b.jpg',720,93196,'/image/2e7492117eea4eac8171fc4b3803053b.jpg',1280,NULL,NULL,NULL,NULL,0),(1190,'548f2cc8d56d40c59ba025bb808953f0.jpg',618,61839,'/image/ff8b5d23caeb461a92c4d4d1857bd24d6139457916657694621.jpg',1000,NULL,NULL,NULL,NULL,0),(1191,'skin.jpeg',201,219593,'/image/326a5879ca9e4baca81e88a066abaeba1930798862478157899.jpeg',1004,NULL,NULL,NULL,NULL,0),(1192,'skin.jpeg',640,314045,'/image/b889eb73291d40aa9848078ad15c52316582112981528433782.jpeg',640,NULL,NULL,NULL,NULL,0),(1193,'skin.jpeg',618,258152,'/image/5e602818c5d1409697d9316667d6b5a21451257383607723447.jpeg',618,NULL,NULL,NULL,NULL,0),(1194,'skin.jpeg',640,314045,'/image/9f26cc3750e542279314a7cd2404f7a67204835749988242442.jpeg',640,NULL,NULL,NULL,NULL,0),(1195,'skin.jpeg',618,281328,'/image/ee6a257931ba4349bcdfc316ab8a7f2f3365055219952993422.jpeg',618,NULL,NULL,NULL,NULL,0),(1196,'timg.jpeg',696,137512,'/image/f2948505a4ca46d7a7c5db4c456cba6a2923200331040455388.jpeg',1280,NULL,NULL,NULL,NULL,0),(1197,'timg.jpeg',696,137512,'/image/a839f2d272ad4228b6f7fa962ec3785b3449251317059099374.jpeg',1280,NULL,NULL,NULL,NULL,0),(1198,'timg.jpeg',696,137512,'/image/e5498f2aa65e41ec9539c04e725203dc4773578019821610621.jpeg',1280,NULL,NULL,NULL,NULL,0),(1199,'88da4936feb04275b226dbc19506c74f.jpg',692,14707,'/image/88da4936feb04275b226dbc19506c74f.jpg',1280,NULL,NULL,NULL,NULL,0),(1200,'timg.jpeg',1000,208253,'/image/facaa013759b43b9860969399eb08a118089400217975361610.jpeg',1000,NULL,NULL,NULL,NULL,0),(1201,'timg.jpeg',1000,208253,'/image/6de992ff3d474a3c8b95777e3e98608e5290902950567159518.jpeg',1000,NULL,NULL,NULL,NULL,0),(1202,'486a73c9e780435298749b9836b5f9dc.jpg',720,145910,'/image/486a73c9e780435298749b9836b5f9dc.jpg',1280,NULL,NULL,NULL,NULL,0),(1203,'timg.jpeg',1000,208253,'/image/228e43df5b2b42bf896e0f341ea1340e8718105629066050108.jpeg',1000,NULL,NULL,NULL,NULL,0),(1204,'d197c7704cd74b429d9f42a5958d12db.jpg',720,145910,'/image/d197c7704cd74b429d9f42a5958d12db.jpg',1280,NULL,NULL,NULL,NULL,0),(1205,'skin.jpeg',602,442101,'/image/a57365779a204be1bc0ceb3efa8216f2714796323135016069.jpeg',602,NULL,NULL,NULL,NULL,0),(1206,'skin.jpeg',377,113016,'/image/9b0aa41b730d425ab306c2701847e5b97439189732972697605.jpeg',377,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `private_message`
--

DROP TABLE IF EXISTS `private_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `private_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sender_id` int(11) NOT NULL COMMENT '发送者id',
  `receiver_id` int(11) NOT NULL COMMENT '接受者id',
  `message_content` varchar(500) NOT NULL COMMENT '消息内容',
  `send_time` datetime NOT NULL COMMENT '消息发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '消息阅读时间',
  `delete_time` datetime DEFAULT NULL COMMENT '消息删除时间',
  `un_read` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否已读 (0：未读 1：已读) ',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`message_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='私信消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `private_message`
--

LOCK TABLES `private_message` WRITE;
/*!40000 ALTER TABLE `private_message` DISABLE KEYS */;
INSERT INTO `private_message` VALUES (1,1012,1013,'hello','2020-03-04 16:31:21','2020-03-04 16:31:23','2020-03-04 16:31:25',_binary '',_binary '\0'),(2,1012,1013,'没有人能忽略这样一张脸孔：泪眼纷纷，呜咽声声，“求求，求求你们。”黑夜在颤抖，墨镜里，必藏着一双红肿、深陷、因其绝望而绝美的眼睛。\n\n　　 她叫苏珊,她说：“这原本是一个温良秋夜，她开车带着3岁和14个月大的两个孩子，行驶在静谧的公路上，忽然一个歹徒窜上车，持枪威逼她下车，带着她的孩子们，扬长而去。\n\n　　 而她，只能无助地站在路边，对瞬间消失的车子挥手，喊道，“再见，宝贝们，妈妈永远爱你们。”而黑暗冰寒无尽。\n\n　　 全美国都为她哭泣祈祷，却有一个女子投书电视台了：苏珊在说谎。\n\n　　 高跟鞋妨碍她，一把拽脱劈手扔过去，她死命追赶。忘了人的速度不可能与车抗衡，看不见脚下的石砾、玻璃屑、柏油，唯一的念头就是：女儿。她只是一个纤细的亚裔女子，那一刻却如豹如鹰，势如疯虎，连歹徒也被吓倒了，弃车而逃。而她裙摆全撕，脚踝扭伤，脚底流下殷红的血。\n\n　　 生死却装做一无所知；','2020-03-04 16:32:44','2020-03-04 16:33:00','2020-03-04 16:33:02',_binary '',_binary '\0'),(3,1013,1012,' 没有人能忽略这样一张脸孔：泪眼纷纷，呜咽声声，“求求，求求你们。”黑夜在颤抖，墨镜里，必藏着一双红肿、深陷、因其绝望而绝美的眼睛。\n\n　　 她叫苏珊,她说：“这原本是一个温良秋夜，她开车带着3岁和14个月大的两个孩子，行驶在静谧的公路上，忽然一个歹徒窜上车，持枪威逼她下车，带着她的孩子们，扬长而去。\n\n　　 而她，只能无助地站在路边，对瞬间消失的车子挥手，喊道，“再见，宝贝们，妈妈永远爱你们。”而黑暗冰寒无尽。\n\n　　 全美国都为她哭泣祈祷，却有一个女子投书电视台了：苏珊在说谎。\n\n　　 女子说，她也是母亲，也曾在山崩石裂瞬间，下车问路，一转头，车被人开走，而车上，有她还是稚婴的女儿。','2020-03-05 23:04:42','2020-03-05 14:02:37','2020-03-05 14:02:39',_binary '',_binary '\0'),(6,1012,1013,'sfdsfdfdf','2020-03-06 15:25:12',NULL,NULL,_binary '',_binary '\0'),(7,1012,1013,'dfdfdfdfdfdfdfdf','2020-03-06 15:27:15',NULL,NULL,_binary '',_binary '\0'),(8,1012,1013,'dfdfdfdfdff','2020-03-06 15:37:59',NULL,NULL,_binary '',_binary '\0'),(9,1013,1012,'teststts','2020-03-06 15:39:08',NULL,NULL,_binary '',_binary '\0'),(10,1013,1012,'dfdsfsdfdsfdsf','2020-03-06 15:39:50',NULL,NULL,_binary '',_binary '\0'),(11,1013,1012,'test','2020-03-06 17:31:48',NULL,NULL,_binary '',_binary '\0'),(12,1013,1012,'test1','2020-03-06 17:32:45',NULL,NULL,_binary '',_binary '\0'),(13,1014,1012,'dfdfdfdfdf df df','2020-03-06 21:24:11',NULL,NULL,_binary '',_binary '\0'),(14,1014,1012,'dfdfdfdfdf df dfdfd fdfdfd','2020-03-06 21:24:52',NULL,NULL,_binary '',_binary '\0'),(15,1012,1014,'hgjhj\n','2020-03-06 21:29:46',NULL,NULL,_binary '',_binary '\0'),(16,1012,1014,'ddfdf','2020-03-06 21:41:53',NULL,NULL,_binary '',_binary '\0'),(17,1014,1012,'dfdf dfsdf','2020-03-06 22:03:51',NULL,NULL,_binary '',_binary '\0'),(18,1015,1012,'tesdfdfdf','2020-03-06 22:07:56',NULL,NULL,_binary '',_binary '\0'),(19,1015,1012,'尼玛','2020-03-09 17:06:21',NULL,NULL,_binary '',_binary '\0'),(20,1013,1012,'你好','2020-03-12 10:52:36',NULL,NULL,_binary '',_binary '\0'),(21,1013,1012,'你好 lidihao3','2020-03-12 10:55:10',NULL,NULL,_binary '',_binary '\0'),(22,1013,1012,'你好1','2020-03-12 11:13:58',NULL,NULL,_binary '',_binary '\0'),(23,1015,1012,'年后\n','2020-03-12 11:20:12',NULL,NULL,_binary '',_binary '\0'),(24,1015,1012,'特色他说他是他同时身体','2020-03-12 11:22:15',NULL,NULL,_binary '',_binary '\0'),(25,1013,1012,'你好3434\n','2020-03-12 11:25:40',NULL,NULL,_binary '',_binary '\0'),(26,1015,1012,'kfjdkfdkfjdkfkdf lidihao3','2020-03-12 11:58:57',NULL,NULL,_binary '',_binary '\0'),(27,1015,1012,'lidihao3','2020-03-12 12:02:45',NULL,NULL,_binary '',_binary '\0'),(28,1013,1012,'lidihao3','2020-03-12 12:03:50',NULL,NULL,_binary '',_binary '\0'),(29,1015,1012,'lidihao4','2020-03-12 12:20:56',NULL,NULL,_binary '',_binary '\0'),(30,1013,1012,'lidihao3','2020-03-12 12:24:22',NULL,NULL,_binary '',_binary '\0'),(31,1015,1012,'lidihao4','2020-03-12 12:28:20',NULL,NULL,_binary '',_binary '\0'),(32,1015,1012,'lidihao42','2020-03-12 12:28:37',NULL,NULL,_binary '',_binary '\0'),(33,1016,1012,'lidihao5','2020-03-12 12:34:20',NULL,NULL,_binary '',_binary '\0'),(34,1014,1012,'lidihao2','2020-03-12 12:36:12',NULL,NULL,_binary '',_binary '\0'),(35,1016,1012,'lidihao5 test','2020-03-12 12:57:57',NULL,NULL,_binary '',_binary '\0'),(36,1016,1012,'lidihao5 test','2020-03-12 12:58:37',NULL,NULL,_binary '',_binary '\0'),(37,1016,1012,'lidihao5 test','2020-03-12 12:58:40',NULL,NULL,_binary '',_binary '\0'),(39,1012,1016,'test','2020-03-12 15:58:11',NULL,NULL,_binary '',_binary '\0');
/*!40000 ALTER TABLE `private_message` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1046 DEFAULT CHARSET=utf8 COMMENT='角色-菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1000,1000,1000,'2019-12-29 19:49:05','lidihao','2019-12-29 19:49:10','lidihao',_binary '\0'),(1001,1000,1001,'2019-12-29 19:50:47','lidihao','2019-12-29 19:50:54','lidihao',_binary '\0'),(1002,1002,1000,'2019-12-29 21:38:57','lidihao','2019-12-29 21:39:03','lidihao',_binary '\0'),(1003,1002,1001,'2019-12-29 21:40:49','lidihao','2019-12-29 21:41:09','lidihao',_binary '\0'),(1004,1002,1002,'2019-12-29 21:40:51','lidihao','2019-12-29 21:41:11','lidihao',_binary '\0'),(1005,1002,1003,'2019-12-29 21:40:51','lidihao','2019-12-29 21:41:12','lidihao',_binary '\0'),(1018,1002,1008,'2020-03-06 21:12:47','lidihao','2020-03-06 21:12:51','lidihao',_binary '\0'),(1019,1002,1009,'2020-03-06 21:12:48','lidihao','2020-03-06 21:13:00','lidihao',_binary '\0'),(1021,1000,1010,'2020-03-13 20:28:05','lidihao','2020-03-13 20:28:32','lidihao',_binary '\0'),(1023,1001,1000,NULL,NULL,NULL,NULL,_binary '\0'),(1024,1001,1001,NULL,NULL,NULL,NULL,_binary '\0'),(1025,1001,1002,NULL,NULL,NULL,NULL,_binary '\0'),(1026,1001,1003,NULL,NULL,NULL,NULL,_binary '\0'),(1027,1001,1004,NULL,NULL,NULL,NULL,_binary '\0'),(1028,1001,1005,NULL,NULL,NULL,NULL,_binary '\0'),(1029,1001,1006,NULL,NULL,NULL,NULL,_binary '\0'),(1030,1001,1007,NULL,NULL,NULL,NULL,_binary '\0'),(1031,1001,1008,NULL,NULL,NULL,NULL,_binary '\0'),(1032,1001,1009,NULL,NULL,NULL,NULL,_binary '\0'),(1033,1001,1010,NULL,NULL,NULL,NULL,_binary '\0'),(1034,1001,1013,NULL,NULL,NULL,NULL,_binary '\0'),(1036,1002,1004,NULL,NULL,NULL,NULL,_binary '\0'),(1037,1002,1005,NULL,NULL,NULL,NULL,_binary '\0'),(1040,1002,1010,NULL,NULL,NULL,NULL,_binary '\0'),(1041,1001,1014,NULL,NULL,NULL,NULL,_binary '\0'),(1042,1001,1015,NULL,NULL,NULL,NULL,_binary '\0'),(1043,1002,1015,NULL,NULL,NULL,NULL,_binary '\0'),(1044,1001,1016,NULL,NULL,NULL,NULL,_binary '\0'),(1045,1002,1016,NULL,NULL,NULL,NULL,_binary '\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='视频标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1000,'linux','linux','2020-01-02 21:00:13','lidihao','2020-01-02 21:00:19','liidhao',0),(1001,'猛男必看','猛男必看','2020-01-02 21:00:48','lidihao','2020-01-02 21:00:54','lidihao',0),(1002,'丧尸','丧尸','2020-03-10 17:18:29','lidihao','2020-03-10 17:18:48','lidihao',0),(1003,'灾难','灾难','2020-03-10 17:18:31','lidihao','2020-03-10 17:18:49','lidihao',0),(1004,'日韩','日韩','2020-03-10 17:18:33','lidihao','2020-03-10 17:18:55','lidihao',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=1043 DEFAULT CHARSET=utf8 COMMENT='标签与视频申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_video_approval`
--

LOCK TABLES `tag_video_approval` WRITE;
/*!40000 ALTER TABLE `tag_video_approval` DISABLE KEYS */;
INSERT INTO `tag_video_approval` VALUES (1006,1000,1004),(1007,1001,1004),(1008,1000,1005),(1009,1001,1005),(1010,1000,1006),(1011,1001,1006),(1012,1000,1007),(1013,1001,1007),(1014,1000,1008),(1015,1001,1008),(1016,1000,1009),(1017,1001,1009),(1018,1000,1010),(1019,1001,1010),(1020,1000,1011),(1021,1001,1011),(1022,1000,1012),(1023,1001,1012),(1024,1000,1013),(1025,1001,1013),(1026,1000,1014),(1027,1001,1014),(1028,1000,1015),(1029,1001,1015),(1030,1000,1016),(1031,1001,1016),(1032,1000,1017),(1033,1000,1018),(1034,1000,1019),(1035,1000,1020),(1036,1001,1020),(1037,1000,1021),(1038,1001,1021),(1039,1002,1022),(1040,1003,1022),(1041,1004,1022),(1042,1004,1023);
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
  `user_skin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `mail` (`email`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1019 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1012,'lidihao','68f15af1da571718b86a3407b563af5a','ba8412810fa83de8c190b122e1bd19b6','18302026070@163.com','hello i am lidihao',1206,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0',1186),(1013,'lidihao3','5ebc5d8bdf09a31f45535846c8438e0f','4050e4ad2020890280592f8d7a65f993','23154475801@qq.com','',1160,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0',1160),(1014,'lidihao2','ab6e6e36c0876fa2eeba4f2380b34cc7','a258a7c583b97e5b77524cf4271d15a0','23154475804@qq.com','',1160,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0',1160),(1015,'lidihao4','e698e39d3f54df8201e3bec981aea076','7c66402810d0bef80c95e0e5e6a68561','23154475805@qq.com','kdjfkdjfkdfk k',1160,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0',1185),(1016,'lidihao5','7628b189ecc2758e8ee9a7b477b22c4c','fbb71fd1cb756200bdfa893bf3826b79','23154475808@qq.com','',1205,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0',1160),(1017,'lidihao10','07390244c8eceb64440a8f8166c9deac','5b3a1491c2467e531673e27460ff6664','19875713836@qq.com','',1000,_binary '',NULL,NULL,NULL,NULL,NULL,_binary '\0',1160),(1018,'lidihao11','5db5a73c199f112a56eb1d516a37263c','df350fa47ac5d750f721a0bfdac35449','2315447580@qq.com','',1000,_binary '\0',NULL,NULL,NULL,NULL,NULL,_binary '\0',1160);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_msg_mapping`
--

DROP TABLE IF EXISTS `user_msg_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_msg_mapping` (
  `mapping_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `un_read` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否已读 (0：未读 1：已读)',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`mapping_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1071 DEFAULT CHARSET=utf8 COMMENT='私信消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_msg_mapping`
--

LOCK TABLES `user_msg_mapping` WRITE;
/*!40000 ALTER TABLE `user_msg_mapping` DISABLE KEYS */;
INSERT INTO `user_msg_mapping` VALUES (1003,1012,1013,6,'2020-03-06 15:25:13',_binary '\0',_binary '\0'),(1004,1013,1012,6,'2020-03-06 15:25:14',_binary '\0',_binary '\0'),(1005,1012,1013,7,'2020-03-06 15:27:15',_binary '\0',_binary '\0'),(1006,1013,1012,7,'2020-03-06 15:27:15',_binary '\0',_binary '\0'),(1007,1012,1013,8,'2020-03-06 15:37:59',_binary '\0',_binary '\0'),(1008,1013,1012,8,'2020-03-06 15:37:59',_binary '\0',_binary '\0'),(1009,1013,1012,9,'2020-03-06 15:39:08',_binary '\0',_binary '\0'),(1010,1012,1013,9,'2020-03-06 15:39:08',_binary '\0',_binary '\0'),(1011,1013,1012,10,'2020-03-06 15:39:50',_binary '\0',_binary '\0'),(1012,1012,1013,10,'2020-03-06 15:39:50',_binary '\0',_binary '\0'),(1013,1013,1012,11,'2020-03-06 17:31:49',_binary '\0',_binary '\0'),(1014,1012,1013,11,'2020-03-06 17:31:49',_binary '\0',_binary '\0'),(1015,1013,1012,12,'2020-03-06 17:32:45',_binary '\0',_binary '\0'),(1016,1012,1013,12,'2020-03-06 17:32:45',_binary '\0',_binary '\0'),(1017,1014,1012,13,'2020-03-06 21:24:11',_binary '\0',_binary '\0'),(1018,1012,1014,13,'2020-03-06 21:24:11',_binary '\0',_binary '\0'),(1019,1014,1012,14,'2020-03-06 21:24:52',_binary '\0',_binary '\0'),(1020,1012,1014,14,'2020-03-06 21:24:52',_binary '\0',_binary '\0'),(1021,1012,1014,15,'2020-03-06 21:29:46',_binary '\0',_binary '\0'),(1022,1014,1012,15,'2020-03-06 21:29:46',_binary '\0',_binary '\0'),(1023,1012,1014,16,'2020-03-06 21:41:53',_binary '\0',_binary '\0'),(1024,1014,1012,16,'2020-03-06 21:41:53',_binary '\0',_binary '\0'),(1025,1014,1012,17,'2020-03-06 22:03:51',_binary '\0',_binary '\0'),(1026,1012,1014,17,'2020-03-06 22:03:51',_binary '\0',_binary '\0'),(1027,1015,1012,18,'2020-03-06 22:07:56',_binary '\0',_binary '\0'),(1028,1012,1015,18,'2020-03-06 22:07:56',_binary '\0',_binary '\0'),(1029,1015,1012,19,'2020-03-09 17:06:21',_binary '\0',_binary '\0'),(1030,1012,1015,19,'2020-03-09 17:06:21',_binary '\0',_binary '\0'),(1031,1013,1012,20,'2020-03-12 10:52:36',_binary '\0',_binary '\0'),(1032,1012,1013,20,'2020-03-12 10:52:36',_binary '\0',_binary '\0'),(1033,1013,1012,21,'2020-03-12 10:55:10',_binary '\0',_binary '\0'),(1034,1012,1013,21,'2020-03-12 10:55:10',_binary '\0',_binary '\0'),(1035,1013,1012,22,'2020-03-12 11:13:58',_binary '\0',_binary '\0'),(1036,1012,1013,22,'2020-03-12 11:13:58',_binary '\0',_binary '\0'),(1037,1015,1012,23,'2020-03-12 11:20:12',_binary '\0',_binary '\0'),(1038,1012,1015,23,'2020-03-12 11:20:12',_binary '\0',_binary '\0'),(1039,1015,1012,24,'2020-03-12 11:22:15',_binary '\0',_binary '\0'),(1040,1012,1015,24,'2020-03-12 11:22:15',_binary '\0',_binary '\0'),(1041,1013,1012,25,'2020-03-12 11:25:40',_binary '\0',_binary '\0'),(1042,1012,1013,25,'2020-03-12 11:25:40',_binary '\0',_binary '\0'),(1043,1015,1012,26,'2020-03-12 11:58:58',_binary '\0',_binary '\0'),(1044,1012,1015,26,'2020-03-12 11:58:58',_binary '\0',_binary '\0'),(1045,1015,1012,27,'2020-03-12 12:02:45',_binary '\0',_binary '\0'),(1046,1012,1015,27,'2020-03-12 12:02:45',_binary '\0',_binary '\0'),(1047,1013,1012,28,'2020-03-12 12:03:51',_binary '\0',_binary '\0'),(1048,1012,1013,28,'2020-03-12 12:03:51',_binary '\0',_binary '\0'),(1049,1015,1012,29,'2020-03-12 12:20:56',_binary '\0',_binary '\0'),(1050,1012,1015,29,'2020-03-12 12:20:56',_binary '\0',_binary '\0'),(1051,1013,1012,30,'2020-03-12 12:24:22',_binary '\0',_binary '\0'),(1052,1012,1013,30,'2020-03-12 12:24:22',_binary '\0',_binary '\0'),(1053,1015,1012,31,'2020-03-12 12:28:20',_binary '\0',_binary '\0'),(1054,1012,1015,31,'2020-03-12 12:28:20',_binary '\0',_binary '\0'),(1055,1015,1012,32,'2020-03-12 12:28:37',_binary '\0',_binary '\0'),(1056,1012,1015,32,'2020-03-12 12:28:37',_binary '\0',_binary '\0'),(1057,1016,1012,33,'2020-03-12 12:34:20',_binary '\0',_binary '\0'),(1058,1012,1016,33,'2020-03-12 12:34:20',_binary '\0',_binary '\0'),(1059,1014,1012,34,'2020-03-12 12:36:12',_binary '\0',_binary '\0'),(1060,1012,1014,34,'2020-03-12 12:36:12',_binary '\0',_binary '\0'),(1061,1016,1012,35,'2020-03-12 12:57:57',_binary '\0',_binary '\0'),(1062,1012,1016,35,'2020-03-12 12:57:57',_binary '\0',_binary '\0'),(1063,1016,1012,36,'2020-03-12 12:58:37',_binary '\0',_binary '\0'),(1064,1012,1016,36,'2020-03-12 12:58:37',_binary '\0',_binary '\0'),(1065,1016,1012,37,'2020-03-12 12:58:40',_binary '\0',_binary '\0'),(1066,1012,1016,37,'2020-03-12 12:58:40',_binary '\0',_binary '\0'),(1069,1012,1016,39,'2020-03-12 15:58:11',_binary '\0',_binary '\0'),(1070,1016,1012,39,'2020-03-12 15:58:11',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `user_msg_mapping` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1000,1012,1001,'2019-12-29 21:43:07','lidihao','2019-12-29 21:43:09','lidihao',_binary '\0'),(1001,1013,1001,'2020-03-01 22:24:34','lidihao','2020-03-01 22:24:39','lidihao',_binary '\0'),(1002,1014,1002,NULL,NULL,NULL,NULL,_binary '\0'),(1003,1015,1002,NULL,NULL,NULL,NULL,_binary '\0'),(1004,1016,1002,NULL,NULL,NULL,NULL,_binary '\0');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_skin`
--

DROP TABLE IF EXISTS `user_skin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_skin` (
  `user_skin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '主键',
  `picture_id` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  PRIMARY KEY (`user_skin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1013 DEFAULT CHARSET=utf8 COMMENT='上传的文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_skin`
--

LOCK TABLES `user_skin` WRITE;
/*!40000 ALTER TABLE `user_skin` DISABLE KEYS */;
INSERT INTO `user_skin` VALUES (1002,1015,1177),(1010,1015,1185),(1011,1012,1186),(1012,1012,1191);
/*!40000 ALTER TABLE `user_skin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `video_title` varchar(100) NOT NULL COMMENT '视频题目',
  `video_poster_id` int(11) DEFAULT NULL COMMENT '视频海报url',
  `video_play_count` bigint(20) NOT NULL COMMENT '视频播放数',
  `video_comment_person` bigint(20) NOT NULL COMMENT '视频评论人数',
  `video_desc` varchar(400) NOT NULL COMMENT '视频简介',
  `video_rate` double NOT NULL DEFAULT '0' COMMENT '视频的评分,用于视频的推荐',
  `upload_user_id` int(11) NOT NULL COMMENT '视频上传user_id',
  `category_id` int(11) NOT NULL COMMENT '视频的类别',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`video_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8 COMMENT='视频详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1001,'生活琐事',1094,6,0,'测试',3,1012,1001,NULL,NULL,NULL,NULL,0),(1002,'1生活琐事1',1094,5,0,'测试1',5,1012,1001,NULL,NULL,NULL,NULL,0),(1003,'测试11',1112,7,0,'测试',3.3333333333333335,1012,1000,NULL,NULL,NULL,NULL,0),(1004,'而是11',1118,11,0,'Ces ',0,1012,1000,NULL,NULL,NULL,NULL,0),(1005,'测试是',1128,51,0,'测试',1.1888888888888889,1012,1000,'2020-02-26 14:44:23','lidihao','2020-02-26 14:44:23','lidihao',0),(1006,'比比东',1190,25,0,'测试',4,1012,1001,'2020-03-08 22:30:22','lidihao','2020-03-08 22:30:22','lidihao',0),(1007,'釜山行',1198,27,0,'       证券公司基金管理人石宇（孔刘 饰）光鲜精干，却也是个重利轻义之徒。妻子为此与之决裂，女儿秀安（金秀安 饰）则对如此自私的父亲越来越失望，决定前往釜山和母亲生活。',5,1015,1002,'2020-03-10 18:06:14','lidihao','2020-03-10 18:06:14','lidihao',0),(1008,'梨泰院',1203,8,0,'梨泰院class',0,1015,1003,'2020-03-11 00:05:02','lidihao','2020-03-17 11:17:02','lidihao',0);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
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
  `remark` varchar(200) DEFAULT '',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`video_approval_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COMMENT='视频审批';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_approval`
--

LOCK TABLES `video_approval` WRITE;
/*!40000 ALTER TABLE `video_approval` DISABLE KEYS */;
INSERT INTO `video_approval` VALUES (1015,1012,1094,'1生活琐事1','测试1',1001,-1,1,'同意','2020-01-12 11:05:36','lidihao','2020-01-12 11:05:36','lidihao',0),(1016,1012,1107,'测试','测试',1000,-1,0,'','2020-01-15 11:48:56','lidihao','2020-01-15 11:48:56','lidihao',0),(1017,1012,1108,'测试1','测试1',1000,-1,2,'','2020-01-15 12:02:27','lidihao','2020-01-15 12:02:27','lidihao',0),(1018,1012,1112,'测试11','测试',1000,-1,1,'同意','2020-01-16 05:06:39','lidihao','2020-01-16 05:06:39','lidihao',0),(1019,1012,1118,'而是11','Ces ',1000,1004,1,'同意\n','2020-01-16 07:37:58','lidihao','2020-01-16 07:37:58','lidihao',0),(1020,1012,1128,'测试是','测试',1000,1005,1,'通过','2020-01-16 08:17:15','lidihao','2020-01-16 08:17:15','lidihao',0),(1021,1012,1190,'比比东','测试',1001,1006,1,'通过','2020-03-08 22:09:37','lidihao','2020-03-08 22:09:37','lidihao',0),(1022,1015,1198,'釜山行','       证券公司基金管理人石宇（孔刘 饰）光鲜精干，却也是个重利轻义之徒。妻子为此与之决裂，女儿秀安（金秀安 饰）则对如此自私的父亲越来越失望，决定前往釜山和母亲生活。',1002,1007,1,'通过','2020-03-10 17:28:01','lidihao4','2020-03-10 17:28:01','lidihao4',0),(1023,1015,1203,'梨泰院class','梨泰院class',1003,1008,1,'pass','2020-03-11 00:03:29','lidihao4','2020-03-11 00:03:29','lidihao4',0);
/*!40000 ALTER TABLE `video_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_comment`
--

DROP TABLE IF EXISTS `video_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `comment_content` text NOT NULL COMMENT '评论内容',
  `comment_up` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `comment_down` int(11) NOT NULL DEFAULT '0' COMMENT '踩的人数',
  `comment_user_id` int(11) NOT NULL COMMENT '评论人',
  `rate` double NOT NULL DEFAULT '0' COMMENT '评分',
  `video_id` int(11) NOT NULL COMMENT '视频id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`comment_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1052 DEFAULT CHARSET=utf8 COMMENT='视频评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_comment`
--

LOCK TABLES `video_comment` WRITE;
/*!40000 ALTER TABLE `video_comment` DISABLE KEYS */;
INSERT INTO `video_comment` VALUES (1000,'test test',0,0,1012,0,1005,'2020-02-28 21:13:25','lidihao','2020-02-28 21:13:34','lidihao',0),(1001,'dfsdf d f',0,0,1012,0,1005,'2020-02-29 06:27:48','lidihao','2020-02-29 06:27:48','lidihao',0),(1002,'dfsdf d fdfdf ',0,0,1012,0,1005,'2020-02-29 06:28:13','lidihao','2020-02-29 06:28:13','lidihao',0),(1003,'sdfdt',0,0,1012,0,1005,'2020-02-29 06:29:44','lidihao','2020-02-29 06:29:44','lidihao',0),(1004,'test',0,0,1012,0,1005,'2020-02-29 06:36:29','lidihao','2020-02-29 06:36:29','lidihao',0),(1005,'tets234',0,0,1012,0,1005,'2020-02-29 06:36:54','lidihao','2020-02-29 06:36:54','lidihao',0),(1006,'tets234',0,0,1012,0,1005,'2020-02-29 06:37:13','lidihao','2020-02-29 06:37:13','lidihao',0),(1007,'csdfd f df dfsdfadf',0,0,1012,0,1005,'2020-02-29 06:38:09','lidihao','2020-02-29 06:38:09','lidihao',0),(1008,'csdfd f df dfsdfadf 4343',0,0,1012,0,1005,'2020-02-29 06:38:17','lidihao','2020-02-29 06:38:17','lidihao',0),(1009,'csdfd f df dfsdfadf 4343dfer ',0,0,1012,0,1005,'2020-02-29 06:38:23','lidihao','2020-02-29 06:38:23','lidihao',0),(1010,'csdfd f df dfsdfadf 4343dfer erere ',0,0,1012,0,1005,'2020-02-29 06:38:46','lidihao','2020-02-29 06:38:46','lidihao',0),(1011,'erere ',0,0,1012,0,1005,'2020-02-29 06:47:04','lidihao','2020-02-29 06:47:04','lidihao',0),(1012,'erere 12322213',0,0,1012,0,1005,'2020-02-29 06:47:17','lidihao','2020-02-29 06:47:17','lidihao',0),(1013,'erere 12322213dfdfdf',0,0,1012,0,1005,'2020-02-29 06:47:27','lidihao','2020-02-29 06:47:27','lidihao',0),(1014,'ere',0,0,1012,0,1005,'2020-02-29 06:47:39','lidihao','2020-02-29 06:47:39','lidihao',0),(1015,'eredfer',0,0,1012,0,1005,'2020-02-29 06:48:19','lidihao','2020-02-29 06:48:19','lidihao',0),(1016,'fsfer',0,0,1012,0,1005,'2020-02-29 06:52:00','lidihao','2020-02-29 06:52:00','lidihao',0),(1017,'fsferdfd dfdfdfdssfe432243',0,0,1012,0,1005,'2020-02-29 06:55:06','lidihao','2020-02-29 06:55:06','lidihao',0),(1018,'fsferdfd dfdfdfdssfe432243er343434',0,0,1012,0,1005,'2020-02-29 06:55:16','lidihao','2020-02-29 06:55:16','lidihao',0),(1019,'fsferdfd dfdfdfdssfe432243er3434343434',0,0,1012,0,1005,'2020-02-29 06:55:25','lidihao','2020-02-29 06:55:25','lidihao',0),(1020,'34343erdfsdf',0,0,1012,0,1005,'2020-02-29 06:55:54','lidihao','2020-02-29 06:55:54','lidihao',0),(1021,'34343erdfsdf dr334343434',0,0,1012,0,1005,'2020-02-29 06:56:03','lidihao','2020-02-29 06:56:03','lidihao',0),(1022,'34343erdfsdf dr334343434ererer3434324',0,0,1012,0,1005,'2020-02-29 06:56:28','lidihao','2020-02-29 06:56:28','lidihao',0),(1023,'5545',0,0,1012,0,1005,'2020-02-29 07:00:40','lidihao','2020-02-29 07:00:40','lidihao',0),(1024,'5454',0,0,1012,0,1005,'2020-02-29 07:00:50','lidihao','2020-02-29 07:00:50','lidihao',0),(1025,'dfdf',0,0,1012,0,1005,'2020-02-29 07:03:03','lidihao','2020-02-29 07:03:03','lidihao',0),(1026,'dfdf343434',0,0,1012,0,1005,'2020-02-29 07:03:20','lidihao','2020-02-29 07:03:20','lidihao',0),(1027,'ererer34',0,0,1012,0,1005,'2020-02-29 07:18:30','lidihao','2020-02-29 07:18:30','lidihao',0),(1028,'fdfdfdf',0,0,1012,0,1005,'2020-02-29 07:20:57','lidihao','2020-02-29 07:20:57','lidihao',0),(1029,'erer',0,0,1012,0,1005,'2020-02-29 07:57:44','lidihao','2020-02-29 07:57:44','lidihao',0),(1030,'dfdfd',0,0,1013,0,1005,'2020-03-01 14:26:14','lidihao3','2020-03-01 14:26:14','lidihao3',0),(1031,'dfsdfdf',0,0,1012,0,1005,'2020-03-08 17:37:32','lidihao','2020-03-08 17:37:32','lidihao',0),(1032,'test343',0,0,1012,2.5,1005,'2020-03-08 18:19:26','lidihao','2020-03-08 18:19:26','lidihao',0),(1033,'fdfdf',0,0,1012,5,1005,'2020-03-08 18:20:28','lidihao','2020-03-08 18:20:28','lidihao',0),(1034,'dfdss',0,0,1012,5,1005,'2020-03-08 18:20:36','lidihao','2020-03-08 18:20:36','lidihao',0),(1035,'dfdsfdfdf',0,0,1012,5,1005,'2020-03-08 18:20:43','lidihao','2020-03-08 18:20:43','lidihao',0),(1036,'dfdsfffffffff',0,0,1012,5,1005,'2020-03-08 18:20:49','lidihao','2020-03-08 18:20:49','lidihao',0),(1037,'dfsfdsfd',0,0,1012,5,1005,'2020-03-08 18:20:54','lidihao','2020-03-08 18:20:54','lidihao',0),(1038,'dfsdfdf',0,0,1012,5,1005,'2020-03-08 18:21:00','lidihao','2020-03-08 18:21:00','lidihao',0),(1039,'fdfdf',0,0,1012,5,1005,'2020-03-08 18:21:41','lidihao','2020-03-08 18:21:41','lidihao',0),(1040,'dfsdfd',0,0,1012,4.5,1005,'2020-03-08 18:21:57','lidihao','2020-03-08 18:21:57','lidihao',0),(1041,'fdfdf',0,0,1012,3,1005,'2020-03-08 18:22:05','lidihao','2020-03-08 18:22:05','lidihao',0),(1042,'dfdf',0,0,1012,3,1005,'2020-03-08 21:38:59','lidihao','2020-03-08 21:38:59','lidihao',0),(1043,'dfdfdf',0,0,1012,2.5,1005,'2020-03-08 21:39:08','lidihao','2020-03-08 21:39:08','lidihao',0),(1044,'测试',0,0,1012,4,1006,'2020-03-08 22:32:36','lidihao','2020-03-08 22:32:36','lidihao',0),(1045,'lidihao4',0,0,1015,3,1005,'2020-03-09 15:40:28','lidihao4','2020-03-09 15:40:28','lidihao4',0),(1046,'lidihao',0,0,1012,3,1001,'2020-03-09 20:41:46','lidihao','2020-03-09 20:41:46','lidihao',0),(1047,'好看啊',0,0,1012,5,1007,'2020-03-10 18:09:58','lidihao','2020-03-10 18:09:58','lidihao',0),(1048,'tst',0,0,1012,5,1002,'2020-03-13 20:32:02','lidihao','2020-03-13 20:32:02','lidihao',0),(1049,'test',0,0,1012,3.5,1003,'2020-03-13 22:37:32','lidihao','2020-03-13 22:37:32','lidihao',0),(1050,'tst',0,0,1012,2.5,1003,'2020-03-15 12:52:57','lidihao','2020-03-15 12:52:57','lidihao',0),(1051,'resr',0,0,1012,4,1003,'2020-03-15 12:54:38','lidihao','2020-03-15 12:54:38','lidihao',0);
/*!40000 ALTER TABLE `video_comment` ENABLE KEYS */;
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
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `video_apply_id` int(11) DEFAULT '-1' COMMENT '审批Id',
  `size` bigint(20) NOT NULL COMMENT '大小',
  `file_name` varchar(100) NOT NULL COMMENT '名字',
  `approval_type` int(11) NOT NULL DEFAULT '0' COMMENT '审批状态',
  `file_url` varchar(100) NOT NULL COMMENT '视频url',
  `file_type` varchar(50) NOT NULL COMMENT '视频类型',
  `poster_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`video_file_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=1067 DEFAULT CHARSET=utf8 COMMENT='上传的文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_file`
--

LOCK TABLES `video_file` WRITE;
/*!40000 ALTER TABLE `video_file` DISABLE KEYS */;
INSERT INTO `video_file` VALUES (1000,1012,0,-1,26049082,'VID_20190207_125557.mp4',0,'/video/a3d6f601a9714f2984e177a3cf33748d7378870285752026536.mp4','video/mp4\n',1093,NULL,NULL,NULL,NULL,0),(1001,1012,2,1015,7645567,'VID_20190207_125723.mp4',1,'/video/d9bbc7f0ead44b378cb99cc656ac22166335017380459127678.mp4','video/mp4\n',1095,NULL,NULL,NULL,NULL,0),(1003,1012,0,1015,33839497,'VID_20190207_125532.mp4',1,'/video/99179c16ac264a4195a334c7f11212a84719428600086586797.mp4','video/mp4\n',1097,NULL,NULL,NULL,NULL,0),(1004,1012,3,1015,14449411,'VID_20190207_130034.mp4',1,'/video/4f389b497bde455da3591a44229b037e4937064999930495541.mp4','video/mp4\n',1098,NULL,NULL,NULL,NULL,0),(1005,1012,5,1015,10660331,'VID_20190207_130554.mp4',1,'/video/3e8b1491c2b14f729f2d660ffa188c994635000571410347613.mp4','video/mp4\n',1099,NULL,NULL,NULL,NULL,0),(1007,1012,7,1015,9507448,'VID_20190207_130727.mp4',1,'/video/8ad3f000164d4cf4b396499fba075eac6212010444927785473.mp4','video/mp4\n',1102,NULL,NULL,NULL,NULL,0),(1008,1012,6,1015,8484480,'VID_20190207_130707.mp4',1,'/video/10e66fc1268b434183c91ad84118425a7304397911935318314.mp4','video/mp4\n',1101,NULL,NULL,NULL,NULL,0),(1013,1012,2,1017,14449411,'VID_20190207_130034.mp4',2,'/video/3efb03c08f3a48eb8040231be050be087418863667515869907.mp4','video/mp4\n',1109,NULL,NULL,NULL,NULL,0),(1014,1012,1,1017,7645567,'VID_20190207_125723.mp4',2,'/video/4c6b9dabfc134092be44d0f8522a3dda4846426803094691074.mp4','video/mp4\n',1110,NULL,NULL,NULL,NULL,0),(1015,1012,0,1017,26049082,'VID_20190207_125557.mp4',2,'/video/c1d24ccb7e494a81bf77e482234c61455965323331965160722.mp4','video/mp4\n',1111,NULL,NULL,NULL,NULL,0),(1022,1012,3,1019,14449411,'VID_20190207_130034.mp4',1,'/video/ed322f3ca423446fae52133d441fd2171986915009148206589.mp4','video/mp4\n',1120,NULL,NULL,NULL,NULL,0),(1023,1012,1,1019,26049082,'VID_20190207_125557.mp4',1,'/video/40818b000b96468890b2c4eeac3143231398275183464223406.mp4','video/mp4\n',1122,NULL,NULL,NULL,NULL,0),(1024,1012,2,1019,7645567,'VID_20190207_125723.mp4',1,'/video/52e326f7c1b04bd6b681202628b9f4fb1980762269386073870.mp4','video/mp4\n',1119,NULL,NULL,NULL,NULL,0),(1025,1012,0,1020,26049082,'VID_20190207_125557.mp4',1,'/video/bec6179a58784b399249889caa0c6c022831408918205399250.mp4','video/mp4\n',1125,NULL,NULL,NULL,NULL,0),(1026,1012,2,1020,14449411,'VID_20190207_130034.mp4',1,'/video/6d1850a9b124404ba0be6b98cb681ed97224517055446610720.mp4','video/mp4\n',1124,NULL,NULL,NULL,NULL,0),(1027,1012,1,1020,7645567,'VID_20190207_125723.mp4',1,'/video/c1c02d2cc8b84a299b9f5bdb92a75d6f6185226812503329167.mp4','video/mp4\n',1126,NULL,NULL,NULL,NULL,0),(1028,1012,3,1020,15835391,'VID_20190207_130542.mp4',1,'/video/1d06e1d03bb54f9c9b859a90d58d3f8c8210058301795740238.mp4','video/mp4\n',1127,NULL,NULL,NULL,NULL,0),(1029,1012,0,-1,7645567,'VID_20190207_125723.mp4',0,'/video/4a9e24c60a9944c790824bb1fe38d6f9563894028666827032.mp4','video/mp4\n',1139,NULL,NULL,NULL,NULL,0),(1030,1012,0,-1,26049082,'VID_20190207_125557.mp4',0,'/video/a998bd42d4f242b6b1a9d31358db37ec1016277414204121865.mp4','video/mp4\n',1138,NULL,NULL,NULL,NULL,0),(1031,1012,0,-1,14449411,'VID_20190207_130034.mp4',0,'/video/d53ad25554b143f29f75272c0d0a445a7204351971598404526.mp4','video/mp4\n',1135,NULL,NULL,NULL,NULL,0),(1032,1012,0,-1,10660331,'VID_20190207_130554.mp4',0,'/video/eeb6d6a86d12420394ea42128d8ccd4d5990596211719850411.mp4','video/mp4\n',1134,NULL,NULL,NULL,NULL,0),(1033,1012,0,-1,15835391,'VID_20190207_130542.mp4',0,'/video/60df2a7a2bde4c11899ae48a261c78e98737945323958656523.mp4','video/mp4\n',1136,NULL,NULL,NULL,NULL,0),(1034,1012,0,-1,33839497,'VID_20190207_125532.mp4',0,'/video/c16a684593b24dc09b5667fe3b5681d93505250620324388689.mp4','video/mp4\n',1137,NULL,NULL,NULL,NULL,0),(1035,1012,0,-1,9507448,'VID_20190207_130727.mp4',0,'/video/f75360b3d5be49888787ef0da776860b6246396106355628217.mp4','video/mp4\n',1141,NULL,NULL,NULL,NULL,0),(1036,1012,0,-1,8484480,'VID_20190207_130707.mp4',0,'/video/e979f334f6a2420f9183c4565183b26a4838372160440003734.mp4','video/mp4\n',1140,NULL,NULL,NULL,NULL,0),(1037,1012,0,-1,33839497,'VID_20190207_125532.mp4',0,'/video/0c9e27d5ad41436ba17470b0eed8c4984679532886523069233.mp4','video/mp4\n',1142,NULL,NULL,NULL,NULL,0),(1038,1012,0,-1,33839497,'99179c16ac264a4195a334c7f11212a84719428600086586797 (1).mp4',0,'/video/2690f72d5fbd4f4c912e86cfe1f898383930418027731264987.mp4','video/mp4\n',1143,NULL,NULL,NULL,NULL,0),(1039,1012,0,-1,26049082,'VID_20190207_125557.mp4',0,'/video/0dfc3de8dd5e44868a367f4f70f2e5837919406216910505907.mp4','video/mp4\n',1144,NULL,NULL,NULL,NULL,0),(1040,1012,0,-1,7645567,'VID_20190207_125723.mp4',0,'/video/b9354c2d24674507ac7606c0496982235110196417054821419.mp4','video/mp4\n',1145,NULL,NULL,NULL,NULL,0),(1041,1012,0,-1,26049082,'VID_20190207_125557.mp4',0,'/video/bcb45ec223b14b4c83483b318954427f5339271231332124032.mp4','video/mp4\n',1146,NULL,NULL,NULL,NULL,0),(1043,1012,3,1019,33839497,'VID_20190207_125532.mp4',1,'/video/666d1181618c4a8ca8bd412bd052f77d7343805485469508635.mp4','video/mp4\n',1149,NULL,NULL,NULL,NULL,0),(1044,1012,5,1019,7645567,'VID_20190207_125723.mp4',1,'/video/dcf189f0578540d696754a4903af7c8b3843066954976860564.mp4','video/mp4\n',1150,NULL,NULL,NULL,NULL,0),(1045,1012,4,1019,26049082,'VID_20190207_125557.mp4',1,'/video/2d13529bf94e46cb9854d475438554fb4215055440569864708.mp4','video/mp4\n',1151,NULL,NULL,NULL,NULL,0),(1046,1012,6,1019,14449411,'VID_20190207_130034.mp4',1,'/video/6efc55a0b4ca477ea5fee935858a3f2f6210840330631794261.mp4','video/mp4\n',1152,NULL,NULL,NULL,NULL,0),(1047,1012,8,1019,10660331,'VID_20190207_130554.mp4',1,'/video/10a9540620ad4dceb8a1f9ff37d7e7841212051924521225752.mp4','video/mp4\n',1153,NULL,NULL,NULL,NULL,0),(1048,1012,7,1019,15835391,'VID_20190207_130542.mp4',1,'/video/fc9d7bfb2f1344d386686c097d5458ce4832869918527251833.mp4','video/mp4\n',1154,NULL,NULL,NULL,NULL,0),(1049,1012,9,1019,8484480,'VID_20190207_130707.mp4',1,'/video/0c4a51f69b6d447a87a6648c1f61fb461747183882728506238.mp4','video/mp4\n',1155,NULL,NULL,NULL,NULL,0),(1050,1012,10,1019,9507448,'VID_20190207_130727.mp4',1,'/video/92f376fc28154e2b969ff211777affd08509154011607079249.mp4','video/mp4\n',1156,NULL,NULL,NULL,NULL,0),(1051,1012,4,1018,33839497,'VID_20190207_125532.mp4',1,'/video/ebefbd4668ca43bbbbe74194ef33d5005075053370173439346.mp4','video/mp4\n',1157,NULL,NULL,NULL,NULL,0),(1052,1012,6,1018,7645567,'VID_20190207_125723.mp4',1,'/video/ccd5d3d6a54c44a8b8e4ab0f5cdb2c0f7491523460478621577.mp4','video/mp4\n',1158,NULL,NULL,NULL,NULL,0),(1053,1012,5,1018,26049082,'VID_20190207_125557.mp4',1,'/video/e39f42edf1914983b778ea428ca626747416454967786961287.mp4','video/mp4\n',1159,NULL,NULL,NULL,NULL,0),(1054,1012,7,1018,14449411,'VID_20190207_130034.mp4',1,'/video/ef5f600fbf614d3992dd0942a306241357644264394753441.mp4','video/mp4\n',1160,NULL,NULL,NULL,NULL,0),(1055,1012,8,1016,8484480,'VID_20190207_130707.mp4',0,'/video/14d42157c9184609bceeabf25ac9ddb23740899080503783990.mp4','video/mp4\n',1161,NULL,NULL,NULL,NULL,0),(1056,1012,6,1016,15835391,'VID_20190207_130542.mp4',0,'/video/8a34e5f47fa14de9bd7a1bbe8eb3e2d31130374040212203550.mp4','video/mp4\n',1163,NULL,NULL,NULL,NULL,0),(1057,1012,7,1016,10660331,'VID_20190207_130554.mp4',0,'/video/0b7555f2ead3427c9ed770c940cab97c372207786386525480.mp4','video/mp4\n',1165,NULL,NULL,NULL,NULL,0),(1058,1012,5,1016,14449411,'VID_20190207_130034.mp4',0,'/video/d03c5f8f80c44fccb9cacc5f47b085111164686817866315970.mp4','video/mp4\n',1164,NULL,NULL,NULL,NULL,0),(1059,1012,4,1016,7645567,'VID_20190207_125723.mp4',0,'/video/6319bb8bcf184b0281693799a3dbc1148439258831112344562.mp4','video/mp4\n',1166,NULL,NULL,NULL,NULL,0),(1060,1012,3,1016,26049082,'VID_20190207_125557.mp4',0,'/video/03c99ae5393b4a54a1d9327bf475f9b98815389805223109573.mp4','video/mp4\n',1162,NULL,NULL,NULL,NULL,0),(1061,1012,2,1021,9507448,'VID_20190207_130727.mp4',1,'/video/93708c5ebd0a4c4387a310e723e823d22791381536355305910.mp4','video/mp4\n',1187,NULL,NULL,NULL,NULL,0),(1062,1012,0,1021,33839497,'99179c16ac264a4195a334c7f11212a84719428600086586797.mp4',1,'/video/3b6c5804602c4e8192eb39bf655a40ac4324224106763935091.mp4','video/mp4\n',1188,NULL,NULL,NULL,NULL,0),(1063,1012,1,1021,7645567,'VID_20190207_125723.mp4',1,'/video/512811a554ae48d58a3e46b47b425c718772921870661899308.mp4','video/mp4\n',1189,NULL,NULL,NULL,NULL,0),(1064,1015,0,1022,2618502461,'釜山行.mp4',1,'/video/2f5cb616028c49f7b3962bcb15a1f2782876797546041168523.mp4','video/mp4\n',1199,NULL,NULL,NULL,NULL,0),(1065,1015,0,-1,771620103,'[阳光电影-www.ygdy8.com]梨泰院Class-01.mp4',0,'/video/66a6ae3a843246139d49736897168920937603907692469888.mp4','video/mp4\n',1202,NULL,NULL,NULL,NULL,0),(1066,1015,0,1023,771620103,'[阳光电影-www.ygdy8.com]梨泰院Class-01.mp4',1,'/video/b4aa34bd9142400db6f5a0124762ae6d6508881688486300347.mp4','video/mp4\n',1204,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `video_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_tag`
--

DROP TABLE IF EXISTS `video_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_tag` (
  `video_tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `video_id` int(11) NOT NULL COMMENT 'video_id',
  `tag_id` int(11) NOT NULL COMMENT 'tag_id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`video_tag_id`),
  KEY `is_delete` (`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='视频标签关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_tag`
--

LOCK TABLES `video_tag` WRITE;
/*!40000 ALTER TABLE `video_tag` DISABLE KEYS */;
INSERT INTO `video_tag` VALUES (1,1001,1000,NULL,NULL,NULL,NULL,0),(2,1001,1001,NULL,NULL,NULL,NULL,0),(3,1002,1000,NULL,NULL,NULL,NULL,0),(4,1002,1001,NULL,NULL,NULL,NULL,0),(5,1003,1000,NULL,NULL,NULL,NULL,0),(6,1004,1000,NULL,NULL,NULL,NULL,0),(7,1005,1000,NULL,NULL,NULL,NULL,0),(8,1005,1001,NULL,NULL,NULL,NULL,0),(9,1006,1000,NULL,NULL,NULL,NULL,0),(10,1006,1001,NULL,NULL,NULL,NULL,0),(11,1007,1002,NULL,NULL,NULL,NULL,0),(12,1007,1003,NULL,NULL,NULL,NULL,0),(13,1007,1004,NULL,NULL,NULL,NULL,0),(14,1008,1004,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `video_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-18 22:48:08
