package notice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
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
	public int updateNotice(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(int no) {
		// TODO Auto-generated method stub
		return 0;
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

}
