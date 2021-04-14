
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
VALUES ('만든이',
        'G',
        0,
        6,
        'mdi-account-hard-hat',
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
SELECT 'Github',
       'W',
       ID,
       7,
       'https://github.com/bestheroz/',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '만든이';


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
SELECT 'Blog',
       'W',
       ID,
       8,
       'https://bestheroz.blog.me',
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '만든이';


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
 ICON,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('메뉴그룹1',
        'G',
        0,
        50,
        'mdi-numeric-1-box-outline',
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
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '메뉴1',
       'P',
       ID,
       100,
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '메뉴그룹1';


INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '메뉴2',
       'P',
       ID,
       200,
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '메뉴그룹1';


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
VALUES ('메뉴그룹2',
        'G',
        0,
        70,
        'mdi-numeric-2-box-outline',
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
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '메뉴1',
       'P',
       ID,
       100,
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '메뉴그룹2';


INSERT
INTO menu
(NAME,
 TYPE,
 PARENT_ID,
 DISPLAY_ORDER,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
SELECT '메뉴2',
       'P',
       ID,
       200,
       'developer',
       NOW(),
       'developer',
       NOW()
FROM menu
WHERE NAME = '메뉴그룹2';
