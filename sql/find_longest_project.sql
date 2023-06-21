SELECT  id, DATEDIFF(MONTH, start_date, finish_date) AS MONTH_COUNT
FROM project
ORDER BY month_count DESC
LIMIT 1;