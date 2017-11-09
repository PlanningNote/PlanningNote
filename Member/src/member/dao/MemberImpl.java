package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import member.dto.MemberDTO;

public class MemberImpl implements MemberDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<MemberDTO>{

		@Override
		public MemberDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			MemberDTO dto = new MemberDTO();
			dto.setNickname(arg0.getString("nickname"));
			dto.setGender(arg0.getString("gender"));
			dto.setEmail(arg0.getString("email"));
			dto.setAge(arg0.getInt("age"));
			dto.setPwd(arg0.getString("pwd"));
			return dto;
		}
	}
	
	MyRowMapper mapper = new MyRowMapper();
	
	@Override
	public void insertMember(MemberDTO dto) {
		String sql = "insert into PN_member values(member_seq.nextval,?,?,?,?,?)";
		Object[] values = new Object[] {dto.getNickname(),dto.getGender(),dto.getEmail(),dto.getAge(),dto.getPwd()};
		jdbcTemplate.update(sql,values);
	}

	@Override
	public int updateMember(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberDTO> listMember() {
		String sql = "select * from PN_member";
		List<MemberDTO> result = jdbcTemplate.query(sql, mapper);
		return result;
	}

	@Override
	public String findPwd(String nickname, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
