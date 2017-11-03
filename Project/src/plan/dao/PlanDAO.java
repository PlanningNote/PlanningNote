package plan.dao;

import java.util.List;

import plan.dto.PlanDTO;

public interface PlanDAO {
	public int insertPlan(PlanDTO dto);
	public int updatePlan(int no);
	public int deletePlan(int no);
	public List<PlanDTO> listPlan(); //��ü �׷� �Խù� ����Ʈ
	public PlanDTO findPlan(String search,String searchString); //���������Ұ��� �ۼ��ڷ� �Ұ���
	public List<PlanDTO> rankPlan();
	public List<PlanDTO> tagPlan(String tag);
	public List<PlanDTO> findOption(String search,String searchString); //����Ⱓ, �ñ�, �׸� �˻�
	public List<PlanDTO> subList(int group_no); //�׷쿡 �ش��ϴ� ��ü ���긮��Ʈ


}
