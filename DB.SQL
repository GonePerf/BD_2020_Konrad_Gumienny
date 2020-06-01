CREATE TABLE czytelnicy (
    id_czytelnika VARCHAR2(4)
    	CONSTRAINT id_czytelnika_pk PRIMARY KEY
    	CONSTRAINT id_czytelnika_nn NOT NULL,
    imie VARCHAR2(15) NOT NULL,
    nazwisko VARCHAR2(25) NOT NULL,
    PESEL INT NOT NULL UNIQUE
        CONSTRAINT pesel_length CHECK(PESEL > 10000000000 AND PESEL < 99999999999),
    data_urodzenia DATE
);
INSERT INTO czytelnicy (id_czytelnika,imie,nazwisko,PESEL,data_urodzenia) VALUES (
   0001, 'Dawid', 'Kowalski', 94121030712, '10-DEC-94');
INSERT INTO czytelnicy VALUES (
   0002, 'Kamil', 'Mak', 93052530712, '25-MAY-1993');
INSERT INTO czytelnicy VALUES (
   0003, 'Agata', 'Kowalska', 84101030712, '10-OCT-1984');
INSERT INTO czytelnicy VALUES (
   0004, 'Julka', 'Jonarska', 79101030712, '10-OCT-1979');
INSERT INTO czytelnicy VALUES (
   0005, 'Maja', 'Marek', 88031330712, '13-MAR-1988');
INSERT INTO czytelnicy VALUES (
   0006, 'Dawid', 'Nowak', 99120930712, '09-DEC-1999');
INSERT INTO czytelnicy VALUES (
   0007, 'Marek', 'Kowalski', 92111030712, '10-NOV-1992');
INSERT INTO czytelnicy VALUES (
   0008, 'Magdalena', 'Nowak', 97060330712, '03-APR-1997');
INSERT INTO czytelnicy VALUES (
   0009, 'Agata', 'Janiga', 98101530712, '15-OCT-1998');
INSERT INTO czytelnicy VALUES (
   0010, 'Bartosz', 'Trybinski', 85120630712, '06-DEC-1985');

CREATE SEQUENCE czytelnicy_seq
START WITH 11
INCREMENT BY 1;

CREATE TABLE autorzy(
    id_autora INT 
      CONSTRAINT id_autora_pk PRIMARY KEY
      CONSTRAINT id_autora_nn NOT NULL,
    imie VARCHAR2(15) NOT NULL,
    nazwisko VARCHAR2(30)
);

INSERT INTO autorzy VALUES (
   0001, 'Juliusz', 'Słowacki');
INSERT INTO autorzy VALUES (
   0002, 'Adam', 'Mickiewicz');
INSERT INTO autorzy VALUES (
   0003, 'Wiliam', 'Szekspir');
INSERT INTO autorzy VALUES (
   0004, 'Zofia', 'Nałkowska');
INSERT INTO autorzy VALUES (
   0005, 'Joseph', 'Conrad');
INSERT INTO autorzy VALUES (
   0006, 'Bolesław', 'Prus');
INSERT INTO autorzy VALUES (
   0007, 'Andrzej', 'Sapkowski');
INSERT INTO autorzy VALUES (
   0008, 'Henryk', 'Sienkiewicz');
INSERT INTO autorzy VALUES (
   0009, 'Maria', 'Konopnicka');
INSERT INTO autorzy VALUES (
   0010, 'Ernest', 'Hemingway');
INSERT INTO autorzy VALUES (
   0012, 'Erich', 'Gamma');
INSERT INTO autorzy VALUES (
   0013, 'Marek', 'Kurzyński');

CREATE SEQUENCE autorzy_seq
START WITH 14
INCREMENT BY 1;

CREATE TABLE pracownicy(
    id_pracownika INT
    CONSTRAINT id_pracownika_pk PRIMARY KEY,
    imie VARCHAR2(15) NOT NULL,
    nazwisko VARCHAR2(25) NOT NULL,
    PESEL INT NOT NULL UNIQUE
    CONSTRAINT pesel_length_ CHECK(PESEL > 10000000000 AND PESEL < 99999999999)
);

INSERT INTO pracownicy VALUES (
   '0001', 'Jan', 'Brączyk', 92021030712);
INSERT INTO pracownicy VALUES (
   '0002', 'Dominika', 'Mastej', 91061030712);
INSERT INTO pracownicy VALUES (
   '0003', 'Krzysztof', 'Bożek', 87121030712);
INSERT INTO pracownicy VALUES (
   '0004', 'Jadwiga', 'Bal', 86021730712);
INSERT INTO pracownicy VALUES (
   '0005', 'Krzysztof', 'Buba', 97051030712);

CREATE SEQUENCE ksiazki_seq
START WITH 1
INCREMENT BY 1;

CREATE TABLE ksiazki(
	id_ksiazki INT
      CONSTRAINT id_ksiazki_pk PRIMARY KEY
      CONSTRAINT id_ksiazki_nn NOT NULL,
	tytul VARCHAR2(100),
	rok_wydania INT
    CONSTRAINT rok_wyd CHECK(rok_wydania > 1800 AND rok_wydania < 2020),
	dostepnosc NUMBER(1),
	id_autora INT CONSTRAINT id_autora_fk REFERENCES autorzy(id_autora)
);



INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Wzorce Projektowe', 2019, 1, 12); 
INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Dziady cz. II', 2003, 1, 2); 
INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Potop', 2004, 1, 8); 
INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Ogniem i mieczem', 2019, 1, 8); 
INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Dziady cz. I', 2002, 1, 2); 
INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Potop', 2004, 1, 8);
INSERT INTO ksiazki VALUES (
   ksiazki_seq.nextval, 'Metody sztucznej inteligencji', 2017, 1, 13);

CREATE TABLE wypozyczenia(
	id_wypozyczenia INT
		CONSTRAINT id_wypozyczenia_pk PRIMARY KEY
		CONSTRAINT id_wypozyczenia_nn NOT NULL,
	id_czytelnika VARCHAR2(4) CONSTRAINT id_czyt_wyp_fk REFERENCES czytelnicy(id_czytelnika),
	id_ksiazki INT CONSTRAINT id_ksiazka_wyp_fk REFERENCES ksiazki(id_ksiazki),
	data_wypozyczenia DATE,
	id_pracownika INT CONSTRAINT id_prac_wyp_fk REFERENCES pracownicy(id_pracownika)
);

CREATE TABLE zwroty(
	id_zwrotu INT 
		CONSTRAINT id_zwrotu_pk PRIMARY KEY,
	id_wypozyczenia INT CONSTRAINT id_czyt_zwrot_fk REFERENCES czytelnicy(id_czytelnika,
	id_pracownika INT CONSTRAINT id_prac_zwrot_fk REFERENCES pracownicy(id_pracownika),
	data_zwrotu DATE
);
