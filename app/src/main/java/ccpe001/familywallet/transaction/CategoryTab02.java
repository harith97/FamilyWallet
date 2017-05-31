package ccpe001.familywallet.transaction;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import ccpe001.familywallet.R;

/**
 * Created by Knight on 5/21/2017.
 */

public class CategoryTab02 extends Fragment {
    GridView grid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_tab02, container, false);

        Resources res = getResources();
        final String[] itemname = res.getStringArray(R.array.ExpenseCategory);

        final Integer[] imgid = {
                R.drawable.cat100,R.drawable.cat101,R.drawable.cat102,R.drawable.cat103,
                R.drawable.cat104,R.drawable.cat_other,
        };

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), itemname, imgid);
        grid = (GridView) view.findViewById(R.id.tab02_list);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getActivity(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}
