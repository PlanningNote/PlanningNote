package plan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public interface PlanDAO {
	public int insertPlan(TagDTO dtoT,PlanDTO dtoP,SubPlanDTO sdto, String[] a, String[] b, String[] c);
	public List<PlanDTO> listPlanA();
	public List<PlanDTO> searchPlanA(String mode, String searching);
	public TagDTO tagList(int group_no);
	public PlanDTO listPlan(int group_no);
	public List<SubPlanDTO> subList(int group_no);
	public SubPlanDTO getSubContent(int board_num);
	public int updatePlan(TagDTO dtoT,PlanDTO dtoP,SubPlanDTO dtoS);
	public int deletePlan(int group_no);
	
	
	
	public List<PlanDTO> mylistAPlan(String nickname);
	
}
