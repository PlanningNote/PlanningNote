package center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CenterController {

	@RequestMapping(value= "/center_main.do")
	public ModelAndView centerBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/Service/center_main.jsp");
	
	}  
	@RequestMapping(value= "/intro_company.do")
	public ModelAndView introBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/Service/intro_company.jsp");
	
	}  
	
	@RequestMapping(value= "/howto_main.do")
	public ModelAndView howtoBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/Service/howto_main.jsp");
	
	}  
	
	
	
}
