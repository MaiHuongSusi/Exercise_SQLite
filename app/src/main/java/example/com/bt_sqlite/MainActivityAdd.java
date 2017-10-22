package example.com.bt_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 19/10/2017.
 */

public class MainActivityAdd extends AppCompatActivity {
    SinhVien sv;
    private EditText edname,edphone,eddc;
    private RadioButton radioButton1,radioButton2;
     DBManager dbManager= new DBManager(MainActivityAdd.this);
    private boolean needRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edname=(EditText) findViewById(R.id.edname);
        edphone=(EditText) findViewById(R.id.edphone);
        eddc=(EditText) findViewById(R.id.eddc);
        radioButton1=(RadioButton) findViewById(R.id.rb1);
        radioButton2=(RadioButton) findViewById(R.id.rb2);
    }

    public void buttonSaveClicked(View view)  {
        DBManager db = new DBManager(this);

        String name = edname.getText().toString();
        String phone = edphone.getText().toString();
        String dc = eddc.getText().toString();
        String gender;

        if(name.equals("")||phone.equals("")||dc.equals("")) {
            Toast.makeText(MainActivityAdd.this,"Please enter name, phone and address", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            if(radioButton1.isChecked()==false&&radioButton2.isChecked()==false)
                Toast.makeText(MainActivityAdd.this,"Please select gender", Toast.LENGTH_LONG).show();
            else{
                if(radioButton1.isChecked()) gender=radioButton1.getText().toString();
                else gender=radioButton2.getText().toString();
                Date date=new Date();
                String strDate="dd//MM//yyyy";
                SimpleDateFormat sdf =new SimpleDateFormat(strDate);
                Date time=new Date();
                String strtime="HH:mm:ss a";
                SimpleDateFormat sdftime=null;
                sdftime=new SimpleDateFormat(strtime);
                SinhVien sv = new SinhVien(name,phone,dc,gender,sdf.format(date),sdftime.format(time));
                db.addSinhVien(sv);
            }

        }
        Intent intent = new Intent(MainActivityAdd.this,MainActivity.class);
        startActivity(intent);
    }
    public void buttonCancelClicked(View view)  {
        // Không làm gì, trở về MainActivity.
        this.onBackPressed();
    }
}
