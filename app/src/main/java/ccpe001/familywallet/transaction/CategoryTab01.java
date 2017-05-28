package ccpe001.familywallet.transaction;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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


        final String[] itemname = { "Food","Car","Entertainment","Clothes","Travel","Shopping","Bill","Holiday"};

        Integer[] imgid = {R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
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
                getActivity().finish();
                startActivity(intent);



            }
        });
        return view;

    }




}
