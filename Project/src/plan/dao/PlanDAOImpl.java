package plan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import plan.dto.PlanDTO;

public class PlanDAOImpl implements PlanDAO {
	private JdbcTemplate jdbcTemplate;
    private MyRowMapper mapper = new MyRowMapper();
    
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertPlan(PlanDTO dto) {
		String sql = "insert into PN_planning values(group_no.nextval, " + "?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] values = new Object[] { dto.getSubject(), dto.getDay(), dto.getWriter(), dto.getCount(), dto.getPwd(),
				dto.getTag_no(), dto.getCountry(), dto.getCity(), dto.getThumbnail(), dto.getTotalprice(),
				dto.getTravel_period(), dto.getTravel_seasion(), dto.getTravel_theme(), dto.getRecom() };

		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int updatePlan(int no, PlanDTO dto) {
		String sql = "update PN_planning"
				+ "set subject=?, day=?, writer=?, count=?, pwd=?, tag_no=?,county=?,city=?,thumbnail=?,"
				+ "totalprice=?,travel_period=?,travel_seasion=?,travel_theme=?,recom=?" + " where group_no=?)";

		Object[] values = new Object[] { dto.getSubject(), dto.getDay(), dto.getWriter(), dto.getCount(), dto.getPwd(),
				dto.getTag_no(), dto.getCountry(), dto.getCity(), dto.getThumbnail(), dto.getTotalprice(),
				dto.getTravel_period(), dto.getTravel_seasion(), dto.getTravel_theme(), dto.getRecom(),
				dto.getGroup_no() };

		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int deletePlan(int group_no) {
		String sql = "delete from PN_planning where group_no = ?";
		int res = jdbcTemplate.update(sql, group_no);
		return res;
	}

	@Override
	public List<PlanDTO> listPlan(int group_no, PlanDTO dto) {
		String sql = "select * from PN_planning where group_no = ?";
		List<PlanDTO> result = jdbcTemplate.query(sql, mapper);
		return result;
	}

	private class MyRowMapper implements RowMapper<PlanDTO>{
		@Override
		public PlanDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanDTO dto = new PlanDTO();
			dto.setSubject(rs.getString("subject"));
			return dto;
		}
	}
	
	@Override
	public PlanDTO findPlan(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanDTO> rankPlan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanDTO> tagPlan(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanDTO> findOption(String search, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanDTO> subList(int group_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanDTO getContent(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanDTO> listPlan() {
		// TODO Auto-generated method stub
		return null;
	}
}
