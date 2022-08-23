package fr.eni.ventesauxencheres.bll;

public class BLLException extends Exception {
    private static final long serialVersionUID = 2270280020015195969L;

    public BLLException() {
        super();
    }
    
    public BLLException(String message) {
        super(message);
    }
    
    public BLLException(String message, Throwable exception) {
        super(message, exception);
    }
    
    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder("Erreur Couche BLL - ");
        message.append(super.getMessage());
        return message.toString();        
    }
}