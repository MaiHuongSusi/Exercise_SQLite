package example.com.bt_sqlite;

import java.io.Serializable;

/**
 * Created by Administrator on 19/10/2017.
 */

public class SinhVien implements Serializable {

    private int MSSV;
    private String name;
    private String phone;
    private String address;
    private String gender;
    private String date;
    private String time;

    public SinhVien() {
    }

    public SinhVien(String name, String phone, String address, String gender, String date, String time) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.date = date;
        this.time = time;
    }

    public SinhVien(int MSSV, String name, String phone, String address, String gender, String date, String time) {
        this.MSSV = MSSV;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
