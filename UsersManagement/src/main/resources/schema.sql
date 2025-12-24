DROP TABLE IF EXISTS users;

CREATE TABLE users (
	
    user_id			VARCHAR(32) 	PRIMARY KEY 
,	password 		VARCHAR(255) 	NOT NULL
,	role 			VARCHAR(15) 	NOT NULL
,	name 			VARCHAR(255) 	
,	mail 			VARCHAR(255)	UNIQUE
,	account_enabled BOOLEAN 		NOT NULL
,	add_date 		DATETIME 		NOT NULL
,	upd_date 		DATETIME
,	last_login_date DATETIME
);