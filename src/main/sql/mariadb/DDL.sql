--
--   auteurs : Luc Anne / ENI
--   email : lucanne.t@gmx.fr
--
--   description : script de création des tables de la base de données ENIVAE
--   langage : sql/mariaDB
--
USE ENIVAE

CREATE TABLE CATEGORIES (
    no_categorie   INT NOT NULL AUTO_INCREMENT,
    libelle        VARCHAR(30) NOT NULL,
    PRIMARY KEY (no_categorie)
);

CREATE TABLE ENCHERES (
    no_utilisateur   INT NOT NULL,
    no_article       INT NOT NULL,
    date_enchere     DATETIME NOT NULL,
    montant_enchere  INT NOT NULL,
    PRIMARY KEY (no_article)
);

CREATE TABLE RETRAITS (
    no_article       INT NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    PRIMARY KEY (no_article)
);

CREATE TABLE UTILISATEURS (
    no_utilisateur   INT NOT NULL AUTO_INCREMENT,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(20) NOT NULL,
    telephone        VARCHAR(15) NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INT NOT NULL,
    administrateur   BOOLEAN NOT NULL,
    PRIMARY KEY (no_utilisateur),
    CONSTRAINT UNIQUE (pseudo),
    CONSTRAINT UNIQUE (email)
);

CREATE TABLE ARTICLES (
    no_article          INT NOT NULL AUTO_INCREMENT,
    nom_article         VARCHAR(30) NOT NULL,
    description         VARCHAR(300) NOT NULL,
    date_debut_enchere  DATETIME NOT NULL,
    date_fin_enchere    DATETIME NOT NULL,
    prix_initial        INT NULL,
    prix_vente          INT NULL,
    no_utilisateur      INT NOT NULL,
    no_categorie        INT NOT NULL,
    etat_vente          CHAR(2) NOT NULL DEFAULT 'CR',
    image                 VARCHAR(150) NULL,
    PRIMARY KEY (no_article),
    CONSTRAINT ck_articles_etat_vente CHECK (etat_vente IN ('CR','EC','VD','RT'))
);

--MISE EN PLACE DE L'INTEGRITE REFERENTIELLE
ALTER TABLE ARTICLES
    ADD CONSTRAINT fk_articles_utilisateurs FOREIGN KEY ( no_utilisateur )
    REFERENCES UTILISATEURS ( no_utilisateur )
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION
;
    
ALTER TABLE ARTICLES
    ADD CONSTRAINT fk_articles_vendus_categories FOREIGN KEY ( no_categorie )
        REFERENCES CATEGORIES ( no_categorie )
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION
;
    
ALTER TABLE ENCHERES
    ADD CONSTRAINT fk_encheres_articles_vendus FOREIGN KEY ( no_article )
        REFERENCES ARTICLES ( no_article )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
;
    
ALTER TABLE ENCHERES
    ADD CONSTRAINT fk_ventes_utilisateurs FOREIGN KEY ( no_utilisateur )
        REFERENCES UTILISATEURS ( no_utilisateur )
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION
;

ALTER TABLE RETRAITS
    ADD CONSTRAINT fk_retraits_articles_vendus FOREIGN KEY ( no_article )
        REFERENCES ARTICLES ( no_article )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
;
