INSERT INTO tasks (
    id,
    title,
    description,
    completed,
    priority,
    due_date,
    "createdAt"
)
SELECT

    nextval('tasks_SEQ'),

    'Sample Task #' || i AS title,
    'Auto-generated seed task for testing #' || i AS description,

    (random() < 0.3) AS completed,


    (ARRAY['low', 'medium', 'high'])[floor(random() * 3 + 1)] AS priority,

    NOW() + (random() * INTERVAL '14 days') AS due_date,

    NOW() AS "createdAt"

FROM generate_series(1, 10) AS s(i);