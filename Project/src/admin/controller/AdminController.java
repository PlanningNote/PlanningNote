package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

@Controller
public class AdminController {
	@Autowired
	private MemberDAO memberDAO;
	
	//관리자 메인화면
	@RequestMapping(value= "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}
	
	/* 회원 관리 */
	
	//회원 목록
	@RequestMapping(value= "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/list.jsp");
		return mav;
	}
	
	//회원 검색
	@RequestMapping(value= "/admin_memberFind.do")
		public ModelAndView memberFind(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
			return new ModelAndView("WEB-INF/admin/find.jsp");
		}
	

}
