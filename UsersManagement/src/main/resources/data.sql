DELETE FROM users;

INSERT INTO
	users (
		user_id
	, 	password
	,	role
	,	name
	,	mail
	,	account_enabled
	,	add_date
	)
VALUES 
	(
		'kaito'
	,	'$2a$10$j.TrzVKf6neZ2Reul4arBec8RWGtaxVUurKp0L1umh40iW9vjVZLa'
	,	'ROLE_ADMIN'
	,	'かいと'
	,	'abc@abc'
	,	true
	,	'2025-12-24 22:14:07.382481'
	);