package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class TrainingNotFoundException extends RuntimeException{

    public TrainingNotFoundException(Integer id){
        super("Training with id "+ id + "not found");
    }
}
