/**
 * Created by Griffon on 04.11.2015.
 */
public class Command {
    public static String [] CType= {"ACCEPT", "DISCONNECT", "MASSAGE", "NICK", "REJECT"};
    private String com;
    public Command(String com){
        if (check(com)) {
            this.com = com;
        } else {
            this.com="INCORRECT";
        }
    }
    private boolean check(String com) {
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
