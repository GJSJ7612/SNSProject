-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sns_project
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity_body`
--

DROP TABLE IF EXISTS `activity_body`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_body` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `content` longtext COMMENT '活动内容',
  `content_html` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_body`
--

LOCK TABLES `activity_body` WRITE;
/*!40000 ALTER TABLE `activity_body` DISABLE KEYS */;
INSERT INTO `activity_body` VALUES (1,'活动测试',NULL),(2,'不知道写啥了',NULL),(3,'随便写的',NULL);
/*!40000 ALTER TABLE `activity_body` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_brief`
--

DROP TABLE IF EXISTS `activity_brief`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_brief` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_counts` int DEFAULT '0',
  `createDate` datetime DEFAULT NULL,
  `summary` text,
  `title` varchar(255) DEFAULT NULL,
  `view_counts` int DEFAULT '0',
  `weight` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `body_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `status` int DEFAULT '0' COMMENT '0-未开始，1-已结束',
  PRIMARY KEY (`id`),
  KEY `activity_brief_activity_body_id_fk` (`body_id`),
  KEY `activity_brief_activity_catgory_id_fk` (`category_id`),
  KEY `activity_brief_user_id_fk` (`author_id`),
  CONSTRAINT `activity_brief_activity_body_id_fk` FOREIGN KEY (`body_id`) REFERENCES `activity_body` (`id`),
  CONSTRAINT `activity_brief_activity_catgory_id_fk` FOREIGN KEY (`category_id`) REFERENCES `activity_catgory` (`id`),
  CONSTRAINT `activity_brief_user_id_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_brief`
--

LOCK TABLES `activity_brief` WRITE;
/*!40000 ALTER TABLE `activity_brief` DISABLE KEYS */;
INSERT INTO `activity_brief` VALUES (1,0,'2024-07-30 14:02:27','活动测试','活动测试',1005,0,'2024-07-30 14:02:48','2024-07-31 14:02:49',1,1,2,0),(2,0,'2024-07-30 14:03:06','测试2','测试2',684,0,'2024-07-26 14:03:20','2024-07-28 14:04:57',1,2,3,0),(3,0,'2024-07-30 14:09:15','哈哈哈哈','哈哈哈哈',48585,0,'2024-08-01 14:09:49','2024-08-02 14:09:53',1,3,1,0);
/*!40000 ALTER TABLE `activity_brief` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_catgory`
--

DROP TABLE IF EXISTS `activity_catgory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_catgory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `categoryname` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_catgory`
--

