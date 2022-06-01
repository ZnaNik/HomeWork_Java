package part2.homework7.common.enums;

import java.util.Objects;

public enum Command {
    BROADCAST_MESSAGE("/broadcast"),
    LIST_USERS("/list"),
    PRIVATE_MESSAGE("/private"),
    AUTH_MESSAGE("/auth"),
    AUTH_OK("/auth-ok"),
    ERROR_MESSAGE("/error");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static String getName(Command command){
        return command.name();
    }

    public static Command getByCommand(String command) {
        for (Command value : values()) {
            if (Objects.equals(value.name(), command)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
