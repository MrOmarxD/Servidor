-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 13-02-2023 a las 17:08:59
-- Versión del servidor: 8.0.27
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdcharlas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `charla`
--

DROP TABLE IF EXISTS `charla`;
CREATE TABLE IF NOT EXISTS `charla` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tema` varchar(60) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `dia` date NOT NULL,
  `hora` int NOT NULL,
  `minutos` int NOT NULL,
  `tarifa` double NOT NULL,
  `sala` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sala` (`sala`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `charla`
--

INSERT INTO `charla` (`id`, `tema`, `dia`, `hora`, `minutos`, `tarifa`, `sala`) VALUES
(1, 'Criptografía: RSA', '2023-02-15', 11, 30, 80, 'SAT1-01'),
(2, 'Redes IPv4', '2021-02-04', 13, 0, 64, 'SDM-PB'),
(3, 'Redes IPV4', '2021-02-04', 16, 0, 64, 'SEG1-02'),
(4, 'Aplicación de LOPD', '2023-02-15', 11, 15, 80, 'SAT1-01'),
(5, 'Desarrollo Apps iPhone', '2021-02-14', 12, 15, 80, 'SPN3-01'),
(6, 'Linux Exploiting', '2023-02-15', 8, 30, 64, 'SEG1-02'),
(7, 'Linux Exploiting', '2021-02-15', 16, 30, 64, 'SPN3-01'),
(8, 'Ciberseguridad: Mitos', '2021-02-15', 20, 30, 80, 'SEG1-02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `dni` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(60) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`dni`, `nombre`, `email`) VALUES
('10845812', 'Jesus Marigorta', 'jmaita@gmail.com'),
('10858871', 'Nicolas Sanchez', 'utnico@gmail.com'),
('30190900', 'Pedro Orbea', 'porbea@correo.org'),
('41352696', 'Carlos Morales', 'cmorales@gmail.com'),
('41522188', 'Luisa Briceno', 'lbricen.b@gmail.com'),
('42539687', 'Martin Ramirez', 'mramirez@gmail.com'),
('42558685', 'Juan Carlos Damian', 'jdamian@gmail.com'),
('44531258', 'Yolanda Briceno', 'ybricen.b@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pagado` tinyint NOT NULL,
  `dni` varchar(10) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `charla` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dni` (`dni`),
  KEY `charla` (`charla`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `pagado`, `dni`, `charla`) VALUES
(1, 0, '44531258', 1),
(2, 0, '41522188', 2),
(3, 0, '41352696', 4),
(4, 0, '10845812', 1),
(5, 1, '30190900', 2),
(6, 1, '30190900', 2),
(7, 1, '44531258', 6),
(8, 1, '30190900', 6),
(9, 1, '30190900', 1),
(10, 1, '10845812', 7),
(11, 1, '30190900', 7),
(12, 1, '42539687', 7),
(13, 1, '44531258', 7),
(14, 1, '10858871', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

DROP TABLE IF EXISTS `sala`;
CREATE TABLE IF NOT EXISTS `sala` (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `capacidad` int NOT NULL,
  `preparada` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`id`, `capacidad`, `preparada`) VALUES
('SAT1-01', 8, 1),
('SDM-PB', 20, 0),
('SEG1-02', 15, 1),
('SPN3-01', 10, 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `charla`
--
ALTER TABLE `charla`
  ADD CONSTRAINT `charla_ibfk_1` FOREIGN KEY (`sala`) REFERENCES `sala` (`id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`charla`) REFERENCES `charla` (`id`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`dni`) REFERENCES `cliente` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
