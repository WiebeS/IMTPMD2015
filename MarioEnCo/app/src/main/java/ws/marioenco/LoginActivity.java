package ws.marioenco;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import ws.marioenco.Helpers.ClientHelper;
import ws.marioenco.Models.InformatieServiceModel;
import ws.marioenco.Models.ServiceLijstModel;
import ws.marioenco.Models.Settings;

/**
 * Created by Wiebe Steverink on 1/19/2015.
 * IMTPMD HSLEIDEN
 */
public class LoginActivity extends Activity {

    // vars
    EditText ipEdit;
    TextView textViewIP,loginHead;
    Button loginButton;
    Typeface customFont;

    // Instances
    Settings settingsData = Settings.getInstance();
    ServiceLijstModel serviceLijstModel = ServiceLijstModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // Setup
        ipEdit = (EditText)findViewById(R.id.editTextIP);
        textViewIP = (TextView) findViewById(R.id.textViewIP);
        loginHead  = (TextView) findViewById(R.id.loginHead);
        loginButton = (Button) findViewById(R.id.loginButton);

        customFont = Typeface.createFromAsset(getAssets(), "fonts/customfont.ttf");
        loginHead.setTypeface(customFont);
        ipEdit.setText(settingsData.getIp4Adress());
    }

    public void login(View view){

        // Proberen een connectie te maken het het ingevoerde IP adres
        String ip= String.valueOf(ipEdit.getText());
        settingsData.setIp4Adress(ip);

        settingsData.setOnline(true);

        // Boolean zetten adv een connectie of niet
        boolean connectionAvailable = getOnlineOffline();
        if(connectionAvailable == false){

            serviceLijstModel.setServiceHardCoded();
            noConnectionMessage();
        }

        else if (connectionAvailable == true){

            getServices();
            connectionMessage();
        }

        // next page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Services opvragen
    public void getServices() {
        //aanmaken van een nieuw jsonobject
        JSONObject serviceObject = new JSONObject();

        try {
            //verzenden van het jsonobject
            serviceObject.put("servicelijst","");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String reactie = "string";
        {
            //servercommunicator proberen te verbinden met de server
            try {
                reactie = new ClientHelper(this, settingsData.getIp4Adress(), 4444, serviceObject.toString()).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            JSONArray services = null;
            try {
                services = new JSONArray(reactie);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Clear array
            serviceLijstModel.clearService();

            // Vullen array
            for (int i = 0 ; i < services.length(); i++)
            {
                try {
                    JSONObject value = services.getJSONObject(i);

                    String valueString = value.getString("naam");
                    serviceLijstModel.addService(valueString);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean getOnlineOffline() {
        //aanmaken van een nieuw jsonobject
        JSONObject serviceObject = new JSONObject();

        try {
            //verzenden van het jsonobject
            serviceObject.put("servicelijst","");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        {
            //servercommunicator proberen te verbinden met de server
            try {
                String reactie;
                reactie = new ClientHelper(this, settingsData.getIp4Adress(), 4444, serviceObject.toString()).execute().get();

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();

            }

        }

        return settingsData.getisOnline();
    }

    // Error msg
    public void noConnectionMessage(){

        Toast.makeText(this, R.string.errorConnection,
                Toast.LENGTH_LONG).show();
    }

    // Welkom msg
    public void connectionMessage(){

        Toast.makeText(this, R.string.connection,
                Toast.LENGTH_LONG).show();
    }


    public void setServicesHardCoded(){

        ArrayList<String> services = new ArrayList<String>();
        services.add("Riolering");
        services.add("Dak Lekkage");
        services.add("Prinses In Nood");

        serviceLijstModel.clearService();
        serviceLijstModel.setServicesLijst(services);
    }

}


