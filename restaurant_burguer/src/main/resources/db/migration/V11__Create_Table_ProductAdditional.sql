CREATE TABLE IF NOT EXISTS `product_additional` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `product_id` bigint NOT NULL,
    `name` varchar(255) NOT NULL,
    `price` decimal(10,2) NOT NULL,
    `max_quantity` int NOT NULL,
    `description` text,
    `required` tinyint(1) NOT NULL DEFAULT '0',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_product_additional_product` (`product_id`),
    CONSTRAINT `fk_product_additional_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;