package report;

import java.util.List;

public interface BReportDAO {
	public int insertReport(BReportDTO dto);
	public List<BReportDTO> listBReport();
	public BReportDTO getBContent(int no);
	public void sendEmail(String email, String content, String subject);
	public int updateReport(int no);
	
}
