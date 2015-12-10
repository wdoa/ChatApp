

public class MessageCommand extends Command{
    private String text;

    public MessageCommand(String text){
        super(CommandType.MESSAGE);
        this.text = text;
        
    }
    public String toString(){
        return this.text;
    }
}
