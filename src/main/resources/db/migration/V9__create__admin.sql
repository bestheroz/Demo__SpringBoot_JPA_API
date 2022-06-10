CREATE TABLE admin
(
    id               BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    login_id         VARCHAR(100) NOT NULL,
    password         CHAR(80) NULL,
    name             VARCHAR(100) NOT NULL,
    role_id          BIGINT(20) NOT NULL,
    sign_in_fail_cnt INT(1) DEFAULT 0 NOT NULL,
    available        BOOLEAN      NOT NULL,
    token            VARCHAR(4000) NULL,
    expired          DATETIME(6) NOT NULL,
    created_by       BIGINT(20) NOT NULL,
    created          DATETIME(6) NOT NULL,
    updated_by       BIGINT(20) NOT NULL,
    updated          DATETIME(6) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
