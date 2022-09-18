package Core;

import java.util.ArrayList;

public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private ArrayList<Coupon> coupons = new ArrayList<>();

    public Company() {

    }

    public Company(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Coupon> getCoupons() {
        return this.coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return " id='" + getId() + "'" + ", name='" + getName() + "'" + ", email='" + getEmail() + "'" + ", password='"
                + getPassword() + "'" + ", coupons='" + getCoupons() + "'";
    }

}
