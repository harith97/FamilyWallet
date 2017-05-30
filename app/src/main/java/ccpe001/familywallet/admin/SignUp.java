package ccpe001.familywallet.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ccpe001.familywallet.R;
import ccpe001.familywallet.Validate;


public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button signUpBtn,fbUpBtn,googleUpBtn;
    private EditText emailTxt,passTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle(R.string.signup_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        signUpBtn= (Button)findViewById(R.id.signupBtn);
        fbUpBtn= (Button)findViewById(R.id.fbOptBtn);
        emailTxt= (EditText)findViewById(R.id.emailTxt);
        passTxt= (EditText)findViewById(R.id.passwordTxt);
        googleUpBtn= (Button)findViewById(R.id.googleOptBtn);
        signUpBtn.setOnClickListener(this);
        fbUpBtn.setOnClickListener(this);
        googleUpBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signupBtn){
            if(Validate.anyValidMail(emailTxt.getText().toString())) {
                if(Validate.anyValidPass(passTxt.getText().toString())){
                    Intent intent = new Intent("ccpe001.familywallet.GETINFO");
                    startActivity(intent);
                }else{
                    passTxt.setError("Invalid password");
                }
            }else {
                emailTxt.setError("Invalid email");
            }
        }else if(view.getId()== R.id.fbOptBtn) {
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }else if(view.getId()== R.id.googleOptBtn) {
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }
    }







}
