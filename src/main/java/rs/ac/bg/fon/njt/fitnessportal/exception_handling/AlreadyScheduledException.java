package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class AlreadyScheduledException extends RuntimeException{

    public AlreadyScheduledException(){
        super("You have already scheduled a training appointment on that date");
    }
}
