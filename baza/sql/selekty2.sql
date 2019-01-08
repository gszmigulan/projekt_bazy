USE `baza_pracownikow`;
DROP PROCEDURE IF EXISTS szukaj;



DELIMITER $$
CREATE PROCEDURE szukaj(
in imie varchar(30),
in nazwisko varchar(30),
in pesel char(11),
in uro varchar(20),#date,
in adres varchar(60),
in telefon char(9),
in stanowisko varchar(30),#int(10),
in dzial varchar(30),#int(10),
in etat varchar(30),#float,
in wynagrodzenie varchar(30),#float,
in zatrudniony varchar(30),#int(1)
in n1 varchar(1), in n2 varchar(1), in n3 varchar(1), in n4 varchar(1), in n5 varchar(1),
 in n6 varchar(1), in n7 varchar(1), in n8 varchar(1), in n9 varchar(1), in n10 varchar(1), 
 in n11 varchar(1), in n12 varchar(1), in n13 varchar(1), in n14 varchar(1), in n15 varchar(1)
)
## 1 jeśli zatrudniony , 0 jeśli zwolniony, 2 to % jeśli nieistotne
BEGIN
   if(zatrudniony=1) then
    SELECT id FROM sz as p #pracownicy
   WHERE p.imie like imie and
   p.nazwisko like nazwisko and
   p.pesel like pesel and
   p.data_urodzenia like uro and
   p.stanowisko like stanowisko and
   p.dzial like dzial and
   p.etat like etat and
  # p.adres like adres and
   p.nr_telefonu like telefon and
   p.wynagrodzenie like wynagrodzenie and
   p.stan_zatrudnienia like 'zatrudniony'
   
and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n1)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n2)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n3)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n4)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n5)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n6)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n7)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n8)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n9)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n10)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n11)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n12)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n13)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n14)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n15)
   ;
  
   end if;
   
   if(zatrudniony=0) then
    SELECT id FROM pracownicy as p
   WHERE p.imie like imie and
   p.nazwisko like nazwisko and
   p.pesel like pesel and
   p.data_urodzenia like uro and
   p.stanowisko like stanowisko and
   p.dzial like dzial and
   p.etat like etat and
   #p.adres like adres and
   p.nr_telefonu like telefon and
   p.wynagrodzenie like wynagrodzenie and
   p.stan_zatrudnienia like 'zwolniony'
   
   
and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n1)
  and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n2)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n3)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n4)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n5)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n6)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n7)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n8)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n9)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n10)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n11)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n12)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n13)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n14)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n15)
   ;  # dodaje
   end if;
   
   if(zatrudniony=2) then
    SELECT id FROM pracownicy as p
   WHERE p.imie like imie and
   p.nazwisko like nazwisko and
   p.pesel like pesel and
   p.data_urodzenia like uro and
   p.stanowisko like stanowisko and
   p.dzial like dzial and
   p.etat like etat and
   #p.adres like adres and
   # adres to null
   p.nr_telefonu like telefon and
   p.wynagrodzenie like wynagrodzenie and
   p.stan_zatrudnienia like '%'
   
and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n1)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n2)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n3)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n4)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n5)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n6)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n7)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n8)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n9)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n10)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n11)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n12)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n13)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n14)
   and id in (select id_pracownika from (select * from  pracownik_kwalifikacje) as p
   where p.id_kwalifikacji like n15)
   ;  
   end if;
   
   
   
END $$
DELIMITER ;