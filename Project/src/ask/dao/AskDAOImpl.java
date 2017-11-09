package ask.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ask.dto.AskDTO;



public class AskDAOImpl implements AskDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class MyRowMapper implements RowMapper<AskDTO> {
		@Override
		public AskDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			AskDTO dto = new AskDTO();  
			dto.setNo(arg0.getInt("no"));
			dto.setWriter(arg0.getString("write"));
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
	public int insertAsk(AskDTO dto) {
		String sql="insert into PN_ask values(no_seq.nextval, ?,?,0,sysdate,?,?,?,?,?)";
		Object[] values = new Object[] {dto.getSubject(),dto.getContent(),dto.getImg(),dto.getPwd()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int updateAsk(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAsk(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AskDTO> listAsk() {
		String sql = "select * from PN_ask order by no desc ";
		List<AskDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}
	@Override
	public List<AskDTO> rankAsk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AskDTO findAsk(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	

}



