package ccpe001.familywallet;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;
import net.rdrei.android.dirchooser.DirectoryChooserFragment;

import java.util.Calendar;

import static ccpe001.familywallet.transaction.TimeDialog.pad;

/**
 * Created by harithaperera on 5/8/17.
 */
public class Settings extends Fragment implements View.OnClickListener,Switch.OnCheckedChangeListener,DirectoryChooserFragment.OnFragmentInteractionListener{

    private Switch localMode,statusIcon,autoSync,autoBackUp,appNotySwitch,enDisPinSwitch;
    private Button signOutBtn,setPinBtn;
    private TextView langText,currText,dateForText,dailyRemText,backupLocText,appPwText;
    private AlertDialog.Builder langBuilder,currBuilder,dateForBuilder,enterPinBuilder;
    private TableRow langRow,currRow,dateForRow,dailyRemRow,backupLocRow,appPassRow,feedBackRow,rateRow;
    private Calendar c;
    private DirectoryChooserFragment mDialog;
    private static final int SET_PIN = 0;
    private static final int ENABLE_PIN = 1;
    private static final int DIR_CHOOSER = 2;
    private static final int EXTERNAL_READ_PERMIT = 3;
    private static final int EXTERNAL_WRITE_PERMIT = 4;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;


    private String[] langArr,currArr,dateForArr;

    private boolean pinStatus,mode,appNoty,appIcon,appSync,appBackUp;
    private String pin;
    private String preferedLang;
    private String preferedDateFor;
    private String preferedCurr;
    private String remTime;
    private String appbackUpPath;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        init(view);

        return view;
    }

    private void init(View v){
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Settings");
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
        signOutBtn.setOnClickListener(this);
        feedBackRow = (TableRow) v.findViewById(R.id.feedbackRow);
        feedBackRow.setOnClickListener(this);
        rateRow = (TableRow) v.findViewById(R.id.rateRow);
        rateRow.setOnClickListener(this);

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
            prefs = getContext().getSharedPreferences("First Time",Context.MODE_PRIVATE);
            editor = prefs.edit();
            editor.putBoolean("isFirst",true);
            editor.commit();
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

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},EXTERNAL_READ_PERMIT);
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},EXTERNAL_WRITE_PERMIT);
            }

            final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                    .allowNewDirectoryNameModification(true)
                    .newDirectoryName("FamilyWallet Backups")
                    .build();

            mDialog = DirectoryChooserFragment.newInstance(config);
            mDialog.show(getActivity().getFragmentManager(),null);
            mDialog.setDirectoryChooserListener(this);

        }else if(view.getId()==R.id.appPasswordRow){
            enterPinBuilder = new AlertDialog.Builder(getActivity());
            enterPinBuilder.setTitle("Enter PIN");
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View alertDiaView = inflater.inflate(R.layout.passwordsetter,null);
            enterPinBuilder.setView(alertDiaView);

            setPinBtn = (Button) alertDiaView.findViewById(R.id.setPinBtn);
            enDisPinSwitch = (Switch) alertDiaView.findViewById(R.id.enDisPinSwitch);
            setPinBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),CustomPinActivity.class);
                    intent.putExtra(AppLock.EXTRA_TYPE,AppLock.ENABLE_PINLOCK);
                    getActivity().startActivityForResult(intent,SET_PIN);
                }
            });
            enDisPinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Intent intent = new Intent(getContext(),CustomPinActivity.class);
                    if(b){
                        Toast.makeText(getContext(),"FamilyWallet Backups",Toast.LENGTH_LONG).show();
                        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
                        lockManager.enableAppLock(getContext(),CustomPinActivity.class);
                        startActivityForResult(intent, ENABLE_PIN);
                    }else{
                        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
                        lockManager.disableAppLock();
                    }
                }
            });
            enterPinBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            enterPinBuilder.show();

        }else if(view.getId()==R.id.feedbackRow){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL,"ccpe_001@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Customer Feedback");
            intent.setType("message/rfc882");
            intent.createChooser(intent,"Send email");
            startActivity(intent);
        }else if(view.getId()==R.id.rateRow){
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 34){
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED){
                data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);
            }
        }else if(requestCode == ENABLE_PIN){
            Toast.makeText(getContext(),"enabled pin",Toast.LENGTH_LONG).show();

        }
        else if(requestCode == SET_PIN){
            Toast.makeText(getContext(),"set pin",Toast.LENGTH_LONG).show();

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
        prefs = getContext().getSharedPreferences("App Settings",Context.MODE_PRIVATE);

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
        appbackUpPath = prefs.getString("appBackUpPath","/storage/emulated/0/");


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
        prefs = getContext().getSharedPreferences("App Settings",Context.MODE_PRIVATE);
        editor = prefs.edit();

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
        editor.putString("appBackUpPath", appbackUpPath);



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
        appbackUpPath = "/storage/emulated/0/";
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




    @Override
    public void onSelectDirectory(@NonNull String path) {
        mDialog.setTargetFragment(getActivity().getFragmentManager().findFragmentById(R.id.settingFrag),DIR_CHOOSER);
        //mDialog.show(getActivity().getFragmentManager(),null);
        mDialog.dismiss();

        appbackUpPath = path;
        storePWSharedPref();
        backupLocText.setText(path);
    }

    @Override
    public void onCancelChooser() {
        mDialog.dismiss();
    }


}

