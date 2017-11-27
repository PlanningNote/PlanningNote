package admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import FAQ.dao.FAQDAO;
import FAQ.dto.FAQDTO;
import ask.dao.AskDAO;
import ask.dto.AskDTO;
import member.dao.MemberDAO;
import member.dto.MemberDTO;
import notice.dao.NoticeDAO;
import notice.dto.NoticeDTO;

@Controller
public class AdminController {
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private NoticeDAO noticeDAO;

	@Autowired
	private FAQDAO faqDAO;
	
	@Autowired
	private AskDAO askDAO;
	
	// 관리자 메인화면
	@RequestMapping(value = "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}

	/* 회원 관리 */

	// 회원 목록
	@RequestMapping(value = "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/member/list.jsp");
		return mav;
	}

	// 회원 검색
	@RequestMapping(value = "/admin_memberFind.do")
	public ModelAndView memberFind(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String search = arg0.getParameter("search");
		String searchString = arg0.getParameter("searchString");

		ModelAndView mav = new ModelAndView();
		if (search != null && searchString != null) {
			List<MemberDTO> list = memberDAO.fineMember(search, searchString);
			mav.addObject("getList", list);
		}
		mav.setViewName("WEB-INF/admin/member/find.jsp");
		return mav;
	}

	// 회원 삭제하기전 비밀번호 확인을 위한 form
	@RequestMapping(value = "/admin_memberDeleteForm.do")
	public ModelAndView memberDeleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.setViewName("WEB-INF/admin/member/deleteForm.jsp");
		return mav;
	}

	// 회원 삭제하기전 비밀번호 확인
	@RequestMapping(value = "/admin_deleteMember.do")
	public ModelAndView memberDeleteCheck(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String pwd = arg0.getParameter("pwd");
		int no = Integer.parseInt(arg0.getParameter("no"));
		boolean result = memberDAO.checkPwd("admin", pwd);
		ModelAndView mav = new ModelAndView();
		String msg = null, url = null;
		if (result) {
			int res = memberDAO.deleteMember(no);
			if (res > 0) {
				msg = "회원 삭제 성공... 회원목록페이지로 이동합니다.";
				url = "admin_memberList.do";
			} else {
				msg = "회원 삭제 실패... 회원목록페이지로 이동합니다.";
				url = "admin_memberList.do";
			}
		} else {
			msg = "비밀번호가 틀렸습니다. 다시 입력해주세요~!";
			url = "admin_memberDeleteForm.do?no=" + no;
		}
		arg0.setAttribute("msg", msg);
		arg0.setAttribute("url", url);
		return new ModelAndView("message.jsp");
	}

	/* 공지사항 */

	@RequestMapping(value = "/admin_noticeList.do")
	public ModelAndView listNotice(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<NoticeDTO> list = noticeDAO.listNotice(); // 가져오는거
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeList", list);
		mav.setViewName("WEB-INF/admin/noticeBoard/notice_list.jsp");
		return mav;
	}
	
	@RequestMapping(value= "/admin_noticeWrite.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/noticeBoard/notice_writeForm.jsp");
	}
	
	@RequestMapping(value= "/admin_noticeWrite.do",method=RequestMethod.POST)
	
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
		return new ModelAndView("redirect:admin_noticeList.do");
		
	
		
		
			
		}
	
	
	@RequestMapping(value= "/admin_noticeContent.do")
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
		
		ModelAndView mav = new ModelAndView("WEB-INF/admin/noticeBoard/notice_content.jsp");
		mav.addObject("getNoticeBoard", dto);
		return mav;
	}
	
	@RequestMapping(value= "/admin_noticeUpdate.do", method=RequestMethod.GET)
	protected ModelAndView updateNoticeBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/noticeBoard/notice_updateForm.jsp","getNoticeBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/admin_noticeUpdate.do", method=RequestMethod.POST)
	protected ModelAndView updateProNoticeBoard(HttpServletRequest arg0, @ModelAttribute NoticeDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =noticeDAO.updateNotice(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:admin_noticeList.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_noticeUpdate.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_noticeContent.do");
		}
		return mav;
	}
	
	@RequestMapping(value= "/admin_noticeDelete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/noticeBoard/notice_delete.jsp");
	}
	
	@RequestMapping(value= "/admin_noticeDelete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = noticeDAO.deleteNotice(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:admin_noticeList.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", no);
			mav.setViewName("admin_noticeDelete.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", no);
			mav.setViewName("admin_noticeContent.do");
		}		
		return mav;
	}
	
	/* FAQ */	
		
		@RequestMapping(value="/admin_FAQList.do")
	public ModelAndView listFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<FAQDTO> list = faqDAO.listFAQ(); // 가져오는거
		ModelAndView mav = new ModelAndView();
		mav.addObject("FAQList", list);
		mav.setViewName("WEB-INF/admin/FAQBoard/FAQ_list.jsp");
		return mav;
	}
	

	
	@RequestMapping(value= "/admin_FAQWrite.do", method=RequestMethod.GET)
	protected ModelAndView writeFormFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_writeForm.jsp");
	}
	
	@RequestMapping(value= "/admin_FAQWrite.do",method=RequestMethod.POST)
	protected ModelAndView writeProFAQ(HttpServletRequest arg0, @ModelAttribute FAQDTO dto, BindingResult result)
			throws Exception {
		//이제 arg2로 dto 한번에 값 못받아온다.		
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);		
			dto.setCount(0);
		}
		faqDAO.insertFAQ(dto);
		return new ModelAndView("redirect:admin_FAQList.do");
	}
	
	@RequestMapping(value= "/admin_FAQContent.do")
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
		
		ModelAndView mav = new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_content.jsp");
		mav.addObject("getFAQBoard", dto);
		return mav;
	}
	
	@RequestMapping(value= "/admin_FAQUpdate.do", method=RequestMethod.GET)
	protected ModelAndView updateFAQ(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		FAQDTO dto = faqDAO.getFAQBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_updateForm.jsp","getFAQBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/admin_FAQUpdate.do", method=RequestMethod.POST)
	protected ModelAndView updateProFAQ(HttpServletRequest arg0, @ModelAttribute FAQDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =faqDAO.updateFAQ(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:admin_FAQList.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_FAQUpdate.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_FAQContent.do");
		}
		return mav;
	}
	
	@RequestMapping(value= "/admin_FAQDelete.do", method=RequestMethod.GET)
	public ModelAndView deleteFormFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_delete.jsp");
	}
	
	@RequestMapping(value= "/admin_FAQDelete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProFAQ(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = faqDAO.deleteFAQ(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:admin_FAQList.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", no);
			mav.setViewName("admin_FAQDelete.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", no);
			mav.setViewName("admin_FAQContent.do");
		}		
		return mav;
	}

	
	/* Q&A */
	

	@RequestMapping(value="/admin_askList.do")
	public ModelAndView listAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		System.out.println("여기는 왔쇼");
		List<AskDTO> list = askDAO.listAsk();
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list);
		mav.setViewName("WEB-INF/admin/AskBoard/ask_list.jsp");		
		return mav;		
	}
	
	@RequestMapping(value= "/admin_askContent.do")
	public ModelAndView contentAsk(@RequestParam String no) throws Exception {
		//@RequestParam Map<String, String> params
		// Set<Entry<String, String>> set = params.entrySet();
		// for(Entry<String,String> entry = set){
		// System.out.println(entry.getKey() + "=" + entry.getValues());
		// }
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/admin/AskBoard/ask_content.jsp");
		mav.addObject("getAskBoard", dto);
		return mav;
	}

	
	@RequestMapping(value= "/admin_askWrite.do", method=RequestMethod.GET)
	protected ModelAndView writeFormAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_writeForm.jsp");
	}
	@RequestMapping(value= "/admin_askWrite.do",method=RequestMethod.POST)
	protected ModelAndView writeProAsk(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)
			throws Exception {
		//이제 arg2로 dto 한번에 값 못받아온다.		
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);
			
		}
		
		 askDAO.insertAsk(dto);
		return new ModelAndView("redirect:admin_askList.do");
	}
	
	@RequestMapping(value= "/admin_askDelete.do", method=RequestMethod.GET)
	public ModelAndView deleteFormAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_delete.jsp");
	}
	
	@RequestMapping(value= "/admin_askDelete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProAsk(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = askDAO.deleteAsk(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:admin_askList.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", no);
			mav.setViewName("admin_askDelete.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", no);
			mav.setViewName("admin_askContent.do");
		}		
		return mav;
	}
	
	@RequestMapping(value= "/admin_askReply.do", method=RequestMethod.GET)
	public ModelAndView replyAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/AskBoard/ask_replyForm.jsp");
	}
	
	@RequestMapping(value= "/admin_askReply.do", method=RequestMethod.POST)
	protected ModelAndView replyProAsk(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result) throws Exception {
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);
			dto.setRe_group(0);
			dto.setRe_level(0);
			dto.setRe_step(0);
		}		
		
		askDAO.insertAsk(dto);
		return new ModelAndView("redirect:admin_askList.do");
	}
	
	
	
	
	@RequestMapping(value= "/admin_askUpdate.do", method=RequestMethod.GET)
	protected ModelAndView updateAsk(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/AskBoard/ask_updateForm.jsp","getAskBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/admin_askUpdate.do", method=RequestMethod.POST)
	protected ModelAndView updateProAsk(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =askDAO.updateAsk(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:admin_askList.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_askUpdate.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_askContent.do");
		}
		return mav;
	}

}
