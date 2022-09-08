CREATE TABLE admin
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    login_id         VARCHAR(100) NOT NULL,
    password         CHAR(80) NULL,
    name             VARCHAR(100) NOT NULL,
    role_id          BIGINT       NOT NULL,
    sign_in_fail_cnt INT                   DEFAULT 0 NOT NULL,
    available_flag   BOOLEAN      NOT NULL,
    token            VARCHAR(4000) NULL,
    expired          DATETIME(6) NOT NULL,
    deleted          BOOLEAN      NOT NULL default false,
    created_by       BIGINT       NOT NULL,
    created          DATETIME(6) NOT NULL,
    updated_by       BIGINT       NOT NULL,
    updated          DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
