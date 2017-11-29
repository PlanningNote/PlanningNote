package com.project.planning;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.planning.model.FileUpload;
import com.project.planning.model.PlanDTO;
import com.project.planning.model.SubPlanDTO;
import com.project.planning.model.TagDTO;
import com.project.planning.service.PlanMapper;

@org.springframework.stereotype.Controller
public class PlanController {

	@Autowired
	private PlanMapper dao;
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "index";
	}
	
	@RequestMapping(value = "/plan.do") // ��ȹ���� �������� �̵�.
	public ModelAndView plan(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/addPlan.jsp");
		return mav;
	}

	// subPlanDTO �̹��� ������ ���丮�� �����ϰ� �̹������� �̸��� �и������ִ� �޼ҵ�
	protected void mappingSubDTO(HttpServletRequest arg0, HttpServletResponse arg1, FileUpload upload, SubPlanDTO dto) {
		HttpSession session = arg0.getSession();
		List<MultipartFile> files = upload.getFile();
		String img = null;
		String filePath = null;

		List<String> imgName = new ArrayList<String>();
		List<String> imgPath = new ArrayList<String>();

		// �̹��� ���� ����
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				img = multipartFile.getOriginalFilename();
				filePath = session.getServletContext().getRealPath("img");

				imgName.add(img);
				imgPath.add(filePath);

				File file = new File(filePath, img);
				try {
					multipartFile.transferTo(file);
				} catch (IOException e) {
					System.err.println("sub�������۽���!!");
					e.printStackTrace();
				}
			}
		}

		// �̹������� ���� dto�� �����
		dto.setImgName(imgName);
		dto.setImgPath(imgPath);
		// ���Ϲ� ������ dto�� ����.
	}

	// PlanDTO �̹��� ������ ���丮�� �����ϰ� �̹������� �̸��� �и������ִ� �޼ҵ�
	protected void mappingPlanDTO(HttpServletRequest arg0, HttpServletResponse arg1, PlanDTO dtoP) {

		HttpSession session = arg0.getSession();
		MultipartFile files = dtoP.getThumbfile();

		String img = null;
		String filePath = null;

		// �̹��� ���� ����
		if (null != files && files.getSize() > 0) {
			img = files.getOriginalFilename();
			filePath = session.getServletContext().getRealPath("img");

			File file = new File(filePath, img);
			try {
				files.transferTo(file);
			} catch (IOException e) {
				System.err.println("plan�������۽���!!");
				e.printStackTrace();
			}
		}
		// �̹������� ���� dto�� �����
		dtoP.setThumbnail(img);
		dtoP.setThumbPath(filePath);
	}

	protected int totalPrice(SubPlanDTO dto) {
		int total = 0, num = 0;
		for (SubPlanDTO i : dto.getTargets()) {
			num = i.getPrice();
			total += num;
		}
		return total;
	}

	@RequestMapping(value = "/goView.do") // ��ȹ ����
	public ModelAndView addPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT) throws Exception {
		PrintWriter writer = arg1.getWriter();
		ModelAndView mav = new ModelAndView();

		// ��addPlan.jsp���� �޾ƿ� �����͸� ���� ���ִ� �޼ҵ�
		mappingSubDTO(arg0, arg1, upload, dtoS);
		mappingPlanDTO(arg0, arg1, dtoP);

		// �� ����� ����
		int total = totalPrice(dtoS);
		dtoP.setTotalprice(total);

		// �� DAOImpl working..
		int res = 0;
		res = dao.insertPlan(dtoT, dtoP, dtoS);

		if (res > 0) {
			mav.setViewName("WEB-INF/planning/list.jsp");
		} else {
			writer.println("�Խñ� ����� �����Ͽ����ϴ�.");
			mav.setViewName("plan.do");
		}

		mav.addObject("dtoT", dtoT);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/listPlanA.do") // ��ȹ��� �������� �̵�.
	public ModelAndView listPlanA(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("searching") String searching) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		List<PlanDTO> dtoP;
		List<SubPlanDTO> dtoS;

		dtoP = dao.listPlanA(searching);

		mav.addObject("dtoP", dtoP);
		return mav;
	}

	@RequestMapping(value = "/list.do") // ��ȹ��� �������� �̵�.
	public ModelAndView listPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("group_no") int group_no) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");

		Map map = new HashMap();
		map = dao.listPlan(group_no);

		PlanDTO dtoP;
		List<SubPlanDTO> dtoS;

		dtoP = (PlanDTO) map.get("dtoP");
		dtoS = (List<SubPlanDTO>) map.get("dtoS");

		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/subPlanContent.do") // ��ȹ��� �������� �̵�.
	public ModelAndView subContentPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("board_num") int board_num) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/subPlanContent.jsp");
		SubPlanDTO dtoS = new SubPlanDTO();
		dtoS = dao.getSubPlan(board_num);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/updatePlan.do", method = RequestMethod.GET) // ��ȹ���� �������� �̵�.
	public ModelAndView updatePlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("group_no") int group_no) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/updatePlan.jsp");
		Map map = new HashMap();
		map = dao.listPlan(group_no);

		PlanDTO dtoP;
		List<SubPlanDTO> dtoS;

		dtoP = (PlanDTO) map.get("dtoP");
		dtoS = (List<SubPlanDTO>) map.get("dtoS");

		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/updatePlan.do", method = RequestMethod.POST) // ��ȹ ����
	public ModelAndView updatePro(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT) throws Exception {

		PrintWriter writer = arg1.getWriter();
		ModelAndView mav = new ModelAndView();

		// ��addPlan.jsp���� �޾ƿ� �����͸� ���� ���ִ� �޼ҵ�
		mappingSubDTO(arg0, arg1, upload, dtoS);
		mappingPlanDTO(arg0, arg1, dtoP);

		// �� ����� ����
		int total = totalPrice(dtoS);
		dtoP.setTotalprice(total);
		
		// board_num�� ���� list
		/*List numlist = new ArrayList();
		for (int i = 0; i < dtoS.getTargets().size(); i++) {
			numlist.add(dtoS.getTargets().get(i).getBoard_num());
		}*/
		// �� DAOImpl working..
		int res = 0;
		res = dao.updatePlan(dtoT, dtoP, dtoS);

		if (res > 0) {
			mav.setViewName("WEB-INF/planning/list.jsp");
		} else {
			writer.println("�Խñ� ����� �����Ͽ����ϴ�.");
			mav.setViewName("plan.do");
		}

		mav.addObject("dtoT", dtoT);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}
	
	@RequestMapping(value = "/deletePlan.do") // ��ȹ���� �������� �̵�.
	public ModelAndView deletePlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("group_no") int group_no) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		dao.deletePlan(group_no);
		return mav;
	}
}
