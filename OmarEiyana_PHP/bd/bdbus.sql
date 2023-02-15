-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 15, 2022 at 12:08 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdbus`
--

-- --------------------------------------------------------

--
-- Table structure for table `averia`
--

DROP TABLE IF EXISTS `averia`;
CREATE TABLE IF NOT EXISTS `averia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `motivo` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha` date NOT NULL,
  `coste` double NOT NULL,
  `reparada` tinyint(4) NOT NULL,
  `bus` char(10) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus` (`bus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
CREATE TABLE IF NOT EXISTS `bus` (
  `id` char(10) COLLATE utf8_spanish2_ci NOT NULL,
  `capacidad` int(11) NOT NULL,
  `imagen` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`id`, `capacidad`, `imagen`) VALUES
('WMX-0001', 18, 'mercedes 1721.jpg'),
('WMX-0002', 20, 'otokar 2130.jpg'),
('WMX-0003', 35, 'tata 8000.jpg'),
('WMX-0004', 21, 'volvo b12.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `dni` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`dni`, `nombre`, `direccion`, `email`) VALUES
('10845812', 'Jesus Maita', 'Av. Los portales 754', 'jmaita@gmail.com'),
('10858871', 'Nicolas Sanchez', 'Av. Ica 578', 'utnico@gmail.com'),
('30190900', 'Pedro Orbea', 'Paseo Acacias 309 - Lima', 'porbea@correo.pe'),
('41352696', 'Carlos Morales', 'Jr.Aguarico 875', 'cmorales@gmail.com'),
('41522188', 'Lesly Briceno', 'Calle Larco 111', 'lbricen.b@gmail.com'),
('42539687', 'Martin Ramirez', 'Jr.Aguarico 555', 'mramirez@gmail.com'),
('42558685', 'Juan Carlos Damian', 'Jr. Iquitos 235', 'jdamian@gmail.com'),
('44531258', 'Yessel Briceno', 'Jr.Aguarico 649', 'ybricen.b@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pagado` tinyint(4) NOT NULL,
  `numAsiento` int(11) NOT NULL,
  `dni` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `ruta` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dni` (`dni`),
  KEY `ruta` (`ruta`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `reserva`
--

INSERT INTO `reserva` (`id`, `pagado`, `numAsiento`, `dni`, `ruta`) VALUES
(21, 0, 1, '44531258', 1),
(22, 0, 1, '41522188', 2),
(23, 0, 1, '10845812', 3),
(24, 0, 1, '41352696', 4),
(25, 0, 2, '10845812', 1),
(26, 1, 2, '30190900', 2),
(27, 1, 3, '30190900', 2),
(28, 1, 4, '30190900', 2),
(29, 1, 5, '30190900', 2),
(30, 1, 6, '30190900', 2),
(31, 1, 31, '44531258', 6),
(32, 1, 18, '30190900', 1),
(33, 1, 15, '30190900', 5),
(34, 1, 12, '30190900', 5),
(35, 1, 15, '10845812', 7),
(36, 1, 1, '10845812', 9),
(37, 1, 2, '10845812', 9),
(38, 1, 3, '10845812', 9),
(39, 1, 19, '10845812', 9),
(40, 1, 20, '10845812', 9);

-- --------------------------------------------------------

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
CREATE TABLE IF NOT EXISTS `ruta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ciudadOrigen` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `ciudadDestino` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `horaSalida` datetime NOT NULL,
  `horaLlegada` datetime NOT NULL,
  `tarifa` double NOT NULL,
  `bus` char(10) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bus` (`bus`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `ruta`
--

INSERT INTO `ruta` (`id`, `ciudadOrigen`, `ciudadDestino`, `horaSalida`, `horaLlegada`, `tarifa`, `bus`) VALUES
(1, 'Tumbes', 'Barranca', '2022-08-10 11:40:00', '2022-08-24 00:00:00', 74, 'WMX-0002'),
(2, 'Lima', 'Ica', '2022-08-10 09:36:00', '2022-08-24 14:00:00', 88, 'WMX-0004'),
(3, 'Chiclayo', 'Arequipa', '2022-08-25 09:00:00', '2022-08-25 00:00:00', 45, 'WMX-0003'),
(4, 'Lima', 'Barranca', '2022-06-07 18:00:00', '2022-06-07 21:40:00', 35, 'WMX-0002'),
(5, 'Tumbes', 'Arequipa', '2022-08-27 20:15:00', '2022-08-27 00:00:00', 86, 'WMX-0001'),
(6, 'Tumbes', 'Barranca', '2022-06-05 13:10:00', '2022-06-06 14:21:00', 22, 'WMX-0003'),
(7, 'Chiclayo', 'Barranca', '2022-06-22 08:30:00', '2022-06-22 10:00:00', 14, 'WMX-0001'),
(8, 'Tumbes', 'Barranca', '2022-06-16 18:45:00', '2022-06-16 20:30:00', 25, 'WMX-0003'),
(9, 'Chiclayo', 'Ica', '2022-06-22 06:30:00', '2022-06-23 00:00:00', 56, 'WMX-0004');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`ruta`) REFERENCES `ruta` (`id`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`dni`) REFERENCES `cliente` (`dni`);

--
-- Constraints for table `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `ruta_ibfk_1` FOREIGN KEY (`bus`) REFERENCES `bus` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
