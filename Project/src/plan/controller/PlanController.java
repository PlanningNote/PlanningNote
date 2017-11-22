package plan.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import plan.dao.PlanDAO;
import plan.dto.PlanDTO;
import subplan.dto.FileUpload;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

@org.springframework.stereotype.Controller
public class PlanController{
	
	@Autowired
	private PlanDAO dao;
	
	@RequestMapping(value = "/plan.do") // 계획적는 페이지로 이동.
	public ModelAndView plan(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/addPlan.jsp");
		return mav;
	}
	
	//subPlanDTO 이미지 파일을 디렉토리에 저장하고 이미지파일 이름을 분리시켜주는 메소드
	protected void mappingSubDTO(HttpServletRequest arg0, HttpServletResponse arg1,
			FileUpload upload , SubPlanDTO dto) {
		HttpSession session = arg0.getSession();
		List<MultipartFile> files = upload.getFile();
		String img=null;
		String filePath=null;
		
		List<String> imgName= new ArrayList<String>();
		List<String> imgPath= new ArrayList<String>();
		
		//이미지 파일 저장
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				img=multipartFile.getOriginalFilename();
				filePath =session.getServletContext().getRealPath("img");
				
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
		 
		//이미지파일 정보 dto에 담기▽▽
		dto.setImgName(imgName);
		dto.setImgPath(imgPath);
		System.out.println("서브이미지가 비었니?:"+dto.getImgName().isEmpty());
		//파일및 데이터 dto에 저장.
		System.out.println("sub 이미지 매핑:"+dto.getImgName().size());
	}
	
	//PlanDTO 이미지 파일을 디렉토리에 저장하고 이미지파일 이름을 분리시켜주는 메소드
	protected void mappingPlanDTO(HttpServletRequest arg0,HttpServletResponse arg1,PlanDTO dtoP) {
		
		HttpSession session = arg0.getSession();
		MultipartFile files = dtoP.getThumbfile();
		
		
		String img=null;
		String filePath=null;
		
		//이미지 파일 저장
		if (null != files && files.getSize() > 0) {
				img=files.getOriginalFilename();
				filePath =session.getServletContext().getRealPath("img");
				
				File file = new File(filePath, img);
				try {
					files.transferTo(file);
				} catch (IOException e) {
					System.err.println("plan파일전송실패!!");
					e.printStackTrace();
				}
		}
		
		//이미지파일 정보 dto에 담기▽▽
		dtoP.setThumbnail(img);
		dtoP.setThumbPath(filePath);
		//파일및 데이터 dto에 저장.
	}
	
	@RequestMapping(value = "/goView.do") // 계획 저장
	public ModelAndView addSubPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload 
			,@ModelAttribute("targets") SubPlanDTO dtoS,@ModelAttribute PlanDTO dtoP,@ModelAttribute TagDTO dtoT) throws Exception {
		
		PrintWriter writer=arg1.getWriter();
		ModelAndView mav = new ModelAndView();
		
		System.out.println(dtoS.getTargets().get(0).getSubject());
		System.out.println(dtoS.getTargets().get(0).getContent());
		System.out.println(dtoS.getTargets().get(0).getPrice());
		System.out.println(dtoS.getTargets().get(0).getTraffic());
		//↓addPlan.jsp에서 받아온 데이터를 맵핑 해주는 메소드 
		mappingSubDTO(arg0,arg1,upload,dtoS);
		mappingPlanDTO(arg0,arg1,dtoP);
		
		//▽ DAOImpl working..
		int resP =0,resS=0,resT=0;
		
		resT = dao.tagPlan(dtoT);// dtoT만 주는걸로
		resP=dao.insertPlan(dtoP);
		resS= dao.insertsubPlan(dtoS);
		
		if(resP>0&&resT>0&&resS>0){
			mav.setViewName("WEB-INF/planning/listPlan.jsp");
		}
		/*else if(dtoP.getSubject().equals(null)&&dtoP.getCountry().equals(null)&&dtoP.getCity().equals(null)
				&&dtoP.getThumbnail().equals(null)&&dtoP.getTravel_period().equals(null)
				&&dtoP.getTravel_seasion().equals(null)&&dtoP.getTravel_theme().equals(null)
				&&dtoS.getSubject().equals(null)&&dtoS.getImg().equals(null)&&dtoS.getContent().equals(null)
				&&dtoS.getPrice()>0&&dtoS.getTraffic().equals(null)
				){
			writer.print("필수 항목을 입력해주세요");
			mav.setViewName("plan.do");
		}*/else {
			writer.println("게시글 등록을 실패하였습니다.");
			mav.setViewName("plan.do");
		}
		
		mav.addObject("dtoP", dtoP);
		mav.addObject("dtoS", dtoS);
		return mav;
	}
	
	@RequestMapping(value="/list.do")//계획목록 페이지로 이동.
	public ModelAndView list(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");
		return mav;
	}
	@RequestMapping(value="/listPlanA.do")//계획목록 페이지로 이동.
	public ModelAndView listPlanA(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlanA.jsp");
		return mav;
	}
	
	@RequestMapping(value="/subPlanContent.do")//계획목록 페이지로 이동.
	public ModelAndView contentPlan(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/subPlanContent.jsp");
		return mav;
	}
	@RequestMapping(value="/updatePlan.do")//계획수정 페이지로 이동.
	public ModelAndView updatePlan(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/updatePlan.jsp");
		return mav;
	}
}
