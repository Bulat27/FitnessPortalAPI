package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class FutureAppointmentException extends RuntimeException{
    public FutureAppointmentException(){
        super("You can only update attendances of appointments that have passed.");
    }
}
