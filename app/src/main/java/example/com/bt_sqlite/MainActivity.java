package example.com.bt_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public  static final  String NAME="name";
    public  static final  String MSSV="mssv";
    public  static final  String PHONE="phone";
    public  static final  String ADDRESS="address";
    public  static final  String GENDER="gender";
    public  static final  String DATE="date";
    public  static final  String TIME="time";
    public  static final  String BUNDLE="bundle";

    private ListView listView;
    private ArrayList<SinhVien> mList;
    private Custom_ListView adapter=null;
    DBManager dbManager= new DBManager(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView) findViewById(R.id.listview);
        mList=new ArrayList<>();
        //dbManager.notifyAll();
        mList.clear();
        mList=dbManager.getallSV();
        if(adapter==null){
            adapter= new Custom_ListView(MainActivity.this,R.layout.custom_listview,mList);
            listView.setAdapter(adapter);
        }
        else adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,MainActivityDetail.class);
                Bundle bundle = new Bundle();
                bundle.putInt(MSSV,mList.get(i).getMSSV());
                bundle.putString(NAME,mList.get(i).getName().toString());
                bundle.putString(PHONE,mList.get(i).getPhone().toString());
                bundle.putString(ADDRESS,mList.get(i).getAddress().toString());
                bundle.putString(GENDER,mList.get(i).getGender().toString());
                bundle.putString(DATE,mList.get(i).getDate().toString());
                bundle.putString(TIME,mList.get(i).getTime().toString());
                intent.putExtra(BUNDLE,bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_view:
            {
                Intent intent = new Intent(MainActivity.this,MainActivityAdd.class);
                startActivity(intent);
                break;
            }
            case R.id.delete_view:
            {
                DBManager dbManager=new DBManager(MainActivity.this);
                dbManager.deleteallSV();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

            }
        }
        return super.onOptionsItemSelected(item);
    }
}

