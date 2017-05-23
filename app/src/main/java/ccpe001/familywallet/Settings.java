package ccpe001.familywallet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ccpe001.familywallet.transaction.DateDialog;
import ccpe001.familywallet.transaction.TimeDialog;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;
import net.rdrei.android.dirchooser.DirectoryChooserFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static ccpe001.familywallet.transaction.TimeDialog.pad;

/**
 * Created by harithaperera on 5/8/17.
 */
public class Settings extends Fragment implements View.OnClickListener,Switch.OnCheckedChangeListener{

    private Switch localMode,statusIcon,autoSync,autoBackUp,appNotySwitch;
    private Button signOutBtn;
    private TextView langText,currText,dateForText,dailyRemText,backupLocText,appPwText;
    private AlertDialog.Builder langBuilder,currBuilder,dateForBuilder,enterPinBuilder;
    private TableRow langRow,currRow,dateForRow,dailyRemRow,backupLocRow,appPassRow;
    private Calendar c;
    private List<String> fileList = new ArrayList<>();
    private File root;
    private File currentFolder;
    private DirectoryChooserFragment mDialog;
    private static final int RQ_CODE = 0;
    private String[] langArr,currArr,dateForArr;
    private EditText confPwTxt,newTxt,oldPwTxt;

    private boolean pinStatus,mode,appNoty,appIcon,appSync,appBackUp;
    private String pin,preferedLang,preferedDateFor,preferedCurr,remTime,appbackUpPath;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        init(view);

