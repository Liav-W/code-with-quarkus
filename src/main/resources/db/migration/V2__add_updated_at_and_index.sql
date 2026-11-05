ALTER TABLE tasks
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE,

CREATE INDEX idx_completed
ON tasks (completed),