package member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDTO;
@Controller
public class MemberController {   
	@Autowired
	private MemberDAO memberDAO;
	 
	
	@RequestMapping(value= "/login.do")
	protected ModelAndView Login(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/login.jsp");
	}
	
	
	@RequestMapping(value= "/login_ok.do")
	protected ModelAndView LoginOk(HttpSession session,@RequestParam String email, @RequestParam String pwd) throws Exception {
		if(email==null || pwd==null) {
			return new ModelAndView("redirect:login.do");
		}
		
		boolean result = memberDAO.checkMember(email, pwd);		
		ModelAndView mav = new ModelAndView();
		if(result) {
			 session.setAttribute("loginKey", email);
			 if(email.equals("admin")) {				 
				 mav.addObject("message","�����ڸ�� ����");
				 mav.setViewName("admin_main.do");
				 return mav;
			 }else {
				 mav.addObject("message","ȸ����� ����");
				 mav.addObject("location","index.jsp");
				 mav.setViewName("message.jsp");
				 return mav;
			 }
		}else {
			mav.addObject("message","�α��� ����");
			 mav.addObject("location","login.do");
			 mav.setViewName("message.jsp");
			 return mav;
		}		
	}
	
	@RequestMapping(value= "/email_check.do") //ȸ������form���� ����(join_member.jsp)
	protected ModelAndView emailCheck(HttpServletRequest arg0,@RequestParam String email) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("getEmail",email);		
		mav.setViewName("WEB-INF/member/EmailCheckForm.jsp");
		
		return mav;
	}
	
	@RequestMapping(value= "/nickname_check.do") //ȸ������form���� ����(join_member.jsp)
	protected ModelAndView nicknameCheck(HttpServletRequest arg0,@RequestParam String nickname) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("getNickname",nickname);		
		mav.setViewName("WEB-INF/member/NicknameCheckForm.jsp");
		
		return mav;
	}
	
	@RequestMapping(value= "/join_member.do", method=RequestMethod.GET) //ȸ������form���� ����(join_member.jsp)
	protected ModelAndView joinMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/join_member.jsp");
	}
	
	@RequestMapping(value= "/join_member.do", method=RequestMethod.POST) //join_member.jsp���� �Է�ó���� �� db�� �ִ� �۾�
	protected ModelAndView joinProMember(HttpServletRequest arg0, @ModelAttribute MemberDTO dto, BindingResult result) throws Exception {

		memberDAO.insertMember(dto);
		
		return new ModelAndView("redirect:login.do");
	}
	
	/*
	@RequestMapping(value= "/check_email.me", method=RequestMethod.POST) //join_member.jsp���� �Է�ó���� �� db�� �ִ� �۾�
	protected ModelAndView checkEmail(@RequestParam String email) throws Exception {
		memberDAO.checkEmail(email);
		
		return new ModelAndView("redirect:login.me");
	}
	*/
	
	@RequestMapping(value= "/MemberEmailCheckAction.do") 
	protected ModelAndView Check(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		boolean result = memberDAO.duplicateEmailCheck(email);
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();

		if(result)	out.println("0"); // ���̵� �ߺ�
		else		out.println("1");
		
		out.close();				
		
		return null;
	}
		
	@RequestMapping(value= "/MemberNicknameCheckAction.do") 
	protected ModelAndView NicknameCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nickname = request.getParameter("nickname");
		boolean result = memberDAO.duplicateNicknameCheck(nickname);
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();

		if(result)	out.println("0"); // ���̵� �ߺ�
		else		out.println("1");
		
		out.close();				
		
		return null;
	}
	
	
	
	
	
}