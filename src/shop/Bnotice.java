package shop;

public class Bnotice {
	protected String id;
    private int productid;
    private String itemname;
    private int price;
    private String author;
    
    public Bnotice() {
    	
    }
    
    public Bnotice(String id, int productid, String itemname, int price, String author) {
    	setId(id);
    	setProductid(productid);
    	setItemname(itemname);
    	setPrice(price);
    	setAuthor(author);
    	
	}

    
    public void showPick() {
    	System.out.println("ID: "+ id +"	번호: " + productid+ "	| 제목:	" +itemname+ "	|  가격: " +price+ "	| 작가: " +author);
    } 
    
    
    public String getId() {
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

    public String getAuthor() {
        return author;
    }

    public void setId(String id) {
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

    public void setAuthor(String author) {
        this.author = author;
    }
}
