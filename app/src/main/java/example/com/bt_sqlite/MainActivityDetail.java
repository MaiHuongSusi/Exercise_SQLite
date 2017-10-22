package example.com.bt_sqlite;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 21/10/2017.
 */

public class MainActivityDetail extends AppCompatActivity {
    private TextView textname, textphone, textaddress, textgender, texttime, textdate;
    int mssv;
    private ImageView imgcall;
    public static final String MSSV = "mssv";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String GENDER = "gender";
    public static final String BUNDLE = "bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textname = (TextView) findViewById(R.id.textname);
        textphone = (TextView) findViewById(R.id.textnumberphone);
        textaddress = (TextView) findViewById(R.id.textaddress);
        textgender = (TextView) findViewById(R.id.textgender);
        textdate = (TextView) findViewById(R.id.textdate);
        texttime = (TextView) findViewById(R.id.texttime);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(MainActivity.BUNDLE);
        mssv = bundle.getInt(MSSV);
        textname.setText(bundle.getString(MainActivity.NAME));
        textaddress.setText(bundle.getString(MainActivity.ADDRESS));
        textphone.setText(bundle.getString(MainActivity.PHONE));
        textgender.setText(bundle.getString(MainActivity.GENDER));
        textdate.setText(bundle.getString(MainActivity.DATE));
        texttime.setText(bundle.getString(MainActivity.TIME));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        imgcall = (ImageView) findViewById(R.id.imcall);
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + textphone.getText().toString()));//change the number
                if (ActivityCompat.checkSelfPermission(MainActivityDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar1,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.create_view:
            {
                Intent intent = new Intent(MainActivityDetail.this,MainAcivityEdit.class);
                Bundle bundle = new Bundle();
                bundle.putInt(MSSV,mssv);
                bundle.putString(NAME,textname.getText().toString());
                bundle.putString(PHONE,textphone.getText().toString());
                bundle.putString(ADDRESS,textaddress.getText().toString());
                bundle.putString(GENDER,textgender.getText().toString());
                intent.putExtra(BUNDLE,bundle);
                startActivity(intent);
                break;
            }
            case R.id.clear_view:
            {
                DBManager dbManager=new DBManager(MainActivityDetail.this);
                dbManager.deleteSV(mssv);
                Intent intent = new Intent(MainActivityDetail.this,MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
