package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(Integer id){
        super("Appointment with id "+ id + " not found");
    }
}
