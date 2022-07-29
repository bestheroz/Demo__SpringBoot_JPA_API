INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by,
                           updated)
VALUES (9, null, 2, 3, 3, '["VIEW"]', 1, NOW(), 1, NOW()),
       (10, 9, 2, 4, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (11, 9, 2, 5, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (12, 9, 2, 6, 3, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (13, null, 7, 9, 4, '["VIEW"]', 1, NOW(), 1, NOW()),
       (14, 13, 2, 8, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (15, 13, 2, 9, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),


       (24, null, 3, 3, 3, '["VIEW"]', 1, NOW(), 1, NOW()),
       (25, 24, 3, 4, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (26, 24, 3, 5, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (27, 24, 3, 6, 3, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (28, null, 3, 7, 4, '["VIEW"]', 1, NOW(), 1, NOW()),
       (29, 28, 3, 8, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (30, 28, 3, 9, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),


       (39, null, 4, 3, 3, '["VIEW"]', 1, NOW(), 1, NOW()),
       (40, 39, 4, 4, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (41, 39, 4, 5, 2, '["VIEW"]', 1, NOW(), 1, NOW()),
       (42, 39, 4, 6, 3, '["VIEW"]', 1, NOW(), 1, NOW()),
       (43, null, 4, 7, 4, '["VIEW"]', 1, NOW(), 1, NOW()),
       (44, 43, 4, 8, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (45, 43, 4, 9, 2, '["VIEW"]', 1, NOW(), 1, NOW()),


       (54, null, 5, 3, 3, '["VIEW"]', 1, NOW(), 1, NOW()),
       (55, 54, 5, 4, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (56, 54, 5, 5, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (57, 54, 5, 6, 3, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (58, null, 5, 7, 4, '["VIEW"]', 1, NOW(), 1, NOW()),
       (59, 58, 5, 8, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (60, 58, 5, 9, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW());
