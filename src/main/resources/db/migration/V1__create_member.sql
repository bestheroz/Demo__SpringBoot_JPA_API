CREATE TABLE `member`
(
    `id`             BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `user_id`        VARCHAR(100)     NOT NULL,
    `password`       CHAR(128)        NULL,
    `name`           VARCHAR(100)     NOT NULL,
    `authority_id`   BIGINT(20)           NOT NULL,
    `login_fail_cnt` INT(1) DEFAULT 0 NOT NULL,
    `available`      BOOLEAN          NOT NULL,
    `theme`          VARCHAR(10)      NULL,
    `token`          VARCHAR(4000)    NULL,
    `expired`        DATETIME         NOT NULL,
    `created_by`     VARCHAR(100)     NOT NULL,
    `created`        DATETIME         NOT NULL,
    `updated_by`     VARCHAR(100)     NOT NULL,
    `updated`        DATETIME         NOT NULL
);
