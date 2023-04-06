package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;



	 // 조성욱
public class Shop extends JFrame {
	
    protected static Vector<User> users = new Vector<User>();
    protected static User currentuser;
    User currentuser1;
    public static BookList book;
    Scanner scanner = new Scanner(System.in);
    static Book b;
    
    protected static Order currentorder;
    protected Order currentorder1;
    protected Order currentorder2;
    protected static Vector<Order> order = new Vector<Order>();
    
    public Order getOrder(int id) {
    	return order.elementAt(id);
    }
    public static void setOrder(Order order) {
    	Shop.order.add(order);
    }
    public static Order getCurrentorder() {
    	return currentorder;
    }
    public static void setCurrentorder(Order currentorder) {
    	Shop.currentorder = currentorder;
    }
    public static Order createOrder(String uid) {
        Order order = new Order(uid);
        setOrder(order);
        
        return order;
    }
    public Order selectOrder(int oid) {
        Order uorder;
        uorder = order.elementAt(oid);
        return uorder;
    }
    public static Vector<Order> selectOrder(String uid) {
    	Vector<Order> userOrder  = new Vector<Order>();
    	for(Order o : order) {
    		if(uid.equals(o.getUid())) {
    			userOrder.add(o);
    		}
    	}
    	return userOrder;
    }
    public BookList getBook() {
        return book;
    }
    public void setBook(BookList book) {
    	Shop.book = book;
    }
    public User getUsers(int id) {
    	return users.elementAt(id);
    }
    public void setUsers(User user) {
    	Shop.users.add(user);
    }
    
    public static User getCurrentuser() {
    	return currentuser;
    }
    public static void setCurrentuser(User currentuser) {
    	Shop.currentuser = currentuser;
    }
    
    public User selectUser(int id) {
    	User u = users.elementAt(id);
    	return u;
    }
    public static User selectUser(String id) {
    	for(User u : users) {
    		if(id.equals(u.getUid()))
    			return u;
    	}
    	return null;
    }
    public static User selectUser1(String id) {
    	for(User u : users) {
    		if(id.equals(u.getPassword()))
    			return u;
    	}
    	return null;
    }

    public JPanel011 jpanel01 = null;
    public static JPanel022 jpanel02 = null;
    public static JPanel033 jpanel03 = null;
    public static JPanel044 jpanel04 = null;
    static JTabbedPane jtab = new JTabbedPane();   //  JTabbedPane  객체 생성
    public static void main(String[] args) {
    	Shop shop = new Shop();
    	shop.setup();
   // 	shop.run();		//cli로 실행
    	
    	
    	shop.setTitle("online book store");
        shop.jpanel01 = new JPanel011();
        shop.jpanel02 = new JPanel022();
        shop.jpanel03 = new JPanel033();
        shop.jpanel04 = new JPanel044();
        
        jtab.addTab("메인", shop.jpanel01 );

        shop.add(jtab);
        
        shop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shop.setSize(500,700);
        shop.setVisible(true);    
    }
    
    public void run() {
    	System.out.println(getCurrentuser().getUid() + "님 안녕하세요. 오신 것을 환영합니다.");
    	System.out.println("1. 유저 생성		2. 유저 로그인	3. 책 리스트 보기	4. 주문 생성");
    	System.out.println("5. 주문목록에 책 넣기		6. 주문목록 확인 	7. 책 추가 	8. 재입고");
    	System.out.println("9. 결제 			10. 결제 취소  	11. 구매 목록 	12. 모든 유저 구매 목록	");
    	System.out.print("13. 포장			14. 유저 정보  	15.	 16. 	:");
    	
    	int sel = scanner.nextInt();
    	
    	if(sel == 1) {
    		addUser();
    	}
    	else if(sel == 2) {
    		login2();
    	}
    	else if(sel == 3) {
    		book.sList();
    	}
    	else if(sel == 4) {
    		create();
    	}
    	else if(sel == 5) {
    		select();
    		pick();
    	}
    	else if(sel == 6) {
    		list();
    	}
    	else if(sel == 7) {
    		addBook();
    	}
    	else if(sel == 8) {
    		book.restock();
    	}
    	else if(sel == 9) {
    		
    		Order order = new Order();
    		OrderSelect();
    		if (getCurrentorder().getA() == true) {
        		System.out.println("이미 구매한 주문 입니다 메인화면으로 돌아갑니다.");
        		run();
        	}
        	else {
        		System.out.println("aaaa.");
        	}
    		order.pay();
    		getCurrentorder().setType(order.getType());
    		getCurrentorder().setPayment(true);
    	}
    	else if(sel == 10) {
    		Order order = new Order();
    		OrderSelect();
        	if (getCurrentorder().getA() == false) {
        		System.out.println("구매하지 않은 주문 입니다 메인화면으로 돌아갑니다.");
        		run();
        	}
        	if (getCurrentorder().getType() == 5) {
    			getCurrentuser().setPoint(getCurrentuser().getPoint()+getCurrentorder().getAmount());
    			System.out.println("구매 취소 후 남은 포인트:  " + getCurrentuser().getPoint());
    			getCurrentorder().setType(1);
    		}
    		else if (getCurrentorder().getType() == 6) {
    			getCurrentorder().setType(1);
    		}
    		order.cancel();
    		getCurrentorder().cancelBuy();
    	}
    	else if(sel == 11) {
    		plist();
    	}
    	else if(sel == 12) {
    		sOrder();
    	}
    	else if(sel == 13) {
    //		packing();
    	}
    	else if(sel == 14) {
    		getCurrentuser().userinfo();
    	}
    	run();
    }
    
    public int sel4;
    public void pay() {

    }
    
    int bb;
    public void select() {
    	System.out.print("선택할 제품의 번호를 입력해주세요. :");
    	bb = scanner.nextInt();
			System.out.println("");
			System.out.println("번호: " + book.selectBook(bb).getBid() +", "
							+ "제조사: "+ book.selectBook(bb).getName() 
							+", 가격: " + book.selectBook(bb).getPrice() + "을 선택하셨습니다.");
			System.out.println("");
			System.out.print("다시 선택해주세요");
    }
    
    
    
    
    public void list() {		//장바구니 보기
    	System.out.println("");
		System.out.println("");
		Vector<Order> currentorder = selectOrder(getCurrentuser().getUid());
		for(Order o : currentorder ) {
			setCurrentorder(o); //current
			if (getCurrentorder().getA() == false) {	
	    		System.out.println("주문 번호: "+getCurrentorder().getOid());
	    		getCurrentorder().sDate();
	    		getCurrentorder().order();
	    		getCurrentorder().showAmount();
	    		getCurrentorder().pushBuyKey();
	    		getCurrentorder().sPrice();
			}
		}
	}
    
    public void plist() {		// 구매된 장바구니 보기
    	System.out.println("");
		System.out.println("");
		Vector<Order> currentorder = selectOrder(getCurrentuser().getUid());
		for(Order o : currentorder ) {
			setCurrentorder(o); //current
			if (getCurrentorder().getA() == true) {	
	    		System.out.println("주문 번호: "+getCurrentorder().getOid());
	    		getCurrentorder().sDate();
	    		getCurrentorder().order();
	    		getCurrentorder().showAmount();
	    		getCurrentorder().pushBuyKey();
	    		getCurrentorder().sPrice();
			}
		}
	}
    
	public void sOrder() {		// 모든 유저 결제 목록 확인			미완성
		for(Order o : order ) {
			if (o.getA() == true) {
				System.out.println("주문 번호: "+o.getOid());
	    		o.sDate();
	    		o.order();
	    		o.showAmount();
	    		o.pushBuyKey();
	    		o.sPrice();
			}
		}
	}
    
    public void pick() {
    	System.out.println("장바구니를 새로 만드시겠습니까 ?");
		System.out.print("1. 네	2. 아니오	:");
		int bbb = scanner.nextInt();
		if (bbb == 1) {
			create();
		}
		System.out.println("어떤 주문에 추가하시겠습니까 ? 주문 목록을 불러옵니다.");
		list();
		System.out.println("");
		System.out.print("제품을 추가할 ");
		OrderSelect();
		if(getCurrentorder().getA() == true) {
			System.out.println("해당 장바구니는 이미 구매한 장바구니입니다.");
			run();
		}
		
		System.out.print("몇개를 추가하시겠습니까 ?");
		System.out.print("  : ");
		int dd = scanner.nextInt();
		System.out.println("");
		Pick pick = new Pick(BookList.selectBook(bb).getBid(), BookList.selectBook(bb).getName(), BookList.selectBook(bb).getPrice(), dd);
		getCurrentorder().addProduct(pick);
		System.out.println("장바구니에 추가 되었습니다.");
		System.out.print("");
		System.out.println("장바구니를 확인하시려면 '1' 을 입력해주시길 바랍니다. 다른 숫자 입력 시 초기 화면으로 돌아갑니다.");
		System.out.print("  : ");
		int ee = scanner.nextInt();	
		if (ee == 1) {	//장바구니 확인
			list();
		}
		else {			// 메인메뉴
			run();
		}
    }
    
    public void delete() {
    	OrderSelect();
    	System.out.println("");
		System.out.print("몇 번째 제품을 삭제하시겠습니까?(맨 처음 제품이 0입니다.) :");
		int sel3 = scanner.nextInt();
	//	getCurrentorder().delete(sel3);
		System.out.println("");
		list();
    }
    
    int ff;
    public void OrderSelect() {
    	System.out.print("주문의 번호를 입력해주세요.	:");
		ff = scanner.nextInt();
		System.out.println("");
		for(Order o : order ) {  //장바구니 찾기 
			if (getCurrentorder().getOid() == ff) {	// 입력한 장바구니일 경우
			}
			else {				// 입력한 장바구니가 아닌 경우
				setCurrentorder(o); //currentSBasket
			}
		}
		if (getCurrentorder().getUid().equals(getCurrentuser().getUid())) {
			System.out.println("장바구니 번호: "+getCurrentorder().getOid() + "을(를) 선택하셨습니다.");
			System.out.println("-------------- 해당 장바구니 목록 -------------");
			getCurrentorder().sDate();
    		getCurrentorder().order();
    		getCurrentorder().showAmount();
    		getCurrentorder().pushBuyKey();
    		getCurrentorder().sPrice();
		}
		else { 
			System.out.println("해당 바구니는 다른 유저의 바구니입니다 메인 화면으로 돌아갑니다.");
			run();
		}
	}
    
    public void addBook() {
    	System.out.print("추가할 책의 제목을 입력해주세요	:");
		String pd = scanner.next();
		System.out.print("추가할 제품의 가격을 입력해주세요(숫자만).	:");
		int pd1 = scanner.nextInt(); 
		System.out.print("추가할 제품의 수량를 입력해주세요(숫자만).	:");
		int pd2 = scanner.nextInt(); 
		System.out.print("추가할 제품의 장르를 입력해주세요.	:");
		String pd3 = scanner.next(); 
		book.addBook(new Book(pd, pd1, pd2, pd3));
		System.out.print("상품이 추가되었습니다.");
    }
    
