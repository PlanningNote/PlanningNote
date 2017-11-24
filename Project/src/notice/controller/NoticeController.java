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
	

	
	@RequestMapping(value= "/notice_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/NoticeBoard/notice_writeForm.jsp");
	}
	
	@RequestMapping(value= "/notice_write.do",method=RequestMethod.POST)
	
	protected ModelAndView writeProBoard(HttpServletRequest arg0, @ModelAttribute NoticeDTO dto, BindingResult result)
			throws Exception {
		
		//파일받기
	MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0;
		//파일 제대로왔는지 확인
	
	//형변환 되려면 꼭 xml에 MultipartResolver 넣어야 됨.
	MultipartFile mf = mr.getFile("img");
	
				String filename= mf.getOriginalFilename(); //실제 파일이름 올라와짐
				
				/*if(filename==null || filename.trim().equals(""))
					return; //파일업로드가안되는거 
				*/
				//파일이 받아졌다면 경로지정 session? request ? 
				
				HttpSession session = arg0.getSession();
				String upPath = session.getServletContext().getRealPath("/files/notice"); //파일즈라는 폴더를 하나만들겠다.
				System.out.println(upPath);
				
				//서버에 파일을 옮겨 적기 . (파일쓰기)
				File file = new File(upPath, filename);
				
				try{
					mf.transferTo(file); //실제 파일 전송
					System.out.println("파일전송 성공! ");
				}catch(IOException e) {
					System.out.println("파일전송실패ㅠㅠ ");
					e.printStackTrace();
				}
				
				
		//이제 arg2로 dto 한번에 값 못받아온다.		
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);		
			dto.setCount(0);
		}
		noticeDAO.insertNotice(dto);
		return new ModelAndView("redirect:notice_list.do");
		
	
		
		
			
		}
	
	
	@RequestMapping(value= "/notice_content.do")
	public ModelAndView contentNotice(@RequestParam String no) throws Exception {
		//@RequestParam Map<String, String> params
		// Set<Entry<String, String>> set = params.entrySet();
		// for(Entry<String,String> entry = set){
		// System.out.println(entry.getKey() + "=" + entry.getValues());
		// }
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/NoticeBoard/notice_content.jsp");
		mav.addObject("getNoticeBoard", dto);
		return mav;
	}
	
	@RequestMapping(value= "/notice_update.do", method=RequestMethod.GET)
	protected ModelAndView updateNoticeBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/NoticeBoard/notice_updateForm.jsp","getNoticeBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/notice_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProNoticeBoard(HttpServletRequest arg0, @ModelAttribute NoticeDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =noticeDAO.updateNotice(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:notice_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", dto.getNo());
			mav.setViewName("notice_update.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", dto.getNo());
			mav.setViewName("notice_content.do");
		}
		return mav;
	}

	
	/*@RequestMapping(value= "/notice_update.do", method=RequestMethod.GET)
	protected ModelAndView updateBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/NoticeBoard/notice_updateForm.jsp","getNoticeBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/notice_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProBoard(HttpServletRequest arg0, @ModelAttribute NoticeDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =noticeDAO.updateNotice(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:notice_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", dto.getNo());
			mav.setViewName("notice_update.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", dto.getNo());
			mav.setViewName("notice_content.do");
		}
		return mav;
	}
	*/
	@RequestMapping(value= "/notice_delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/NoticeBoard/notice_delete.jsp");
	}
	
	@RequestMapping(value= "/notice_delete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = noticeDAO.deleteNotice(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:notice_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", no);
			mav.setViewName("notice_delete.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", no);
			mav.setViewName("notice_content.do");
		}		
		return mav;
	}
	/*
	@RequestMapping(value="/board_list.do")
	public ModelAndView listBoard(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		List<BoardDBBean> list = boardDAO.listBoard();
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", list);
		mav.setViewName("WEB-INF/board/list.jsp");
		return mav;
	}
	
	@RequestMapping(value="/board_content.do")
	public ModelAndView contentBoard(@RequestParam String num) throws Exception {
		//(@RequestParam Map<String, String> params)
		//Set<Entry<String, String>> set = params.entrySet();
		//for(Entry<String, String> entry : set){
		//	System.out.println(entry.getKey() + " = " + entry.getValues());
		//}
		if (num==null || num.trim().equals("")) {
			return null;
		}
				
		BoardDBBean dto = boardDAO.getBoard(Integer.parseInt(num));
		
		ModelAndView mav = new ModelAndView("WEB-INF/board/content.jsp");
		mav.addObject("getBoard", dto);
		return mav;
	}
	
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/board/writeForm.jsp");
	}
	
	@RequestMapping(value="/board_write.do", method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpServletRequest arg0, 
			@ModelAttribute BoardDBBean dto, BindingResult result) throws Exception {
		//BoardDBBean dto = (BoardDBBean)arg2;
		if (result.hasErrors()) {
			dto.setNum(0);
		}
		dto.setIp(arg0.getRemoteAddr());
		
		boardDAO.insertBoard(dto);
		return new ModelAndView("redirect:board_list.do");
	}
*/
}













