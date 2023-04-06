package shop;

import java.util.Calendar;

public class uAlarm {
	private String a;
	private String adate;
	
	public uAlarm() {
    	
    }
    
    public uAlarm(String a) {
    	setA(a);
    	Calendar cal = Calendar.getInstance(); // static method로 객체를 할당받는다.
        this.adate =  cal.get(Calendar.YEAR) +"년 "
         +(cal.get(Calendar.MONTH)+1)  +"월 " 
		 + cal.get(Calendar.DAY_OF_MONTH) +"일 " 
		 + cal.get(Calendar.HOUR_OF_DAY) +"시 " 
		 + cal.get(Calendar.MINUTE) +"분 " 
		 + cal.get(Calendar.SECOND) +"초 " ;
	}
    
    public String getA() {
        return a;
    }
    public void setA(String a) {
        this.a = a;
    }
    
    public String getDate() {
        return adate;
    }
    public void setDate(String adate) {
        this.adate = adate;
    }
    
    
    public void showA() {
    	System.out.println(a);
    } 
}
