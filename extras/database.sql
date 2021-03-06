  DROP DATABASE IF EXISTS yam;
  CREATE DATABASE IF NOT EXISTS yam;
  USE yam;

  DROP TABLE IF EXISTS words;
  CREATE TABLE IF NOT EXISTS words(
    id INT NOT NULL AUTO_INCREMENT,
    word VARCHAR (50) NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE KEY (word)
  );

  INSERT INTO words (word) VALUES ('sunshine'),('love'),('heart'),('soul'),('oxygen'),('enemy'),('friend'),('human diary');

  DROP TABLE IF EXISTS requests;
  CREATE TABLE IF NOT EXISTS requests(
    id INT NOT NULL AUTO_INCREMENT,
    word_id INT NOT NULL ,
    ip VARCHAR (17) NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY (word_id) REFERENCES words(id) ON UPDATE CASCADE ON DELETE CASCADE
  );