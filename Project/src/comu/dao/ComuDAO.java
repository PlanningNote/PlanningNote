package comu.dao;

import java.util.List;

import comu.dto.ComuDTO;

public interface ComuDAO {
	public int insertComu(ComuDTO dto);
	public int updateComu(int no);
	public int deleteComu(int no);
	public List<ComuDTO> listComu();
	public ComuDTO findComu(String search,String searchString);
}
