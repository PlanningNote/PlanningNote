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
	
	@RequestMapping(value = "/plan.do") // 계획적는 페이지로 이동.
	public ModelAndView plan(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/addPlan.jsp");
		return mav;
	}

	// subPlanDTO 이미지 파일을 디렉토리에 저장하고 이미지파일 이름을 분리시켜주는 메소드
	protected void mappingSubDTO(HttpServletRequest arg0, HttpServletResponse arg1, FileUpload upload, SubPlanDTO dto) {
		HttpSession session = arg0.getSession();
		List<MultipartFile> files = upload.getFile();
		String img = null;
		String filePath = null;

		List<String> imgName = new ArrayList<String>();
		List<String> imgPath = new ArrayList<String>();

		// 이미지 파일 저장
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
					System.err.println("sub파일전송실패!!");
					e.printStackTrace();
				}
			}
		}

		// 이미지파일 정보 dto에 담기▽▽
		dto.setImgName(imgName);
		dto.setImgPath(imgPath);
		// 파일및 데이터 dto에 저장.
	}

	// PlanDTO 이미지 파일을 디렉토리에 저장하고 이미지파일 이름을 분리시켜주는 메소드
	protected void mappingPlanDTO(HttpServletRequest arg0, HttpServletResponse arg1, PlanDTO dtoP) {

		HttpSession session = arg0.getSession();
		MultipartFile files = dtoP.getThumbfile();

		String img = null;
		String filePath = null;

		// 이미지 파일 저장
		if (null != files && files.getSize() > 0) {
			img = files.getOriginalFilename();
			filePath = session.getServletContext().getRealPath("img");

			File file = new File(filePath, img);
			try {
				files.transferTo(file);
			} catch (IOException e) {
				System.err.println("plan파일전송실패!!");
				e.printStackTrace();
			}
		}
		// 이미지파일 정보 dto에 담기▽▽
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

	@RequestMapping(value = "/goView.do") // 계획 저장
	public ModelAndView addPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT) throws Exception {
		PrintWriter writer = arg1.getWriter();
		ModelAndView mav = new ModelAndView();

		// ↓addPlan.jsp에서 받아온 데이터를 맵핑 해주는 메소드
		mappingSubDTO(arg0, arg1, upload, dtoS);
		mappingPlanDTO(arg0, arg1, dtoP);

		// 총 여행비 계산▽
		int total = totalPrice(dtoS);
		dtoP.setTotalprice(total);

		// ▽ DAOImpl working..
		int res = 0;
		res = dao.insertPlan(dtoT, dtoP, dtoS);

		if (res > 0) {
			mav.setViewName("WEB-INF/planning/list.jsp");
		} else {
			writer.println("게시글 등록을 실패하였습니다.");
			mav.setViewName("plan.do");
		}

		mav.addObject("dtoT", dtoT);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/listPlanA.do") // 계획목록 페이지로 이동.
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

	@RequestMapping(value = "/list.do") // 계획목록 페이지로 이동.
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

	@RequestMapping(value = "/subPlanContent.do") // 계획목록 페이지로 이동.
	public ModelAndView subContentPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("board_num") int board_num) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/subPlanContent.jsp");
		SubPlanDTO dtoS = new SubPlanDTO();
		dtoS = dao.getSubPlan(board_num);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/updatePlan.do", method = RequestMethod.GET) // 계획수정 페이지로 이동.
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

	@RequestMapping(value = "/updatePlan.do", method = RequestMethod.POST) // 계획 저장
	public ModelAndView updatePro(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT) throws Exception {

		PrintWriter writer = arg1.getWriter();
		ModelAndView mav = new ModelAndView();

		// ↓addPlan.jsp에서 받아온 데이터를 맵핑 해주는 메소드
		mappingSubDTO(arg0, arg1, upload, dtoS);
		mappingPlanDTO(arg0, arg1, dtoP);

		// 총 여행비 계산▽
		int total = totalPrice(dtoS);
		dtoP.setTotalprice(total);
		
		// board_num을 담은 list
		/*List numlist = new ArrayList();
		for (int i = 0; i < dtoS.getTargets().size(); i++) {
			numlist.add(dtoS.getTargets().get(i).getBoard_num());
		}*/
		// ▽ DAOImpl working..
		int res = 0;
		res = dao.updatePlan(dtoT, dtoP, dtoS);

		if (res > 0) {
			mav.setViewName("WEB-INF/planning/list.jsp");
		} else {
			writer.println("게시글 등록을 실패하였습니다.");
			mav.setViewName("plan.do");
		}

		mav.addObject("dtoT", dtoT);
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}
	
	@RequestMapping(value = "/deletePlan.do") // 계획수정 페이지로 이동.
	public ModelAndView deletePlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("group_no") int group_no) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		dao.deletePlan(group_no);
		return mav;
	}
}
