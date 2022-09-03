--
--   auteurs : Luc Anne / ENI
--   email : lucanne.t@gmx.fr
--
--   description : script de création des données de tests pour le site web ENIVAE
--   langage : sql/mariaDB
--
USE ENIVAE

DELETE FROM ENCHERES;
DELETE FROM RETRAITS;
DELETE FROM ARTICLES;
DELETE FROM CATEGORIES;
DELETE FROM UTILISATEURS;
TRUNCATE TABLE ENCHERES;
TRUNCATE TABLE RETRAITS;
TRUNCATE TABLE ARTICLES;
TRUNCATE TABLE CATEGORIES;
TRUNCATE TABLE UTILISATEURS;


INSERT INTO CATEGORIES (no_categorie, libelle)
VALUES (1, 'Informatique');
INSERT INTO CATEGORIES (no_categorie, libelle)
VALUES (2, 'Ameublement');
INSERT INTO CATEGORIES (no_categorie, libelle)
VALUES (3, 'Vêtement');
INSERT INTO CATEGORIES (no_categorie, libelle)
VALUES (4, 'Sport & Loisir');


INSERT INTO UTILISATEURS (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES (1, 'PSEUDO_UTILISATEUR', 'nom_UTILISATEUR', 'Luisa', 'u@u', '', '9 rue de l''océan', '85000', 'Les Sables d''Olonnes', '', 0, 0);
INSERT INTO UTILISATEURS (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES (2, 'PSEUDO_ADMINISTRATEUR', 'nom_ADMINISTRATEUR', 'Laure', 'a@a', '0202020202', 'rue de Nantes', '44000', 'Nantes', '', 0, 1);
INSERT INTO UTILISATEURS (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES (3, 'D_HERVE', 'DURAND', 'Hervé', 'herve_D@campus.fr', '0202020202', '7 rue de Nantes', '44000', 'Nantes', '12345678', 2000, 0);
INSERT INTO UTILISATEURS (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES (4, 'M_QUENTIN', 'MARTI', 'Quentin', 'quentin_M@campus.fr', '0202020202', '10 boulevard des Oliviers', '44000', 'Nantes', '12345678', 3000, 0);

-- Présentation
-- Articles de D_HERVE (tous les cas possibles d'articles)
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (1, 'D_HERVE_CR_ench_personne', 'article_D_HERVE_CR_encherisseur_personne', CURRENT_DATE + INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 5 DAY, 25, 0, 3, 1, 'CR', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (2, 'D_HERVE_EC_ench_personne', 'article_D_HERVE_EC_encherisseur_personne', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 3 DAY, 25, 0, 3, 1, 'EC', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (3, 'D_HERVE_VD_ench_personne', 'article_D_HERVE_VD_encherisseur_personne', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE - INTERVAL 1 DAY, 25, 0, 3, 1, 'VD', null);
-- IMPOSSIBLE D'AVOIR UN ETAT RETRAIT POU UN ARTICLE QUI N'A PAS EU D'ENCHERISSEUR
-- IMPOSSIBLE D'AVOIR UN ENCHERISSEUR POUR UN ARTICLE EN ETAT CR
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (4 , 'D_HERVE_EC_ench_M_QUENTIN', 'article_D_HERVE_EC_encherisseur_M_QUENTIN', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 3 DAY, 25, 0, 3, 2, 'EC', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (5 , 'D_HERVE_VD_ench_M_QUENTIN', 'article_D_HERVE_VD_encherisseur_M_QUENTIN', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE - INTERVAL 1 DAY, 25, 50, 3, 2, 'VD', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (6, 'D_HERVE_RT_ench_M_QUENTIN', 'article_D_HERVE_RT_encherisseur_M_QUENTIN', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE - INTERVAL 1 DAY, 25, 65, 3, 2, 'RT', null);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (4, 4, CURRENT_DATE - INTERVAL 2 DAY, 50);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (4, 5, CURRENT_DATE - INTERVAL 2 DAY, 50);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (4, 6, CURRENT_DATE - INTERVAL 2 DAY, 50);

-- Articles de M_QUENTIN (tous les cas possibles d'articles)
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (7, 'M_QUENTIN_CR_ench_personne', 'article_M_QUENTIN_CR_encherisseur_personne', CURRENT_DATE + INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 5 DAY, 25, 0, 4, 1, 'CR', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (8, 'M_QUENTIN_EC_ench_personne', 'article_M_QUENTIN_EC_encherisseur_personne', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 3 DAY, 25, 0, 4, 1, 'EC', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (9, 'M_QUENTIN_VD_ench_personne', 'article_M_QUENTIN_VD_encherisseur_personne', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE - INTERVAL 1 DAY, 25, 0, 4, 1, 'VD', null);
-- IMPOSSIBLE D'AVOIR UN ETAT RETRAIT POU UN ARTICLE QUI N'A PAS EU D'ENCHERISSEUR
-- IMPOSSIBLE D'AVOIR UN ENCHERISSEUR POUR UN ARTICLE EN ETAT CR
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (10, 'M_QUENTIN_EC_ench_D_HERVE', 'article_M_QUENTIN_EC_encherisseur_D_HERVE', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 3 DAY, 25, 0, 4, 2, 'EC', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (11, 'M_QUENTIN_VD_ench_D_HERVE', 'article_M_QUENTIN_VD_encherisseur_D_HERVE', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE - INTERVAL 1 DAY, 25, 50, 4, 2, 'VD', null);
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (12, 'M_QUENTIN_RT_ench_D_HERVE', 'article_M_QUENTIN_RT_encherisseur_D_HERVE', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE - INTERVAL 1 DAY, 25, 65, 4, 2, 'RT', null);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (3, 10, CURRENT_DATE - INTERVAL 2 DAY, 50);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (3, 11, CURRENT_DATE - INTERVAL 2 DAY, 50);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (3, 12, CURRENT_DATE - INTERVAL 2 DAY, 65);

-- Articles robe
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (13, 'robe', 'robe d''été', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 3 DAY, 20, 0, 3, 3, 'EC', null);

-- Article pour enchérir sur quelqu'un
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (14, 'PC ACER', 'article_M_QUENTIN_RT_encherisseur_D_HERVE', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 3 DAY, 25, 0, 4, 1, 'EC', null);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (1, 14, CURRENT_DATE - INTERVAL 2 DAY, 100);

-- Test procédure stockée
-- Article CR to EC
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (15, 'D_HERVE_CR_ench_personne', 'article_D_HERVE_CR_encherisseur_personne', CURRENT_DATE - INTERVAL 3 DAY, CURRENT_DATE + INTERVAL 5 DAY, 25, 0, 3, 1, 'CR', null);
-- Article EC to VD
INSERT INTO ARTICLES(no_article, nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image)
VALUES (16, 'D_HERVE_CR_ench_personne', 'article_D_HERVE_CR_encherisseur_personne', CURRENT_DATE - INTERVAL 5 DAY, CURRENT_DATE - INTERVAL 2 DAY, 25, 0, 3, 1, 'EC', null);
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES (4, 16, CURRENT_DATE - INTERVAL 3 DAY, 50);
