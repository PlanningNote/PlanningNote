package my.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.support.SQLErrorCodes;

import my.db.ConnectionPoolBean;

public class LocationDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private ConnectionPoolBean pool;
	
	public void setPool(ConnectionPoolBean pool) {
		this.pool = pool;
	}
	
	public int insertContent(LocationDTO dto) throws SQLException{
		String sql ="insert into test values(test_seq.nextval, ?, ?, ?)";
		try {
			con = pool.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getSubject());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getLocation());
			int res = ps.executeUpdate();
			return res;
		}finally {
			if(con != null) con.close();
			if(ps != null) ps.close();
		}
	}
	
	public ArrayList<LocationDTO> listContent() throws SQLException{
		String sql ="select * from test";
		try {
			con = pool.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ArrayList<LocationDTO> list = new ArrayList<LocationDTO>();
			while(rs.next()) {
				LocationDTO dto = new LocationDTO();
				dto.setNo(rs.getInt("no"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setLocation(rs.getString("location"));
				list.add(dto);
			}
			return list;
		}finally {
			if(con!= null) con.close();
			if(rs != null) rs.close();
			if(ps != null) ps.close();
		}
	}

}
