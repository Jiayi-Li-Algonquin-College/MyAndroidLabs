package algonquin.cst2335.torunse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    TextView addressTextView;
    Button pictureButton;
    Button callButton;
    String emailAddress;
    String phoneNumber;
    EditText editTextPhone;
    ImageView profileImage;
    FileOutputStream fOut = null;
    Bitmap thumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        Intent fromPrevious = getIntent();
        Intent call = new Intent(Intent.ACTION_DIAL);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        emailAddress = "shouldn't show this!😅";
        emailAddress = fromPrevious.getStringExtra("EmailAddress");


        addressTextView = findViewById(R.id.addressTextView);

        if (fromPrevious != null) {
            addressTextView.setText(emailAddress);
        }

        pictureButton = findViewById(R.id.pictureButton);
        callButton = findViewById(R.id.callButton);
        profileImage = findViewById(R.id.profileImage);
        editTextPhone = findViewById(R.id.editTextPhone);

        callButton.setOnClickListener( clk-> {
            phoneNumber = editTextPhone.getText().toString();
            call.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(call);
        } );


        ActivityResultLauncher <Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    thumbnail = data.getParcelableExtra("data");
                    profileImage.setImageBitmap( thumbnail );
                }

                try {
                    fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        File file = new File( getFilesDir(), "Picture.png");

        if(file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            profileImage.setImageBitmap(theImage);
        }

        pictureButton.setOnClickListener( clk-> {
            cameraResult.launch(cameraIntent);
        } );

    }
}