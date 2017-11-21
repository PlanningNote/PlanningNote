package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@RequestMapping(value= "/admin_main.do")
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}

}
