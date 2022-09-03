--
--   auteurs : Luc Anne / ENI
--   email : lucanne.t@gmx.fr
--
--   description : script de création d'une procédure stockée
--   objet : mettre à jour l'état de vente des articles
--   langage : sql/mariaDB
--
USE ENIVAE;

DELIMITER //

CREATE OR REPLACE PROCEDURE updateArticle()
BEGIN
    DECLARE id int;
    DECLARE no_vendeur int;
    DECLARE montant int;

    DECLARE exitFlag BOOLEAN DEFAULT FALSE;
    DECLARE updateCR CURSOR FOR
        SELECT no_article FROM ARTICLES WHERE etat_vente = 'CR' AND CURRENT_DATE >= date_debut_enchere;
    DECLARE updateEC CURSOR FOR
        SELECT no_article, no_utilisateur FROM ARTICLES WHERE etat_vente = 'EC' AND CURRENT_DATE > date_fin_enchere;
    DECLARE setMontant CURSOR FOR
        SELECT montant_enchere FROM ENCHERES WHERE no_article = id ORDER BY montant_enchere DESC LIMIT 1;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET exitFlag = TRUE;
    
    -- update CR to EC
    OPEN updateCR;
    updateCRLoop : LOOP
        FETCH updateCR INTO id;
        IF exitFlag THEN LEAVE updateCRLoop;
        END IF;
        UPDATE ARTICLES SET etat_vente = 'EC' WHERE no_article = id;
    END LOOP;
    CLOSE updateCR;
    
    -- update EC to VD
    OPEN updateEC;
    SET exitFlag = FALSE;
    updateECLoop : LOOP
        FETCH updateEC INTO id , no_vendeur;
        IF exitFlag THEN LEAVE updateECLoop;
        END IF;
        
        SET montant = 0;
        OPEN setMontant;
            FETCH setMontant INTO montant;
        CLOSE setMontant;
        
        UPDATE ARTICLES SET etat_vente = 'VD', prix_vente = montant WHERE no_article = id;
        
        IF montant > 0 THEN
            UPDATE UTILISATEURS SET credit = credit + montant WHERE no_utilisateur = no_vendeur;
        END IF;
    END LOOP;
    CLOSE updateEC;

END; //

DELIMITER ;
