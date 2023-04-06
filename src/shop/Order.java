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
    protected int type = 1;		//1 �̰���, 5 ����Ʈ ����, 6 ī�� ����
    protected String sDelivery;
    protected int state;
    
    protected String cp;	// ī���
    protected String cn;	//ī���ȣ
    protected int cpass;	// ��й�ȣ
    protected Vector<Pick> orderList = new Vector<Pick>();
    
    
    public Order(String uid) {
    	this.uid = uid;
    	this.oid = oid;
    	this.a = a;
        Calendar cal = Calendar.getInstance(); // static method�� ��ü�� �Ҵ�޴´�.
        this.odate =  cal.get(Calendar.YEAR) +"�� "
         +(cal.get(Calendar.MONTH)+1)  +"�� " 
		 + cal.get(Calendar.DAY_OF_MONTH) +"�� " 
		 + cal.get(Calendar.HOUR_OF_DAY) +"�� " 
		 + cal.get(Calendar.MINUTE) +"�� " 
		 + cal.get(Calendar.SECOND) +"�� " ;
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
    		System.out.println("amount :" + amount + "   payment: " + payment + "	���� ���: ����Ʈ ����");
    	}
    	else if (type == 6) {
    		System.out.println("amount :" + amount + "   payment: " + payment + "	���� ���: ī�� ����");
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
    	System.out.println("����� :" + getUid());
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
    	if(i == 1) {				// �̱���
    		sDelivery = "";
        }
        else if(i == 2) {			// ����
        	sDelivery = "";
        }
        else if(i == 3) {			// ����
        	sDelivery = "��� �غ� ��";
        }
        else if(i == 4) {			// ���
        	sDelivery = "��� ��";
        }
        else if(i == 5) {			// �Ϸ�
        	sDelivery = "��� �Ϸ�";
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