package part2.homework8.server.error;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
