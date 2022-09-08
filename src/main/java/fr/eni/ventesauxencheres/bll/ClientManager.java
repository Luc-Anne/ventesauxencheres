package fr.eni.ventesauxencheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;
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

	public List<String> invalidCause(Client client) {
		return null;
	}
/*
	public List<String> invalidCause(Client client)  {
		List<String> invalidCause = new ArrayList<>();

		// pseudo
		if (client.getPseudo() == null ||
			client.getPseudo() == "") {
			invalidCause.add("client.pseudo_vide");
		} else {
			if (client.getPseudo().length() > 30) {
				invalidCause.add("client.pseudo_tropLong");
			}
			if (client.getPseudo().contains(" ")) {
				invalidCause.add("client.pseudo_avecEspace");
			}
		}
		// nom
		if (client.getNom() == null ||
			client.getNom() == "") {
			invalidCause.add("client.nom_vide");
		} else {
			if (client.getNom().length() > 30) {
				invalidCause.add("client.nom_tropLong");
			}
		}
		// prenom
		if (client.getPrenom() == null ||
			client.getPrenom() == "") {
			invalidCause.add("client.prenom_vide");
		} else {
			if (client.getPrenom().length() > 30) {
				invalidCause.add("client.prenom_tropLong");
			}
		}
		// email
		if (client.getEmail() == null ||
			client.getEmail() == "") {
			invalidCause.add("client.email_vide");
		} else {
			if (client.getEmail().length() > 20) {
				invalidCause.add("client.email_tropLong");
			}
			if (! client.getEmail().contains("@")) {
				invalidCause.add("client.email_manque@");
			}
		}
		// telephone
		if (client.getNom().length() > 15) {
			invalidCause.add("client.telephone_tropLong");
		}
		// rue
		if (client.getRue() == null ||
			client.getRue() == "") {
			invalidCause.add("client.rue_vide");
		} else {
			if (client.getRue().length() > 30) {
				invalidCause.add("client.rue_tropLong");
			}
		}
		// codePostal
		if (client.getCodePostal() == null ||
			client.getCodePostal() == "") {
			invalidCause.add("client.codePostal_vide");
		} else {
			if (client.getCodePostal().length() > 10) {
				invalidCause.add("client.codePostal_tropLong");
			}
		}
		// ville
		if (client.getVille() == null ||
			client.getVille() == "") {
			invalidCause.add("client.ville_vide");
		} else {
			if (client.getVille().length() > 30) {
				invalidCause.add("client.ville_tropLong");
			}
		}
		// motDePasse
		if (client.getMotDePasse() == null ||
			client.getMotDePasse() == "") {
			invalidCause.add("client.motDePasse_vide");
		} else {
			if (client.getMotDePasse().length() > 30) {
				invalidCause.add("client.motDePasse_tropLong");
			}
			if (client.getMotDePasse().length() < 8) {
				// Un mot de passe est d'au moins 8 caractères
				invalidCause.add("client.motDePasse_faible");
			}
		}

		// credit
		if (client.getCredit() < 0) {
			invalidCause.add("client.credit_negatif");
		}
		// administrateur
		// Aucun test car boolean

		return invalidCause;
	}
*/
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

	public void delete(int id) throws BLLException {
		try {
			clientDAO.delete(id);
		} catch (DALException e) {
			throw new BLLException("", e);
		}
	}

	// Spécifiques
	public Client connexion(String email, String password) throws BLLException {
		try {
			return clientDAO.connexion(email, password);
		} catch (DALException e) {
			throw new BLLException("ERREUR MANAGER", e);
		}
	}

	public Client getByPseudo(String pseudo) throws BLLException {
		try {
			return clientDAO.selectByPseudo(pseudo);
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
