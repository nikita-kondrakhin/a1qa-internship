SELECT test.name AS 'Test name',
       method_name AS 'Test method',
       status.name AS 'Latest test result',
       start_time AS 'Latest test start time',
       end_time AS 'Latest test end time',
       SEC_TO_TIME(TIMESTAMPDIFF(SECOND, start_time, end_time)) AS 'Latest test duration (H:m:s.S)'
FROM union_reporting.test
JOIN status ON test.status_id = status.id
JOIN project ON test.project_id = project.id
WHERE project.name = 'Nexage'
ORDER BY test.start_time DESC;