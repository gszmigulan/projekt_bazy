DROP DATABASE IF EXISTS `Baza_pracownikow`;

CREATE DATABASE IF NOT EXISTS `Baza_pracownikow`;
USE `Baza_pracownikow`;

CREATE TABLE IF NOT EXISTS stanowiska(
	id int UNSIGNED NOT NULL AUTO_INCREMENT,
    nazwa varchar(30),
    
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS dzialy(
	id int UNSIGNED NOT NULL AUTO_INCREMENT,
    nazwa varchar(30),
    
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS pracownicy(
	id int UNSIGNED NOT NULL AUTO_INCREMENT,
    imie varchar(30),
    nazwisko varchar(30),
    stanowisko int UNSIGNED NOT NULL,
    dzial int UNSIGNED NOT NULL,
    etat float,
    stan_zatrudnienia enum('zatrudniony', 'zwolniony'),
    wynagrodzenie float,
    pesel char(11),
    data_urodzenia date,
    nr_telefonu char(9),
    adres varchar(60),
    
    PRIMARY KEY(id),
    FOREIGN KEY stanowisko (stanowisko) REFERENCES stanowiska(id),
    FOREIGN KEY dzial (dzial) REFERENCES dzialy(id)
);

CREATE TABLE IF NOT EXISTS uzytkownicy(
login varchar(20) NOT NULL,
id_pracownika int UNSIGNED NOT NULL,
haslo varchar(30) NOT NULL,
uprawnienia enum('administrator', 'dyrektor', 'kierownik', 'osoba_rekrutujaca') NOT NULL,

PRIMARY KEY(login),
FOREIGN KEY id_pracownika (id_pracownika) REFERENCES pracownicy(id)
);

CREATE TABLE IF NOT EXISTS historia_zatrudnienia(
	id_pracownika int UNSIGNED NOT NULL,
    data_wydarzenia date,
    wydarzenie enum('zatrudnienie', 'zwolnienie'),
    
    
    FOREIGN KEY pracownik (id_pracownika) REFERENCES pracownicy(id)
);

CREATE TABLE IF NOT EXISTS kwalifikacje(
	id int UNSIGNED AUTO_INCREMENT,
    nazwa varchar(60),
    
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS wymagane_kwalifikacje(
	id_stanowiska int UNSIGNED NOT NULL,
    id_kwalifikacji int UNSIGNED NOT NULL,
    
    FOREIGN KEY id_stanowiska (id_stanowiska) REFERENCES stanowiska(id),
    FOREIGN KEY id_kwalifikacji (id_kwalifikacji) REFERENCES kwalifikacje(id)
);

CREATE TABLE IF NOT EXISTS pracownik_kwalifikacje(
	id_pracownika int UNSIGNED NOT NULL,
    id_kwalifikacji int UNSIGNED,
    
    FOREIGN KEY id_prac (id_pracownika) REFERENCES pracownicy(id),
    FOREIGN KEY kwalifikacje (id_kwalifikacji) REFERENCES kwalifikacje(id)
);