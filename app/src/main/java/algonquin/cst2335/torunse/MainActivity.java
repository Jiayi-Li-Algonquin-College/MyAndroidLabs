package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        Button my_button = (Button) findViewById(R.id.my_button);
        my_button.setOnClickListener(new View.OnClickListener() {
            /*
            The View v parameter in the onClick() method represents the view that was clicked to trigger the event.
            In this case, it is used to indicate which view the user clicked to trigger the Toast message.
            However, since it is not being used in the actual implementation of the method,
            it could be removed without affecting the functionality of the code.
            The parameter is commonly used when multiple views are associated with a single listener,
            and it helps to distinguish which view was clicked.*/
            @Override
            public void onClick(View v) {
                String toast_message = getResources().getString(R.string.toast_message);
                //CONTEXT??????????????❓❓❓❓❓❓❓❓❓❓❔❔❔❔❔❔❔❔❔❔⁉⁉⁉⁉⁉⁉⁉⁉⁉⁉‼‼‼‼‼‼
                Toast.makeText(getApplicationContext(), toast_message, Toast.LENGTH_LONG).show();
            }
        });

        Switch bar_switch = (Switch) findViewById(R.id.bar_switch);
        //CompoundButton??????????????❓❓❓❓❓❓❓❓❓❓❔❔❔❔❔❔❔❔❔❔⁉⁉⁉⁉⁉⁉⁉⁉⁉⁉‼‼‼‼‼‼
        bar_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton switch_bt, boolean isChecked) {

                String message;
                if(isChecked){
                    message = "on";
                } else{
                    message = "off";
                }
                                                                                    //how to translate here?❓❓❓❓❓❓❓❓???????
                Snackbar snackbar = Snackbar.make((Switch) findViewById(R.id.bar_switch), "@string/Lab2_Snack_Message" + message, Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bar_switch.setChecked(!isChecked);
                    }
                });
                snackbar.show();
            }
        });







    }
}