INSERT
INTO member
(USER_ID,
 NAME,
 AUTHORITY_ID,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer',
        '개발자',
        1,
        0,
        TRUE,
        DATEADD(YEAR, 1, NOW()),
        'developer',
        NOW(),
        'developer',
        NOW());

INSERT
INTO member
(USER_ID,
 PASSWORD,
 NAME,
 AUTHORITY_ID,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('1',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '개발자1',
        1,
        0,
        TRUE,
        DATEADD(YEAR, 1, NOW()),
        'developer',
        NOW(),
        'developer',
        NOW());

INSERT
INTO member
(USER_ID,
 PASSWORD,
 NAME,
 AUTHORITY_ID,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('admin',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '관리자1',
        2,
        0,
        TRUE,
        DATEADD(YEAR, 1, NOW()),
        'developer',
        NOW(),
        'developer',
        NOW());
