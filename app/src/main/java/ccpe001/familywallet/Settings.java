package ccpe001.familywallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by harithaperera on 5/8/17.
 */
public class Settings extends Fragment implements View.OnClickListener,DialogInterface.OnClickListener{

    private Button signOutBtn;
    //private CheckBox
    AlertDialog.Builder langBuilder,currBuilder;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        init(view);
        return view;
    }

    private void init(View v){
        signOutBtn = (Button) v.findViewById(R.id.button);
        signOutBtn.setOnClickListener(this);
        langBuilder = new AlertDialog.Builder(getContext());
        langBuilder.setTitle("Language");
        langBuilder.setSingleChoiceItems(R.array.spinnerLanguage,-1,this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){
            Intent intent = new Intent("ccpe001.familywallet.SIGNIN");
            startActivity(intent);
        }else if(view.getId()==R.id.spinner){
            langBuilder.show();
        }else if(view.getId()==R.id.spinner){
            langBuilder.show();
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}