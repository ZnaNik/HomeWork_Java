package part2.homework7.common.message;

import org.json.JSONObject;
import part2.homework7.common.enums.Command;

public class Message {

    String owner_id;
    String reciever_id;
    String message;
    Command command;

    JSONObject jsonMessage;
    public String getMessage() {
        return message;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public String getReciever_id() {
        return reciever_id;
    }

    public Command getCommand() {
        return command;
    }

    public static String getJson(String owner_id, String reciever_id, String message, Command command) {
        var jo = new JSONObject();
        jo.put("owner_id", owner_id); //message owner
        jo.put("reciever_id", reciever_id); //need to private message
        jo.put("message", message); // message
        jo.put("command", Command.getName(command));
        return jo.toString();
    }

    public Message(String owner_id, String reciever_id, String message, Command command) {
        this.owner_id = owner_id;
        this.reciever_id = reciever_id;
        this.message = message;
        this.command = command;
    }
    public Message(JSONObject jsonMessage)
    {
        if (jsonMessage == null)
            throw new IllegalCallerException("Error with message");
        this.jsonMessage = jsonMessage;

        this.owner_id = getField("owner_id");
        this.reciever_id = getField("reciever_id");
        this.message = getField("message");
        this.command = Command.getByCommand(getField("command"));
    }

    public String toString() {
        var jo = new JSONObject();
        jo.put("owner_id", owner_id); //message owner
        jo.put("reciever_id", reciever_id); //need to private message
        jo.put("message", message); // message
        jo.put("command", Command.getName(command));
        return jo.toString();
    }

    public static Message getMessage(String inValue) {
        return new Message(new JSONObject(inValue));

    }

    private String getField(String fieldName){

        if (jsonMessage.has(fieldName) && !jsonMessage.isNull(fieldName))
            return jsonMessage.getString(fieldName);
        else
            return null;
    }

}