    public void addUser() {
    	System.out.println("");
		System.out.print("사용할 id를 입력해주세요. 	: ");
		String id = scanner.next();
		System.out.println("");
		System.out.print("사용할 비밀번호를 입력해주세요.	: ");
		String pass = scanner.next();
		System.out.println("");
		System.out.println("가입이 완료되었습니다. 메인화면으로 이동합니다.");
		User u = new User(id, pass);
		setUsers(u);
		u.setPoint(1000);
    }

    public void login2() {
    	System.out.println("");
		System.out.print("id를 입력해주세요. 		: ");
		String selectuser = scanner.next();
		System.out.println("");
		System.out.print("비밀번호를 입력해주세요.	: ");
		String pass = scanner.next();
		if (selectUser(selectuser).getUid().equals(selectuser)) {
			if (selectUser1(pass).getPassword().equals(pass)) {
				setCurrentuser(selectUser(selectuser));
			}
			else {
				System.out.println("다시 입력해주세요");
				login2();
			}
		}
		else {
			System.out.println("다시 입력해주세요");
			login2();
		}
    }
    
    //// GUI  //// 
    
    
    
    public static void packing() {
    	if(getCurrentorder().getState() == 3) {
    		setCurrentuser(selectUser(getCurrentorder().getUid()));
    		uAlarm alarm = new uAlarm("주문 번호 '"+ getCurrentorder().getOid() +"'가 배송 준비 중입니다.");
            getCurrentuser().addalarm(alarm);
    		setCurrentuser(selectUser("admin"));
    		setbooks();
    		setalarm();
    	}
    	else if(getCurrentorder().getState() == 4) {
    		Shop.setCurrentuser(Shop.selectUser(Shop.getCurrentorder().getUid()));
    		uAlarm alarm = new uAlarm("주문 번호 '"+ Shop.getCurrentorder().getOid() +"'가 배송 중입니다.");
            Shop.getCurrentuser().addalarm(alarm);
    		Shop.setCurrentuser(Shop.selectUser("admin"));
    		Shop.setbooks();
    		Shop.setalarm();
    	}
    	else if(getCurrentorder().getState() == 5) {
    		Shop.setCurrentuser(Shop.selectUser(Shop.getCurrentorder().getUid()));
    		uAlarm alarm = new uAlarm("주문 번호 '"+ Shop.getCurrentorder().getOid() +"'가 배송 완료되었습니다.");
            Shop.getCurrentuser().addalarm(alarm);
    		Shop.setCurrentuser(Shop.selectUser("admin"));
    		Shop.setbooks();
    		Shop.setalarm();
    	}
    }
    
    
    static File uf = new File("file/user.txt");	
    static File of = new File("file/order.txt");
    static File bf = new File("file/book.txt");
    static File pi = new File("file/pick.txt");
    static File nf = new File("file/notice.txt");
    static File af = new File("file/alarm.txt");
    public void setup() {
        BookList book = new BookList();
        setBook(book);
        
        getfile();
        setpick();
    	setCurrentuser(selectUser("guest"));

		b = Shop.pickup(book);
    }
    
    public void getfile() {//파일에서 불러오기
    	getbooks();
        getusers();
		getorders();
		getpicks();
		getnotice();
    	getalarm();
    }
    
