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

public class BReportDAOImpl implements BReportDAO {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<BReportDTO>{

		@Override
		public BReportDTO mapRow(ResultSet arg0, int arg1) throws SQLException {
			BReportDTO dto = new BReportDTO();
			dto.setNo(arg0.getInt("no"));
			dto.setReporter(arg0.getString("reporter"));
			dto.setSuspecter(arg0.getString("suspecter"));
			dto.setBoard_no(arg0.getInt("board_no"));
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
	public int insertReport(BReportDTO dto) {
		String sql="insert into PN_BReport values(breport_seq.nextval, ?,?,?,?,?,?,sysdate,?)";
		Object[] values = new Object[] {dto.getReporter(),dto.getSuspecter(),dto.getBoard_no(),dto.getContent(),dto.getImg(),dto.getHandling(),dto.getHandleday()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public List<BReportDTO> listBReport() {
		String sql ="select * from PN_BReport order by no desc";
		List<BReportDTO> list = jdbcTemplate.query(sql, mapper);
		return list;		
	}

	@Override
	public BReportDTO getBContent(int no) {
		String sql = "select * from PN_BReport where no = ?";
		BReportDTO dto = jdbcTemplate.query(sql, new MyPreparedStatementSetterForPrimaryKey(no), new MyResultSetExtractor());
		return dto;
	}
	
	class MyResultSetExtractor implements ResultSetExtractor<BReportDTO>{
		@Override
		public BReportDTO extractData(ResultSet arg0) throws SQLException, DataAccessException {
			if(arg0.next()) {
				BReportDTO dto = new BReportDTO();
				dto.setNo(arg0.getInt("no"));
				dto.setReporter(arg0.getString("reporter"));
				dto.setSuspecter(arg0.getString("suspecter"));
				dto.setBoard_no(arg0.getInt("board_no"));
				dto.setContent(arg0.getString("content"));
				dto.setImg(arg0.getString("img"));
				dto.setHandling(arg0.getString("handling"));
				dto.setAskday(arg0.getString("askday"));
				dto.setHandleday(arg0.getString("handleday"));
				return dto;
			}
			throw new DataRetrievalFailureException("�ش� ��ü�� ã������ �����ϴ�.");
		}		
	}
	
	//����ǥ�� �־��ֱ����� Ŭ����
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
	public void sendEmail(String email, String c) {
		String host = "smtp.gmail.com";
		String subject = "�����ڵ� �Խù� ���� �ȳ�";
		String fromName = "�����ڵ� ������";
		String from = "homie2032@gmail.com";
		String to1 = email;
		String content = "��й�ȣ [" + c + "]";
		
		try {
			Properties props = new Properties();
			// G-Mail SMTP ����
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
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B"))); // ������ ��� ����
			InternetAddress[] address1 = { new InternetAddress(to1) }; 
			msg.setRecipients(Message.RecipientType.TO,  address1); // �޴� ��� ����1
			msg.setSentDate(new java.util.Date()); //������ ��¥ ����
			msg.setContent(content,"text/html;charset=euc-kr"); //���� ����(HTML����)
			
			Transport.send(msg); //���� ������
		}catch(MessagingException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
