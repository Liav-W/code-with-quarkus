-- 1. Upgrade id column to BIGINT for Long support
ALTER TABLE tasks
    ALTER COLUMN id SET DATA TYPE BIGINT;

-- 2. Create the sequence expected by Hibernate's default @GeneratedValue
CREATE SEQUENCE IF NOT EXISTS tasks_SEQ START WITH 1 INCREMENT BY 50;

-- 3. Fix column name casing if PostgreSQL converted createdAt to lowercase
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name='tasks' AND column_name='createdat'
    ) THEN
        ALTER TABLE tasks RENAME COLUMN createdat TO "createdAt";
    END IF;
END $$;