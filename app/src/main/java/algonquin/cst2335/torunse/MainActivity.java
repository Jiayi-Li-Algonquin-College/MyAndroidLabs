package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity class represents an Android activity that allows the user to enter and check a password.
 * It uses various methods to validate the password complexity and display feedback messages.
 *
 * @author Jiayi Li
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**This holds the text at the center of the screen*/
    private TextView tv = null;
    /**This holds the password that users entered at the bottom of text*/
    private EditText et = null;
    /**This holds button the at the bottom of the screen*/
    private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}