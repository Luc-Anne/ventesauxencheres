package fr.eni.ventesauxencheres.dal.jdbc.utilisateur;

import fr.eni.ventesauxencheres.bll.utilisateur.ClientMgr;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.dal.dao.FactoryDAO;
import fr.eni.ventesauxencheres.exceptions.BLLException;
import fr.eni.ventesauxencheres.exceptions.DALException;

public class TestClientDAOJdbcImpl {

	public static void main(String[] args) {
		Client client = new Client("pseudo", "pseudo@example.com", "Richard", "Gaston", 100 , "02065314");
		System.out.println(client);
		try {
			byte[] motDePasse = ClientMgr.getInstance().hashMotDePasse("test");
			client = FactoryDAO.getClientDAO().insert(client, motDePasse);
		} catch (DALException | BLLException e) {
			e.printStackTrace();
		}
		System.out.println(client);
	}

}
