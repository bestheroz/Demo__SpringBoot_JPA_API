INSERT INTO `role` (id, parent_id, name, available_flag, display_order, created_by, created,
                    updated_by, updated)
VALUES (1, null, '마스터', 1, 1, 1, NOW(), 1, NOW()),
       (2, null, '서울 관리자', 1, 2, 1, NOW(), 1, NOW()),
       (3, 2, '을지로 관리자', 1, 1, 1, NOW(), 1, NOW()),
       (4, 2, '잠실 관리자', 1, 2, 1, NOW(), 1, NOW()),
       (5, 2, '서초 관리자', 1, 3, 1, NOW(), 1, NOW()),
       (6, null, '제주 관리자', 1, 2, 1, NOW(), 1, NOW()),
       (7, 6, '중문 관리자', 1, 1, 1, NOW(), 1, NOW())
;

