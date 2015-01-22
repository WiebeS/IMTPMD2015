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

    public void setShortInfoServiceHardCoded(String string){

//        // set info service adv de pos
//        if(pos == 0){
//            this.shortInfoService ="Uw toiletproblemen in mum van tijd verholpen!";
//        }
//        if(pos == 1) {
//        }
//        this.shortInfoService = "Valt alles in het water? Wij helpen u uit de brand!";
//        if(pos == 2) {
//            this.shortInfoService = "Wij vinden het juiste kasteel voor u!";
//        }
        this.shortInfoService = string;

    }
}