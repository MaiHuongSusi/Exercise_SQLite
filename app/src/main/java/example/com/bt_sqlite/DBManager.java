package example.com.bt_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 19/10/2017.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // Phiên bản
    private static final int DATABASE_VERSION = 1;
    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Note_Manager";
    // Tên bảng
    private static final String TABLE_SV = "Sinh_Vien";

    private static final String COLUMN_NOTE_ID ="Note_Id";
    private static final String COLUMN_NOTE_NAME ="Note_Name";
    private static final String COLUMN_NOTE_PHONE ="Note_Phone";
    private static final String COLUMN_NOTE_ADDRESS="Note_Address";
    private static final String COLUMN_NOTE_GENDER ="Note_Gender";
    private static final String COLUMN_NOTE_DATE ="Note_Date";
    private static final String COLUMN_NOTE_TIME ="Note_Time";


    public DBManager(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String script = "CREATE TABLE " + TABLE_SV + "("
                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_NOTE_NAME + " TEXT," +
                COLUMN_NOTE_PHONE + " TEXT,"+ COLUMN_NOTE_ADDRESS + " TEXT,"+ COLUMN_NOTE_GENDER +" TEXT," +
                COLUMN_NOTE_DATE +" TEXT," + COLUMN_NOTE_TIME +" TEXT"+")";
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SV);
        // Và tạo lại.
        onCreate(sqLiteDatabase);
    }
    public void addSinhVien(SinhVien sinhvien) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + sinhvien.getName());

        SQLiteDatabase db = this.getWritableDatabase();//mở database de co the doc vai chinh sua

        ContentValues values = new ContentValues();//de put all gia tri cua table vao
        values.put(COLUMN_NOTE_NAME, sinhvien.getName());
        values.put(COLUMN_NOTE_PHONE,sinhvien.getPhone());
        values.put(COLUMN_NOTE_ADDRESS,sinhvien.getAddress());
        values.put(COLUMN_NOTE_GENDER,sinhvien.getGender());
        values.put(COLUMN_NOTE_DATE,sinhvien.getDate());
        values.put(COLUMN_NOTE_TIME,sinhvien.getTime());
        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_SV, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public int updateSV(SinhVien sinhvien) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_NAME, sinhvien.getName());
        values.put(COLUMN_NOTE_PHONE,sinhvien.getPhone());
        values.put(COLUMN_NOTE_ADDRESS,sinhvien.getAddress());
        values.put(COLUMN_NOTE_GENDER,sinhvien.getGender());
        values.put(COLUMN_NOTE_DATE,sinhvien.getDate());
        values.put(COLUMN_NOTE_TIME,sinhvien.getTime());
        // updating row
        return db.update(TABLE_SV,values,COLUMN_NOTE_ID +"=?",new String[]{String.valueOf(sinhvien.getMSSV())});
    }

    public ArrayList<SinhVien> getallSV(){
        ArrayList<SinhVien> mList= new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_SV;//cau truy van
        SQLiteDatabase db = this.getWritableDatabase();//mở database de co the doc vai chinh sua
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())//kq tra ve co it nhat 1 gia tri
        {
            do{
                SinhVien sinhVien=new SinhVien();
                sinhVien.setMSSV(cursor.getInt(0));
                sinhVien.setName(cursor.getString(1));
                sinhVien.setPhone(cursor.getString(2));
                sinhVien.setAddress(cursor.getString(3));
                sinhVien.setGender(cursor.getString(4));
                sinhVien.setDate(cursor.getString(5));
                sinhVien.setTime(cursor.getString(6));
                mList.add(sinhVien);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return mList;
    }
    public int deleteSV(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SV,COLUMN_NOTE_ID+"=?",new String[]{String.valueOf(id)});
    }
    public void deleteallSV(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SV,null,null);
    }
}
