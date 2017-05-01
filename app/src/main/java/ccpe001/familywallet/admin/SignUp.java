package ccpe001.familywallet.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import ccpe001.familywallet.R;


public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button signUpBtn,fbUpBtn,googleUpBtn;

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
        googleUpBtn= (Button)findViewById(R.id.googleOptBtn);
        signUpBtn.setOnClickListener(this);
        fbUpBtn.setOnClickListener(this);
        googleUpBtn.setOnClickListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem m) {
        if(m.getItemId()== R.id.nav_next){
            startActivity(new Intent(this,SignIn.class));//this to sign in
        }
        return super.onOptionsItemSelected(m);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem inflater;
        getMenuInflater().inflate(R.menu.sign_menu,menu);
        inflater = menu.findItem(R.id.nav_next);

        return true;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.signupBtn){
            Intent intent = new Intent("ccpe001.familywallet.GETINFO");
            startActivity(intent);
        }
    }



    /*<activity
    android:name=".MainActivity"
    android:theme="@style/AppTheme.NoActionBar"

    android:label="@string/title_activity_navigation_drawer"
            >
            <intent-filter>

                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>

            </intent-filter>
        </activity>*/


}
