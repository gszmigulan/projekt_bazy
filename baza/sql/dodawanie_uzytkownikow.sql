USE `baza_pracownikow`;
DROP PROCEDURE IF EXISTS dodaj_uzytkownika;

DELIMITER $$
CREATE PROCEDURE dodaj_uzytkownika(IN login varchar(20), IN haslo varchar(30), IN pracownik int(10),
									IN uprawnienia enum('administrator', 'dyrektor', 'kierownik', 'osoba_rekrutujaca'),
                                    OUT wynik varchar(5))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		ROLLBACK;
		SET wynik = 'false';
	END;

	SET @s = CONCAT("CREATE USER '", login,"'@'localhost' IDENTIFIED BY '", haslo, "';");
	PREPARE stmt FROM @s;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
    /*
    SET @s = CONCAT("GRANT ", uprawnienia, " TO '", login, "'@'localhost';");
    PREPARE stmt FROM @s;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    */
    
	IF (uprawnienia = 'administrator') THEN 
    BEGIN
		SET @s = CONCAT("GRANT ALL PRIVILEGES ON `baza_pracownikow`.* TO '", login ,"'@'localhost' WITH GRANT OPTION;");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EVENT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT SHUTDOWN, SHOW DATABASES, SUPER ON *.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
         # administrator ma wszystko to nic nie dopisuje 
	END;
    ELSEIF (uprawnienia = 'dyrektor') THEN
    BEGIN
		SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.pokaz_wszystkich_pracownikow TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        # tu dodaje
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortname TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortnazwisko TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortetat TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortwynagrodzenie TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sorturodzenie TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        # to chyba tylko admin
       /* SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.addDzial TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.addStanowisko TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.addKwalifikacja TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;*/
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.szukaj TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.get_kwalifikacje TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        
        # koniec
        
        SET @s = CONCAT("GRANT SELECT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.pokaz_pracownikow_dzialu TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EVENT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT SHUTDOWN, SHOW DATABASES, SUPER ON *.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	END;
    ELSEIF (uprawnienia = 'kierownik') THEN
    BEGIN
		SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.pokaz_pracownikow_dzialu TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        # tu dodaje
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortnameK TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortnazwiskoK TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortetatK TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sortynagrodzenieK TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.sorturodzenieK TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.szukaj TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.get_kwalifikacje TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        # koniec
        
        SET @s = CONCAT("GRANT SELECT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EVENT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT SHUTDOWN, SHOW DATABASES, SUPER ON *.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	END;
    ELSEIF (uprawnienia = 'osoba_rekrutujaca') THEN
    BEGIN
		SET @s = CONCAT("GRANT EXECUTE ON PROCEDURE `baza_pracownikow`.pokaz_wszystkich_pracownikow TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        # tu dodaje
        
        # on chyba nie może przeglądać dancych tylko dodawać pracownika
        
        #koniec
        
        SET @s = CONCAT("GRANT SELECT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT EVENT ON `baza_pracownikow`.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @s = CONCAT("GRANT SHUTDOWN, SHOW DATABASES, SUPER ON *.* TO '", login ,"'@'localhost';");
        PREPARE stmt FROM @s;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	END;
    END IF;
    
    /*
    SET @s = CONCAT("SET DEFAULT ROLE ",uprawnienia," FOR '",login,"'@'localhost';");
    #SET @s = CONCAT("SET DEFAULT ROLE ALL TO '",login,"'@'localhost';");
	PREPARE stmt FROM @s;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    */
    
    
	SET @s = CONCAT("FLUSH PRIVILEGES;");
	PREPARE stmt FROM @s;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt; 
    
    SET @s = CONCAT("INSERT INTO uzytkownicy(login, id_pracownika, haslo, uprawnienia) 
    VALUES('", login, "', '", pracownik, "', '", haslo, "' ,'", uprawnienia, "');");
    PREPARE stmt FROM @s;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
    SET wynik = 'true';
END $$
DELIMITER ;