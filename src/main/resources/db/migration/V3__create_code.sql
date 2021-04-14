CREATE TABLE code
(
    ID            BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    TYPE         VARCHAR(100)          NOT NULL,
    VALUE         VARCHAR(100)          NOT NULL,
    NAME          VARCHAR(1000)         NOT NULL,
    AVAILABLE     BOOLEAN DEFAULT FALSE NOT NULL,
    DISPLAY_ORDER INT(10)               NOT NULL,
    AUTHORITY_ID     INT(3)  DEFAULT 999   NOT NULL,
    CREATED_BY    VARCHAR(100)          NOT NULL,
    CREATED       DATETIME              NOT NULL,
    UPDATED_BY    VARCHAR(100)          NOT NULL,
    UPDATED       DATETIME              NOT NULL
);
