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
import report.BReportDAO;
import report.BReportDTO;

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

	// ������ ����ȭ��
	@RequestMapping(value = "/admin_main.do")
	public ModelAndView adminMain(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/admin/admin_main.jsp");
	}

	/* ȸ�� ���� */

	// ȸ�� ���
	@RequestMapping(value = "/admin_memberList.do")
	public ModelAndView memberList(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDTO> list = memberDAO.listMember();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/member/list.jsp");
		return mav;
	}

	// ȸ�� �˻�
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

	// ȸ�� �����ϱ��� ��й�ȣ Ȯ���� ���� form
	@RequestMapping(value = "/admin_memberDeleteForm.do")
	public ModelAndView memberDeleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.setViewName("WEB-INF/admin/member/deleteForm.jsp");
		return mav;
	}

	// ȸ�� �����ϱ��� ��й�ȣ Ȯ��
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
				msg = "ȸ�� ���� ����... ȸ������������� �̵��մϴ�.";
				url = "admin_memberList.do";
			} else {
				msg = "ȸ�� ���� ����... ȸ������������� �̵��մϴ�.";
				url = "admin_memberList.do";
			}
		} else {
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���~!";
			url = "admin_memberDeleteForm.do?no=" + no;
		}
		arg0.setAttribute("msg", msg);
		arg0.setAttribute("url", url);
		return new ModelAndView("message.jsp");
	}

	/* ================================================================== */
	/* �������� */

	@RequestMapping(value = "/admin_noticeList.do")
	public ModelAndView listNotice(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<NoticeDTO> list = noticeDAO.listNotice(); // �������°�
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
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/noticeImg");

			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file);
				System.out.println("�������� ����! ");
			} catch (IOException e) {
				System.out.println("�������۽��ФФ� ");
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
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/noticeImg"); // �������� ������ �ϳ�����ڴ�.

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
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		int res = noticeDAO.updateNotice(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "�ۼ����� �����Ͽ����ϴ�.");
			mav.addObject("url", "admin_noticeList.do");
		} else if (res == -1) {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url", "admin_noticeUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "�����߻�");
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
			mav.addObject("msg", "�ۻ����� �����Ͽ����ϴ�.");
			mav.addObject("url", "admin_noticeList.do");
		} else if (res == -1) {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url", "admin_noticeDelete.do?no=" + no);
		} else {
			mav.addObject("msg", "�����߻�");
			mav.addObject("url", "admin_noticeContent.do?no=" + no);
		}
		mav.setViewName("message.jsp");
		return mav;
	}
	/* ========================================================= */
	/* FAQ */

	@RequestMapping(value = "/admin_FAQList.do")
	public ModelAndView listFAQ(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<FAQDTO> list = faqDAO.listFAQ(); // �������°�
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
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/faqImg");

			File file = new File(upPath, saveImg);

			try {
				mf.transferTo(file);
				System.out.println("�������� ����! ");
			} catch (IOException e) {
				System.out.println("�������۽��ФФ� ");
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

		String img = mf.getOriginalFilename(); // ���� �����̸� �ö����

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			String saveImg = now + img;
			String upPath = session.getServletContext().getRealPath("imgfile/faqImg"); // �������� ������ �ϳ�����ڴ�.

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
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		int res = faqDAO.updateFAQ(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "�ۼ����� �����Ͽ����ϴ�.");
			mav.addObject("url", "admin_FAQList.do");
		} else if (res == -1) {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url", "admin_FAQUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "�����߻�");
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
			mav.addObject("msg", "�ۻ����� �����Ͽ����ϴ�.");
			mav.addObject("url", "admin_FAQList.do");
		} else if (res == -1) {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url", "admin_FAQDelete.do?no=" + no);
		} else {
			mav.addObject("msg", "�����߻�");
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
				mav.addObject("msg", "�����Ǿ����ϴ�.");
				mav.addObject("url", "admin_askList.do");
			} else {
				mav.addObject("msg", "�����߻�... �����ڿ��� �����ּ���");
				mav.addObject("url", "admin_askContent.do?no=" + no);
			}

		} else {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
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

		String img = mf.getOriginalFilename(); // ���� �����̸� �ö����

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			String saveImg = now + img;
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

		String img = mf.getOriginalFilename(); // ���� �����̸� �ö����

		if (img != null && !(img.trim().equals(""))) {
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			String saveImg = now + img;
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
		} else {
			dto.setImg(arg0.getParameter("beforeimg"));
		}

		System.out.println(dto.getNo());
		int res = askDAO.updateAsk(dto);

		ModelAndView mav = new ModelAndView();
		if (res > 0) {
			mav.addObject("msg", "�ۼ����� �����Ͽ����ϴ�.");
			mav.addObject("url", "admin_askList.do");
		} else if (res == -1) {
			mav.addObject("msg", "��й�ȣ�� Ʋ�Ƚ��ϴ�.�ٽ� �Է��� �ּ���");
			mav.addObject("url", "admin_askUpdate.do?no=" + String.valueOf(dto.getNo()));
		} else {
			mav.addObject("msg", "�����߻�");
			mav.addObject("url", "admin_askContent.do?no=" + String.valueOf(dto.getNo()));
		}
		mav.setViewName("message.jsp");
		return mav;
	}

	/* ===================================================== */
	/* ȸ�� �Ű� */

	@RequestMapping(value = "/reportPlanForm.do")
	protected ModelAndView reportPlanForm(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		int no = Integer.parseInt(arg0.getParameter("no"));
		String suspecter = arg0.getParameter("suspecter");
		String reporter = arg0.getParameter("reporter");
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.addObject("suspecter", suspecter);
		mav.addObject("reporter", reporter);
		mav.setViewName("WEB-INF/planning/reportForm.jsp");
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
				System.out.println("�������� ����! ");
			} catch (IOException e) {
				System.out.println("�������۽��ФФ� ");
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
		mav.addObject("msg", "�Ű������� �Ϸ�Ǿ����ϴ�.");
		mav.addObject("url", "list.do?group_no=" + arg0.getParameter("board_no"));
		return mav;
	}
	
	@RequestMapping(value = "/goBReport.do")
	protected ModelAndView goBReport(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		List<BReportDTO> list = breportDAO.listBReport();
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/report/blist.jsp");
		return mav;
	}
		
		@RequestMapping(value = "/getBContent.do")
	protected ModelAndView getBContent(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		/*
		ModelAndView mav = new ModelAndView();
		mav.addObject("getList", list);
		mav.setViewName("WEB-INF/admin/report/blist.jsp");*/
		return null;
	}
		
		@RequestMapping(value = "/sendMsg.do")
		protected ModelAndView sendMsg(@RequestParam String msg, @RequestParam String url) throws Exception {
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg",msg);
			mav.addObject("url",url);
			mav.setViewName("message.jsp");
			return mav;
		}
		
		@RequestMapping(value = "/openerClose.do")
		protected ModelAndView openerClose(@RequestParam String msg, @RequestParam String url) throws Exception {
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg",msg);
			mav.addObject("url",url);
			mav.setViewName("openerClose.jsp");
			return mav;
		}

}
