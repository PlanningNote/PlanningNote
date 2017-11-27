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
	
	@RequestMapping(value = "/notLogin.do") // 계획적는 페이지로 이동.
	public ModelAndView login(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notLogin.jsp");
		return mav;
	}
	
	
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
		 mav.setViewName("message.jsp");
		if(result) {
			String nickname = memberDAO.getNickname(email);
			 session.setAttribute("loginKey", email);
			 session.setAttribute("mynick", nickname);
			 if(email.equals("admin")) {
				
				 mav.addObject("msg", "관리자모드로 로그인되었습니다.");
				 mav.addObject("location","admin_main.do");
				 return mav;
			 }else {
				 mav.addObject("msg", "로그인에 성공하셨습니다.");
				 mav.addObject("location","index.jsp");
				 return mav;				
			 }
		}   
			 else{
				 mav.addObject("msg", "로그인실패");
				 mav.addObject("location","redirect:login.do");
				 return mav;
			 }
	}
	
	@RequestMapping(value= "/logout.do")
	protected ModelAndView logout(HttpSession session,HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		 session.invalidate(); 
		return new ModelAndView("index.jsp");
	}
	
	@RequestMapping(value= "/email_check.do") //회원가입form으로 가기(join_member.jsp)
	protected ModelAndView emailCheck(HttpServletRequest arg0,@RequestParam String email) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("getEmail",email);		
		mav.setViewName("WEB-INF/member/EmailCheckForm.jsp");
		
		return mav;
	}
	
	@RequestMapping(value= "/nickname_check.do") //회원가입form으로 가기(join_member.jsp)
	protected ModelAndView nicknameCheck(HttpServletRequest arg0,@RequestParam String nickname) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("getNickname",nickname);		
		mav.setViewName("WEB-INF/member/NicknameCheckForm.jsp");
		
		return mav;
	}
	
	@RequestMapping(value= "/join_member.do", method=RequestMethod.GET) //회원가입form으로 가기(join_member.jsp)
	protected ModelAndView joinMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/join_member.jsp");
	}
	
	@RequestMapping(value= "/join_member.do", method=RequestMethod.POST) //join_member.jsp에서 입력처리한 후 db에 넣는 작업
	protected ModelAndView joinProMember(HttpServletRequest arg0, @ModelAttribute MemberDTO dto, BindingResult result) throws Exception {

		memberDAO.insertMember(dto);
		
		return new ModelAndView("redirect:login.do");
	}
	
	/*
	@RequestMapping(value= "/check_email.me", method=RequestMethod.POST) //join_member.jsp에서 입력처리한 후 db에 넣는 작업
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

		if(result)	out.println("0"); // 아이디 중복
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

		if(result)	out.println("0"); // 아이디 중복
		else		out.println("1");
		
		out.close();				
		
		return null;
	}
	
	
	
	
	
}