    public void getusers() {//파일에서 불러오기
    	try {
    		Scanner ufile = new Scanner(uf);
    		User us;
    		while(ufile.hasNext()) {
    			String data = ufile.nextLine();
    			String [] split = data.split(";;");
    			us = new User();
    			us.setUid(split[0]);
    			us.setPassword(split[1]);
    			us.setName(split[2]);
    			us.setAddress(split[3]);
    			us.setTel(split[4]);
    			us.setPoint(Integer.parseInt(split[5]));
    			us.setUsertype(Integer.parseInt(split[6]));
    			setUsers(us);
    			setCurrentuser(us);
    			getCurrentuser().setName(split[1]);
    			getCurrentuser().setAddress(split[3]);
    			getCurrentuser().setTel(split[4]);
    			getCurrentuser().setPoint(Integer.parseInt(split[5]));
    			getCurrentuser().setUsertype(Integer.parseInt(split[6]));
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}

    }
    public void finduser() {//파일에서 불러오기
    	try {
    		Scanner ufile = new Scanner(uf);
    		User us;
    		while(ufile.hasNext()) {
    			String data = ufile.nextLine();
    			String [] split = data.split(";;");
    			us = new User();
    			if(split[0].equals("guest")) {
    				System.out.println("t");
    			}
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
    }
    
    
    public void getbooks() {//파일에서 불러오기
    	BookList book = new BookList();
    	try {
    		Scanner bfile = new Scanner(bf);
    		Book b;
    		while(bfile.hasNext()) {
    			String data = bfile.nextLine();
    			String [] split = data.split(";;");
    			b = new Book();
    			b.setBid(Integer.parseInt(split[0]));
    			b.setName(split[1]);
    			b.setPrice(Integer.parseInt(split[2]));
    			b.setQuantity(Integer.parseInt(split[3]));
    			b.setAuthor(split[4]);
    			b.setPQuantity(Integer.parseInt(split[5]));
    			book.bookList.add(b);
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
    	book.sbid = book.bookList.lastElement().getBid()+1;
    }
    
    public void getorders() {//파일에서 불러오기
    	try {
    		Scanner ofile = new Scanner(of);
    		Order o;
    		while(ofile.hasNext()) {
    			String data = ofile.nextLine();
    			String [] split = data.split(";;");
    			o = new Order();
    			o.setOid(Integer.parseInt(split[0]));
    			o.setUid(split[1]);
    			o.setAmount(Integer.parseInt(split[2]));
    			int s = Integer.parseInt(split[3]);
    			if(s == 1) {
    				o.setPayment(false);
    			}
    			else if ( s > 1 && s < 6) {
    				o.setPayment(true);
    			}
    			o.setType(Integer.parseInt(split[5]));
    			o.setDate(split[6]);
    			o.setState(Integer.parseInt(split[3]));;
    			setOrder(o);
    			setCurrentorder(o);
    			/*
    			if(o.getType() == 6) {
    				getCurrentorder().setCompany(split[7]);
    				getCurrentorder().setCardnum(split[8]);
    				getCurrentorder().setCardpass(Integer.parseInt(split[9]));
    			}*/
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
    }
    
    public static void getpicks() {//파일에서 불러오기
    	try {
    		Scanner pfile = new Scanner(pi);
    		Pick p;
    		int m;
    		while(pfile.hasNext()) {
    			m = 0;
    			String data = pfile.nextLine();
    			String [] split = data.split(";;");
    			String a = split[0];
    			int b = Integer.parseInt(split[1]);
    			int c = Integer.parseInt(split[2]);
    			String d = split[3];
    			int e = Integer.parseInt(split[4]);
    			int f = Integer.parseInt(split[5]);
    			//int m = Integer.parseInt(split[6]);
    			for(Order o : order ) {  //장바구니 찾기 
        			setCurrentorder(o);
        			if(getCurrentorder().getOid() == b) {
        				p = new Pick(c, d, e, f);
            			p.setId(m);
            			m += 1;
            			getCurrentorder().addProduct(p);
        			}
        		}
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
    	sbid = getCurrentorder().getOid();
    }
    
    public void getnotice() {//파일에서 불러오기
    	try {
    		Scanner nfile = new Scanner(nf);
    		Bnotice n;
    		while(nfile.hasNext()) {
    			String data = nfile.nextLine();
    			String [] split = data.split(";;");
    			String a = split[0];
    			int b = Integer.parseInt(split[1]);
    			String c = split[2];
    			int d = Integer.parseInt(split[3]);
    			String e = split[4];
    			for(User u : users ) {  //장바구니 찾기 
        			setCurrentuser(u);
        			if(getCurrentuser().getUid().equals(a)) {
        				n = new Bnotice(a, b, c, d, e);
            			getCurrentuser().addNotice(n);
        			}
        		}		
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
    }
    
    public void getalarm() { //파일에서 불러오기
    	try {
    		Scanner afile = new Scanner(af);
    		uAlarm al;
    		while(afile.hasNext()) {
    			String data = afile.nextLine();
    			String [] split = data.split(";;");
    			String a = split[0];
    			String b = split[1];
    			String c = split[2];
    			for(User u : users ) {  //장바구니 찾기 
        			setCurrentuser(u);
        			if(getCurrentuser().getUid().equals(a)) {
        				al = new uAlarm(b);
            			getCurrentuser().addalarm(al);
            			al.setDate(c);
        			}
        		}		
    			
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
    }
    
    public static void setusers() { //파일에 저장
    	FileWriter fwu = null;
    	try {
    		fwu = new FileWriter(uf);
    		for (int i = 0; i < users.size(); i++) {
    			User user = users.get(i);
    			String data = user.getUid() + ";;" + user.getPassword() + ";;" + user.getName() + ";;" + user.getAddress() + ";;" + user.getTel() + ";;" + user.getPoint() + ";;" + user.getUsertype()+"\n";
    			fwu.write(data);
    			fwu.flush();
    		}
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	try {
    		fwu.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void setorders() { //파일에 저장
    	FileWriter fwo = null;
    	try {
    		fwo = new FileWriter(of);
    		for (int i = 0; i < order.size(); i++) {
    			Order or = order.get(i);
    			String data = or.getOid() + ";;" + or.getUid() + ";;" + or.getAmount() + ";;" + or.getState() + ";;" +or.getA() + ";;" + or.getType() + ";;" + or.getDate() + ";;" + or.getDelivery()+ ";;" + or.getCompany()+ ";;" + or.getCardnum()+ ";;" + or.getCardpass()+"\n";		
    			fwo.write(data);
    			fwo.flush();
    		}
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	try {
    		fwo.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void setbooks() { //파일에 저장
    	FileWriter fwb = null;
    	try {
    		fwb = new FileWriter(bf);
    		for (int i = 0; i < book.bookList.size(); i++) {
    			Book b = book.bookList.get(i);
    			String data = b.getBid() + ";;" + b.getName() + ";;" + b.getPrice() + ";;" + b.getQuantity() + ";;" + b.getAuthor() + ";;" + b.getPQuantity() +"\n"; 
    			fwb.write(data);
    			fwb.flush();
    		}
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	
    	try {
    		fwb.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void setpick() { //파일에 저장
    	int v = getCurrentorder().getOid();
    	FileWriter fwp = null;
    	try {
    		fwp = new FileWriter(pi);
    		for(Order o : order ) {  //장바구니 찾기 
    			setCurrentorder(o);
    			for (int i = 0; i < getCurrentorder().orderList.size(); i++) {
        			Pick p = getCurrentorder().orderList.get(i);
        			String data = getCurrentorder().getUid() + ";;" + getCurrentorder().getOid() + ";;"  + p.getProductid() + ";;" + p.getItemname() + ";;" + p.getPrice() + ";;" + p.getQuantity() + ";;" + p.getId() +"\n"; 
        			fwp.write(data);
        			fwp.flush();
        		}
    		}
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	
    	try {
    		fwp.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	for(Order o : order ) {  //장바구니 찾기 
			if (getCurrentorder().getOid() == v) {	// 입력한 장바구니일 경우
		
			}
			else {				// 입력한 장바구니가 아닌 경우
				setCurrentorder(o); //currentSBasket
			}
		}
    }
    
    public static void setnotice() { //파일에 저장
    	FileWriter fwn = null;
    	String uid = getCurrentuser().getUid();
    	try {
    		fwn = new FileWriter(nf);
    		for(User u : users) {
    			setCurrentuser(u);
    			for (int i = 0; i < currentuser.noList.size(); i++) {
                	Bnotice n = currentuser.noList.get(i);
                	String data = n.getId() + ";;" + n.getProductid() + ";;" + n.getItemname() + ";;" + n.getPrice() + ";;" + n.getAuthor() + "\n"; 
                	fwn.write(data);
                	fwn.flush();	
            	}
    		}
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	
    	try {
    		fwn.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	setCurrentuser(selectUser(uid));
    }
    
    public static void setalarm() {  //파일에 저장
    	FileWriter fwa = null;
    	String uid = getCurrentuser().getUid();
    	try {
    		fwa = new FileWriter(af);
    		for(User u : users) {
    			setCurrentuser(u);
    			for (int i = 0; i < getCurrentuser().alarm.size(); i++) {
            		uAlarm a = getCurrentuser().alarm.get(i);
            		String data = getCurrentuser().getUid() + ";;" + a.getA() + ";;" + a.getDate() + "\n"; 
            		fwa.write(data);
            		fwa.flush();
        		}
    		}
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	
    	try {
    		fwa.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	setCurrentuser(selectUser(uid));
    }
    
    static int sbid = 0;
    public void create() {
		//사용자 추가 장바구니 생성
		User u = selectUser(getCurrentuser().getUid());
		setCurrentuser(u);
		currentorder = createOrder(getCurrentuser().getUid());
		//현재 장바구니로 설정
		setCurrentorder(currentorder); //currentSBasket
		sbid = sbid +1 ;
		getCurrentorder().setOid(sbid);
		b = pickup(book);
		getCurrentorder().setState(1);
		setpick();
		setorders();
	}

    public static Book pickup(BookList bl) {
    	int id;
    	int max = bl.getBookList().size();
    	id = (int) (Math.random()*max);
    	return  BookList.selectBook(id);
    }
}


class join extends JDialog {		//회원가입 
	join() {
        setTitle("회원가입");
        JPanel p = new JPanel();
        Label l1 = new Label("아이디");
        Label l2 = new Label("비밀번호	");
        Label l3 = new Label("이름");
        Label l4 = new Label("주소");
        Label l5 = new Label("전화번호");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JTextField t5 = new JTextField();
        JButton btn1 = new JButton("확인");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(btn1); 
        add(btn2); 
        l1.setBounds(40, 30, 40, 40);
        l2.setBounds(40, 80, 80, 40);
        l3.setBounds(40, 130, 40, 40);
        l4.setBounds(40, 180, 40, 40);
        l5.setBounds(40, 230, 80, 40);
        t1.setBounds(120, 30, 200, 30);
        t2.setBounds(120, 80, 200, 30);
        t3.setBounds(120, 130, 200, 30);
        t4.setBounds(120, 180, 200, 30);
        t5.setBounds(120, 230, 200, 30);
        btn1.setBounds(120, 300, 80, 30);
        btn2.setBounds(240, 300, 80, 30);
        add(p); 
        setSize(450,400);
        setVisible(true);
        
        Shop s = new Shop();
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int t = 0;
				try {											//입력한 아이디와 비밀번호가 일치한지 확인
		    		Scanner ufile = new Scanner(Shop.uf);
		    		while(ufile.hasNext()) {
		    			String data = ufile.nextLine();
		    			String [] split = data.split(";;");
		    			if(split[0].equals(t1.getText())) {
		    				t = 2;
		    			}
		    		}
		    	} catch (Exception e1) {
		    		   e1.printStackTrace();
		    	}
				if(t == 2) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
				}
				else {
					if(t1.getText().equals("") || t2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 입력해주세요");
					}
					else if(t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.");
					}
					else if(t1.getText().contains(";") || t2.getText().contains(";")) {
						JOptionPane.showMessageDialog(null, "아이디나 비밀번호에 들어갈 수 없는 단어가 있습니다.");
					}
					else if(t3.getText().contains(";") || t4.getText().contains(";") || t5.getText().contains(";")) {
						JOptionPane.showMessageDialog(null, "';'는 정보에 들어갈 수 없는 문자입니다.");
					}
					
					else {
						User u = new User(t1.getText(), t2.getText());
						s.setUsers(u);
						u.setPoint(1000);
						s.setCurrentuser(u);
						s.currentuser.setName(t3.getText());
						s.currentuser.setAddress(t4.getText());
						s.currentuser.setTel(t5.getText());
			           	if(Shop.getCurrentuser().getUsertype() !=3 ) {
			                Shop.jtab.addTab("책 리스트", Shop.jpanel02 );
			                Shop.jtab.addTab("주문", Shop.jpanel03 );
			           	}
						JPanel011.label.setText(Shop.getCurrentuser().getUid()+" 님 환영합니다.");
						JPanel033.oidList();
			           	JPanel033.model.setNumRows(0);
						s.setusers();
						dispose();
					}
				}
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
}

class userinfo extends JDialog {		//회원 정보 
	userinfo() {
        setTitle("유저 정보 수정");
        String d = Shop.getCurrentuser().getPoint()+"";
        JPanel p = new JPanel();
        Label l1 = new Label("아이디");
        Label l2 = new Label("비밀번호	");
        Label l3 = new Label("이름");
        Label l4 = new Label("주소");
        Label l5 = new Label("전화번호");
        Label l6 = new Label("보유 포인트");
        Label l7 = new Label(Shop.getCurrentuser().getUid());
        Label l8 = new Label(d);
        JTextField t1 = new JTextField(Shop.getCurrentuser().getPassword());
        JTextField t2 = new JTextField(Shop.getCurrentuser().getName());
        JTextField t3 = new JTextField(Shop.getCurrentuser().getAddress());
        JTextField t4 = new JTextField(Shop.getCurrentuser().getTel());
        JButton btn1 = new JButton("수정");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(btn1); 
        add(btn2); 
        l1.setBounds(40, 10, 40, 30);
        l2.setBounds(40, 50, 80, 30);
        l3.setBounds(40, 90, 40, 30);
        l4.setBounds(40, 130, 40, 30);
        l5.setBounds(40, 170, 80, 30);
        l6.setBounds(40, 210, 80, 30);
        l7.setBounds(150, 10, 200, 30);
        t1.setBounds(150, 50, 200, 30);
        t2.setBounds(150, 90, 200, 30);
        t3.setBounds(150, 130, 200, 30);
        t4.setBounds(150, 170, 200, 30);
        l8.setBounds(150, 210, 200, 30);
        btn1.setBounds(100, 300, 80, 30);
        btn2.setBounds(230, 300, 80, 30);
        add(p); 
        setSize(400,400);
        setVisible(true);

        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = t1.getText();
				String a2 = t2.getText();
				String a3 = t3.getText();
				String a4 = t4.getText();
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				}
				else if(t1.getText().contains(";")) {
					JOptionPane.showMessageDialog(null, "비밀번호에 들어갈 수 없는 단어가 있습니다.");
				}
				else if(t2.getText().contains(";") || t3.getText().contains(";") || t4.getText().contains(";")) {
					JOptionPane.showMessageDialog(null, "';'는 정보에 들어갈 수 없는 문자입니다.");
				}
				else if(t2.getText().contains("") || t3.getText().contains("") || t4.getText().contains("")) {
					JOptionPane.showMessageDialog(null, "빈 칸을 채워주시길 바랍니다.");
				}
				else {
					Shop.getCurrentuser().setPassword(a1);
					Shop.getCurrentuser().setName(a2);
					Shop.getCurrentuser().setAddress(a3);
					Shop.getCurrentuser().setTel(a4);
					Shop.setusers();
					dispose();
				}
				
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
}

class login extends JDialog {		//회원 로그인
	JButton btnLogin;
    JButton btnInit;
    JPasswordField passText;
    JTextField userText;
    boolean bLoginCheck;
	Shop s;
	
	login() {
		setTitle("login");
        setSize(280, 150);
        setResizable(false);
        setLocation(800, 450);
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
        add(panel);
        setVisible(true);
    }
    public void placeLoginPanel(JPanel panel){
       panel.setLayout(null);     
       JLabel userLabel = new JLabel("User");
       JLabel passLabel = new JLabel("Pass");
       userText = new JTextField(20);
       passText = new JPasswordField(20);
       btnInit = new JButton("join");
       btnLogin = new JButton("Login");
       panel.add(userLabel);
       panel.add(passLabel);  
       panel.add(userText);
       panel.add(passText);
       panel.add(btnInit);
       panel.add(btnLogin);
       userLabel.setBounds(10, 10, 80, 25);
       passLabel.setBounds(10, 40, 80, 25);
       userText.setBounds(100, 10, 160, 25);
       passText.setBounds(100, 40, 160, 25);
       btnInit.setBounds(10, 80, 100, 25);
       btnLogin.setBounds(160, 80, 100, 25);
       
       passText.addActionListener(new ActionListener() {          
    	   public void actionPerformed(ActionEvent e) {
    		   isLoginCheck();        
    	   }
       });
       btnInit.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   new join();
           }
       });
       btnLogin.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   isLoginCheck();
           }
       });
    }
    public void isLoginCheck(){
    	int t = 0;
    	String userid = userText.getText();
    	String userpass = passText.getText();
    	System.out.println(userid);
   		System.out.println(userpass);
   		try {											//입력한 아이디와 비밀번호가 일치한지 확인
    		Scanner ufile = new Scanner(Shop.uf);
    		User us;
    		while(ufile.hasNext()) {
    			String data = ufile.nextLine();
    			String [] split = data.split(";;");
    			us = new User();
    			if(split[0].equals(userid)) {
    				t = 2;
    				if(split[1].equals(userpass)) {
    					t = 3;
    				}
    			}
    		}
    	} catch (Exception e) {
    		   e.printStackTrace();
    	}
   		if(t==3) {												//일치할경우
   			Shop.setCurrentuser(Shop.selectUser(userid));
           	JOptionPane.showMessageDialog(null, "Success");
           	if(Shop.getCurrentuser().getUid().equals("admin")) {
				JPanel011.label.setText("관리자님 환영합니다.");
			}
			else if(!Shop.getCurrentuser().getUid().equals("guest")) {
				JPanel011.label.setText(Shop.getCurrentuser().getName()+"님 환영합니다.");
			}
           	bLoginCheck = true;
           	if(Shop.getCurrentuser().getUsertype() !=3 ) {
                Shop.jtab.addTab("책 리스트", Shop.jpanel02 );
                Shop.jtab.addTab("주문", Shop.jpanel03 );
           	}
           	if(Shop.getCurrentuser().getUsertype() == 10) {
           		Shop.jtab.addTab("관리자기능", Shop.jpanel04 );
           	}
           	else if(Shop.getCurrentuser().getUsertype() != 10) {
           		Shop.jtab.remove(Shop.jpanel04);
           	}

           	for(Order o : Shop.order ) {  //장바구니 찾기 
    			if (Shop.getCurrentorder().getUid().equals(Shop.getCurrentuser().getUid())) {	// 입력한 장바구니일 경우
    			}
    			else {				// 입력한 장바구니가 아닌 경우
    				Shop.setCurrentorder(o); //currentSBasket
    			}
    		}
           	JPanel033.oidList();
           	JPanel033.model.setNumRows(0);
           	JPanel033.set();
           	
           	dispose();
       	}
       	else if (t==2){													//비밀번호 불일치
       		JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
       	}
       	else {															//아이디가 파일에 없는경우
       		JOptionPane.showMessageDialog(null, "아이디를 다시 확인해주세요");
       	}
    }
}

class pay extends JDialog {							//결제 종류 선택
	pay() {
        setTitle("결제");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn1 = new JButton("포인트 결제");
        JButton btn2 = new JButton("카드 결제");
		getContentPane().add(btn1); 
		getContentPane().add(btn2); 
		btn1.setBounds(27, 48, 97, 23);
		btn2.setBounds(27, 48, 157, 23);
		
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new pointp();
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new cardp();
			}
		});
		
        setSize(300,150);
        setResizable(false);
        setVisible(true);
    }
}

class orsel extends JDialog {						// 주문 수정
	orsel() {
        setTitle("주문 수정");
        JPanel p = new JPanel();
        JButton btn1 = new JButton("책 삭제");
        JButton btn2 = new JButton("책 수량 수정");
		add(btn1); 
		add(btn2); 
		add(p);
		btn1.setBounds(20, 20, 100, 30);
		btn2.setBounds(150, 20, 100, 30);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new orrem();
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ordel();
			}
		});
        setSize(300,150);
        setResizable(false);
        setVisible(true);
    }
}

class orrem extends JDialog {				//주문에서 도서 삭제
	BookList book;
	orrem() {
        setTitle("책 삭제");
        JPanel p = new JPanel();
        Label l1 = new Label("삭제할 도서 번호 입력 :");
        JTextField t1 = new JTextField("");
        JButton btn1 = new JButton("삭제");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(t1);
        add(btn1); 
        add(btn2);
        add(p); 
        l1.setBounds(30, 50, 130, 30);
        t1.setBounds(180, 50, 50, 30);
        btn1.setBounds(70, 100, 60, 30);
        btn2.setBounds(160, 100, 60, 30);
        setSize(300,200);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "도서를 선택해 주시길 바랍니다.");
				}
				else if( Integer.parseInt(t1.getText()) > BookList.bookList.size()) {
		        	JOptionPane.showMessageDialog(null, "주문에 없는 도서입니다.");
		        }
		        else {
					String z;
					int n = 0;
					z = t1.getText();
			        n = Integer.parseInt(z);
			        int pick = 0, t= 0;
		        	try {
			    		Scanner pfile = new Scanner(Shop.pi);
			    		Pick p;
			    		while(pfile.hasNext()) {
			    			String data = pfile.nextLine();
			    			String [] split = data.split(";;");
			    			int b = Integer.parseInt(split[1]);		// oud
			    			int c = Integer.parseInt(split[2]);		// 책번호
			    			int m = Integer.parseInt(split[6]);		// id
			    			if(Shop.getCurrentorder().getOid() == b) {
			    				if(book.selectBook(n).getBid() == c) {
			    					t = 2;
			    					pick = m;
			    					System.out.println(pick);
				        		}
			    			}
			    		}
			    	} catch (Exception e1) {
			    		   e1.printStackTrace();
			    	}
					if(t==2) {
						Order.remove(pick);
						int h = Shop.getCurrentorder().getOid();
						for (Order o : Shop.order) {
							int id = 0;
							Shop.setCurrentorder(o);
							for(Pick p : Shop.getCurrentorder().orderList) {
								p.setId(id);
								id +=1;
							}
						}
						for(Order o : Shop.order ) {  //장바구니 찾기 
							if (Shop.getCurrentorder().getOid() == h) {	// 입력한 장바구니일 경우
							}
							else {				// 입력한 장바구니가 아닌 경우
								Shop.setCurrentorder(o); //currentSBasket
							}
						}
				        JPanel033.model.setNumRows(0);
		    			JPanel033.table();
			        	Shop.setpick();
			        	Shop.setorders();
			        	dispose();
					}
					else {
		        		JOptionPane.showMessageDialog(null, "주문에 없는 도서입니다.");
		        	}
					
		        }
				
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        
    }
}

class ordel extends JDialog {				// 주문에서 도서 수량 수정 
	BookList book;
	Order o;
	ordel() {
        setTitle("책 수량 수정");
        JPanel p = new JPanel();
        Label l1 = new Label("수량을 수정할 도서 번호 입력 :");
        Label l2 = new Label("수량 입력 :");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JButton btn1 = new JButton("수정");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(btn1); 
        add(btn2);
        add(p); 
        l1.setBounds(20, 50, 160, 30);
        l2.setBounds(20, 90, 160, 30);
        t1.setBounds(200, 50, 50, 30);
        t2.setBounds(200, 90, 50, 30);
        btn1.setBounds(70, 140, 60, 30);
        btn2.setBounds(160, 140, 60, 30);
        setSize(300,300);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        t2.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String z, y;
				int n = 0;
				int m = 0;
				if(t1.getText().equals("") && t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "빈 칸을 입력해주세요");
				}
				else if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "도서를 선택해 주시길 바랍니다.");
				}
				else if(t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "수량을 적어주시길 바랍니다");
				}
				else if(Integer.parseInt(t1.getText()) > BookList.bookList.size()) {
		        	JOptionPane.showMessageDialog(null, "주문에 없는 도서입니다.");
		        }
		        else {
		        	z = t1.getText();
			        n = Integer.parseInt(z);
			        y = t2.getText();
			        m = Integer.parseInt(y);
			        int pick = 0, t= 0;
		        	try {
			    		Scanner pfile = new Scanner(Shop.pi);
			    		Pick p;
			    		while(pfile.hasNext()) {
			    			String data = pfile.nextLine();
			    			String [] split = data.split(";;");
			    			int b = Integer.parseInt(split[1]);		// oud
			    			int c = Integer.parseInt(split[2]);		// 책번호
			    			int o = Integer.parseInt(split[6]);		// id
			    			if(Shop.getCurrentorder().getOid() == b) {
			    				if(book.selectBook(n).getBid() == c) {
			    					t = 2;
			    					pick = o;
			    					System.out.println(pick);
				        		}
			    			}
			    		}
			    	} catch (Exception e1) {
			    		   e1.printStackTrace();
			    	}
					if(t==2) {
						if(BookList.selectBook(n).getQuantity() ==0) {
				        	JOptionPane.showMessageDialog(null, "선택하신 도서는 품절된 도서입니다.");
				        }
				        else if(BookList.selectBook(n).getQuantity() < m) {
				        	JOptionPane.showMessageDialog(null, "선택하신 도서의 수량이 서점이 보유한 수량보다 많습니다.");
				        }
				        else {
				        	Order.delete(n, m);
				        	JPanel033.model.setNumRows(0);
		        			JPanel033.table();
				        	Shop.setpick();
				        	Shop.setorders();
		        			dispose();
				        }
					}
					else {
		        		JOptionPane.showMessageDialog(null, "주문에 없는 도서입니다.");
		        	}
		        }
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        
    }
}

class pointp extends JDialog {				//포인트 결제
	JPanel022 j;
	pointp() {
    	setTitle("포인트 결제");
    	int Amount = 0;
        for( Pick pk : Shop.getCurrentorder().orderList) {
    		Amount = Amount + pk.getPrice() * pk.getQuantity();
    	}
        String b = ""+Amount;
        int point = Shop.getCurrentuser().getPoint();
        String c = ""+point;
        int sum;
        if(point - Amount < 0) {
        	sum = 0;
        }
        else {
        	sum = point - Amount;
        }
        String d = ""+sum;
        
        JPanel p = new JPanel();
        Label l1 = new Label("현재 보유 포인트 :");
        Label l2 = new Label("소모될 포인트 :");
        Label l3 = new Label("남게되는 포인트: ");
        Label l4 = new Label(c);
        Label l5 = new Label(b);
        Label l6 = new Label(d);
        JButton btn1 = new JButton("결제");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(btn1); 
        add(btn2); 
        add(p);
        l1.setBounds(130, 100, 140, 30);
        l2.setBounds(130, 140, 140, 30);
        l3.setBounds(130, 180, 140, 30);
        l4.setBounds(280, 100, 40, 30);
        l5.setBounds(280, 140, 40, 30);
        l6.setBounds(280, 180, 40, 30);
        btn1.setBounds(125, 260, 80, 30);
        btn2.setBounds(240, 260, 80, 30);
        setSize(500,500);
        setVisible(true);

        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = 0;
	            for( Pick pk : Shop.getCurrentorder().orderList) {
	        		amount = amount + pk.getPrice() * pk.getQuantity();
	        		Shop.getCurrentorder().setAmount(amount);
	        	}
	            int t = 0;
	            int u = 0;
	            int s = 0;
		    	for(Pick pk : Shop.getCurrentorder().orderList) {
		    		if(BookList.selectBook(pk.getProductid()).getQuantity() == 0) {
		    			JOptionPane.showMessageDialog(null, "선택하신 주문에 품절된 도서가 있습니다.");
		    			u=4;
		    		}
		    		else if(BookList.selectBook(pk.getProductid()).getQuantity() < pk.getQuantity() && BookList.selectBook(pk.getProductid()).getQuantity() > 0) {
		    			JOptionPane.showMessageDialog(null, "선택하신 주문에 서점이 보유한 도서보다 많은 도서가 있습니다.");
		    			s=2;
		    		}
		    		else {
		    			t=3;	
		    		}
		    		BookList.selectBook(pk.getProductid()).setQuantity(BookList.selectBook(pk.getProductid()).getQuantity()-pk.getQuantity());
		    		BookList.selectBook(pk.getProductid()).setPQuantity(BookList.selectBook(pk.getProductid()).getPQuantity()+pk.getQuantity());
		    	}
		    	if(t==3 && s != 2 && u != 4) {
		    		int mn=0;
		    		try {
		        		Scanner ufile = new Scanner(Shop.uf);
		        		while(ufile.hasNext()) {
		        			String data = ufile.nextLine();
		        			String [] split = data.split(";;");
		        			if(Shop.getCurrentorder().getUid().equals(split[0])) {
		        				mn=1;
		        				System.out.println("1: "+amount);
		        				System.out.println("2: "+Integer.parseInt(split[5]));
		        				if(amount <= Integer.parseInt(split[5])) {
		        					mn=2;
		        				}
		        			}
		        		}
		        	} catch (Exception e1) {
		        		   e1.printStackTrace();
		        	}
		    		if(mn==2) {
			    		Shop.getCurrentuser().setPoint(Shop.getCurrentuser().getPoint() - amount);
		    			Shop.getCurrentorder().setPayment(true);
				    	Shop.getCurrentorder().setType(5);
				    	Shop.getCurrentorder().setState(2);
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "포인트가 부족합니다.");
		    		}
		    	}
		    	else if(s == 2 || u == 4) {
		    		for(Pick pk : Shop.getCurrentorder().orderList) {
		    			BookList.selectBook(pk.getProductid()).setQuantity(BookList.selectBook(pk.getProductid()).getQuantity()+pk.getQuantity());
			    		BookList.selectBook(pk.getProductid()).setPQuantity(BookList.selectBook(pk.getProductid()).getPQuantity()-pk.getQuantity());
		    		}
		    	}
		    	Shop.setbooks();
		    	Shop.setusers();
		    	Shop.setpick();
		    	Shop.setorders();
		    	JPanel033.set();
				dispose();
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
}

class cardp extends JDialog {
	cardp() {
    	setTitle("카드 결제");
    	int Amount = 0;
        for( Pick pk : Shop.getCurrentorder().orderList) {
    		Amount = Amount + pk.getPrice() * pk.getQuantity();
    	}
        String a = ""+Amount;
        JPanel p = new JPanel();
        Label l1 = new Label("카드사");
        Label l2 = new Label("카드번호	");
        Label l3 = new Label("비밀번호");
        Label l4 = new Label("결재 금액 :");
        Label l5 = new Label(a);
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JButton btn1 = new JButton("결제");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(t1);
        add(t2);
        add(t3);
        add(btn1); 
        add(btn2);
        add(p); 
        l1.setBounds(90, 110, 40, 40);
        l2.setBounds(90, 150, 80, 40);
        l3.setBounds(90, 190, 80, 40);
        l4.setBounds(90, 230, 80, 40);
        l5.setBounds(170, 230, 200, 30);
        t1.setBounds(170, 110, 200, 30);
        t2.setBounds(170, 150, 200, 30);
        t3.setBounds(170, 190, 200, 30);
        btn1.setBounds(125, 280, 80, 30);
        btn2.setBounds(240, 280, 80, 30);
        setSize(500,500);
        setVisible(true);
        
        t3.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.");
				}
				else if(t1.getText().contains(";") || t2.getText().contains(";")) {
					JOptionPane.showMessageDialog(null, "';'는 들어갈 수 없는 문자입니다.");
				}
				int t = 0;
	            int u = 0;
	            int s = 0;
				for(Pick pk : Shop.getCurrentorder().orderList) {
	    			if(BookList.selectBook(pk.getProductid()).getQuantity() == 0) {
	    				JOptionPane.showMessageDialog(null, "선택하신 도서는 품절된 도서입니다.");
	    				u=4;
	    			}
	    			else if(BookList.selectBook(pk.getProductid()).getQuantity() < pk.getQuantity()) {
	    				JOptionPane.showMessageDialog(null, "선택하신 도서는 현재 주문하신 수량보다 적습니다.");
	    				s=2;
	    			}
	    			else {
	    				t=3;
	    			}
	    			BookList.selectBook(pk.getProductid()).setQuantity(BookList.selectBook(pk.getProductid()).getQuantity()-pk.getQuantity());
	    			BookList.selectBook(pk.getProductid()).setPQuantity(BookList.selectBook(pk.getProductid()).getPQuantity()+pk.getQuantity());
	    		}
				if(t==3 && s != 2 && u != 4) {
    				Shop.getCurrentorder().setPayment(true);
		    		Shop.getCurrentorder().setType(6);
		    		Shop.getCurrentorder().setState(2);
	    		}
	    		else if(s == 2 || u == 4) {
	    			for(Pick pk : Shop.getCurrentorder().orderList) {
	    				BookList.selectBook(pk.getProductid()).setQuantity(BookList.selectBook(pk.getProductid()).getQuantity()+pk.getQuantity());
		    			BookList.selectBook(pk.getProductid()).setPQuantity(BookList.selectBook(pk.getProductid()).getPQuantity()-pk.getQuantity());
	    			}
	    		}
				Shop.setbooks();
	    		Shop.setusers();
	    		Shop.setpick();
	    		Shop.setorders();
	    		JPanel022.model.setNumRows(0);
	    		JPanel022.set();
				dispose();
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
}

class bookselect extends JDialog {
	BookList b;
	Book book;
	Shop s;
	Order o;
	Vector<Order> currentorder = Shop.selectOrder(Shop.getCurrentuser().getUid());
    Vector orderid;
	bookselect() {
    	setTitle("책 선택");
        JPanel p = new JPanel();
        Label l0 = new Label("책 번호 입력");
        Label l1 = new Label("책 수량 입력");
        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        Label l6 = new Label("");
        Label l7 = new Label("");
        Label l8 = new Label("주문 선택");
        Label l9 = new Label("");

        JTextField t1 = new JTextField("");
        JTextField t2 = new JTextField("");
        JButton btn1 = new JButton("선택");
        JButton btn2 = new JButton("주문에 추가");
        JButton btn3 = new JButton("주문 새로 생성 및 선택한 책 추가");
        JLabel il = new JLabel();
        JComboBox cbx1 = new JComboBox();
        add(l0);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(il);
        add(t1);
        add(t2);
        add(btn1); 
        add(btn2); 
        add(btn3); 
        add(cbx1);
        add(p);
        
        orderid = new Vector();
        for (Order o : currentorder) {
        	if(o.getA() == false) {
        		orderid.addElement(o.getOid());
        		cbx1.addItem(orderid);
        	}
    	}
        cbx1.setModel(new DefaultComboBoxModel(orderid));
        
        l0.setBounds(65, 30, 80, 30);
        l1.setBounds(65, 70, 80, 30);
        l2.setBounds(200, 130, 200, 30);
        l3.setBounds(30, 240, 250, 30);
        l4.setBounds(30, 290, 250, 30);
        l5.setBounds(30, 340, 250, 30);
        l6.setBounds(30, 390, 250, 30);
        l7.setBounds(30, 440, 250, 30);
        l8.setBounds(60, 550, 60, 30);
        l9.setBounds(385, 160, 60, 30);
        il.setBounds(300, 200, 200, 300);
        t1.setBounds(205, 30, 80, 30);
        t2.setBounds(205, 70, 80, 30);
        btn1.setBounds(350, 30, 80, 30);
        btn2.setBounds(280, 550, 100, 30);
        btn3.setBounds(280, 600, 220, 30);
        cbx1.setBounds(170, 550, 50, 30);
        
        setSize(550,700);
        setVisible(true);
		
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        t2.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "번호를 입력해주세요");
				}
				else {
					int h=0;
			    	try {
			    		Scanner bfile = new Scanner(Shop.bf);
			    		while(bfile.hasNext()) {
			    			String data = bfile.nextLine();
			    			String [] split = data.split(";;");
			    			if(split[0].equals(t1.getText())) {
			    				h = 2;
			    			}
			    		}
			    	} catch (Exception e1) {
			    		   e1.printStackTrace();
			    	}
			    	if(h==2) {
			    		il.setIcon(null);
						String a, b, c, d, f;
						String z = t1.getText();
					    int n = Integer.parseInt(z);
						ImageIcon img = new ImageIcon("file/img/"+BookList.selectBook(n).getName()+".jpg");
						il.setIcon(img);
						a = "책 번호 :  " + BookList.selectBook(n).getBid();
					    b = "책 이름 :  " + BookList.selectBook(n).getName();
					    c = "책 가격 :  " + BookList.selectBook(n).getPrice();
					    if(BookList.selectBook(n).getQuantity() == 0) {
					    	d = "책 수량 :  품절";
					    }
					    else {
					    	d = "책 수량 :  " + BookList.selectBook(n).getQuantity();
					    }
					    f = "책 작가 :  " + BookList.selectBook(n).getAuthor();
					    l2.setText("책 정보");
						l3.setText(a);
						l4.setText(b);
						l5.setText(c);
						l6.setText(d);
						l7.setText(f);
						l9.setText("사진");
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "해당 번호는 등록이 안된 도서입니다.");
			    	}
				}
			}
		});
        cbx1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb =(JComboBox)e.getSource();
				String index = cb.getSelectedItem().toString();
				int n = Integer.parseInt(index);
				for(Order o : Shop.order ) {  //장바구니 찾기 
					if (Shop.getCurrentorder().getOid() == n) {	// 입력한 장바구니일 경우	
					}
					else {				// 입력한 장바구니가 아닌 경우
						Shop.setCurrentorder(o); //currentSBasket
					}
				}
				
			}
    	});
        
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String z, y;
				int n = 0;
				int m = 0;
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "도서를 선택해 주시길 바랍니다.");
				}
				else if(t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "수량을 적어주시길 바랍니다");
				}
				
				else {
					z = t1.getText();
			        n = Integer.parseInt(z);
			        y = t2.getText();
			        m = Integer.parseInt(y);
			        int hh=0;
			    	try {
			    		Scanner bfile = new Scanner(Shop.bf);
			    		while(bfile.hasNext()) {
			    			String data = bfile.nextLine();
			    			String [] split = data.split(";;");
			    			if(split[0].equals(t1.getText())) {
			    				hh = 2;
			    			}
			    		}
			    	} catch (Exception e1) {
			    		   e1.printStackTrace();
			    	}
			    	if(hh==2) {
			    		if(Shop.getCurrentorder().getA() == true) {
				        	JOptionPane.showMessageDialog(null, "선택하신 주문은 이미 구매한 주문입니다.");
				        }
						if(!Shop.getCurrentorder().getUid().equals(Shop.getCurrentuser().getUid())) {
				        	JOptionPane.showMessageDialog(null, "선택하신 주문은 다른 유저의 주문입니다..");
				        }
				        if(BookList.selectBook(n).getQuantity() ==0) {
				        	JOptionPane.showMessageDialog(null, "선택하신 도서는 품절된 도서입니다.");
				        }
				        else if(BookList.selectBook(n).getQuantity() < m) {
				        	JOptionPane.showMessageDialog(null, "선택하신 도서의 수량이 서점이 보유한 수량보다 많습니다.");
				        }
				        else {
				        	
				        	int t = 0;
				        	for(Pick pk : Shop.getCurrentorder().orderList) {
				        		if(pk.getProductid() == BookList.selectBook(n).getBid()) {
				        			pk.setQuantity(pk.getQuantity() + m);
				        			t = 1;
				        			dispose();
				        			break;
				        		}
				        		else {
				        			t = 2;
				        		}
				        	}
				        	if(t== 2) {
				        		Pick pick = new Pick(BookList.selectBook(n).getBid(), BookList.selectBook(n).getName(), BookList.selectBook(n).getPrice(), m);
					        	Shop.getCurrentorder().addProduct(pick);
					        	int h = Shop.getCurrentorder().getOid();
								for (Order o : Shop.order) {
									int id = 0;
									Shop.setCurrentorder(o);
									for(Pick p : Shop.getCurrentorder().orderList) {
										p.setId(id);
										id +=1;
									}
								}
								for(Order o : Shop.order ) {  //장바구니 찾기 
									if (Shop.getCurrentorder().getOid() == h) {	// 입력한 장바구니일 경우
									}
									else {				// 입력한 장바구니가 아닌 경우
										Shop.setCurrentorder(o); //currentSBasket
									}
								}
					        	Shop.setpick();
					        	Shop.setorders();
					        	JPanel033.model.setNumRows(0);
					        	JPanel033.table();
					        	JPanel033.set();
					        	dispose();
				        	}
				        }
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "해당 번호는 등록이 안된 도서입니다.");
			    	}
				}
			}
		});
        btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String z, y;
				int n = 0;
				int m = 0;
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "도서를 선택해 주시길 바랍니다.");
				}
				else {
					z = t1.getText();
			        n = Integer.parseInt(z);
				}
				if(t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "수량을 적어주시길 바랍니다");
				}
				else {
					y = t2.getText();
			        m = Integer.parseInt(y);
				}
				int hh=0;
		    	try {
		    		Scanner bfile = new Scanner(Shop.bf);
		    		while(bfile.hasNext()) {
		    			String data = bfile.nextLine();
		    			String [] split = data.split(";;");
		    			if(split[0].equals(t1.getText())) {
		    				hh = 2;
		    			}
		    		}
		    	} catch (Exception e1) {
		    		   e1.printStackTrace();
		    	}
		    	if(hh==2) {
		    		if(BookList.selectBook(n).getQuantity() ==0) {
			        	JOptionPane.showMessageDialog(null, "선택하신 도서는 품절된 도서입니다.");
			        }
			        else if(BookList.selectBook(n).getQuantity() < m) {
			        	JOptionPane.showMessageDialog(null, "선택하신 도서의 수량이 서점이 보유한 수량보다 많습니다.");
			        }
					//사용자 추가 장바구니 생성
					User u = Shop.selectUser(Shop.getCurrentuser().getUid());
					Shop.setCurrentuser(u);
					
					o = Shop.createOrder(Shop.getCurrentuser().getUid());
					
					//현재 장바구니로 설정
					Shop.setCurrentorder(o); //currentSBasket
					Shop.sbid = Shop.sbid +1 ;
					Shop.getCurrentorder().setOid(Shop.sbid);
					Shop.getCurrentorder().setState(1);
			        
			        
	        		Pick pick = new Pick(BookList.selectBook(n).getBid(), BookList.selectBook(n).getName(), BookList.selectBook(n).getPrice(), m);
		        	Shop.getCurrentorder().addProduct(pick);
					JPanel033.orderid.addElement(Shop.getCurrentorder().getOid());
		        	Shop.setpick();
		        	Shop.setorders();
		        	dispose();
		    	}
		    	else {
		    		JOptionPane.showMessageDialog(null, "해당 번호는 등록이 안된 도서입니다.");
		    	}
			}
		});
    }
}

