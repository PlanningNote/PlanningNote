package ask.controller;

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
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ask.dao.AskDAO;
import ask.dto.AskDTO;


@Controller
public class AskController {
	@Autowired
	private AskDAO askDAO;

	@RequestMapping(value="/ask_list.do")
	public ModelAndView listAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<AskDTO> list = askDAO.listAsk();
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list);
		mav.setViewName("WEB-INF/AskBoard/ask_list.jsp");		
		return mav;		
	}
	
	@RequestMapping(value="/ask_find.do")
	public ModelAndView findAsk(@RequestParam String search, @RequestParam String searchString) throws Exception {
		List<AskDTO> list = askDAO.findAsk(search,searchString);
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list);
		mav.setViewName("WEB-INF/AskBoard/ask_list.jsp");		
		return mav;		
	}
	
	@RequestMapping(value= "/ask_content.do")
	public ModelAndView contentBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/AskBoard/ask_content.jsp");
		mav.addObject("getAskBoard", dto);
		return mav;
	}

	
	@RequestMapping(value= "/ask_write.do", method=RequestMethod.GET)
	protected ModelAndView writeAskFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_writeForm.jsp");
		
	}
	@RequestMapping(value= "/ask_write.do",method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpSession session, HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)
			throws Exception {
	
			//���Ϲޱ� 
		ModelAndView mav = new ModelAndView();
			
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0;
		
		//����ȯ �Ƿ��� �� xml�� MultipartResolver �־�� ��.
		MultipartFile mf = mr.getFile("img"); 
		
		
		//���� ����οԴ��� Ȯ��
		String img= mf.getOriginalFilename(); //���� �����̸� �ö����
		
		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //����ð�
			String saveImg = now+img;
		
		
		String upPath = session.getServletContext().getRealPath("/imgfile/askImg"); //�������� ������ �ϳ�����ڴ�.
		
		
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
			System.out.println("����");
		}
		
		
	    askDAO.insertAsk(dto);
		return new ModelAndView("ask_list.do");
		
		 
	}
	
	@RequestMapping(value= "/ask_delete.do", method=RequestMethod.GET)
	public ModelAndView deleteAskForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_delete.jsp");
	}
	
	@RequestMapping(value= "/ask_delete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			mav.addObject("msg", "���������� �����Դϴ�. �������� �̵��մϴ�.");
			mav.addObject("url","index.jsp");
		}
		
		int res = askDAO.deleteAsk(Integer.parseInt(no), pwd);
		
		if(res>0) {
			mav.addObject("msg", "�����Ǿ����ϴ�.");
			mav.addObject("url","ask_list.do");
		}else if(res==-1) {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url","ask_delete.do");
		}else {
			mav.addObject("msg", "�����߻� �����ڿ��� �����ּ���");
			mav.addObject("url","ask_content.do");
		}		
		return mav;
	}
	
	
	@RequestMapping(value= "/ask_update.do", method=RequestMethod.GET)
	protected ModelAndView updateAskBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/AskBoard/ask_updateForm.jsp","getAskBoard",dto); //�� �ϳ��϶� ����
		return mav;
	}
	
	@RequestMapping(value= "/ask_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProBoard(HttpSession session, HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)	throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // ���� �����̸� �ö����

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //����ð�
			String saveImg = now+img;
			String upPath = session.getServletContext().getRealPath("imgfile/askImg"); // �������� ������ �ϳ�����ڴ�.

			// ������ ������ �Ű� ���� . (���Ͼ���)
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file); // ���� ���� ����
				System.out.println("�������� ����! ");
			} catch (IOException e) {
				System.out.println("�������۽��ФФ� ");
				e.printStackTrace();
			}

			dto.setImg(saveImg);
		}else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}
		int res =askDAO.updateAsk(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		if(res>0) {
			mav.addObject("msg","�����Ǿ����ϴ�.");
			mav.addObject("url","ask_list.do");
		}else if(res==-1) {
			mav.addObject("msg","��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url","ask_update.do?no="+dto.getNo());
		}else {
			mav.addObject("msg","�����߻�... �����ڿ��� �����ּ���");
			mav.addObject("url","ask_content.do?no="+dto.getNo());
		}
		return mav;
	}	
}

