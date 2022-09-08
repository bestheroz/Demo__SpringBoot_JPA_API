CREATE TABLE role
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id      BIGINT NULL,
    name           VARCHAR(100) NOT NULL,
    available_flag BOOLEAN      NOT NULL,
    display_order  INT          NOT NULL,
    deleted        BOOLEAN      NOT NULL default false,
    created_by     BIGINT       NOT NULL,
    created        DATETIME(6) NOT NULL,
    updated_by     BIGINT       NOT NULL,
    updated        DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
