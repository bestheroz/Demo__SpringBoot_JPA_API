CREATE TABLE code
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    type           VARCHAR(100)          NOT NULL,
    `value`        VARCHAR(100)          NOT NULL,
    text           VARCHAR(1000)         NOT NULL,
    available_flag BOOLEAN DEFAULT FALSE NOT NULL,
    display_order  INT                   NOT NULL,
    created_by     BIGINT                NOT NULL,
    created        DATETIME(6) NOT NULL,
    updated_by     BIGINT                NOT NULL,
    updated        DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
