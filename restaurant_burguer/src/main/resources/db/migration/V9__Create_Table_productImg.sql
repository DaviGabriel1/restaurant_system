CREATE TABLE IF NOT EXISTS `product_image` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `product_id` bigint DEFAULT NULL,
     `url` varchar(2083) NOT NULL,
    `thumbnail` tinyint(1) NOT NULL,
    `orders` int NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_product_image_product` (`product_id`),
    CONSTRAINT `fk_product_image_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;