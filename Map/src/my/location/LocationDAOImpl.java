package my.location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class LocationDAOImpl implements LocationDAO{
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<LocationDTO>{

		@Override
		public LocationDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			LocationDTO dto = new LocationDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setSubject(arg0.getString("subject"));
			dto.setContent(arg0.getString("content"));
			dto.setLocation(arg0.getString("location"));
			dto.setLat(arg0.getDouble("lat"));
			dto.setLng(arg0.getDouble("lng"));
			return dto;
		}		
	}
	
	MyRowMapper mapper = new MyRowMapper();

	@Override
	public int insertContent(LocationDTO dto) {
		String sql ="insert into test values(test_seq.nextval, ?, ?, ?, ?, ?)";
		Object[] values = new Object[] {dto.getSubject(),dto.getContent(), dto.getLocation(), dto.getLat()
				, dto.getLng()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public List<LocationDTO> listContent() {
		String sql ="select * from test";
		List<LocationDTO> result = jdbcTemplate.query(sql, mapper);
		return result;
	}
	

}
