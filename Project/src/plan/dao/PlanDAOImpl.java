package plan.dao;

import java.util.List;

import plan.dto.PlanDTO;
import plan.ibatis.PlanMapper;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

public class PlanDAOImpl implements PlanDAO {
	@Override
	public int insertPlan(TagDTO dtoT, PlanDTO dtoP, SubPlanDTO dtoS, String[] lat, String[] lng, String[] index) {
		int res = 0;
		for (int i = 0; i < dtoS.getTargets().size(); i++) {
			if ((int) (dtoS.getTargets().get(i).getLng()) == Integer.parseInt(index[i])) {
				dtoS.getTargets().get(i).setLat(Double.parseDouble(lat[i]));
				dtoS.getTargets().get(i).setLng(Double.parseDouble(lng[i]));
			}
		}
		dtoT.setTag(dtoT.getTag1() + dtoT.getTag2() + dtoT.getTag3() + dtoT.getTag4() + dtoT.getTag5());
		res = PlanMapper.insertPlan(dtoT, dtoP, dtoS);
		return res;
	}

	@Override
	public List<PlanDTO> listPlanA() {
		List<PlanDTO>dtoP=PlanMapper.listPlanA();
		return dtoP;
	}

	@Override
	public PlanDTO listPlan(int group_no) {
		PlanDTO dtoP = PlanMapper.listPlan(group_no);
		return dtoP;
	}

	@Override
	public List<SubPlanDTO> subList(int group_no) {
		List<SubPlanDTO> dtoS = PlanMapper.subList(group_no);
		return dtoS;
	}

	@Override
	public SubPlanDTO getSubContent(int board_num) {
		SubPlanDTO dtoS = PlanMapper.subContent(board_num);
		return dtoS;
	}

	@Override
	public int updatePlan(TagDTO dtoT, PlanDTO dtoP, SubPlanDTO dtoS) {
		int res = 0;
		dtoT.setTag(dtoT.getTag1() + dtoT.getTag2() + dtoT.getTag3() + dtoT.getTag4() + dtoT.getTag5());
		res = PlanMapper.updatePlan(dtoT, dtoP, dtoS);
		return res; 
	}

	@Override
	public int deletePlan(int group_no) {
		int res=0;
		res=PlanMapper.deletePlan(group_no);
		return res;
	}
	
}
