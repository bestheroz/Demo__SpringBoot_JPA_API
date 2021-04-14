INSERT
INTO authority
(CODE,
 NAME,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('SUPER',
        '모든 권한',
        'developer',
        NOW(),
        'developer',
        NOW());

INSERT
INTO authority
(CODE,
 NAME,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('ADMIN',
        '관리자 권한',
        'developer',
        NOW(),
        'developer',
        NOW());


INSERT
INTO authority
(CODE,
 NAME,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('WRITER',
        '쓰기 권한',
        'developer',
        NOW(),
        'developer',
        NOW());

INSERT
INTO authority
(CODE,
 NAME,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('VIEWER',
        '보기 권한',
        'developer',
        NOW(),
        'developer',
        NOW());
