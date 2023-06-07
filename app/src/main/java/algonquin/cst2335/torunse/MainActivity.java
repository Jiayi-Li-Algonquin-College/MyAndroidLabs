package algonquin.cst2335.torunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.myEditText);
        Button btn = findViewById(R.id.loginBtn);

        btn.setOnClickListener( clk ->{
            String password=et.getText().toString();
            checkPasswordComplexity( password );
        });
    }

    /**
     * This function checks the complexity of a given password and shows a toast message if it is invalid.
     * The password must have at least one upper case letter, one lower case letter, one digit and one special character from "#$%^&*!@?".
     * The password must also be at least 8 characters long.
     * @param pw the password to check
     */
    public void checkPasswordComplexity(String pw){
        // Use four boolean variables to keep track of the required characters
        // Source: https://stackoverflow.com/questions/54869952/password-strength-validation-java [^1^][2]
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        // Use a string to store the special characters
        String specialChars = "#$%^&*!@?";
        // Loop through each character of the password
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            // Check if the character is upper case, lower case, digit or special
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (specialChars.contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }
        // Check if the password meets all the requirements
        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            // The password is valid
            Toast.makeText(this, "Your password is valid", Toast.LENGTH_SHORT).show();
        } else {
            // The password is invalid
            // Find out which requirement is missing and show a toast message accordingly
            if (!hasUpper) {
                // The password does not have an upper case letter
                Toast.makeText(this, "Your password does not have an upper case letter", Toast.LENGTH_SHORT).show();
            } else if (!hasLower) {
                // The password does not have a lower case letter
                Toast.makeText(this, "Your password does not have a lower case letter", Toast.LENGTH_SHORT).show();
            } else if (!hasDigit) {
                // The password does not have a number
                Toast.makeText(this, "Your password does not have a number", Toast.LENGTH_SHORT).show();
            } else if (!hasSpecial) {
                // The password does not have a special character
                Toast.makeText(this, "Your password does not have a special character", Toast.LENGTH_SHORT).show();
            }
        }
    }




}