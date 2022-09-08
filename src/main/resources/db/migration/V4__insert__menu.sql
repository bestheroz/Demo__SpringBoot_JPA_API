INSERT INTO menu (id, name, type, parent_id, display_order, icon, url, created_by, created,
                  updated_by, updated)
VALUES (1, '관리자 및 역할 관리', 'GROUP', null, 1, 'mdi-account-lock-open', null, 1, NOW(), 1, NOW()),
       (2, '관리자', 'PAGE', 1, 1, null, '/management/admin', 1, NOW(), 1, NOW()),
       (3, '역할', 'PAGE', 1, 2, null, '/management/role', 1, NOW(), 1, NOW()),
       (4, '역할별 메뉴', 'PAGE', 1, 3, null, '/management/role-menu', 1, NOW(), 1, NOW()),

       (5, '시스템 관리', 'GROUP', null, 2, 'mdi-cog', null, 1, NOW(), 1, NOW()),
       (6, '코드', 'PAGE', 5, 1, null, '/management/code', 1, NOW(), 1, NOW()),
       (7, '메뉴', 'PAGE', 5, 2, null, '/management/menu', 1, NOW(), 1, NOW());
