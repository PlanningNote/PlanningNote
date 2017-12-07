package plan.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import plan.dao.PlanDAOImpl;
import plan.dto.PlanDTO;
import plan.ibatis.PlanMapper;
import subplan.dto.FileUpload;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

@org.springframework.stereotype.Controller
public class PlanController {
	@Autowired
	private PlanDAOImpl planDAO;

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
		System.out.println("����1");
		String img = null;
		String filePath = null;
		List<String> imgName = new ArrayList<String>();
		List<String> imgPath = new ArrayList<String>();

		// �̹��� ���� ����
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				img = multipartFile.getOriginalFilename();
				String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
				String saveImg = now + img;
				filePath = session.getServletContext().getRealPath("imgfile/plan");

				imgName.add(saveImg);
				imgPath.add(filePath);

				File file = new File(filePath, saveImg);
				try {
					multipartFile.transferTo(file);
				} catch (IOException e) {
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
		System.out.println("����2");
		String img = null;
		String filePath = null;
		String saveImg = null;

		// �̹��� ���� ����
		if (null != files && files.getSize() > 0) {
			img = files.getOriginalFilename();
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // ����ð�
			saveImg = now + img;
			filePath = session.getServletContext().getRealPath("imgfile/plan");

			File file = new File(filePath, saveImg);
			try {
				files.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// �̹������� ���� dto�� �����
		dtoP.setThumbnail(saveImg);
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

	@RequestMapping(value = "/checkMap.do")
	protected ModelAndView checkMap(HttpServletRequest arg0, @RequestParam int index) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("index", index);
		mav.setViewName("WEB-INF/planning/mapInsertForm.jsp");
		return mav;
	}

	@RequestMapping(value = "/goView.do") // ��ȹ ����
	public ModelAndView addSubPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT, @RequestParam String mapLat,
			@RequestParam String mapLng, @RequestParam String mapIndex) throws Exception {
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
		int res = 0;
		res = planDAO.insertPlan(dtoT, dtoP, dtoS, a, b, c);
		if (res < 0) {
			writer.println("<script> <alert>");
			writer.println("�Խñ� ����� �����Ͽ����ϴ�.");
			writer.println(" </alert> </script>");
			mav.setViewName("plan.do");
			return mav;
		} else {
			mav.setViewName("list.do?group_no="+res);
			System.out.println("group_no//"+res);
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
		List<PlanDTO> dtoP = planDAO.listPlanA();
		mav.addObject("dtoP", dtoP);
		arg0.setAttribute("size", dtoP.size());
		return mav;
	}

	@RequestMapping(value = "/searchPlanA.do") // ��ȹ��� �������� �̵�.
	public ModelAndView searchPlanA(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("mode")String mode ,@RequestParam("searching") String searching) throws Exception {
		ModelAndView mav = new ModelAndView(); 
		List<PlanDTO> dtoP = null;
		System.out.println(mode);
		System.out.println(searching);
		if(searching==null||searching.trim().equals("")) {
			dtoP = planDAO.listPlanA();
		}else {
			
			if(mode.equals("country")) {
				if(searching.equals("korea")) {
					mode="����";
					searching="����";
				}else if(searching.equals("japan")) {
					mode="����";
					searching="�Ϻ�";
				}else if(searching.equals("hongkong")) {
					mode="����";
					searching="ȫ��";
				}else if(searching.equals("usa")) {
					mode="����";
					searching="�̱�";
				}else if(searching.equals("uk")) {
					mode="����";
					searching="����";
				}
			}else if(mode.equals("seasion")) {
				if(searching.equals("spring")) {
					mode="�ñ�";
					searching="��";
				}else if(searching.equals("summer")) {
					mode="�ñ�";
					searching="����";
				}else if(searching.equals("autumn")) {
					mode="�ñ�";
					searching="����";
				}else if(searching.equals("winter")) {
					mode="�ñ�";
					searching="�ܿ�";
				}
			}else if(mode.equals("theme")) {
				if(searching.equals("family")) {
					mode="�׸�";
					searching="��������";
				}else if(searching.equals("couple")) {
					mode="�׸�";
					searching="���ΰ��Բ�";
				}else if(searching.equals("friend")) {
					mode="�׸�";
					searching="ģ���� �Բ�";
				}else if(searching.equals("solo")) {
					mode="�׸�";
					searching="�� Ȧ�� ����";
				}
			}
			System.out.println(mode+"//"+searching);
			dtoP = planDAO.searchPlanA(mode,searching);
		}
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		mav.addObject("dtoP", dtoP);
		if(dtoP == null||dtoP.isEmpty()==true) {
			arg0.setAttribute("size", 1);
		}
		else {
			arg0.setAttribute("size", dtoP.size());
		}
		return mav;
	}

	@RequestMapping(value = "/list.do") // ��ȹ��� �������� �̵�.
	public ModelAndView list(HttpServletRequest arg0, HttpServletResponse arg1, @RequestParam("group_no") int group_no)
			throws Exception {
		System.out.println("group_no//"+group_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");
		PlanDTO dtoP = new PlanDTO();
		SubPlanDTO dtoS = new SubPlanDTO();
		List<SubPlanDTO> listS = new ArrayList<SubPlanDTO>();

		TagDTO dtoT = planDAO.tagList(group_no);
		dtoP = planDAO.listPlan(group_no);
		listS = planDAO.subList(group_no);

		dtoS.setTargets(listS);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoT", dtoT);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/subPlanContent.do") // ��ȹ�ڼ������� �������� �̵�.
	public ModelAndView subContentPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("board_num") int board_num) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/subPlanContent.jsp");
		SubPlanDTO dtoS = new SubPlanDTO();
		dtoS = planDAO.getSubContent(board_num);
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

		dtoP = planDAO.listPlan(group_no);
		listS = planDAO.subList(group_no);

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
		int res = 0;
		res = planDAO.updatePlan(dtoT, dtoP, dtoS);
		if (res < 3) {
			writer.println("<script> <alert>");
			writer.println("�Խñ� ����� �����Ͽ����ϴ�.");
			writer.println(" </alert> </script>");
			mav.setViewName("plan.do");
			return mav;
		} else {
			mav.setViewName("WEB-INF/planning/list.jsp");
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
		mav.setViewName("WEB-INF/planning/deletePlan.jsp");
		int res = planDAO.deletePlan(group_no);
		if (res < 0) {
			mav.setViewName("listPlanA.do");
		} else {
			mav.setViewName("listPlanA.do");
		}
		return mav;
	}

}
