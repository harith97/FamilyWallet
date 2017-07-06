package ccpe001.familywallet.transaction;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ccpe001.familywallet.R;

/**
 * Created by Knight on 5/23/2017.
 */

public class TransactionListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[]title;
    private final String[]category;
    private final String[]date;
    private final String[]amount;

    private Integer[]imgid;


    public TransactionListAdapter(Activity context, String[] title, String[]category, String[]date, String[]amount, Integer[] imgid) {
        super(context, R.layout.category_list, title);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.title = title;
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.imgid = imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.transaction_list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
        TextView txtCategory = (TextView) rowView.findViewById(R.id.txtCategory);
        TextView txtDate = (TextView) rowView.findViewById(R.id.txtTime);
        TextView txtAmount = (TextView) rowView.findViewById(R.id.txtAmount);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtTitle.setText(title[position]);
        txtCategory.setText(category[position]);
        txtDate.setText(date[position]);
        txtAmount.setText(amount[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;


    }

}