package ws.marioenco.Helpers;

import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import ws.marioenco.MainActivity;
import ws.marioenco.Models.ServiceLijstModel;
import ws.marioenco.R;

/**
 * Created by Wiebe on 1/19/2015.
 */
public class CustomOnItemSelectedListener implements OnItemSelectedListener {

    ServiceLijstModel serviceLijstModel = ServiceLijstModel.getInstance();
    MainActivity main = new MainActivity();

    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {

        Toast.makeText(parent.getContext(),
                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();

        // Het saven van de geselecteerde service
        serviceLijstModel.setSelectedService(pos);


        // Het setten van het services label





    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}