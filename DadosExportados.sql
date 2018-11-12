CREATE DATABASE  IF NOT EXISTS `projetoProgramacao` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `projetoProgramacao`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: projetoProgramacao
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `Aluguel`
--

DROP TABLE IF EXISTS `Aluguel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Aluguel` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataAluguel` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quilometroSaida` decimal(12,2) NOT NULL,
  `codTipoAluguel` bigint(20) NOT NULL,
  `codCliente` bigint(20) NOT NULL,
  `codCarro` bigint(20) NOT NULL,
  `codFuncionario` bigint(20) NOT NULL,
  `codFilial` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Aluguel_TipoAluguel` (`codTipoAluguel`),
  KEY `fk_Aluguel_Cliente` (`codCliente`),
  KEY `fk_Aluguel_Carro` (`codCarro`),
  KEY `fk_Aluguel_Funcionario` (`codFuncionario`),
  KEY `fk_Aluguel_Filial` (`codFilial`),
  CONSTRAINT `fk_Aluguel_Carro` FOREIGN KEY (`codCarro`) REFERENCES `Carro` (`codigo`),
  CONSTRAINT `fk_Aluguel_Cliente` FOREIGN KEY (`codCliente`) REFERENCES `Cliente` (`codigo`),
  CONSTRAINT `fk_Aluguel_Filial` FOREIGN KEY (`codFilial`) REFERENCES `Filial` (`codigo`),
  CONSTRAINT `fk_Aluguel_Funcionario` FOREIGN KEY (`codFuncionario`) REFERENCES `Funcionario` (`codigo`),
  CONSTRAINT `fk_Aluguel_TipoAluguel` FOREIGN KEY (`codTipoAluguel`) REFERENCES `TipoAluguel` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Aluguel`
--

