-- 1
    -- 4.4
-- Select all employees who attended the Java AND the XML course
SELECT MNR FROM MEDEWERKERS
WHERE MNR IN (
    SELECT CURSIST
    FROM INSCHRIJVINGEN
    WHERE CURSUS = 'JAV'
    ) AND MNR IN (
    SELECT CURSIST
    FROM INSCHRIJVINGEN
    WHERE CURSUS = 'XML'
);

    -- 4.7
-- Select all employees who are not in the courses department
SELECT MNR
FROM MEDEWERKERS
WHERE AFD NOT IN (SELECT ANR
                  FROM AFDELINGEN
                  WHERE naam = 'Opleidingen');


    -- 4.8
-- Select all employees who did not attend the Java course
SELECT MNR
FROM MEDEWERKERS
WHERE MNR NOT IN (SELECT CURSIST
                  FROM INSCHRIJVINGEN
                  WHERE CURSUS = 'JAV');

    -- 4.10
-- Select all employees who are chief of at least one employee
SELECT DISTINCT CHEF
FROM MEDEWERKERS
WHERE CHEF IS NOT NULL;

-- Select all employees who aren't the chief of anyone
SELECT MNR
FROM MEDEWERKERS
WHERE MNR NOT IN (SELECT DISTINCT CHEF
                  FROM MEDEWERKERS
                  WHERE CHEF IS NOT NULL);

    -- 4.11
-- Select all versions of courses with type 'ALG' In the year 1999
SELECT *
FROM UITVOERINGEN
WHERE CURSUS IN (SELECT CODE
                 FROM CURSUSSEN
                 WHERE type= 'ALG')
AND BEGINDATUM BETWEEN TO_DATE ('1999/01/01', 'yyyy/mm/dd')
                       AND TO_DATE ('2000/01/01', 'yyyy/mm/dd');

-- 2
    -- 8.4
-- Select all versions of all courses and registrations per version
SELECT u.CURSUS, u.BEGINDATUM, count(*) aantal
FROM UITVOERINGEN u, INSCHRIJVINGEN i
WHERE u.CURSUS = i.CURSUS
AND u.BEGINDATUM = i.BEGINDATUM
GROUP BY u.cursus, u.BEGINDATUM
ORDER BY BEGINDATUM;

    -- 8.9
-- Select name and initials from employees who gave a course to their own chief
SELECT DISTINCT m.naam, m.voorl
FROM MEDEWERKERS m, uitvoeringen u, inschrijvingen i
WHERE u.docent = m.mnr
AND u.begindatum = i.begindatum
AND u.cursus = i.cursus
AND u.cursus IN (SELECT CODE
                 FROM CURSUSSEN
                 WHERE type= 'ALG')
AND m.chef IN (SELECT cursist
               FROM inschrijvingen
               WHERE cursus = u.cursus
               AND begindatum = u.begindatum);

-- 3
    -- 9.3
-- Select all employees who never gave a course
SELECT *
FROM MEDEWERKERS
WHERE MNR NOT IN (SELECT DISTINCT DOCENT
                  FROM UITVOERINGEN
                  WHERE DOCENT IS NOT NULL);