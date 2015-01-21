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

        Log.v("wiebe", String.valueOf(getUserGegevens()));

        if (settingsData.getisOnline() == true)
        {


        }

    }

    // functie om met de button verder te gaan naar de vorige pagina.
    public void prevPage(View view){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
        finish();
    }
}
