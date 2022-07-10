package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class InvalidNumberOfSpotsException extends RuntimeException {
    public InvalidNumberOfSpotsException(){
        super("Maximum number of spots has to be a positive integer");
    }
}
