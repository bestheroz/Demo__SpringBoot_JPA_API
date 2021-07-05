INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 ICON,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('관리자',
        'G',
        0,
        1,
        'mdi-account-cog',
        'developer',
        NOW(),
        'developer',
        NOW());

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '메뉴관리',
       'P',
       ID,
       2,
       '/admin/menu',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '관리자';


INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '권한관리',
       'P',
       ID,
       3,
       '/admin/authority',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '관리자';

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '코드관리',
       'P',
       ID,
       4,
       '/admin/code',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '관리자';

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '회원관리',
       'P',
       ID,
       5,
       '/admin/member',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '관리자';

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 ICON,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('개발자',
        'G',
        0,
        9,
        'mdi-developer-board',
        'developer',
        NOW(),
        'developer',
        NOW());

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT 'Picker',
       'P',
       ID,
       10,
       '/developer/picker',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '개발자';

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '파일 업로드',
       'P',
       ID,
       11,
       '/developer/file-uploader',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '개발자';

INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 URL,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '텍스트 에디터',
       'P',
       ID,
       12,
       '/developer/text-editor',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '개발자';

