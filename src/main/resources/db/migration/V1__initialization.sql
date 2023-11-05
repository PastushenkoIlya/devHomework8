CREATE SCHEMA IF NOT EXISTS `flywaycreated` default character set utf8;
use `flywaycreated`

CREATE TABLE IF NOT EXISTS `flywaycreated`.`buildings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flywaycreated`.`flats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apartment_number` int NOT NULL,
  `floor` tinyint NOT NULL,
  `area` decimal(10,0) DEFAULT NULL,
  `number_of_bedrooms` tinyint DEFAULT NULL,
  `building_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `building_id` (`building_id`),
  CONSTRAINT `flats_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `flywaycreated`.`buildings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flywaycreated`.`inhabitants_to_flats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resident_id` int DEFAULT NULL,
  `flat_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `resident_id` (`resident_id`),
  KEY `flat_id` (`flat_id`),
  CONSTRAINT `inhabitants_to_flats_ibfk_1` FOREIGN KEY (`resident_id`) REFERENCES `flywaycreated`.`residents` (`id`),
  CONSTRAINT `inhabitants_to_flats_ibfk_2` FOREIGN KEY (`flat_id`) REFERENCES `flywaycreated`.`flats` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flywaycreated`.`members_osbb` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` enum('member','worker','board member','chairman') DEFAULT NULL,
  `person_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `members_osbb_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `flywaycreated`.`residents` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flywaycreated`.`owners_to_flats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int DEFAULT NULL,
  `flat_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `flat_id` (`flat_id`),
  KEY `idx_owners_to_flats_owner_id_flat_id` (`owner_id`,`flat_id`),
  CONSTRAINT `owners_to_flats_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `flywaycreated`.`members_osbb` (`id`),
  CONSTRAINT `owners_to_flats_ibfk_2` FOREIGN KEY (`flat_id`) REFERENCES `flywaycreated`.`flats` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flywaycreated`.`residents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `vehicle_parking_access` binary(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
