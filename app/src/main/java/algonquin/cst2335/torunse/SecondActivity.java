package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button pictureButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");



        pictureButton = findViewById(R.id.pictureButton);
        pictureButton.setOnClickListener( clk-> {

        } );


    }

    //Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
}