package ws.marioenco.Models;


/**
 * Created by Wiebe on 1/19/2015.\
 * Model met data
 */
public class InformatieServiceBeknoptModel {

    //singleton pattern
    private static InformatieServiceBeknoptModel _instance;

    public static InformatieServiceBeknoptModel getInstance() {
        if (_instance == null)
            _instance = new InformatieServiceBeknoptModel();

        return _instance;
    }
    private String shortInfoService;

    public String getShortInfoService() {
        return shortInfoService;
    }

    public void setShortInfoService(String shortInfoService) {
        this.shortInfoService = shortInfoService;
    }

    public void setShortInfoServiceHardCoded(String string){

        this.shortInfoService = string;

    }
}