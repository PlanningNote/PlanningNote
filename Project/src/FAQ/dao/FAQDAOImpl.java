package FAQ.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import FAQ.dto.FAQDTO;

public class FAQDAOImpl implements FAQDAO {  
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	class MyRowMapper implements RowMapper<FAQDTO> {
		@Override
		public FAQDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			FAQDTO dto = new FAQDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setSubject(arg0.getString("subject"));
			dto.setContent(arg0.getString("content"));
			dto.setCount(arg0.getInt("count"));
			dto.setDay(arg0.getString("day"));
			dto.setImg(arg0.getString("img"));
			dto.setPwd(arg0.getString("pwd"));

			return dto;
		}
	}

	MyRowMapper mapper = new MyRowMapper();

	@Override
	public int insertFAQ(FAQDTO dto) {
		String sql="insert into PN_FAQ values(no_seq.nextval, ?,?,0,sysdate,?,?)";
		Object[] values = new Object[] {dto.getSubject(),dto.getContent(),dto.getImg(),dto.getPwd()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}


	@Override
	public FAQDTO findFAQ(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FAQDTO> rankFAQ() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FAQDTO> listFAQ() {
		String sql = "select * from PN_FAQ order by no desc ";
		List<FAQDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

    
protected void plusReadCount(int no) {
	String sql = "update PN_FAQ set count = count+1 where no = ?";
	jdbcTemplate.update(sql, no);
}

	@Override 
	public FAQDTO getFAQBoard(int no, String mode) {
		
		String sql = "select * from PN_FAQ where no = ?";
		FAQDTO dto = jdbcTemplate.query
				(sql, new MyPreparedStatementSetterForPrimaryKey(no), new MyResultSetExtractor());
		return dto;
	}
	
	class MyResultSetExtractor implements ResultSetExtractor<FAQDTO>{
		@Override
		public FAQDTO extractData(ResultSet arg0) throws SQLException, DataAccessException {
			if(arg0.next()) {
				FAQDTO dto = new FAQDTO();
				
				dto.setNo(arg0.getInt("no"));
				dto.setSubject(arg0.getString("subject"));
				dto.setContent(arg0.getString("content"));
				dto.setCount(arg0.getInt("count"));
				dto.setDay(arg0.getString("day"));
				dto.setImg(arg0.getString("img"));
			/*	dto.setPwd(arg0.getString("pwd"));*/
				return dto;
			}
			throw new DataRetrievalFailureException("해당 객체를 찾을수가 없습니다.");
		}		
	}
	
	//물음표를 넣어주기위한 클래스
	class MyPreparedStatementSetterForPrimaryKey implements PreparedStatementSetter{
		private Integer no;
		public MyPreparedStatementSetterForPrimaryKey(Integer no) {
			this.no = no;
		}
		@Override
		public void setValues(PreparedStatement arg0) throws SQLException {
			arg0.setInt(1, no);
		}
		
	}
	
	protected boolean isPassword(int no, String pwd) {
		String sql ="select pwd from PN_FAQ where no = ?";
		String pw  = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
		if(pw.equals(pwd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int updateFAQ(FAQDTO dto) {
		boolean isPass = isPassword(dto.getNo(), dto.getPwd());
		if(isPass) {
			String sql ="update PN_FAQ set subject=?,content=? ,img=? pwd=?  where no = ?";
			Object[] values = new Object[] {dto.getSubject(),dto.getContent(),dto.getImg(),dto.getNo()};
			int res = jdbcTemplate.update(sql, values);
			return res;
		}else {
			return -1;
		}
	}
	
	@Override
	public int deleteFAQ(int no, String pwd) {
		boolean isPass = isPassword(no, pwd);
		if(isPass) {
			String sql ="delete from PN_FAQ where no = ?";
			int res = jdbcTemplate.update(sql,no);
			return res;
		}else {
			return -1;
		}
		
	}

}
