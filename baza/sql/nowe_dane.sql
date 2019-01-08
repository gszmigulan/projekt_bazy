USE `baza_pracownikow`;

INSERT INTO dzialy(nazwa) VALUES('kadr');
INSERT INTO dzialy(nazwa) VALUES('logistyki');
INSERT INTO dzialy(nazwa) VALUES('marketingu');
INSERT INTO dzialy(nazwa) VALUES('sprzedaży');
INSERT INTO dzialy(nazwa) VALUES('produkcji');
INSERT INTO dzialy(nazwa) VALUES('reklamy');
INSERT INTO dzialy(nazwa) VALUES('dyrekcja');
INSERT INTO dzialy(nazwa) VALUES('informatyki');

INSERT INTO stanowiska(nazwa) VALUES('kierownik');
INSERT INTO stanowiska(nazwa) VALUES('sprzedawca');
INSERT INTO stanowiska(nazwa) VALUES('dyrektor');
INSERT INTO stanowiska(nazwa) VALUES('specjalista');
INSERT INTO stanowiska(nazwa) VALUES('główny księgowy');
INSERT INTO stanowiska(nazwa) VALUES('główny specjalista');
INSERT INTO stanowiska(nazwa) VALUES('referent');
INSERT INTO stanowiska(nazwa) VALUES('informatyk');


INSERT INTO kwalifikacje(nazwa) VALUES('wykształcenie zawodowe');
INSERT INTO kwalifikacje(nazwa) VALUES('wykształcenie średnie');
INSERT INTO kwalifikacje(nazwa) VALUES('wykształcenie wyższe');
INSERT INTO kwalifikacje(nazwa) VALUES('wykształcenie wyższe informtyczne');
INSERT INTO kwalifikacje(nazwa) VALUES('wykształcenie wyższe ekonomiczne');
INSERT INTO kwalifikacje(nazwa) VALUES('praca na wysokości');
INSERT INTO kwalifikacje(nazwa) VALUES('znajomość języka angielskiego');
INSERT INTO kwalifikacje(nazwa) VALUES('znajomość języka niemieckiego');
INSERT INTO kwalifikacje(nazwa) VALUES('znajomość języka francuskiego');


DROP FUNCTION IF EXISTS losowy_float;
DROP FUNCTION IF EXISTS losowy_int;
DROP FUNCTION IF EXISTS losowy_numer;
DROP FUNCTION IF EXISTS losowa_data;
DROP FUNCTION IF EXISTS losowe_imie;
DROP FUNCTION IF EXISTS losowe_nazwisko;
DROP FUNCTION IF EXISTS losowe_cyfry_char;
DROP FUNCTION IF EXISTS losowy_pesel;
DROP FUNCTION IF EXISTS losowy_stan;
DROP PROCEDURE IF EXISTS NowiPracownicy;

CREATE FUNCTION losowy_int(d INT, g INT) #zwraca losową liczbę całkowitą z przedziału [d,g)
RETURNS INT DETERMINISTIC
RETURN FLOOR(RAND()*(g-d)+d);

CREATE FUNCTION losowy_float(d INT, g INT) #zwraca losową liczbę z przedziału [d,g] zaokrągloną do dwóch miejsc po przecinku
RETURNS float DETERMINISTIC
RETURN ROUND((RAND()*(g-d)+d),2);

DELIMITER $$
CREATE FUNCTION losowe_imie() 
RETURNS varchar(30) DETERMINISTIC
BEGIN
	DECLARE id int(3);
    SET id=losowy_int(1,200);
	RETURN ( SELECT sakila.actor.first_name FROM sakila.actor WHERE sakila.actor.actor_id=id);
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION losowe_nazwisko()
RETURNS varchar(30) DETERMINISTIC
BEGIN
	DECLARE id int(3);
    SET id=losowy_int(1,200);
	RETURN ( SELECT sakila.actor.last_name FROM sakila.actor WHERE sakila.actor.actor_id=id);
END $$
DELIMITER ;

CREATE FUNCTION losowa_data(min_wiek_dni int, max_wiek_dni int) #zwraca losową datę
RETURNS date DETERMINISTIC
RETURN DATE_ADD(CURDATE(), INTERVAL -losowy_int(min_wiek_dni, max_wiek_dni) DAY);

DELIMITER $$
CREATE FUNCTION losowe_cyfry_char() #generuje losowe cyfry 7-10 numeru pesel, na ich podstawie wylicza 11. cyfrę
RETURNS char(4) DETERMINISTIC
BEGIN
	RETURN CONCAT(CONVERT(FLOOR(RAND()*(9)+1),char(1)),CONVERT(FLOOR(RAND()*(9)+1),char(1)),
    CONVERT(FLOOR(RAND()*(9)+1),char(1)),CONVERT(FLOOR(RAND()*(9)+1),char(1)));
END $$

