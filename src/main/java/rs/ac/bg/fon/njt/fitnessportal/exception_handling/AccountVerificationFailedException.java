package rs.ac.bg.fon.njt.fitnessportal.exception_handling;

public class AccountVerificationFailedException extends RuntimeException{
    public AccountVerificationFailedException(){
        super("Verification code invalid! Check whether your account has already been verified!");
    }
}
