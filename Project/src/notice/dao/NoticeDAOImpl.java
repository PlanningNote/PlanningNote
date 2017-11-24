package notice.dao;

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

import notice.dto.NoticeDTO;

public class NoticeDAOImpl implements NoticeDAO {  
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	class MyRowMapper implements RowMapper<NoticeDTO> {
		@Override
		public NoticeDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			NoticeDTO dto = new NoticeDTO();
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
	public int insertNotice(NoticeDTO dto) {
		String sql="insert into PN_notice values(no_seq.nextval, ?,?,0,sysdate,?,?)";
		Object[] values = new Object[] {dto.getSubject(),dto.getContent(),dto.getImg(),dto.getPwd()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}


	@Override
	public NoticeDTO findNotice(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeDTO> rankNotice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeDTO> listNotice() {
		String sql = "select * from PN_notice order by no desc ";
		List<NoticeDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

    
protected void plusReadCount(int no) {
	String sql = "update PN_notice set count = count+1 where no = ?";
	jdbcTemplate.update(sql, no);
}

	@Override 
	public NoticeDTO getNoticeBoard(int no, String mode) {
		if(mode.equals("content")) {
			plusReadCount(no);
		}	
		String sql = "select * from PN_notice where no = ?";
		NoticeDTO dto = jdbcTemplate.query
				(sql, new MyPreparedStatementSetterForPrimaryKey(no), new MyResultSetExtractor());
		return dto;
	}
	
	class MyResultSetExtractor implements ResultSetExtractor<NoticeDTO>{
		@Override
		public NoticeDTO extractData(ResultSet arg0) throws SQLException, DataAccessException {
			if(arg0.next()) {
				NoticeDTO dto = new NoticeDTO();
				
				dto.setNo(arg0.getInt("no"));
				dto.setSubject(arg0.getString("subject"));
				dto.setContent(arg0.getString("content"));
				dto.setCount(arg0.getInt("count"));
				dto.setDay(arg0.getString("day"));
				dto.setImg(arg0.getString("img"));
				dto.setPwd(arg0.getString("pwd"));
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
		String sql ="select pwd from PN_notice where no = ?";
		String pw  = jdbcTemplate.queryForObject(sql, new Object[] {no}, String.class);
		if(pw.equals(pwd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int updateNotice(NoticeDTO dto) {
		boolean isPass = isPassword(dto.getNo(), dto.getPwd());
		if(isPass) {
			String sql ="update PN_notice set subject=?,content=? ,img=?  where no = ?";
			Object[] values = new Object[] {dto.getSubject(),dto.getContent(),dto.getImg(),dto.getNo()};
			int res = jdbcTemplate.update(sql, values);
			return res;
		}else {
			return -1;
		}
	}
	
	@Override
	public int deleteNotice(int no, String pwd) {
		boolean isPass = isPassword(no, pwd);
		if(isPass) {
			String sql ="delete from PN_notice where no = ?";
			int res = jdbcTemplate.update(sql,no);
			return res;
		}else {
			return -1;
		}
		
	}

}
