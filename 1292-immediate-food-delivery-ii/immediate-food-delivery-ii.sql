# Write your MySQL query statement below
WITH FirstOrders AS (
    SELECT 
        customer_id,
        order_date,
        customer_pref_delivery_date,
        LAG(order_date) OVER (PARTITION BY customer_id ORDER BY order_date) as prev_order_date
    FROM Delivery
)
SELECT 
    ROUND(AVG(order_date = customer_pref_delivery_date) * 100, 2) AS immediate_percentage
FROM FirstOrders
WHERE prev_order_date IS NULL;