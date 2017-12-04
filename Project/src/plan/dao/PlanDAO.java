package plan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public interface PlanDAO {
	public int insertPlan(PlanDTO dto);
	public int insertsubPlan(SubPlanDTO sdto, String[] lat, String[] lng,String index[],int no);
	public int inserttag(TagDTO dto);
	//-----insert---------
	public int updatePlan(int no, PlanDTO dto);
	public int updateSubPlan(List num, SubPlanDTO dto);
	//update------
	public int deletePlan(int no);
	//delete------
	public List<PlanDTO> listAPlan(); //listPlanA�� ���� PlanDTO����Ʈ ������. 
	public List<SubPlanDTO> subList(int group_no); //�׷쿡 �ش��ϴ� ��ü ���긮��Ʈ
	public PlanDTO listPlan(int group_no);
	public SubPlanDTO getSubContent(int no);//�ڼ��� ���� �������� �̵�
	public List<TagDTO> taglist();
	//list-----
	public PlanDTO findPlan(String search,String searchString); //���������Ұ��� �ۼ��ڷ� �Ұ���
	public List<PlanDTO> rankPlan();
	public List<PlanDTO> findOption(String search,String searchString); //����Ⱓ, �ñ�, �׸� �˻�
	public int getBoardNo();
	//����
		public List<PlanDTO> mylistAPlan(String nickname);
}
