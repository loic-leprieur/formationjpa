use  requetejpa;

INSERT INTO fournisseurs ( nom,version) VALUES
('Fournisseur 1',0),
('Fournisseur 2',0),
( 'Fournisseur 3',0),
('Fournisseur 4',0);

INSERT INTO marques (nom, version,date_creation) VALUES
('Marque A', 0,'1982-03-01'),
('Marque B', 0,'1950-09-01'),
('Marque C', 0,'2000-04-01');

INSERT into articles (description, prix, version,fk_marque) VALUES
('Tv 4k',  650, 0, 1),
('SmartPhone Android',  350, 0, 1),
('Clavier multimédia',  19.9, 0, 2),
('Souris gaming', 40, 0, 2),
('Disque SSD 1 GO',  120, 0, 3),
('Carte mère',  140, 0, 2),
('Mémoire DDR4 8 GO', 70, 0, 2),
('Mémoire DDR4 8 GO', 80, 0, 3),
('Câble RJ45 5m', 20, 0, 1),
('Câble HDMI', 19, 0, 1);

INSERT INTO fournisseur2article (fk_article, fk_fournisseur) VALUES
(6, 2),
(10, 2),
(2, 1),
(3, 1),
(5, 1),
(5, 4),
(7, 3),
(7, 2),
(1, 1),
(1, 4),
(4, 2);