package admin.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

@Controller
public class AdminController {
	@Autowired
	private MemberDAO memberDAO;

	// 관리자 메인화면
	@RequestMapping(value = "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}

	// 로그아웃
	@RequestMapping(value = "/admin_logout.do")
	public ModelAndView adminLogout(HttpSession session, HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		String key = (String) session.getAttribute("loginKey");
		session.removeAttribute(key);
		// ★이동안됌 ㅠ★
		return new ModelAndView("redirect:index.jsp");
	}

	/* 회원 관리 */

	// 회원 목록
	@RequestMapping(value = "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/member/list.jsp");
		return mav;
	}

	// 회원 검색
	@RequestMapping(value = "/admin_memberFind.do")
	public ModelAndView memberFind(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String search = arg0.getParameter("search");
		String searchString = arg0.getParameter("searchString");

		ModelAndView mav = new ModelAndView();
		if (search != null && searchString != null) {
			List<MemberDTO> list = memberDAO.fineMember(search, searchString);
			mav.addObject("getList", list);
		}
		mav.setViewName("WEB-INF/admin/member/find.jsp");
		return mav;
	}

	// 회원 삭제하기전 비밀번호 확인을 위한 form
	@RequestMapping(value = "/admin_memberDeleteForm.do")
	public ModelAndView memberDeleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.setViewName("WEB-INF/admin/member/deleteForm.jsp");
		return mav;
	}

	// 회원 삭제하기전 비밀번호 확인
	@RequestMapping(value = "/admin_deleteMember.do")
	public ModelAndView memberDeleteCheck(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String pwd = arg0.getParameter("pwd");
		int no = Integer.parseInt(arg0.getParameter("no"));
		System.out.println("########");
		System.out.println("no : "+no);
		boolean result = memberDAO.checkPwd("admin", pwd);
		ModelAndView mav = new ModelAndView();
		String msg = null, url = null;
		if(result) {
			int res = memberDAO.deleteMember(no);			
			if(res>0) {				
				msg = "회원 삭제 성공... 회원목록페이지로 이동합니다.";
				url = "admin_memberList.do";     
			}else {
	            msg = "회원 삭제 실패... 회원목록페이지로 이동합니다.";
				url = "admin_memberList.do";  
			}				
		}else {
			msg = "비밀번호가 틀렸습니다. 다시 입력해주세요~!";
			url = "admin_memberDeleteForm.do?no="+no; 
		}
		arg0.setAttribute("msg", msg);
		arg0.setAttribute("url", url);
		return new ModelAndView("message.jsp");
	}
	
	
	
	
	
	
	
	
	
	

}
