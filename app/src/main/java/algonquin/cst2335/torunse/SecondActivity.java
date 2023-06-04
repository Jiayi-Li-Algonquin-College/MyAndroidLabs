package algonquin.cst2335.torunse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button pictureButton;
    Button callButton;
    String emailAddress;
    int phoneNumber;
    EditText editTextPhone;
    ImageView profileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        Intent call = new Intent(Intent.ACTION_DIAL);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        emailAddress = "shouldn't show this!😅";
        emailAddress = fromPrevious.getStringExtra("EmailAddress");

        pictureButton = findViewById(R.id.pictureButton);
        callButton = findViewById(R.id.callButton);
        profileImage = findViewById(R.id.profileImage);

        callButton.setOnClickListener( clk-> {
            //Toast.makeText(getApplicationContext(), emailAddress,  Toast.LENGTH_SHORT).show();
            phoneNumber = Integer.parseInt( editTextPhone.getText().toString() );
            call.setData(Uri.parse("tel:" + phoneNumber));
        } );

        pictureButton.setOnClickListener( clk-> {

        } );

        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity. RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        profileImage.setImageBitmap( thumbnail );
                    }
                }
            });
        cameraResult.launch(cameraIntent);






    }
}