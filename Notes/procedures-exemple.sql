USE requetejpa;

DELIMITER $
CREATE PROCEDURE addition(IN a INT, IN b INT, OUT somme INT) 
BEGIN
	SET somme = a + b;
END $
DELIMITER ;

CALL addition(1, 2, @resultat) ;
SELECT @resultat;

DELIMITER $
CREATE PROCEDURE count_article_by_marque (IN id_marque BIGINT, OUT nb_article INT)
BEGIN
	SELECT COUNT(id) INTO nb_article FROM articles a WHERE a.fk_marque = id_marque;
END $

DELIMITER ;

CALL count_article_by_marque(2, @resultat);
SELECT @resultat;
