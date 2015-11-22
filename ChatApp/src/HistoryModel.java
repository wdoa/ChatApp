
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class HistoryModel extends Observable {
	private ArrayList<Message> arrayListMessages;
	
	HistoryModel(){
		arrayListMessages = new ArrayList<Message>();
	}
	
	public void	addMessage(Message m){
		arrayListMessages.add(m);
		setChanged();
		notifyObservers();
	} 
	
	public void	addMessage(String nick, Date date, String text){
		Message message = new Message(nick,date,text);
		arrayListMessages.add(message);
		setChanged();
		notifyObservers();
	} 
	
	public void	clear(){
		arrayListMessages.clear();
		setChanged();
		notifyObservers();
	} 
	
	public Message	getMessage(int pos){
		return arrayListMessages.get(pos);
	} 
	
	public int	getSize(){
		return arrayListMessages.size();
	} 
	
	class Message {
		private String nick;
		private Date date;
		private String text;

		Message(String nick, Date date, String text) {
			this.nick = nick;
			this.text = text;
			this.date = date;
		}

		Date getDate() {
			return date;
		}

		String getNick() {
			return nick;
		}

		String getText() {
			return text;
		}
		
	public void setNick(String nick) {
			this.nick = nick;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
	
	
}
