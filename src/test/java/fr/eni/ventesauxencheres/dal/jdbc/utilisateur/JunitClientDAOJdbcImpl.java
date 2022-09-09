package fr.eni.ventesauxencheres.dal.jdbc.utilisateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.dao.FactoryDAO;
import fr.eni.ventesauxencheres.exceptions.BLLException;
import fr.eni.ventesauxencheres.exceptions.DALException;

class JunitClientDAOJdbcImpl {

	@Test
	void test_insert() {
		try {
			// Enregistrer un client
			Client client = new Client("pseudo", "pseudo@example.com", "Richard", "Gaston", 100 , "02065314");
			byte[] motDePasse = ClientMgr.getInstance().hashMotDePasse("test");
			client = FactoryDAO.getClientDAO().insert(client, motDePasse);
			// Récupérer un client
			Client clientRecupere = FactoryDAO.getClientDAO().selectById(client.getNoClient());
			// Comparer les deux
			if (client.getPseudo().equals(clientRecupere.getPseudo()) &&
				client.getCourriel().equals(clientRecupere.getCourriel()) &&
				client.getNom().equals(clientRecupere.getNom()) &&
				client.getPrenom().equals(clientRecupere.getPrenom()) &&
				client.getCredit() == clientRecupere.getCredit() &&
				client.getTelephone().equals(clientRecupere.getTelephone())
				) {
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		} catch (DALException | BLLException e) {
			e.printStackTrace();
		}
	}

}
