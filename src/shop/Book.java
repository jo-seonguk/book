package shop;

import java.util.*;

public class Book {
	
	protected int bid;
	protected String name;
    protected int price;
    protected int quantity;
    protected String author;
    protected int pquantity;
    public Book() {
    	
    }
    public Book(String name, int price, int quantity, String author) {
    	setName(name);
    	setPrice(price);
    	setQuantity(quantity);
    	setAuthor(author);
    }

    public int getBid() {
    	return bid;
    }
    
    public int getPrice() {
    	return price;
    }
    
    public int getQuantity() {
    	return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getPQuantity() {
    	return pquantity;
    }
    
    public void setPQuantity(int pquantity) {
        this.pquantity = pquantity;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setBid(int bid) {
        this.bid = bid;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void book() {
    	System.out.println(" 책 번호: " + bid + "	| 책 이름: " + name + "	|  가격: " + price +  "	|  수량: " + quantity +   "	| 장르: " + author);
    	   
    }

    
    
}