package fr.eni.ventesauxencheres.dal;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestConnectionProvider
 */
@WebServlet("/test/TestConnectionProvider")
public class TestConnectionProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Tester la connection à la base de donnée
        String message = "";
       try {
            ConnectionProvider.getConnection_VAE();
            message += "Connexion avec la base de donnée Ventes aux Enchères est établie";
        } catch (SQLException e) {
            e.printStackTrace();
            message += e.getMessage();
        }
       response.getWriter().append("Test de connection : ").append(message);
	}

}
