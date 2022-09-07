--
--   auteurs : Luc Anne / ENI
--   email : lucanne.t@gmx.fr
--
--   description : script de création des tables de la base de données ENIVAE
--   langage : sql/mariaDB
--
USE ENIVAE

DROP TABLE ENCHERE;
DROP TABLE VENTE;
DROP TABLE ARTICLE;
DROP TABLE CATEGORIE;
DROP TABLE PROFIL;
DROP TABLE CLIENT;
DROP TABLE ADRESSE;
DROP TABLE PERMISSION;
DROP TABLE ADMINISTRATEUR;
DROP TABLE DROIT;


--
-- UTIL
--
CREATE TABLE ADRESSE (
    no_adresse INT AUTO_INCREMENT,
    rue VARCHAR(100) NOT NULL,
    code_postal VARCHAR(15) NOT NULL,
    ville VARCHAR(50) NOT NULL,
    pays VARCHAR(50) NOT NULL DEFAULT 'FRANCE',
    PRIMARY KEY (no_adresse)
);

--
-- USERS
--
CREATE TABLE PROFIL (
    no_profil INT NOT NULL,
    pseudo VARCHAR(50) NOT NULL,
    courriel VARCHAR(50) NOT NULL,
    mot_de_passe CHAR(16) NOT NULL,
    date_enregistrement DATETIME NOT NULL DEFAULT NOW(),
    no_client INT,
    no_administrateur INT,
    PRIMARY KEY (no_profil),
    CONSTRAINT un_profil_pseudo UNIQUE (pseudo),
    CONSTRAINT un_profil_courriel UNIQUE (courriel),
    CONSTRAINT ck_profil_courriel CHECK (courriel LIKE '%@%')
);

CREATE TABLE CLIENT (
    no_client INT NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    actif BOOLEAN NOT NULL,
    credit FLOAT NOT NULL,
    no_adresse INT NOT NULL,
    telephone VARCHAR(20),
    PRIMARY KEY (no_client),
    CONSTRAINT ck_client_credit CHECK (credit >= 0)
);

-- integrity constraints
ALTER TABLE CLIENT
    ADD CONSTRAINT fk_client_no_adresse FOREIGN KEY (no_adresse) REFERENCES ADRESSE(no_adresse)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

CREATE TABLE ADMINISTRATEUR (
    no_administrateur INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (no_administrateur)
);

-- inheritance integrity constraints
ALTER TABLE PROFIL
    ADD CONSTRAINT fk_profil_no_client FOREIGN KEY (no_client) REFERENCES CLIENT(no_client)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE PROFIL
    ADD CONSTRAINT un_profil_no_client UNIQUE (no_client);
    
ALTER TABLE PROFIL
    ADD CONSTRAINT fk_profil_no_administrateur FOREIGN KEY (no_administrateur) REFERENCES ADMINISTRATEUR(no_administrateur)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE PROFIL
    ADD CONSTRAINT un_profil_no_administrateur UNIQUE (no_administrateur);

--
-- RIGHTS OF ADMINISTRATEURS
--
CREATE TABLE DROIT (
    no_droit INT NOT NULL AUTO_INCREMENT,
    designation VARCHAR(50) NOT NULL,
    PRIMARY KEY (no_droit)
);

CREATE TABLE PERMISSION (
    no_administrateur INT NOT NULL,
    no_droit INT NOT NULL
);

-- integrity constraints
ALTER TABLE PERMISSION
    ADD CONSTRAINT fk_permission_no_administrateur
    FOREIGN KEY (no_administrateur) REFERENCES ADMINISTRATEUR(no_administrateur)
    ON DELETE CASCADE
    ON UPDATE NO ACTION;

ALTER TABLE PERMISSION
    ADD CONSTRAINT fk_permission_no_droit
    FOREIGN KEY (no_droit) REFERENCES DROIT(no_droit)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

--
-- ARTICLE
--
CREATE TABLE CATEGORIE (
    no_categorie INT AUTO_INCREMENT,
    designation VARCHAR(50) NOT NULL,
    no_categorie_parent INT,
    PRIMARY KEY (no_categorie)
);

-- integrity constraints
ALTER TABLE CATEGORIE
    ADD CONSTRAINT fk_categorie_no_categorie FOREIGN KEY (no_categorie_parent) REFERENCES CATEGORIE(no_categorie)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

CREATE TABLE ARTICLE (
    no_article INT NOT NULL AUTO_INCREMENT,
    designation VARCHAR(50) NOT NULL,
    description VARCHAR(256),
    imageFileName VARCHAR(50),
    etat_vente INT NOT NULL, -- 1: NEUF, 2: BON, 3: MOYEN, 4: MAUVAIS
    date_enregistrement DATETIME NOT NULL DEFAULT NOW(),
    no_proprietaire INT,
    PRIMARY KEY (no_article)
);

-- integrity constraints
ALTER TABLE ARTICLE
    ADD CONSTRAINT fk_article_no_proprietaire FOREIGN KEY (no_proprietaire) REFERENCES CLIENT(no_client)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

--
-- VENTE
--
CREATE TABLE VENTE (
    no_vente INT AUTO_INCREMENT,
    montant_depart FLOAT NOT NULL,
    date_debut DATETIME NOT NULL,
    date_fin DATETIME NOT NULL,
    date_fin_delta_origine INT NOT NULL DEFAULT 0,
    date_changement_proprietaire DATETIME,
    no_adresse_retrait INT,
    PRIMARY KEY (no_vente),
    CONSTRAINT ck_vente_montant_depart CHECK (montant_depart >= 0),
    CONSTRAINT ck_vente_dates CHECK (
        date_debut < (date_fin - INTERVAL date_fin_delta_origine MINUTE)
        AND (date_fin - INTERVAL date_fin_delta_origine MINUTE) < date_changement_proprietaire
    )
);

-- integrity constraints
ALTER TABLE VENTE
    ADD CONSTRAINT fk_vente_no_adresse_retrait FOREIGN KEY (no_adresse_retrait) REFERENCES ADRESSE(no_adresse)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

CREATE TABLE ENCHERE (
    no_vente INT NOT NULL,
    no_encherisseur INT NOT NULL,
    montant FLOAT NOT NULL,
    date DATETIME NOT NULL
);

-- integrity constraints
ALTER TABLE ENCHERE
    ADD CONSTRAINT fk_enchere_no_vente FOREIGN KEY (no_vente) REFERENCES VENTE(no_vente)
    ON DELETE CASCADE
    ON UPDATE NO ACTION;

ALTER TABLE ENCHERE
    ADD CONSTRAINT fk_enchere_no_encherisseur FOREIGN KEY (no_encherisseur) REFERENCES CLIENT(no_client)
    ON DELETE CASCADE
    ON UPDATE NO ACTION;

--
--
--