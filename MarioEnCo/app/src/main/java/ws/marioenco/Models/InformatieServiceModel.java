package ws.marioenco.Models;

/**
 * Created by Wiebe on 1/19/2015.
 */
public class InformatieServiceModel {

    //singleton pattern
    private static InformatieServiceModel _instance;

    public static InformatieServiceModel getInstance() {
        if (_instance == null)
            _instance = new InformatieServiceModel();

        return _instance;
    }
    private String infoService;

    public String getinfoService() {
        return infoService;
    }

    public void setinfoService(String infoService) {
        this.infoService = infoService;
    }

    public void setInfoServiceHardCoded(int pos){



    }
}