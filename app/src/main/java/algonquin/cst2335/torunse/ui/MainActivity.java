package algonquin.cst2335.torunse.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.torunse.R;
import algonquin.cst2335.torunse.data.MainViewModel;
import algonquin.cst2335.torunse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 4
        model = new ViewModelProvider(this).get(MainViewModel.class);


        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;

        variableBinding.textview.setText((CharSequence) model.editString);
        model.editString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                variableBinding.textview.setText("Your edit text has "+ s);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

//            The View v parameter in the onClick() method represents the view that was clicked to trigger the event.
//            In this case, it is used to indicate which view the user clicked to trigger the Toast message.
//            However, since it is not being used in the actual implementation of the method,
//            it could be removed without affecting the functionality of the code.
//            The parameter is commonly used when multiple views are associated with a single listener,
//            and it helps to distinguish which view was clicked.
            @Override
            public void onClick(View v) {
                model.editString.postValue(variableBinding.myedittext.getText().toString());
                variableBinding.textview.setText("Your edit text has: " + model.editString);
            }
        });
        /*
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
        });*/







    }
}