# Write your MySQL query statement below
SELECT u.unique_id , n.name
FROM Employees n
LEFT JOIN EmployeeUNI u
ON n.id = u.id;