package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class InvalidBlogException extends RuntimeException{

    public InvalidBlogException(){
        super("You can only use the crud operations on your blogs");
    }
}
