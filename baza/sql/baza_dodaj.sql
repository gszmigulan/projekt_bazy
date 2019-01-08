USE `baza_pracownikow`;
DROP PROCEDURE IF EXISTS addDzial;
DROP PROCEDURE IF EXISTS addStanowisko;
DROP PROCEDURE IF EXISTS addKwalifikacja;


DELIMITER $$
CREATE PROCEDURE addDzial(text varchar(120))
begin
INSERT INTO dzialy(nazwa) VALUES(text);
end $$
delimiter ;

DELIMITER $$
CREATE PROCEDURE addStanowisko(text varchar(120))
begin
INSERT INTO stanowiska(nazwa) VALUES(text);
end $$
delimiter ;

DELIMITER $$
CREATE PROCEDURE addKwalifikacja(text varchar(120))
begin
INSERT INTO kwalifikacje(nazwa) VALUES(text);
end $$
delimiter ;