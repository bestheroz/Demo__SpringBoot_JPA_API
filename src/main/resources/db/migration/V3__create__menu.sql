CREATE TABLE menu
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(1000) NOT NULL,
    type          VARCHAR(10)   NOT NULL,
    parent_id     BIGINT,
    display_order INT           NOT NULL,
    icon          VARCHAR(50),
    url           VARCHAR(4000),
    created_by    BIGINT        NOT NULL,
    created       DATETIME(6) NOT NULL,
    updated_by    BIGINT        NOT NULL,
    updated       DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
