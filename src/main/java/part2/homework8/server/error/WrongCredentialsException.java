package part2.homework8.server.error;

public class WrongCredentialsException extends IllegalArgumentException {
    public WrongCredentialsException() {
    }

    public WrongCredentialsException(String s) {
        super(s);
    }
}
