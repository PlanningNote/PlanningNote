package report.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import plan.dto.BReportDTO;

public class BReportDAOImpl implements BReportDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertReport(BReportDTO dto) {
		String sql="insert into PN_BReport values(breport_seq.nextval, ?,?,?,?,?,sysdate)";
		Object[] values = new Object[] {dto.getReporter(),dto.getSuspecter(),dto.getBoard_no(),dto.getContent(),dto.getHandling()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

}
