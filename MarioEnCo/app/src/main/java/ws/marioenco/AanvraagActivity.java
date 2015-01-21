package ws.marioenco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import ws.marioenco.Helpers.ClientHelper;
import ws.marioenco.Models.InformatieServiceBeknoptModel;
import ws.marioenco.Models.InformatieServiceModel;
import ws.marioenco.Models.ServiceLijstModel;
import ws.marioenco.Models.Settings;
import ws.marioenco.Models.UserGegevensModel;


public class AanvraagActivity extends Activity  {

    TextView serviceTag,serviceInfo;
    EditText naam,adres,tel,mail;

    Settings settingsData = Settings.getInstance();
    ServiceLijstModel serviceLijstModel = ServiceLijstModel.getInstance();
    InformatieServiceModel informatieServiceModel = InformatieServiceModel.getInstance();
    InformatieServiceBeknoptModel informatieServiceBeknoptModel = InformatieServiceBeknoptModel.getInstance();
    UserGegevensModel userGegevensModel = UserGegevensModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanvraag);

        serviceTag = (TextView) findViewById(R.id.serviceTextViewServiceAanvraag);
        serviceInfo = (TextView) findViewById(R.id.servicesInfoAanvraag);

        naam = (EditText) findViewById(R.id.naamEdit);
        adres = (EditText) findViewById(R.id.adresEdit);
        tel = (EditText) findViewById(R.id.telEdit);
        mail = (EditText) findViewById(R.id.mailEdit);

        serviceTag.setText(serviceLijstModel.getServicesLijst().get(serviceLijstModel.getSelectedService()));
        // Tonen van beknopte info
        serviceInfo.setText(informatieServiceBeknoptModel.getShortInfoService());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Get user gegevens uit de textfields
    public boolean getUserGegevens()
    {
        boolean compleet = true;
        if (naam.getText().length() > 3){
            userGegevensModel.setUserNaam(String.valueOf(naam.getText()));
            compleet = true;
        }
        else{
            Toast.makeText(this,"Voer uw Naam in",
                    Toast.LENGTH_SHORT).show();
            compleet = false;
        }

        if (adres.getText().length() > 3){
            userGegevensModel.setUserAdres(String.valueOf(adres.getText()));
            compleet = true;
        }
        else{
            Toast.makeText(this,"Voer uw Adres in",
                    Toast.LENGTH_SHORT).show();
            compleet = false;

        }

        if (tel.getText().length() > 3){
            userGegevensModel.setUserTel(String.valueOf(tel.getText()));
            compleet = true;
        }
        else{
            Toast.makeText(this,"Voer uw tel in",
                    Toast.LENGTH_SHORT).show();
            compleet = false;

        }

        if (mail.getText().length() > 3){
            userGegevensModel.setUserNaam(String.valueOf(mail.getText()));
            compleet = true;
        }
        else{
            Toast.makeText(this,"Voer uw Email in",
                    Toast.LENGTH_SHORT).show();
            compleet = false;

        }
        return compleet;
    }

    // functie om met de button verder te gaan naar de volgende pagina.
    public void aanvraag(View view){

   //     Log.v("wiebe", String.valueOf(getUserGegevens()));

        if (settingsData.getisOnline() == true && getUserGegevens() == true)
        {
            // Verzend de aanvraag
            getAanvraag();
        }

        else{
            Toast.makeText(this,"Geen aanvraag mogelijk ivm connectie",
                    Toast.LENGTH_SHORT).show();
        }

    }

    // functie om met de button verder te gaan naar de vorige pagina.
    public void prevPage(View view){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
        finish();
    }

// TODO HIER BEZIG

    // Functie om de aanvraag te verwerken
    public void getAanvraag() {
        //aanmaken van een nieuw jsonobject
        JSONObject sendObject = new JSONObject();
// Opbouwen van de te verzenden json array
        JSONArray koperInfoArray = new JSONArray();
// Voeg de service naam toe
        JSONObject serviceNameObject = new JSONObject();
        try {
            serviceNameObject.put("servicenaam",serviceLijstModel.getServicesLijst().get(serviceLijstModel.getSelectedService()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        koperInfoArray.put(serviceNameObject);
   // Voeg de naam toe
        JSONObject nameObject = new JSONObject();
        try {
            nameObject.put("kopernaam",userGegevensModel.getUserNaam());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        koperInfoArray.put(nameObject);
   // Voeg de adres toe
        JSONObject adresObject = new JSONObject();
        try {
            adresObject.put("koperadres",userGegevensModel.getUserAdres());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        koperInfoArray.put(adresObject);

        // Voeg de telnr toe
        JSONObject telnrObject = new JSONObject();
        try {
            telnrObject.put("kopertelnr",userGegevensModel.getUserTel());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        koperInfoArray.put(telnrObject);

        // Voeg de email toe
        JSONObject emailObject = new JSONObject();
        try {
            emailObject.put("koperemail","MEHEHHEH");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        koperInfoArray.put(emailObject);





        //
        try {
            //verzenden van het jsonobject
            sendObject.put("aanvraag",koperInfoArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String reactie = "string";
        {
            //servercommunicator proberen te verbinden met de server
            try {
                reactie = new ClientHelper(this, settingsData.getIp4Adress(), 4444, sendObject.toString()).execute().get();

                Log.v("wiebe`",reactie);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            Log.v("wiebe12",reactie);


        }
        Log.v("wiebeqqwqw3",reactie);
    }
}
