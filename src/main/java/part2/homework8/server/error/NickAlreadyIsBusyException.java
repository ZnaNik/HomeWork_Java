package part2.homework8.server.error;

public class NickAlreadyIsBusyException extends IllegalArgumentException {
    public NickAlreadyIsBusyException() {
    }

    public NickAlreadyIsBusyException(String s) {
        super(s);
    }
}
