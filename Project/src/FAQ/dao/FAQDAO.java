package FAQ.dao;

import java.util.List;

import FAQ.dto.FAQDTO;

public interface FAQDAO {
	public int insertFAQ(FAQDTO dto);
	public int updateFAQ(FAQDTO dto);
	public int deleteFAQ(int no, String pwd);
	public List<FAQDTO> listFAQ() ;
	public FAQDTO findFAQ(String search,String searchString);
	public List<FAQDTO> rankFAQ();
	public FAQDTO getFAQBoard(int parseInt, String string);
	


}
