package ccpe001.familywallet.transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * Created by Knight on 7/4/2017.
 */

public class DatabaseOps extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "family_wallet";
    private static final String COL1 = "AMOUNT";
    private static final String COL2 = "TITLE";
    private static final String COL3 = "CATEGORY";
    private static final String COL4 = "DATE";
    private static final String COL5 = "IMGID";

    public DatabaseOps(Context context) {
        super(context, "family_wallet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME + " (AMOUNT TEXT, "+COL2+" TEXT, "+COL3+" TEXT, "+COL4+" TEXT, "+COL5+" INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public String addData(String amount, String title, String category, String date, Integer imgId){
        String ex="";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contVal = new ContentValues();
            contVal.put(COL1,amount);
            contVal.put(COL2,title);
            contVal.put(COL3,category);
            contVal.put(COL4,date);
            contVal.put(COL5,imgId);
            db.insert(TABLE_NAME, null, contVal);


        }catch (Exception e){
            ex=""+e;
        }
        return ex;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
