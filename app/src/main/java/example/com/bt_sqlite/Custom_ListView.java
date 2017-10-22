package example.com.bt_sqlite;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 20/10/2017.
 */

public class Custom_ListView extends ArrayAdapter<SinhVien> {
    private Activity activity;
    private int idLayout;
    private ArrayList<SinhVien> list;

    public Custom_ListView(Activity activity, int idLayout, ArrayList<SinhVien> list) {
        super(activity, idLayout, list);
        this.activity = activity;
        this.idLayout = idLayout;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SinhVien sinhVien = getItem(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(idLayout,null);
        TextView textView1 =(TextView) convertView.findViewById(R.id.tvname);
        textView1.setText(sinhVien.getName());
        return convertView;
    }
}
