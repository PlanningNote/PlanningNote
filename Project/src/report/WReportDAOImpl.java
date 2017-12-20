package report;

import java.sql.PreparedStatement;
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

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import report.BReportDAOImpl.MyPreparedStatementSetterForPrimaryKey;
import report.BReportDAOImpl.MyResultSetExtractor;
import report.BReportDAOImpl.MyRowMapper;

public class WReportDAOImpl implements WReportDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<WReportDTO>{

		@Override
		public WReportDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			WReportDTO dto = new WReportDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setReporter(arg0.getString("reporter"));
			dto.setSuspecter(arg0.getString("suspecter"));
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
	public int insertReport(WReportDTO dto) {
		String sql="insert into PN_WReport values(wreport_seq.nextval, ?,?,?,?,?,sysdate,?)";
		Object[] values = new Object[] {dto.getReporter(),dto.getSuspecter(),dto.getContent(),dto.getImg(),dto.getHandling(),dto.getHandleday()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public List<WReportDTO> listWReport() {
		String sql ="select * from PN_WReport order by no desc";
		List<WReportDTO> list = jdbcTemplate.query(sql, mapper);
		return list;		
	}

	@Override
	public WReportDTO getWContent(int no) {
		String sql = "select * from PN_WReport where no = ?";
		WReportDTO dto = jdbcTemplate.query(sql, new MyPreparedStatementSetterForPrimaryKey(no), new MyResultSetExtractor());
		return dto;
	}
	
	class MyResultSetExtractor implements ResultSetExtractor<WReportDTO>{
		@Override
		public WReportDTO extractData(ResultSet arg0) throws SQLException, DataAccessException {
			if(arg0.next()) {
				WReportDTO dto = new WReportDTO();
				dto.setNo(arg0.getInt("no"));
				dto.setReporter(arg0.getString("reporter"));
				dto.setSuspecter(arg0.getString("suspecter"));
				dto.setContent(arg0.getString("content"));
				dto.setImg(arg0.getString("img"));
				dto.setHandling(arg0.getString("handling"));
				dto.setAskday(arg0.getString("askday"));
				dto.setHandleday(arg0.getString("handleday"));
				return dto;
			}
			throw new DataRetrievalFailureException("해당 객체를 찾을수가 없습니다.");
		}		
	}
	
	//물음표를 넣어주기위한 클래스
	class MyPreparedStatementSetterForPrimaryKey implements PreparedStatementSetter{
		private Integer no;
		public MyPreparedStatementSetterForPrimaryKey(Integer no) {
			this.no = no;
		}
		@Override
		public void setValues(PreparedStatement arg0) throws SQLException {
			arg0.setInt(1, no);
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
	public int updateReport(int no) {
		String sql = "update PN_WReport set handleday  = sysdate ,handle = ? where no= ?";
		int res = jdbcTemplate.update(sql,"Y",no);
		return res;
	}

}
