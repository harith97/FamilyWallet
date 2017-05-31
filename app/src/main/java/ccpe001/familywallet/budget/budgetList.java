package ccpe001.familywallet.budget;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ccpe001.familywallet.R;



public class budgetList extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        Toast.makeText(getContext(), "Budget", Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.budget_list, container, false);
        final String[] title = {"First", "Second"};
        final String[] catName = {"Food", "Travel"};
        final String[] status = {"Expired", "Ongoing"};
        final Integer[] imgId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        budgetListAd addList = new budgetListAd(getActivity(), title, catName, status, imgId);
        ListView budList = (ListView) view.findViewById(R.id.list);
        budList.setAdapter(addList);
        budList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = ((TextView) view.findViewById(R.id.budStat)).getText().toString();
                Intent newInt1 = new Intent("ccpe001.familywallet.budget.budgetTrack");
                startActivity(newInt1);
                Toast toast = Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        FloatingActionButton f=(FloatingActionButton)view.findViewById(R.id.fab1);
        f.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), "Add new budget", Toast.LENGTH_SHORT);
                toast.show();
                //Intent newInt3 = new Intent("ccpe001.familywallet.budget.BudgetHandling");
               // startActivity(newInt3);
            }
        });

        return view;
    }

}
