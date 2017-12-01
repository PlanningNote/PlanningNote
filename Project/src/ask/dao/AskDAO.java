package ask.dao;

import java.util.List;

import ask.dto.AskDTO;



public interface AskDAO {
	
	public void insertAsk(AskDTO dto);
	public int updateAsk(AskDTO dto);
	public int deleteAsk(int no, String pwd);
	public List<AskDTO> listAsk();
	public AskDTO findAsk(String search,String searchString);
	public List<AskDTO> rankAsk(); 
	public AskDTO getAskBoard(int no, String mode);
	public boolean checkpwd(String pwd);
	


}
