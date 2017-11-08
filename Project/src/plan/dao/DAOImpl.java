package plan.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import plan.dto.PlanDTO;

public class DAOImpl implements PlanDAO {
	private JdbcTemplate jdbcTemplate;
    
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePlan(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PlanDTO> listPlan(int group_no, PlanDTO dto) {
		// TODO Auto-generated method stub
		return null;
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

}
