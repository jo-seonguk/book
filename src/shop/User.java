package shop;

import java.util.*;


public class User {
	protected int nid = 0;
    private String uid;
    private String password;
    private String name;
    private String address;
    private String tel;
    private int usertype = 1;
    private int point;
    protected Vector<Bnotice> noList = new Vector<Bnotice>();
    protected Vector<uAlarm> alarm = new Vector<uAlarm>();
    protected uAlarm a;
    protected Bnotice b = new Bnotice();
    public User() {
    	
    }
    
    public User(String uid, String password) {
    	this.uid = uid;
    	this.password = password;
    }
    
    
    public void userinfo() {
    	System.out.println(getUid());
    	System.out.println(getPassword());
    	System.out.println(getName());
    	System.out.println(getAddress());
    	System.out.println(getTel());
    	System.out.println(getPoint());
    }
    public void addNotice(Bnotice n) {
    	noList.add(n);
    }
    public void addalarm(uAlarm a) {
    	alarm.add(a);
    }
    
    public void showalarm() {
    	a.getA();
    }
    
    public void shownotice() {
    	b.showPick();
    }
    
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
    public int getPoint() {
    	return point;
    }
    public void setPoint(int point) {
    	this.point = point;
    }
    
    
    public int getUsertype() {
        return usertype;
    }
    public void setUsertype(int type) {
        this.usertype = type;    
    }
}