class addbook extends JDialog {
	BookList book;
	Shop s;
	addbook() {
		setTitle("도서 추가");
        JPanel p = new JPanel();
        Label l1 = new Label("도서 이름");
        Label l2 = new Label("도서 가격");
        Label l3 = new Label("도서 수량");
        Label l4 = new Label("작가");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JButton btn1 = new JButton("추가");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(btn1); 
        add(btn2); 
        add(p); 
        l1.setBounds(40, 10, 60, 30);
        l2.setBounds(40, 50, 60, 30);
        l3.setBounds(40, 90, 60, 30);
        l4.setBounds(40, 130, 60, 30);
        t1.setBounds(120, 10, 200, 30);
        t2.setBounds(120, 50, 200, 30);
        t3.setBounds(120, 90, 200, 30);
        t4.setBounds(120, 130, 200, 30);
        btn1.setBounds(90, 190, 80, 30);
        btn2.setBounds(210, 190, 80, 30);
        setSize(400,300);
        setVisible(true);
        
        t2.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        t3.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        
        Shop s = new Shop();
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.");
				}
				else if(t1.getText().contains(";") || t2.getText().contains(";") || t3.getText().contains(";") || t4.getText().contains(";")) {
					JOptionPane.showMessageDialog(null, "';'는 정보에 들어갈 수 없는 문자입니다.");
				}
				else if(t1.getText().contains(":") || t2.getText().contains(":") || t3.getText().contains(":") || t4.getText().contains(":")) {
					JOptionPane.showMessageDialog(null, "':'는 정보에 들어갈 수 없는 문자입니다.");
				}
				else {
					String a = t2.getText();
					String b = t3.getText();
					int n = Integer.parseInt(a);
					int m = Integer.parseInt(b);
					BookList.addBook(new Book(t1.getText(), n, m, t4.getText()));
					
					for(User u : Shop.users) {
						Shop.setCurrentuser(u);
				        uAlarm alarm = new uAlarm("신규 도서 '"+BookList.bookList.lastElement().getName()+"'가 추가되었습니다..");
				        Shop.getCurrentuser().addalarm(alarm);
					}
					JOptionPane.showMessageDialog(null, "추가 완료!");
					Shop.setCurrentuser(Shop.selectUser("admin"));
					Shop.setbooks();
					Shop.setalarm();
					JPanel022.model.setNumRows(0);
					JPanel022.set();
					dispose();
				}
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
}

