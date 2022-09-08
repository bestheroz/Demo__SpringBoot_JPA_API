INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (1, null, 2, 12, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (2, 1, 2, 13, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (3, 1, 2, 14, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (4, 1, 2, 15, 3, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (5, 1, 2, 16, 4, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (6, 1, 2, 17, 5, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),

       (7, null, 2, 18, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (8, 7, 2, 19, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (9, 7, 2, 20, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (10, 7, 2, 21, 3, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),

       (11, null, 2, 5, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (12, 11, 2, 6, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (13, 11, 2, 7, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (14, 11, 2, 8, 3, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),

       (15, null, 2, 9, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (16, 15, 2, 10, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (17, 15, 2, 11, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),

       (18, null, 2, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (19, 18, 2, 2, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (20, 18, 2, 3, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW()),
       (21, 18, 2, 4, 3, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW());


INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (22, null, 3, 12, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (23, 22, 3, 13, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (24, 22, 3, 14, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (25, 22, 3, 15, 3, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (26, 22, 3, 16, 4, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (27, 22, 3, 17, 5, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),

       (28, null, 3, 18, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (29, 28, 3, 19, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (30, 28, 3, 20, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (31, 28, 3, 21, 3, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),

       (32, null, 3, 5, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (33, 32, 3, 6, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (34, 32, 3, 7, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (35, 32, 3, 8, 3, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),

       (36, null, 3, 9, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (37, 36, 3, 10, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (38, 36, 3, 11, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),

       (39, null, 3, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (40, 39, 3, 2, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (41, 39, 3, 3, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW()),
       (42, 39, 3, 4, 3, '["VIEW","WRITE"]', 1, NOW(), 1, NOW());


INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (43, null, 4, 12, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (44, 43, 4, 13, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (45, 43, 4, 14, 2, '["VIEW"]', 1, NOW(), 1, NOW()),
       (46, 43, 4, 15, 3, '["VIEW"]', 1, NOW(), 1, NOW()),
       (47, 43, 4, 16, 4, '["VIEW"]', 1, NOW(), 1, NOW()),
       (48, 43, 4, 17, 5, '["VIEW"]', 1, NOW(), 1, NOW()),

       (49, null, 4, 18, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (50, 49, 4, 19, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (51, 49, 4, 20, 2, '["VIEW"]', 1, NOW(), 1, NOW()),
       (52, 49, 4, 21, 3, '["VIEW"]', 1, NOW(), 1, NOW()),

       (53, null, 4, 5, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (54, 53, 4, 6, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (55, 53, 4, 7, 2, '["VIEW"]', 1, NOW(), 1, NOW()),
       (56, 53, 4, 8, 3, '["VIEW"]', 1, NOW(), 1, NOW()),

       (57, null, 4, 9, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (58, 57, 4, 10, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (59, 57, 4, 11, 2, '["VIEW"]', 1, NOW(), 1, NOW()),

       (60, null, 4, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (61, 60, 4, 2, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (62, 60, 4, 3, 2, '["VIEW"]', 1, NOW(), 1, NOW()),
       (63, 60, 4, 4, 3, '["VIEW"]', 1, NOW(), 1, NOW());


INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (64, null, 5, 12, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (65, 64, 5, 13, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (66, 64, 5, 14, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (67, 64, 5, 15, 3, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (68, 64, 5, 16, 4, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (69, 64, 5, 17, 5, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),

       (70, null, 5, 18, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (71, 70, 5, 19, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (72, 70, 5, 20, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (73, 70, 5, 21, 3, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),

       (74, null, 5, 5, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (75, 74, 5, 6, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (76, 74, 5, 7, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (77, 74, 5, 8, 3, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),

       (78, null, 5, 9, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (79, 78, 5, 10, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (80, 78, 5, 11, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),

       (81, null, 5, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW()),
       (82, 81, 5, 2, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (83, 81, 5, 3, 2, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW()),
       (84, 81, 5, 4, 3, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW());
