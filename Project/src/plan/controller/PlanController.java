package plan.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import plan.dao.PlanDAO;
import plan.dto.PlanDTO;

@org.springframework.stereotype.Controller
public class PlanController {

	@Autowired
	private PlanDAO planDAO;
	
	@RequestMapping(value="/plan.do")//계획적는 페이지로 이동.
	public ModelAndView add(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/addPlan.jsp");
		return mav;
	}
	@RequestMapping(value="/list.do")//계획적는 페이지로 이동.
	public ModelAndView list(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");
		return mav;
	}
	@RequestMapping(value="/content.do")//계획적는 페이지로 이동.
	public ModelAndView content(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/contentPlan.jsp");
		return mav;
	}
	
	/*@RequestMapping(value="/addPlan.do")//계획 저장
	public ModelAndView addPlan(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		PlanDTO dto = new PlanDTO();
		int res=0;
		res=planDAO.insertPlan(dto);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/plan/list.jsp");
		return mav;
	}
	@RequestMapping(value="/getContent.do")//상세페이지로 이동
	public ModelAndView getContent(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		PlanDTO dto = new PlanDTO();
		int no=Integer.parseInt(arg0.getParameter("no"));
		dto = planDAO.getContent(no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/plan/list.jsp");
		return mav;
	}*/
	
}