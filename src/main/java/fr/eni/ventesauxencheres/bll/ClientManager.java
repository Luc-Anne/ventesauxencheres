package fr.eni.ventesauxencheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
import fr.eni.ventesauxencheres.bo.utilisateur.Profil;
import fr.eni.ventesauxencheres.dal.ClientDAO;
import fr.eni.ventesauxencheres.dal.DALException;
import fr.eni.ventesauxencheres.dal.FactoryDAO;

public class ClientManager {

	private static ClientManager clientManager;

	private ClientDAO clientDAO;

	private ClientManager() {
		clientDAO = FactoryDAO.getClientDAO();
	}

	public static ClientManager getInstance() {
		if (clientManager == null) {
			clientManager = new ClientManager();
		}
		return clientManager;
	}

	// Règles métiers
	public static final int DEFAULT_CREDIT = 100;

	// Validation
	public boolean isValide(Client client) {
		return invalidCause(client).size() == 0 ? true : false;
	}

	public List<String> invalidCause(Client client)  {
		List<String> invalidCause = new ArrayList<>();

		// profil (héritage)
		invalidCause.addAll(ProfilManager.getInstance().invalidCause((Profil)client));
		// nom
		if (client.getNom() == null ||
			"".equals(client.getNom())) {
			invalidCause.add("client.nom_vide");
		} else {
			if (client.getNom().length() > 50) {
				invalidCause.add("client.nom_tropLong");
			}
		}
		// prenom
		if (client.getPrenom() == null ||
			"".equals(client.getPrenom())) {
			invalidCause.add("client.prenom_vide");
		} else {
			if (client.getPrenom().length() > 50) {
				invalidCause.add("client.prenom_tropLong");
			}
		}
		// credit
		if (client.getCredit() < 0) {
			invalidCause.add("client.credit_negatif");
		}
		// telephone
		if (client.getTelephone().length() > 20) {
			invalidCause.add("client.telephone_tropLong");
		}
		// adresseDomicile
		if (client.getAdresseDomicile() == null) {
			invalidCause.add("client.adresse_vide");
		} else {
			invalidCause.addAll(AdresseManager.getInstance().invalidCause(client.getAdresseDomicile()));
		}

		return invalidCause;
	}

	// Méthodes métier basiques
	public Client save(Client client, String motDePasse) throws BLLException {
		try {
			if (this.isValide(client)) {
				byte[] hashedMotDePasse = hashMotDePasse(motDePasse);
				return clientDAO.insert(client, hashedMotDePasse);
			} else {
				throw new BLLException("client invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Client get(int id) throws BLLException {
		try {
			return clientDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Client getByPseudo(String pseudo) throws BLLException {
		try {
			return clientDAO.selectByPseudo(pseudo);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}
	
	public List<Client> getAll() throws BLLException {
		try {
			return clientDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void modify(Client client)throws BLLException{
		try {
			if (this.isValide(client)) {
				clientDAO.update(client);
			} else {
				throw new BLLException("client invalide");
			}
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public void delete(Client client) throws BLLException {
		try {
			clientDAO.delete(client);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	public Client connexion(String email, String motDePasse) throws BLLException {
		try {
			byte[] hashedMotDePasse = hashMotDePasse(motDePasse);
			return clientDAO.connexion(email, hashedMotDePasse);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}

	public byte[] hashMotDePasse(String motDePasse) throws BLLException {
		byte[] digest;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(motDePasse.getBytes());
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new BLLException("Problème lors du hashage du mot de passe.", e);
		}
		return digest;
	}

}
