package ask.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ask.dto.AskDTO;

import ask.dao.AskDAOImpl.MyRowMapper;
import ask.dto.AskDTO;

public class AskDAOImpl implements AskDAO{
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	class MyRowMapper implements RowMapper<AskDTO> {
		@Override
		public AskDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			AskDTO dto = new AskDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setWriter(arg0.getString("writer"));
			dto.setSubject(arg0.getString("subject"));
			dto.setContent(arg0.getString("content"));
			dto.setCount(arg0.getInt("count"));
			dto.setDay(arg0.getString("day"));
			dto.setImg(arg0.getString("img"));
			dto.setPwd(arg0.getString("pwd"));
			dto.setRe_group(arg0.getInt("re_group"));
			dto.setRe_step(arg0.getInt("re_step"));
			dto.setRe_level(arg0.getInt("re_level"));

			return dto;
		}
	}

	MyRowMapper mapper = new MyRowMapper();


	protected void plusReadCount(int no) {
		String sql = "update PN_ask set count = count+1 where no = ?";
		jdbcTemplate.update(sql, no);
	}

	
	
	@Override
	public AskDTO getAskBoard(int no, String mode) {
		if(mode.equals("content")) {
			plusReadCount(no);
		}		
		String sql = "select * from PN_ask where no = ?";
		AskDTO dto = jdbcTemplate.query(sql, new MyPreparedStatementSetterForPrimaryKey(no), new MyResultSetExtractor());
		return dto;
		//List<AskDTO> list =jdbcTemplate.query(sql, mapper, no);				
		//return list.get(0);
	}
	
	class MyResultSetExtractor implements ResultSetExtractor<AskDTO>{
		@Override
		public AskDTO extractData(ResultSet arg0) throws SQLException, DataAccessException {
			if(arg0.next()) {
				AskDTO dto = new AskDTO();
				dto.setNo(arg0.getInt("no"));
				dto.setWriter(arg0.getString("writer"));
				dto.setSubject(arg0.getString("subject"));
				dto.setContent(arg0.getString("content"));
				dto.setCount(arg0.getInt("count"));
				dto.setDay(arg0.getString("day"));
				dto.setImg(arg0.getString("img"));
				dto.setPwd(arg0.getString("pwd"));
				dto.setRe_group(arg0.getInt("re_group"));
				dto.setRe_step(arg0.getInt("re_step"));
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

	@Override
	public void insertAsk(AskDTO dto) {
		String sql = null;
		if(dto.getNo() ==0 ) {
			sql ="select max(no) from PN_ask";
			int max = jdbcTemplate.queryForInt(sql);
			dto.setRe_group(max+1);
		}else {
			sql = "update PN_ask set re_step = re_step + 1 where re_group=? and re_step>?";
			jdbcTemplate.update(sql, dto.getRe_group(), dto.getRe_step());
			dto.setRe_step(dto.getRe_group() +1);
			dto.setRe_level(dto.getRe_level()+1);
		}
		sql = "insert into PN_ask values(ask_seq.nextval,?,?,?,0,sysdate,?,?,?,?,?)";
		Object[] values = new Object[] {dto.getWriter(),dto.getSubject(),dto.getContent(),dto.getImg(),dto.getPwd(),dto.getRe_step(),dto.getRe_group(),dto.getRe_level()};
		jdbcTemplate.update(sql, values);
	}


	
	@Override
	public int updateAsk(AskDTO dto) {
		boolean isPass = isPassword(dto.getNo(), dto.getPwd());
		if(isPass) {
			String sql ="update PN_ask set writer=?, subject=?,content=?,img=? where no = ?";
			Object[] values = new Object[] {dto.getWriter(),dto.getSubject(),dto.getContent(), dto.getImg(),dto.getNo()};
			int res = jdbcTemplate.update(sql, values);
			return res;
		}else {
			return -1;
		}
	}



	@Override
	public int deleteAsk(int no, String pwd) {
		boolean isPass = isPassword(no, pwd);
		if(isPass) {
			String sql ="delete from PN_ask where no = ?";
			int res = jdbcTemplate.update(sql,no);
			return res;
		}else {
			return -1;
		}
		
	}
	
	protected boolean isPassword(int no, String pwd) {
		String sql ="select pwd from PN_ask where no = ?";
		String pw  = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
		if(pw.equals(pwd)) {
			return true;
		}
		return false;
	}


	@Override
	public List<AskDTO> listAsk() {
		String sql = "select * from PN_ask order by re_group desc, re_step asc";
		List<AskDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public AskDTO findAsk(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AskDTO> rankAsk() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount() throws SQLException{
		String sql = "select count(*) from ask_list";
		
		int res = jdbcTemplate.update(sql);
		return res ;
	}



	@Override
	public boolean checkpwd(String pwd) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
