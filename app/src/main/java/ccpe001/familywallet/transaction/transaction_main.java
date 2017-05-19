package ccpe001.familywallet.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ccpe001.familywallet.R;

public class transaction_main extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn= (ImageButton) findViewById(R.id.btnAddTransaction);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.btnAddTransaction){
            Intent newInt = new Intent("ccpe001.familywallet.add_transaction");
            startActivity(newInt);
        }
    }


}
