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
	

	
	@RequestMapping(value= "/FAQ_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/FAQBoard/FAQ_writeForm.jsp");
	}
	
	@RequestMapping(value= "/FAQ_write.do",method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpServletRequest arg0, @ModelAttribute FAQDTO dto, BindingResult result)
			throws Exception {
		//���� arg2�� dto �ѹ��� �� ���޾ƿ´�.		
		if(result.hasErrors()) { //������ �߻��ϴ� ���� �� �ϳ��� String���� �޾ƿԴµ� null���� ���Դµ� �װ��� int������ �ڵ������� ��Ű�鼭 ������ �߻��Ѵ�.
			dto.setNo(0);		
			dto.setCount(0);
		}
		faqDAO.insertFAQ(dto);
		return new ModelAndView("redirect:FAQ_list.do");
	}
	
	@RequestMapping(value= "/FAQ_content.do")
	public ModelAndView contentFAQ(@RequestParam String no) throws Exception {
		//@RequestParam Map<String, String> params
		// Set<Entry<String, String>> set = params.entrySet();
		// for(Entry<String,String> entry = set){
		// System.out.println(entry.getKey() + "=" + entry.getValues());
		// }
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		FAQDTO dto = faqDAO.getFAQBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/FAQBoard/FAQ_content.jsp");
		mav.addObject("getFAQBoard", dto);
		return mav;
	}
	
	@RequestMapping(value= "/FAQ_update.do", method=RequestMethod.GET)
	protected ModelAndView updateBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		FAQDTO dto = faqDAO.getFAQBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/FAQBoard/FAQ_updateForm.jsp","getFAQBoard",dto); //�� �ϳ��϶� ����
		return mav;
	}
	
	@RequestMapping(value= "/FAQ_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProBoard(HttpServletRequest arg0, @ModelAttribute FAQDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =faqDAO.updateFAQ(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:FAQ_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("no", dto.getNo());
			mav.setViewName("FAQ_update.do");
		}else {
			JOptionPane.showMessageDialog(null, "�����߻�");
			mav.addObject("no", dto.getNo());
			mav.setViewName("FAQ_content.do");
		}
		return mav;
	}
	
	@RequestMapping(value= "/FAQ_delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/FAQBoard/FAQ_delete.jsp");
	}
	
	@RequestMapping(value= "/FAQ_delete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = faqDAO.deleteFAQ(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:FAQ_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("no", no);
			mav.setViewName("FAQ_delete.do");
		}else {
			JOptionPane.showMessageDialog(null, "�����߻�");
			mav.addObject("no", no);
			mav.setViewName("FAQ_content.do");
		}		
		return mav;
	}

}













