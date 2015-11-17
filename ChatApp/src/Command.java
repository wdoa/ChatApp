/**
 * Created by Griffon on 04.11.2015.
 */
public class Command {
    protected static String [] CType= {"ACCEPT", "DISCONNECT", "MASSAGE", "NICK", "REJECT"};
    protected String com;
    Command(){

    }
    public Command(String com){
        if (check(com)) {
            this.com = com;
        } else {
            this.com="INCORRECT";
        }
    }
    protected boolean check(String com) {
        for (int i = 0; i <= CType.length; i++)
            if (CType[i].equals(com)) {
                return true;
            }
        return false;
    }
    public String getCom(){
        return com;
    }
}
