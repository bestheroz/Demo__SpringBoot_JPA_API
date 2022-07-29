INSERT INTO menu (id, name, type, parent_id, display_order, icon, url, created_by, created,
                  updated_by, updated)
VALUES (1, '개발자', 'GROUP', null, 3, 'mdi-developer-board', null, 1, NOW(), 1, NOW()),
       (2, 'Picker', 'PAGE', 1, 1, null, '/developer/picker', 1, NOW(), 1, NOW()),

       (3, '관리자 및 역할 관리', 'GROUP', null, 1, 'mdi-account-lock-open', null, 1, NOW(), 1, NOW()),
       (4, '관리자', 'PAGE', 3, 1, null, '/management/admin', 1, NOW(), 1, NOW()),
       (5, '역할', 'PAGE', 3, 2, null, '/management/role', 1, NOW(), 1, NOW()),
       (6, '역할별 메뉴', 'PAGE', 3, 3, null, '/management/role-menu', 1, NOW(), 1, NOW()),

       (7, '시스템 관리', 'GROUP', null, 2, 'mdi-cog', null, 1, NOW(), 1, NOW()),
       (8, '코드', 'PAGE', 7, 1, null, '/management/code', 1, NOW(), 1, NOW()),
       (9, '메뉴', 'PAGE', 7, 2, null, '/management/menu', 1, NOW(), 1, NOW());
