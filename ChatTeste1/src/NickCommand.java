/**
 * Created by forwi on 19.11.2015.
 */
public class NickCommand extends Command {

    private boolean isBusy;
    private String nick;
    private String version;

    public NickCommand (String version, String nick, boolean busy) {
        super(Command.CommandType.NICK);
        this.version = version;
        isBusy = busy;
        this.nick = nick;
    }

    public String getNick(){
        return nick;
    }
    public String toString(){
        if(isBusy){
            return "ChatApp" + version + " user " + nick + " busy";
        }
        else{
            return "ChatApp" +version + " user " + nick;
        }
    }

}
