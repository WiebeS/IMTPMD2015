package ws.marioenco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import ws.marioenco.Helpers.ClientHelper;
import ws.marioenco.Models.InformatieServiceBeknoptModel;
import ws.marioenco.Models.InformatieServiceModel;
import ws.marioenco.Models.ServiceLijstModel;
import ws.marioenco.Models.Settings;


public class AanvraagActivity extends Activity  {

    // public String ipAdress = "192.168.56.1";
    TextView serviceTag,serviceInfo;


    Settings settingsData = Settings.getInstance();
    ServiceLijstModel serviceLijstModel = ServiceLijstModel.getInstance();
    InformatieServiceModel informatieServiceModel = InformatieServiceModel.getInstance();
    InformatieServiceBeknoptModel informatieServiceBeknoptModel = InformatieServiceBeknoptModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanvraag);

          serviceTag = (TextView) findViewById(R.id.serviceTextViewServiceAanvraag);
          serviceInfo = (TextView) findViewById(R.id.servicesInfoAanvraag);

//// TODO data gesaved ophalen
//            list.add("Riolering1");
//            list.add("Lekkage1");
//            list.add("Prinses in nood1");

        // TONEN VAN GESELECTEERDE SERVICE
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



// functie om met de button verder te gaan naar de volgende pagina.
    public void nextPage(View view){

    }

    // functie om met de button verder te gaan naar de vorige pagina.
    public void prevPage(View view){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
        finish();
    }
}
