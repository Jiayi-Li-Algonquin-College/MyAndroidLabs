package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton


        loginButton.setOnClickListener( clk-> { } );

        Log.w( TAG, "In onCreate() - Loading Widgets" );
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.w( TAG, "In onPause() - The application no longer responds to user input" );
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.w( TAG, "In onStop() - The application is no longer visible." );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.w( TAG, "In onDestroy() - Any memory used by the application is freed." );
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.w( TAG, "In onResume() - The application is now responding to user input" );
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.w( TAG, "In onStart() - The application is now visible on screen." );
    }

}