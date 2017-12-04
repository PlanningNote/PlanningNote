package member.controller;
  
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import plan.dao.PlanDAO;
import plan.dto.PlanDTO;
@Controller
public class MemberController {   
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired 
	private PlanDAO dao;
	@RequestMapping(value = "/notLogin.do") // ��ȹ���� �������� �̵�.
	public ModelAndView login(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notLogin.jsp");
		return mav;
	}
	
	@RequestMapping(value= "/mypage.do")
	protected ModelAndView Mypage(HttpSession session, HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		String my = (String) session.getAttribute("mynick");
		mav.setViewName("WEB-INF/Mypage/mypage_main.jsp");
		List<PlanDTO> dtoP = dao.mylistAPlan(my);
		mav.addObject("dtoP", dtoP);	
		
		
		
		
		return mav;
	}
	
	@RequestMapping(value= "/mypage_update.do")
	protected ModelAndView Mypage_update(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/Mypage/mypage_update.jsp");
	}
	
	@RequestMapping(value= "/mypage_updatePro.do")
	protected ModelAndView Mypage_updatePro(HttpSession session,HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
			String nickname = (String) session.getAttribute("mynick");
			String oldPwd = arg0.getParameter("oldPwd");
			String newPwd = arg0.getParameter("newPwd");
			boolean result = memberDAO.checkPwd(nickname, oldPwd);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("message.jsp");
			if(result) {
				int res = memberDAO.updateMember(newPwd, nickname);
				if(res>0) {
					mav.addObject("msg","��й�ȣ�� ����Ǿ����ϴ�.");
					mav.addObject("url","mypage.do");
				}else {
					mav.addObject("msg","��й�ȣ���� ����... �����ڿ��� �����ּ���");
					mav.addObject("url","mypage.do");
				}
			}else {
				mav.addObject("msg","���� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				mav.addObject("url","mypage_update.do");
			}	
		return mav;
	}
	
	@RequestMapping(value= "/login.do")
	protected ModelAndView Login(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/login.jsp");
	}
	
	@RequestMapping(value= "/mypage_delete.do")
	protected ModelAndView mypage_delete(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/Mypage/mypage_delete.jsp");
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
				
				 mav.addObject("msg", "�����ڸ��� �α��εǾ����ϴ�.");
				 mav.addObject("url","admin_main.do");
				 return mav;
			 }else {
				 mav.addObject("msg", "�α��ο� �����ϼ̽��ϴ�.");
				 mav.addObject("url","index.jsp");
				 return mav;				
			 }
		}   
		else{
				mav.addObject("msg", "�α��ν���");
				mav.addObject("url","login.do");
				return mav;
			 }
	}
	
	@RequestMapping(value= "/logout.do")
	protected ModelAndView logout(HttpSession session,HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		session.invalidate(); 
		return new ModelAndView("index.jsp");
	}
	
	@RequestMapping(value= "/find_pwd.do")
	protected ModelAndView Find_pwd(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/find_pwd.jsp");
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
	
	@RequestMapping(value= "/findemail.do") 
	public ModelAndView findemail(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String email = request.getParameter("email");
		String pwd = memberDAO.findPwd(email);
		memberDAO.sendEmail(email.toString(),pwd);
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value= "/delete.do") 
	public ModelAndView delete(HttpSession session,@RequestParam String pwd) throws Exception{
		String nickname = (String) session.getAttribute("mynick");
		boolean bb = memberDAO.checkPwd(nickname, pwd); //�г��Ӱ� ����� ������ ȸ������ üũ
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		if(bb) {
			int result = memberDAO.delete(nickname); 
			if(result>0) {
				mav.addObject("msg","ȸ��Ż��Ǿ����ϴ�. �׵��� �̿����ּż� �����մϴ�~");
				mav.addObject("url","index.jsp");		
				session.invalidate();
				return mav;
				
			}else {
				mav.addObject("msg","ȸ��Ż��ó�� ����... �˼��մϴ� �����ڿ��� �����ּ���");
				mav.addObject("url","index.jsp");	
				return mav;
			}
		}else{
			mav.addObject("msg","��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���");
			mav.addObject("url","mypage_delete.do");	
			return mav;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}