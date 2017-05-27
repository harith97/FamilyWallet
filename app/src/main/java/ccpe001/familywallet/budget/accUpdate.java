package ccpe001.familywallet.budget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ccpe001.familywallet.R;

public class accUpdate extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext(),"transfer money",Toast.LENGTH_LONG).show();
        View v = inflater.inflate(R.layout.acc_update, container, false);

        return v;
        }
    }


