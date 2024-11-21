CREATE TABLE `Strompriser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `price_zone` char(3) NOT NULL,
  `nok_per_kwh` decimal(6,4) NOT NULL,
  `time_start` datetime NOT NULL,
  `time_end` datetime NOT NULL,
  `timezone` varchar(50) DEFAULT 'UTC',
  PRIMARY KEY (`id`),
  CONSTRAINT `strompriser_chk_1` CHECK ((`price_zone` in (_utf8mb4'NO1',_utf8mb4'NO2',_utf8mb4'NO3',_utf8mb4'NO4',_utf8mb4'NO5')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci