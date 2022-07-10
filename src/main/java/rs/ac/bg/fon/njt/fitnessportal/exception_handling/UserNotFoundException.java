package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String email) {
        super("User with email " + email + " not found");
    }

    public UserNotFoundException(Integer id) {
        super("User with id " + id + " not found");
    }
}
