-- 1.1
-- Overzicht cursusuitvoeringen
SELECT u.cursus, u.begindatum, m.naam cursist, c.lengte
FROM UITVOERINGEN U, MEDEWERKERS M, CURSUSSEN C
WHERE u.docent = m.mnr
  AND c.code = u.cursus;

-- 1.2
-- Cursisten van S02 met zijn docent
SELECT m.naam cursist, d.naam docent
FROM medewerkers m, medewerkers d, inschrijvingen i, uitvoeringen u
WHERE i.cursus = 'S02'
  AND i.cursus = u.cursus AND i.begindatum = u.begindatum
  AND i.cursist = m.mnr
  AND u.docent = d.mnr;

-- 2
-- Afdeling + afdelingshoofd
SELECT a.naam afdeling, m.naam hoofd
FROM afdelingen a, medewerkers m
WHERE a.hoofd = m.mnr;

-- 3
-- Alle medewerkers met hun afdeling en locatie
SELECT m.naam, a.naam afdeling, a.locatie
FROM medewerkers m, afdelingen a
WHERE m.afd = a.anr;

-- 4
-- Alle cursisten van uitvoering 12-04-1999 S02.
SELECT m.naam
FROM medewerkers m, inschrijvingen i
WHERE i.cursus = 'S02' AND i.begindatum = TO_DATE('1999-04-12','YYYY-MM-DD')
  AND m.mnr = i.cursist;

-- 5
-- Alle medewerkers met hun toelages
SELECT m.naam, s.toelage
FROM medewerkers m, schalen s
WHERE m.maandsal > s.ondergrens AND m.maandsal < s.bovengrens;