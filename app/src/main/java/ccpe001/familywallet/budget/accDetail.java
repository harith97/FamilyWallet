package ccpe001.familywallet.budget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import ccpe001.familywallet.R;

public class accDetail extends AppCompatActivity {
    private String[] arraySpinner;
    Button btUpdate,btDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_detail);
        this.arraySpinner = new String[] {
                "1234", "2345"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        s.setAdapter(adapter);
        btUpdate=(Button)findViewById(R.id.btnUpdate);
        btDelete=(Button)findViewById(R.id.transfer);

    }
}


