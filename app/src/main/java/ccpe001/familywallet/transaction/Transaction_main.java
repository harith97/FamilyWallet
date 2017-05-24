package ccpe001.familywallet.transaction;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ccpe001.familywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Transaction_main extends Fragment {

    ListView list;
    FloatingActionButton fab_income, fab_expense,fab_main;
    Animation fabOpen, fabClose, fabClockwise, fabAntiClockwise;
    TextView txtIncome,txtExpense;
    boolean isOpen = false;
    CoordinatorLayout layout;

    public Transaction_main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.transaction_main, container, false);
        txtExpense = (TextView) view.findViewById(R.id.txtExpense);
        txtIncome = (TextView) view.findViewById(R.id.txtIncome);
        layout = (CoordinatorLayout) view.findViewById(R.id.layout);
        fab_main = (FloatingActionButton) view.findViewById(R.id.fabMain);
        fab_expense = (FloatingActionButton) view.findViewById(R.id.fabExpense);
        fab_income = (FloatingActionButton) view.findViewById(R.id.fabIncome);
        fabOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockwise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_anticlockwise);
        fab_main.startAnimation(fabOpen);
        fab_main.setVisibility(view.VISIBLE);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isOpen){

                }
                else {
                    fab_income.startAnimation(fabOpen);
                    fab_expense.startAnimation(fabOpen);
                    fab_income.setClickable(true);
                    fab_expense.setClickable(true);
                    fab_main.startAnimation(fabClose);
                    fab_main.setVisibility(view.GONE);
                    txtExpense.setVisibility(view.VISIBLE);
                    txtIncome.setVisibility(view.VISIBLE);
                    isOpen = true;



                }

                if (isOpen) {
                    fab_income.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent("ccpe001.familywallet.add_transaction");
                            startActivity(intent);
                        }
                    });
                    fab_expense.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent("ccpe001.familywallet.add_transaction");
                            startActivity(intent);
                        }
                    });
                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isOpen) {
                                fab_income.startAnimation(fabClose);
                                fab_expense.startAnimation(fabClose);
                                fab_income.setClickable(false);
                                fab_expense.setClickable(false);
                                fab_main.startAnimation(fabOpen);
                                fab_main.setVisibility(v.VISIBLE);
                                txtExpense.setVisibility(v.GONE);
                                txtIncome.setVisibility(v.GONE);
                                isOpen = false;
                            }
                        }
                    });
                }

            }
        });


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
