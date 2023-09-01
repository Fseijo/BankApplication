CREATE TABLE IF NOT EXISTS loans(
loan_number INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
customer_id INT,
start_dt DATE,
loan_type VARCHAR(100),
total_loan INT,
amount_paid INT,
outstanding_amount INT,
created_dt DATE
);

INSERT INTO loans(customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, created_dt)
VALUES
(1, '2005-09-01', 'Mortgage', 1000000, 800000, 200000,'2005-09-01'),
(1, '2023-06-01', 'Car finance', 150000, 15000, 135000,'2023-06-01'),
(1, '2018-05-30', 'Student', 250000, 80000, 170000,'2018-05-30'),
(2, '2015-11-01', 'Car finance', 45000, 40000, 5000,'2015-11-01'),
(3, '2010-10-01', 'Mortgage', 350000, 160000, 140000,'2010-10-01');