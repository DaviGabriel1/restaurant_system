CREATE TABLE IF NOT EXISTS `phone` (
     `id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint DEFAULT NULL,
    `ddi` varchar(5) DEFAULT '+55',
    `ddd` varchar(5) NOT NULL,
    `phone` varchar(9) NOT NULL,
    `principal` tinyint(1) DEFAULT '0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    `active` tinyint(1) DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY `fk_phone_user` (`user_id`),
    CONSTRAINT `fk_phone_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;