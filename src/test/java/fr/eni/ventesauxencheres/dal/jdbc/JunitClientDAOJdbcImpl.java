package fr.eni.ventesauxencheres.dal.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.ClientManager;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

class JunitClientDAOJdbcImpl {

	@Test
	void test_insert() {
		Client client = new Client("pseudo", "pseudo@example.com", "Richard", "Gaston", 100 , "02065314");
		System.out.println(client);
		try {
			byte[] motDePasse = ClientManager.getInstance().hashMotDePasse("test");
			client = FactoryDAO.getClientDAO().insert(client, motDePasse);
		} catch (DALException | BLLException e) {
			e.printStackTrace();
		}
		//System.out.println(client);
	}

}
