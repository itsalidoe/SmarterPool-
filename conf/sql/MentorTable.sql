CREATE TABLE Mentor (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    timestamp VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    field VARCHAR(255) NOT NULL,
    linkedinId VARCHAR(255)
)