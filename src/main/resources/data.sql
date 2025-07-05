-- ==========================
-- Step 1: Insert Departments (without department_head_id for now)
-- ==========================

INSERT IGNORE INTO department (id, name, created_date)
VALUES 
(1, 'Engineering', '2023-01-01'),
(2, 'Human Resources', '2023-02-01'),
(3, 'Marketing', '2023-03-01'),
(4, 'Finance', '2023-04-01'),
(5, 'Operations', '2023-05-01');

-- ==========================
-- Step 2: Insert Employees (including heads and reporting managers)
-- ==========================

-- CEO (no manager)
INSERT IGNORE INTO employee (id, name, date_of_birth, salary, department_id, address, role, joining_date, yearly_bonus_percentage, reporting_manager_id)
VALUES (1, 'Alice Johnson', '1980-01-01', 150000, 1, '123 Main St', 'CEO', '2020-01-01', 20.0, NULL);

-- Department Heads
INSERT IGNORE INTO employee (
    id, name, date_of_birth, salary, department_id, address, role, joining_date, yearly_bonus_percentage, reporting_manager_id
) VALUES
(2, 'Bob Smith',     '1982-02-02', 120000, 2, '456 Park Ave',     'HR Head',        '2020-02-01', 15.0, 1),
(3, 'Carol White',   '1983-03-03', 125000, 3, '789 Elm St',       'Marketing Head', '2020-03-01', 15.0, 1),
(4, 'David Black',   '1984-04-04', 130000, 4, '321 Oak Dr',       'Finance Head',   '2020-04-01', 15.0, 1),
(5, 'Eve Green',     '1985-05-05', 128000, 5, '654 Pine Rd',      'Ops Head',       '2020-05-01', 15.0, 1);

-- Engineering Team
INSERT IGNORE INTO employee (
    id, name, date_of_birth, salary, department_id, address, role, joining_date, yearly_bonus_percentage, reporting_manager_id
)  VALUES
(6,  'Frank Miller', '1990-01-10', 80000,  1, 'Eng St 1', 'Developer',       '2021-01-15', 10.0, 1),
(7,  'Grace Lee',    '1991-02-12', 82000,  1, 'Eng St 2', 'Developer',       '2021-03-10', 10.0, 6),
(8,  'Henry King',   '1992-03-14', 78000,  1, 'Eng St 3', 'QA Engineer',     '2021-05-10',  8.0, 6),
(9,  'Ivy Chen',     '1993-04-16', 75000,  1, 'Eng St 4', 'Backend Dev',     '2022-02-01',  9.0, 6),
(10, 'Jack Wong',    '1994-05-18', 77000,  1, 'Eng St 5', 'Frontend Dev',    '2022-06-20',  9.0, 6);

-- HR Team
INSERT IGNORE INTO employee (
    id, name, date_of_birth, salary, department_id, address, role, joining_date, yearly_bonus_percentage, reporting_manager_id
)  VALUES
(11, 'Kathy Brown',  '1989-06-20', 65000,  2, 'HR St 1',  'Recruiter',       '2021-02-10',  7.0, 2),
(12, 'Leo Patel',    '1990-07-22', 67000,  2, 'HR St 2',  'Recruiter',       '2021-04-15',  7.0, 2),
(13, 'Mona Das',     '1991-08-24', 69000,  2, 'HR St 3',  'Coordinator',     '2021-06-20',  6.0, 11),
(14, 'Neha Shah',    '1992-09-26', 70000,  2, 'HR St 4',  'Admin',           '2022-03-15',  6.5, 11),
(15, 'Omar Khan',    '1993-10-28', 72000,  2, 'HR St 5',  'HR Assistant',    '2022-05-05',  6.5, 11);

-- Marketing Team
INSERT IGNORE INTO employee (
    id, name, date_of_birth, salary, department_id, address, role, joining_date, yearly_bonus_percentage, reporting_manager_id
)  VALUES
(16, 'Peter Quinn',  '1988-11-30', 72000,  3, 'Mkt St 1', 'SEO Specialist',  '2021-07-01',  8.0, 3),
(17, 'Quinn Ray',    '1989-12-01', 71000,  3, 'Mkt St 2', 'Content Writer',  '2021-08-15',  7.5, 3),
(18, 'Rita Lin',     '1990-01-03', 73000,  3, 'Mkt St 3', 'Social Manager',  '2022-01-01',  7.5, 16),
(19, 'Steve Jobs',   '1991-02-04', 74000,  3, 'Mkt St 4', 'Product Analyst', '2022-04-10',  7.0, 16),
(20, 'Tina Roy',     '1992-03-06', 76000,  3, 'Mkt St 5', 'Brand Manager',   '2022-06-15',  7.0, 16);

-- Finance & Ops
INSERT IGNORE INTO employee (
    id, name, date_of_birth, salary, department_id, address, role, joining_date, yearly_bonus_percentage, reporting_manager_id
)  VALUES
(21, 'Uma Singh',    '1988-04-08', 68000,  4, 'Fin St 1', 'Accountant',      '2021-09-01',  7.0, 4),
(22, 'Victor Rana',  '1989-05-10', 69000,  4, 'Fin St 2', 'Auditor',         '2021-10-10',  6.5, 4),
(23, 'Wendy George', '1990-06-12', 70000,  5, 'Ops St 1', 'Ops Analyst',     '2021-11-11',  6.5, 5),
(24, 'Xander Joe',   '1991-07-14', 72000,  5, 'Ops St 2', 'Process Lead',    '2021-12-12',  6.0, 5),
(25, 'Yara Khan',    '1992-08-16', 74000,  5, 'Ops St 3', 'Quality Officer', '2022-01-10',  6.0, 5);

-- ==========================
-- Step 3: Update department_head_id AFTER employee insert
-- ==========================

UPDATE department SET department_head_id = 1 WHERE id = 1;
UPDATE department SET department_head_id = 2 WHERE id = 2;
UPDATE department SET department_head_id = 3 WHERE id = 3;
UPDATE department SET department_head_id = 4 WHERE id = 4;
UPDATE department SET department_head_id = 5 WHERE id = 5;
