package example.com.bt_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 21/10/2017.
 */

public class MainAcivityEdit extends AppCompatActivity {
    private EditText editname,editphone,editdc;
    private RadioButton radioButton1,radioButton2;
    private int mssv;
    private SinhVien sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editname=(EditText) findViewById(R.id.edname);
        editphone=(EditText) findViewById(R.id.edphone);
        editdc=(EditText) findViewById(R.id.eddc);
        radioButton1=(RadioButton) findViewById(R.id.rb1);
        radioButton2=(RadioButton) findViewById(R.id.rb2);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra(MainActivity.BUNDLE);
        mssv=bundle.getInt(MainActivityDetail.MSSV);
        editname.setText(bundle.getString(MainActivityDetail.NAME));
        editdc.setText(bundle.getString(MainActivityDetail.ADDRESS));
        editphone.setText(bundle.getString(MainActivityDetail.PHONE));
        String gender=bundle.getString(MainActivityDetail.GENDER);
        if(radioButton1.getText()==gender) radioButton1.setChecked(true);
        else radioButton2.setChecked(true);


    }

    public  void buttonSaveOnClicked(View view){
        DBManager db = new DBManager(MainAcivityEdit.this);
        sv.setMSSV(mssv);
        sv.setName(editname.getText().toString());
        sv.setPhone(editphone.getText().toString());
        sv.setAddress(editdc.getText().toString());
        if(radioButton1.isChecked()) sv.setGender(radioButton1.getText().toString());
        else sv.setGender(radioButton2.getText().toString());
        Date date=new Date();
        String strDate="dd/MM/yyyy";
        SimpleDateFormat sdf =new SimpleDateFormat(strDate);
        Date time=new Date();
        sv.setDate(sdf.format(date));
        String strtime="HH:mm:ss a";
        SimpleDateFormat sdftime=null;
        sdftime=new SimpleDateFormat(strtime);
        sv.setTime(sdftime.format(time));
        db.updateSV(sv);
        Intent intent=new Intent(MainAcivityEdit.this,MainActivity.class);
        startActivity(intent);
    }
    public void buttonCancelOnClicked(View view)  {
        // Không làm gì, trở về MainActivityDetail.
        this.onBackPressed();
    }
}
