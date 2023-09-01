CREATE TABLE IF NOT EXISTS cards(
card_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
customer_id INT,
card_number VARCHAR(20),
card_type VARCHAR(20),
total_limit INT,
amount_used INT,
available_amount INT,
created_dt DATE
);

INSERT INTO cards (customer_id, card_number, card_type, total_limit, amount_used, available_amount, created_dt)
VALUES
(1,'5135-1800-0000-001', 'Mastercard', 100000, 7800, 92200, CURDATE()),
(1,'6703-1111-2222-3334', 'Maestro', 100000, 7800, 92200, CURDATE()),
(1,'4236-8615-8842-3130', 'Visa', 100000, 7800, 92200, CURDATE()),
(2,'4012-0010-3714-1112', 'Visa', 50000, 4200, 45800, CURDATE()),
(3,'4012-0010-3844-3335', 'Visa', 80000, 6000, 74000, CURDATE());
