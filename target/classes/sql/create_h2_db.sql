/*
   H2 test database schema creation 
 */

/* Create table for entity Car */
CREATE TABLE Car (
id INTEGER NOT NULL,
nom VARCHAR(45) NOT NULL,
driver INTEGER ,
PRIMARY KEY(id)
);

/* Create table for entity Driver */
CREATE TABLE Driver (
id INTEGER NOT NULL,
nom VARCHAR(45) NOT NULL,
PRIMARY KEY(id)
);

