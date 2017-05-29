package ccpe001.familywallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harithaperera on 5/28/17.
 */
public class ExportData extends Fragment implements View.OnClickListener,CheckBox.OnCheckedChangeListener{


    private CheckBox checkCSV,checkEXEL;
    private Button createBtn;
    private EditText fileName;
    private Switch isMail;
    private String filename,finalLoc;
    private boolean exelChecked;
    private boolean csvChecked;
    private boolean mailChecked;

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
        checkCSV.setOnCheckedChangeListener(this);
        checkEXEL.setOnCheckedChangeListener(this);
        isMail.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.createBtn){
            if (Validate.fileValidate(fileName.getText())) {
                filename = fileName.getText().toString();
                try {
                    if ((!csvChecked)&&(!exelChecked)){
                        Toast.makeText(getContext(),"Check at least one option",Toast.LENGTH_SHORT).show();
                    }else {
                        export();
                        if (mailChecked) {
                            createMail();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                fileName.setError("Invalid filename");
            }
        }
    }

    public void export() throws IOException {
        SharedPreferences prefs = getContext().getSharedPreferences("App Settings", Context.MODE_PRIVATE);
        finalLoc = prefs.getString("appBackUpPath","/storage/emulated/0/");
        finalLoc += filename;

        if (csvChecked){
            CSVWriter writer = new CSVWriter(new FileWriter(finalLoc+".csv"));

            //change from here//2 sem
            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"dfd", "dsfdfdsf"});
            data.add(new String[]{"1", "4"});

            writer.writeAll(data);
            writer.close();
        }

        if(exelChecked){
            Workbook wb = new HSSFWorkbook();
            Cell c = null;

            //cell style for heder row
            CellStyle cs = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.LIME.index);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            //new sheet
            Sheet sheet1 = null;
            sheet1 = wb.createSheet("SheetNametest");

            //generate column headings
            Row row = sheet1.createRow(0);

            c = row.createCell(0);
            c.setCellValue("Cell One");
            c.setCellStyle(cs);

            c = row.createCell(1);
            c.setCellValue("Cell Two");
            c.setCellStyle(cs);

            sheet1.setColumnWidth(0,(15*500));
            sheet1.setColumnWidth(1,(15*500));

            File file = new File(getContext().getExternalFilesDir(null),filename);
            FileOutputStream fOut = null;

            try {
                fOut = new FileOutputStream(file);
                wb.write(fOut);
                Toast.makeText(getContext(),"cretaed",Toast.LENGTH_SHORT).show();

            }catch (Exception e){

            }finally {
                fOut.close();
            }
        }

    }

    private void createMail(){
        String attches[] = {(finalLoc+".csv"),("df")};
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,"ccpe_001@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Family Wallet Backup");
        Toast.makeText(getContext(),(finalLoc+".csv"),Toast.LENGTH_SHORT).show();

        intent.putExtra(Intent.EXTRA_STREAM,attches);
        intent.createChooser(intent,"Send email");
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.getId()==R.id.csvCheck){
            if (b){
                csvChecked = true;
            }else {
                csvChecked = false;
            }
        }else if(compoundButton.getId()==R.id.exelCheck){
            if (b){
                exelChecked = true;
            }else {
                exelChecked = false;
            }
        }else if(compoundButton.getId()==R.id.sendBackMail){
            if (b){
                mailChecked = true;
            }else {
                mailChecked = false;
            }
        }

    }
}
