CREATE TABLE todo (
	id  INT PRIMARY KEY,
	finished INT NOT NULL,
	title VARCHAR(64) NOT NULL,
	description VARCHAR(1024) NOT NULL,
	priority INT NOT NULL
);