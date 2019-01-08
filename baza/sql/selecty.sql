USE `baza_pracownikow`;
DROP PROCEDURE IF EXISTS pokaz_wszystkich_pracownikow;
DROP PROCEDURE IF EXISTS pokaz_pracownikow_dzialu;
DROP PROCEDURE IF EXISTS sortname;
DROP PROCEDURE IF EXISTS sortnameK;

DROP PROCEDURE IF EXISTS get_kwalifikacje;

DELIMITER $$
CREATE PROCEDURE pokaz_wszystkich_pracownikow()
BEGIN
	/*
	SELECT p.id FROM pracownicy AS p INNER JOIN stanowiska AS s ON p.stanowisko = s.id
	INNER JOIN dzialy AS d ON p.dzial = d.id;
    */
    SELECT id FROM pracownicy;
END $$
DELIMITER ;

/*
Procedura przyjmuje jako argument id pracownika i wybiera wszystkich pracowników pracujących w tym samym dziale
*/
DELIMITER $$
CREATE PROCEDURE pokaz_pracownikow_dzialu(IN id_pracownika int)
BEGIN
	/*
	SELECT p.id FROM pracownicy AS p 
    INNER JOIN (SELECT id, dzial FROM pracownicy WHERE id = id_pracownika) AS t ON p.dzial = t.dzial
    INNER JOIN stanowiska AS s ON p.stanowisko = s.id
	INNER JOIN dzialy AS d ON p.dzial = d.id;
    */
    SELECT p.id FROM pracownicy AS p 
    INNER JOIN (SELECT id, dzial FROM pracownicy WHERE id = id_pracownika) AS t ON p.dzial = t.dzial;
END $$
DELIMITER ;

# sortowanie po imieniu kiedy robi to kierownik tylko z jego działu 
DELIMITER $$
CREATE PROCEDURE sortnameK(IN id_pracownika int)
BEGIN
	
    SELECT p.id FROM pracownicy AS p 
    INNER JOIN (SELECT id, dzial FROM pracownicy WHERE id = id_pracownika) AS t ON p.dzial = t.dzial
    order by p.imie;
END $$
DELIMITER ;
# sortowanie po imieniu reszta sortowań w selecty2
DELIMITER $$
CREATE PROCEDURE sortname()
BEGIN
    SELECT id FROM pracownicy 
    order by imie;
END $$
DELIMITER ;
# pokazuje listę wszystkich kwalifikacji
DELIMITER $$
CREATE PROCEDURE get_kwalifikacje()
BEGIN
    SELECT nazwa FROM kwalifikacje;
   
END $$
DELIMITER ;
call get_kwalifikacje();