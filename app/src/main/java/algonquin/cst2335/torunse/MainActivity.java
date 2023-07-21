package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Format;

import algonquin.cst2335.torunse.R;
import algonquin.cst2335.torunse.databinding.ActivityMainBinding;

/**
 * MainActivity class represents an Android activity that allows the user to enter and check a password.
 * It uses various methods to validate the password complexity and display feedback messages.
 *
 * @author Jiayi Li
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    protected String cityName;
    String iconName;
    Bitmap image;
    RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        queue = Volley.newRequestQueue(this);

        mainBinding.forecastButton.setOnClickListener(click -> {
            cityName = mainBinding.cityTextField.getText().toString();
            String stringURL = null;

            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(cityName, "UTF-8")
                        + "&appid=a98af505e8630cb978c87fdd695e495f&units=metric";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            Toast.makeText(MainActivity.this, stringURL,
                    Toast.LENGTH_LONG).show();

            //dsfasdfsafddsaf
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try{
                            JSONObject coord = response.getJSONObject("coord");
                            JSONArray weatherArray = response.getJSONArray ( "weather" );
                            int vis = response.getInt("visibility");
                            String name = response.getString( "name" );
                            JSONObject position0 = weatherArray.getJSONObject(0);
                            String description = position0.getString("description");




//                             iconName = position0.getString("icon");
                             iconName = "01d";




                            JSONObject mainObject = response.getJSONObject( "main" );
                            double current = mainObject.getDouble("temp");
                            double min = mainObject.getDouble("temp_min");
                            double max = mainObject.getDouble("temp_max");
                            int humidity = mainObject.getInt("humidity");





                            runOnUiThread( (  )  -> {

                                mainBinding.temp.setText("The current temperature is "  + current);
                                mainBinding.temp.setVisibility(View.VISIBLE);

                                mainBinding.minTemp.setText("The min temperature is "  + min);
                                mainBinding.minTemp.setVisibility(View.VISIBLE);

                                mainBinding.maxTemp.setText("The max temperature is "  + max);
                                mainBinding.maxTemp.setVisibility(View.VISIBLE);

                                mainBinding.humitidy.setText("The humitidy is " + humidity +"%");
                                mainBinding.humitidy.setVisibility(View.VISIBLE);

                                mainBinding.icon.setImageBitmap(image);
                                mainBinding.icon.setVisibility(View.VISIBLE);

                                mainBinding.description.setText(description);
                                mainBinding.description.setVisibility(View.VISIBLE);

                            });

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    },
                    (error) -> {    });
            queue.add(request);



//                            String pathname = getFilesDir() + "/" + iconName + ".png";
//                            File file = new File(pathname);
//                            if(file.exists())
//                            {
//                                image = BitmapFactory.decodeFile(pathname);
//                            }
//                            else {
//                                ImageRequest imgReq = new ImageRequest( "https://openweathermap.org/img/w/" + iconName + ".png", new Response.Listener<Bitmap>() {
//                                    @Override
//                                    public void onResponse (Bitmap bitmap) {
////                                        FileOutputStream fOut = null;
//                                        try {
//                                            // Do something with loaded bitmap...
////                                            fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
//                                            image = bitmap;
//                                            image.compress (Bitmap.CompressFormat.PNG,  100, MainActivity.this.openFileOutput( iconName + ".png", Activity.MODE_PRIVATE));
//                                        }
//                                        catch (Exception e) {
//                                        }
//                                    }
//                                },  1024,  1024, ImageView.ScaleType.CENTER,  null, (error) -> {   });
//                            }

            ImageRequest imgReq = new ImageRequest("https://openweathermap.org/img/w/" + iconName + ".png", new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    // Do something with loaded bitmap...
                    FileOutputStream fOut = null;
                    try {
                        fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                        image = bitmap;
                        image.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                        runOnUiThread( (  )  -> {
                            mainBinding.icon.setImageBitmap(image);
                            mainBinding.icon.setVisibility(View.VISIBLE);
                        });

                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error ) -> {

            });
        queue.add(imgReq);











        });



    }
}



















