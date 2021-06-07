CREATE TABLE `member_config`
(
    `id`               BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `member_id`        BIGINT(20)            NOT NULL,
    `global_theme`     VARCHAR(16)           NOT NULL,
    `toolbar_theme`    VARCHAR(16)           NOT NULL,
    `menu_theme`       VARCHAR(16)           NOT NULL,
    `toolbar_detached` BOOLEAN DEFAULT FALSE NOT NULL,
    `content_boxed`    BOOLEAN DEFAULT FALSE NOT NULL,
    `primary_color`    VARCHAR(16)           NOT NULL,
    `created_by`       VARCHAR(100)          NOT NULL,
    `created`          DATETIME              NOT NULL,
    `updated_by`       VARCHAR(100)          NOT NULL,
    `updated`          DATETIME              NOT NULL
);
