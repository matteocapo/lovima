-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 19, 2018 alle 18:29
-- Versione del server: 10.1.21-MariaDB
-- Versione PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `type` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `species` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `maxNPost` int(11) DEFAULT NULL,
  `minTemp` int(11) DEFAULT NULL,
  `food` int(11) DEFAULT NULL,
  `foodDoses` int(11) DEFAULT NULL,
  `maxTemp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `boxes`
--

INSERT INTO `boxes` (`id`, `id_box`, `idAnimals`, `dateTime`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `humidity`, `alarm`, `display`, `windler`, `type`) VALUES
(1, '1', '0', NULL, 0, 25, 0, 0, 0, 0, 0, 0, 0, 'T'),
(2, '2', '0', NULL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'T'),
(3, '1', '0', NULL, 0, 0, 0, 0, 233, 0, 0, 0, 0, '0'),
(4, '1', '0', '2018-01-19 17:25:38', 0, 0, 0, 0, 233, 0, 0, 0, 0, '0');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `boxes`
--
ALTER TABLE `boxes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
