package plan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

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
	public int insertsubPlan(SubPlanDTO sdto) {
		String sql = "insert into PN_subplan values(group_no.nextval,board_no_sequence.nextval, ?,?,?,?,?)";
		
		Object[] values;
		int res = 0;
		System.out.println("PlanDAOImpl: "+sdto.targets.get(0).getSubject());
		for(int i=0;i <1;i++) {
			System.out.println("now is 'for':"+sdto.targets.get(i).getSubject());
			sdto.setSubject(sdto.targets.get(i).getSubject());
			sdto.setPrice(sdto.targets.get(i).getPrice());
			sdto.setContent(sdto.targets.get(i).getContent());
			sdto.setImg(sdto.targets.get(i).getImg());
			sdto.setTraffic(sdto.targets.get(i).getTraffic());
		
			values = new Object[] {
					sdto.getSubject(),sdto.getPrice(),sdto.getContent(),sdto.getImg(),sdto.getTraffic()
			};
			res=jdbcTemplate.update(sql, values);
		}
		
		 if(res!=1) {
			 //res에 update횟수가 list의 사이드와 다르다면 sql업데이트가 제대로 되지 않음을 확인할수 있다.
			 res=-1;
			 return res;
		 }
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
	public List<PlanDTO> tagPlan(TagDTO dto) {
		String sql ="select ";
		dto.setTag1("tag1");
		dto.setTag2("tag2");
		dto.setTag3("tag3");
		dto.setTag4("tag4");
		dto.setTag5("tag5");
		dto.setTag_no(Integer.parseInt(dto.getTag1())+Integer.parseInt(dto.getTag2())
		+Integer.parseInt(dto.getTag3())+Integer.parseInt(dto.getTag4())+Integer.parseInt(dto.getTag5()));
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
