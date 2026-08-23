# Write your MySQL query statement below
SELECT p.product_name, SUM(unit) AS unit
FROM Products p
INNER JOIN Orders o
ON p.product_id = o.product_id
WHERE MONTH(order_date)  = 02 AND YEAR(order_date) = 2020
GROUP BY product_name
HAVING SUM(unit) >= 100