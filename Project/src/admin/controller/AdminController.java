package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;

@Controller
public class AdminController {
	
	//������ ����ȭ��
	@RequestMapping(value= "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}
	
	/* ȸ�� ���� */
	
	//ȸ�� ���
	@RequestMapping(value= "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/list.jsp");
	}
	
	//ȸ�� �˻�
	@RequestMapping(value= "/admin_memberFind.do")
		public ModelAndView memberFind(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
			return new ModelAndView("WEB-INF/admin/find.jsp");
		}
	

}
