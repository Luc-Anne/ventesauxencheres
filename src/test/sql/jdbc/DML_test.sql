USE [Projet_VentesAuxEncheres]
GO

DELETE FROM ENCHERES;
DELETE FROM  RETRAITS;
DELETE FROM  ARTICLES_VENDUS;
DELETE FROM  CATEGORIES;
DELETE FROM  UTILISATEURS
TRUNCATE TABLE ENCHERES;
TRUNCATE TABLE RETRAITS;
TRUNCATE TABLE ARTICLES_VENDUS;
TRUNCATE TABLE CATEGORIES;
TRUNCATE TABLE UTILISATEURS;
GO

SET IDENTITY_INSERT [dbo].[CATEGORIES] ON 

INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (1, N'Informatique')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (2, N'Ameublement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (3, N'Vêtement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (4, N'Sport & Loisir')
SET IDENTITY_INSERT [dbo].[CATEGORIES] OFF
SET IDENTITY_INSERT [dbo].[UTILISATEURS] ON

INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (1, N'admin', N'Bluth', N'Georges', N'gbluth@campus.fr', N'0241468598', N'16 av du monde', N'49000', N'Angers', N'Pa$$w0rd', 0, 1)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (2, N'felix', N'Bluth', N'Félix', N'fbluth@campus.fr', N'0241461708', N'11 rue du chêne vert', N'85000', N'La Roche/Yon', N'Pa$$w0rd', 100, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (3, N'tom', N'Bluth', N'Tom', N'tbluth@campus.fr', N'0695020202', N'5 rue de la mer', N'44000', N'Nantes', N'Pa$$w0rd', 65, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (4, N'samuel', N'Bluth', N'Samuel', N'sbluth@campus.fr', N'0788030303', N'9 chemin des gites', N'44000', N'Nantes', N'Pa$$w0rd', 260, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (5, N'luisa', N'Bluth', N'Luisa', N'lbluth@campus.fr', N'', N'9 rue de l''océan', N'85000', N'Les Sables d''Olonnes', N'Pa$$w0rd', 0, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (6, N'Laure', N'Bluth', N'Laure', N'laure@campus.fr', N'0202020202', N'rue de Nantes', N'44000', N'Nantes', N'12345678', 0, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (7, N'PSEUDO_UTILISATEUR', N'nom_UTILISATEUR', N'Luisa', N'u@u', N'', N'9 rue de l''océan', N'85000', N'Les Sables d''Olonnes', N'', 0, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (8, N'PSEUDO_ADMINISTRATEUR', N'nom_ADMINISTRATEUR', N'Laure', N'a@a', N'0202020202', N'rue de Nantes', N'44000', N'Nantes', N'', 0, 1)
-- AJOUT test présentation
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (9, N'D_HERVE', N'DURAND', N'Hervé', N'herve_D@campus.fr', N'0202020202', N'7 rue de Nantes', N'44000', N'Nantes', N'12345678', 2000, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (10, N'M_QUENTIN', N'MARTIN', N'Quentin', N'quentin_M@campus.fr', N'0202020202', N' 10 boulevard des Oliviers', N'44000', N'Nantes', N'12345678', 3000, 0)
SET IDENTITY_INSERT [dbo].[UTILISATEURS] OFF

--DES ARTICLES
--un article enchère en cours et une enchère réalisée

SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] ON
/*
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (1
		   ,'article 1'
           ,'description article 1'
           ,getdate()
           ,getdate() + 1
           ,10
           ,null
           ,2
           ,1
           ,'EC'
           ,null);
--un article enchère non démarrée
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (2
		   ,'article 2'
           ,'description article 2'
           ,getdate() + 1
           ,getdate() + 3
           ,5
           ,null
           ,3
           ,2
           ,DEFAULT
           ,null);
--un article enchère en cours et aucune enchère réalisée
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (3
		   ,'article 3'
           ,'description article 3'
           ,getdate()
           ,getdate() + 3
           ,15
           ,null
           ,3
           ,2
           ,'EC'
           ,null);
--un article vendu enchère terminée et une enchère liée
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (4
		   ,'article 4'
           ,'description article 4'
           ,getdate() - 3
           ,getdate() - 1
           ,25
           ,50
           ,3
           ,2
           ,'VD'
           ,null);
*/





----
----
----
----
----
----
----




-- AJOUT test présentation
-- Articles de D_HERVE (les 7 cas de figures)
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (5, 'D_HERVE_CR_ench_personne', 'article_D_HERVE_CR_encherisseur_personne', getdate() + 3, getdate() + 5, 25, 0, 9, 1, 'CR', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (6, 'D_HERVE_EC_ench_personne', 'article_D_HERVE_EC_encherisseur_personne', getdate() - 3, getdate() + 3, 25, 0, 9, 1, 'EC', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (7, 'D_HERVE_VD_ench_personne', 'article_D_HERVE_VD_encherisseur_personne', getdate() - 3, getdate() - 1, 25, 0, 9, 1, 'VD', null);
-- IMPOSSIBLE D'AVOIR UN ETAT RETRAIT POU UN ARTICLE QUI N'A PAS EU D'ENCHERISSEUR
--INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
--     VALUES (8, 'D_HERVE_RT_ench_personne', 'article_D_HERVE_RT_encherisseur_personne', getdate() - 3, getdate() - 1, 25, 0, 9, 1, 'RT', null);
-- IMPOSSIBLE D'AVOIR UN ENCHERISSEUR POUR UN ARTICLE EN ETAT CR
--INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
--     VALUES (9, 'article 9', 'article_D_HERVE_CR_encherisseur_M_QUENTIN', getdate() - 3, getdate() - 1, 25, 50, 9, 2, 'VD', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (8 , 'D_HERVE_EC_ench_M_QUENTIN', 'article_D_HERVE_EC_encherisseur_M_QUENTIN', getdate() - 3, getdate() + 3, 25, 0, 9, 2, 'EC', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (9 , 'D_HERVE_VD_ench_M_QUENTIN', 'article_D_HERVE_VD_encherisseur_M_QUENTIN', getdate() - 3, getdate() - 1, 25, 50, 9, 2, 'VD', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (10, 'D_HERVE_RT_ench_M_QUENTIN', 'article_D_HERVE_RT_encherisseur_M_QUENTIN', getdate() - 3, getdate() - 1, 25, 65, 9, 2, 'RT', null);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (10 ,8  ,getdate() - 2 ,50);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (10 ,9  ,getdate() - 2 ,50);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (10 ,10 ,getdate() - 2 ,65);

-- Articles de M_QUENTIN (les 7 cas de figures)
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (11, 'M_QUENTIN_CR_ench_personne', 'article_M_QUENTIN_CR_encherisseur_personne', getdate() + 3, getdate() + 5, 25, 0, 10, 1, 'CR', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (12, 'M_QUENTIN_EC_ench_personne', 'article_M_QUENTIN_EC_encherisseur_personne', getdate() - 3, getdate() + 3, 25, 0, 10, 1, 'EC', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (13, 'M_QUENTIN_VD_ench_personne', 'article_M_QUENTIN_VD_encherisseur_personne', getdate() - 3, getdate() - 1, 25, 0, 10, 1, 'VD', null);
-- IMPOSSIBLE D'AVOIR UN ETAT RETRAIT POU UN ARTICLE QUI N'A PAS EU D'ENCHERISSEUR
--INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
--     VALUES (15, 'M_QUENTIN_RT_ench_personne', 'article_M_QUENTIN_RT_encherisseur_personne', getdate() - 3, getdate() - 1, 25, 0, 10, 1, 'RT', null);
-- IMPOSSIBLE D'AVOIR UN ENCHERISSEUR POUR UN ARTICLE EN ETAT CR
--INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
--     VALUES (9, 'article 9', 'article_D_HERVE_CR_encherisseur_M_QUENTIN', getdate() - 3, getdate() - 1, 25, 50, 10, 2, 'VD', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (14, 'M_QUENTIN_EC_ench_D_HERVE', 'article_M_QUENTIN_EC_encherisseur_D_HERVE', getdate() - 3, getdate() + 3, 25, 0, 10, 2, 'EC', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (15, 'M_QUENTIN_VD_ench_D_HERVE', 'article_M_QUENTIN_VD_encherisseur_D_HERVE', getdate() - 3, getdate() - 1, 25, 50, 10, 2, 'VD', null);
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (16, 'M_QUENTIN_RT_ench_D_HERVE', 'article_M_QUENTIN_RT_encherisseur_D_HERVE', getdate() - 3, getdate() - 1, 25, 65, 10, 2, 'RT', null);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (9 ,14 ,getdate() - 2 ,50);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (9 ,15 ,getdate() - 2 ,50);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (9 ,16 ,getdate() - 2 ,65);

-- Articles robe
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (17, 'robe', 'robe d''été', getdate() - 3, getdate() + 3, 20, 0, 9, 3, 'EC', null);

-- Article pour enchérir sur quelqu'un
INSERT INTO [dbo].[ARTICLES_VENDUS]([no_article],[nom_article],[description],[date_debut_enchere],[date_fin_enchere],[prix_initial],[prix_vente],[no_utilisateur],[no_categorie],[etat_vente],[image])
     VALUES (18, 'PC ACER', 'article_M_QUENTIN_RT_encherisseur_D_HERVE', getdate() - 3, getdate() + 3, 25, 0, 10, 1, 'EC', null);
INSERT INTO [dbo].[ENCHERES] ([no_utilisateur] ,[no_article] ,[date_enchere] ,[montant_enchere])
					  VALUES (6 ,18 ,getdate() - 2 ,100);
----
----
----
----
----
----
----



SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] OFF
--DES ENCHERES
/*
INSERT INTO [dbo].[ENCHERES]
           ([no_utilisateur]
           ,[no_article]
           ,[date_enchere]
           ,[montant_enchere])
     VALUES
           (4
           ,4
           ,getdate() - 2
           ,65);
INSERT INTO [dbo].[ENCHERES]
           ([no_utilisateur]
           ,[no_article]
           ,[date_enchere]
           ,[montant_enchere])
     VALUES
           (4
           ,1
           ,getdate()
           ,20);
*/
GO