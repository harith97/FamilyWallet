package ccpe001.familywallet.transaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccpe001.familywallet.R;

public class add_transaction extends AppCompatActivity {


    private TextView txtLocation,txtCategory;
    private Spinner spinCurrency, spinAccount;
    int PLACE_PICKER_REQUEST=1;
    private EditText txtAmount, txtDate, txtTime, txtTitle;
    String categoryName, categoryID, title, date, amount,currency,account,time,location;
    Long currencyIndex, accountIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtAmount =(EditText)findViewById(R.id.txtAmount);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtLocation = (TextView) findViewById(R.id.txtLocation);
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        spinCurrency = (Spinner) findViewById(R.id.spinCurrency);
        spinAccount = (Spinner) findViewById(R.id.spinAccount);

        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlacePickerActivity();
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = this.getIntent().getExtras();
            if(extras == null) {
                categoryName = null;
                categoryID = null;
                title = null;
                date = null;
                amount = null;
            } else {
                categoryName = extras.getString("categoryName");
                categoryID = extras.getString("categoryID");
                title = extras.getString("title");
                date = extras.getString("date");
                amount = extras.getString("amount");
            }
        } else {
            categoryName = (String) savedInstanceState.getSerializable("categoryName");
            categoryID = (String) savedInstanceState.getSerializable("categoryID");
            title = (String) savedInstanceState.getSerializable("title");
            date = (String) savedInstanceState.getSerializable("date");
            amount = (String) savedInstanceState.getSerializable("amount");
            txtTitle.setText(title);

        }

        txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = txtAmount.getText().toString();
                date = txtDate.getText().toString();
                time = txtTime.getText().toString();
                title = txtTitle.getText().toString();
                currencyIndex = spinCurrency.getSelectedItemId();
                accountIndex = spinCurrency.getSelectedItemId();
                location = txtLocation.getText().toString();
                Intent intent = new Intent(add_transaction.this,ccpe001.familywallet.transaction.transaction_category.class);
                intent.putExtra("title",title);
                intent.putExtra("amount",amount);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("currencyIndex",currencyIndex);
                intent.putExtra("accountIndex",accountIndex);
                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                categoryName = null;
                categoryID = null;
            } else {
                categoryName = extras.getString("categoryName");
                categoryID = extras.getString("categoryID");
            }
        } else {
            categoryName = (String) savedInstanceState.getSerializable("categoryName");
            categoryID = (String) savedInstanceState.getSerializable("categoryID");
        }

        if (categoryName!=null){
            txtCategory.setText(categoryName);
        }


        txtAmount.setFilters(new InputFilter[] {new CurrencyFormatInputFilter()});
    }



    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();

        //TextView enterCurrentLocation = (TextView) findViewById(R.id.txtLocation);
        txtLocation.setText(name + ", " + address);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        txtCategory = (TextView)findViewById(R.id.txtCategory);
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }

    }

    public class CurrencyFormatInputFilter implements InputFilter {

        Pattern mPattern = Pattern.compile("(0|[1-9]+[0-9]*)?(\\.[0-9]{0,2})?");

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                int dstart, int dend) {

            String result =
                    dest.subSequence(0, dstart) + source.toString() + dest.subSequence(dend, dest.length());

            Matcher matcher = mPattern.matcher(result);

            if (!matcher.matches()) return dest.subSequence(dstart, dend);

            return null;
        }
    }


    public void onStart(){
        super.onStart();
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DateDialog dialog = new DateDialog(v);
                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"DatePicker");

            }
        });

        TextView txtTime = (TextView) findViewById(R.id.txtTime);
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog dialog = new TimeDialog(v);
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialog.show(ft,"TimePicker");

            }
        });



    }

    public void saveTransaction(View view){
        amount = txtAmount.getText().toString();
        date = txtDate.getText().toString();
        title = txtTitle.getText().toString();
        currency = spinCurrency.getSelectedItem().toString();
        Intent intent = new Intent("ccpe001.familywallet.DASHBOARD");
        intent.putExtra("categoryID",categoryID);
        intent.putExtra("categoryName",categoryName);
        intent.putExtra("title",title);
        intent.putExtra("amount",currency+" "+amount);
        intent.putExtra("date",date);
        startActivity(intent);
    }

}
