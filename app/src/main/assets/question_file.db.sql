BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `question_file` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`question`	TEXT,
	`odp`	TEXT
);
INSERT INTO `question_file` VALUES (1,'Tak czy nie?','Tak');
COMMIT;
