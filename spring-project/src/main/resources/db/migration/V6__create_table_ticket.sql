CREATE TABLE IF NOT EXISTS ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    purchase_id INT NOT NULL,
    function_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    seat VARCHAR(3) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    FOREIGN KEY (function_id) REFERENCES movie_function(id)
);