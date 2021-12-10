-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2021 a las 18:14:55
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdinvernadero`
--
CREATE DATABASE IF NOT EXISTS `bdinvernadero` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdinvernadero`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

DROP TABLE IF EXISTS `ejemplares`;
CREATE TABLE `ejemplares` (
  `id` bigint(20) NOT NULL,
  `edad` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `fechaPlantacion` date DEFAULT NULL,
  `idLocalizacion` bigint(20) DEFAULT NULL,
  `idPlanta` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `edad`, `fechaCompra`, `fechaPlantacion`, `idLocalizacion`, `idPlanta`) VALUES
(1, 1, '2020-05-02', '2020-05-04', 1, 1),
(2, 2, '2020-05-02', '2020-05-04', 2, 1),
(3, 1, '2020-05-02', '2020-05-05', 3, 9),
(4, 1, '2020-05-02', '2020-05-05', 4, 9),
(5, 1, '2020-05-03', '2020-05-10', 5, 2),
(6, 1, '2020-05-03', '2020-05-07', 6, 2),
(7, 2, '2020-05-03', '2020-05-10', 7, 2),
(8, 2, '2020-05-03', '2020-05-10', 8, 2),
(10, 22, '2022-07-05', '2022-07-05', 1, 7),
(11, 12, '2022-07-05', '2022-07-05', 37, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `localizaciones`
--

DROP TABLE IF EXISTS `localizaciones`;
CREATE TABLE `localizaciones` (
  `id` bigint(20) NOT NULL,
  `longitud` char(1) NOT NULL,
  `latitud` char(1) NOT NULL,
  `gradosLongitud` float NOT NULL,
  `gradosLatitud` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `localizaciones`
--

INSERT INTO `localizaciones` (`id`, `longitud`, `latitud`, `gradosLongitud`, `gradosLatitud`) VALUES
(1, 'E', 'S', 124.172, -10.875),
(2, 'E', 'S', 126.172, -10.175),
(3, 'E', 'S', 126.378, -10.179),
(4, 'E', 'S', 125.297, -10.655),
(5, 'E', 'S', 125.307, -10.71),
(6, 'E', 'S', 124.902, -10.115),
(7, 'E', 'S', 122.772, -11.005),
(8, 'O', 'S', 123.882, -9.975),
(23, 'O', 'N', 23, 623),
(26, 'E', 'N', -4, -33),
(32, 'O', 'N', 99, -99.9),
(33, 'O', 'N', 643, 35),
(34, 'O', 'S', -22.66, -66.22),
(35, 'O', 'S', 1241, 1241),
(37, 'E', 'S', 9.94, 45.73);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parcelas`
--

DROP TABLE IF EXISTS `parcelas`;
CREATE TABLE `parcelas` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `area` float NOT NULL,
  `privada` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `parcelas`
--

INSERT INTO `parcelas` (`id`, `nombre`, `area`, `privada`) VALUES
(1, 'Parcela Este', 24.7, b'0'),
(2, 'Parcela fuente', 5.8, b'1'),
(4, 'Parcela Suroeste', 6.8, b'0'),
(5, 'Parcela cactus', 8.4, b'0'),
(6, 'Parcela nueva', 40.22, b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantaparcela`
--

DROP TABLE IF EXISTS `plantaparcela`;
CREATE TABLE `plantaparcela` (
  `idplanta` bigint(11) NOT NULL,
  `idparcela` bigint(11) NOT NULL,
  `porcentaje` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `plantaparcela`
--

INSERT INTO `plantaparcela` (`idplanta`, `idparcela`, `porcentaje`) VALUES
(1, 1, '10.00'),
(1, 2, '4.00'),
(2, 1, '12.75'),
(10, 1, '22.55');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

DROP TABLE IF EXISTS `plantas`;
CREATE TABLE `plantas` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`id`, `nombre`) VALUES
(1, 'Acer palmatum'),
(5, 'Cucumis melo'),
(10, 'Cupressus sempervirens'),
(8, 'Echeveria elegans'),
(6, 'Hibiscus rosa-sinensis'),
(7, 'Jasminum officinale'),
(3, 'Lavandula angustifolia'),
(9, 'Pinus longaeva'),
(12, 'Polytrichum strictum'),
(2, 'Taxus Reis'),
(11, 'Ulva lactuca');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_EJEMPLARLOCAL` (`idLocalizacion`),
  ADD KEY `FK_EJEMPLARPLAnTAS` (`idPlanta`);

--
-- Indices de la tabla `localizaciones`
--
ALTER TABLE `localizaciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `parcelas`
--
ALTER TABLE `parcelas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `plantaparcela`
--
ALTER TABLE `plantaparcela`
  ADD PRIMARY KEY (`idplanta`,`idparcela`),
  ADD KEY `FK_PARCELA` (`idparcela`,`idplanta`) USING BTREE;

--
-- Indices de la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `localizaciones`
--
ALTER TABLE `localizaciones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `parcelas`
--
ALTER TABLE `parcelas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `plantas`
--
ALTER TABLE `plantas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `FK_EJEMPLARLOCAL` FOREIGN KEY (`idLocalizacion`) REFERENCES `localizaciones` (`id`),
  ADD CONSTRAINT `FK_EJEMPLARPLAnTAS` FOREIGN KEY (`idPlanta`) REFERENCES `plantas` (`id`);

--
-- Filtros para la tabla `plantaparcela`
--
ALTER TABLE `plantaparcela`
  ADD CONSTRAINT `FKPLAnTA` FOREIGN KEY (`idplanta`) REFERENCES `plantas` (`id`),
  ADD CONSTRAINT `FK_PARCELA` FOREIGN KEY (`idparcela`) REFERENCES `parcelas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
