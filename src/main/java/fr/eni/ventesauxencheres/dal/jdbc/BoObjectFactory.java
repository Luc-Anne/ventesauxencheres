package fr.eni.ventesauxencheres.dal.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.eni.ventesauxencheres.bo.utilisateur.Client;

public class BoObjectFactory {

	private static BoObjectFactory boObjectFactory;

	public static BoObjectFactory getInstance() {
		if (boObjectFactory == null) {
			boObjectFactory = new BoObjectFactory();
		}
		return boObjectFactory;
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
				rs.getString("telephone")
				);
	}

}
