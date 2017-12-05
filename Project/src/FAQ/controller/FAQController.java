package FAQ.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import FAQ.dao.FAQDAO;
import FAQ.dto.FAQDTO;



@Controller
public class FAQController {

	@Autowired
	private FAQDAO faqDAO;
	
	@RequestMapping(value="/FAQ_list.do")
	public ModelAndView listFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<FAQDTO> list = faqDAO.listFAQ(); // �������°�
		ModelAndView mav = new ModelAndView();
		mav.addObject("FAQList", list);
		mav.setViewName("WEB-INF/FAQBoard/FAQ_list.jsp");
		return mav;
	}
	
	@RequestMapping(value= "/FAQ_content.do")
	public ModelAndView contentFAQ(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		FAQDTO dto = faqDAO.getFAQBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/FAQBoard/FAQ_content.jsp");
		mav.addObject("getFAQBoard", dto);
		return mav;
	}
	
	@RequestMapping(value="/FAQ_find.do")
	public ModelAndView findFAQ(@RequestParam String search, @RequestParam String searchString) throws Exception {
		List<FAQDTO> list = faqDAO.findFAQ(search, searchString); // �������°�
		ModelAndView mav = new ModelAndView();
		mav.addObject("FAQList", list);
		mav.setViewName("WEB-INF/FAQBoard/FAQ_list.jsp");
		return mav;
	}
}













