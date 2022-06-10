CREATE TABLE role
(
    id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    parent_id     BIGINT(20) NULL,
    name          VARCHAR(100) NOT NULL,
    available     BOOLEAN      NOT NULL,
    display_order INT(3) NOT NULL,
    created_by    BIGINT(20) NOT NULL,
    created       DATETIME(6) NOT NULL,
    updated_by    BIGINT(20) NOT NULL,
    updated       DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
