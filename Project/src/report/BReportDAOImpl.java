package report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BReportDAOImpl implements BReportDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<BReportDTO>{

		@Override
		public BReportDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			BReportDTO dto = new BReportDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setReporter(arg0.getString("reporter"));
			dto.setSuspecter(arg0.getString("suspecter"));
			dto.setBoard_no(arg0.getInt("board_no"));
			dto.setContent(arg0.getString("content"));
			dto.setImg(arg0.getString("img"));
			dto.setHandling(arg0.getString("handling"));
			dto.setAskday(arg0.getString("askday"));
			dto.setHandleday(arg0.getString("handleday"));
			return dto;
		}
	}
	
	MyRowMapper mapper = new MyRowMapper();

	@Override
	public int insertReport(BReportDTO dto) {
		String sql="insert into PN_BReport values(breport_seq.nextval, ?,?,?,?,?,?,sysdate,?)";
		Object[] values = new Object[] {dto.getReporter(),dto.getSuspecter(),dto.getBoard_no(),dto.getContent(),dto.getImg(),dto.getHandling(),dto.getHandleday()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public List<BReportDTO> listBReport() {
		String sql ="select * from PN_BReport order by no desc";
		List<BReportDTO> list = jdbcTemplate.query(sql, mapper);
		return list;		
	}

}
