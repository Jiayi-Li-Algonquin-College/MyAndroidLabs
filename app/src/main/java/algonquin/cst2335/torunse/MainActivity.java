package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        queue = Volley.newRequestQueue(this);

        mainBinding.getForecast.setOnClickListener(click -> {
            cityName = mainBinding.cityTextField.getText().toString();
            String stringURL = null;

                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(cityName, "UTF-8")
                        + "&appid=a98af505e8630cb978c87fdd695e495f&units=metric";


            //dsfasdfsafddsaf
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {    },
                    (error) -> {    });
            queue.add(request);
        });



    }
}