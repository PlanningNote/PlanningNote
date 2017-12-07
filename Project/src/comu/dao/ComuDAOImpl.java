package comu.dao;

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
import ask.dto.AskDTO;
import comu.dto.ComuDTO;

public class ComuDAOImpl implements ComuDAO {  
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	class MyRowMapper implements RowMapper<ComuDTO> {
		@Override 
		public ComuDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			ComuDTO dto = new ComuDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setWriter(arg0.getString("writer"));
			dto.setSubject(arg0.getString("subject"));
			dto.setContent(arg0.getString("content"));
			dto.setCount(arg0.getInt("count"));
			dto.setDay(arg0.getString("day"));
			dto.setImg(arg0.getString("img"));
			dto.setPwd(arg0.getString("pwd")); 
			dto.setRe_step(arg0.getInt("re_step"));
			dto.setRe_group(arg0.getInt("re_group"));			
			dto.setRe_level(arg0.getInt("re_level"));

			return dto;
		}
	}

	MyRowMapper mapper = new MyRowMapper();

	@Override
	public int insertComu(ComuDTO dto) {
		String sql = null;
		if(dto.getNo() ==0 ) {
			sql ="select max(no) from PN_community";
			int max = jdbcTemplate.queryForInt(sql);
			dto.setRe_group(max+1);
		}else {
			sql = "update PN_community set re_step = re_step + 1 where re_group=? and re_step>?";
			jdbcTemplate.update(sql, dto.getRe_group(), dto.getRe_step());
			dto.setRe_step(dto.getRe_group() +1);
			dto.setRe_level(dto.getRe_level()+1);
		}
		sql="insert into PN_community values(no_seq.nextval, ?,?,?,0,sysdate,?,?,?,?,?)";
		Object[] values = new Object[] {dto.getWriter(),dto.getSubject(),dto.getContent(),dto.getImg(),dto.getPwd(),dto.getRe_step(),dto.getRe_group(),dto.getRe_level()};
		int res = jdbcTemplate.update(sql, values);		
		return res;
	}


	

	@Override 
	public List<ComuDTO> listComu() {
		String sql = "select * from PN_community order by re_group desc, re_step asc";
		List<ComuDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

    
protected void plusReadCount(int no) {
	String sql = "update PN_community set count = count+1 where no = ?";
	jdbcTemplate.update(sql, no);
}

	@Override 
	public ComuDTO getComuBoard(int no, String mode) {
		if(mode.equals("content")) {
			plusReadCount(no);
		}		
		
		String sql = "select * from PN_community where no = ?";
		ComuDTO dto = jdbcTemplate.query
				(sql, new MyPreparedStatementSetterForPrimaryKey(no), new MyResultSetExtractor());
		return dto;
	}
	
	class MyResultSetExtractor implements ResultSetExtractor<ComuDTO>{
		@Override
		public ComuDTO extractData(ResultSet arg0) throws SQLException, DataAccessException {
			if(arg0.next()) {
				ComuDTO dto = new ComuDTO();
				
				dto.setNo(arg0.getInt("no"));
				dto.setSubject(arg0.getString("subject"));
				dto.setContent(arg0.getString("content"));
				dto.setCount(arg0.getInt("count"));
				dto.setDay(arg0.getString("day"));
				dto.setImg(arg0.getString("img"));
				dto.setPwd(arg0.getString("pwd"));
				dto.setRe_step(arg0.getInt("re_step"));
				dto.setRe_group(arg0.getInt("re_group"));			
				dto.setRe_level(arg0.getInt("re_level"));
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
		String sql ="select pwd from PN_community where no = ?";
		String pw  = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
		if(pw.equals(pwd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int updateComu(ComuDTO dto) {
		boolean isPass = isPassword(dto.getNo(), dto.getPwd());
		if(isPass) {
			String sql ="update PN_community set subject=?,content=? ,img=? pwd=?  where no = ?";
			Object[] values = new Object[] {dto.getSubject(),dto.getContent(),dto.getImg(),dto.getNo()};
			int res = jdbcTemplate.update(sql, values);
			return res;
		}else {
			return -1;
		}
	}
	
	@Override
	public int deleteComu(int no, String pwd) {
		boolean isPass = isPassword(no, pwd);
		if(isPass) {
			String sql ="delete from PN_community where no = ?";
			int res = jdbcTemplate.update(sql,no);
			return res;
		}else {
			return -1;
		}
		
	}







	@Override
	public ComuDTO findComu(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<ComuDTO> rankComu() {
		// TODO Auto-generated method stub
		return null;
	}





}
