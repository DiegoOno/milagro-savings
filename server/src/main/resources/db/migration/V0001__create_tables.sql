CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create users table
CREATE TABLE IF NOT EXISTS finance_user
(
    id             SERIAL PRIMARY KEY                                  NOT NULL,
    name           VARCHAR(255)                                        NOT NULL,
    email          VARCHAR(255)                                        NOT NULL,
    status         BOOLEAN                                             NOT NULL,
    register_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP        NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_finance_user_email ON finance_user(email);

CREATE TABLE IF NOT EXISTS goal
(
    id                  UUID DEFAULT gen_random_uuid()       PRIMARY KEY,
    name                VARCHAR(255)                            NOT NULL,
    description         VARCHAR(255)                            NOT NULL,
    amount              DECIMAL                                 NOT NULL,
    status              BOOLEAN                                 NOT NULL,
    finance_user_id     BIGINT NOT NULL REFERENCES finance_user (id)
);

CREATE INDEX IF NOT EXISTS idx_goal_finance_user ON goal(finance_user_id);

CREATE TABLE IF NOT EXISTS budget
(
    id                  UUID DEFAULT gen_random_uuid()       PRIMARY KEY,
    name                VARCHAR(255)                            NOT NULL,
    category            VARCHAR(255)                            NOT NULL,
    amount              DECIMAL                                 NOT NULL,
    status              BOOLEAN                                 NOT NULL,
    finance_user_id     BIGINT NOT NULL REFERENCES finance_user (id)
);

CREATE INDEX IF NOT EXISTS idx_budget_finance_user ON budget(finance_user_id);

CREATE TABLE IF NOT EXISTS transactions
(
    id                  UUID DEFAULT gen_random_uuid()       PRIMARY KEY,
    description         VARCHAR(255)                            NOT NULL,
    amount              DECIMAL(10, 2)                          NOT NULL,
    date                DATE                                    NOT NULL,
    status              BOOLEAN                                 NOT NULL,
    finance_user_id     BIGINT NOT NULL REFERENCES finance_user (id),
    goal_id             UUID REFERENCES goal (id)
);

CREATE INDEX IF NOT EXISTS idx_transactions_finance_user ON transactions(finance_user_id);
CREATE INDEX IF NOT EXISTS idx_transactions_goal_id ON transactions(goal_id);
