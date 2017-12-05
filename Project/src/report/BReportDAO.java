package report;

import java.util.List;

public interface BReportDAO {
	public int insertReport(BReportDTO dto);
	public List<BReportDTO> listBReport();
}
