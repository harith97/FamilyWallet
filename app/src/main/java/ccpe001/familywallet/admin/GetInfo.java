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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info);
        init();
    }

    private void init() {
        imageButton = (at.markushi.ui.CircleButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        if(!hasCamera()){
            imageButton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.imageButton){
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent,RQ_CAPTURE);

            //Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //startActivityForResult(galleryIntent,RQ_GALLERY_REQUEST);
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
            Toast.makeText(this, "Unable to Open file.", Toast.LENGTH_SHORT).show();
            Uri imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }
    }
}
