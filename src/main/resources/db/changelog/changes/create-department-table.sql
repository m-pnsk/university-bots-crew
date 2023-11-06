CREATE TABLE IF NOT EXISTS `departments`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `name`                  varchar(255) DEFAULT NULL,
    `head_of_department_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `head_of_department_id` (`head_of_department_id`),
    CONSTRAINT `head_of_department_id` FOREIGN KEY (`head_of_department_id`) REFERENCES `lectors` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
