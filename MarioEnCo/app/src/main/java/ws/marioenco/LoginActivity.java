package ws.marioenco;

import android.app.Activity;
import android.content.Intent;
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

import java.util.concurrent.ExecutionException;

import ws.marioenco.Helpers.ClientHelper;
import ws.marioenco.Models.ServiceLijstModel;
import ws.marioenco.Models.Settings;

/**
 * Created by Wiebe on 1/20/2015.
 */
public class LoginActivity extends Activity {

    EditText ipEdit;
    TextView textViewIP;
    Button loginButton;

    Settings settingsData = Settings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
// Setup the elements
        ipEdit = (EditText)findViewById(R.id.editTextIP);
        textViewIP = (TextView) findViewById(R.id.textViewIP);
        loginButton = (Button) findViewById(R.id.loginButton);

        ipEdit.setText(settingsData.getIp4Adress());
    }


    public void login(View view){

        String ip= String.valueOf(ipEdit.getText());
        settingsData.setIp4Adress(ip);

        if (settingsData.getisOnline() == true){
            //  getServices();

            boolean connectionAvailable = getOnlineOffline();
            if(connectionAvailable == false){
                noConnectionMessage();
            }

            else if (connectionAvailable == true){
                getServices();

                connectionMessage();
            }

            // TODO get SERVICES aanroepen
        }



        // TODO OFFLINE
        // Load next page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

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

                //  String test = String.valueOf(services.get("naam"));

                Log.v("wiebe", "JA" );

            } catch (JSONException e) {
                e.printStackTrace();
            }


            for (int i = 0 ; i < services.length(); i++)
            {
                ServiceLijstModel serviceLijstModel = ServiceLijstModel.getInstance();

                //     ArrayList<String> servicesLijst = new ArrayList<String>();

                try {
                    JSONObject value = services.getJSONObject(i);

                    String valueString = value.getString("naam");

                    Log.v("wiebe", "JA" + i + valueString);

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
        // TODO hierb ezig

        Log.v("wiebe", String.valueOf(settingsData.getisOnline()));

        return settingsData.getisOnline();

    }


    public void noConnectionMessage(){

        Toast.makeText(this, R.string.errorConnection,
                Toast.LENGTH_LONG).show();
    }


    public void connectionMessage(){

        Toast.makeText(this, R.string.connection,
                Toast.LENGTH_LONG).show();
    }

}


