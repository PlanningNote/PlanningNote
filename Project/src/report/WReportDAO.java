package report;

import java.util.List;

public interface WReportDAO {
	public int insertReport(WReportDTO dto);
	public List<WReportDTO> listWReport();
	public WReportDTO getWContent(int no);
	public void sendEmail(String email, String content, String subject);
	public int updateReport(int no);

}