LOCK TABLES `activity_catgory` WRITE;
/*!40000 ALTER TABLE `activity_catgory` DISABLE KEYS */;
INSERT INTO `activity_catgory` VALUES (1,NULL,'聚餐',NULL),(2,NULL,'健身',NULL),(3,NULL,'团建',NULL);
/*!40000 ALTER TABLE `activity_catgory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_comment`
--

DROP TABLE IF EXISTS `activity_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `create_date` date DEFAULT NULL,
  `activity_id` int DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `to_user` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_comment_activity_brief_id_fk` (`activity_id`),
  KEY `activity_comment_activity_comment_id_fk` (`parent_id`),
  KEY `activity_comment_user_id_fk` (`author_id`),
  KEY `activity_comment_user_id_fk_2` (`to_user`),
  CONSTRAINT `activity_comment_activity_brief_id_fk` FOREIGN KEY (`activity_id`) REFERENCES `activity_brief` (`id`),
  CONSTRAINT `activity_comment_activity_comment_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `activity_comment` (`id`),
  CONSTRAINT `activity_comment_user_id_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `activity_comment_user_id_fk_2` FOREIGN KEY (`to_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_comment`
--

LOCK TABLES `activity_comment` WRITE;
/*!40000 ALTER TABLE `activity_comment` DISABLE KEYS */;
INSERT INTO `activity_comment` VALUES (1,'评论1','2024-07-30',1,1,NULL,NULL,1),(2,'回复','2024-07-30',1,2,1,1,2);
/*!40000 ALTER TABLE `activity_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_tag`
--

DROP TABLE IF EXISTS `activity_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `tagname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_tag`
--

LOCK TABLES `activity_tag` WRITE;
/*!40000 ALTER TABLE `activity_tag` DISABLE KEYS */;
INSERT INTO `activity_tag` VALUES (1,NULL,'生活'),(2,NULL,'学习'),(3,NULL,'交友');
/*!40000 ALTER TABLE `activity_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_tag_link`
--

DROP TABLE IF EXISTS `activity_tag_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_tag_link` (
  `activity_id` int DEFAULT NULL,
  `tag_id` int DEFAULT NULL,
  KEY `activity_tag_link_activity_brief_id_fk` (`activity_id`),
  KEY `activity_tag_link_activity_tag_id_fk` (`tag_id`),
  CONSTRAINT `activity_tag_link_activity_brief_id_fk` FOREIGN KEY (`activity_id`) REFERENCES `activity_brief` (`id`),
  CONSTRAINT `activity_tag_link_activity_tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `activity_tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_tag_link`
--

LOCK TABLES `activity_tag_link` WRITE;
/*!40000 ALTER TABLE `activity_tag_link` DISABLE KEYS */;
INSERT INTO `activity_tag_link` VALUES (1,1),(1,2),(2,1),(2,3),(3,3);
/*!40000 ALTER TABLE `activity_tag_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '申请操作的ID',
  `uid` int NOT NULL COMMENT '申请者ID',
  `pid` bigint NOT NULL COMMENT '任务ID',
  `bid` int DEFAULT NULL COMMENT '报价',
  `time` date DEFAULT NULL COMMENT '预计完成时间',
  `resume` varchar(127) DEFAULT NULL COMMENT '申请者简历',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_body`
--

DROP TABLE IF EXISTS `article_body`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_body` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '博文主体ID',
  `content` longtext COMMENT '博文主体内容',
  `content_html` longtext COMMENT '博文html内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文主体';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_body`
--

LOCK TABLES `article_body` WRITE;
/*!40000 ALTER TABLE `article_body` DISABLE KEYS */;
INSERT INTO `article_body` VALUES (1,'这是一篇文章',NULL),(2,'*升天*',NULL),(3,'***老闭登***',NULL);
/*!40000 ALTER TABLE `article_body` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_brief`
--

DROP TABLE IF EXISTS `article_brief`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_brief` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '博文ID',
  `comment_counts` int NOT NULL DEFAULT '0' COMMENT '博文评论数',
  `createDate` datetime NOT NULL,
  `summary` text COMMENT '博文概要',
  `title` varchar(255) DEFAULT NULL COMMENT '博文标题',
  `view_counts` int NOT NULL DEFAULT '0' COMMENT '博文观看数',
  `author_id` int DEFAULT NULL COMMENT '博文作者id',
  `body_id` int DEFAULT NULL COMMENT '博文主题ID',
  `category_id` int DEFAULT NULL COMMENT '博文分类ID',
  `weight` int DEFAULT '0',
  `status` int DEFAULT '0' COMMENT '0-未审核，1-已审核，2-拒绝',
  PRIMARY KEY (`id`),
  KEY `article_brief_user_uid_fk` (`author_id`),
  KEY `article_brief_ article_body_id_fk` (`body_id`),
  KEY `article_brief_category_id_fk` (`category_id`),
  CONSTRAINT `article_brief_ article_body_id_fk` FOREIGN KEY (`body_id`) REFERENCES `article_body` (`id`),
  CONSTRAINT `article_brief_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `article_brief_user_uid_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文概要';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_brief`
--

LOCK TABLES `article_brief` WRITE;
/*!40000 ALTER TABLE `article_brief` DISABLE KEYS */;
INSERT INTO `article_brief` VALUES (1,13,'2024-07-27 09:57:43','非常好文章','这是一个文章',114562,1,1,3,0,1),(2,0,'2024-07-27 12:22:17','为什么要演奏春日影','MyGo',1919826,1,2,2,0,1),(3,0,'2022-09-27 16:24:32','古早文章测试','老登',123654791,1,3,1,0,2);
/*!40000 ALTER TABLE `article_brief` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag_link`
--

DROP TABLE IF EXISTS `article_tag_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_tag_link` (
  `article_id` int DEFAULT NULL COMMENT '博文ID',
  `tag_id` int DEFAULT NULL COMMENT '标签ID',
  KEY `article_tag_link_article_brief_id_fk` (`article_id`),
  KEY `article_tag_link_tag_id_fk` (`tag_id`),
  CONSTRAINT `article_tag_link_article_brief_id_fk` FOREIGN KEY (`article_id`) REFERENCES `article_brief` (`id`),
  CONSTRAINT `article_tag_link_tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文与标签关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag_link`
--

LOCK TABLES `article_tag_link` WRITE;
/*!40000 ALTER TABLE `article_tag_link` DISABLE KEYS */;
INSERT INTO `article_tag_link` VALUES (1,2),(1,1),(2,1),(2,3),(3,3);
/*!40000 ALTER TABLE `article_tag_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `avatar` varchar(255) DEFAULT NULL COMMENT '分类图标',
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `description` longtext COMMENT '分类评论',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'static/category/database.png','数据库',NULL),(2,'static/category/front.png','前端',NULL),(3,'static/category/back.png','后端',NULL),(4,'static/category/language.png','人工智能',NULL),(6,'static/category/database.png','数据库',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_article`
--

DROP TABLE IF EXISTS `comment_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `content` text COMMENT '评论内容',
  `create_date` datetime DEFAULT NULL COMMENT '标签创建时间',
  `article_id` int DEFAULT NULL COMMENT '评论所属文章ID',
  `author_id` int DEFAULT NULL COMMENT '评论发布者ID',
  `parent_id` int DEFAULT NULL COMMENT '父评论ID',
  `to_user` int DEFAULT NULL COMMENT '目标用户ID',
  `level` int DEFAULT '1' COMMENT '评论层级',
  PRIMARY KEY (`id`),
  KEY `comment_article_brief_id_fk` (`article_id`),
  KEY `comment_comment_id_fk` (`parent_id`),
  KEY `comment_user_uid_fk` (`author_id`),
  KEY `comment_user_uid_fk_2` (`to_user`),
  CONSTRAINT `comment_article_brief_id_fk` FOREIGN KEY (`article_id`) REFERENCES `article_brief` (`id`),
  CONSTRAINT `comment_comment_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `comment_article` (`id`),
  CONSTRAINT `comment_user_uid_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `comment_user_uid_fk_2` FOREIGN KEY (`to_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_article`
--

LOCK TABLES `comment_article` WRITE;
/*!40000 ALTER TABLE `comment_article` DISABLE KEYS */;
INSERT INTO `comment_article` VALUES (1,'你好','2024-07-28 10:56:46',1,1,NULL,NULL,1),(2,'你也好','2024-07-28 10:57:24',1,2,1,1,2),(3,'我不好','2024-07-28 12:06:16',1,1,1,2,3),(15,'1111','2024-07-29 00:35:55',1,1,NULL,NULL,2),(16,'12345','2024-07-29 00:36:10',1,1,NULL,NULL,3),(17,'666','2024-07-29 00:36:26',1,1,NULL,NULL,4),(18,'444','2024-07-29 09:58:36',1,1,15,1,5),(19,'555','2024-07-29 09:59:21',1,1,15,1,5),(20,'666','2024-07-29 10:00:31',1,1,15,1,5),(21,'999','2024-07-29 10:03:09',1,2,15,1,1),(22,'98','2024-07-29 10:06:41',1,2,1,1,1),(23,'666','2024-07-29 10:10:40',1,2,1,2,1),(24,'66','2024-07-29 10:11:16',1,2,15,2,1);
/*!40000 ALTER TABLE `comment_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_referral`
--

DROP TABLE IF EXISTS `comment_referral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_referral` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` text COMMENT '评论内容',
  `create_date` date DEFAULT NULL COMMENT '评论创建日期',
  `article_id` int DEFAULT NULL COMMENT '内推id',
  `author_id` int DEFAULT NULL COMMENT '评论者id',
  `parent_id` int DEFAULT NULL COMMENT '评论父级id',
  `to_user` int DEFAULT NULL COMMENT '目标者id',
  `level` int DEFAULT NULL COMMENT '层级',
  PRIMARY KEY (`id`),
  KEY `comment_referral_comment_referral_id_fk` (`parent_id`),
  KEY `comment_referral_user_id_fk` (`author_id`),
  KEY `comment_referral_user_id_fk_2` (`to_user`),
  KEY `comment_referral_referral_brief_id_fk` (`article_id`),
  CONSTRAINT `comment_referral_comment_referral_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `comment_referral` (`id`),
  CONSTRAINT `comment_referral_referral_brief_id_fk` FOREIGN KEY (`article_id`) REFERENCES `referral_brief` (`id`),
  CONSTRAINT `comment_referral_user_id_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `comment_referral_user_id_fk_2` FOREIGN KEY (`to_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_referral`
--

LOCK TABLES `comment_referral` WRITE;
/*!40000 ALTER TABLE `comment_referral` DISABLE KEYS */;
INSERT INTO `comment_referral` VALUES (5,'4444\n','2024-07-29',3,1,NULL,NULL,1),(6,'666','2024-07-29',3,1,5,1,1),(7,'777','2024-07-29',3,1,5,1,1);
/*!40000 ALTER TABLE `comment_referral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '公司头像',
  `category_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `description` text COMMENT '公司描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'/company/baidu.jpeg','百度',NULL),(2,'/company/jingdong.jpeg','京东',NULL),(3,'/company/meituan.png','美团',NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `pid` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `uid` int NOT NULL COMMENT '发布任务的用户ID',
  `category` int NOT NULL COMMENT '任务类别',
  `title` varchar(31) DEFAULT NULL COMMENT '任务标题',
  `info` varchar(511) DEFAULT NULL COMMENT '任务详情',
  `deadline` date DEFAULT NULL COMMENT '任务投标截止时间',
  `price_lower` int DEFAULT NULL COMMENT '任务价格下限',
  `price_upper` int DEFAULT NULL COMMENT '任务价格上限',
  `tel` varchar(15) DEFAULT NULL COMMENT '联系方式',
  `receiver` int DEFAULT NULL COMMENT '最终接收项目者的ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referral_body`
--

DROP TABLE IF EXISTS `referral_body`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referral_body` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '内推id',
  `content` longtext COMMENT '内推内容',
  `content_html` longtext COMMENT '内推html',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referral_body`
--

LOCK TABLES `referral_body` WRITE;
/*!40000 ALTER TABLE `referral_body` DISABLE KEYS */;
INSERT INTO `referral_body` VALUES (1,'**1111**','<p><strong>1111</strong></p>\n'),(2,'**22222**','<p><strong>22222</strong></p>\n'),(3,'33333','<p>33333</p>\n'),(4,'# zzzz','<h1><a id=\"zzzz_0\"></a>zzzz</h1>\n');
/*!40000 ALTER TABLE `referral_body` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referral_brief`
--

DROP TABLE IF EXISTS `referral_brief`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referral_brief` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_counts` int DEFAULT '0',
  `createDate` date DEFAULT NULL,
  `summary` text,
  `title` varchar(255) DEFAULT NULL,
  `view_counts` int DEFAULT '0',
  `author_id` int DEFAULT NULL,
  `body_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `status` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `referral_brief___fk` (`category_id`),
  KEY `referral_brief_referral_body_id_fk` (`body_id`),
  KEY `referral_brief_user_id_fk` (`author_id`),
  CONSTRAINT `referral_brief___fk` FOREIGN KEY (`category_id`) REFERENCES `company` (`id`),
  CONSTRAINT `referral_brief_referral_body_id_fk` FOREIGN KEY (`body_id`) REFERENCES `referral_body` (`id`),
  CONSTRAINT `referral_brief_user_id_fk` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referral_brief`
--

LOCK TABLES `referral_brief` WRITE;
/*!40000 ALTER TABLE `referral_brief` DISABLE KEYS */;
INSERT INTO `referral_brief` VALUES (1,0,'2024-07-29','2222222','1111',17,1,1,1,NULL,1),(2,0,'2024-07-29','66666','2222',8,1,2,1,NULL,2),(3,3,'2024-07-29','32333','333',14,1,3,2,NULL,1),(4,0,'2024-07-29','qqqqqqqq\n','aaaa',2,1,4,2,NULL,0);
/*!40000 ALTER TABLE `referral_brief` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referral_tech_link`
--

DROP TABLE IF EXISTS `referral_tech_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referral_tech_link` (
  `article_id` int DEFAULT NULL,
  `tag_id` int DEFAULT NULL,
  KEY `referral_tech_link_referral_brief_id_fk` (`article_id`),
  KEY `referral_tech_link_tech_id_fk` (`tag_id`),
  CONSTRAINT `referral_tech_link_referral_brief_id_fk` FOREIGN KEY (`article_id`) REFERENCES `referral_brief` (`id`),
  CONSTRAINT `referral_tech_link_tech_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `tech` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referral_tech_link`
--

LOCK TABLES `referral_tech_link` WRITE;
/*!40000 ALTER TABLE `referral_tech_link` DISABLE KEYS */;
INSERT INTO `referral_tech_link` VALUES (1,1),(1,2),(2,2),(2,3),(3,1),(4,3);
/*!40000 ALTER TABLE `referral_tech_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `avatar` varchar(255) DEFAULT NULL COMMENT '标签图标',
  `tagname` varchar(255) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'static/tag/vue.png','Vue'),(2,'static/tag/css.png','Css'),(3,'static/tag/html.png','Html');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tech`
--

DROP TABLE IF EXISTS `tech`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tech` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '技术栈id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '技术栈头像',
  `tagname` varchar(255) DEFAULT NULL COMMENT '技术栈名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tech`
--

LOCK TABLES `tech` WRITE;
/*!40000 ALTER TABLE `tech` DISABLE KEYS */;
INSERT INTO `tech` VALUES (1,'/stack/AI.jpeg','AI'),(2,'/stack/back.png','后端'),(3,'/stack/dataAnalytics.jpg','数据分析');
/*!40000 ALTER TABLE `tech` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `admin_status` int NOT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'admin','21232f297a57a5a743894a0e4a801fc3','https://sns-project.oss-cn-beijing.aliyuncs.com/5b96cc8b-fedc-4aef-87de-fbb0b0c4df28.jpg','w','222','admin',1,'2024-07-29','2024-07-29',0,'2024-07-08T16:00:00.000Z','ff','fff'),(1,'123456','e10adc3949ba59abbe56e057f20f883e','https://sns-project.oss-cn-beijing.aliyuncs.com/77f2be4b-5410-49d4-9245-8238117d5a5a.png','123@123.com','123456789','123456',0,'2024-07-26','2024-07-26',1,'2024-07-10T16:00:00.000Z','asdsa','啊大苏打'),(2,'111','698d51a19d8a121ce581499d7b701668','static/user/user_1.png',NULL,NULL,'小号',0,'2024-07-28','2024-07-28',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-31  0:22:33
