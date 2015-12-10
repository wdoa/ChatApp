import java.util.Observable;
import java.util.Observer;

/**
 * Created by Griffon on 29.11.2015.
 */
public class Main {
    public static void main(String[] args) {
        ChatFrame1 chatFrame1 = new ChatFrame1();
        MVC mvc;
        mvc=new MVC(chatFrame1);
    }
}
