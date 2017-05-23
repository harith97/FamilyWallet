package ccpe001.familywallet.budget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ccpe001.familywallet.R;

public class accUpdate extends Fragment implements View.OnClickListener {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(),"transfer money",Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.acc_update, container, false);
        return view;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDtl:
                Intent newInt1 = new Intent(getActivity(),accDelete.class);
                getActivity().startActivity(newInt1);
        }
    }
}
