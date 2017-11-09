package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;
@Controller
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;
	
	
	@RequestMapping(value= "/login.me")
	protected ModelAndView Login(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/login.jsp");
	}
	
	/*
	@RequestMapping(value= "/login_ok.me", method=RequestMethod.GET)
	protected ModelAndView LoginOk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		return new ModelAndView("");
	}
	
	@RequestMapping(value= "/join_member.me", method=RequestMethod.GET)
	protected ModelAndView joinMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/join_member.jsp");
	}
	
	@RequestMapping(value= "/join_member.me", method=RequestMethod.POST)
	protected ModelAndView joinProMember(HttpServletRequest arg0, @ModelAttribute MemberDTO dto, BindingResult result) throws Exception {

		memberDAO.insertMember(dto);
		return new ModelAndView("redirect:login.me");
	}*/
	
}
