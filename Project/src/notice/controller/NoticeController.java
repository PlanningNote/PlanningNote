package notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ask.dto.AskDTO;
import notice.dao.NoticeDAO;
import notice.dto.NoticeDTO;

@Controller
public class NoticeController {

	@Autowired
	private NoticeDAO noticeDAO;
	
	
	@RequestMapping("/submit")
	public void submit(HttpServletRequest request){
	    System.out.println("에디터 컨텐츠값:"+request.getParameter("editor"));
	}


	@RequestMapping(value="/notice_list.do")
	public ModelAndView listNotice(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<NoticeDTO> list = noticeDAO.listNotice(); // 가져오는거
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeList", list);
		mav.setViewName("WEB-INF/NoticeBoard/notice_list.jsp");
		return mav;
	}
	
	@RequestMapping(value= "/notice_content.do")
	public ModelAndView contentNotice(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/NoticeBoard/notice_content.jsp");
		mav.addObject("getNoticeBoard", dto);
		return mav;
	}
	
	
}













