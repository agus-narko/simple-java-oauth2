-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `m_approval`
--

DROP TABLE IF EXISTS `m_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_approval` (
  `approval_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) DEFAULT NULL,
  `create_datetime` varchar(14) DEFAULT NULL,
  `update_datetime` varchar(14) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `active` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`approval_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_approval`
--

LOCK TABLES `m_approval` WRITE;
/*!40000 ALTER TABLE `m_approval` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_log`
--

DROP TABLE IF EXISTS `m_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(50) NOT NULL,
  `client_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) NOT NULL,
  `api_get_url` varchar(100) NOT NULL,
  `command_as_json` text NOT NULL,
  `request_date` datetime NOT NULL,
  `response_as_json` text NOT NULL,
  `response_date` datetime DEFAULT NULL,
  `result_remark` smallint(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_log`
--

LOCK TABLES `m_log` WRITE;
/*!40000 ALTER TABLE `m_log` DISABLE KEYS */;
INSERT INTO `m_log` VALUES (1,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"userId\":61}','2020-09-02 15:07:06','{\"serviceOutput\":[],\"serviceInput\":{\"userId\":61},\"status\":\"S\"}','2020-09-02 15:07:06',1),(2,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"userId\":61}','2020-09-02 15:08:09','{\"serviceOutput\":[],\"serviceInput\":{\"userId\":61},\"status\":\"S\"}','2020-09-02 15:08:09',1),(3,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"userId\":61}','2020-09-02 15:12:29','{\"serviceOutput\":[],\"serviceInput\":{\"userId\":61},\"status\":\"S\"}','2020-09-02 15:12:30',1),(4,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"userId\":61}','2020-09-02 15:16:52','{\"serviceOutput\":[],\"status\":\"S\"}','2020-09-02 15:16:53',1),(5,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"roleId\":\"10\",\"userId\":61}','2020-09-02 15:17:05','{\"serviceOutput\":[{\"createTaskName\":\"\",\"roleId\":10,\"readTaskName\":\"InquiryPD\",\"createTaskCode\":\"\",\"taskCrudName\":\"PD\",\"createTaskId\":-99,\"readTaskId\":1,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryPD\",\"roleTaskCrudActiveId\":1,\"deleteTaskCode\":\"\",\"taskCrudId\":1,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"N\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"\",\"roleId\":10,\"readTaskName\":\"InquiryLGD\",\"createTaskCode\":\"\",\"taskCrudName\":\"LGD\",\"createTaskId\":-99,\"readTaskId\":2,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryLGD\",\"roleTaskCrudActiveId\":2,\"deleteTaskCode\":\"\",\"taskCrudId\":2,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"N\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"GeneratePDLGD\",\"roleId\":10,\"readTaskName\":\"\",\"createTaskCode\":\"GeneratePDLGD\",\"taskCrudName\":\"PDLGD\",\"createTaskId\":17,\"readTaskId\":-99,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"\",\"roleTaskCrudActiveId\":3,\"deleteTaskCode\":\"\",\"taskCrudId\":3,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"N\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"GenerateForwardLookingRate\",\"roleId\":10,\"readTaskName\":\"InquiryPDLGDFL\",\"createTaskCode\":\"GenerateForwardLookingRate\",\"taskCrudName\":\"PDLGDFL\",\"createTaskId\":18,\"readTaskId\":3,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryPDLGDFL\",\"roleTaskCrudActiveId\":4,\"deleteTaskCode\":\"\",\"taskCrudId\":4,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"GenerateRegressionModel\",\"roleId\":10,\"readTaskName\":\"InquiryRegressionModel\",\"createTaskCode\":\"GenerateRegressionModel\",\"taskCrudName\":\"RegressionModel\",\"createTaskId\":11,\"readTaskId\":6,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryRegressionModel\",\"roleTaskCrudActiveId\":5,\"deleteTaskCode\":\"\",\"taskCrudId\":5,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"AddMacroEconomyData\",\"roleId\":10,\"readTaskName\":\"InquiryMacroEconomyData\",\"createTaskCode\":\"AddMacroEconomyData\",\"taskCrudName\":\"MacroEconomyData\",\"createTaskId\":10,\"readTaskId\":7,\"updateTaskName\":\"UpdateMacroEconomyData\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"InquiryMacroEconomyData\",\"roleTaskCrudActiveId\":6,\"deleteTaskCode\":\"DeleteMacroEconomyData\",\"taskCrudId\":6,\"updateTaskId\":8,\"deleteTaskId\":9,\"deleteTaskName\":\"DeleteMacroEconomyData\",\"updateTaskCode\":\"UpdateMacroEconomyData\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"GenerateBackTesting\",\"roleId\":10,\"readTaskName\":\"InquiryBackTesting\",\"createTaskCode\":\"GenerateBackTesting\",\"taskCrudName\":\"BackTesting\",\"createTaskId\":13,\"readTaskId\":12,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryBackTesting\",\"roleTaskCrudActiveId\":7,\"deleteTaskCode\":\"\",\"taskCrudId\":7,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"GenerateForwardLookingScenario\",\"roleId\":10,\"readTaskName\":\"InquiryForwardLookingScenario\",\"createTaskCode\":\"GenerateForwardLookingScenario\",\"taskCrudName\":\"forwardLookingScenario\",\"createTaskId\":15,\"readTaskId\":14,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryForwardLookingScenario\",\"roleTaskCrudActiveId\":8,\"deleteTaskCode\":\"\",\"taskCrudId\":8,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"MigratePDLGDData\",\"roleId\":10,\"readTaskName\":\"\",\"createTaskCode\":\"MigratePDLGDData\",\"taskCrudName\":\"eclModelling\",\"createTaskId\":16,\"readTaskId\":-99,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"\",\"roleTaskCrudActiveId\":9,\"deleteTaskCode\":\"\",\"taskCrudId\":9,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"N\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"AddAssetRating\",\"roleId\":10,\"readTaskName\":\"InquiryAssetRating\",\"createTaskCode\":\"AddAssetRating\",\"taskCrudName\":\"assetRating\",\"createTaskId\":20,\"readTaskId\":19,\"updateTaskName\":\"UpdateAssetRating\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"InquiryAssetRating\",\"roleTaskCrudActiveId\":10,\"deleteTaskCode\":\"DeleteAssetRating\",\"taskCrudId\":10,\"updateTaskId\":21,\"deleteTaskId\":22,\"deleteTaskName\":\"DeleteAssetRating\",\"updateTaskCode\":\"UpdateAssetRating\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"GenerateAssetDataECL\",\"roleId\":10,\"readTaskName\":\"InquiryAssetDataECL\",\"createTaskCode\":\"GenerateAssetDataECL\",\"taskCrudName\":\"assetECL\",\"createTaskId\":23,\"readTaskId\":24,\"updateTaskName\":\"UpdateAssetDataECL\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"InquiryAssetDataECL\",\"roleTaskCrudActiveId\":11,\"deleteTaskCode\":\"DeleteAssetDataECL\",\"taskCrudId\":11,\"updateTaskId\":25,\"deleteTaskId\":26,\"deleteTaskName\":\"DeleteAssetDataECL\",\"updateTaskCode\":\"UpdateAssetDataECL\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"AddMacroEconomyConfig\",\"roleId\":10,\"readTaskName\":\"InquiryMacroEconomyConfig\",\"createTaskCode\":\"AddMacroEconomyConfig\",\"taskCrudName\":\"macroEconomyConfig\",\"createTaskId\":27,\"readTaskId\":28,\"updateTaskName\":\"UpdateMacroEconomyConfig\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"InquiryMacroEconomyConfig\",\"roleTaskCrudActiveId\":12,\"deleteTaskCode\":\"DeleteMacroEconomyConfig\",\"taskCrudId\":12,\"updateTaskId\":29,\"deleteTaskId\":30,\"deleteTaskName\":\"DeleteMacroEconomyConfig\",\"updateTaskCode\":\"UpdateMacroEconomyConfig\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"AddRatingConfig\",\"roleId\":10,\"readTaskName\":\"InquiryRatingConfig\",\"createTaskCode\":\"AddRatingConfig\",\"taskCrudName\":\"ratingConfig\",\"createTaskId\":31,\"readTaskId\":32,\"updateTaskName\":\"UpdateRatingConfig\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"InquiryRatingConfig\",\"roleTaskCrudActiveId\":13,\"deleteTaskCode\":\"DeleteRatingConfig\",\"taskCrudId\":13,\"updateTaskId\":33,\"deleteTaskId\":34,\"deleteTaskName\":\"DeleteRatingConfig\",\"updateTaskCode\":\"UpdateRatingConfig\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"GeneratePDLGDFLTextFile\",\"roleId\":10,\"readTaskName\":\"\",\"createTaskCode\":\"GeneratePDLGDFLTextFile\",\"taskCrudName\":\"generatePDLGDTextFile\",\"createTaskId\":5,\"readTaskId\":-99,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"\",\"roleTaskCrudActiveId\":14,\"deleteTaskCode\":\"\",\"taskCrudId\":14,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"N\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"GenerateRatingTextFile\",\"roleId\":10,\"readTaskName\":\"\",\"createTaskCode\":\"GenerateRatingTextFile\",\"taskCrudName\":\"generateRatingTextFile\",\"createTaskId\":35,\"readTaskId\":-99,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"\",\"roleTaskCrudActiveId\":15,\"deleteTaskCode\":\"\",\"taskCrudId\":15,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"N\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"N\"},{\"createTaskName\":\"CreateECLConfig\",\"roleId\":10,\"readTaskName\":\"InquiryECLConfig\",\"createTaskCode\":\"CreateECLConfig\",\"taskCrudName\":\"eclConfig\",\"createTaskId\":36,\"readTaskId\":38,\"updateTaskName\":\"UpdateECLConfig\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"InquiryECLConfig\",\"roleTaskCrudActiveId\":16,\"deleteTaskCode\":\"DeleteECLConfig\",\"taskCrudId\":16,\"updateTaskId\":37,\"deleteTaskId\":39,\"deleteTaskName\":\"DeleteECLConfig\",\"updateTaskCode\":\"UpdateECLConfig\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"createRoleManagementService\",\"roleId\":10,\"readTaskName\":\"inquiryRoleManagementService\",\"createTaskCode\":\"createRoleManagementService\",\"taskCrudName\":\"roleManagement\",\"createTaskId\":41,\"readTaskId\":43,\"updateTaskName\":\"EditRoleManagementDetailService\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"inquiryRoleManagementService\",\"roleTaskCrudActiveId\":17,\"deleteTaskCode\":\"deactivateRoleService\",\"taskCrudId\":17,\"updateTaskId\":40,\"deleteTaskId\":42,\"deleteTaskName\":\"deactivateRoleService\",\"updateTaskCode\":\"EditRoleManagementDetailService\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"createUserManagementService\",\"roleId\":10,\"readTaskName\":\"inquiryUserManagementService\",\"createTaskCode\":\"createUserManagementService\",\"taskCrudName\":\"userEdit\",\"createTaskId\":45,\"readTaskId\":46,\"updateTaskName\":\"editUserManagementDetailService\",\"deleteTaskActive\":\"Y\",\"readTaskIdCode\":\"inquiryUserManagementService\",\"roleTaskCrudActiveId\":18,\"deleteTaskCode\":\"deactivateUserManagementService\",\"taskCrudId\":18,\"updateTaskId\":44,\"deleteTaskId\":48,\"deleteTaskName\":\"deactivateUserManagementService\",\"updateTaskCode\":\"editUserManagementDetailService\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"Y\",\"updateTaskActive\":\"Y\"},{\"createTaskName\":\"\",\"roleId\":10,\"readTaskName\":\"InquiryPDFL\",\"createTaskCode\":\"\",\"taskCrudName\":\"PDFL\",\"createTaskId\":-99,\"readTaskId\":4,\"updateTaskName\":\"\",\"deleteTaskActive\":\"N\",\"readTaskIdCode\":\"InquiryPDFL\",\"roleTaskCrudActiveId\":19,\"deleteTaskCode\":\"\",\"taskCrudId\":19,\"updateTaskId\":-99,\"deleteTaskId\":-99,\"deleteTaskName\":\"\",\"updateTaskCode\":\"\",\"roleName\":\"SUPERADMIN\",\"readTaskActive\":\"Y\",\"createTaskActive\":\"N\",\"updateTaskActive\":\"N\"}],\"serviceInput\":{\"roleId\":\"10\",\"userId\":61},\"status\":\"S\"}','2020-09-02 15:17:05',1),(6,'READ','test-core','test-core','/service/api/getUserService','{\"userId\":61}','2020-09-02 15:19:38','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@2a458131,com.agus.java.model.userManagement.UserEntity@69d6fb65,com.agus.java.model.userManagement.UserEntity@6ac2daef,com.agus.java.model.userManagement.UserEntity@68f20682,com.agus.java.model.userManagement.UserEntity@6e385ea4,com.agus.java.model.userManagement.UserEntity@306b8dd5,com.agus.java.model.userManagement.UserEntity@4c023c95,com.agus.java.model.userManagement.UserEntity@34bce581,com.agus.java.model.userManagement.UserEntity@18dd50cc],\"serviceInput\":{\"userId\":61}}','2020-09-02 15:19:38',0),(7,'READ','test-core','test-core','/service/api/getUserService','{\"userId\":61}','2020-09-02 15:48:03','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@310cbbd8,com.agus.java.model.userManagement.UserEntity@17dd5ca4,com.agus.java.model.userManagement.UserEntity@f36a1e5,com.agus.java.model.userManagement.UserEntity@7618eab6,com.agus.java.model.userManagement.UserEntity@221e5dd6,com.agus.java.model.userManagement.UserEntity@5fdb1d4d,com.agus.java.model.userManagement.UserEntity@65d8b6da,com.agus.java.model.userManagement.UserEntity@54a4bd8d,com.agus.java.model.userManagement.UserEntity@484ba22],\"serviceInput\":{\"userId\":61}}','2020-09-02 15:48:03',0),(8,'READ','test-core','test-core','/service/api/getUserService','{\"userId\":61}','2020-09-02 15:48:08','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@d5e39fa,com.agus.java.model.userManagement.UserEntity@1d37a0df,com.agus.java.model.userManagement.UserEntity@71a8bec6,com.agus.java.model.userManagement.UserEntity@45ad758d,com.agus.java.model.userManagement.UserEntity@19d16b84,com.agus.java.model.userManagement.UserEntity@2d3ba8f6,com.agus.java.model.userManagement.UserEntity@314b1c1,com.agus.java.model.userManagement.UserEntity@3a4485d5,com.agus.java.model.userManagement.UserEntity@28db4452],\"serviceInput\":{\"userId\":61}}','2020-09-02 15:48:08',0),(9,'READ','test-core','test-core','/service/api/getUserService','{\"userId\":61}','2020-09-02 16:02:35','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@5bafd129,com.agus.java.model.userManagement.UserEntity@53bbf7c7,com.agus.java.model.userManagement.UserEntity@45a6bfa9,com.agus.java.model.userManagement.UserEntity@45a6a137,com.agus.java.model.userManagement.UserEntity@60a50633,com.agus.java.model.userManagement.UserEntity@6c2cd02f,com.agus.java.model.userManagement.UserEntity@1c8771f4,com.agus.java.model.userManagement.UserEntity@6cd8d44a,com.agus.java.model.userManagement.UserEntity@2484d37c],\"serviceInput\":{\"userId\":61}}','2020-09-02 16:02:35',0),(10,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"userId\":61}','2020-09-02 20:32:32','{\"serviceOutput\":[com.agus.java.model.userManagement.Role@7d47ad3f,com.agus.java.model.userManagement.Role@159aa87c],\"status\":\"S\"}','2020-09-02 20:32:32',1),(11,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"parameter\":\"10\",\"userId\":61}','2020-09-02 20:32:41','{\"serviceOutput\":com.agus.java.model.userManagement.Role@47e1998e,\"status\":\"S\"}','2020-09-02 20:32:41',1),(12,'CREATE','test-core','test-core','/service/api/createRoleService','{\"roleCode\":\"admin\",\"scope\":\"read,write,trust\",\"roleName\":\"admin\",\"userId\":61,\"flagNewRole\":\"Y\"}','2020-09-02 20:37:57','{\"serviceOutput\":\"OK\",\"serviceInput\":{\"roleCode\":\"admin\",\"scope\":\"read,write,trust\",\"roleName\":\"admin\",\"userId\":61,\"flagNewRole\":\"Y\"},\"status\":\"S\"}','2020-09-02 20:37:57',1),(13,'READ','test-core','test-core','/service/api/getRoleManagementService/{userId}','{\"userId\":61}','2020-09-02 20:38:14','{\"serviceOutput\":[com.agus.java.model.userManagement.Role@167c98bb,com.agus.java.model.userManagement.Role@500d9dff,com.agus.java.model.userManagement.Role@22467e05],\"status\":\"S\"}','2020-09-02 20:38:14',1),(14,'CREATE','test-core','test-core','/service/api/createUserService','{\"password\":\"admin\",\"address\":\"string\",\"phone\":\"8888\",\"roleId\":43,\"userFullname\":\"admin 1\",\"userId\":61,\"email\":\"admin@ad.min\",\"username\":\"admin\",\"flagNewRole\":\"N\"}','2020-09-02 21:08:12','{\"status\":\"S\"}','2020-09-02 21:08:12',1),(15,'READ','test-core','test-core','/service/api/getUserService','{\"userId\":61}','2020-09-02 21:08:47','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@3a9ac020,com.agus.java.model.userManagement.UserEntity@3ca4f765,com.agus.java.model.userManagement.UserEntity@3b49e974,com.agus.java.model.userManagement.UserEntity@72311579,com.agus.java.model.userManagement.UserEntity@1e4650df,com.agus.java.model.userManagement.UserEntity@5c293ad,com.agus.java.model.userManagement.UserEntity@71b4c828,com.agus.java.model.userManagement.UserEntity@b5491ba,com.agus.java.model.userManagement.UserEntity@27b8e8a7,com.agus.java.model.userManagement.UserEntity@36765806],\"serviceInput\":{\"userId\":61}}','2020-09-02 21:08:47',0),(16,'READ','test-core','test-core','/service/api/getUserManagementService','{\"userId\":61}','2020-09-02 21:09:09','{\"serviceOutput\":[{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"test-core\",\"roleId\":10,\"usernameResult\":\"test-core\",\"userId\":61,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"newcore\",\"roleId\":10,\"usernameResult\":\"newcore\",\"userId\":62,\"email\":\"cuy@cuy.com\",\"activeCode\":\"N\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core2\",\"roleId\":10,\"usernameResult\":\"new-core2\",\"userId\":63,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core3\",\"roleId\":10,\"usernameResult\":\"new-core3\",\"userId\":64,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core4\",\"roleId\":10,\"usernameResult\":\"new-core4\",\"userId\":65,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core5\",\"roleId\":10,\"usernameResult\":\"new-core5\",\"userId\":66,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core6\",\"roleId\":10,\"usernameResult\":\"new-core6\",\"userId\":67,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core7\",\"roleId\":10,\"usernameResult\":\"new-core7\",\"userId\":68,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"SUPERADMIN\",\"userFullnameResult\":\"new-core8\",\"roleId\":10,\"usernameResult\":\"new-core8\",\"userId\":69,\"email\":\"cuy@cuy.com\",\"activeCode\":\"Y\"},{\"role\":\"admin\",\"userFullnameResult\":\"admin 1\",\"roleId\":43,\"usernameResult\":\"admin\",\"userId\":70,\"email\":\"admin@ad.min\",\"activeCode\":\"Y\"}],\"serviceInput\":{\"userId\":61}}','2020-09-02 21:09:09',0),(17,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 11:13:07','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@79f1911c],\"serviceInput\":{\"userId\":70}}','2020-09-03 11:13:07',0),(18,'READ','admin','admin','/service/api/getUserService','{\"parameter\":\"71\",\"userId\":70}','2020-09-03 11:13:13','{\"serviceOutput\":null,\"status\":\"S\"}','2020-09-03 11:13:13',1),(19,'READ','admin','admin','/service/api/getUserService','{\"parameter\":\"70\",\"userId\":70}','2020-09-03 11:23:04','{\"serviceOutput\":com.agus.java.model.userManagement.UserEntity@24a53b63,\"countData\":null,\"status\":\"S\"}','2020-09-03 11:23:04',1),(20,'CREATE','admin','admin','/service/api/createRoleService','{\"scope\":\"read,write\",\"roleName\":\"operator\",\"userId\":70}','2020-09-03 11:40:21','{\"serviceOutput\":\"OK\",\"serviceInput\":{\"scope\":\"read,write\",\"roleName\":\"operator\",\"userId\":70},\"status\":\"S\"}','2020-09-03 11:40:21',1),(21,'CREATE','admin','admin','/service/api/createRoleService','{\"scope\":\"read,write\",\"roleName\":\"operator\",\"userId\":70}','2020-09-03 11:40:31','{\"serviceOutput\":\"Failed\",\"serviceInput\":{\"scope\":\"read,write\",\"roleName\":\"operator\",\"userId\":70},\"errorMessage\":\"role sudah ada\",\"status\":\"F\"}','2020-09-03 11:40:31',0),(22,'READ','admin','admin','/service/api/getRoleService','{\"userId\":70}','2020-09-03 11:43:33','{\"serviceOutput\":[com.agus.java.model.roleManagement.Role@720f22b9,com.agus.java.model.roleManagement.Role@3635a79d],\"status\":\"S\"}','2020-09-03 11:43:33',1),(23,'READ','admin','admin','/service/api/getRoleService','{\"parameter\":\"44\",\"userId\":70}','2020-09-03 11:44:03','{\"serviceOutput\":com.agus.java.model.roleManagement.Role@7236f488,\"status\":\"S\"}','2020-09-03 11:44:03',1),(24,'READ','admin','admin','/service/api/getRoleService','{\"parameter\":\"Y\",\"userId\":70}','2020-09-03 11:44:10','{\"serviceOutput\":\"fail\",\"serviceInput\":{\"parameter\":\"Y\",\"userId\":70}}','2020-09-03 11:44:10',0),(25,'READ','admin','admin','/service/api/getRoleService','{\"parameter\":\"Y\",\"userId\":70}','2020-09-03 11:44:43','{\"serviceOutput\":\"fail\",\"serviceInput\":{\"parameter\":\"Y\",\"userId\":70}}','2020-09-03 11:44:43',0),(26,'READ','admin','admin','/service/api/getRoleService','{\"parameter\":\"Y\",\"userId\":70}','2020-09-03 11:46:29','{\"serviceOutput\":[{\"roleId\":43,\"scope\":\"read,write,trust\",\"roleName\":\"admin\"},{\"roleId\":44,\"scope\":\"read,write\",\"roleName\":\"operator\"}],\"serviceInput\":{\"parameter\":\"Y\",\"userId\":70}}','2020-09-03 11:46:29',0),(27,'READ','admin','admin','/service/api/getRoleService','{\"parameter\":\"44\",\"userId\":70}','2020-09-03 11:46:37','{\"serviceOutput\":com.agus.java.model.roleManagement.Role@20a0d55,\"status\":\"S\"}','2020-09-03 11:46:37',1),(28,'UPDATE','admin','admin','/service/api/editRoleManagementDetailService','{\"roleId\":44,\"scope\":\"read\",\"roleName\":\"report\",\"userId\":70}','2020-09-03 11:48:55','{\"errorMessage\":\"Role tidak ditemukan\",\"status\":\"F\"}','2020-09-03 11:48:55',0),(29,'UPDATE','admin','admin','/service/api/editRoleManagementDetailService','{\"roleId\":44,\"scope\":\"read\",\"roleName\":\"report\",\"userId\":70}','2020-09-03 11:49:50','{\"serviceInput\":{\"roleId\":44,\"scope\":\"read\",\"roleName\":\"report\",\"userId\":70},\"status\":\"S\"}','2020-09-03 11:49:50',1),(30,'READ','admin','admin','/service/api/getRoleService','{\"parameter\":\"44\",\"userId\":70}','2020-09-03 11:49:55','{\"serviceOutput\":com.agus.java.model.roleManagement.Role@579cd73f,\"status\":\"S\"}','2020-09-03 11:49:55',1),(31,'CREATE','admin','admin','/service/api/createRoleService','{\"scope\":\"read,write\",\"roleName\":\"operator\",\"userId\":70}','2020-09-03 11:50:05','{\"serviceOutput\":\"OK\",\"serviceInput\":{\"scope\":\"read,write\",\"roleName\":\"operator\",\"userId\":70},\"status\":\"S\"}','2020-09-03 11:50:05',1),(32,'CREATE','admin','admin','/service/api/createRoleService','{\"scope\":\"read,write\",\"roleName\":\"operator1\",\"userId\":70}','2020-09-03 11:50:11','{\"serviceOutput\":\"OK\",\"serviceInput\":{\"scope\":\"read,write\",\"roleName\":\"operator1\",\"userId\":70},\"status\":\"S\"}','2020-09-03 11:50:11',1),(33,'CREATE','admin','admin','/service/api/createRoleService','{\"scope\":\"read\",\"roleName\":\"report\",\"userId\":70}','2020-09-03 11:50:22','{\"serviceOutput\":\"Failed\",\"serviceInput\":{\"scope\":\"read\",\"roleName\":\"report\",\"userId\":70},\"errorMessage\":\"role sudah ada\",\"status\":\"F\"}','2020-09-03 11:50:22',0),(34,'CREATE','admin','admin','/service/api/createRoleService','{\"scope\":\"read\",\"roleName\":\"report1\",\"userId\":70}','2020-09-03 11:50:42','{\"serviceOutput\":\"OK\",\"serviceInput\":{\"scope\":\"read\",\"roleName\":\"report1\",\"userId\":70},\"status\":\"S\"}','2020-09-03 11:50:42',1),(35,'READ','admin','admin','/service/api/getRoleService','{\"userId\":70}','2020-09-03 11:50:52','{\"serviceOutput\":[com.agus.java.model.roleManagement.Role@55dee2d9,com.agus.java.model.roleManagement.Role@744a8ae4,com.agus.java.model.roleManagement.Role@3e67306,com.agus.java.model.roleManagement.Role@b05ac0c,com.agus.java.model.roleManagement.Role@29ef5f9f],\"status\":\"S\"}','2020-09-03 11:50:52',1),(36,'CREATE','admin','admin','/service/api/createUserService','{\"password\":\"a-report\",\"address\":\"string\",\"phone\":\"string\",\"roleId\":44,\"userFullname\":\"a-report\",\"userId\":70,\"email\":\"string\",\"username\":\"a-report\"}','2020-09-03 11:52:20','{\"status\":\"S\"}','2020-09-03 11:52:20',1),(37,'CREATE','admin','admin','/service/api/createUserService','{\"password\":\"b-report\",\"address\":\"string\",\"phone\":\"string\",\"roleId\":44,\"userFullname\":\"b-report\",\"userId\":70,\"email\":\"string\",\"username\":\"b-report\"}','2020-09-03 11:52:38','{\"status\":\"S\"}','2020-09-03 11:52:38',1),(38,'CREATE','admin','admin','/service/api/createUserService','{\"password\":\"c-report\",\"address\":\"string\",\"phone\":\"string\",\"roleId\":44,\"userFullname\":\"c-report\",\"userId\":70,\"email\":\"string\",\"username\":\"c-report\"}','2020-09-03 11:52:49','{\"status\":\"S\"}','2020-09-03 11:52:49',1),(39,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 11:53:53','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@71dfe0ff,com.agus.java.model.userManagement.UserEntity@35519512,com.agus.java.model.userManagement.UserEntity@225ab69,com.agus.java.model.userManagement.UserEntity@48eb5759],\"serviceInput\":{\"userId\":70}}','2020-09-03 11:53:53',0),(40,'UPDATE','admin','admin','/service/api/deactivateUserService','{\"updateId\":71,\"userId\":70}','2020-09-03 11:57:39','{\"serviceInput\":{\"updateId\":71,\"userId\":70},\"status\":\"S\"}','2020-09-03 11:57:39',1),(41,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 11:58:05','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@5b7a0647,com.agus.java.model.userManagement.UserEntity@4710e898,com.agus.java.model.userManagement.UserEntity@46fced25,com.agus.java.model.userManagement.UserEntity@245a548c],\"serviceInput\":{\"userId\":70}}','2020-09-03 11:58:05',0),(42,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 11:59:27','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@4e4736b9,com.agus.java.model.userManagement.UserEntity@787b801,com.agus.java.model.userManagement.UserEntity@70032031,com.agus.java.model.userManagement.UserEntity@3cabae8f],\"serviceInput\":{\"userId\":70}}','2020-09-03 11:59:27',0),(43,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"Y\"}','2020-09-03 13:53:29','{\"serviceInput\":{\"updateId\":71,\"userId\":70,\"status\":\"Y\"},\"status\":\"S\"}','2020-09-03 13:53:29',1),(44,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 13:53:45','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@7a65b720,com.agus.java.model.userManagement.UserEntity@13981da4,com.agus.java.model.userManagement.UserEntity@6e428d2d,com.agus.java.model.userManagement.UserEntity@11ace365],\"serviceInput\":{\"userId\":70}}','2020-09-03 13:53:45',0),(45,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"N\"}','2020-09-03 13:54:01','{\"serviceInput\":{\"updateId\":71,\"userId\":70,\"status\":\"N\"},\"status\":\"S\"}','2020-09-03 13:54:01',1),(46,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 13:54:06','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@6d3b0d31,com.agus.java.model.userManagement.UserEntity@211274b7,com.agus.java.model.userManagement.UserEntity@2a8ce3e9,com.agus.java.model.userManagement.UserEntity@171e42f0],\"serviceInput\":{\"userId\":70}}','2020-09-03 13:54:06',0),(47,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 14:17:34','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@730b85c,com.agus.java.model.userManagement.UserEntity@6393f86f,com.agus.java.model.userManagement.UserEntity@62c5a143,com.agus.java.model.userManagement.UserEntity@3f972f46],\"serviceInput\":{\"userId\":70}}','2020-09-03 14:17:34',0),(48,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 14:31:15','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@75ffa75c,com.agus.java.model.userManagement.UserEntity@62d25250,com.agus.java.model.userManagement.UserEntity@20dc55f5,com.agus.java.model.userManagement.UserEntity@30c72d56],\"serviceInput\":{\"userId\":70}}','2020-09-03 14:31:15',0),(49,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":43,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:31:29','{\"errorMessage\":\"user sedang aktif\",\"status\":\"F\"}','2020-09-03 14:31:29',0),(50,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:31:40','{\"serviceInput\":{\"updateId\":71,\"userId\":70,\"status\":\"N\"},\"status\":\"S\"}','2020-09-03 14:31:40',1),(51,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":43,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:42:12','{\"serviceOutput\":\"OK\",\"errorMessage\":\"user masih aktif\",\"status\":\"S\"}','2020-09-03 14:42:12',1),(52,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":43,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:46:08','{\"serviceOutput\":\"OK\",\"errorMessage\":\"user masih aktif\",\"status\":\"S\"}','2020-09-03 14:46:08',1),(53,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":43,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:47:14','{\"serviceOutput\":\"OK\",\"errorMessage\":\"user masih aktif\",\"status\":\"S\"}','2020-09-03 14:47:14',1),(54,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":42,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:48:01','{\"serviceOutput\":\"OK\",\"errorMessage\":\"role tidak di temukan\",\"status\":\"S\"}','2020-09-03 14:48:01',1),(55,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":44,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:48:21','{\"errorMessage\":\"unexpected end of subtree []\",\"status\":\"F\"}','2020-09-03 14:48:21',0),(56,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":44,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:49:49','{\"errorMessage\":\"unexpected end of subtree []\",\"status\":\"F\"}','2020-09-03 14:49:49',0),(57,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":44,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:52:00','{\"errorMessage\":\"could not execute statement\",\"status\":\"F\"}','2020-09-03 14:52:00',0),(58,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":44,\"userId\":70,\"status\":\"N\"}','2020-09-03 14:53:12','{\"serviceOutput\":\"OK\",\"status\":\"S\"}','2020-09-03 14:53:12',1),(59,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 14:53:41','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@34d76c0f,com.agus.java.model.userManagement.UserEntity@1f2d4e6d,com.agus.java.model.userManagement.UserEntity@38591275,com.agus.java.model.userManagement.UserEntity@5a36b63a],\"serviceInput\":{\"userId\":70}}','2020-09-03 14:53:41',0),(60,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"Y\"}','2020-09-03 15:26:20','{\"serviceInput\":{\"updateId\":71,\"userId\":70,\"status\":\"Y\"},\"status\":\"S\"}','2020-09-03 15:26:20',1),(61,'UPDATE','admin','admin','/service/api/changePasswordService','{\"prevPassword\":\"admin\",\"newPassword\":\"admin1\",\"userId\":70}','2020-09-03 15:26:43','{\"serviceInput\":{\"prevPassword\":\"admin\",\"newPassword\":\"admin1\",\"userId\":70},\"status\":\"S\"}','2020-09-03 15:26:43',1),(62,'CREATE','admin','admin','/service/api/createUserService','{\"password\":\"admin1\",\"address\":\"jakarta\",\"phone\":\"123456789\",\"roleId\":43,\"userFullname\":\"admin satu\",\"userId\":70,\"email\":\"admin1@email.com\",\"username\":\"admin1\"}','2020-09-03 15:27:56','{\"status\":\"S\"}','2020-09-03 15:27:56',1),(63,'UPDATE','admin','admin','/service/api/editUserManagementDetailService','{\"updateId\":71,\"password\":\"adminz\",\"address\":\"jakarta\",\"phone\":\"123456789\",\"roleId\":43,\"userFullname\":\"admin zed\",\"userId\":70,\"email\":\"adminz@email.com\",\"username\":\"adminz\"}','2020-09-03 15:28:18','{\"errorMessage\":\"username tidak di temukan\",\"status\":\"F\"}','2020-09-03 15:28:18',0),(64,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 15:28:36','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@2cd0db0b,com.agus.java.model.userManagement.UserEntity@7306ed1b,com.agus.java.model.userManagement.UserEntity@6776a8c,com.agus.java.model.userManagement.UserEntity@35728336,com.agus.java.model.userManagement.UserEntity@1fd01e10],\"serviceInput\":{\"userId\":70}}','2020-09-03 15:28:36',0),(65,'UPDATE','admin','admin','/service/api/editUserManagementDetailService','{\"updateId\":71,\"password\":\"adminz\",\"address\":\"jakarta\",\"phone\":\"123456789\",\"roleId\":43,\"userFullname\":\"admin zed\",\"userId\":70,\"email\":\"adminz@email.com\",\"username\":\"adminz\"}','2020-09-03 15:35:01','{\"errorMessage\":\"unexpected token: address near line 1, column 111 [UPDATE com.agus.java.model.userManagement.UserEntity SET username = :userName, email = :email, phone = :phone address = :addressuserRole = :userRoleupdateDatetime = :updateDatetime, updateUserId = :updateUserId, userFullname = :userFullname, statusLogin =:statusLogin  WHERE id = :userId ]\",\"status\":\"F\"}','2020-09-03 15:35:01',0),(66,'READ','admin','admin','/service/api/getUserService','{\"userId\":70}','2020-09-03 11:05:20','{\"serviceOutput\":[com.agus.java.model.userManagement.UserEntity@1c96cac5,com.agus.java.model.userManagement.UserEntity@17db60b8,com.agus.java.model.userManagement.UserEntity@394aef77,com.agus.java.model.userManagement.UserEntity@27db152a]}','2020-09-03 11:05:20',0),(67,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"N\"}','2020-09-03 11:05:34','{\"errorMessage\":\"Role tidak aktif\",\"status\":\"F\"}','2020-09-03 11:05:34',0),(68,'READ','admin','admin','/service/api/getRoleService','{\"userId\":70}','2020-09-03 11:05:57','{\"serviceOutput\":[com.agus.java.model.roleManagement.Role@50a9da73,com.agus.java.model.roleManagement.Role@7328bd1e,com.agus.java.model.roleManagement.Role@10f71a8f,com.agus.java.model.roleManagement.Role@2283c5ce,com.agus.java.model.roleManagement.Role@3ce7e9af],\"status\":\"S\"}','2020-09-03 11:05:57',1),(69,'UPDATE','admin','admin','/service/api/activationRoleService','{\"updateId\":44,\"userId\":70,\"status\":\"Y\"}','2020-09-03 11:06:20','{\"status\":\"S\"}','2020-09-03 11:06:20',1),(70,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"N\"}','2020-09-03 11:06:26','{\"status\":\"S\"}','2020-09-03 11:06:26',1),(71,'UPDATE','admin','admin','/service/api/activationUserService','{\"updateId\":71,\"userId\":70,\"status\":\"Y\"}','2020-09-03 11:06:35','{\"status\":\"S\"}','2020-09-03 11:06:35',1),(72,'UPDATE','admin','admin','/service/api/changePasswordService','{\"prevPassword\":\"admin1\",\"newPassword\":\"admin\",\"userId\":70}','2020-09-03 11:06:58','{\"status\":\"S\"}','2020-09-03 11:06:58',1);
/*!40000 ALTER TABLE `m_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_role`
--

DROP TABLE IF EXISTS `m_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `scope` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_role`
--

LOCK TABLES `m_role` WRITE;
/*!40000 ALTER TABLE `m_role` DISABLE KEYS */;
INSERT INTO `m_role` VALUES (43,'Y','20200902203756',61,'admin','20200902203756',61,'read,write,trust'),(44,'Y','20200903114021',70,'report','20200903110619',70,'read'),(45,'Y','20200903115005',70,'operator','20200903115005',70,'read,write'),(46,'Y','20200903115010',70,'operator1','20200903115010',70,'read,write'),(47,'Y','20200903115041',70,'report1','20200903115041',70,'read');
/*!40000 ALTER TABLE `m_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_role_task_crud_active`
--

DROP TABLE IF EXISTS `m_role_task_crud_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_role_task_crud_active` (
  `role_task_crud_active_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `task_crud_id` bigint(20) DEFAULT NULL,
  `create_task_active` varchar(255) DEFAULT NULL,
  `read_task_active` varchar(255) DEFAULT NULL,
  `update_task_active` varchar(255) DEFAULT NULL,
  `delete_task_active` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_task_crud_active_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_role_task_crud_active`
--

LOCK TABLES `m_role_task_crud_active` WRITE;
/*!40000 ALTER TABLE `m_role_task_crud_active` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_role_task_crud_active` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_task`
--

DROP TABLE IF EXISTS `m_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_task` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `task_code` varchar(255) DEFAULT NULL,
  `task_category_id` bigint(20) NOT NULL,
  `task_sub_category_id` bigint(20) NOT NULL,
  `crud_type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_task`
--

LOCK TABLES `m_task` WRITE;
/*!40000 ALTER TABLE `m_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_task_category`
--

DROP TABLE IF EXISTS `m_task_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_task_category` (
  `task_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_category_code` varchar(255) DEFAULT NULL,
  `task_category_name` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`task_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_task_category`
--

LOCK TABLES `m_task_category` WRITE;
/*!40000 ALTER TABLE `m_task_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_task_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_task_crud`
--

DROP TABLE IF EXISTS `m_task_crud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_task_crud` (
  `task_crud_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_category_id` bigint(20) DEFAULT NULL,
  `create_task_id` bigint(20) DEFAULT NULL,
  `read_task_id` bigint(20) DEFAULT NULL,
  `update_task_id` bigint(20) DEFAULT NULL,
  `delete_task_id` bigint(20) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `task_crud_name` varchar(255) DEFAULT NULL,
  `task_crud_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_crud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_task_crud`
--

LOCK TABLES `m_task_crud` WRITE;
/*!40000 ALTER TABLE `m_task_crud` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_task_crud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_task_sub_category`
--

DROP TABLE IF EXISTS `m_task_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_task_sub_category` (
  `task_sub_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_sub_category_code` varchar(255) DEFAULT NULL,
  `task_sub_category_name` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`task_sub_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_task_sub_category`
--

LOCK TABLES `m_task_sub_category` WRITE;
/*!40000 ALTER TABLE `m_task_sub_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_task_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_user`
--

DROP TABLE IF EXISTS `m_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `active` varchar(1) DEFAULT NULL,
  `create_datetime` varchar(14) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(14) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_role` varchar(25) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_fullname` varchar(255) DEFAULT NULL,
  `status_login` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_user`
--

LOCK TABLES `m_user` WRITE;
/*!40000 ALTER TABLE `m_user` DISABLE KEYS */;
INSERT INTO `m_user` VALUES (70,'admin','21232f297a57a5a743894a0e4a801fc3','Y','20200902210811',61,'20200903110657',70,'8888','string','43','admin@ad.min','admin 1','N'),(71,'a-report','8f514f69078f636601304d91dc592ed4','Y','20200903115220',70,'20200903110635',70,'string','string','44','string','a-report','N'),(72,'b-report','476eeeee3e5f5f4d5a521b74d0f81774','Y','20200903115237',70,'20200903110619',70,'string','string','44','string','b-report','N'),(73,'c-report','045f413ecb46e025be56e8932460f8e9','Y','20200903115248',70,'20200903110619',70,'string','string','44','string','c-report','N');
/*!40000 ALTER TABLE `m_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('admin',NULL,'{noop}21232f297a57a5a743894a0e4a801fc3','read,write,trust','authorization_code,check_token,refresh_token,password',NULL,NULL,1000,100000000,NULL,NULL),('admin1',NULL,'{noop}e00cf25ad42683b3df678c61f42c6bda','read,write,trust','authorization_code,check_token,refresh_token,password',NULL,NULL,1000,100000000,NULL,NULL),('adminz',NULL,'{noop}8f514f69078f636601304d91dc592ed4','read','authorization_code,check_token,refresh_token,password',NULL,NULL,1000,100000000,NULL,NULL),('b-report',NULL,'{noop}476eeeee3e5f5f4d5a521b74d0f81774','read','authorization_code,check_token,refresh_token,password',NULL,NULL,1000,100000000,NULL,NULL),('c-report',NULL,'{noop}045f413ecb46e025be56e8932460f8e9','read','authorization_code,check_token,refresh_token,password',NULL,NULL,1000,100000000,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_approval_config`
--

DROP TABLE IF EXISTS `t_approval_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_approval_config` (
  `approval_config_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approval_id` bigint(20) DEFAULT NULL,
  `role_task_id_submit` bigint(20) DEFAULT NULL,
  `role_task_id_approval` bigint(20) DEFAULT NULL,
  `flag_need_approval` varchar(14) DEFAULT NULL,
  `create_datetime` varchar(14) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(14) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `active` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`approval_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_approval_config`
--

LOCK TABLES `t_approval_config` WRITE;
/*!40000 ALTER TABLE `t_approval_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_approval_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_approval_request`
--

DROP TABLE IF EXISTS `t_approval_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_approval_request` (
  `approval_request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id_request` bigint(20) DEFAULT NULL,
  `role_id_request` bigint(20) DEFAULT NULL,
  `role_id_approve` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `json_string_data` longtext NOT NULL,
  `json_string_data_before` longtext NOT NULL,
  `json_string_data_after` longtext NOT NULL,
  `request_status` varchar(100) DEFAULT NULL,
  `create_datetime` varchar(50) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(50) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`approval_request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_approval_request`
--

LOCK TABLES `t_approval_request` WRITE;
/*!40000 ALTER TABLE `t_approval_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_approval_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_task`
--

DROP TABLE IF EXISTS `t_role_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_task` (
  `role_task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_task`
--

LOCK TABLES `t_role_task` WRITE;
/*!40000 ALTER TABLE `t_role_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task_api`
--

DROP TABLE IF EXISTS `t_task_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task_api` (
  `task_api_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) DEFAULT NULL,
  `api_name` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`task_api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task_api`
--

LOCK TABLES `t_task_api` WRITE;
/*!40000 ALTER TABLE `t_task_api` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_task_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_datetime` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `update_datetime` varchar(255) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `flg_default_role` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-03 18:07:42
