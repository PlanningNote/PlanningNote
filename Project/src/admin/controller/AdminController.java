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

	// 淫軒切 五昔鉢檎
	@RequestMapping(value = "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}

	/* 噺据 淫軒 */

	// 噺据 鯉系
	@RequestMapping(value = "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/member/list.jsp");
		return mav;
	}

	// 噺据 伊事
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

	// 噺据 肢薦馬奄穿 搾腔腰硲 溌昔聖 是廃 form
	@RequestMapping(value = "/admin_memberDeleteForm.do")
	public ModelAndView memberDeleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.setViewName("WEB-INF/admin/member/deleteForm.jsp");
		return mav;
	}

	// 噺据 肢薦馬奄穿 搾腔腰硲 溌昔
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
				msg = "噺据 肢薦 失因... 噺据鯉系凪戚走稽 戚疑杯艦陥.";
				url = "admin_memberList.do";
			} else {
				msg = "噺据 肢薦 叔鳶... 噺据鯉系凪戚走稽 戚疑杯艦陥.";
				url = "admin_memberList.do";
			}
		} else {
			msg = "搾腔腰硲亜 堂携柔艦陥. 陥獣 脊径背爽室推~!";
			url = "admin_memberDeleteForm.do?no=" + no;
		}
		arg0.setAttribute("msg", msg);
		arg0.setAttribute("url", url);
		return new ModelAndView("message.jsp");
	}

	/* 因走紫牌 */

	@RequestMapping(value = "/admin_noticeList.do")
	public ModelAndView listNotice(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<NoticeDTO> list = noticeDAO.listNotice(); // 亜閃神澗暗
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeList", list);
		mav.setViewName("WEB-INF/admin/noticeBoard/notice_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_noticeWrite.do", method = RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/noticeBoard/notice_writeForm.jsp");
	}

	@RequestMapping(value = "/admin_noticeWrite.do", method = RequestMethod.POST)

	protected ModelAndView writeProBoard(HttpServletRequest arg0, @ModelAttribute NoticeDTO dto, BindingResult result)
			throws Exception {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); 

		/*
		 * if(filename==null || filename.trim().equals("")) return; //督析穣稽球亜照鞠澗暗
		 */
		// 督析戚 閤焼然陥檎 井稽走舛 session? request ?

		HttpSession session = arg0.getSession();
		String upPath = session.getServletContext().getRealPath("imgfile/noticeImg"); 
		System.out.println(upPath);
		
		File file = new File(upPath, img);

		try {
			mf.transferTo(file); 
			System.out.println("督析穿勺 失因! ");
		} catch (IOException e) {
			System.out.println("督析穿勺叔鳶ばば ");
			e.printStackTrace();
		}

		if (result.hasErrors()) { 
			dto.setNo(0);
			dto.setCount(0);
		}

		dto.setImg(img);
		noticeDAO.insertNotice(dto);
		return new ModelAndView("redirect:admin_noticeList.do");

	}

	@RequestMapping(value = "/admin_noticeContent.do")
	public ModelAndView contentNotice(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}

		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "content");

		ModelAndView mav = new ModelAndView("WEB-INF/admin/noticeBoard/notice_content.jsp");
		mav.addObject("getNoticeBoard", dto);
		return mav;
	}

	@RequestMapping(value = "/admin_noticeUpdate.do", method = RequestMethod.GET)
	protected ModelAndView updateNoticeBoard(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}
		NoticeDTO dto = noticeDAO.getNoticeBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/noticeBoard/notice_updateForm.jsp", "getNoticeBoard", dto);
		return mav;
	}

	@RequestMapping(value = "/admin_noticeUpdate.do", method = RequestMethod.POST)
	protected ModelAndView updateProNoticeBoard(HttpServletRequest arg0, @ModelAttribute NoticeDTO dto,
			BindingResult result) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img != null && !(img.trim().equals(""))) {
			HttpSession session = arg0.getSession();
			String upPath = session.getServletContext().getRealPath("imgfile/noticeImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

			// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
			File file = new File(upPath, img);

			try {
				mf.transferTo(file); // 叔薦 督析 穿勺
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}

			dto.setImg(img);
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		System.out.println(dto.getNo());
		int res = noticeDAO.updateNotice(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越呪舛拭 失因馬心柔艦陥.");
			mav.addObject("location", "admin_noticeList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("location", "admin_noticeUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("location", "admin_noticeContent.do?no=" + String.valueOf(dto.getNo()));
		}
		mav.setViewName("message.jsp");
		return mav;

	}

	@RequestMapping(value = "/admin_noticeDelete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/noticeBoard/notice_delete.jsp");
	}

	@RequestMapping(value = "/admin_noticeDelete.do", method = RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if (no == null || pwd == null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}

		int res = noticeDAO.deleteNotice(Integer.parseInt(no), pwd);
		
		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越肢薦拭 失因馬心柔艦陥.");
			mav.addObject("location", "admin_noticeList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("location", "admin_noticeDelete.do?no=" + no);
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("location", "admin_noticeContent.do?no=" + no);
		}
		mav.setViewName("message.jsp");
		return mav;
	}

	/* FAQ */

	@RequestMapping(value = "/admin_FAQList.do")
	public ModelAndView listFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<FAQDTO> list = faqDAO.listFAQ(); // 亜閃神澗暗
		ModelAndView mav = new ModelAndView();
		mav.addObject("FAQList", list);
		mav.setViewName("WEB-INF/admin/FAQBoard/FAQ_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_FAQWrite.do", method = RequestMethod.GET)
	protected ModelAndView writeFormFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_writeForm.jsp");
	}

	@RequestMapping(value = "/admin_FAQWrite.do", method = RequestMethod.POST)
		protected ModelAndView writeProFAQ(HttpServletRequest arg0, @ModelAttribute FAQDTO dto, BindingResult result)
			throws Exception {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); 

		/*
		 * if(filename==null || filename.trim().equals("")) return; //督析穣稽球亜照鞠澗暗
		 */
		// 督析戚 閤焼然陥檎 井稽走舛 session? request ?

		HttpSession session = arg0.getSession();
		String upPath = session.getServletContext().getRealPath("imgfile/faqImg"); 
		System.out.println(upPath);
		
		File file = new File(upPath, img);

		try {
			mf.transferTo(file); 
			System.out.println("督析穿勺 失因! ");
		} catch (IOException e) {
			System.out.println("督析穿勺叔鳶ばば ");
			e.printStackTrace();
		}

		if (result.hasErrors()) { 
			dto.setNo(0);
			dto.setCount(0);
		}

		dto.setImg(img);
		faqDAO.insertFAQ(dto);
		return new ModelAndView("redirect:admin_FAQList.do");

	}

	@RequestMapping(value = "/admin_FAQContent.do")
	public ModelAndView contentFAQ(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}

		FAQDTO dto = faqDAO.getFAQBoard(Integer.parseInt(no), "content");

		ModelAndView mav = new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_content.jsp");
		mav.addObject("getFAQBoard", dto);
		return mav;
	}

	@RequestMapping(value = "/admin_FAQUpdate.do", method = RequestMethod.GET)
	protected ModelAndView updateFAQ(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}
		FAQDTO dto = faqDAO.getFAQBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_updateForm.jsp", "getFAQBoard", dto); 
																												
		return mav;
	}

	@RequestMapping(value = "/admin_FAQUpdate.do", method = RequestMethod.POST)
	protected ModelAndView updateProFAQ(HttpServletRequest arg0, @ModelAttribute FAQDTO dto,
			BindingResult result) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img != null && !(img.trim().equals(""))) {
			HttpSession session = arg0.getSession();
			String upPath = session.getServletContext().getRealPath("imgfile/faqImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

			// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
			File file = new File(upPath, img);

			try {
				mf.transferTo(file); // 叔薦 督析 穿勺
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}

			dto.setImg(img);
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		int res = faqDAO.updateFAQ(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越呪舛拭 失因馬心柔艦陥.");
			mav.addObject("location", "admin_FAQList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("location", "admin_FAQUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("location", "admin_FAQContent.do?no=" + String.valueOf(dto.getNo()));
		}
		mav.setViewName("message.jsp");
		return mav;

	}

	@RequestMapping(value = "/admin_FAQDelete.do", method = RequestMethod.GET)
	public ModelAndView deleteFormFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/FAQBoard/FAQ_delete.jsp");
	}

	@RequestMapping(value = "/admin_FAQDelete.do", method = RequestMethod.POST)
	protected ModelAndView deleteProFAQ(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if (no == null || pwd == null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		int res = faqDAO.deleteFAQ(Integer.parseInt(no), pwd);
		
		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越肢薦拭 失因馬心柔艦陥.");
			mav.addObject("location", "admin_FAQList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("location", "admin_FAQDelete.do?no=" + no);
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("location", "admin_FAQContent.do?no=" + no);
		}
		mav.setViewName("message.jsp");
		return mav;
	}

	/* Q&A */

	@RequestMapping(value = "/admin_askList.do")
	public ModelAndView listAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		System.out.println("淫軒切admin_askList食奄澗 尽珠");
		List<AskDTO> list = askDAO.listAsk();
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list);
		mav.setViewName("WEB-INF/admin/AskBoard/ask_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_askContent.do")
	public ModelAndView contentAsk(@RequestParam String no) throws Exception {
		// @RequestParam Map<String, String> params
		// Set<Entry<String, String>> set = params.entrySet();
		// for(Entry<String,String> entry = set){
		// System.out.println(entry.getKey() + "=" + entry.getValues());
		// }
		if (no == null || no.trim().equals("")) {
			return null;
		}

		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "content");

		ModelAndView mav = new ModelAndView("WEB-INF/admin/AskBoard/ask_content.jsp");
		mav.addObject("getAskBoard", dto);
		return mav;
	}

	@RequestMapping(value = "/admin_askWrite.do", method = RequestMethod.GET)
	protected ModelAndView writeFormAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_writeForm.jsp");
	}

	@RequestMapping(value = "/admin_askWrite.do", method = RequestMethod.POST)
	protected ModelAndView writeProAsk(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)
			throws Exception {
		// 戚薦 arg2稽 dto 廃腰拭 葵 公閤焼紳陥.
		if (result.hasErrors()) { // 拭君亜 降持馬澗 戚政 掻 馬蟹亜 String生稽 閤焼尽澗汽 null葵戚 級嬢尽澗汽 益葵聖 int莫生稽 切疑莫痕莫 獣徹檎辞 神嫌亜 降持廃陥.
			dto.setNo(0);

		}

		// 督析閤奄
		ModelAndView mav = new ModelAndView();

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;

		// 莫痕発 鞠形檎 伽 xml拭 MultipartResolver 隔嬢醤 喫.
		MultipartFile mf = mr.getFile("img");

		// 督析 薦企稽尽澗走 溌昔
		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img == null || img.trim().equals(""))
			return null; // 督析穣稽球亜照鞠澗暗

		// 督析戚 閤焼然陥檎 井稽走舛 session? request ?

		HttpSession session = arg0.getSession();
		String upPath = session.getServletContext().getRealPath("/imgfile/askImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

		// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
		File file = new File(upPath, img);

		try {
			mf.transferTo(file); // 叔薦 督析 穿勺
			System.out.println("督析穿勺 失因! ");
		} catch (IOException e) {
			System.out.println("督析穿勺叔鳶ばば ");
			e.printStackTrace();
		}

		dto.setImg(img);
		askDAO.insertAsk(dto);
		return new ModelAndView("admin_askList.do");
	}

	@RequestMapping(value = "/admin_askDelete.do", method = RequestMethod.GET)
	public ModelAndView deleteFormAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_delete.jsp");
	}

	@RequestMapping(value = "/admin_askDelete.do", method = RequestMethod.POST)
	protected ModelAndView deleteProAsk(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if (no == null || pwd == null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}

		int res = askDAO.deleteAsk(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			return new ModelAndView("redirect:admin_askList.do");
		} else if (res == -1) {
			JOptionPane.showMessageDialog(null, "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("no", no);
			mav.setViewName("admin_askDelete.do");
		} else {
			JOptionPane.showMessageDialog(null, "神嫌降持");
			mav.addObject("no", no);
			mav.setViewName("admin_askContent.do");
		}
		return mav;
	}

	@RequestMapping(value = "/admin_askReply.do", method = RequestMethod.GET)
	public ModelAndView replyAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/AskBoard/ask_replyForm.jsp");
	}

	@RequestMapping(value = "/admin_askReply.do", method = RequestMethod.POST)
	protected ModelAndView replyProAsk(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)
			throws Exception {
		if (result.hasErrors()) { // 拭君亜 降持馬澗 戚政 掻 馬蟹亜 String生稽 閤焼尽澗汽 null葵戚 級嬢尽澗汽 益葵聖 int莫生稽 切疑莫痕莫 獣徹檎辞 神嫌亜 降持廃陥.
			dto.setNo(0);
			dto.setRe_group(0);
			dto.setRe_level(0);
			dto.setRe_step(0);
		}
		// 督析閤奄
		ModelAndView mav = new ModelAndView();

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;

		// 莫痕発 鞠形檎 伽 xml拭 MultipartResolver 隔嬢醤 喫.
		MultipartFile mf = mr.getFile("img");

		// 督析 薦企稽尽澗走 溌昔
		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img == null || img.trim().equals(""))
			return null; // 督析穣稽球亜照鞠澗暗

		// 督析戚 閤焼然陥檎 井稽走舛 session? request ?

		HttpSession session = arg0.getSession();
		String upPath = session.getServletContext().getRealPath("/imgfile/askImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

		// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
		File file = new File(upPath, img);

		try {
			mf.transferTo(file); // 叔薦 督析 穿勺
			System.out.println("督析穿勺 失因! ");
		} catch (IOException e) {
			System.out.println("督析穿勺叔鳶ばば ");
			e.printStackTrace();
		}

		dto.setImg(img);
		askDAO.insertAsk(dto);
		return new ModelAndView("redirect:admin_askList.do");
	}

	@RequestMapping(value = "/admin_askUpdate.do", method = RequestMethod.GET)
	protected ModelAndView updateAsk(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}
		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/AskBoard/ask_updateForm.jsp", "getAskBoard", dto); // 葵 馬蟹析凶
																												// 亜管
		return mav;
	}

	@RequestMapping(value = "/admin_askUpdate.do", method = RequestMethod.POST)
	protected ModelAndView updateProAsk(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			dto.setNo(0);
		}

		int res = askDAO.updateAsk(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			return new ModelAndView("redirect:admin_askList.do");
		} else if (res == -1) {
			JOptionPane.showMessageDialog(null, "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_askUpdate.do");
		} else {
			JOptionPane.showMessageDialog(null, "神嫌降持");
			mav.addObject("no", dto.getNo());
			mav.setViewName("admin_askContent.do");
		}
		return mav;
	}

}
