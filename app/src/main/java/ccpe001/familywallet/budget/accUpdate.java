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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import ccpe001.familywallet.R;

public class accUpdate extends Fragment {
    Button btnDel,btltran;
    private String[] arraySpinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext(),"transfer money",Toast.LENGTH_LONG).show();
        View v = inflater.inflate(R.layout.acc_update, container, false);
        btnDel=(Button)v.findViewById(R.id.btnDtl);
        btltran=(Button)v.findViewById(R.id.transfer);

        this.arraySpinner = new String[] {
                "1234", "2345"
        };

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

                    }
                });

                builder.setNegativeButton("NO | View History", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Transfer:Cancel", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
                        builderSingle.setIcon(R.drawable.ic_launcher);
                        builderSingle.setTitle("Transfer History[Date]");
                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
                        final ArrayAdapter<String> arrayAddate = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
                        arrayAdapter.add("Transfer $500 from account 1 to account 2 on Date ");
                        arrayAddate.add("2017/06/01");
                        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builderSingle.setAdapter(arrayAddate, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                                builderInner.setMessage(strName);
                                builderInner.setTitle("Transfer Detail :");
                                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builderInner.show();
                            }
                        });
                        builderSingle.show();


                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        Spinner s = (Spinner) v.findViewById(R.id.transFrom);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        s.setAdapter(adapter);
        Spinner c = (Spinner) v.findViewById(R.id.transTo);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        c.setAdapter(adapter2);
        return v;
        }
    }


