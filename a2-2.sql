-- Add below your SQL statements.
-- You can create intermediate views (as needed). Remember to drop these views after you have populated the result tables.
-- You can use the "\i a2.sql" command in psql to execute the SQL commands in this file.

DROP SCHEMA IF EXISTS A2 CASCADE;
CREATE SCHEMA A2;
SET search_path TO A2;

DROP TABLE IF EXISTS country CASCADE;
DROP TABLE IF EXISTS language CASCADE;
DROP TABLE IF EXISTS religion CASCADE;
DROP TABLE IF EXISTS hdi CASCADE;
DROP TABLE IF EXISTS ocean CASCADE;
DROP TABLE IF EXISTS neighbour CASCADE;
DROP TABLE IF EXISTS oceanAccess CASCADE;
DROP TABLE IF EXISTS query1 CASCADE;
DROP TABLE IF EXISTS query2 CASCADE;
DROP TABLE IF EXISTS query3 CASCADE;
DROP TABLE IF EXISTS query4 CASCADE;
DROP TABLE IF EXISTS query5 CASCADE;
DROP TABLE IF EXISTS query6 CASCADE;
DROP TABLE IF EXISTS query7 CASCADE;
DROP TABLE IF EXISTS query8 CASCADE;
DROP TABLE IF EXISTS query9 CASCADE;
DROP TABLE IF EXISTS query10 CASCADE;


-- The country table contains all the countries in the world and their facts.
-- 'cid' is the id of the country.
-- 'name' is the name of the country.
-- 'height' is the highest elevation point of the country.
-- 'population' is the population of the country.
CREATE TABLE country (
    cid 		INTEGER 	PRIMARY KEY,
    cname 		VARCHAR(20)	NOT NULL,
    height 		INTEGER 	NOT NULL,
    population	INTEGER 	NOT NULL);

-- The language table contains information about the languages and the percentage of the speakers of the language for each country.
-- 'cid' is the id of the country.
-- 'lid' is the id of the language.
-- 'lname' is the name of the language.
-- 'lpercentage' is the percentage of the population in the country who speak the language.
CREATE TABLE language (
    cid 		INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,
    lid 		INTEGER 	NOT NULL,
    lname 		VARCHAR(20) NOT NULL,
    lpercentage	REAL 		NOT NULL,
	PRIMARY KEY(cid, lid));

-- The religion table contains information about the religions and the percentage of the population in each country that follow the religion.
-- 'cid' is the id of the country.
-- 'rid' is the id of the religion.
-- 'rname' is the name of the religion.
-- 'rpercentage' is the percentage of the population in the country who follows the religion.
CREATE TABLE religion (
    cid 		INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,
    rid 		INTEGER 	NOT NULL,
    rname 		VARCHAR(20) NOT NULL,
    rpercentage	REAL 		NOT NULL,
	PRIMARY KEY(cid, rid));

