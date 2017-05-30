package ccpe001.familywallet.budget;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import ccpe001.familywallet.R;

public class accUpdate extends Fragment {
    Button btnDel,btltran;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext(),"transfer money",Toast.LENGTH_LONG).show();
        View v = inflater.inflate(R.layout.acc_update, container, false);
        btnDel=(Button)v.findViewById(R.id.btnDtl);
        btltran=(Button)v.findViewById(R.id.transfer);
        btnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newInt2 = new Intent("ccpe001.familywallet.budget.accDetail");
                startActivity(newInt2);
            }
        });
        btltran.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("This procedure cant be undo .Confirm to proceed! ");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Transfer:DONE", Toast.LENGTH_LONG).show();
                        Intent newInt1 = new Intent("ccpe001.familywallet.budget.tranHistory");
                        startActivity(newInt1);


                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Transfer:Cancel", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        return v;
        }
    }


