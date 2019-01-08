DROP ROLE IF EXISTS 'administrator';
CREATE ROLE 'administrator';
GRANT ALL PRIVILEGES ON `baza_pracownikow`.* TO 'administrator' WITH GRANT OPTION;

#zmienić przywileje zgodnie z projektem
DROP ROLE IF EXISTS 'dyrektor';
CREATE ROLE 'dyrektor';
GRANT ALL PRIVILEGES ON `baza_pracownikow`.* TO 'dyrektor';

#zmienić przywileje zgodnie z projektem
DROP ROLE IF EXISTS 'kierownik_dzialu';
CREATE ROLE 'kierownik_dzialu';
GRANT ALL PRIVILEGES ON `baza_pracownikow`.* TO 'kierownik_dzialu';

#zmienić przywileje zgodnie z projektem
DROP ROLE IF EXISTS 'osoba_rekrutujaca';
CREATE ROLE 'osoba_rekrutujaca';
GRANT ALL PRIVILEGES ON `baza_pracownikow`.* TO 'osoba_rekrutujaca';