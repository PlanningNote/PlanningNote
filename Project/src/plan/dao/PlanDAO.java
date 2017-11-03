package plan.dao;

import java.util.List;

import plan.dto.PlanDTO;

public interface PlanDAO {
	public int insertPlan(PlanDTO dto);
	public int updatePlan(int no);
	public int deletePlan(int no);
	public List<PlanDTO> listPlan(); //전체 그룹 게시물 리스트
	public PlanDTO findPlan(String search,String searchString); //제목으로할건지 작성자로 할건지
	public List<PlanDTO> rankPlan();
	public List<PlanDTO> tagPlan(String tag);
	public List<PlanDTO> findOption(String search,String searchString); //여행기간, 시기, 테마 검색
	public List<PlanDTO> subList(int group_no); //그룹에 해당하는 전체 서브리스트


}
