-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-06-2023 a las 02:02:44
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `mail` varchar(50) NOT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `last_log` date DEFAULT NULL,
  `session` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`mail`, `pass`, `name`, `last_log`, `session`) VALUES
('miguel11nemo@gmail.com', '1234', 'Miguel', '2023-06-05', 109637318),
('miguel12nemo@gmail.com', '1234', 'Miguel2', '2023-06-03', 450818917),
('miguel13nemo@gmail.com', '1234', 'Miguel3', '2023-06-05', 5540475);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `tmax` double DEFAULT NULL,
  `tmin` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `tmax`, `tmin`) VALUES
(1, 'Medicina A', 30, 10),
(2, 'Medicina B', 10, 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient`
--

CREATE TABLE `patient` (
  `mail` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `patient`
--

INSERT INTO `patient` (`mail`, `name`) VALUES
('correo@ejemplo.com', 'Paciente A'),
('miguel@adadw.com', 'Miguel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xip`
--

CREATE TABLE `xip` (
  `id` int(10) NOT NULL,
  `doctor_mail` varchar(50) DEFAULT NULL,
  `id_medicine` bigint(20) UNSIGNED NOT NULL,
  `id_patient` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `xip`
--

INSERT INTO `xip` (`id`, `doctor_mail`, `id_medicine`, `id_patient`, `date`) VALUES
(0, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2023-05-26'),
(1, 'miguel11nemo@gmail.com', 2, 'correo@ejemplo.com', '2023-09-13'),
(4, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2024-12-12'),
(5, 'miguel11nemo@gmail.com', 2, 'miguel@adadw.com', '2025-12-24'),
(12, 'miguel13nemo@gmail.com', 1, 'correo@ejemplo.com', '2028-12-12'),
(44, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2024-12-12'),
(50, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2025-12-12'),
(55, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2055-12-12'),
(100, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2030-12-12'),
(101, 'miguel12nemo@gmail.com', 1, 'miguel@adadw.com', '2025-12-12'),
(102, 'miguel12nemo@gmail.com', 1, 'correo@ejemplo.com', '2025-12-12'),
(111, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2028-12-12'),
(444, 'miguel12nemo@gmail.com', 1, 'correo@ejemplo.com', '2025-12-12'),
(1999, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2034-12-12'),
(112122, 'miguel11nemo@gmail.com', 1, 'correo@ejemplo.com', '2047-12-12'),
(12312312, 'miguel11nemo@gmail.com', 1, 'miguel@adadw.com', '2035-12-28');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `xip`
--
ALTER TABLE `xip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `doctor_mail` (`doctor_mail`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_medicine` (`id_medicine`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `xip`
--
ALTER TABLE `xip`
  MODIFY `id_medicine` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `xip`
--
ALTER TABLE `xip`
  ADD CONSTRAINT `xip_ibfk_1` FOREIGN KEY (`doctor_mail`) REFERENCES `doctor` (`mail`),
  ADD CONSTRAINT `xip_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id`),
  ADD CONSTRAINT `xip_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`mail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
