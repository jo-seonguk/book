package shop;

import java.util.Calendar;

public class uAlarm {
	private String a;
	private String adate;
	
	public uAlarm() {
    	
    }
    
    public uAlarm(String a) {
    	setA(a);
    	Calendar cal = Calendar.getInstance(); // static method�� ��ü�� �Ҵ�޴´�.
        this.adate =  cal.get(Calendar.YEAR) +"�� "
         +(cal.get(Calendar.MONTH)+1)  +"�� " 
		 + cal.get(Calendar.DAY_OF_MONTH) +"�� " 
		 + cal.get(Calendar.HOUR_OF_DAY) +"�� " 
		 + cal.get(Calendar.MINUTE) +"�� " 
		 + cal.get(Calendar.SECOND) +"�� " ;
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
