package ccpe001.familywallet.transaction;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccpe001.familywallet.R;

import static android.R.attr.data;

public class add_transaction extends AppCompatActivity {


    private TextView txtLocation,txtCategory;
    int PLACE_PICKER_REQUEST=1;
    private EditText txtAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtAmount =(EditText)findViewById(R.id.txtAmount);
        txtLocation = (TextView)findViewById(R.id.txtLocation);
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlacePickerActivity();
            }
        });
        txtCategory = (TextView)findViewById(R.id.txtCategory);
        txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newInt = new Intent(add_transaction.this,ccpe001.familywallet.transaction.transaction_category.class);
                startActivityForResult(newInt,1);
            }
        });
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("categoryID");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("categoryID");
        }
        txtCategory.setText(newString);
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
        public CharSequence filter(
                CharSequence source,
                int start,
                int end,
                Spanned dest,
                int dstart,
                int dend) {

            String result =
                    dest.subSequence(0, dstart)
                            + source.toString()
                            + dest.subSequence(dend, dest.length());

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






}
