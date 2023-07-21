package algonquin.cst2335.torunse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import algonquin.cst2335.torunse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    protected String cityName;
    protected String stringURL;
    protected RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getForecast.setOnClickListener(click -> {
            cityName = binding.editText.getText().toString();
            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q=" +
                        URLEncoder.encode(cityName, "UTF-8") +
                        "&appid=7e943c97096a9784391a981c4d878b22&units=metric";
            } catch (Exception e) {
                e.printStackTrace();
            }

            queue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    stringURL,
                    null,
                    response -> {
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject position0 = weatherArray.getJSONObject(0);
                            JSONObject mainObject = response.getJSONObject("main");

                            double current = mainObject.getDouble("temp");
                            double minTemp = mainObject.getDouble("temp_min");
                            double maxTemp = mainObject.getDouble("temp_max");
                            int humidity = mainObject.getInt("humidity");
                            String description = position0.getString("description");
                            String iconName = position0.getString("icon");

                            // Download icon image and save it to the device
                            String imageUrl = "https://openweathermap.org/img/w/" + iconName + ".png";
                            ImageRequest imgReq = new ImageRequest(imageUrl,
                                    bitmap -> {
                                        binding.weatherIcon.setImageBitmap(bitmap);
                                        saveIconToStorage(iconName, bitmap);
                                    }, 1024, 1024, ImageView.ScaleType.CENTER, null,
                                    error -> {
                                        // Handle error
                                    });
                            queue.add(imgReq);

                            // Update the UI
                            runOnUiThread(() -> {
                                binding.temp.setText("The current temperature is " + current + "°C");
                                binding.temp.setVisibility(View.VISIBLE);

                                binding.minTemp.setText("The min temperature is " + minTemp + "°C");
                                binding.minTemp.setVisibility(View.VISIBLE);

                                binding.maxTemp.setText("The max temperature is " + maxTemp + "°C");
                                binding.maxTemp.setVisibility(View.VISIBLE);

                                binding.humidity.setText("Humidity: " + humidity + "%");
                                binding.humidity.setVisibility(View.VISIBLE);

                                binding.description.setText(description);
                                binding.description.setVisibility(View.VISIBLE);

                                binding.weatherIcon.setVisibility(View.VISIBLE);
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        Toast.makeText(MainActivity.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                    });
            queue.add(request);
        });
    }

    // Save the weather icon to device storage
    private void saveIconToStorage(String iconName, Bitmap image) {
        try {
            FileOutputStream fOut = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
