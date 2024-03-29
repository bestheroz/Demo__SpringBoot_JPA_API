CREATE TABLE `admin_config`
(
    `id`               BIGINT PRIMARY KEY AUTO_INCREMENT,
    `admin_id`         BIGINT                NOT NULL,
    `global_theme`     VARCHAR(16)           NOT NULL,
    `toolbar_theme`    VARCHAR(16)           NOT NULL,
    `menu_theme`       VARCHAR(16)           NOT NULL,
    `toolbar_detached` BOOLEAN DEFAULT FALSE NOT NULL,
    `content_boxed`    BOOLEAN DEFAULT FALSE NOT NULL,
    `primary_color`    VARCHAR(16)           NOT NULL,
    `created_by`       BIGINT                NOT NULL,
    `created`          DATETIME(6) NOT NULL,
    `updated_by`       BIGINT                NOT NULL,
    `updated`          DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
