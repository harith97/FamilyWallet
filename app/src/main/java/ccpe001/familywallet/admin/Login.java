package ccpe001.familywallet.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ccpe001.familywallet.R;

/**
 * Created by Knight on 4/23/2017.
 */

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();
    }

    private void init() {
    }
}

/*<activity android:name=".MainActivity"
        android:windowSoftInputMode="adjustPan">
<intent-filter>
<action android:name="android.intent.action.MAIN" />

<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>*/

//<action android:name="ccpe001.familywallet.LOGIN" />
//<category android:name="android.intent.category.DEFAULT" />