package plan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public class PlanDAOImpl implements PlanDAO {
	private JdbcTemplate jdbcTemplate;
	private MyRowMapper mapper = new MyRowMapper();
	private SubRowMapper submapper = new SubRowMapper();

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int tagPlan(TagDTO dto) {
		String sql = "insert into PN_tag (tag_no_sequence,tag) values(tag_no_sequence.nextval, " + "?)";
		String tag = dto.getTag1() + dto.getTag2() + dto.getTag3() + dto.getTag4() + dto.getTag5();
		// String[] arr = tag.split("#");
		Object[] values = new Object[] { tag };
		int result = jdbcTemplate.update(sql, values);
		return result;
	}

	@Override
	public int insertPlan(PlanDTO dto) {
		// String sql = "insert into PN_planning " + "values(group_no.nextval, " +
		// "?,?,sysdate,?,?,"
		// + "tag_no_sequence.currval,?,?,?,?,?,?,?,?)";
		String sql = "insert into PN_planning " + "values(group_no.nextval, " + "?,?,sysdate,?,?,"
				+ "tag_no_sequence.nextval,?,?,?,?,?,?,?,?)";
		int res = 0;
		Object[] values = new Object[] { dto.getWriter(), dto.getSubject(), "pwd", dto.getCount(), dto.getCountry(),
				dto.getCity(), dto.getThumbnail(), dto.getTotalprice(), dto.getTravel_period(), dto.getTravel_seasion(),
				dto.getTravel_theme(), dto.getRecom() };
		res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int insertsubPlan(SubPlanDTO sdto) {
		// String sql = "insert into PN_subplan
		// values(group_no.currval,board_num.nextval, ?,?,?,?,?)";
		String sql = "insert into PN_subplan values(group_no.nextval,board_num.nextval, ?,?,?,?,?)";
		Object[] values;
		int res = 0;
		for (int i = 0; i < sdto.getImgName().size(); i++) {
			sdto.getTargets().get(i).setImg(sdto.getImgName().get(i));
			sdto.getTargets().get(i).setPath(sdto.getImgPath().get(i));
			values = new Object[] { sdto.getTargets().get(i).getSubject(), // not null
					sdto.getTargets().get(i).getImg(), sdto.getTargets().get(i).getContent(),
					sdto.getTargets().get(i).getPrice(), sdto.getTargets().get(i).getTraffic() };
			res = jdbcTemplate.update(sql, values);
		}
		if (res < 0) {
			// res에 update횟수가 list의 사이드와 다르다면 sql업데이트가 제대로 되지 않음을 확인할수 있다.
			res = -1;
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
				dto.getTag_no_sequence(), dto.getCountry(), dto.getCity(), dto.getThumbnail(), dto.getTotalprice(),
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
	public List<PlanDTO> listAPlan() {
		String sql = "select * from PN_planning order by group_no asc";
		List<PlanDTO> result = jdbcTemplate.query(sql, mapper);
		return result;
	}
	@Override
	public PlanDTO listPlan(int group_no) {
		String sql = "select * from PN_planning where group_no = ?";
		PlanDTO result = jdbcTemplate.query(sql, new MyPreparedStatementSetterForPrimaryKey(group_no),
				new MyResultSetExtractor());
		return result;
	}
	@Override
	public List<SubPlanDTO> subList(int group_no) {
		String sql = "select * from PN_subplan where board_num=?";
		List<SubPlanDTO> result = jdbcTemplate.query(sql, new MyPreparedStatementSetterForPrimaryKey(group_no),
				new SubRowMapper());
		return result;
	}

	// PlanDTO 의 RowMapper
	private class MyRowMapper implements RowMapper<PlanDTO> {
		@Override
		public PlanDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanDTO dto = new PlanDTO();
			dto.setGroup_no(rs.getInt("group_no"));
			dto.setWriter(rs.getString("writer"));
			dto.setSubject(rs.getString("subject"));
			dto.setDay(rs.getString("day"));
			dto.setPwd(rs.getString("pwd"));
			dto.setCount(rs.getInt("count"));
			dto.setTag_no_sequence(rs.getInt("tag_no_sequence"));
			dto.setCountry(rs.getString("country"));
			dto.setCity(rs.getString("city"));
			dto.setThumbnail(rs.getString("thumbnail"));
			dto.setTotalprice(rs.getInt("totalprice"));
			dto.setTravel_period(rs.getString("travel_period"));
			dto.setTravel_seasion(rs.getString("travel_seasion"));
			dto.setTravel_theme(rs.getString("travel_theme"));
			return dto;
		}
	}

	// SubPlanDTO 의 RowMapper
	private class SubRowMapper implements RowMapper<SubPlanDTO> {
		@Override
		public SubPlanDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			SubPlanDTO dto = new SubPlanDTO();
			dto.setGroup_no(rs.getInt("group_no"));
			dto.setBoard_num(rs.getInt("board_num"));
			dto.setSubject(rs.getString("subject"));
			dto.setImg(rs.getString("img"));
			dto.setContent(rs.getString("content"));
			dto.setPrice(rs.getInt("price"));
			dto.setTraffic(rs.getString("traffic"));
			return dto;
		}
	}

	// 골라서 하나 가져올때
	class MyResultSetExtractor implements ResultSetExtractor<PlanDTO> {
		@Override
		public PlanDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (rs.next()) {
				PlanDTO dto = new PlanDTO();
				dto.setGroup_no(rs.getInt("group_no"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setDay(rs.getString("day"));
				dto.setPwd(rs.getString("pwd"));
				dto.setCount(rs.getInt("count"));
				dto.setTag_no_sequence(rs.getInt("tag_no_sequence"));
				dto.setCountry(rs.getString("country"));
				dto.setCity(rs.getString("city"));
				dto.setThumbnail(rs.getString("thumbnail"));
				dto.setTotalprice(rs.getInt("totalprice"));
				dto.setTravel_period(rs.getString("travel_period"));
				dto.setTravel_seasion(rs.getString("travel_seasion"));
				dto.setTravel_theme(rs.getString("travel_theme"));
				return dto;
			}
			throw new DataRetrievalFailureException("해당 객체를 찾을수가 없습니다.");
		}
	}

	// 골라서 하나 가져올때 , 위에거랑 같이 씀.
	class MyPreparedStatementSetterForPrimaryKey implements PreparedStatementSetter {
		private Integer num;

		public MyPreparedStatementSetterForPrimaryKey(Integer num) {
			this.num = num;
		}

		@Override
		public void setValues(PreparedStatement arg0) throws SQLException {
			// TODO Auto-generated method stub
			arg0.setInt(1, num);
		}
	}

	@Override
	public PlanDTO findPlan(String search, String searchString) {
		String sql = "";
		return null;
	}

	@Override
	public List<PlanDTO> rankPlan() {
		String sql = "";
		return null;
	}

	@Override
	public List<PlanDTO> findOption(String search, String searchString) {
		String sql = "";
		return null;
	}

	@Override
	public PlanDTO getContent(int no) {
		// TODO Auto-generated method stub
		return null;
	}

}
