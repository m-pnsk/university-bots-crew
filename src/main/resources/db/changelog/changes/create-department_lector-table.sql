CREATE TABLE IF NOT EXISTS `department_lector`
(
    `salary`        decimal(19, 2) DEFAULT NULL,
    `department_id` bigint NOT NULL,
    `lector_id`     bigint NOT NULL,
    PRIMARY KEY (`department_id`, `lector_id`),
    KEY `lector_id` (`lector_id`),
    CONSTRAINT `department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
    CONSTRAINT `lector_id` FOREIGN KEY (`lector_id`) REFERENCES `lectors` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
