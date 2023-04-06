package shop;

public class Pick {
	protected int id = 0;
    private int productid;
    private String itemname;
    private int price;
    private int quantity;
    
    public Pick() {
    	
    }
    
    public Pick(int productid,String itemname,int price, int quantity) {
    	setProductid(productid);
    	setItemname(itemname);
    	setPrice(price);
    	setQuantity(quantity);
    	setId(id);
    	id += 1;
	}

    
    public void showPick() {
    	System.out.println("번호: " + productid+ "	| 제목:	" +itemname+ "	|  가격: " +price+ "	| 수량: " +quantity);
    } 
    
    public int getId() {
        return id;
    }

    public int getProductid() {
        return productid;
    }

    public String getItemname() {
        return itemname;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
