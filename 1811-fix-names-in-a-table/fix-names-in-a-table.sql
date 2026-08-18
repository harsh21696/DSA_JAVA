# Write your MySQL query statement below
SELECT user_id, INSERT(LOWER(name), 1, 1, UPPER(LEFT(name, 1))) AS name
FROM Users
ORDER BY user_id;