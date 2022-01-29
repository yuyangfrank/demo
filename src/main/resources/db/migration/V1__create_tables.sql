CREATE table if not exists user_salary
(
    name char(36) primary key,
    salary FLOAT
);

CREATE INDEX IF NOT EXISTS idx_saray on user_salary (salary);