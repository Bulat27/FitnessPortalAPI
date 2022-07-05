package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class AdminCannotBeModifiedException extends RuntimeException{

    public AdminCannotBeModifiedException(){
        super("Initial admin cannot be modified");
    }
}
