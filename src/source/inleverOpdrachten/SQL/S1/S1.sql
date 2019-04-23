-- 1
CREATE TABLE COPY_MEDEWERKERS AS SELECT * FROM MEDEWERKERS; 

ALTER TABLE COPY_MEDEWERKERS
ADD GESLACHT VARCHAR2(1);

ALTER TABLE COPY_MEDEWERKERS
ADD CHECK (GESLACHT='M' OR GESLACHT='V');

INSERT INTO COPY_MEDEWERKERS (MNR, NAAM, VOORL, FUNCTIE, CHEF, GBDATUM, MAANDSAL, AFD, GESLACHT)
VALUES (6705, 'PAULSEN', 'K', 'TRAINER', '7698', TO_DATE('02-03-1999'), 2000, 20, 'X'); -- Geeft error

INSERT INTO COPY_MEDEWERKERS (MNR, NAAM, VOORL, FUNCTIE, CHEF, GBDATUM, MAANDSAL, AFD, GESLACHT)
VALUES (6705, 'PAULSEN', 'K', 'TRAINER', '7698', TO_DATE('02-03-1999'), 2000, 20, 'M'); -- Geeft geen error

-- 2
CREATE SEQUENCE seq_afdelingsnummer
MINVALUE 40
START WITH 40
INCREMENT BY 10
CACHE 10;

INSERT INTO AFDELINGEN (ANR, NAAM, LOCATIE, HOOFD)
VALUES (seq_afdelingsnummer.nextval,'MARKETING','AMSTERDAM', 7839);

INSERT INTO AFDELINGEN (ANR, NAAM, LOCATIE, HOOFD)
VALUES (seq_afdelingsnummer.nextval,'SALES','UTRECHT', 5000);

INSERT INTO AFDELINGEN (ANR, NAAM, LOCATIE, HOOFD)
VALUES (seq_afdelingsnummer.nextval,'ICT','AMSTERDAM', 7839);

ALTER TABLE AFDELINGEN
MODIFY ANR NUMBER(3,0);

-- 3
DROP TABLE ADRESSEN;
CREATE TABLE ADRESSEN (
    POSTCODE VARCHAR(6),
    HUISNUMMER INTEGER,
    INGANGSDATUM DATE NOT NULL,
    EINDDATUM DATE,
    TELEFOON CHAR(10),
    MED_MNR NUMBER(4,0) NOT NULL,
    CONSTRAINT ADRESSEN_PK PRIMARY KEY (POSTCODE, HUISNUMMER, INGANGSDATUM),
    CONSTRAINT TELEFOON_UNIEK UNIQUE (TELEFOON),
    CONSTRAINT CHK_Einddatum CHECK (INGANGSDATUM < EINDDATUM),
    CONSTRAINT FK_MNR FOREIGN KEY (MED_MNR) REFERENCES MEDEWERKERS(MNR)
);

-- 4
CHECK ((FUNCTIE = 'VERKOPER' and COMM is not null) or ((not FUNCTIE = 'VERKOPER') and comm is null));