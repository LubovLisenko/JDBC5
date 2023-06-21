SELECT  'YOUNGEST' AS TYPE, name, birthday
FROM worker
WHERE birthday IN(
 SELECT  max(birthday)
 FROM worker
)

UNION

SELECT  'OLDEST' AS TYPE, name, birthday
FROM worker
WHERE birthday IN(
 SELECT  min(birthday)
 FROM worker
);