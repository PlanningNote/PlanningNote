package plan.dao;

import java.util.List;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public interface PlanDAO {
	public int insertPlan(PlanDTO dto);
	public int insertsubPlan(SubPlanDTO sdto);
	public int tagPlan(TagDTO dto);
	//-----insert---------
	public int updatePlan(int no, PlanDTO dto);
	public int updateSubPlan(int no, SubPlanDTO dto);
	//update------
	public int deletePlan(int no);
	//delete------
	public List<PlanDTO> listAPlan(); //listPlanA�� ���� PlanDTO����Ʈ ������. 
	public List<SubPlanDTO> subList(int group_no); //�׷쿡 �ش��ϴ� ��ü ���긮��Ʈ
	public PlanDTO listPlan(int group_no);
	//list-----
	public PlanDTO findPlan(String search,String searchString); //���������Ұ��� �ۼ��ڷ� �Ұ���
	public List<PlanDTO> rankPlan();
	public List<PlanDTO> findOption(String search,String searchString); //����Ⱓ, �ñ�, �׸� �˻�
	
	public SubPlanDTO getSubContent(int no);//�ڼ��� ���� �������� �̵�
	
	
}
