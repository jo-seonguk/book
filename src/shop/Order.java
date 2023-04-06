package shop;

import java.util.*;

public class Order {
	protected int pid = 0;
    private int oid = 0;
    private String uid;
    private String odate;
    private int amount;
    private boolean payment;
    private boolean a;
    protected int type = 1;		//1 미결제, 5 포인트 결제, 6 카드 결제
    protected String sDelivery;
    protected int state;
    
    protected String cp;	// 카드사
    protected String cn;	//카드번호
    protected int cpass;	// 비밀번호
    protected Vector<Pick> orderList = new Vector<Pick>();
    
    
    public Order(String uid) {
    	this.uid = uid;
    	this.oid = oid;
    	this.a = a;
        Calendar cal = Calendar.getInstance(); // static method로 객체를 할당받는다.
        this.odate =  cal.get(Calendar.YEAR) +"년 "
         +(cal.get(Calendar.MONTH)+1)  +"월 " 
		 + cal.get(Calendar.DAY_OF_MONTH) +"일 " 
		 + cal.get(Calendar.HOUR_OF_DAY) +"시 " 
		 + cal.get(Calendar.MINUTE) +"분 " 
		 + cal.get(Calendar.SECOND) +"초 " ;
    }
    
    public int getOid() {
    	return oid;
    }
    public void setOid(int oid) {
    	this.oid = oid;
    }
    
    
    public String getUid() {
    	return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
        setDelivery(state);
    }
    
    
    public String getDate() {
        return odate;
    }
    public void setDate(String odate) {
        this.odate = odate;
    }
    
    
    public boolean getA() {
    	if(payment == false) {
    		a = false;
    	}
    	else if(payment == true) {
    		a = true;
    	}
    	payment = a;
    	return payment;
    }
    
    private boolean isPayment() {
        return payment;
    }
    void setPayment( boolean payment) {
        this.payment = payment;
    }
    
    
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void showAmount() {
    	int Amount=0;
    	int price;
    	int quantity;
    	for( Pick pk : orderList) {
    		Amount = Amount + pk.getPrice() * pk.getQuantity();
    	}
    	this.amount = Amount ;
    	System.out.println("Amount = " + amount);
    }
    
    
    public void pushBuyKey() {
    	if(!isPayment())
    		payment = false;
    }
    
    
    public void sPrice() {
    	if (type == 5) {
    		System.out.println("amount :" + amount + "   payment: " + payment + "	결제 방식: 포인트 결제");
    	}
    	else if (type == 6) {
    		System.out.println("amount :" + amount + "   payment: " + payment + "	결제 방식: 카드 결제");
    	}
    	else {
        	System.out.println("amount :" + amount + "   payment: " + payment);

    	}
    }
    
    
    public void cancelBuy() {
    	payment = false;
    }
    
    
    public int getType() {
    	return type;
    }
    public void setType(int type) { 
    	this.type = type;
    }
    
    
    public Order() {
    	
    }
    public void addProduct(Pick p) {
    	orderList.add(p);
    	p.id = pid;
        pid = pid +1;
    }

    public static void delete(int i, int j) {
    	for(Pick pk : Shop.getCurrentorder().orderList) {
    		if(pk.getProductid() == BookList.selectBook(i).getBid()) {
    			pk.setQuantity(j);
    			break;
    		}
    	}
    }
    
    public static void remove(int i) {
    	Shop.getCurrentorder().orderList.remove(i);
    }
    
    public void order() {
    	System.out.println("사용자 :" + getUid());
    	for( Pick pk : orderList) {
    		pk.showPick(); 
    	}
    }
    

    public void pay() {

    }

    public void cancel() {

    }

    
    
    public String getDelivery() {
    	return sDelivery;
    }
    
    public void setDelivery(int i) {
    	if(i == 1) {				// 미구매
    		sDelivery = "";
        }
        else if(i == 2) {			// 구매
        	sDelivery = "";
        }
        else if(i == 3) {			// 포장
        	sDelivery = "배송 준비 중";
        }
        else if(i == 4) {			// 배송
        	sDelivery = "배송 중";
        }
        else if(i == 5) {			// 완료
        	sDelivery = "배송 완료";
        }
    }
    
    

    public void sDate() {
    	System.out.println(odate);
    }

    public String getCompany() {
        return cp;
    }
    public void setCompany(String cp) {
        this.cp = cp;
    }
    
    
    public String getCardnum() {
        return cn;
    }
    public void setCardnum(String cn) {
        this.cn = cn;
    }
    
    
    public int getCardpass() {
    	return cpass;
    }
    public void setCardpass(int cpass) {
    	this.cpass = cpass;
    }
    
}