# Write your MySQL query statement below
/*SELECT 
    CASE
        WHEN id % 2 = 1 AND id = (SELECT MAX(id) FROM Seat) THEN id
        WHEN id % 2 = 1 THEN id + 1
        WHEN id % 2 = 0 THEN id - 1
    END AS id, student
FROM Seat
ORDER BY id;*/
SELECT id,
    CASE
        WHEN MOD(id,2)=1 
            THEN LEAD(student,1,student) OVER(ORDER BY id)
        ELSE
            LAG(student) OVER(ORDER BY id)
    END AS student
FROM Seat
ORDER BY id;
