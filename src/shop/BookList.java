package shop;

import java.util.*;


public class BookList {

	private BookList booklist;
	protected static Vector<Book> bookList = new Vector<Book>();
    protected static int sbid= 0;
    //책 리스트 벡터
    public Vector<Book> getBookList(){
    	return bookList;
    }
    
    public void setBooklist(BookList booklist) {
        this.booklist = booklist;
    }
    
    public BookList getBook(String name) {
    	booklist = new BookList();
    	for(Book b : bookList) {
    		if(name.equals(b.getName())) {
    			booklist.bookList.add(b);
    		}
    	}
    	return booklist;
    }
    
    public BookList getBookid(int id) {
    	booklist = new BookList();
    	for(Book b : bookList) {
    		if(id == b.getBid()) {
    			booklist.bookList.add(b);
    		}
    	}
    	return booklist;
    }
    
    
    
    
    //책 추가
    public static void addBook(Book b) {
        bookList.add(b);
        b.bid = sbid;
        sbid = sbid +1;
    }
    
    public int getBid(Book b) {
    	return sbid;
    }

    //책 리스트 보여주기
    public void sList() {
        for(Book b : bookList) {
        	b.book();
        }
    }

    
    //책 선택
    public static Book selectBook(int id) {
        return bookList.elementAt(id);
    }
    
    //책 재입고
    public void restock() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("재입고 할 책의 번호 입력  :");
    	int bn = scanner.nextInt();
    	
    	System.out.print("재입고 할 책의 수량 입력  :");
    	int bq = scanner.nextInt();
    	
    	for(Book b : bookList) {
    		if(bn == b.getBid()) {
    			b.setQuantity(b.getQuantity()+bq);
    		}
    	}
    	
    }
    
    //책 검색
    public BookList bookSearch(String name) {
    	booklist = new BookList();

    	for(Book b : bookList) {
    		if(b.getName().contains(name)) {
    			booklist.bookList.add(b);
    		}
    	}
    	return booklist;
    }
}