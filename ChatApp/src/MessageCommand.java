

public class MessageCommand extends Command{
    private String text;
    public MessageCommand(String com, String text){
        this.text=text;
        if (check(com)) {
            this.com = com;
        } else {
            this.com="INCORRECT";
        }
    }
    public String getText(){
        return text;
    }
}
