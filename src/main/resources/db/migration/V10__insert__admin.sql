INSERT
INTO `admin`
(LOGIN_ID,
 PASSWORD,
 NAME,
 ROLE_ID,
 SIGN_IN_FAIL_CNT,
 AVAILABLE,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('developer', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '개발자', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('developer1', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '개발자1', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('developer2', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '개발자2', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('developer3', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '개발자3', 1, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('1', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '임시계정', 1, 0, TRUE, DATEADD(YEAR, 1, NOW()),
        1, NOW(), 1, NOW()),
       ('admin', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '관리자계정', 2, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('writer', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '쓰기권한계정', 3, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('viewer', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '보기권한계정', 4, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW()),
       ('excel', '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9', '엑셀권한계정', 5, 0, TRUE,
        DATEADD(YEAR, 1, NOW()), 1, NOW(), 1, NOW());
