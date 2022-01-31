INSERT INTO menu (id, name, type, parent_id, display_order, icon, url, created_by, created,
                  updated_by, updated)
VALUES (1, '개발자', 'GROUP', null, 5, 'mdi-developer-board', null, 1, NOW(), 1, NOW()),
       (2, 'Picker', 'PAGE', 1, 1, null, '/developer/picker', 1, NOW(), 1, NOW()),
       (3, '파일 업로드', 'PAGE', 1, 2, null, '/developer/file-uploader', 1, NOW(), 1, NOW()),
       (4, '텍스트 에디터', 'PAGE', 1, 3, null, '/developer/text-editor', 1, NOW(), 1, NOW()),

       (5, '관리자 및 역할 관리', 'GROUP', null, 3, 'mdi-account-lock-open', null, 1, NOW(), 1, NOW()),
       (6, '관리자', 'PAGE', 5, 1, null, '/management/admin', 1, NOW(), 1, NOW()),
       (7, '역할', 'PAGE', 5, 2, null, '/management/role', 1, NOW(), 1, NOW()),
       (8, '역할별 메뉴', 'PAGE', 5, 3, null, '/management/role-menu', 1, NOW(), 1, NOW()),

       (9, '시스템 관리', 'GROUP', null, 4, 'mdi-cog', null, 1, NOW(), 1, NOW()),
       (10, '코드', 'PAGE', 9, 1, null, '/management/code', 1, NOW(), 1, NOW()),
       (11, '메뉴', 'PAGE', 9, 2, null, '/management/menu', 1, NOW(), 1, NOW()),

       (12, '게시 관리', 'GROUP', null, 1, 'mdi-clipboard-edit', null, 1, NOW(), 1, NOW()),
       (13, '공지사항', 'PAGE', 12, 1, null, '/management/notice', 1, NOW(), 1, NOW()),
       (14, 'FAQ', 'PAGE', 12, 2, null, '/management/faq', 1, NOW(), 1, NOW()),
       (15, '팝업', 'PAGE', 12, 3, null, '/management/popup', 1, NOW(), 1, NOW()),
       (16, '약관', 'PAGE', 12, 4, null, '/management/terms', 1, NOW(), 1, NOW()),

       (17, '앱 관리', 'GROUP', null, 2, 'mdi-apps', null, 1, NOW(), 1, NOW()),
       (18, '앱', 'PAGE', 17, 1, null, '/management/app', 1, NOW(), 1, NOW()),
       (19, '앱 버전', 'PAGE', 17, 2, null, '/management/app-version', 1, NOW(), 1, NOW());
