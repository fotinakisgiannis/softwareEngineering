package user.example.com.roadworks;

/**
 * Created by User on 18/3/2017.
 */

public class ConnectionClass {
    String classs;
    String db;
    String un;
    String password;
    String ip;

    public ConnectionClass()
    {
        classs = "net.sourceforge.jtds.jdbc.Driver";
        db = "softwareEngineering";
        un = "giannis";
        password = "Fotinakis94";
        ip = "softwareengineering.database.windows.net:1433";
    }

    public ConnectionClass(String Ip, String Classs, String Db, String Un, String Password)
    {
        ip = Ip;
        classs = Classs;
        db = Db;
        un = Un;
        password = Password;
    }

    public String getIp()
    {
        return ip;
    }

    public String getClasss(){
        return classs;
    }

    public String getDb(){
        return db;
    }

    public String getUn(){
        return un;
    }
    public String getPassword(){
        return password;
    }

    public void setip(String Ip){
        ip = Ip;
    }

    public void setdb(String Db)
    {
        db = Db;
    }

    public void setclasss(String Classs){
        classs = Classs;
    }

    public void setun(String Un){
        un = Un;
    }

    public void setpassword(String Password){
        password = Password;
    }
}
