package comu.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import comu.dao.ComuDAO;
import comu.dto.ComuDTO;
import comu.dto.ComuReplyDTO;

@Controller
public class ComuController {

	@Autowired
	private ComuDAO comuDAO;	
	
	@RequestMapping(value="/comu_list.do")
	public ModelAndView listComu(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<ComuDTO> list = comuDAO.listComu(); // �������°�
		ModelAndView mav = new ModelAndView();
		mav.addObject("comuList", list);
		mav.setViewName("WEB-INF/ComuBoard/comu_list.jsp");
		return mav;
	}	
	
	@RequestMapping(value= "/comu_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/ComuBoard/comu_writeForm.jsp");
	}
	
	@RequestMapping(value= "/comu_write.do",method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpSession session, HttpServletRequest arg0, @ModelAttribute ComuDTO dto, BindingResult result)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0;
		
		MultipartFile mf = mr.getFile("img"); 
		
		
		//���� ����οԴ��� Ȯ��
		String img= mf.getOriginalFilename(); //���� �����̸� �ö����
		
		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //����ð�
			String saveImg = now+img;
		
		
		String upPath = session.getServletContext().getRealPath("/imgfile/comuImg"); //�������� ������ �ϳ�����ڴ�.
		
		
		//������ ������ �Ű� ���� . (���Ͼ���)
		File file = new File(upPath, saveImg);
		
		try{
			mf.transferTo(file); //���� ���� ����
			System.out.println("�������� ����! ");
		}catch(IOException e) {
			System.out.println("�������۽��ФФ� ");
			e.printStackTrace();
		}
				
		dto.setImg(saveImg);
		}else {
			dto.setImg("");
		}
		if(result.hasErrors()) { //������ �߻��ϴ� ���� �� �ϳ��� String���� �޾ƿԴµ� null���� ���Դµ� �װ��� int������ �ڵ������� ��Ű�鼭 ������ �߻��Ѵ�.
			dto.setNo(0);
			dto.setCount(0);
		}
		
		
	    comuDAO.insertComu(dto);
	    mav.setViewName("message.jsp");
	    mav.addObject("url","comu_list.do");
	    mav.addObject("msg", "�ۿø��� �����Ͽ����ϴ�.");
		return mav;
	}
	
	@RequestMapping(value= "/comu_content.do")
	public ModelAndView contentComu(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		ComuDTO dto = comuDAO.getComuBoard(Integer.parseInt(no), "content");
		List<ComuReplyDTO> list = comuDAO.listComuReply(Integer.parseInt(no));
		ModelAndView mav = new ModelAndView("WEB-INF/ComuBoard/comu_content.jsp");
		mav.addObject("getComuBoard", dto);
		mav.addObject("comuReplyList",list);
		return mav;
	}
	
	@RequestMapping(value= "/comu_update.do", method=RequestMethod.GET)
	protected ModelAndView updateBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		ComuDTO dto = comuDAO.getComuBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/ComuBoard/comu_updateForm.jsp","getComuBoard",dto); //�� �ϳ��϶� ����
		return mav;
	}
	
	@RequestMapping(value= "/comu_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProBoard(HttpServletRequest arg0, @ModelAttribute ComuDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =comuDAO.updateComu(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		if(res>0) {
			mav.addObject("url","comu_list.do");
			mav.addObject("msg","�ۼ��� �����ϼ̽��ϴ�.");
		}else if(res==-1) {
			mav.addObject("url","comu_update.do?no="+dto.getNo());
			mav.addObject("msg","��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
		}else {
			mav.addObject("url","comu_content.do?no="+dto.getNo());
			mav.addObject("msg","�����߻�... �����ڿ��� �����ּ���");
		}
		return mav;
	}
	
	@RequestMapping(value= "/comu_delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/ComuBoard/comu_delete.jsp");
	}
	

	
	@RequestMapping(value= "/comu_delete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = comuDAO.deleteComu(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		if(res>0) {
			mav.addObject("url","comu_list.do");
			mav.addObject("msg","�ۻ��� �����ϼ̽��ϴ�.");
		}else if(res==-1) {
			mav.addObject("url","comu_delete.do");
			mav.addObject("msg","��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
		}else {
			mav.addObject("url","comu_content.do?no="+no);
			mav.addObject("msg","�����߻� �����ڿ��� �����ּ���");
		}		
		return mav;
	}
	
	@RequestMapping(value= "/insertComuReply.do")
	public ModelAndView insertComuReply(@RequestParam int comu_no, @RequestParam String writer, @RequestParam String content) throws Exception {
		comuDAO.insertReply(comu_no, writer, content);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		mav.addObject("msg","����� �߰��Ͽ����ϴ�.");
		mav.addObject("url","comu_content.do?no="+comu_no);
		return mav;
	}
	
	@RequestMapping(value= "/nicknameClick.do", method=RequestMethod.GET)
	public ModelAndView nickClickForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/ComuBoard/nickClickForm.jsp");
		mav.addObject("nickname",arg0.getParameter("nickname"));
		return mav;
	}
	
	
}













