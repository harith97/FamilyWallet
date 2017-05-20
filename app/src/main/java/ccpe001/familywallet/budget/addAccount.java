package ccpe001.familywallet.budget;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import ccpe001.familywallet.R;

public class addAccount extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(),"Add wallet",Toast.LENGTH_LONG).show();

        View view = inflater.inflate(R.layout.add_account,container, false);
        return view;
    }
}