class bookstock extends JDialog {
	BookList book;
	Shop s;
	User u;
	bookstock() {
		setTitle("도서 재입고");
        JPanel p = new JPanel();
        Label l1 = new Label("재입고할 도서 번호");
        Label l2 = new Label("추가할 도서 수량");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JButton btn1 = new JButton("추가");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(btn1); 
        add(btn2); 
        add(p); 
        l1.setBounds(30, 20, 100, 30);
        l2.setBounds(30, 60, 100, 30);
        t1.setBounds(150, 10, 100, 30);
        t2.setBounds(150, 50, 100, 30);
        btn1.setBounds(90, 100, 80, 30);
        btn2.setBounds(210, 100, 80, 30);
        setSize(400, 200);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        t2.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("") || t2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요.");
				}
				else if( Integer.parseInt(t1.getText()) > BookList.bookList.size()) {
		        	JOptionPane.showMessageDialog(null, "리스트에 없는 도서입니다.");
		        }
				else {
					String a = t1.getText();
					String c = t2.getText();
					int n = Integer.parseInt(a);
					int m = Integer.parseInt(c);
					
			    	for(Book b : book.bookList) {
			    		if(n == b.getBid()) {
			    			BookList.selectBook(b.getBid()).setQuantity(BookList.selectBook(b.getBid()).getQuantity()+m);
			    		}
			    	}
					for(User u : Shop.users) {
						Shop.setCurrentuser(u);
						for(Bnotice notice : Shop.getCurrentuser().noList) {
							if(notice.getProductid() == n) {
				           		Shop.setCurrentuser(Shop.selectUser(notice.getId()));
				           		uAlarm alarm = new uAlarm("'"+notice.getItemname()+"'가 재입고 되었습니다.");
				           		Shop.getCurrentuser().addalarm(alarm);
				           		break;
							}
						}
					}
					Shop.setCurrentuser(Shop.selectUser("admin"));
					Shop.setbooks();
					Shop.setalarm();
					JPanel022.model.setNumRows(0);
					JPanel022.set();
					JOptionPane.showMessageDialog(null, "추가 완료!");
					dispose();
				}
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
    }
}

