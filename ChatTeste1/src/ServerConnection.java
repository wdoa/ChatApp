import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Griffon on 02.12.2015.
 */
    public class ServerConnection {
        private String serverAddress;
        private String localNick;

        private java.sql.Connection con;
        private Statement st;

        public ServerConnection(){

        }

        public ServerConnection(String address){
            this(address, null);
        }

        public ServerConnection(String address, String nick){
            if(nick!=null)
                setLocalNick(nick);
            if(address!=null){
                setServerAddress(address);
                connect();
            }// if
        }

        public void connect(){
            if(isConnected())
                return;
            assert(serverAddress != null && !serverAddress.trim().isEmpty());
            try {
                con = DriverManager.getConnection(serverAddress,"guest","guest");
                st = con.createStatement();
            } catch (SQLException e) {
                // TODO throw real error
                e.printStackTrace();
            }// catch
        }

        public void disconnect(){
            if(st!=null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                st = null;
            }
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                con=null;
            }// if
        }

        public boolean isConnected(){
            return st != null;
        }

        public String getServerAddress() {
            return serverAddress;
        }

        public void setServerAddress(String serverAddress) {
            if(serverAddress.equals(this.serverAddress))
                return;
            boolean connected = isConnected();
            if(connected){
                goOffline();
                disconnect();
            }
            this.serverAddress = serverAddress;
            if(connected)
                connect();
        }

        public String getLocalNick() {
            return localNick;
        }

        public void setLocalNick(String newNick) {
            assert newNick!=null;
            newNick = safe(newNick);
            if(newNick.equals(this.localNick))
                return;

            boolean online = false;
            if(isConnected()){
                online = isNickOnline(localNick);
                if(online)
                    goOffline();
            }

            this.localNick = newNick;
            if(online)
                goOnline();
        }

        public void goOnline(){
            goOnline(28411);
        }

        public void goOnline(int port){
            assert localNick != null;
            assert isConnected();
            String q = "INSERT INTO user (nick, ip, online, port) values ('"+localNick+"', SUBSTRING_INDEX(USER(),'@',-1), 1,'"+port+"');";
            try {
                st.executeUpdate(q);
            } catch (SQLException e) {
                boolean nick_collision=true;
                try{
                    if(getIpForNick(localNick)!=null){			// if nick collision
                        q = "UPDATE user set ip=SUBSTRING_INDEX(USER(),'@',-1), online=1, port="+port+" WHERE nick='"+localNick+"';";
                        nick_collision = true;
                    }
                    else{										// if address collision
                        q = "UPDATE user set nick='"+localNick+"', online=1 WHERE ip=SUBSTRING_INDEX(USER(),'@',-1) AND port="+port+";";
                        nick_collision = false;
                    }
                    st.executeUpdate(q);
                }catch(SQLException e2){
                    try {
                        if(nick_collision)
                            st.execute("DELETE FROM user WHERE ip=SUBSTRING_INDEX(USER(),'@',-1) AND port="+port+";");
                        else
                            st.execute("DELETE FROM user WHERE nick='"+localNick+"';");
                        st.execute(q);
                    } catch (SQLException e3) {
                        e2.printStackTrace();
                    }// catch3
                }// catch2
            }// catch
        }// goOnline

        public void goOffline(){
            assert localNick != null;
            assert isConnected();
            try {
                st.execute("INSERT INTO user (nick, ip, online) values ('"+localNick+"', SUBSTRING_INDEX(USER(),'@',-1), 0) ON DUPLICATE KEY UPDATE ip=VALUES(ip), online=VALUES(online);");
            } catch (SQLException e) {
                e.printStackTrace();
            }// catch
        }

        public String getIpForNick(String nick){
            assert isConnected();
            assert nick!=null;
            nick = safe(nick);
            ResultSet rs;
            try {
                rs = st.executeQuery("SELECT ip FROM user WHERE nick='"+nick+"'");
                if(!rs.next())
                    return null;
                return rs.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }// catch
            return null;
        }

        public int getPortForNick(String nick){
            assert isConnected();
            assert nick!=null;
            nick = safe(nick);
            ResultSet rs;
            try {
                rs = st.executeQuery("SELECT port FROM user WHERE nick='"+nick+"'");
                if(!rs.next())
                    return 0;
                return rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }// catch
            return 0;
        }

        public boolean isNickOnline(String nick){
            if(nick==null)
                return false;

            assert isConnected();
            nick = safe(nick);
            ResultSet rs;
            try {
                rs = st.executeQuery("SELECT online FROM user WHERE nick='"+nick+"'");
                if(!rs.next())
                    return false;
                return rs.getBoolean(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }// catch
            return false;
        }

        public String[] getAllNicks(){
            assert isConnected();
            ResultSet rs;
            List<String> res = new ArrayList<String>();
            try {
                rs = st.executeQuery("SELECT nick FROM user;");
                while(rs.next()){
                    String nick = rs.getString(1);
                    res.add(nick);
                }// while
            } catch (SQLException e) {
                e.printStackTrace();
            }// catch
            return res.toArray(new String[0]);
        }

        private static String safe(String s){
            return s.replaceAll("['\";]", "").replaceAll("\\s", "");
        }

        public static void main(String[] args) {
            String nick1 = "latin&кириллица";
            String nick2 = "another";

            ServerConnection c = new ServerConnection();
            c.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
            c.connect();
            assert c.isConnected();
            c.setLocalNick(nick1);
            System.out.println("Before: " + c.isNickOnline(nick1));
            c.goOnline();
            System.out.println("After: " + c.isNickOnline(nick1));
            c.goOffline();
            System.out.println("After offline: " + c.isNickOnline(nick1));

            System.out.println("Another nick: " + c.isNickOnline(nick2));
            System.out.println("My ip: " + c.getIpForNick(nick1));
            System.out.println("Other ip: " + c.getIpForNick(nick2));

            System.out.println("My port: " + c.getPortForNick(nick1));
            System.out.println("Other port: " + c.getPortForNick(nick2));
        }// main
    }
