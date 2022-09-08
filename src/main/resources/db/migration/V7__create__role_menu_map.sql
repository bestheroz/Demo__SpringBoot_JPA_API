CREATE TABLE role_menu_map
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id        BIGINT NULL,
    role_id          BIGINT       NOT NULL,
    menu_id          BIGINT       NOT NULL,
    display_order    INT          NOT NULL,
    authorities_json VARCHAR(100) NOT NULL,
    created_by       BIGINT       NOT NULL,
    created          DATETIME(6) NOT NULL,
    updated_by       BIGINT       NOT NULL,
    updated          DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
