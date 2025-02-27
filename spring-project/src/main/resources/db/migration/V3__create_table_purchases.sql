CREATE TABLE IF NOT EXISTS Purchases (
    Purchase_ID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(100) NOT NULL,
    Purchase_Date DATETIME NOT NULL,
    Ticket_Amount INT NOT NULL,
    Purchase_State VARCHAR(20) NOT NULL,
    FOREIGN KEY (Email) REFERENCES Customers(Email)
);