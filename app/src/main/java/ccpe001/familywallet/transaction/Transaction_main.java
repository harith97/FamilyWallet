package ccpe001.familywallet.transaction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.dynamic.LifecycleDelegate;

import ccpe001.familywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Transaction_main extends Fragment {

    ListView list;

    public Transaction_main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.transaction_main, container, false);


        final String[] Title = {
                "Lunch",
                "Repair",
                "Movie",
                "Denim",
                "Vacation",
                "Shoes",
                "Electric",
                "Table"
        };
        final String[] Category = {
                "Food",
                "Car",
                "Entertainment",
                "Clothes",
                "Travel",
                "Shopping",
                "Bill",
                "Furniture"
        };
        final String[] Date = {
                "2017/02/01",
                "2017/02/02",
                "2017/02/03",
                "2017/02/04",
                "2017/02/05",
                "2017/02/06",
                "2017/02/07",
                "2017/02/08"
        };
        final String[] Amount = {
                "Rs.500",
                "Rs.1500",
                "Rs.800",
                "Rs.600",
                "Rs.400",
                "Rs.700",
                "Rs.2500",
                "Rs.4500"
        };

        Integer[] imgid = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
        };

        TransactionListAdapter adapter = new TransactionListAdapter(getActivity(), Title, Category, Date, Amount, imgid);
        list = (ListView) view.findViewById(R.id.transactionList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

}
