INSERT
INTO `admin`
(LOGIN_ID,
 PASSWORD,
 NAME,
 ROLE_ID,
 SIGN_IN_FAIL_CNT,
 AVAILABLE_FLAG,
 EXPIRED,
 CREATED_BY,
 CREATED,
 UPDATED_BY,
 UPDATED)
VALUES ('1',
        '887728a46a77744868d5089fea52fac809e7dcead39847ecb87d3a60ff53e5a9c3703ec4229e09b9',
        '테스터',
        1,
        0,
        TRUE,
        DATEADD(YEAR, 1, NOW()),
        1,
        NOW(),
        1,
        NOW());
