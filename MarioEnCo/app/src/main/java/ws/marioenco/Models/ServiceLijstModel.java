package ws.marioenco.Models;

import java.util.ArrayList;

/**
 * Created by Wiebe on 1/19/2015.
 */
public class ServiceLijstModel {

    //singleton pattern
    private static ServiceLijstModel _instance;

    public static ServiceLijstModel getInstance() {
        if (_instance == null)
            _instance = new ServiceLijstModel();

        return _instance;
    }

    private ArrayList<String> servicesLijst = new ArrayList<String>();

    private int selectedService = 0;

    public void setServicesLijst(ArrayList<String> servicesLijst) {
        this.servicesLijst = servicesLijst;
    }

    public ArrayList<String> getServicesLijst() {
        return servicesLijst;
    }

    public void addService(String string){
        servicesLijst.add(string);
    }


    public int getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(int selectedService) {
        this.selectedService = selectedService;
    }
}