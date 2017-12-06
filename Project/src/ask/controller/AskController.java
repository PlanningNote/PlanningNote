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
	
			//파일받기 
		ModelAndView mav = new ModelAndView();
			
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0;
		
		//형변환 되려면 꼭 xml에 MultipartResolver 넣어야 됨.
		MultipartFile mf = mr.getFile("img"); 
		
		
		//파일 제대로왔는지 확인
		String img= mf.getOriginalFilename(); //실제 파일이름 올라와짐
		
		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //현재시간
			String saveImg = now+img;
		
		
		String upPath = session.getServletContext().getRealPath("/imgfile/askImg"); //파일즈라는 폴더를 하나만들겠다.
		
		
		//서버에 파일을 옮겨 적기 . (파일쓰기)
		File file = new File(upPath, saveImg);
		
		try{
			mf.transferTo(file); //실제 파일 전송
			System.out.println("파일전송 성공! ");
		}catch(IOException e) {
			System.out.println("파일전송실패ㅠㅠ ");
			e.printStackTrace();
		}
				
		dto.setImg(saveImg);
		}else {
			dto.setImg("");
		}
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);
			System.out.println("에러");
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
			mav.addObject("msg", "비정상적인 접근입니다. 메인으로 이동합니다.");
			mav.addObject("url","index.jsp");
		}
		
		int res = askDAO.deleteAsk(Integer.parseInt(no), pwd);
		
		if(res>0) {
			mav.addObject("msg", "삭제되었습니다.");
			mav.addObject("url","ask_list.do");
		}else if(res==-1) {
			mav.addObject("msg", "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("url","ask_delete.do");
		}else {
			mav.addObject("msg", "오류발생 관리자에게 문의주세요");
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
		ModelAndView mav = new ModelAndView("WEB-INF/AskBoard/ask_updateForm.jsp","getAskBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/ask_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProBoard(HttpSession session, HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)	throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // 실제 파일이름 올라와짐

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //현재시간
			String saveImg = now+img;
			String upPath = session.getServletContext().getRealPath("imgfile/askImg"); // 파일즈라는 폴더를 하나만들겠다.

			// 서버에 파일을 옮겨 적기 . (파일쓰기)
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file); // 실제 파일 전송
				System.out.println("파일전송 성공! ");
			} catch (IOException e) {
				System.out.println("파일전송실패ㅠㅠ ");
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
			mav.addObject("msg","수정되었습니다.");
			mav.addObject("url","ask_list.do");
		}else if(res==-1) {
			mav.addObject("msg","비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("url","ask_update.do?no="+dto.getNo());
		}else {
			mav.addObject("msg","오류발생... 관리자에게 문의주세요");
			mav.addObject("url","ask_content.do?no="+dto.getNo());
		}
		return mav;
	}	
}

