package ws.marioenco;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ws.marioenco.Controllers.ServicesController;
import ws.marioenco.Helpers.ClientHelper;
import ws.marioenco.Helpers.CustomOnItemSelectedListener;
import ws.marioenco.Models.Settings;


public class MainActivity extends Activity {

    // public String ipAdress = "192.168.56.1";
    TextView bisInfo;
    Button nextButton;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bisInfo = (TextView) findViewById(R.id.bisInfoTextView);
        //  bisInfo.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");

        nextButton = (Button) findViewById(R.id.nextButton);


        spinner1 = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Riolering");
        list.add("Lekkage");
        list.add("Prinses in nood");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);


        spinner1.setAdapter(dataAdapter);

        // Spinner item selection Listener
        addListenerOnSpinnerItemSelection();

//        // Button click Listener
//        addListenerOnButton();


    }

    // Add spinner data

    public void addListenerOnSpinnerItemSelection() {

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    //get the selected dropdown list value


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

    public void sendIP(View view) {

        //aanmaken van een nieuw jsonobject
        JSONObject serviceObject = new JSONObject();
        try {
            //verzenden van het jsonobject
            serviceObject.put("informatie","Riolering");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String reactie = "string";
        {
            Settings settingsData = Settings.getInstance();

            //servercommunicator proberen te verbinden met de server
            try {
                reactie = new ClientHelper(this, settingsData.getIp4Adress(), 4444, serviceObject.toString()).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //        reactie = new ClientHelper( this, ipAdress, 4444,"informatie").execute().get();

            // TODO De reactie doorloopen, en weer een JSON van bouwen?!
            Log.v("wiebe", reactie + "   ");

//            JSONArray reactieArray = null;
//            try
//            {
//                reactieArray = new JSONArray(reactie);
//
//                Log.v("wiebe", "wiebe"+reactieArray);
//            }
//            catch (JSONException e)
//            {
//                e.printStackTrace();
//                Log.v("wiebe", "wiebe"+reactieArray);
//
//            }

        }

    }
}
//            Voor elke service ontvangen 1 opslaan
//
//            for(String test : reactie ){
//
//
//            }
//String reactieFormat = reactie.replace("null", "{\n" +
//        "    \"Array\": ");
//reactieFormat = reactieFormat + "}";
//                JSONObject xxx = new JSONObject(reactieFormat);
//                JSONArray test = xxx.getJSONArray("Array");
//                try {
//                    Log.v("reactie",""+reactieFormat);
//                     test = new JSONArray(reactieFormat);
//                    Log.v("ja",""+test);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    for (int x = 0; x < test.length(); x++){
//JSONObject naamobject = (JSONObject) test.get(x);
//                        String naam = naamobject.getString("naam");
//                        Log.v("TESTERSENS", "" + naam);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
