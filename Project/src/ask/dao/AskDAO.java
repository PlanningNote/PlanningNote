package ask.dao;

import java.util.List;

import ask.dto.AskDTO;

public interface AskDAO {
	public int insertAsk(AskDTO dto);
	public int updateAsk(int no);
	public int deleteAsk(int no);
	public List<AskDTO> listAsk();
	public AskDTO findAsk(String search,String searchString);
	public List<AskDTO> rankAsk();

}
