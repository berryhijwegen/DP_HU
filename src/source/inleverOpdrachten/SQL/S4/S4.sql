-- 1
    -- 4.6
SELECT mnr, functie, gbdatum
FROM medewerkers
WHERE GBDATUM < TO_DATE('01 Jan 1960', 'DD MON YYYY') AND
    (FUNCTIE = 'TRAINER' OR FUNCTIE = 'VERKOPER');

    -- 4.9
SELECT *
FROM medewerkers
WHERE NAAM LIKE '% %';

-- 2
    -- 8.5
SELECT cursus, begindatum, COUNT(*) aantal
FROM inschrijvingen
WHERE begindatum BETWEEN TO_DATE( '01 Jan 1999', 'DD MON YYYY' ) AND TO_DATE( '31 Dec 1999', 'DD MON YYYY' )
GROUP BY cursus, begindatum
HAVING COUNT(*) >= 3;

    -- 8.7
SELECT cursist, cursus, COUNT(*) aantal
FROM inschrijvingen
GROUP BY cursist, cursus
HAVING COUNT(*) > 1;

-- 3
SELECT cursus, COUNT(*)
FROM uitvoeringen
GROUP BY cursus
ORDER BY cursus;

-- 4
SELECT FLOOR((MAX(GBDATUM) - MIN(GBDATUM)) / 365)
FROM medewerkers;

SELECT AVG(FLOOR((sysdate - gbdatum) / 365))
FROM medewerkers;

-- 5

SELECT COUNT(*) aantal, AVG(comm) avgCommissie
FROM medewerkers;

SELECT AVG(comm) avgCommissieVerkoper
FROM medewerkers
WHERE functie = 'VERKOPER';


