ALTER TABLE tasks
    ADD COLUMN priority VARCHAR(10) NOT NULL DEFAULT 'low'
    CONSTRAINT chk_tasks_priority CHECK (priority IN ('low', 'medium', 'high'));

ALTER TABLE tasks
    ADD COLUMN due_date TIMESTAMP WITHOUT TIME ZONE;