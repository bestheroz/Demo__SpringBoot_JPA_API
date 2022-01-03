INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (1, null, 2, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (2, 1, 2, 2, 1, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (3, 1, 2, 3, 2, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (4, 1, 2, 4, 3, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (5, 1, 2, 5, 4, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (6, 1, 2, 6, 5, '["VIEW","WRITE","DELETE","EXCEL"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (7, null, 3, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (8, 7, 3, 2, 1, '["VIEW","WRITE"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (9, 7, 3, 3, 2, '["VIEW","WRITE"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (10, 7, 3, 4, 3, '["VIEW","WRITE"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (11, 7, 3, 5, 4, '["VIEW","WRITE"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (12, 7, 3, 6, 5, '["VIEW","WRITE"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (13, null, 4, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (14, 13, 4, 2, 1, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (15, 13, 4, 3, 2, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json,
                           created_by, created, updated_by, updated)
VALUES (16, 13, 4, 4, 3, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json, created_by, created, updated_by, updated)
VALUES (17, 13, 4, 5, 4, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json, created_by, created, updated_by, updated)
VALUES (18, 13, 4, 6, 5, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json, created_by, created, updated_by, updated)
VALUES (19, null, 5, 1, 1, '["VIEW"]', 1, NOW(), 1, NOW());
INSERT INTO role_menu_map (id, parent_id, role_id, menu_id, display_order, authorities_json, created_by, created, updated_by, updated)
VALUES (20, 19, 5, 3, 1, '["VIEW","EXCEL"]', 1, NOW(), 1, NOW());
