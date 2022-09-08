package fr.eni.ventesauxencheres.dal.jdbc;

import fr.eni.ventesauxencheres.bll.BLLException;
import fr.eni.ventesauxencheres.bll.ClientManager;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

public class TestClientDAOJdbcImpl {

	public static void main(String[] args) {
		Client client = new Client("pseudo", "pseudo@example.com", "Richard", "Gaston", 100 , "02065314");
		System.out.println(client);
		try {
			byte[] motDePasse = ClientManager.getInstance().hashMotDePasse("test");
			client = FactoryDAO.getClientDAO().insert(client, motDePasse);
		} catch (DALException | BLLException e) {
			e.printStackTrace();
		}
		System.out.println(client);
	}

}
