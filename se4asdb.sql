-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Creato il: Gen 19, 2018 alle 17:51
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
  `id_box` varchar(11) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `active_boxes`
--

INSERT INTO `active_boxes` (`id_box`) VALUES
('1'),
('2');

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
  `id_box` varchar(11) DEFAULT '0',
  `idAnimals` varchar(11) DEFAULT '0',
  `dateTime` date DEFAULT NULL,
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

INSERT INTO `boxes` (`id_box`, `idAnimals`, `dateTime`, `animalsN`, `temp`, `foodQnt`, `waterQnt`, `light`, `humidity`, `alarm`, `display`, `windler`, `type`) VALUES
('1', '0', NULL, 0, 56.7, 0, 0, 0, 0, 0, 0, 0, '0'),
('2', '0', NULL, 0, 20.6, 0, 0, 0, 0, 0, 0, 0, '0');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `active_boxes`
--
ALTER TABLE `active_boxes`
  ADD PRIMARY KEY (`id_box`),
  ADD KEY `id_boxes` (`id_box`);

--
-- Indici per le tabelle `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `boxes`
--
ALTER TABLE `boxes`
  ADD KEY `id_box` (`id_box`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `animals`
--
ALTER TABLE `animals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;