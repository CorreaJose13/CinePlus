CREATE TABLE IF NOT EXISTS movie_function (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    room_id INT NOT NULL,
    function_date DATE NOT NULL,
    function_time TIME NOT NULL,
    available_seats TEXT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
);