DELIMITER $$
CREATE FUNCTION losowy_pesel(data_urodzenia date)
RETURNS char(11) DETERMINISTIC
BEGIN
	DECLARE data_string varchar(10);
	DECLARE pom varchar(4);
    DECLARE rok char(2);
    DECLARE miesiac char(2);
    DECLARE dzien char(2);
    DECLARE rok_int int(4);
    DECLARE miesiac_int int(2);
    DECLARE pesel varchar(11);
    DECLARE ostatnia_cyfra int(1);
    DECLARE cyfra int(2);
    
    SET miesiac_int=MONTH(data_urodzenia);
    SET rok_int=YEAR(data_urodzenia);
    SET data_string=CONVERT(data_urodzenia,char(10));
    SET rok=SUBSTR(data_string,3,2);
    SET miesiac=SUBSTR(data_string,6,2);
    SET dzien=RIGHT(data_string,2);
    
    IF rok_int>=1800 AND rok_int<=1899 THEN
    BEGIN
		SET miesiac_int=miesiac_int+80;
        SET miesiac=CONVERT(miesiac_int,char(2));
	END;
    END IF;
    
    IF rok_int>=2000 AND rok_int<=2099 THEN
    BEGIN
		SET miesiac_int=miesiac_int+20;
        SET miesiac=CONVERT(miesiac_int,char(2));
	END;
    END IF;
    
    IF rok_int>=2100 AND rok_int<=2199 THEN
    BEGIN
		SET miesiac_int=miesiac_int+40;
        SET miesiac=CONVERT(miesiac_int,char(2));
	END;
    END IF;
    
    IF rok_int>=2200 AND rok_int<=2299 THEN
    BEGIN
		SET miesiac_int=miesiac_int+60;
        SET miesiac=CONVERT(miesiac_int,char(2));
	END;
    END IF;
    
    SET pesel=CONCAT(rok,miesiac,dzien,losowe_cyfry_char()); #pesel bez ostatniej cyfry
    #pobieram kolejne cyfry numeru pesel, konwertuję na int i dodaję do ostatniej cyfry numeru pesel
    SET ostatnia_cyfra=0;
    
    SET cyfra=CONVERT((LEFT(pesel,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 9*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,2,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 7*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,3,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 3*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,4,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 1*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,5,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 9*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,6,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 7*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,7,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 3*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,8,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 1*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,9,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 9*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET cyfra=CONVERT((SUBSTR(pesel,10,1)),signed);
    SET ostatnia_cyfra=ostatnia_cyfra + 7*cyfra;
    SET ostatnia_cyfra=ostatnia_cyfra % 10;
    
    SET pesel=CONCAT(pesel,CONVERT(ostatnia_cyfra,char(1)));
    RETURN pesel;
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION losowy_stan()
RETURNS varchar(20) DETERMINISTIC
BEGIN
DECLARE a int(10);
DECLARE stan varchar(20);
SET a = losowy_int(1,3);
IF a = 1 THEN SET stan='zatrudniony';
	ELSEIF a=2 THEN SET stan='zwolniony';
END IF;
RETURN stan;
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION losowy_numer()
RETURNS char(9) DETERMINISTIC
BEGIN
	DECLARE i int DEFAULT 0;
    DECLARE a int;
    DECLARE numer varchar(9);
    
    WHILE i < 9 DO
    SET a = losowy_int(1,9);
    IF i = 1 THEN SET numer=CONVERT(a,char(1)); END IF;
    SET numer = CONCAT(numer,CONVERT(a,char(1)));
    SET i = i + 1;
    END WHILE;
    RETURN numer;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE NowiPracownicy(ilosc int)
BEGIN
	DECLARE i int DEFAULT 0;
    DECLARE data_ur date;
    SET i=0;
    
    WHILE i < ilosc DO
    SET data_ur=losowa_data(6588,24156);
    INSERT INTO pracownicy(imie, nazwisko, stanowisko, dzial, etat, stan_zatrudnienia, wynagrodzenie, pesel,
    data_urodzenia, nr_telefonu)
    VALUES (losowe_imie(), losowe_nazwisko(), losowy_int(1,9), losowy_int(1,9), 1, losowy_stan(), losowy_float(4000,6000),
    losowy_pesel(data_ur), data_ur, losowy_numer());
    SET i = i + 1;
    END WHILE;
END $$
DELIMITER ;

CALL NowiPracownicy(100);

DROP FUNCTION IF EXISTS losowy_float;
DROP FUNCTION IF EXISTS losowy_int;
DROP FUNCTION IF EXISTS losowy_numer;
DROP FUNCTION IF EXISTS losowa_data;
DROP FUNCTION IF EXISTS losowe_imie;
DROP FUNCTION IF EXISTS losowe_nazwisko;
DROP FUNCTION IF EXISTS losowe_cyfry_char;
DROP FUNCTION IF EXISTS losowy_pesel;
DROP FUNCTION IF EXISTS losowy_stan;
DROP PROCEDURE IF EXISTS NowiPracownicy;