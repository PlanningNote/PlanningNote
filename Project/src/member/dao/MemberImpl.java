package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

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
			dto.setNo(arg0.getInt("no"));
			dto.setNickname(arg0.getString("nickname"));
			dto.setGender(arg0.getString("gender"));
			dto.setEmail(arg0.getString("email"));
			dto.setAge(arg0.getInt("age"));
			dto.setPwd(arg0.getString("pwd"));
			dto.setImg(arg0.getString("img"));
			return dto;
		}
	}
	
	MyRowMapper mapper = new MyRowMapper();
	
	@Override
	public void insertMember(MemberDTO dto) {
		String sql = "insert into PN_member values(member_seq.nextval,?,?,?,?,?,?)";
		Object[] values = new Object[] {dto.getNickname(),dto.getGender(),dto.getEmail(),dto.getAge(),dto.getPwd(), "profile_default.png"};
		jdbcTemplate.update(sql,values);
	}

	@Override
	public int updateMember(String pwd, String nickname) {
		String sql = "update PN_member set pwd = ? where nickname= ?";
		Object[] values = new Object[] {pwd, nickname};
		int res = jdbcTemplate.update(sql,values);
		return res;
	}

	@Override
	public int deleteMember(int no) {
		String sql = "delete from PN_member where no = ?";
		int result = jdbcTemplate.update(sql, no);
		return result;
	}

	@Override
	public List<MemberDTO> listMember() {
		String sql = "select * from PN_member";
		List<MemberDTO> result = jdbcTemplate.query(sql, mapper);
		return result;
	}

	@Override
	public String findPwd(String email) {
		String sql ="select pwd from PN_member where email = ?";
		try {
			String result = jdbcTemplate.queryForObject(sql,new Object[] {email},String.class);
			return result;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
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
		String sql="select * from PN_member where "+search+" like ?";
		List<MemberDTO> result = jdbcTemplate.query(sql, mapper, "%"+searchString+"%");
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
		//관리자모드에서 사용
		@Override
		public boolean checkPwd(String nickname, String pwd) {
			String sql = "select pwd from  PN_member where nickname = ?";
			String result = (String) this.jdbcTemplate.queryForObject(
				       sql,    new Object[]{nickname}, String.class);
			if(result.equals(pwd)) {
				return true;
			}else {
				return false;
			}
	}

		@Override
		public void sendEmail(String email, String content, String subject) {
			String host = "smtp.gmail.com";			
			String fromName = "녀행자들 관리자";
			String from = "homie2032@gmail.com";
			String to1 = email;
			
			try {
				Properties props = new Properties();
				// G-Mail SMTP 사용시
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.transport.protocol", "smtp");
				props.put("mail.smtp.host", host);
				props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.port", "465");
				props.put("mail.smtp.user", from);
				props.put("mail.smtp.auth", "true");
				
				Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("homie2032@gmail.com","homie1234");
					}
				});
				
				Message msg = new MimeMessage(mailSession);
				msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B"))); // 보내는 사람 설정
				InternetAddress[] address1 = { new InternetAddress(to1) }; 
				msg.setRecipients(Message.RecipientType.TO,  address1); // 받는 사람 설정1
				msg.setSentDate(new java.util.Date()); //보내는 날짜 설정
				msg.setContent(content,"text/html;charset=euc-kr"); //내용 설정(HTML형식)
				msg.setSubject(subject);
				Transport.send(msg); //메일 보내기
			}catch(MessagingException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public int delete(String nickname) {
			String sql ="delete from PN_member where nickname = ?";
			try {
				int result = jdbcTemplate.update(sql, nickname);

				return result;

			}catch(EmptyResultDataAccessException e) {
				return -1;
			}		
		}

		@Override
		public String getEmail(String nickname) {
			String sql ="select email from PN_member where nickname = ?";
			try {
				String result = jdbcTemplate.queryForObject(sql,new Object[] {nickname},String.class);
				return result;
			}catch(EmptyResultDataAccessException e) {
				return null;
			}
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}