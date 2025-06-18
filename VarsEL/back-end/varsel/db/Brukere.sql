CREATE TABLE `Brukere` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fornavn` varchar(50) NOT NULL,
  `etternavn` varchar(50) NOT NULL,
  `epost` varchar(100) NOT NULL,
  `posisjon` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci