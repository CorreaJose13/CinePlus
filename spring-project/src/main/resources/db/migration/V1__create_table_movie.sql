CREATE TABLE IF NOT EXISTS movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    synopsis TEXT,
    duration INT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    image_url VARCHAR(255),
    enabled BOOLEAN NOT NULL
);