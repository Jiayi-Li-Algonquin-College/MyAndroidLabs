package algonquin.cst2335.torunse.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        model.isSelected.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);

            Toast.makeText(getApplicationContext(), "The value is now: " + model.isSelected,  Toast.LENGTH_LONG).show();
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.editString.postValue(variableBinding.myedittext.getText().toString());
                variableBinding.textview.setText("Your edit text has: " + model.editString);
            }
        });


        variableBinding.radioButton.setOnCheckedChangeListener((button, isChecked) -> {

            model.isSelected.postValue(isChecked);
        });

        variableBinding.switch1.setOnCheckedChangeListener((button, isChecked) -> {

            model.isSelected.postValue(isChecked);
        });

        variableBinding.checkBox.setOnCheckedChangeListener((button, isChecked) -> {

            model.isSelected.postValue(isChecked);
        });

        variableBinding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hey!",  Toast.LENGTH_LONG).show();
            }
        });
        int width = variableBinding.myimagebutton.getWidth();
        int height = variableBinding.myimagebutton.getHeight();
        variableBinding.myimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "The width = " + width + " and height = " + height,  Toast.LENGTH_LONG).show();
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