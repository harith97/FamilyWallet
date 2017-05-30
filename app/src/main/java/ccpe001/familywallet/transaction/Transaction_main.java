package ccpe001.familywallet.transaction;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import android.widget.ImageView;
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
    String categoryID, categoryName, title, date, amount;

    public Transaction_main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.transaction_main, container, false);
        if (savedInstanceState == null) {
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras == null) {
                categoryName = null;
                categoryID = null;
                title = null;
                date = null;
                amount = null;
            } else {
                categoryName = extras.getString("categoryName");
                categoryID = extras.getString("categoryID");
                title = extras.getString("title");
                date = extras.getString("date");
                amount = extras.getString("amount");

                final String[] Title = {
                        title
                };
                final String[] Category = {
                        categoryName
                };
                final String[] Date = {
                        date
                };
                final String[] Amount = {
                        amount
                };

                Integer[] imgid = {
                        Integer.parseInt(categoryID)
                };


                TransactionListAdapter adapter = new TransactionListAdapter(getActivity(), Title, Category, Date, Amount, imgid);
                list = (ListView) view.findViewById(R.id.transactionList);
                list.setAdapter(adapter);
            }
        } else {
            categoryName = (String) savedInstanceState.getSerializable("categoryName");
            categoryID = (String) savedInstanceState.getSerializable("categoryID");
            title = (String) savedInstanceState.getSerializable("title");
            date = (String) savedInstanceState.getSerializable("date");
            amount = (String) savedInstanceState.getSerializable("amount");


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();

                }
            });
        }


        txtExpense = (TextView) view.findViewById(R.id.txtExpense);
        txtIncome = (TextView) view.findViewById(R.id.txtIncome);
        fab_main = (FloatingActionButton) view.findViewById(R.id.fabMain);
        fab_expense = (FloatingActionButton) view.findViewById(R.id.fabExpense);
        fab_income = (FloatingActionButton) view.findViewById(R.id.fabIncome);
        fabOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockwise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_anticlockwise);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isOpen){
                    fab_income.startAnimation(fabClose);
                    fab_expense.startAnimation(fabClose);
                    txtExpense.setAnimation(fabClose);
                    txtIncome.setAnimation(fabClose);
                    txtExpense.setClickable(false);
                    txtIncome.setClickable(false);
                    fab_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1976d2")));
                    fab_main.setScaleType(ImageView.ScaleType.CENTER);
                    fab_main.setImageResource(R.mipmap.add_transaction);
                    fab_income.setClickable(false);
                    fab_expense.setClickable(false);
                    isOpen = false;

                }
                else {
                    fab_income.startAnimation(fabOpen);
                    fab_expense.startAnimation(fabOpen);
                    txtExpense.setAnimation(fabOpen);
                    txtIncome.setAnimation(fabOpen);
                    txtExpense.setClickable(true);
                    txtIncome.setClickable(true);
                    fab_main.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffcc0000")));
                    fab_main.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    fab_main.setImageResource(R.mipmap.cancel);
                    fab_income.setClickable(true);
                    fab_expense.setClickable(true);
                    isOpen = true;
                }

                if (isOpen) {
                    fab_income.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent("ccpe001.familywallet.add_transaction");
                            intent.putExtra("category","income");
                            startActivity(intent);
                        }
                    });
                    fab_expense.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent("ccpe001.familywallet.add_transaction");
                            intent.putExtra("category","expense");
                            startActivity(intent);
                        }
                    });
                }

            }
        });





            return view;
        }



}
