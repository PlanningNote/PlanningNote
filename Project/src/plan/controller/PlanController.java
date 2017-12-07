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
		System.out.println("여기1");
		String img = null;
		String filePath = null;
		List<String> imgName = new ArrayList<String>();
		List<String> imgPath = new ArrayList<String>();

		// 이미지 파일 저장
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				img = multipartFile.getOriginalFilename();
				String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 현재시간
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

		// 이미지파일 정보 dto에 담기▽▽
		dto.setImgName(imgName);
		dto.setImgPath(imgPath);
		// 파일및 데이터 dto에 저장.
	}

	// PlanDTO 이미지 파일을 디렉토리에 저장하고 이미지파일 이름을 분리시켜주는 메소드
	protected void mappingPlanDTO(HttpServletRequest arg0, HttpServletResponse arg1, PlanDTO dtoP) {

		HttpSession session = arg0.getSession();
		MultipartFile files = dtoP.getThumbfile();
		System.out.println("여기2");
		String img = null;
		String filePath = null;
		String saveImg = null;

		// 이미지 파일 저장
		if (null != files && files.getSize() > 0) {
			img = files.getOriginalFilename();
			String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); // 현재시간
			saveImg = now + img;
			filePath = session.getServletContext().getRealPath("imgfile/plan");

			File file = new File(filePath, saveImg);
			try {
				files.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 이미지파일 정보 dto에 담기▽▽
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

	@RequestMapping(value = "/goView.do") // 계획 저장
	public ModelAndView addSubPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload, @ModelAttribute("targets") SubPlanDTO dtoS,
			@ModelAttribute PlanDTO dtoP, @ModelAttribute TagDTO dtoT, @RequestParam String mapLat,
			@RequestParam String mapLng, @RequestParam String mapIndex) throws Exception {
		PrintWriter writer = arg1.getWriter();
		ModelAndView mav = new ModelAndView();
		String a[] = mapLat.split(",");
		String b[] = mapLng.split(",");
		String c[] = mapIndex.split(",");

		// ↓addPlan.jsp에서 받아온 데이터를 맵핑 해주는 메소드
		mappingSubDTO(arg0, arg1, upload, dtoS);
		mappingPlanDTO(arg0, arg1, dtoP);

		// 총 여행비 계산▽
		int total = totalPrice(dtoS);
		dtoP.setTotalprice(total);

		// ▽ DAOImpl working..
		int res = 0;
		res = planDAO.insertPlan(dtoT, dtoP, dtoS, a, b, c);
		if (res < 0) {
			writer.println("<script> <alert>");
			writer.println("게시글 등록을 실패하였습니다.");
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

	@RequestMapping(value = "/listPlanA.do") // 계획목록 페이지로 이동.
	public ModelAndView listPlanA(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		List<PlanDTO> dtoP = planDAO.listPlanA();
		mav.addObject("dtoP", dtoP);
		arg0.setAttribute("size", dtoP.size());
		return mav;
	}

	@RequestMapping(value = "/searchPlanA.do") // 계획목록 페이지로 이동.
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
					mode="나라";
					searching="국내";
				}else if(searching.equals("japan")) {
					mode="나라";
					searching="일본";
				}else if(searching.equals("hongkong")) {
					mode="나라";
					searching="홍콩";
				}else if(searching.equals("usa")) {
					mode="나라";
					searching="미국";
				}else if(searching.equals("uk")) {
					mode="나라";
					searching="영국";
				}
			}else if(mode.equals("seasion")) {
				if(searching.equals("spring")) {
					mode="시기";
					searching="봄";
				}else if(searching.equals("summer")) {
					mode="시기";
					searching="여름";
				}else if(searching.equals("autumn")) {
					mode="시기";
					searching="가을";
				}else if(searching.equals("winter")) {
					mode="시기";
					searching="겨울";
				}
			}else if(mode.equals("theme")) {
				if(searching.equals("family")) {
					mode="테마";
					searching="가족여행";
				}else if(searching.equals("couple")) {
					mode="테마";
					searching="연인과함께";
				}else if(searching.equals("friend")) {
					mode="테마";
					searching="친구와 함께";
				}else if(searching.equals("solo")) {
					mode="테마";
					searching="나 홀로 여행";
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

	@RequestMapping(value = "/list.do") // 계획목록 페이지로 이동.
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

	@RequestMapping(value = "/subPlanContent.do") // 계획자세히보기 페이지로 이동.
	public ModelAndView subContentPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@RequestParam("board_num") int board_num) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/subPlanContent.jsp");
		SubPlanDTO dtoS = new SubPlanDTO();
		dtoS = planDAO.getSubContent(board_num);
		mav.addObject("dtoS", dtoS);
		return mav;
	}

	@RequestMapping(value = "/updatePlan.do", method = RequestMethod.GET) // 계획수정 페이지로 이동.
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
		List numlist = new ArrayList();
		for (int i = 0; i < dtoS.getTargets().size(); i++) {
			numlist.add(dtoS.getTargets().get(i).getBoard_num());
		}
		// ▽ DAOImpl working..
		int res = 0;
		res = planDAO.updatePlan(dtoT, dtoP, dtoS);
		if (res < 3) {
			writer.println("<script> <alert>");
			writer.println("게시글 등록을 실패하였습니다.");
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

	@RequestMapping(value = "/deletePlan.do") // 계획수정 페이지로 이동.
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
