package admin.controller;

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
	
	//������ ����ȭ��
	@RequestMapping(value= "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}
	
	//�α׾ƿ�
	@RequestMapping(value= "/admin_logout.do")
	public ModelAndView adminLogout(HttpSession session,HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String key = (String) session.getAttribute("loginKey");		
		session.removeAttribute(key);
		// ���̵��ȉ� �С�
		return new ModelAndView("redirect:index.jsp");
	}
	
	
	/* ȸ�� ���� */
	
	//ȸ�� ���
	@RequestMapping(value= "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		System.out.println("########AdminController.java#########");
		System.out.println(list.get(0).getNickname());
		System.out.println(list.get(1).getNickname());
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/list.jsp");
		return mav;
	}
	
	//ȸ�� �˻�
	@RequestMapping(value= "/admin_memberFind.do")
		public ModelAndView memberFind(@RequestParam String search, @RequestParam String searchString) throws Exception {
			List<MemberDTO> list = memberDAO.fineMember(search, searchString);
			ModelAndView mav = new ModelAndView();
			mav.addObject("getList", list);
			mav.setViewName("WEB-INF/admin/find.jsp");
			return mav;
		}
	

}
