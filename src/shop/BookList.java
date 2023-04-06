package shop;

import java.util.*;


public class BookList {

	private BookList booklist;
	protected static Vector<Book> bookList = new Vector<Book>();
    protected static int sbid= 0;
    //å ����Ʈ ����
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
    
    
    
    
    //å �߰�
    public static void addBook(Book b) {
        bookList.add(b);
        b.bid = sbid;
        sbid = sbid +1;
    }
    
    public int getBid(Book b) {
    	return sbid;
    }

    //å ����Ʈ �����ֱ�
    public void sList() {
        for(Book b : bookList) {
        	b.book();
        }
    }

    
    //å ����
    public static Book selectBook(int id) {
        return bookList.elementAt(id);
    }
    
    //å ���԰�
    public void restock() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("���԰� �� å�� ��ȣ �Է�  :");
    	int bn = scanner.nextInt();
    	
    	System.out.print("���԰� �� å�� ���� �Է�  :");
    	int bq = scanner.nextInt();
    	
    	for(Book b : bookList) {
    		if(bn == b.getBid()) {
    			b.setQuantity(b.getQuantity()+bq);
    		}
    	}
    	
    }
    
    //å �˻�
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