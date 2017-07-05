package ccpe001.familywallet.transaction;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

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

        String[] Amount ;
        String[] Title ;
        String[] Category ;
        String[] Date ;
        Integer[] imgid;

        DatabaseOps dbOps = new DatabaseOps(getActivity());
        //ArrayList<String> listData = new ArrayList<>();
        try {
            Cursor data = dbOps.getData();

            final int size = data.getCount();
            Amount = new String[size];
            Title = new String[size];
            Category = new String[size];
            Date = new String[size];
            imgid = new Integer[size];

            if (data.moveToFirst()) {
                int i = 0;
                do {
                    Amount[i] = data.getString(0);
                    Title[i] = data.getString(1);
                    Category[i] = data.getString(2);
                    Date[i] = data.getString(3);
                    imgid[i] = data.getInt(4);
                    i++;
                } while (data.moveToNext());


            }
            TransactionListAdapter adapter = new TransactionListAdapter(getActivity(), Title, Category, Date, Amount, imgid);
            list = (ListView) view.findViewById(R.id.transactionList);
            list.setAdapter(adapter);

        }catch (Exception e){

        }
        //String[] Amount = listData.toArray(new String[0]);
        /*try {
            if (savedInstanceState == null) {
                Bundle extras = getActivity().getIntent().getExtras();
                if(extras == null) {
                } else {
                    categoryName = extras.getString("categoryName");
                    categoryID = extras.getString("categoryID");
                    title = extras.getString("title");
                    date = extras.getString("date");
                    amount = extras.getString("amount");

                     String[] Title = {
                            title,
                    };
                     String[] Category = {
                            categoryName,
                    };
                     String[] Date = {
                             date,
                    };
                     String[] Amount = {
                             amount
                    };

                    Integer[] imgid = {
                            Integer.parseInt(categoryID)
                    };



                }
            }
        }catch (Exception ex){

        }*/




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
                            intent.putExtra("transactionType","Income");
                            startActivity(intent);
                        }
                    });
                    fab_expense.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent("ccpe001.familywallet.add_transaction");
                            intent.putExtra("transactionType","Expense");
                            startActivity(intent);
                        }
                    });
                }

            }
        });





        return view;
    }



}
