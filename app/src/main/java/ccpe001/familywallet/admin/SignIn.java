package ccpe001.familywallet.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import ccpe001.familywallet.R;

/**
 * Created by harithaperera on 4/30/17.
 */
public class SignIn extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle(R.string.signin_title);

    }
}
