package ccpe001.familywallet.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import ccpe001.familywallet.R;

/**
 * Created by harithaperera on 4/30/17.
 */
public class SignIn extends AppCompatActivity implements View.OnClickListener{

    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle(R.string.signin_title);
        signIn= (Button)findViewById(R.id.signInBtn);
        signIn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem m) {
        if(m.getItemId()== R.id.nav_next){
            startActivity(new Intent(this,SignUp.class));//this to sign in
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
        if(view.getId()== R.id.signInBtn){
            Toast.makeText(this,"sdfsdf",Toast.LENGTH_LONG).show();
            Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
            startActivity(intent);
        }
    }
}
