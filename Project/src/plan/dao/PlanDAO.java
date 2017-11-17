package plan.dao;

import java.util.List;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public interface PlanDAO {
	public int insertPlan(PlanDTO dto);
	public int updatePlan(int no, PlanDTO dto);
	public int deletePlan(int no);
	public PlanDTO findPlan(String search,String searchString); //���������Ұ��� �ۼ��ڷ� �Ұ���
	public List<PlanDTO> rankPlan();
	public int tagPlan(TagDTO dto,String tag,String[] arr);
	public List<PlanDTO> findOption(String search,String searchString); //����Ⱓ, �ñ�, �׸� �˻�
	public List<PlanDTO> subList(int group_no); //�׷쿡 �ش��ϴ� ��ü ���긮��Ʈ
	public PlanDTO getContent(int no);//�ڼ��� ���� �������� �̵�
	public List<PlanDTO> listPlan(int group_no, PlanDTO dto);
	public int insertsubPlan(SubPlanDTO sdto);
}
