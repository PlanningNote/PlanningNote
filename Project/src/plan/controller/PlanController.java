package plan.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import plan.dao.PlanDAO;
import plan.dto.PlanDTO;
import subplan.dto.FileUpload;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;
@org.springframework.stereotype.Controller
public class PlanController {

	@Autowired
	private PlanDAO dao;

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
	public ModelAndView addSubPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT,@RequestParam String mapLat , @RequestParam String mapLng,@RequestParam String mapIndex) throws Exception {
		PrintWriter writer = arg1.getWriter();
		ModelAndView mav = new ModelAndView();
		
		String a[] = mapLat.split(",");
		String b[] = mapLng.split(",");
		String c[] = mapIndex.split(",");

		// ��addPlan.jsp���� �޾ƿ� �����͸� ���� ���ִ� �޼ҵ�
		mappingSubDTO(arg0, arg1, upload, dtoS);
		mappingPlanDTO(arg0, arg1, dtoP);

		// �� ����� ����
		int total = totalPrice(dtoS);
		dtoP.setTotalprice(total);

		// �� DAOImpl working..
		int resP = 0, resS = 0, resT = 0;
		resT = dao.tagPlan(dtoT);
		resP = dao.insertPlan(dtoP);
		resS = dao.insertsubPlan(dtoS,a,b,c);

		if (resP > 0 && resT > 0 && resS > 0) {
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
	public ModelAndView listPlanA(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		List<PlanDTO> dtoP = dao.listAPlan();
		mav.addObject("dtoP", dtoP);
		return mav;
	}

	@RequestMapping(value = "/list.do") // ��ȹ��� �������� �̵�.
	public ModelAndView list(HttpServletRequest arg0, HttpServletResponse arg1, @RequestParam("group_no") int group_no)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");
		PlanDTO dtoP = new PlanDTO();
		SubPlanDTO dtoS = new SubPlanDTO();
		List<SubPlanDTO> listS = new ArrayList<SubPlanDTO>();

		dtoP = dao.listPlan(group_no);
		listS = dao.subList(group_no);
		System.out.println("����Ʈ�÷�: " + listS.size());

		dtoS.setTargets(listS);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/subPlanContent.do") // ��ȹ�ڼ������� �������� �̵�.
	public ModelAndView subContentPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("board_num") int board_num) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/subPlanContent.jsp");
		SubPlanDTO dtoS = new SubPlanDTO();
		dtoS = dao.getSubContent(board_num);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/updatePlan.do", method = RequestMethod.GET) // ��ȹ���� �������� �̵�.
	public ModelAndView updatePlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("group_no") int group_no) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/updatePlan.jsp");
		PlanDTO dtoP = new PlanDTO();
		SubPlanDTO dtoS = new SubPlanDTO();
		List<SubPlanDTO> listS = new ArrayList<SubPlanDTO>();

		dtoP = dao.listPlan(group_no);
		listS = dao.subList(group_no);

		dtoS.setTargets(listS);
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
		List numlist = new ArrayList();
		for (int i = 0; i < dtoS.getTargets().size(); i++) {
			numlist.add(dtoS.getTargets().get(i).getBoard_num());
		}
		// �� DAOImpl working..
		int resP = 0, resS = 0, resT = 0;
		resT = dao.tagPlan(dtoT);
		resP = dao.updatePlan(dtoP.getGroup_no(), dtoP);
		resS = dao.updateSubPlan(numlist, dtoS);

		if (resP > 0 && resT > 0 && resS > 0) {
			mav.setViewName("WEB-INF/planning/listPlan.jsp");
		} else {
			writer.println("�Խñ� ����� �����Ͽ����ϴ�.");
			mav.setViewName("listPlanA.do");
		}

		mav.addObject("dtoT", dtoT);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}
	
	@RequestMapping(value= "/checkMap.do")
	protected ModelAndView checkMap(HttpServletRequest arg0,@RequestParam int index) throws Exception {
		 ModelAndView mav = new  ModelAndView();
		 mav.addObject("index", index);
		 mav.setViewName("WEB-INF/planning/mapInsertForm.jsp");
		 return mav;
	}
}