class orlist extends JDialog {
	JTable bookTable;
    JScrollPane listJs;
    JPanel listPanel, panel;
    static Vector<String> orderColumn = new Vector<String>();
    static DefaultTableModel model;
    static Vector userRow;
    JLabel label;
    JButton btn1, btn2, btn3;
    JComboBox cbx;       
    private    String []list = {"미포장 주문", "배송 전 주문", "배송 중 주문", "배송 완료 주문", "전체보기"}; 
	orlist() {
		setTitle("주문 리스트");
		orderColumn.clear();
		orderColumn.addElement("주문번호");
    	orderColumn.addElement("유저id");
    	orderColumn.addElement("결제 여부");
    	orderColumn.addElement("배송 정보");
    	
    	model = new DefaultTableModel(orderColumn, 0);
    	bookTable = new JTable(model);
    	listPanel = new JPanel();
    	listPanel.setLayout(new BorderLayout());
    	listJs = new JScrollPane(bookTable);
    	
    	listPanel.add(listJs, BorderLayout.CENTER);
    	add(listPanel);
    	
    	listPanel.setBounds(10, 10, 470, 440);
    	
    	table2();
    	
    	panel = new JPanel();
    	label = new JLabel("정렬");
        cbx = new JComboBox(list);
        btn1 = new JButton("도서 포장");
        btn2 = new JButton("도서 배송");
        btn3 = new JButton("배송 완료");
        
        add(label);
        add(cbx);
        add(btn1);
        add(btn2);
        add(btn3);
        add(panel);
    	
    	
    	label.setBounds(60, 500, 50, 40);
    	cbx.setBounds(120,500,100,30);
        btn1.setBounds(300, 500, 100, 30);
        btn2.setBounds(300, 550, 100, 30);
        btn3.setBounds(300, 600, 100, 30);
    	panel.setBounds(0, 450, 500, 250);
    	
        setSize(500, 700);
        setVisible(true);
        
        cbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb =(JComboBox)e.getSource();
				String index = cb.getSelectedItem().toString();
				if(index.equals("미포장 주문")) {
					model.setNumRows(0);
					for(Order o : Shop.order) {
					    if(o.getA() == true) {
					    	if(o.getDelivery().equals("")) {
					    		userRow= new Vector();     
						    	userRow.addElement(o.getOid());         
						    	userRow.addElement(o.getUid());   
						    	userRow.addElement(o.getA());         
						    	userRow.addElement(o.getDelivery());         
						    	model.addRow(userRow); 
					    	}
					    }
					}
				}
				else if(index.equals("배송 전 주문")) {
					model.setNumRows(0);
					for(Order o : Shop.order) {
					    if(o.getA() == true) {
					    	if(o.getDelivery().equals("배송 준비 중")) {
					    		userRow= new Vector();       //내용추가 (행추가)     
						    	userRow.addElement(o.getOid());         
						    	userRow.addElement(o.getUid());   
						    	userRow.addElement(o.getA());         
						    	userRow.addElement(o.getDelivery());         
						    	model.addRow(userRow); 
					    	}
					    }
					}
				}
				else if(index.equals("배송 중 주문")) {
					model.setNumRows(0);
					for(Order o : Shop.order) {
					    if(o.getA() == true) {
					    	if(o.getDelivery().equals("배송 중")) {
					    		userRow= new Vector();       //내용추가 (행추가)     
						    	userRow.addElement(o.getOid());         
						    	userRow.addElement(o.getUid());   
						    	userRow.addElement(o.getA());         
						    	userRow.addElement(o.getDelivery());         
						    	model.addRow(userRow); 
					    	}
					    }
					}
				}
				else if(index.equals("배송 완료 주문")) {
					model.setNumRows(0);
					for(Order o : Shop.order) {
					    if(o.getA() == true) {
					    	if(o.getDelivery().equals("배송 완료")) {
					    		userRow= new Vector();       //내용추가 (행추가)     
						    	userRow.addElement(o.getOid());         
						    	userRow.addElement(o.getUid());   
						    	userRow.addElement(o.getA());         
						    	userRow.addElement(o.getDelivery());         
						    	model.addRow(userRow); 
					    	}
					    }
					}
				}
				else if(index.equals("전체보기")) {
					model.setNumRows(0);
					table2();
				}
			}
    	});
    	btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pack();
			}
		});
    	btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pdelivery();
			}
		});

    	btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new fdelivery();
			}
		});

    }
	public static void table2() {
		for(Order o : Shop.order) {
    		if(o.getA() == true) {
    			userRow= new Vector();       //내용추가 (행추가)     
    	        userRow.addElement(o.getOid());         
    	        userRow.addElement(o.getUid());   
    	        userRow.addElement(o.getA());         
    	        userRow.addElement(o.getDelivery());         
    	        model.addRow(userRow); 
    		}
    	}
	}
}


