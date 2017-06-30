package ccpe001.familywallet.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import ccpe001.familywallet.R;
import ccpe001.familywallet.Validate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by harithaperera on 4/30/17.
 */
public class SignIn extends AppCompatActivity implements View.OnClickListener{

    private Button signIn,scannerBtn;
    private TextView toSignUp,forgotTxt;
    private EditText emailTxt,passTxt;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

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
        forgotTxt = (TextView)findViewById(R.id.textView);
        emailTxt = (EditText)findViewById(R.id.emailTxt);
        passTxt = (EditText)findViewById(R.id.passwordTxt);

        signIn.setOnClickListener(this);
        scannerBtn.setOnClickListener(this);
        toSignUp.setOnClickListener(this);
        forgotTxt.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            finish();
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }
    }




    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signInBtn){
            if(Validate.anyValidMail(emailTxt.getText().toString().trim())) {
                if(Validate.anyValidPass(passTxt.getText().toString().trim())){
                    progressDialog.setMessage("Wait..");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(emailTxt.getText().toString().trim(),
                            passTxt.getText().toString().trim())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        finish();
                                        Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(SignIn.this,"Sign In Error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    passTxt.setError("Invalid password");
                }
            }else {
                emailTxt.setError("Invalid email");
            }
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
            startActivity(new Intent(this,SignUp.class));
        }else if(view.getId()== R.id.textView){
            Intent intent = new Intent(this,Forgot.class);
            startActivity(intent);
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
