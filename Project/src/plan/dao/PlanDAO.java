package plan.dao;

import java.util.List;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public interface PlanDAO {
	public int insertPlan(PlanDTO dto);
	public int updatePlan(int no, PlanDTO dto);
	public int deletePlan(int no);
	public PlanDTO findPlan(String search,String searchString); //제목으로할건지 작성자로 할건지
	public List<PlanDTO> rankPlan();
	public int tagPlan(TagDTO dto,String tag,String[] arr);
	public List<PlanDTO> findOption(String search,String searchString); //여행기간, 시기, 테마 검색
	public List<PlanDTO> subList(int group_no); //그룹에 해당하는 전체 서브리스트
	public PlanDTO getContent(int no);//자세히 보기 페이지로 이동
	public List<PlanDTO> listPlan(int group_no, PlanDTO dto);
	public int insertsubPlan(SubPlanDTO sdto);
}
