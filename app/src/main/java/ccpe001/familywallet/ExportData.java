package ccpe001.familywallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by harithaperera on 5/28/17.
 */
public class ExportData extends Fragment implements View.OnClickListener {


    private CheckBox checkCSV,checkEXEL;
    private Button createBtn;
    private EditText fileName;
    private Switch isMail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.exportdata, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        checkCSV = (CheckBox)view.findViewById(R.id.csvCheck);
        checkEXEL = (CheckBox)view.findViewById(R.id.exelCheck);
        createBtn = (Button)view.findViewById(R.id.createBtn);
        fileName = (EditText) view.findViewById(R.id.filename);
        isMail = (Switch)view.findViewById(R.id.sendBackMail);
        createBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    public void export(){

    }
}
