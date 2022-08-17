package fr.eni.ventesauxencheres.bll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/TestCategorieManager")
public class TestCategorieManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String message = "";
		
		// Test Method 1
		pw.append("\nMethode \n");
		// Test 1 ###################################################
		pw.append("\nTest 1 : \n");
		message = "";

		pw.append(message);
		// Test 2 ###################################################
		pw.append("\nTest 2 : \n");
		message = "";

		pw.append(message);
		// Test Method 2
		pw.append("\nMethode \n");
		// Test 3 ###################################################
		pw.append("\nTest 3 : \n");
		message = "";

		pw.append(message);
		// Test 4 ###################################################
		pw.append("\nTest 4 : \n");
		message = "";

		pw.append(message);
		// Display test results #####################################
		pw.flush();
	}

}
