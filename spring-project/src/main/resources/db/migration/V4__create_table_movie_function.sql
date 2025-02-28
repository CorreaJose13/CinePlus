CREATE TABLE IF NOT EXISTS movie_function (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    function_date DATE NOT NULL,
    function_time TIME NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);