-- The hdi table contains the human development index of each country per year. (http://en.wikipedia.org/wiki/Human_Development_Index)
-- 'cid' is the id of the country.
-- 'year' is the year when the hdi score has been estimated.
-- 'hdi_score' is the Human Development Index score of the country that year. It takes values [0, 1] with a larger number representing a higher HDI.
CREATE TABLE hdi (
    cid 		INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,
    year 		INTEGER 	NOT NULL,
    hdi_score 	REAL 		NOT NULL,
	PRIMARY KEY(cid, year));

-- The ocean table contains information about oceans on the earth.
-- 'oid' is the id of the ocean.
-- 'oname' is the name of the ocean.
-- 'depth' is the depth of the deepest part of the ocean
CREATE TABLE ocean (
    oid 		INTEGER 	PRIMARY KEY,
    oname 		VARCHAR(20) NOT NULL,
    depth 		INTEGER 	NOT NULL);

-- The neighbour table provides information about the countries and their neighbours.
-- 'country' refers to the cid of the first country.
-- 'neighbor' refers to the cid of a country that is neighbouring the first country.
-- 'length' is the length of the border between the two neighbouring countries.
CREATE TABLE neighbour (
    country 	INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,
    neighbor 	INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,
    length 		INTEGER 	NOT NULL,
	PRIMARY KEY(country, neighbor));

-- The oceanAccess table provides information about the countries which have a border with an ocean.
-- 'cid' refers to the cid of the country.
-- 'oid' refers to the oid of the ocean.
CREATE TABLE oceanAccess (
    cid 	INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,
    oid 	INTEGER 	REFERENCES ocean(oid) ON DELETE RESTRICT,
    PRIMARY KEY(cid, oid));

-- The following tables will be used to store the results of your queries.
-- Each of them should be populated by your last SQL statement that looks like:
-- â€œINSERT INTO queryX (SELECT â€¦ <complete your SQL query here> â€¦)â€

CREATE TABLE query1(
	c1id	INTEGER,
    c1name	VARCHAR(20),
	c2id	INTEGER,
    c2name	VARCHAR(20)
);



CREATE TABLE query2(
	cid		INTEGER,
    cname	VARCHAR(20)
);

CREATE TABLE query4(
	cname	VARCHAR(20),
    oname	VARCHAR(20)
);

CREATE TABLE query5(
	cid		INTEGER,
    cname	VARCHAR(20),
	avghdi	REAL
);

CREATE TABLE query6(
	cid		INTEGER,
    cname	VARCHAR(20)
);

CREATE TABLE query7(
	rid			INTEGER,
    rname		VARCHAR(20),
	followers	INTEGER
);

CREATE TABLE query8(
	c1name	VARCHAR(20),
    c2name	VARCHAR(20),
	lname	VARCHAR(20)
);

CREATE TABLE query9(
    cname		VARCHAR(20),
	totalspan	INTEGER
);

CREATE TABLE query10(
    cname			VARCHAR(20),
	borderslength	INTEGER
);

INSERT INTO country
VALUES ('1', 'Bangladesh', '500', '12345678'),
('2', 'Canada', '1500', '549095'),
('3', 'USA', '1200', '110210'),
('4', 'India', '890', '119525987'),
('5', 'England', '400', '20050'),
('6', 'Spain', '300', '119510'),
('7', 'Portugal', '295', '290190'),
('8', 'Brazil', '1000', '1111110'),
('9', 'Mexico', '1100', '5520510'),
('10', 'Ireland', '550', '200050');

INSERT INTO language
VALUES ('1', '10', 'Bengali', '100'),
('2', '20', 'English', '75'),
('2', '30', 'French', '25'),
('3', '20', 'English', '80'),
('3', '40', 'Spanih', '20'),
('4', '18', 'Hindi', '67'),
('4', '15', 'Tamil', '20'),
('4', '10', 'Bengali', '13'),
('5', '20', 'English', '100'),
('6', '40', 'Spanish', '100'),
('7', '50', 'Portugese', '100'),
('8', '40', 'Spanish', '100'),
('9', '40', 'Spanish', '90'),
('9', '20', 'English', '10'),
('10', '90', 'Gaelic', '25'),
('10', '20', 'English', '75');

INSERT INTO religion
VALUES ('1','5','Islam','90'),
('1','10','Hinduism','10'),
('2','15','Christianity','100'),
('3','15','Christianity','100'),
('4','5','Islam','12'),
('4','10','Hinduism','80'),
('4','20','Buddhism','8'),
('5','15','Christianity','100'),
('6','15','Christianity','100'),
('7','15','Christianity','100'),
('8','15','Christianity','100'),
('9','15','Christianity','100'),
('10','15','Christianity','100');

INSERT INTO hdi
VALUES ('1', '2009', '55'),
('1', '2010', '56.7'),
('2', '2009', '70'),
('2', '2010', '67'),
('4', '2010', '73'),
('4', '2011', '71.8'),
('5', '2019', '61.3'),
('6', '2019', '68'),
('7', '2019', '67.2'),
('8', '2018', '55'),
('8', '2019', '61.7'),
('9', '2017', '65'),
('9', '2018', '68'),
('9', '2019', '71.1'),
('10', '2018', '70.8'),
('10', '2019', '73.2');


INSERT INTO ocean
VALUES ('1','Pacific','3970'),
('2','Atlantic','3646'),
('3','Indian','3741'),
('4','Southern','3270'),
('5','Arctic','1205');

INSERT INTO oceanAccess
VALUES ('2', '2'),
('3', '2'),
('4', '3'),
('5', '1'),
('6', '1'),
('7', '1'),
('9', '2');



INSERT INTO neighbour
VALUES ('1','4','1000'),
('2','3','5500'),
('3','2','5500'),
('3','9','2000'),
('4','1','1000'),
('5','10','800'),
('5','6','330'),
('6','5','330'),
('6','7','670'),
('7','6','670'),
('9','3','2000'),
('10','5','800');



-- Query 1 statements
CREATE VIEW q1 as
(SELECT N.country as c1id, max(A.height) as maxHeight
FROM neighbour N, country A
WHERE A.cid = N.neighbor
GROUP BY N.country);
-- HAVING A.height IN (SELECT max(height) FROM country WHERE cid = N.neighbor);

INSERT INTO query1
(SELECT c1id, A.cname as c1name, B.cid as c2id, B.cname as c2name
FROM q1, country A, country B
WHERE A.cid = c1id AND q1.maxHeight = B.height
ORDER By 2);

--DROP TABLE q1 CASCADE;


-- Query 2 statements
INSERT INTO query2
(SELECT cid, cname
FROM country
WHERE cid NOT IN (SELECT cid FROM oceanAccess)
ORDER BY cname ASC);

CREATE TABLE query3(
    c1id    INTEGER,
    c1name  VARCHAR(20),
    c2id    INTEGER,
    c2name  VARCHAR(20)
);


-- Query 3 statements
CREATE VIEW q3 AS
((SELECT country as cid
FROM neighbour
GROUP BY country
HAVING count(*) = 1)
INTERSECT
(SELECT cid
FROM country
WHERE cid NOT IN (SELECT cid FROM oceanAccess)));

CREATE VIEW q33 AS
(SELECT country.cid AS c1id, country.cname AS c1name, neighbour.neighbor AS c2id
FROM q3, neighbour, country
WHERE q3.cid = country.cid AND q3.cid = neighbour.country);

INSERT INTO query3
(SELECT c1id, c1name, c2id, A.cname AS c2name FROM q33 JOIN country A ON q33.c2id = A.cid ORDER BY 2);

--DROP TABLE q3 CASCADE;
--DROP TABLE q33 CASCADE;


-- Query 4 statements
CREATE VIEW o1 AS
(SELECT country.cname, oname, country2.cname AS cname2
FROM country, country AS country2,  ocean, oceanaccess, neighbour
WHERE country.cid=oceanaccess.cid AND oceanaccess.oid=ocean.oid AND country.cid=neighbour.country AND country2.cid=neighbour.neighbor) ;

CREATE VIEW op1 AS
(SELECT cname, oname
FROM o1);

CREATE VIEW op2 AS
(SELECT cname2 as cname, oname
from o1);

INSERT INTO query4
(SELECT cname, oname
FROM op1 UNION
SELECT cname, oname
FROM op2
ORDER BY cname ASC, oname DESC);

-- Query 5 statements
CREATE VIEW q5 AS
(SELECT cid, avg(hdi_score) as avghdi
FROM hdi
WHERE year >= 2009 AND year <= 2013
GROUP BY cid);

INSERT INTO query5
(SELECT country.cid, cname, avghdi FROM q5 JOIN country ON q5.cid = country.cid ORDER BY 3 DESC LIMIT 10);


-- Query 6 statements



-- Query 7 statements

INSERT INTO query7
(SELECT rid,rname, SUM((c.population*rpercentage)/100) AS followers
FROM country AS c, religion AS r
WHERE c.cid=r.cid
GROUP BY 1,2
ORDER BY 3 DESC);

-- Query 8 statements

CREATE VIEW q8 AS
(SELECT aa.cname AS c1name, bb.cname AS c2name, a.lname AS lname
FROM neighbour n, language a, language b, country aa, country bb
WHERE n.country = a.cid AND n.neighbor = b.cid AND a.lname = b.lname AND a.cid = aa.cid AND b.cid = bb.cid
ORDER BY 3 ASC, 1 DESC);

INSERT INTO query8
(SELECT *
FROM q8);


-- Query 9 statements

CREATE VIEW q9 AS
(SELECT cname, height, depth
FROM country c, oceanAccess oa,ocean o
WHERE c.cid = oa.cid AND oa.oid = o.oid AND ((c.height+o.depth) IN (SELECT max(height+depth) FROM country c,oceanAccess oa,ocean o
WHERE c.cid = oa.cid AND oa.oid = o.oid)
));

-- max height country
CREATE VIEW q99 AS
(SELECT cname, height FROM country
WHERE cid NOT IN (SELECT cid FROM oceanAccess) AND height IN
(SELECT max(height) FROM country WHERE cid NOT IN (SELECT cid FROM oceanAccess)));


CREATE VIEW q999 AS
(SELECT q99.cname, q99.height
FROM q99, q9
WHERE q99.height >= q9.height + q9.depth);

CREATE VIEW q9999 AS
(SELECT q9.cname, q9.height, q9.depth
FROM q99, q9
WHERE q99.height <= q9.height + q9.depth);

INSERT INTO query9
(SELECT cname, height AS totalspan
FROM q999
UNION
SELECT cname, (height+depth) AS totalspan
FROM q9999);


-- Query 10 statements

INSERT INTO query10
(SELECT c.cname, MAX(sum) AS borderslength
FROM (SELECT cid, SUM(length) AS sum
FROM neighbour , country
WHERE cid=country
GROUP BY 1) AS b JOIN country AS c ON b.cid=c.cid
GROUP BY 1
ORDER BY 2 DESC);
