package ws.marioenco.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import ws.marioenco.MainActivity;
import ws.marioenco.Models.Settings;
import ws.marioenco.R;

/**
 * Created by Wiebe on 1/19/2015.
 */
public class ClientHelper  extends AsyncTask<Void, Void, String>
{

    private String message;
    private String ip;
    private String reactie = null;
    private int port;
    private Context context;

    Settings settingsData = Settings.getInstance();

    public ClientHelper(Context context, String ip, int port, String message )
    {
        // Variabelen welke de communicator nodig heeft.

        this.context = context;
        this.message = message;
        this.ip = ip;
        this.port = port;
    }

    //Deze methode verzend de gegevens naar de server
    private void sendMessage( String message, Socket serverSocket )
    {
        OutputStreamWriter outputStreamWriter = null;

        try
        {
            outputStreamWriter = new OutputStreamWriter(serverSocket.getOutputStream());
        }

        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        if( outputStreamWriter != null )
        {

            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            PrintWriter writer = new PrintWriter( bufferedWriter, true );

            writer.println(message);
        }
    }


    @Override
    protected String doInBackground(Void... params)
    {
        try
        {
            Socket serverSocket = new Socket();
            serverSocket.connect( new InetSocketAddress( this.ip, this.port ), 4000 );

            //verzend een bericht naar de server
            this.sendMessage(message, serverSocket);

            InputStream input;

            //zorgt voor een reactie van de server
            try
            {
                input = serverSocket.getInputStream();
                BufferedReader reactieStreamReader = new BufferedReader(new InputStreamReader(input));

                StringBuilder stringBouwer = new StringBuilder();

            // TODO   hier nalopen waarom de NULL er niet uitgefulterd wordt
                // Er is nu de I ingevoegd zodat er pas 1 x draaien een string wordt toegevoegd

                int i =0;

                String line;

                while ((line = reactieStreamReader.readLine()) != null)
                {

                    if(i <= 0){

                    }
                    else{
                        stringBouwer.append(line);
                    }
                    i++;


                }
                reactieStreamReader.close();

                this.reactie = stringBouwer.toString();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        catch( UnknownHostException e )
        {
            Log.v("debug", "can't find host");
            settingsData.setOnline(false);

            // TODO hier de offline modus maken

        }

        catch( SocketTimeoutException e )
        {
             Log.v("debug", "time-out");
            settingsData.setOnline(false);
        }

        catch (IOException e)
        {
            e.printStackTrace();
            settingsData.setOnline(false);
         }

        return reactie;
    }


}
