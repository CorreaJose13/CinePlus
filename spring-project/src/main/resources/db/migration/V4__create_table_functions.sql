CREATE TABLE IF NOT EXISTS Functions (
    Function_ID INT AUTO_INCREMENT PRIMARY KEY,
    Movie_ID INT NOT NULL,
    Function_Date DATE NOT NULL,
    Function_Time TIME NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Movie_ID) REFERENCES Movies(Movie_ID)
);