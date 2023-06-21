SELECT project.id, SUM(salary)*DATEDIFF(MONTH, start_date, finish_date) AS price
  FROM project
  JOIN project_worker ON project.id=project_worker.project_id
  JOIN worker ON project_worker.worker_id=worker.id
  GROUP BY project.id
  ORDER BY price DESC;