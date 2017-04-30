package ccpe001.familywallet.summary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ccpe001.familywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class summarymain extends Fragment {


    public summarymain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summarymain, container, false);
    }

}