class pack extends JDialog {
	BookList book;
	pack() {
		setTitle("도서 포장");
        JPanel p = new JPanel();
        Label l1 = new Label("포장할 주문 번호");
        JTextField t1 = new JTextField();
        JButton btn1 = new JButton("포장");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(t1);
        add(btn1); 
        add(btn2); 
        add(p); 
        l1.setBounds(30, 20, 100, 30);
        t1.setBounds(140, 20, 100, 30);
        btn1.setBounds(40, 70, 80, 30);
        btn2.setBounds(155, 70, 80, 30);
        setSize(300, 150);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "번호를 입력해 주세요.");
				}
				else if(Integer.parseInt(t1.getText()) > Shop.order.size()) {
		        	JOptionPane.showMessageDialog(null, "없는 주문입니다.");
		        }
				else {
					String a = t1.getText();
					int n = Integer.parseInt(a);
					for(Order o : Shop.order ) {  //장바구니 찾기 
						if (Shop.getCurrentorder().getOid() == n) {	// 입력한 장바구니일 경우	
							
						}
						else {				// 입력한 장바구니가 아닌 경우
							Shop.setCurrentorder(o); //currentSBasket
						}
					}
					if(Shop.getCurrentorder().getA() == true) {
						Shop.getCurrentorder().setState(3);
						Shop.packing();
						JOptionPane.showMessageDialog(null, "포장 완료!");
						
						Shop.setorders();
						orlist.model.setNumRows(0);
						orlist.table2();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "결제가 안된 주문입니다.");
					}
				}
			}
		});
        
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
    }
}

class pdelivery extends JDialog {
	BookList book;
	pdelivery() {
		setTitle("도서 배송");
        JPanel p = new JPanel();
        Label l1 = new Label("배달원에게 전달할 주문 번호");
        JTextField t1 = new JTextField();
        JButton btn1 = new JButton("전달");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(t1);
        add(btn1); 
        add(btn2); 
        add(p); 
        l1.setBounds(30, 20, 200, 30);
        t1.setBounds(240, 20, 100, 30);
        btn1.setBounds(40, 70, 80, 30);
        btn2.setBounds(155, 70, 80, 30);
        setSize(300, 150);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "번호를 입력해 주세요.");
				}
				else if(Integer.parseInt(t1.getText()) > Shop.order.size()) {
		        	JOptionPane.showMessageDialog(null, "없는 주문입니다.");
		        }
				else {
					String a = t1.getText();
					int n = Integer.parseInt(a);
					for(Order o : Shop.order ) {  //장바구니 찾기 
						if (Shop.getCurrentorder().getOid() == n) {	// 입력한 장바구니일 경우	
							
						}
						else {				// 입력한 장바구니가 아닌 경우
							Shop.setCurrentorder(o); //currentSBasket
						}
					}
					if(Shop.getCurrentorder().getA() == true) {
						Shop.getCurrentorder().setState(4);
						Shop.packing();
						JOptionPane.showMessageDialog(null, "배달원에게 전달 완료!");
						
						Shop.setorders();
						orlist.model.setNumRows(0);
						orlist.table2();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "결제가 안된 주문입니다.");
					}
				}
			}
		});
        
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
    }
}

class fdelivery extends JDialog {
	BookList book;
	fdelivery() {
		setTitle("배송 완료");
        JPanel p = new JPanel();
        Label l1 = new Label("배송 완료된 주문 번호");
        JTextField t1 = new JTextField();
        JButton btn1 = new JButton("확인");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(t1);
        add(btn1); 
        add(btn2); 
        add(p); 
        l1.setBounds(30, 20, 150, 30);
        t1.setBounds(200, 20, 100, 30);
        btn1.setBounds(40, 70, 80, 30);
        btn2.setBounds(155, 70, 80, 30);
        setSize(300, 150);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "번호를 입력해 주세요.");
				}
				else if(Integer.parseInt(t1.getText()) > Shop.order.size()) {
		        	JOptionPane.showMessageDialog(null, "없는 주문입니다.");
		        }	
				else {
					String a = t1.getText();
					int n = Integer.parseInt(a);
					for(Order o : Shop.order ) {  //장바구니 찾기 
						if (Shop.getCurrentorder().getOid() == n) {	// 입력한 장바구니일 경우	
							
						}
						else {				// 입력한 장바구니가 아닌 경우
							Shop.setCurrentorder(o); //currentSBasket
						}
					}
					if(Shop.getCurrentorder().getA() == true) {
						Shop.getCurrentorder().setState(5);
						Shop.packing();
						JOptionPane.showMessageDialog(null, "배송 완료!");
						
						Shop.setorders();
						orlist.model.setNumRows(0);
						orlist.table2();
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "결제가 안된 주문입니다.");
					}
				}
				
			}
		});
        
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
    }
}


class cancel extends JDialog {
	BookList book;
	cancel() {
		setTitle("도서 포장");
        JPanel p = new JPanel();
        Label l1 = new Label("정말로 취소 하시겠습니까?");
        JButton btn1 = new JButton("결제 취소");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(btn1); 
        add(btn2); 
        add(p); 
        l1.setBounds(30, 20, 200, 30);
        btn1.setBounds(40, 70, 100, 30);
        btn2.setBounds(155, 70, 100, 30);
        setSize(300, 150);
        setVisible(true);
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(Shop.getCurrentorder().getUid().equals(Shop.getCurrentuser().getUid())) {
					if (Shop.getCurrentorder().getA() == false) {
						JOptionPane.showMessageDialog(null, "해당 주문 번호는 결제가 안된 주문입니다.");
		        	}
					else {
						if (Shop.getCurrentorder().getType() == 5) {
			    			Shop.getCurrentuser().setPoint(Shop.getCurrentuser().getPoint()+Shop.getCurrentorder().getAmount());
			    			Shop.getCurrentorder().cancelBuy();
			    			Shop.getCurrentorder().setType(1);
			    			Shop.getCurrentorder().setState(1);
			    			for(Pick pk : Shop.getCurrentorder().orderList) {
								BookList.selectBook(pk.getProductid()).setQuantity(BookList.selectBook(pk.getProductid()).getQuantity()+pk.getQuantity());
							}
							JOptionPane.showMessageDialog(null, "결제가 취소되었습니다.");
			    		}
			    		else if (Shop.getCurrentorder().getType() == 6) {
			    			Shop.getCurrentorder().cancelBuy();
			    			Shop.getCurrentorder().setType(1);
			    			Shop.getCurrentorder().setState(1);
			    			for(Pick pk : Shop.getCurrentorder().orderList) {
								BookList.selectBook(pk.getProductid()).setQuantity(BookList.selectBook(pk.getProductid()).getQuantity()+pk.getQuantity());
							}
							JOptionPane.showMessageDialog(null, "결제가 취소되었습니다.");
			    		}

		    			Shop.setusers();
		    			Shop.setbooks();
		    			Shop.setorders();
						JPanel022.model.setNumRows(0);
			    		JPanel022.set();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "해당 주문 번호는 다른 유저의 주문 입니다.");
				}
				dispose();
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
    }
}

class notice extends JDialog {
	BookList book;
	notice() {
        setTitle("재입고 알림받을 책 선택");
        JPanel p = new JPanel();
        Label l1 = new Label("재입고시 알림을 받을 책 번호 :");
        JTextField t1 = new JTextField();
        JButton btn1 = new JButton("설정");
        JButton btn2 = new JButton("취소");
        add(l1);
        add(t1);
        add(btn1); 
        add(btn2);
        add(p); 
        l1.setBounds(20, 50, 160, 30);
        t1.setBounds(200, 50, 50, 30);
        btn1.setBounds(70, 140, 60, 30);
        btn2.setBounds(160, 140, 60, 30);
        setSize(300,300);
        setVisible(true);
        
        t1.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
        			getToolkit().beep();
        			e.consume();
        		}
        	}
        });
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int h=0;
				try {
		    		Scanner bfile = new Scanner(Shop.bf);
		    		while(bfile.hasNext()) {
		    			String data = bfile.nextLine();
		    			String [] split = data.split(";;");
		    			if(split[0].equals(t1.getText())) {
		    				h = 2;
		    			}
		    		}
		    	} catch (Exception e1) {
		    		   e1.printStackTrace();
		    	}
				if(h==2) {
					String z, y;
					int n = 0;
					z = t1.getText();
				    n = Integer.parseInt(z);
					
					if(BookList.selectBook(n).getQuantity() > 0) {
			        	JOptionPane.showMessageDialog(null, "선택하신 도서는 현재 재고가 있는 도서입니다.");
			        	dispose();
			        }
			        else if(BookList.selectBook(n).getQuantity() == 0) {
			        	Bnotice notice = new Bnotice(Shop.getCurrentuser().getUid() ,BookList.selectBook(n).getBid(), BookList.selectBook(n).getName(), BookList.selectBook(n).getPrice(), BookList.selectBook(n).getAuthor());
			        	Shop.getCurrentuser().addNotice(notice);
			        	Shop.setnotice();
			        	Shop.setusers();
	        			dispose();
			        }
				}
				else if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "도서를 선택해 주시길 바랍니다.");
				}
				else {
		    		JOptionPane.showMessageDialog(null, "해당 번호는 등록이 안된 도서입니다.");
		    	}
				
			}
		});
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        
    }
}

class aalarm extends JDialog {
	JTable bookTable;
    JScrollPane listJs;
    JPanel listPanel, panel;
    static Vector<String> orderColumn = new Vector<String>();
    static DefaultTableModel model;
    static Vector userRow;
    JButton btn1;      
    aalarm() {
		setTitle("알림 리스트");
		orderColumn.clear();
		orderColumn.addElement("알림 내용");
    	orderColumn.addElement("알림 온 시간");
    	
    	model = new DefaultTableModel(orderColumn, 0);
    	bookTable = new JTable(model);
    	listPanel = new JPanel();
    	listPanel.setLayout(new BorderLayout());
    	listJs = new JScrollPane(bookTable);
    	
    	listPanel.add(listJs, BorderLayout.CENTER);
    	add(listPanel);
    	
    	listPanel.setBounds(10, 10, 470, 440);
    	
    	table2();
    	
    	panel = new JPanel();
        btn1 = new JButton("확인");
        
        add(btn1);
    	add(panel);
    	
        btn1.setBounds(300, 500, 100, 30);
    	panel.setBounds(0, 450, 500, 250);
    	
        setSize(500, 700);
        setVisible(true);
        
        
    	btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
	public static void table2() {
		for(uAlarm al : Shop.getCurrentuser().alarm) {
    		userRow= new Vector();       //내용추가 (행추가)     
    	    userRow.addElement(al.getA());         
    	    userRow.addElement(al.getDate());          
    	    model.addRow(userRow); 
    	}
	}
}
class bestsel extends JDialog {
	JTable bookTable;
    JScrollPane listJs;
    JPanel listPanel, panel;
    Vector<String> bookColumn = new Vector<String>();
    static DefaultTableModel model;
    static Vector userRow;
    private JButton jButton1;
    JTextField t1;  
	bestsel() {
		setTitle("베스트셀러");
		bookColumn.clear();
    	bookColumn.addElement("책 번호");
    	bookColumn.addElement("책 이름");
    	bookColumn.addElement("가격");
    	bookColumn.addElement("남은 수량");
    	bookColumn.addElement("작가");
    	
    	model = new DefaultTableModel(bookColumn, 0);
    	bookTable = new JTable(model);
    	
    	listPanel = new JPanel();
    	listPanel.setLayout(new BorderLayout());
    	listJs = new JScrollPane(bookTable);
    	listPanel.add(listJs, BorderLayout.CENTER);
    	listPanel.setBounds(10, 10, 470, 440);
    	
    	add(listPanel);
    	
    	panel = new JPanel();
    	jButton1 = new JButton("확인");
        
    	add(jButton1);
    	add(panel);
    	jButton1.setBounds(200, 500, 100, 30);
    	panel.setBounds(0, 450, 500, 250);
    	
    	
    	set();
        
        
        setVisible(true);
    	setSize(500, 700);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
    public static void set() {
    	for(Book b : BookList.bookList) {
    		if(b.getPQuantity() > 20) {
    			userRow= new Vector();       //내용추가 (행추가)     
    	        userRow.addElement(b.getBid());         
    	        userRow.addElement(b.getName());   
    	        userRow.addElement(b.getPrice());         
    	        userRow.addElement(b.getQuantity());  
    	        userRow.addElement(b.getAuthor());         
    	        model.addRow(userRow); 
    		}
    	}
    }
}


class JPanel011 extends JPanel {      //  1번 패널 
    private JButton jButton1, jButton2, jButton3, jButton4;
    static JLabel label;
    