LOCK TABLES `Aluguel` WRITE;
/*!40000 ALTER TABLE `Aluguel` DISABLE KEYS */;
INSERT INTO `Aluguel` VALUES (1,'2018-11-09 00:00:00',120000.00,4,2,3,4,2),(2,'2018-11-12 00:00:00',1200000.00,2,2,5,4,2),(3,'2018-11-12 00:00:00',0.00,2,1,1,4,2);
/*!40000 ALTER TABLE `Aluguel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AquisicaoVeiculo`
--

DROP TABLE IF EXISTS `AquisicaoVeiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AquisicaoVeiculo` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  `codCarro` bigint(20) NOT NULL,
  `codFilial` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_AquisicaoVeiculo_Carro` (`codCarro`),
  KEY `fk_AquisicaoVeiculo_Filial` (`codFilial`),
  CONSTRAINT `fk_AquisicaoVeiculo_Carro` FOREIGN KEY (`codCarro`) REFERENCES `Carro` (`codigo`),
  CONSTRAINT `fk_AquisicaoVeiculo_Filial` FOREIGN KEY (`codFilial`) REFERENCES `Filial` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AquisicaoVeiculo`
--

LOCK TABLES `AquisicaoVeiculo` WRITE;
/*!40000 ALTER TABLE `AquisicaoVeiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `AquisicaoVeiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Carro`
--

DROP TABLE IF EXISTS `Carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Carro` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `valor` decimal(12,2) NOT NULL,
  `cor` varchar(45) NOT NULL,
  `ano` datetime NOT NULL,
  `placa` varchar(8) NOT NULL,
  `disponivel` tinyint(1) DEFAULT NULL,
  `dataAquisicao` datetime NOT NULL,
  `dataDesapropriacao` datetime DEFAULT NULL,
  `codFilial` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Carro`
--

LOCK TABLES `Carro` WRITE;
/*!40000 ALTER TABLE `Carro` DISABLE KEYS */;
INSERT INTO `Carro` VALUES (1,'Fiat','Uno',12.00,'Branco','2007-11-03 00:00:00','UNO-2007',0,'2018-11-09 00:00:00',NULL,2),(2,'BMW','i8',500.00,'Cinza','2015-11-07 00:00:00','ABX-1015',1,'2018-11-09 00:00:00',NULL,3),(3,'Fiat','Argo',48.90,'Vermelho','2018-11-09 00:00:00','LHX-0028',0,'2018-11-09 00:00:00',NULL,3),(4,'Ford','Ecoesport',74.90,'Branco','2017-11-10 00:00:00','PQW-5467',1,'2018-11-09 00:00:00',NULL,3),(5,'Ford','Focus',68.90,'Cinza','2016-11-18 00:00:00','YUN-1213',1,'2018-11-09 00:00:00',NULL,2),(6,'Peugeot','208 Active ',52.00,'Vermelho','2018-11-08 00:00:00','PEU-2080',1,'2018-11-09 00:00:00',NULL,2),(7,'Suzuki','Jimny',70.90,'Cinza','2018-11-16 00:00:00','SZK-2018',1,'2018-11-09 00:00:00',NULL,1),(8,'Mitsubishi','Lancer',74.90,'Cinza','2018-11-15 00:00:00','MTB-2018',1,'2018-11-09 00:00:00',NULL,1),(9,'Citroen','Jumpy',93.90,'Branco','2018-11-15 00:00:00','JMP-2018',1,'2018-11-09 00:00:00',NULL,3);
/*!40000 ALTER TABLE `Carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `sobrenome` varchar(100) NOT NULL,
  `dataNascimento` datetime NOT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(150) NOT NULL,
  `dataCadastro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cnh` varchar(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES (1,'Juliana',' Jesus','1998-11-12 00:00:00','987532103','61690023368','sonianataliabarbosa@provale.com.br','2018-11-09 00:00:00','28549780408'),(2,'Augusto','Porto','1996-11-27 00:00:00','8238972300','40504817663','diogoporto-77@castromobile.com.br','2018-11-09 00:00:00','28252673009'),(3,'Caio Daniel ','Thiago Rodrigues','1996-11-13 00:00:00','6625875693','05486770189','caiodanielthiagorodrigues@novaequipem.com.br','2018-11-09 00:00:00','31518850094');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ControleFuncionarios`
--

DROP TABLE IF EXISTS `ControleFuncionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ControleFuncionarios` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  `dataModificacao` datetime DEFAULT NULL,
  `codFilial` bigint(20) NOT NULL,
  `codFuncionario` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_ControleFuncionarios_Funcionario` (`codFuncionario`),
  KEY `fk_ControleFuncionarios_Filial` (`codFilial`),
  CONSTRAINT `fk_ControleFuncionarios_Filial` FOREIGN KEY (`codFilial`) REFERENCES `Filial` (`codigo`),
  CONSTRAINT `fk_ControleFuncionarios_Funcionario` FOREIGN KEY (`codFuncionario`) REFERENCES `Funcionario` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ControleFuncionarios`
--

LOCK TABLES `ControleFuncionarios` WRITE;
/*!40000 ALTER TABLE `ControleFuncionarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `ControleFuncionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Devolucao`
--

DROP TABLE IF EXISTS `Devolucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Devolucao` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataChegada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quilometragemChegada` decimal(12,2) NOT NULL,
  `codAluguel` bigint(20) NOT NULL,
  `codTipoPagamento` bigint(20) NOT NULL,
  `valorTotal` decimal(12,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Devolucao_Aluguel` (`codAluguel`),
  KEY `fk_Devolucao_TipoPagamento` (`codTipoPagamento`),
  CONSTRAINT `fk_Devolucao_Aluguel` FOREIGN KEY (`codAluguel`) REFERENCES `Aluguel` (`codigo`),
  CONSTRAINT `fk_Devolucao_TipoPagamento` FOREIGN KEY (`codTipoPagamento`) REFERENCES `TipoPagamento` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Devolucao`
--

LOCK TABLES `Devolucao` WRITE;
/*!40000 ALTER TABLE `Devolucao` DISABLE KEYS */;
INSERT INTO `Devolucao` VALUES (1,'2018-11-12 00:00:00',13000.00,2,1,16.06);
/*!40000 ALTER TABLE `Devolucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Filial`
--

DROP TABLE IF EXISTS `Filial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Filial` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Filial`
--

LOCK TABLES `Filial` WRITE;
/*!40000 ALTER TABLE `Filial` DISABLE KEYS */;
INSERT INTO `Filial` VALUES (1,'Evelyn e Maria Transportes Ltda','Campinas','SP'),(2,'AlugaFacil','Xanxere','SC'),(3,'AluguaDificil','Chapeco','SC');
/*!40000 ALTER TABLE `Filial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Funcionario`
--

DROP TABLE IF EXISTS `Funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Funcionario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `sobrenome` varchar(100) NOT NULL,
  `dataNascimento` datetime NOT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `salario` decimal(12,2) DEFAULT NULL,
  `dataAdmissao` datetime DEFAULT NULL,
  `dataDemissao` datetime DEFAULT NULL,
  `codFilial` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Funcionario`
--

LOCK TABLES `Funcionario` WRITE;
/*!40000 ALTER TABLE `Funcionario` DISABLE KEYS */;
INSERT INTO `Funcionario` VALUES (1,'Daniela','Freitas','1995-11-08 00:00:00','3433-1231','40180459724','danifreitas@hotmail.com','jK1qD2Kmy5',1200.00,'2018-11-09 00:00:00',NULL,3),(2,'Francisca Ayla ','Campos','1992-11-25 00:00:00','98294-3408','04241442048','franciscaaylaandreiacampos_@msds.com.br','bFTohfG9wI',1600.00,'2018-11-09 00:00:00',NULL,3),(3,'Luciano','Virmes','1997-08-01 00:00:00','49998885394','95427456643','lucianovirmes@gmail.com','abc123',15000.00,'2018-11-09 00:00:00',NULL,2),(4,'Theylor','Marmitt','1998-11-12 00:00:00','49991848417','64029189709','theylor.marmitt@unoesc.com','123456',15000.00,'2018-11-09 00:00:00',NULL,2),(5,'Everson','Signori','1995-11-16 00:00:00','998057363','41288400500','eversonsignori@unoesc.edu.br','hIgx9XPBZz',3000.00,'2018-11-09 00:00:00',NULL,1);
/*!40000 ALTER TABLE `Funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoAluguel`
--

DROP TABLE IF EXISTS `TipoAluguel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoAluguel` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `valor` decimal(12,2) NOT NULL,
  `taxa` decimal(12,2) NOT NULL,
  `inativo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoAluguel`
--

LOCK TABLES `TipoAluguel` WRITE;
/*!40000 ALTER TABLE `TipoAluguel` DISABLE KEYS */;
INSERT INTO `TipoAluguel` VALUES (1,'vista',40.00,0.20,0),(2,'Regional',20.00,1.00,1),(3,'Inter-Estadual',50.00,1.50,1),(4,'Internacional',100.00,2.00,1);
/*!40000 ALTER TABLE `TipoAluguel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoPagamento`
--

DROP TABLE IF EXISTS `TipoPagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoPagamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `desconto` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoPagamento`
--

LOCK TABLES `TipoPagamento` WRITE;
/*!40000 ALTER TABLE `TipoPagamento` DISABLE KEYS */;
INSERT INTO `TipoPagamento` VALUES (1,'A vista',20.00),(2,'A prazo',0.00);
/*!40000 ALTER TABLE `TipoPagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `aquisicao_veiculo`
--

DROP TABLE IF EXISTS `aquisicao_veiculo`;
/*!50001 DROP VIEW IF EXISTS `aquisicao_veiculo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `aquisicao_veiculo` AS SELECT 
 1 AS `marca`,
 1 AS `modelo`,
 1 AS `placa`,
 1 AS `data_aquisicao`,
 1 AS `nome_filial`,
 1 AS `uf`,
 1 AS `ultima_modificacao`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `carros_ativos_disponiveis`
--

DROP TABLE IF EXISTS `carros_ativos_disponiveis`;
/*!50001 DROP VIEW IF EXISTS `carros_ativos_disponiveis`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `carros_ativos_disponiveis` AS SELECT 
 1 AS `codigo`,
 1 AS `marca`,
 1 AS `modelo`,
 1 AS `valor`,
 1 AS `cor`,
 1 AS `ano`,
 1 AS `placa`,
 1 AS `disponivel`,
 1 AS `dataAquisicao`,
 1 AS `dataDesapropriacao`,
 1 AS `codFilial`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `controle_de_funcionarios`
--

DROP TABLE IF EXISTS `controle_de_funcionarios`;
/*!50001 DROP VIEW IF EXISTS `controle_de_funcionarios`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `controle_de_funcionarios` AS SELECT 
 1 AS `nome`,
 1 AS `sobrenome`,
 1 AS `cpf`,
 1 AS `data_admissao`,
 1 AS `nome_filial`,
 1 AS `uf`,
 1 AS `ultima_modificacao`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `aquisicao_veiculo`
--

/*!50001 DROP VIEW IF EXISTS `aquisicao_veiculo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `aquisicao_veiculo` AS select `c`.`marca` AS `marca`,`c`.`modelo` AS `modelo`,`c`.`placa` AS `placa`,`c`.`dataAquisicao` AS `data_aquisicao`,`fil`.`nome` AS `nome_filial`,`fil`.`uf` AS `uf`,`a`.`dataModificacao` AS `ultima_modificacao` from ((`carro` `c` join `aquisicaoveiculo` `a` on((`c`.`codigo` = `a`.`codCarro`))) join `filial` `fil` on((`a`.`codFilial` = `fil`.`codigo`))) group by `a`.`codigo` order by `fil`.`uf`,`nome_filial` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `carros_ativos_disponiveis`
--

/*!50001 DROP VIEW IF EXISTS `carros_ativos_disponiveis`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `carros_ativos_disponiveis` AS select `carro`.`codigo` AS `codigo`,`carro`.`marca` AS `marca`,`carro`.`modelo` AS `modelo`,`carro`.`valor` AS `valor`,`carro`.`cor` AS `cor`,`carro`.`ano` AS `ano`,`carro`.`placa` AS `placa`,`carro`.`disponivel` AS `disponivel`,`carro`.`dataAquisicao` AS `dataAquisicao`,`carro`.`dataDesapropriacao` AS `dataDesapropriacao`,`carro`.`codFilial` AS `codFilial` from `carro` where ((`carro`.`disponivel` = TRUE) and (`carro`.`dataDesapropriacao` = NULL)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `controle_de_funcionarios`
--

/*!50001 DROP VIEW IF EXISTS `controle_de_funcionarios`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `controle_de_funcionarios` AS select `f`.`nome` AS `nome`,`f`.`sobrenome` AS `sobrenome`,`f`.`cpf` AS `cpf`,`f`.`dataAdmissao` AS `data_admissao`,`fil`.`nome` AS `nome_filial`,`fil`.`uf` AS `uf`,`c`.`dataModificacao` AS `ultima_modificacao` from ((`funcionario` `f` join `controlefuncionarios` `c` on((`f`.`codigo` = `c`.`codFuncionario`))) join `filial` `fil` on((`c`.`codFilial` = `fil`.`codigo`))) group by `f`.`codigo`,`nome_filial`,`fil`.`uf`,`ultima_modificacao` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-12 20:48:31
