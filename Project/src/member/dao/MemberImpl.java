package member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
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
		String sql = "insert into PN_member values(?,?,?,?,?)";
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

	@Override
	public boolean checkMember(String email, String pwd) {
		String sql ="select pwd from PN_member where email = ?";
		try {
			String result = jdbcTemplate.queryForObject(sql,new Object[] {email},String.class);
			if(result.equals(pwd)) {
				return true;
			}else {
				return false;
			}
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
		
	}

	@Override
	public boolean checkEmail(String email) {
		String sql = "select * from PN_member where email = ?";
		try {
			List<MemberDTO> result = jdbcTemplate.query(sql, mapper);
			if(result!=null) return true;
			else return false;
		}catch(EmptyResultDataAccessException e) {
			return true;
		}
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		String sql = "select email from PN_member where email = ?";
		try {
			String result = (String) this.jdbcTemplate.queryForObject(
			       sql,    new Object[]{email}, String.class);
			return true;

		}catch(EmptyResultDataAccessException e) {
			return false;
		}	
	}

	@Override
	public String getNickname(String email) {
		String sql ="select nickname from PN_member where email = ?";
		try {
			String result = (String) this.jdbcTemplate.queryForObject(
			       sql,    new Object[]{email}, String.class);
			return result;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<MemberDTO> fineMember(String search, String searchString) {
		String sql="select * from PN_member where "+search+" = ?";
		List<MemberDTO> result = jdbcTemplate.query(sql, mapper, searchString);
		return result;
	}

	@Override
	public boolean checkNickname(String nickname) {
		String sql = "select * from PN_member where nickname = ?";
		try {
			List<MemberDTO> result = jdbcTemplate.query(sql, mapper);
			if(result!=null) return true;
			else return false;
		}catch(EmptyResultDataAccessException e) {
			return true;
		}
	}

	@Override
	public boolean duplicateNicknameCheck(String nickname) {
		String sql = "select nickname from PN_member where nickname = ?";
		try {
			String result = (String) this.jdbcTemplate.queryForObject(
			       sql,    new Object[]{nickname}, String.class);
			return true;

		}catch(EmptyResultDataAccessException e) {
			return false;
		}	
	}
}