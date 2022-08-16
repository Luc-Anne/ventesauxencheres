package fr.eni.ventesauxencheres.dal;

import java.sql.SQLException;
public class DALException extends Exception {
    private static final long serialVersionUID = -8053297193839148325L;

   public DALException() {
        super();
    }
    
    public DALException(String message) {
        super(message);
    }
    
    public DALException(String message, Throwable exception) {
        super(message, exception);
    }
    
    @Override
    public String getMessage() {
        String text = "";
        text = "Erreur Couche DAL - " + super.getMessage();
        return text;
    }
}