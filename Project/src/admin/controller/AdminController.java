package admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import FAQ.dao.FAQDAO;
import FAQ.dto.FAQDTO;
import ask.dao.AskDAO;
import ask.dto.AskDTO;
import member.dao.MemberDAO;
import member.dto.MemberDTO;
import notice.dao.NoticeDAO;
import notice.dto.NoticeDTO;
import plan.dao.PlanDAO;
import report.BReportDAO;
import report.BReportDTO;
import report.WReportDAO;
import report.WReportDTO;

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

	@Autowired
	private BReportDAO breportDAO;
	
	@Autowired
	private WReportDAO wreportDAO;
	
	@Autowired
	private PlanDAO planDAO;

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

	/* ================================================================== */
	/* 因走紫牌 */

	@RequestMapping(value = "/admin_noticeList.do")
	public ModelAndView listNotice(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<NoticeDTO> list = noticeDAO.listNotice(); // 亜閃神澗暗
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeList", list);
		mav.setViewName("WEB-INF/admin/noticeBoard/notice_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_noticeFind.do")
	public ModelAndView findotice(@RequestParam String search, @RequestParam String searchString) throws Exception {
		List<NoticeDTO> list = noticeDAO.findNotice(search, searchString);
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

	protected ModelAndView writeProBoard(HttpSession session, HttpServletRequest arg0, @ModelAttribute NoticeDTO dto,
			BindingResult result) throws Exception {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");
		String img = mf.getOriginalFilename();

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 薄仙獣娃
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/noticeImg");

			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file);
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}
			dto.setImg(saveImg);
		} else {
			dto.setImg("");
		}
		if (result.hasErrors()) {
			dto.setNo(0);
			dto.setCount(0);
		}

		noticeDAO.insertNotice(dto);
		return new ModelAndView("admin_noticeList.do");

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
	protected ModelAndView updateProNoticeBoard(HttpSession session, HttpServletRequest arg0,
			@ModelAttribute NoticeDTO dto, BindingResult result) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename();

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 薄仙獣娃
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/noticeImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

			// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file); // 叔薦 督析 穿勺
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}

			dto.setImg(saveImg);
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		int res = noticeDAO.updateNotice(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越呪舛拭 失因馬心柔艦陥.");
			mav.addObject("url", "admin_noticeList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("url", "admin_noticeUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("url", "admin_noticeContent.do?no=" + String.valueOf(dto.getNo()));
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
			mav.addObject("url", "admin_noticeList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("url", "admin_noticeDelete.do?no=" + no);
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("url", "admin_noticeContent.do?no=" + no);
		}
		mav.setViewName("message.jsp");
		return mav;
	}
	/* ========================================================= */
	/* FAQ */

	@RequestMapping(value = "/admin_FAQList.do")
	public ModelAndView listFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<FAQDTO> list = faqDAO.listFAQ(); // 亜閃神澗暗
		ModelAndView mav = new ModelAndView();
		mav.addObject("FAQList", list);
		mav.setViewName("WEB-INF/admin/FAQBoard/FAQ_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_FAQFind.do")
	public ModelAndView findFAQ(@RequestParam String search, @RequestParam String searchString) throws Exception {
		List<FAQDTO> list = faqDAO.findFAQ(search, searchString);
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
	protected ModelAndView writeProFAQ(HttpSession session, HttpServletRequest arg0, @ModelAttribute FAQDTO dto,
			BindingResult result) throws Exception {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename();

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 薄仙獣娃
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/faqImg");

			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file);
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}
			dto.setImg(saveImg);
		} else {
			dto.setImg("");
		}

		if (result.hasErrors()) {
			dto.setNo(0);
			dto.setCount(0);
		}

		faqDAO.insertFAQ(dto);
		return new ModelAndView("admin_FAQList.do");

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
	protected ModelAndView updateProFAQ(HttpSession session, HttpServletRequest arg0, @ModelAttribute FAQDTO dto,
			BindingResult result) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 薄仙獣娃
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/faqImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

			// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file); // 叔薦 督析 穿勺
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}

			dto.setImg(saveImg);
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		int res = faqDAO.updateFAQ(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越呪舛拭 失因馬心柔艦陥.");
			mav.addObject("url", "admin_FAQList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("url", "admin_FAQUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("url", "admin_FAQContent.do?no=" + String.valueOf(dto.getNo()));
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
			mav.addObject("url", "admin_FAQList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("url", "admin_FAQDelete.do?no=" + no);
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("url", "admin_FAQContent.do?no=" + no);
		}
		mav.setViewName("message.jsp");
		return mav;
	}
	/* ========================================================= */
	/* Q&A ===== ASK */

	@RequestMapping(value = "/admin_askList.do")
	public ModelAndView listAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<AskDTO> list = askDAO.listAsk();
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list);
		mav.setViewName("WEB-INF/admin/AskBoard/ask_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_askFind.do")
	public ModelAndView findAsk(@RequestParam String search, @RequestParam String searchString) throws Exception {
		List<AskDTO> list = askDAO.findAsk(search, searchString);
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list);
		mav.setViewName("WEB-INF/admin/AskBoard/ask_list.jsp");
		return mav;
	}

	@RequestMapping(value = "/admin_askContent.do")
	public ModelAndView contentAsk(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}

		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "content");

		ModelAndView mav = new ModelAndView("WEB-INF/admin/AskBoard/ask_content.jsp");
		mav.addObject("getAskBoard", dto);
		return mav;
	}

	@RequestMapping(value = "/admin_askDelete.do", method = RequestMethod.GET)
	public ModelAndView deleteFormAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/AskBoard/ask_delete.jsp");
	}

	@RequestMapping(value = "/admin_askDelete.do", method = RequestMethod.POST)
	protected ModelAndView deleteProAsk(HttpSession session, @RequestParam String no, @RequestParam String pwd,
			@RequestParam String board_pwd) throws Exception {
		if (no == null || pwd == null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		boolean result = memberDAO.checkPwd((String) session.getAttribute("mynick"), pwd);
		if (result) {
			int res = askDAO.admindeleteAsk(Integer.parseInt(no), board_pwd);
			if (res > 0) {
				mav.addObject("msg", "肢薦鞠醸柔艦陥.");
				mav.addObject("url", "admin_askList.do");
			} else {
				mav.addObject("msg", "神嫌降持... 淫軒切拭惟 庚税爽室推");
				mav.addObject("url", "admin_askContent.do?no=" + no);
			}

		} else {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("url", "admin_askDelete.do?no=" + no);
		}
		return mav;
	}

	@RequestMapping(value = "/admin_askReply.do", method = RequestMethod.GET)
	public ModelAndView replyAsk(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/AskBoard/ask_replyForm.jsp");
	}

	@RequestMapping(value = "/admin_askReply.do", method = RequestMethod.POST)
	protected ModelAndView replyProAsk(HttpSession session, HttpServletRequest arg0, @ModelAttribute AskDTO dto,
			BindingResult result) throws Exception {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 薄仙獣娃
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/askImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

			// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file); // 叔薦 督析 穿勺
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}

			dto.setImg(saveImg);
		} else {
			dto.setImg("");
		}
		askDAO.insertAsk(dto);
		return new ModelAndView("admin_askList.do");
	}

	@RequestMapping(value = "/admin_askUpdate.do", method = RequestMethod.GET)
	protected ModelAndView updateAsk(@RequestParam String no) throws Exception {
		if (no == null || no.trim().equals("")) {
			return null;
		}
		AskDTO dto = askDAO.getAskBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/admin/AskBoard/ask_updateForm.jsp", "getAskBoard", dto);

		return mav;
	}

	@RequestMapping(value = "/admin_askUpdate.do", method = RequestMethod.POST)
	protected ModelAndView updateProAsk(HttpSession session, HttpServletRequest arg0, @ModelAttribute AskDTO dto,
			BindingResult result) throws Exception {

		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;
		MultipartFile mf = mr.getFile("img");

		String img = mf.getOriginalFilename(); // 叔薦 督析戚硯 臣虞人像

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 薄仙獣娃
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/askImg"); // 督析綜虞澗 虹希研 馬蟹幻級畏陥.

			// 辞獄拭 督析聖 薪移 旋奄 . (督析床奄)
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file); // 叔薦 督析 穿勺
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}

			dto.setImg(saveImg);
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		System.out.println(dto.getNo());
		int res = askDAO.updateAsk(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "越呪舛拭 失因馬心柔艦陥.");
			mav.addObject("url", "admin_askList.do");
		} else if (res == -1) {
			mav.addObject("msg", "搾腔腰硲亜 堂携柔艦陥.陥獣 脊径背 爽室推");
			mav.addObject("url", "admin_askUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "神嫌降持");
			mav.addObject("url", "admin_askContent.do?no=" + String.valueOf(dto.getNo()));
		}
		mav.setViewName("message.jsp");
		return mav;
	}
	
	/* ===================================================== */
	/* 噺据重壱 */
	
	@RequestMapping(value = "/memReportForm.do")
	protected ModelAndView memReportForm(HttpSession session, HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		String suspecter = arg0.getParameter("nickname");
		String reporter = (String) session.getAttribute("mynick");
		ModelAndView mav = new ModelAndView();
		mav.addObject("suspecter", suspecter);
		mav.addObject("reporter", reporter);
		mav.setViewName("WEB-INF/admin/report/memReportForm.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/memReport.do")
	protected ModelAndView memReport(HttpSession session, HttpServletRequest arg0, @ModelAttribute WReportDTO dto,
			BindingResult result) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;

		MultipartFile mf = mr.getFile("img");
		String img = mf.getOriginalFilename();

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String saveImg = now + img;

			String upPath = session.getServletContext().getRealPath("/imgfile/report");
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file);
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}
			dto.setImg(saveImg);
		} else {
			dto.setImg("");
		}

		if (result.hasErrors()) {
			dto.setNo(0);
		}

		dto.setHandling("N");
		dto.setHandleday("");

		int res = wreportDAO.insertReport(dto); 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		mav.addObject("msg", "重壱羨呪亜 刃戟鞠醸柔艦陥.");
		mav.addObject("url", "comu_list.do");
		return mav;
	}
	
	@RequestMapping(value = "/goWReport.do")
	protected ModelAndView goWReport(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<WReportDTO> list = wreportDAO.listWReport();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/report/wlist.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/getWContent.do")
	protected ModelAndView getWContent(@RequestParam int no) throws Exception {
		WReportDTO dto = wreportDAO.getWContent(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("WEB-INF/admin/report/wcontent.jsp");
		return mav;
	}	
	
	@RequestMapping(value = "/wreportDelForm.do")
	protected ModelAndView brepwreportDelFormrtDelForm(@RequestParam int no,@RequestParam String suspecter) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("no",no);
		mav.addObject("nickname",suspecter);
		mav.setViewName("WEB-INF/admin/report/wdeleteForm.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/wreportDelete.do")
	protected ModelAndView wreportDelete(@RequestParam int no,@RequestParam String nickname,@RequestParam String content,@RequestParam String subject) throws Exception {
		String email = memberDAO.getEmail(nickname);
		wreportDAO.sendEmail(email, content, subject);	
		wreportDAO.updateReport(no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		mav.addObject("url","goWReport.do");
		mav.addObject("msg","噺据 肢薦 貢 五析 降勺 刃戟");
		return mav;
	}
	
	

	/* ===================================================== */
	/* 惟獣弘重壱 */
	@RequestMapping(value = "/reportPlanForm.do")
	protected ModelAndView reportPlanForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		String suspecter = arg0.getParameter("suspecter");
		String reporter = arg0.getParameter("reporter");
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.addObject("suspecter", suspecter);
		mav.addObject("reporter", reporter);
		mav.setViewName("WEB-INF/admin/report/reportForm.jsp");
		return mav;
	}

	@RequestMapping(value = "/reportPlan.do")
	protected ModelAndView reportPlan(HttpSession session, HttpServletRequest arg0, @ModelAttribute BReportDTO dto,
			BindingResult result) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) arg0;

		MultipartFile mf = mr.getFile("img");
		String img = mf.getOriginalFilename();

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String saveImg = now + img;

			String upPath = session.getServletContext().getRealPath("/imgfile/report");
			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file);
				System.out.println("督析穿勺 失因! ");
			} catch (IOException e) {
				System.out.println("督析穿勺叔鳶ばば ");
				e.printStackTrace();
			}
			dto.setImg(saveImg);
		} else {
			dto.setImg("");
		}

		if (result.hasErrors()) {
			dto.setNo(0);
			dto.setBoard_no(0);
		}

		dto.setHandling("N");
		dto.setHandleday("");

		int res = breportDAO.insertReport(dto);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		mav.addObject("msg", "重壱羨呪亜 刃戟鞠醸柔艦陥.");
		mav.addObject("url", "listPlanA.do");
		return mav;
	}

	@RequestMapping(value = "/goBReport.do")
	protected ModelAndView goBReport(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<BReportDTO> list = breportDAO.listBReport();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/report/blist.jsp");
		return mav;
	}

	@RequestMapping(value = "/getBContent.do")
	protected ModelAndView getBContent(@RequestParam int no) throws Exception {
		BReportDTO dto = breportDAO.getBContent(no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("WEB-INF/admin/report/bcontent.jsp");
		return mav;
	}	
	
	@RequestMapping(value = "/breportDelForm.do")
	protected ModelAndView breportDelForm(@RequestParam int no, @RequestParam int board_no, @RequestParam String suspecter) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("board_no",board_no);
		mav.addObject("nickname",suspecter);
		mav.addObject("no",no);
		mav.setViewName("WEB-INF/admin/report/deleteForm.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/breportDelete.do")
	protected ModelAndView breportDelete(@RequestParam int board_no,@RequestParam int no, @RequestParam String nickname,@RequestParam String content,@RequestParam String subject) throws Exception {
		planDAO.deletePlan(board_no);  // 析舛肢薦
		String email = memberDAO.getEmail(nickname);
		breportDAO.sendEmail(email, content, subject);	
		breportDAO.updateReport(no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("message.jsp");
		mav.addObject("url","goBReport.do");
		mav.addObject("msg","惟獣弘 肢薦 貢 五析 降勺 刃戟");
		return mav;
	}
	

}
