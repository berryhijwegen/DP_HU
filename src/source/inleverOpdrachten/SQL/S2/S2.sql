-- 1
  -- 1.1
SELECT code, omschrijving
FROM cursussen
WHERE LENGTE = 4;

  -- 1.2
SELECT *
FROM medewerkers
ORDER BY functie ASC, EXTRACT(YEAR FROM GBDATUM);

  -- 1.3
SELECT cursus
FROM uitvoeringen
WHERE locatie = 'UTRECHT' OR locatie = 'MAASTRICHT';

  -- 1.4
SELECT cursist
FROM inschrijvingen
WHERE cursist IN (SELECT cursist
                  FROM inschrijvingen
                  WHERE cursus = 'XML')
AND cursus = 'JAV';

  -- 1.5
SELECT naam, voorl
FROM medewerkers
WHERE NOT (naam = 'JANSEN' AND voorl = 'R');

-- 2
INSERT INTO uitvoeringen
VALUES ('S02', TO_DATE('13-03-2019'), 7369,'LEERDAM');

-- 3
INSERT INTO medewerkers
VALUES (1257,'OSSEYRAN', 'Z', 'STAGAIR', 7566, TODATE('01-01-1998'), 400.00, 100.00, 30);

INSERT INTO medewerkers
VALUES (1258,'OUWEKERK', 'B', 'STAGAIR', 7566, TODATE('01-01-2000'), 400.00, 100.00, 30);

-- 4
UPDATE schalen
SET snr = 6, ondergrens = 4001.00
WHERE snr = 5;

INSERT INTO schalen
VALUES (5, 3001.00, 4000.00, 300.00);

-- 5
INSERT INTO cursussen
VALUES ('DAP', 'Dataprocessing', 'BLD', 3);

INSERT INTO uitvoeringen
VALUES ('DAP', TO_DATE('01-01-2019'), 7566, 'UTRECHT');
INSERT INTO uitvoeringen
VALUES ('DAP', TO_DATE('01-07-2019'), 7566, 'DE MEERN');

INSERT INTO inschrijvingen (cursist, cursus, begindatum)
VALUES (7900, 'DAP', TO_DATE('01-01-2019'));

INSERT INTO inschrijvingen (cursist, cursus, begindatum)
VALUES (7782, 'DAP', TO_DATE('01-07-2019'));

-- 6
UPDATE medewerkers
SET maandsal = maandsal * 1.055
WHERE afd = 30 AND functie != 'MANAGER';

UPDATE medewerkers
SET maandsal = maandsal * 1.07
WHERE afd = 30 AND functie = 'MANAGER';

-- 7
    -- Dit lukt niet omdat de id's van deze werknemers dienen
    -- als foreign key in andere tabellen.

-- 8
INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal,afd)
VALUES (7420, 'HIJWEGEN', 'B', 'MANAGER', 7839, TO_DATE('13-04-2001'), 2200.00, 50);

INSERT INTO afdelingen
VALUES (50, 'FINANCIËN', 'LEERDAM', 7420);

