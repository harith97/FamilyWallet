package ccpe001.familywallet.transaction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ccpe001.familywallet.R;

/**
 * Created by Knight on 5/21/2017.
 */

public class CategoryTab01 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_tab01, container, false);
        return rootView;
    }
}
