package my.db;
import java.sql.*;
import java.util.*;
public class ConnectionPoolBean {
	private String url, user, pass;
	private Hashtable<Connection, Boolean> ht;//pool장 만들기
	private int increment;//pool장에 con객체를 더 입력해줘야할때 그 크기
	
	public ConnectionPoolBean() throws ClassNotFoundException, SQLException {
		increment = 3;//pool장에 con객체가 더 필요하면 3개 증가 시키기 위해
		ht = new Hashtable<Connection, Boolean>(5);//pool장에 5개의 객체를 넣기 위해
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "java07";
		pass = "java07";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		for(int i=0; i<5; ++i){
			Connection con = DriverManager.getConnection(url, user, pass);
			ht.put(con, Boolean.FALSE);//노는 con객체를 생성
		}
	}
	
	public synchronized Connection getConnection() throws SQLException {
		Enumeration<Connection> enkey = ht.keys();
		Connection con = null;
		while(enkey.hasMoreElements()){
			con = enkey.nextElement();
			Boolean b = ht.get(con);
			if (b == Boolean.FALSE){
				ht.put(con, Boolean.TRUE);
				return con;
			}
		}
		for(int i=0; i<increment; ++i){
			Connection con2 = DriverManager.getConnection(url, user, pass);
			ht.put(con2, Boolean.FALSE);
		}
		return getConnection();
	}
	
	public void returnConnection(Connection returnCon) throws SQLException{
		Connection con = null;
		Enumeration<Connection> enkey = ht.keys();
		while(enkey.hasMoreElements()){
			con = enkey.nextElement();
			if (returnCon == con) {
				ht.put(con, Boolean.FALSE);
				break;
			}
		}
		removeCon();
	}
	
	public void removeCon() throws SQLException {
		int count = 0;//노는 객체 숫자 카운트
		Connection con = null;
		Enumeration<Connection> enkey = ht.keys();
		while(enkey.hasMoreElements()){
			con = enkey.nextElement();
			Boolean b = ht.get(con);
			if(b == Boolean.FALSE){
				count++;
				if(count>5){
					ht.remove(con);
					con.close();
				}
			}
		}
	}
	
	public void closeAll() throws SQLException {
		Connection con = null;
		Enumeration<Connection> enkey = ht.keys();
		while(enkey.hasMoreElements()){
			con = enkey.nextElement();
			con.close();
		}
	}
}