        return view;
    }

    private void init(View v){

        c = Calendar.getInstance();
        langArr = getActivity().getResources().getStringArray(R.array.spinnerLanguage);
        currArr = getActivity().getResources().getStringArray(R.array.spinnerCurrency);
        dateForArr = getActivity().getResources().getStringArray(R.array.spinnerDateFor);

        appNotySwitch = (Switch) v.findViewById(R.id.appNotySwitch);
        appNotySwitch.setOnCheckedChangeListener(this);
        localMode = (Switch) v.findViewById(R.id.localModeSwitch);
        localMode.setOnCheckedChangeListener(this);
        langRow = (TableRow) v.findViewById(R.id.selectLangRow);
        langRow.setOnClickListener(this);
        langText = (TextView) v.findViewById(R.id.statusLang);
        dateForRow = (TableRow) v.findViewById(R.id.selectDateRow);
        dateForRow.setOnClickListener(this);
        dateForText = (TextView) v.findViewById(R.id.statusDateFor);
        currRow = (TableRow) v.findViewById(R.id.selectCurrRow);
        currRow.setOnClickListener(this);
        currText = (TextView) v.findViewById(R.id.statusCurr);
        statusIcon = (Switch) v.findViewById(R.id.statusIconSwitch);
        statusIcon.setOnCheckedChangeListener(this);
        dailyRemRow = (TableRow) v.findViewById(R.id.remTimeRow);
        dailyRemRow.setOnClickListener(this);
        dailyRemText = (TextView) v.findViewById(R.id.startRem);
        autoSync = (Switch) v.findViewById(R.id.autoSyncSwitch);
        autoSync.setOnCheckedChangeListener(this);
        autoBackUp = (Switch) v.findViewById(R.id.autoBackUpSwitch);
        autoSync.setOnCheckedChangeListener(this);
        backupLocRow = (TableRow) v.findViewById(R.id.backupLocRow);
        backupLocRow.setOnClickListener(this);
        backupLocText = (TextView) v.findViewById(R.id.statusBackUpLoc);
        appPassRow = (TableRow) v.findViewById(R.id.appPasswordRow);
        appPassRow.setOnClickListener(this);
        appPwText = (TextView) v.findViewById(R.id.statusAppPw);
        signOutBtn = (Button) v.findViewById(R.id.signOutBtn);


        langBuilder = new AlertDialog.Builder(getContext());
        langBuilder.setTitle("Language");
        langBuilder.setSingleChoiceItems(R.array.spinnerLanguage, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                preferedLang = langArr[i];
                storePWSharedPref();
                langText.setText(langArr[i]);
                dialogInterface.dismiss();
            }
        });
        currBuilder = new AlertDialog.Builder(getContext());
        currBuilder.setTitle("Currency");
        currBuilder.setSingleChoiceItems(R.array.spinnerCurrency, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                preferedCurr = currArr[i];
                storePWSharedPref();
                currText.setText(currArr[i]);
                dialogInterface.dismiss();
            }
        });
        dateForBuilder = new AlertDialog.Builder(getContext());
        dateForBuilder.setTitle("Date format");
        dateForBuilder.setSingleChoiceItems(R.array.spinnerDateFor, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                preferedDateFor = dateForArr[i];
                storePWSharedPref();
                dateForText.setText(dateForArr[i]);
                dialogInterface.dismiss();
            }
        });

        retrievePWSharedPref();

    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signOutBtn){
            Intent intent = new Intent("ccpe001.familywallet.SIGNIN");
            startActivity(intent);
        }else if(view.getId()==R.id.selectLangRow){
            langBuilder.show();
        }else if(view.getId()==R.id.selectCurrRow){
            currBuilder.show();
        }else if(view.getId()==R.id.selectDateRow){
            dateForBuilder.show();
        }else if(view.getId()==R.id.remTimeRow){
            new TimePickerDialog(getContext(),new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hr, int min) {
                    String am_pm = (hr < 12) ? "AM" : "PM";
                    if (hr==0)
                        hr=12;
                    String time1 = pad(hr) + ":" + pad(min)+" "+am_pm;
                    dailyRemText.setText(time1);
                    remTime = time1;
                    storePWSharedPref();
                }
            },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false).show();
        }else if(view.getId()==R.id.backupLocRow) {
            Intent intent = new Intent(getContext(), DirectoryChooserActivity.class);
            final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                    .newDirectoryName("dfdff")
                    .allowReadOnlyDirectory(true)
                    .allowNewDirectoryNameModification(true)
                    .build();

            intent.putExtra(DirectoryChooserActivity.EXTRA_CONFIG,config);
            startActivityForResult(intent,RQ_CODE);
        }else if(view.getId()==R.id.appPasswordRow){
            enterPinBuilder = new AlertDialog.Builder(getActivity());
            enterPinBuilder.setTitle("Enter PIN");
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View alertDiaView = inflater.inflate(R.layout.passwordsetter,null);
            enterPinBuilder.setView(alertDiaView);

            confPwTxt = (EditText) alertDiaView.findViewById(R.id.passwordConfTxt);
            newTxt = (EditText) alertDiaView.findViewById(R.id.passwordTxt);
            oldPwTxt = (EditText) alertDiaView.findViewById(R.id.oldPasswordTxt);


            enterPinBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            enterPinBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String newPw = newTxt.getText().toString();
                    final String oldPw = oldPwTxt.getText().toString();
                    final String confPw = confPwTxt.getText().toString();

                    if(Validate.confPassword(newPw,confPw,getContext())){
                            pinStatus = true;
                            pin = newPw;
                            storePWSharedPref();
                            appPwText.setText(String.valueOf(pinStatus));
                    }else{
                        pinStatus = false;
                        storePWSharedPref();
                        appPwText.setText(String.valueOf(pinStatus));
                    }
                }
            });
            enterPinBuilder.setNeutralButton("Change", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            enterPinBuilder.show();
        }



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RQ_CODE){
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED){
                data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton switchs, boolean b) {
        if(switchs.getId()==R.id.localModeSwitch){
            if(b) {
                mode = true;
                localMode();
                storePWSharedPref();
            }else{
                mode = false;
                setDefaults();
                storePWSharedPref();
            }
        }else if(switchs.getId()==R.id.appNotySwitch){
            if(b) {
                appNoty = true;
                storePWSharedPref();
            }else{
                appNoty = false;
                storePWSharedPref();
            }
        }else if(switchs.getId()==R.id.statusIconSwitch){
            if(b) {
                appIcon = true;
                storePWSharedPref();
            }else{
                appIcon = false;
                storePWSharedPref();
            }
        }else if(switchs.getId()==R.id.autoSyncSwitch){
            if(b) {
                appSync = true;
                storePWSharedPref();
            }else{
                appSync = false;
                storePWSharedPref();
            }
        }else if(switchs.getId()==R.id.autoBackUpSwitch){
            if(b) {
                appBackUp = true;
                storePWSharedPref();
            }else{
                appBackUp = false;
                storePWSharedPref();
            }
        }


    }

    private void retrievePWSharedPref(){
        SharedPreferences prefs = getContext().getSharedPreferences("App Settings",Context.MODE_PRIVATE);

        pin = prefs.getString("appPass","123");
        pinStatus = prefs.getBoolean("appPinStatus",false);

        mode = prefs.getBoolean("appMode",false);
        preferedLang = prefs.getString("appLang",langArr[1]);
        preferedDateFor = prefs.getString("appDateFor",dateForArr[0]);
        preferedCurr = prefs.getString("appCurr",currArr[0]);
        appNoty = prefs.getBoolean("appNoty",true);
        appIcon = prefs.getBoolean("appIcon",true);
        remTime = prefs.getString("appDailyRem","09:00 AM");
        appSync = prefs.getBoolean("appSync",true);
        appBackUp = prefs.getBoolean("appBackUp",false);
        appbackUpPath = prefs.getString("appBackUpPath","test path");


        localMode.setChecked(mode);
        langText.setText(preferedLang);
        dateForText.setText(preferedDateFor);
        currText.setText(preferedCurr);
        appNotySwitch.setChecked(appNoty);
        statusIcon.setChecked(appIcon);
        dailyRemText.setText(remTime);
        autoSync.setChecked(appSync);
        autoBackUp.setChecked(appBackUp);
        backupLocText.setText(appbackUpPath);
        appPwText.setText(String.valueOf(pinStatus));
    }

    private void storePWSharedPref(){
        SharedPreferences prefs = getContext().getSharedPreferences("App Settings",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("appPass",pin);
        editor.putBoolean("appPinStatus",pinStatus);

        editor.putBoolean("appMode",mode);
        editor.putString("appLang",preferedLang);
        editor.putString("appDateFor",preferedDateFor);
        editor.putString("appCurr",preferedCurr);
        editor.putBoolean("appNoty",appNoty);
        editor.putBoolean("appIcon",appIcon);
        editor.putString("appDailyRem",remTime);
        editor.putBoolean("appSync",appSync);
        editor.putBoolean("appBackUp",appBackUp);
        editor.putString("appBackUpPath",appbackUpPath);



        editor.commit();
    }

    private void setDefaults(){
        mode = false;
        appIcon = true;
        appNoty = true;
        preferedLang = langArr[1];
        preferedDateFor = dateForArr[0];
        preferedCurr = currArr[0];
        remTime = "09:00 AM";
        appBackUp = false;
        appSync = true;
        appbackUpPath = "test path";
        pinStatus = false;

        localMode.setChecked(mode);
        langText.setText(preferedLang);
        dateForText.setText(preferedDateFor);
        currText.setText(preferedCurr);
        appNotySwitch.setChecked(appIcon);
        statusIcon.setChecked(appNoty);
        dailyRemText.setText(remTime);
        autoSync.setChecked(appSync);
        autoBackUp.setChecked(appBackUp);
        backupLocText.setText(appbackUpPath);
        appPwText.setText(String.valueOf(pinStatus));
    }

    private void localMode(){
        preferedLang = langArr[0];
        preferedDateFor = dateForArr[1];
        preferedCurr = currArr[2];

        langText.setText(preferedLang);
        dateForText.setText(preferedDateFor);
        currText.setText(preferedCurr);
    }


}


