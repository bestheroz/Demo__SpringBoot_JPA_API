INSERT
INTO member
(USER_ID,
 PASSWORD,
 NAME,
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '개발자',
        'SUPER',
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
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer1',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '개발자1',
        'SUPER',
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
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer2',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '개발자2',
        'SUPER',
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
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer3',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '개발자3',
        'SUPER',
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
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('1',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '임시계정',
        'SUPER',
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
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('writer',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '쓰기권한계정',
        'WRITER',
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
 AUTHORITY,
 LOGIN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('viewer',
        '�ޤ�o%PO�8�.�9�+�Xڹ1����n�ח',
        '보기권한계정',
        'VIEWER',
        0,
        TRUE,
        DATEADD(YEAR, 1, NOW()),
        'developer',
        NOW(),
        'developer',
        NOW());
