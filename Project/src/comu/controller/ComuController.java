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
		List<ComuDTO> list = comuDAO.listComu(); // 가져오는거
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
		
		
		//파일 제대로왔는지 확인
		String img= mf.getOriginalFilename(); //실제 파일이름 올라와짐
		
		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());  //현재시간
			String saveImg = now+img;
		
		
		String upPath = session.getServletContext().getRealPath("/imgfile/comuImg"); //파일즈라는 폴더를 하나만들겠다.
		
		
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
			dto.setCount(0);
		}
		
		
	    comuDAO.insertComu(dto);
	    mav.setViewName("message.jsp");
	    mav.addObject("url","comu_list.do");
	    mav.addObject("msg", "글올리기 성공하였습니다.");
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
		ModelAndView mav = new ModelAndView("WEB-INF/ComuBoard/comu_updateForm.jsp","getComuBoard",dto); //값 하나일때 가능
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
			mav.addObject("msg","글수정 성공하셨습니다.");
		}else if(res==-1) {
			mav.addObject("url","comu_update.do?no="+dto.getNo());
			mav.addObject("msg","비밀번호가 틀렸습니다.다시 입력해 주세요");
		}else {
			mav.addObject("url","comu_content.do?no="+dto.getNo());
			mav.addObject("msg","오류발생... 관리자에게 문의주세요");
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
			mav.addObject("msg","글삭제 성공하셨습니다.");
		}else if(res==-1) {
			mav.addObject("url","comu_delete.do");
			mav.addObject("msg","비밀번호가 틀렸습니다.다시 입력해 주세요");
		}else {
			mav.addObject("url","comu_content.do?no="+no);
			mav.addObject("msg","오류발생 관리자에게 문의주세요");
		}		
		return mav;
	}
	
	@RequestMapping(value= "/insertComuReply.do")
	public ModelAndView insertComuReply(@RequestParam int comu_no, @RequestParam String writer, @RequestParam String content) throws Exception {
		comuDAO.insertReply(comu_no, writer, content);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		mav.addObject("msg","댓글을 추가하였습니다.");
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













