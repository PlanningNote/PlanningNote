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

	// ������ ����ȭ��
	@RequestMapping(value = "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}

	// �α׾ƿ�
	@RequestMapping(value = "/admin_logout.do")
	public ModelAndView adminLogout(HttpSession session, HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		String key = (String) session.getAttribute("loginKey");
		session.removeAttribute(key);
		// ���̵��ȉ� �С�
		return new ModelAndView("redirect:index.jsp");
	}

	/* ȸ�� ���� */

	// ȸ�� ���
	@RequestMapping(value = "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/member/list.jsp");
		return mav;
	}

	// ȸ�� �˻�
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

	// ȸ�� �����ϱ��� ��й�ȣ Ȯ���� ���� form
	@RequestMapping(value = "/admin_memberDeleteForm.do")
	public ModelAndView memberDeleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.setViewName("WEB-INF/admin/member/deleteForm.jsp");
		return mav;
	}

	// ȸ�� �����ϱ��� ��й�ȣ Ȯ��
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
				msg = "ȸ�� ���� ����... ȸ������������� �̵��մϴ�.";
				url = "admin_memberList.do";     
			}else {
	            msg = "ȸ�� ���� ����... ȸ������������� �̵��մϴ�.";
				url = "admin_memberList.do";  
			}				
		}else {
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���~!";
			url = "admin_memberDeleteForm.do?no="+no; 
		}
		arg0.setAttribute("msg", msg);
		arg0.setAttribute("url", url);
		return new ModelAndView("message.jsp");
	}
	
	
	
	
	
	
	
	
	
	

}
