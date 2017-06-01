package ccpe001.familywallet.budget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ccpe001.familywallet.R;

public class addAccount extends Fragment  {
    String m_txt="";
    private String[] arraySpinner;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(),"Wallet",Toast.LENGTH_LONG).show();
        this.arraySpinner = new String[] {
                "Wallet", "Bank account"
        };

        View v = inflater.inflate(R.layout.add_account,container, false);
        final ArrayList<String> providerlist= new ArrayList<String>();
        Spinner s = (Spinner) v.findViewById(R.id.accType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
        android.R.layout.simple_dropdown_item_1line, arraySpinner);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new OnItemSelectedListener() {
            private AlertDialog.Builder box=null;
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String selected = parent.getItemAtPosition(pos).toString();
                Toast.makeText(getActivity(), selected, Toast.LENGTH_LONG).show();
                if(selected=="Bank account"){
                    box = new AlertDialog.Builder(getContext());
                    box.setTitle("Attention");
                    box.setMessage("Enter bank account ID :");
                    final EditText input = new EditText(getActivity());
                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    box.setView(input);
                    box.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_txt = input.getText().toString();
                            if(m_txt.matches(".*[a-z0-9].*")){
                                Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "Wrong inputs", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    box.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    box.show();
                }

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
                return v;
    }

    }
