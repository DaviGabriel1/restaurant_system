CREATE TABLE IF NOT EXISTS `address` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `user_id` bigint NOT NULL,
     `cep` varchar(9) NOT NULL,
    `number` varchar(10) NOT NULL,
    `street` varchar(255) NOT NULL,
    `city` varchar(255) NOT NULL,
    `state` varchar(2) DEFAULT 'SP',
    `complement` text,
    `neighborhood` varchar(255) NOT NULL,
    `principal` tinyint(1) DEFAULT '0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_address_user` (`user_id`),
    CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;