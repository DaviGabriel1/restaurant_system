CREATE TABLE IF NOT EXISTS `order_item_additional` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `order_item_id` bigint NOT NULL,
    `additional_id` bigint NOT NULL,
    `quantity` int NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_order_item_additional_order_item` (`order_item_id`),
    KEY `fk_order_item_additional_product_additional` (`additional_id`),
    CONSTRAINT `fk_order_item_additional_order_item` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_order_item_additional_product_additional` FOREIGN KEY (`additional_id`) REFERENCES `product_additional` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;