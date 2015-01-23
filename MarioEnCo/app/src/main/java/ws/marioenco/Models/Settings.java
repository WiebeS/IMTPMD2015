package ws.marioenco.Models;

/**
 * Created by Wiebe on 1/19/2015.
 * Model met settings als IP adres en Online of offline boolean
 */
public class Settings {

        //singleton pattern
        private static Settings _instance;
    public static Settings getInstance()
    {
        if( _instance == null )
            _instance = new Settings();

        return _instance;
    }

    private boolean online = true;
    private String ip4Adress = "192.168.178.12";

    public String getIp4Adress()
    {
        return ip4Adress;
    }

    public void setIp4Adress(String ip4Adress) {
        this.ip4Adress = ip4Adress;
    }

    public boolean getisOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
