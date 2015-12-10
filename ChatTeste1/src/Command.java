/**
 * Created by Griffon on 04.11.2015.
 */
public class Command {
    private CommandType type;

    Command(){
        ;
    }

    static enum  CommandType{
        ACCEPT , DISCONNECT , MESSAGE , NICK , REJECT;
    }

    public Command(CommandType commandType) {
        type = commandType;
    }    

    public CommandType getType() {
		return type;
	}

	public String  toString() {
        return type.toString();
    }

}
