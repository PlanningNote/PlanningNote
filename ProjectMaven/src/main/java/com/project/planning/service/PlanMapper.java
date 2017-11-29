package com.project.planning.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.planning.model.PlanDTO;
import com.project.planning.model.SubPlanDTO;
import com.project.planning.model.TagDTO;

@Service
public class PlanMapper {
	
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertPlan(TagDTO dtoT,PlanDTO dtoP,SubPlanDTO dtoS) {
		int index=0,resT=0,resP=0,resS=0;
		resT=sqlSession.insert("tagPlan",dtoT);
		resP=sqlSession.insert("inertPlanDTO",dtoP);
		resS=sqlSession.insert("insertsubPlan", dtoS);
		if(resT+resP+resS!=3) {
			return index=-1;
		}
		return index=1;
	}

	public List<PlanDTO> listPlanA(String searching) {
		List<PlanDTO> dtoP = null;
		
		if(searching==null ||searching.equals("전체")) {
			dtoP=sqlSession.selectList("listPlanA");
		}else if(searching.equals("작성자")) {
			dtoP=sqlSession.selectList("listWriter", searching);
		}else if(searching.equals("제목")) {
			dtoP=sqlSession.selectList("listSubject", searching);
		}else if(searching.equals("나라")) {
			dtoP=sqlSession.selectList("listCountry", searching);
		}else if(searching.equals("도시")) {
			dtoP=sqlSession.selectList("listCity", searching);
		}else if(searching.equals("기간")) {
			dtoP=sqlSession.selectList("listPeriod", searching);
		}else if(searching.equals("계절")) {
			dtoP=sqlSession.selectList("listSeasion", searching);
		}else if(searching.equals("테마")) {
			dtoP=sqlSession.selectList("listTheme", searching);
		}
		return dtoP;
	}
	public Map listPlan(int group_no) {
		Map map = new HashMap();
		PlanDTO dtoP;
		List<SubPlanDTO> dtoS;
		
		dtoP=sqlSession.selectOne("getPlanDTO",group_no);
		dtoS = sqlSession.selectList("listSubPlanDTO",group_no);
		return map;
	}
	public SubPlanDTO getSubPlan(int board_num) {
		SubPlanDTO dtoS;
		dtoS=sqlSession.selectOne("getSubPlanDTO", board_num);
		return dtoS;
	}

	public int updatePlan(TagDTO dtoT, PlanDTO dtoP, SubPlanDTO dtoS) {
		int index=0,resT=0,resP=0,resS=0;
		resT=sqlSession.insert("tagPlan",dtoT);
		resP=sqlSession.insert("inertPlanDTO",dtoP);
		resS=sqlSession.insert("insertsubPlan", dtoS);
		if(resT+resP+resS!=3) {
			return index=-1;
		}
		return index=1;
	}
	public void deletePlan(int group_no) {
		sqlSession.delete("deletePlan", group_no);
		sqlSession.delete("deleteSub", group_no);
		sqlSession.delete("deleteTag", group_no);
	}
	
	
}
