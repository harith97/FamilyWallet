package ccpe001.familywallet.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import ccpe001.familywallet.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by harithaperera on 4/30/17.
 */
public class SignIn extends AppCompatActivity implements View.OnClickListener{

    private Button signIn,scannerBtn;
    private TextView toSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle(R.string.signin_title);
        signIn= (Button)findViewById(R.id.signInBtn);
        scannerBtn= (Button)findViewById(R.id.qrscannerBtn);
        toSignUp = (TextView)findViewById(R.id.textView2);
        signIn.setOnClickListener(this);
        scannerBtn.setOnClickListener(this);
        toSignUp.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signInBtn){
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }else if(view.getId()== R.id.qrscannerBtn){
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.QR_CODE_TYPES);
            intentIntegrator.setPrompt("Scan");
            intentIntegrator.setCameraId(0);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setBeepEnabled(false);
            intentIntegrator.setBarcodeImageEnabled(false);
            intentIntegrator.initiateScan();
        }else if(view.getId()== R.id.textView2){
            startActivity(new Intent(this,SignUp.class));//this to sign in
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if (result.getContents()== null){
                Toast.makeText(this,"You cancelled..!",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
    }

    
}
