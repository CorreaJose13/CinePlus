CREATE TABLE IF NOT EXISTS room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    room_number INT NOT NULL,
    capacity INT NOT NULL,
    available_seats TEXT NOT NULL
);