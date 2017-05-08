package ccpe001.familywallet.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import ccpe001.familywallet.R;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class GetInfo extends AppCompatActivity implements View.OnClickListener{

    private static final int RQ_CAPTURE = 1;
    private static final int RQ_GALLERY_REQUEST = 2;
    private at.markushi.ui.CircleButton imageButton;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info);
        init();
    }

    private void init() {
        imageButton = (at.markushi.ui.CircleButton) findViewById(R.id.imageButton);
        signUpButton = (Button) findViewById(R.id.signUPGetInfo);
        signUpButton.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        if(!hasCamera()){
            imageButton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.imageButton){
            String [] optArr ={"Take a Photo","Select from Gallery"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Option");
            builder.setItems(optArr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int opt) {
                    //Toast.makeText(GetInfo.this,String.valueOf(i),Toast.LENGTH_LONG).show();
                    if(opt==0){
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent,RQ_CAPTURE);
                    }else if(opt==1){
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent,RQ_GALLERY_REQUEST);
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else if(view.getId()==R.id.signUPGetInfo){
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RQ_CAPTURE&&resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap rawImage  = (Bitmap)extras.get("data");
            imageButton.setImageBitmap(rawImage);
        }else if(requestCode==RQ_GALLERY_REQUEST&&resultCode==RESULT_OK){
            Uri imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }
    }
}
