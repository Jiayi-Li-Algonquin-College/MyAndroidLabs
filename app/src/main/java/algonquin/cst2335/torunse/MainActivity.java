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
     * Checks if a given character is a special character from "#$%^&*!@?".
     * @param c the character to check
     * @return true if c is a special character, false otherwise
     */
    boolean isSpecialCharacter(char c) {
        //return true if c is one of: #$%^&*!@?
        //return false otherwise
        String specialChars = "#$%^&*!@?";
        return specialChars.contains(String.valueOf(c));
    }

    /**
     * This function checks the complexity of a given password and shows a toast message if it is invalid.
     * The password must have at least one upper case letter, one lower case letter, one digit and one special character from "#$%^&*!@?".
     * The password must also be at least 8 characters long.
     * @param pw the password to check
     * @return Returns the result of the check, return true if the password is acceptable and vice versa.
     */
    public boolean checkPasswordComplexity(String pw){
        // Use four boolean variables to keep track of the required characters
        // Source: https://stackoverflow.com/questions/54869952/password-strength-validation-java [^1^][2]
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;
        // Loop through each character of the password
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            // Check if the character is upper case, lower case, digit or special
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        // Check if the password meets all the requirements
        if (foundUpperCase && foundLowerCase && foundNumber && foundSpecial) {
            // The password is valid
            Toast.makeText(this, "Your password is valid", Toast.LENGTH_SHORT).show();
        } else {
            // The password is invalid
            // Find out which requirement is missing and show a toast message accordingly
            if (!foundUpperCase) {
                // The password does not have an upper case letter
                Toast.makeText(this, "Your password does not have an upper case letter", Toast.LENGTH_SHORT).show();
            } else if (!foundLowerCase) {
                // The password does not have a lower case letter
                Toast.makeText(this, "Your password does not have a lower case letter", Toast.LENGTH_SHORT).show();
            } else if (!foundNumber) {
                // The password does not have a number
                Toast.makeText(this, "Your password does not have a number", Toast.LENGTH_SHORT).show();
            } else if (!foundSpecial) {
                // The password does not have a special character
                Toast.makeText(this, "Your password does not have a special character", Toast.LENGTH_SHORT).show();
            }
        }

        return foundUpperCase && foundLowerCase && foundNumber && foundSpecial;

    }




}