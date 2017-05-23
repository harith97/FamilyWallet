package ccpe001.familywallet.budget;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import ccpe001.familywallet.R;

public class budgetList extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext(),"Budget",Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.budget_list, container, false);
        return view;
    }
}