    Shop s;
    public JPanel011(){        // 1번째 패널 생성자
        setLayout(null);
        
        jButton1 = new JButton("로그인");
        jButton2 = new JButton("회원가입");
        jButton3 = new JButton("유저 정보 수정");
        jButton4 = new JButton("알림 확인");
        label = new JLabel();
        jButton1.setSize(90,30);
        jButton2.setSize(90,30);
        jButton3.setSize(120,30);
        jButton4.setSize(90,30);
        label.setSize(440, 30);
        jButton1.setLocation(50, 210);
        jButton2.setLocation(180, 210);
        jButton3.setLocation(310, 210);
        jButton4.setLocation(180, 300);
        label.setLocation(50, 50);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(label);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login();
			}
		});
        jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new join();
				
			}
		});
        jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Shop.getCurrentuser().getUid().equals("guest")) {
					JOptionPane.showMessageDialog(null, "로그인을 해주시길 바랍니다.");
				}
				else {
					new userinfo();
				}
			}
		});
        jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Shop.getCurrentuser().getUid().equals("guest")) {
					JOptionPane.showMessageDialog(null, "로그인을 해주시길 바랍니다.");
				}
				else {
					new aalarm();
				}
			}
		});
    }
}

class JPanel022 extends JPanel{        // 2번째 패널
    JTable bookTable;
    JScrollPane listJs;
    JPanel listPanel;
    Vector<String> bookColumn = new Vector<String>();
    static DefaultTableModel model;
    static Vector userRow;
    private JButton jButton1, jButton2, jButton3, jButton4;
    JTextField t1;
    public JPanel022() {        // 2번째 패널 생성자
        
    	bookColumn.addElement("책 번호");
    	bookColumn.addElement("책 이름");
    	bookColumn.addElement("가격");
    	bookColumn.addElement("남은 수량");
    	bookColumn.addElement("작가");
    	
    	model = new DefaultTableModel(bookColumn, 0);
    	bookTable = new JTable(model);
    	
    	listPanel = new JPanel();
    	listPanel.setLayout(new BorderLayout());
    	listJs = new JScrollPane(bookTable);
    	listPanel.add(listJs, BorderLayout.CENTER);
    	listPanel.setSize(500, 400);
    	listPanel.setLocation(0, 0);
    	add(listPanel);
    	setVisible(true);
    	
    	set();
    	t1 = new JTextField(20);
    	jButton1 = new JButton("책 검색");
    	jButton2 = new JButton("선택");
    	jButton3 = new JButton("재입고 알림 설정");
    	jButton4 = new JButton("베스트셀러");
    	t1.setText("검색할 책의 제목이나 작가 입력");
    	t1.setSize(150, 40);
    	jButton1.setSize(90,40);  
    	jButton2.setSize(90,40);
    	t1.setLocation(50, 450);
    	jButton1.setLocation(200, 450);
    	jButton2.setLocation(300, 450);
    	
    	add(t1);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = t1.getText();
				model.setNumRows(0);
				for(Book b : BookList.bookList) {
					if(b.getName().contains(name) || b.getAuthor().contains(name)) {
						userRow= new Vector();       //내용추가 (행추가)  
						userRow.addElement(b.getBid());         
						userRow.addElement(b.getName());   
						userRow.addElement(b.getPrice());         
						userRow.addElement(b.getQuantity());  
						userRow.addElement(b.getAuthor());         
						model.addRow(userRow); 
					}
				}
			}
		});
        jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new bookselect();
			}
		});
        jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new notice();
			}
		});
        jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new bestsel();
			}
		});
    }
    public static void set() {
    	for(Book b : BookList.bookList) {
	    	userRow= new Vector();       //내용추가 (행추가)     
	        userRow.addElement(b.getBid());         
	        userRow.addElement(b.getName());   
	        userRow.addElement(b.getPrice());         
	        userRow.addElement(b.getQuantity());  
	        userRow.addElement(b.getAuthor());         
	        model.addRow(userRow); 
    	}
    }
}


class JPanel033 extends JPanel{        // 3번째 패널
	JTable bookTable;
    JScrollPane listJs;
    JPanel listPanel, panel;
    Vector<String> orderColumn = new Vector<String>();
    static DefaultTableModel model;
    static Vector userRow;
    static JLabel label;
	static JLabel label2;
	JLabel label3;
	static JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
    static JComboBox cbx1;
    static JButton btn1;
	JButton btn3;
    static Vector orderid;
    Shop s = new Shop();
    static String c = "";
    static String d = ""; 
    public JPanel033() {         // 3번째 패널 생성자
    	orderColumn.addElement("책 번호");
    	orderColumn.addElement("책 이름");
    	orderColumn.addElement("가격");
    	orderColumn.addElement("수량");
    	
    	model = new DefaultTableModel(orderColumn, 0);
    	bookTable = new JTable(model);

    	listPanel = new JPanel();
    	listPanel.setLayout(new BorderLayout());
    	listJs = new JScrollPane(bookTable);
    	listPanel.add(listJs, BorderLayout.CENTER);
    	add(listPanel);
    	table();
    	
    	label = new JLabel();
        add(label);
        label2 = new JLabel();
        add(label2);
        label4 = new JLabel();
        add(label4);
        label5 = new JLabel("　    　　　　　　　주문 선택    -     ");
        add(label5);
        cbx1 = new JComboBox();
        oidList();
        
        add(cbx1);
        label6 = new JLabel("                        ");
        add(label6);
        btn1 = new JButton();
        add(btn1);
        label3 = new JLabel("         ");
        add(label3);
        label7 = new JLabel("         ");
        add(label7);
        btn3 = new JButton("주문 수정");
        add(btn3);
        
        set();
    	cbx1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb =(JComboBox)e.getSource();
				String index = cb.getSelectedItem().toString();
				int n = Integer.parseInt(index);
				for(Order o : Shop.order ) {  //장바구니 찾기 
					if (Shop.getCurrentorder().getOid() == n) {	// 입력한 장바구니일 경우	
					}
					else {				// 입력한 장바구니가 아닌 경우
						Shop.setCurrentorder(o); //currentSBasket
					}
				}
				if(s.getCurrentorder().getUid().equals(s.getCurrentuser().getUid())) {
					model.setNumRows(0);
					table();
					set();
				}
			}
    	});
    	btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn1.getText() == "결제") {
					new pay();
				}
				else if(btn1.getText() == "결제 취소") {
					new cancel();
				}
			}
		});
    	btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Shop.getCurrentorder().getA() == true) {
					JOptionPane.showMessageDialog(null, "이미 구매한 주문입니다.");
				}
				else {
					new orsel();
				}
			}
		});
        setVisible(true);
    }
    public static void set() {
    	String a = "　　　　　사용자  :  " + Shop.getCurrentorder().getUid() + "　　　　　　　　　　　　　　　주문  번호:  " + Shop.getCurrentorder().getOid()+ "　　　　　　";
        label.setText(a);
    	
    	int Amount = 0;
        for( Pick pk : Shop.getCurrentorder().orderList) {
    		Amount = Amount + pk.getPrice() * pk.getQuantity();
    	}
        if(Shop.getCurrentorder().getA() == true) {
        	c = "결제 완료된 주문";
        } 
        else if(Shop.getCurrentorder().getA() == false) {
        	c = "미결제 주문　　";
        }
        String b = "　　　　　주문 총 합계 :　" + Amount+"　　　　　　　　　　　"+ "결제 여부:　" + c;
        label2.setText(b);
        if(Shop.getCurrentorder().getState() == 1) {
        	d = "　　　　　　　　　　　　　　　　　";
        }
        else if(Shop.getCurrentorder().getState() == 2){
        	d = "　　　　　　　　　　　　　　　　　";
        }
        else if(Shop.getCurrentorder().getState() == 3){
        	d = "　　배송 정보 :   배송 준비 중　";
        }
        else if(Shop.getCurrentorder().getState() == 4){
        	d = "　　배송 정보 :　　배송　중　　　";
        }
        else if(Shop.getCurrentorder().getState() == 5){
        	d = "　　배송 정보 :　　배송　완료　　";
        }
        label4.setText(d);
    	if(Shop.getCurrentorder().getA() == true) {
        	btn1.setText("결제 취소");
        }
        else if(Shop.getCurrentorder().getA() == false) {
        	btn1.setText("결제");
        }
    }
    public static void table() {
    	for(Pick pk : Shop.getCurrentorder().orderList) {
	    	userRow= new Vector();       //내용추가 (행추가)     
	        userRow.addElement(pk.getProductid());         
	        userRow.addElement(pk.getItemname());   
	        userRow.addElement(pk.getPrice());         
	        userRow.addElement(pk.getQuantity());         
	        model.addRow(userRow); 
    	}
    }
    public static void oidList() {
    	cbx1.removeItem(orderid);
    	orderid = new Vector();
    	orderid.clear();
    	Vector<Order> currentorder = Shop.selectOrder(Shop.getCurrentuser().getUid());
        
        for (Order o : currentorder) {
        	orderid.addElement(o.getOid());
        	cbx1.addItem(orderid);
    	}
        cbx1.setModel(new DefaultComboBoxModel(orderid));
    }
}
class JPanel044 extends JPanel{        // 4번째 패널
	private JButton jButton1, jButton2, jButton3;
	private JLabel label;
    public JPanel044() {         // 4번째 패널 생성자
    	setLayout(null);
        jButton1 = new JButton("도서 추가");
        jButton2 = new JButton("도서 재입고");
        jButton3 = new JButton("주문 정보 확인");
        jButton1.setSize(120,30); 
        jButton2.setSize(120,30);  
        jButton3.setSize(120,30);  
        jButton1.setLocation(190, 100);
        jButton2.setLocation(190, 200);
        jButton3.setLocation(190, 300);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addbook();
			}
		});
        jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new bookstock();
				
			}
		});
        jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new orlist();
				
			}
		});
    }
}
