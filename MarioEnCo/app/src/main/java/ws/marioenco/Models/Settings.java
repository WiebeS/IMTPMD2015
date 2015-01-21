package ws.marioenco.Models;

/**
 * Created by Wiebe on 1/19/2015.
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
//"192.168.56.1";

    private String ip4Adress = "192.168.178.12";

    public String getIp4Adress()
    {
            return ip4Adress;
    }
}
