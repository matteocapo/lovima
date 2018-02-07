-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Creato il: Feb 07, 2018 alle 11:17
-- Versione del server: 5.7.19
-- Versione PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `se4asdb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `active_boxes`
--

DROP TABLE IF EXISTS `active_boxes`;
CREATE TABLE IF NOT EXISTS `active_boxes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_box` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `animals`
--

DROP TABLE IF EXISTS `animals`;
CREATE TABLE IF NOT EXISTS `animals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_animal` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `type` varchar(30) CHARACTER SET utf8 DEFAULT '0',
  `species` varchar(30) CHARACTER SET utf8 DEFAULT '0',
  `sex` varchar(5) CHARACTER SET utf8 DEFAULT '0',
  `maxNPost` int(11) DEFAULT '0',
  `food` int(11) DEFAULT '0',
  `foodDoses` int(11) DEFAULT '0',
  `minTemp` float DEFAULT '0',
  `maxTemp` float DEFAULT '0',
  `minhum` float NOT NULL DEFAULT '0',
  `maxhum` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `animals`
--

INSERT INTO `animals` (`id`, `id_animal`, `type`, `species`, `sex`, `maxNPost`, `food`, `foodDoses`, `minTemp`, `maxTemp`, `minhum`, `maxhum`) VALUES
(1, '1', 'viper', 'snake', 'm', 4, 10, 10, 10, 30, 40, 90),
(2, '2', 'Iguana', 'Rettile', 'f', 1, 500, 2, 30, 40, 45, 85),
(3, '3', 'Ragno', 'Aracnide', 'm', 2, 100, 1, 20, 25, 30, 50);

-- --------------------------------------------------------

--
-- Struttura della tabella `boxes`
--

DROP TABLE IF EXISTS `boxes`;
CREATE TABLE IF NOT EXISTS `boxes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_box` varchar(11) DEFAULT '0',
  `idAnimals` varchar(11) DEFAULT '0',
  `dateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `animalsN` int(11) DEFAULT '0',
  `temp` float DEFAULT '0',
  `foodQnt` double DEFAULT '0',
  `waterQnt` double DEFAULT '0',
  `light` int(11) DEFAULT '0',
  `humidity` float DEFAULT '0',
  `alarm` tinyint(1) DEFAULT '0',
  `display` tinyint(1) DEFAULT '0',
  `windler` tinyint(1) DEFAULT '0',
  `type` varchar(30) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_box` (`id_box`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `boxes`
--

INSERT INTO `boxes` (`id`, `id_box`, `idAnimals`, `dateTime`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `humidity`, `alarm`, `display`, `windler`, `type`) VALUES
(19, '1', '3', '2018-02-07 10:41:57', 1, 0, 0, 0, 0, 0, 0, 0, 0, '0'),
(20, '1', '3', '2018-02-07 10:45:47', 0, 40, 0, 0, 500, 22, 0, 0, 0, 'T'),
(21, '1', '3', '2018-02-07 10:47:38', 0, 40, 0, 0, 500, 22, 0, 1, 0, 'T'),
(22, '1', '3', '2018-02-07 10:48:12', 0, 40, 0, 0, 500, 22, 0, 1, 1, 'T'),
(23, '1', '3', '2018-02-07 10:48:26', 0, 40, 0, 0, 500, 22, 1, 1, 1, 'T'),
(24, '1', '3', '2018-02-07 10:49:42', 0, 40, 10, 0, 500, 22, 1, 1, 1, 'T'),
(25, '1', '3', '2018-02-07 10:49:43', 0, 40, 20, 0, 500, 22, 1, 1, 1, 'T'),
(26, '1', '3', '2018-02-07 10:49:43', 0, 40, 30, 0, 500, 22, 1, 1, 1, 'T'),
(27, '1', '3', '2018-02-07 10:49:43', 0, 40, 40, 0, 500, 22, 1, 1, 1, 'T'),
(28, '1', '3', '2018-02-07 10:49:43', 0, 40, 50, 0, 500, 22, 1, 1, 1, 'T'),
(29, '1', '3', '2018-02-07 10:49:44', 0, 40, 60, 0, 500, 22, 1, 1, 1, 'T'),
(30, '1', '3', '2018-02-07 10:49:44', 0, 40, 70, 0, 500, 22, 1, 1, 1, 'T'),
(31, '1', '3', '2018-02-07 10:49:44', 0, 40, 80, 0, 500, 22, 1, 1, 1, 'T'),
(32, '1', '3', '2018-02-07 10:49:44', 0, 40, 90, 0, 500, 22, 1, 1, 1, 'T'),
(33, '1', '3', '2018-02-07 10:49:45', 0, 40, 100, 0, 500, 22, 1, 1, 1, 'T'),
(34, '1', '3', '2018-02-07 10:49:54', 0, 40, 100, 10, 500, 22, 1, 1, 1, 'T'),
(35, '1', '3', '2018-02-07 10:49:54', 0, 40, 100, 20, 500, 22, 1, 1, 1, 'T'),
(36, '1', '3', '2018-02-07 10:49:55', 0, 40, 100, 30, 500, 22, 1, 1, 1, 'T'),
(37, '1', '3', '2018-02-07 10:49:55', 0, 40, 100, 40, 500, 22, 1, 1, 1, 'T'),
(38, '1', '3', '2018-02-07 10:49:55', 0, 40, 100, 50, 500, 22, 1, 1, 1, 'T'),
(39, '1', '3', '2018-02-07 10:49:55', 0, 40, 100, 60, 500, 22, 1, 1, 1, 'T'),
(40, '1', '3', '2018-02-07 10:49:55', 0, 40, 100, 70, 500, 22, 1, 1, 1, 'T'),
(41, '1', '3', '2018-02-07 10:49:55', 0, 40, 100, 80, 500, 22, 1, 1, 1, 'T'),
(42, '1', '3', '2018-02-07 10:49:56', 0, 40, 100, 90, 500, 22, 1, 1, 1, 'T'),
(43, '1', '3', '2018-02-07 10:49:56', 0, 40, 100, 100, 500, 22, 1, 1, 1, 'T'),
(44, '1', '3', '2018-02-07 10:50:07', 0, 40, 100, 100, 500, 22, 0, 1, 1, 'T'),
(45, '1', '3', '2018-02-07 10:51:10', 0, 40, 100, 100, 500, 26, 0, 1, 1, 'T'),
(46, '1', '3', '2018-02-07 10:52:01', 0, 26, 100, 100, 500, 40, 0, 1, 1, 'T'),
(47, '1', '3', '2018-02-07 10:52:15', 0, 26, 100, 100, 500, 40, 0, 0, 1, 'T'),
(48, '1', '3', '2018-02-07 10:54:24', 0, 22, 100, 100, 500, 40, 0, 0, 1, 'T'),
(49, '1', '3', '2018-02-07 10:54:35', 0, 22, 100, 100, 500, 40, 0, 0, 0, 'T'),
(50, '1', '3', '2018-02-07 10:55:14', 0, 28, 100, 100, 500, 40, 0, 0, 0, 'T'),
(51, '1', '3', '2018-02-07 10:55:25', 0, 28, 100, 100, 500, 40, 0, 0, 1, 'T'),
(52, '1', '3', '2018-02-07 10:58:33', 0, 28, 100, 100, 700, 40, 0, 0, 1, 'T'),
(53, '1', '3', '2018-02-07 10:58:45', 0, 28, 100, 100, 700, 40, 1, 0, 1, 'T'),
(54, '1', '3', '2018-02-07 10:58:56', 0, 28, 100, 100, 700, 40, 1, 0, 0, 'T'),
(55, '1', '3', '2018-02-07 10:59:04', 0, 28, 100, 100, 700, 40, 1, 0, 1, 'T');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
