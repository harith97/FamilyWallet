package ccpe001.familywallet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by harithaperera on 5/28/17.
 */
public class Forgot extends AppCompatActivity implements View.OnClickListener {

    private Button sendBtn;
    private TextView textForTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot);
        init();
    }

    private void init() {
        sendBtn = (Button) findViewById(R.id.sendMail);
        textForTxt = (TextView) findViewById(R.id.emailTxtFor);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.sendMail){

            if(Validate.anyValidMail()){
                sendBtn.setClickable(true);
                //send mail func
                Toast.makeText(this,"Reset successful",Toast.LENGTH_SHORT).show();
            }else {
                textForTxt.setError("Invalid email");
                sendBtn.setClickable(false);
            }

        }
    }
}
