-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Creato il: Gen 23, 2018 alle 17:37
-- Versione del server: 5.5.42
-- Versione PHP: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `se4asdb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `active_boxes`
--

CREATE TABLE `active_boxes` (
  `id` int(11) NOT NULL,
  `id_box` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `animals`
--

CREATE TABLE `animals` (
  `id` int(11) NOT NULL,
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
  `maxhum` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `animals`
--

INSERT INTO `animals` (`id`, `id_animal`, `type`, `species`, `sex`, `maxNPost`, `food`, `foodDoses`, `minTemp`, `maxTemp`, `minhum`, `maxhum`) VALUES
(1, '1', 'viper', 'snake', 'm', 4, 10, 10, 10, 30, 40, 90);

-- --------------------------------------------------------

--
-- Struttura della tabella `boxes`
--

CREATE TABLE `boxes` (
  `id` int(11) NOT NULL,
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
  `type` varchar(30) DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `boxes`
--

INSERT INTO `boxes` (`id`, `id_box`, `idAnimals`, `dateTime`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `humidity`, `alarm`, `display`, `windler`, `type`) VALUES
(1, '1', '0', NULL, 0, 25, 0, 0, 0, 0, 0, 0, 0, 'T'),
(2, '2', '0', NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'T'),
(3, '1', '0', NULL, 0, 0, 0, 0, 233, 0, 0, 0, 0, '0'),
(4, '1', '0', '2018-01-19 17:25:38', 0, 0, 0, 0, 233, 0, 0, 0, 0, '0'),
(5, '3', '0', '2018-01-20 14:50:08', 0, 0, 0, 0, 0, 0, 0, 0, 0, '0'),
(6, '3', '0', '2018-01-20 14:51:01', 0, 0, 0, 0, 0, 0, 1, 0, 0, '0'),
(7, '1', '0', '2018-01-20 14:58:39', 0, 0, 0, 0, 0, 0, 0, 0, 0, '0'),
(8, '25', '123', '2018-01-20 15:26:16', 0, 0, 0, 0, 0, 0, 0, 0, 0, 'T'),
(11, '25', '0', '2018-01-20 15:28:06', 1, 300, 1, 1, 111, 11112, 0, 0, 0, '0'),
(12, '25', '0', '2018-01-20 16:24:39', 300, 1, 1, 1, 111, 11112, 1, 0, 0, '0'),
(13, '25', '0', '2018-01-20 16:25:55', 300, 1, 1, 1, 111, 11112, 1, 0, 0, '0'),
(14, '25', '0', '2018-01-20 16:28:17', 300, 1, 1, 1, 111, 11112, 1, 1, 0, '0'),
(15, '25', '0', '2018-01-20 16:39:01', 300, 1, 50, 1, 111, 11112, 1, 1, 0, '0'),
(16, '25', '0', '2018-01-20 16:42:42', 300, 1, 50, 1, 111, 11112, 1, 1, 0, '0'),
(17, '25', '0', '2018-01-20 16:42:42', 300, 1, 50, 1, 111, 23.6, 1, 1, 0, '0');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `active_boxes`
--
ALTER TABLE `active_boxes`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `boxes`
--
ALTER TABLE `boxes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_box` (`id_box`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `active_boxes`
--
ALTER TABLE `active_boxes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `animals`
--
ALTER TABLE `animals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT per la tabella `boxes`
--
ALTER TABLE `boxes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;