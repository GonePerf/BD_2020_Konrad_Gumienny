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
   '0002', 'Kamil', 'Mak', '293052530712', to_date(value1,'25.05.1993'));
INSERT INTO czytelnicy VALUES (
   '0003', 'Agata', 'Kowalska', 84101030712, '84-10-10');
INSERT INTO czytelnicy VALUES (
   '0004', 'Julka', 'Jonarska', 79101030712, '79-10-10');
INSERT INTO czytelnicy VALUES (
   '0005', 'Maja', 'Marek', 88031330712, '88-03-13');
INSERT INTO czytelnicy VALUES (
   '0006', 'Dawid', 'Nowak', 99120930712, '99-12-09');
INSERT INTO czytelnicy VALUES (
   '0007', 'Marek', 'Kowalski', 92111030712, '92-11-10');
INSERT INTO czytelnicy VALUES (
   '0008', 'Magdalena', 'Nowak', 97060330712, '97-06-03');
INSERT INTO czytelnicy VALUES (
   '0009', 'Agata', 'Janiga', 98101530712, '98-10-15');
INSERT INTO czytelnicy VALUES (
   '0010', 'Bartosz', 'Trybinski', 85120630712, '85-12-06');
INSERT INTO czytelnicy VALUES (
   '0011', 'Dawid', 'Marek', 94021030712, '94-02-10');


CREATE TABLE autorzy(
    id_autora INT PRIMARY KEY,
    imie VARCHAR2(15) NOT NULL,
    nazwisko VARCHAR2(30)
);

INSERT INTO autorzy VALUES (
   '0001', 'Juliusz', 'Słowacki');
INSERT INTO autorzy VALUES (
   '0002', 'Adam', 'Mickiewicz');
INSERT INTO autorzy VALUES (
   '0003', 'Wiliam', 'Szekspir');
INSERT INTO autorzy VALUES (
   '0004', 'Zofia', 'Nałkowska');
INSERT INTO autorzy VALUES (
   '0005', 'Joseph', 'Conrad');
INSERT INTO autorzy VALUES (
   '0006', 'Bolesław', 'Prus');
INSERT INTO autorzy VALUES (
   '0007', 'Andrzej', 'Sapkowski');
INSERT INTO autorzy VALUES (
   '0008', 'Henryk', 'Sienkiewicz');
INSERT INTO autorzy VALUES (
   '0009', 'Maria', 'Konopnicka');
INSERT INTO autorzy VALUES (
   '0010', 'Ernest', 'Hemingway');
INSERT INTO autorzy VALUES (
   '0011', 'Stanisław', 'Wyspiański');

CREATE TABLE pracownicy(
    id_pracownika INT PRIMARY KEY,
    imie VARCHAR2(15) NOT NULL,
    nazwisko VARCHAR2(25) NOT NULL,
    PESEL INT(11) NOT NULL UNIQUE,
);   

INSERT INTO pracownicy VALUES (
   '0001', 'Jan', 'Brączyk', 92021030712);
INSERT INTO pracownicy VALUES (
   '0002', 'Dominik', 'Mastej', 91061030712);
INSERT INTO pracownicy VALUES (
   '0003', 'Krzysztof', 'Bożek', 87121030712);
INSERT INTO pracownicy VALUES (
   '0004', 'Jadwiga', 'Bal', 86021730712);
INSERT INTO pracownicy VALUES (
   '0005', 'Krzysztof', 'Buba', 97051030712);

CREATE TABLE ksiazki(
	id_ksiazki INT PRIMARY KEY,
	tytul VARCHAR2(100),
	rok_wydania INT(4),
	dostepnosc BOOLEAN,
	id_autora INT CONSTRAINT FK_autor REFERENCES autorzy(id_autora),
); 

CREATE TABLE wypozyczenia(
	id_wypozyczenia INT PRIMARY KEY,
	id_czytelnika INT CONSTRAINT FK_czytelnik_wypozyczenie REFERENCES czytelnicy(id_czytelnika,
	id_ksiazki INT CONSTRAINT FK_ksiazka_wypozyczenie REFERENCES ksiazki(id_ksiazka),
	data_wypozyczenia DATE,
	id_pracownika INT CONSTRAINT FK_pracownik_wypozyczenie REFERENCES pracownicy(id_pracownika),
	data_oddania DATE
);

CREATE TABLE zwroty(
	id_zwrotu INT PRIMARY KEY,
	id_wypozyczenia INT CONSTRAINT FK_czytelnik_zwrot REFERENCES czytelnicy(id_czytelnika,
	id_pracownika INT CONSTRAINT FK_pracownik_zwrot REFERENCES pracownicy(id_pracownika),
	data_zwrotu DATE
);
