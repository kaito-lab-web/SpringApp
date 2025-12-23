DROP TABLE IF EXISTS users;

CREATE TABLE users (
	
    user_id VARCHAR(50) PRIMARY KEY ,
    name VARCHAR(255),
    password VARCHAR(255) NOT NULL
);