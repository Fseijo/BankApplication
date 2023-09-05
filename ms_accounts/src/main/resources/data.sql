CREATE TABLE IF NOT EXISTS customer(
customer_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
mobile_number VARCHAR(10),
created_dt DATE
);
CREATE TABLE IF NOT EXISTS accounts(
customer_id INT,
account_number BIGINT AUTO_INCREMENT,
account_type VARCHAR(100),
branch_address VARCHAR(100),
created_dt DATE,
PRIMARY KEY(account_number),
FOREIGN KEY(customer_id) REFERENCES Customer (customer_id)
);

INSERT INTO Customer (name, email, mobile_number, created_dt)
VALUES
('Rébecca Armand', 'rarmand@exemple.com', '0689545566', CURDATE()),
('Aimée Hebert', 'ahebert@exemple.com', '0689544578', '2023-08-19'),
('Marielle Ribeiro', 'marielleribeiro@exemple.com', '0689540219', CURDATE());


INSERT INTO Accounts (customer_id, account_type, branch_address, created_dt)
VALUES
(1, 'Credit', 'Saint-Didier-des-Bois', CURDATE()),
(1, 'Credit', 'Saint-Didier-des-Bois', '2015-09-22'),
(1, 'Credit', 'Saint-Didier-des-Bois', '2022-01-01'),
(2, 'Debit', 'Marigny-le-Châtel', '2023-08-19'),
(3, 'Credit', 'Maillères', CURDATE());