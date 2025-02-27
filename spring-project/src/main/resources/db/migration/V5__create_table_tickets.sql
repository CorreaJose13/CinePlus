CREATE TABLE IF NOT EXISTS Tickets (
    Ticket_ID INT AUTO_INCREMENT PRIMARY KEY,
    Purchase_ID INT NOT NULL,
    Function_ID INT NOT NULL,
    FOREIGN KEY (Purchase_ID) REFERENCES Purchases(Purchase_ID),
    FOREIGN KEY (Function_ID) REFERENCES Functions(Function_ID)
);