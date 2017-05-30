package ccpe001.familywallet.transaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import ccpe001.familywallet.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Knight on 5/21/2017.
 */

public class CategoryTab01 extends Fragment {

    GridView grid;
    TextView txtCategory;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_tab01, container, false);

        Resources res = getResources();
        final String[] itemname = res.getStringArray(R.array.IncomeCategory);

        final Integer[] imgid = {
                R.drawable.cat1,R.drawable.cat2,R.drawable.cat3,R.drawable.cat4,
                R.drawable.cat5,R.drawable.cat6,R.drawable.cat7,R.drawable.cat8,R.drawable.cat9,
                R.drawable.cat10,R.drawable.cat11,R.drawable.cat12,R.drawable.cat13,R.drawable.cat14,
                R.drawable.cat15,R.drawable.cat16,R.drawable.cat17,R.drawable.cat18,R.drawable.cat19,
        };

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), itemname, imgid);
        grid = (GridView) view.findViewById(R.id.tab01_list);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                txtCategory = (TextView) view.findViewById(R.id.txtCategory);

                String category = itemname[+position];
                Intent intent = new Intent("ccpe001.familywallet.add_transaction");
                intent.putExtra("category",category);
                intent.putExtra("categoryID",imgid[+position]);
                Log.d("knight",""+R.drawable.cat1);
                getActivity().finish();
                startActivity(intent);



            }
        });
        return view;

    }




}
