package ws.marioenco.Models;

import java.util.ArrayList;

/**
 * Created by Wiebe on 1/19/2015.
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




}