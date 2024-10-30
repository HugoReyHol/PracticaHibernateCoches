DROP DATABASE IF EXISTS cocheshibernate;
CREATE DATABASE  cocheshibernate;
USE cocheshibernate;

CREATE TABLE Coche(
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  matricula VARCHAR(50) NOT NULL UNIQUE,
  marca VARCHAR(20) NOT NULL,
  modelo VARCHAR(20) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  CONSTRAINT chk_matricula CHECK (matricula REGEXP '^[0-9]{4}[A-Z]{3}$')
 
);