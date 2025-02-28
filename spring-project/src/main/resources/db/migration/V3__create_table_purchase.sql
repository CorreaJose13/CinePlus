CREATE TABLE IF NOT EXISTS purchase (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_email VARCHAR(100) NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    ticket_amount INT NOT NULL,
    status ENUM('successful','failed','pending'),
    FOREIGN KEY (customer_email) REFERENCES customer(email)
);