package ws.marioenco.Controllers;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import ws.marioenco.Helpers.ClientHelper;
import ws.marioenco.MainActivity;
import ws.marioenco.Models.Settings;

/**
 * Created by Wiebe on 1/19/2015.
 */
public class ServicesController  {


public void getServices(Context context){


    Settings settingsData = Settings.getInstance();

    //aanmaken van een nieuw jsonobject
    JSONObject categorieJObject = new JSONObject();
    try
    {
        //verzenden van het jsonobject
        categorieJObject.put("servicelijst","");

//            { “servicelijst” : “” }
    }
    catch (JSONException e)
    {
        e.printStackTrace();
    }

    String reactie = null;
    try
    {
        try
        {
            //servercommunicator proberen te verbinden met de server
            reactie = new ClientHelper( context , settingsData.getIp4Adress() , 4444, categorieJObject.toString()).execute().get();
            //        reactie = new ClientHelper( this, ipAdress, 4444,"informatie").execute().get();

// TODO De reactie doorloopen, en weer een JSON van bouwen?!
            Voor elke service ontvangen 1 opslaan

            for(String test : reactie ){


            }

            Log.v("debug", reactie);
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
    catch (InterruptedException e1)
    {
        e1.printStackTrace();
    }


}



}
