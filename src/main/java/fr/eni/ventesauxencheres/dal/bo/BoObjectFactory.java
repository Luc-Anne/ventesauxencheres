package fr.eni.ventesauxencheres.dal.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.dependencies.Adresse;
import fr.eni.ventesauxencheres.bo.utilisateur.Client;

public class BoObjectFactory {

	private static BoObjectFactory boObjectFactory;

	public static BoObjectFactory getInstance() {
		if (boObjectFactory == null) {
			boObjectFactory = new BoObjectFactory();
		}
		return boObjectFactory;
	}

	public Adresse createAdresse(ResultSet rs) throws SQLException {
		return new Adresse(
				rs.getInt("no_adresse"),
				rs.getString("rue"),
				rs.getString("code_postal"),
				rs.getString("ville"),
				rs.getString("pays")
				);
	}

	public Client createClient(ResultSet rs) throws SQLException {
		return new Client(
				rs.getInt("no_profil"),
				rs.getString("pseudo"),
				rs.getString("courriel"),
				LocalDateTime.of(rs.getDate("date_enregistrement").toLocalDate(), rs.getTime("date_enregistrement").toLocalTime()),
				rs.getInt("no_client"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getBoolean("actif"),
				rs.getInt("credit"),
				rs.getString("telephone"),
				createAdresse(rs)
				);
	}

}
