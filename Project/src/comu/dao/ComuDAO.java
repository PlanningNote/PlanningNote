package comu.dao;

import java.util.List;


import comu.dto.ComuDTO;

public interface ComuDAO {
	public int insertComu(ComuDTO dto);
	public int updateComu(ComuDTO no);
	public int deleteComu(int no, String pwd);
	public List<ComuDTO> listComu();
	public ComuDTO findComu(String search,String searchString);
	public List<ComuDTO> rankComu(); 
	public ComuDTO getComuBoard(int no, String mode);
	
}
