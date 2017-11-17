package plan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
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
	
	protected SubPlanDTO mappingSubDTO(HttpServletRequest arg0, HttpServletResponse arg1,
			FileUpload upload , SubPlanDTO dto) {
		HttpSession session = arg0.getSession();
		List<MultipartFile> files = upload.getImgfile();
		System.out.println("3");
		String img=null;
		String filePath=null;
		
		List<String> imgName= new ArrayList<String>();
		List<String> imgPath= new ArrayList<String>();
		
		//이미지 파일 저장
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				img=multipartFile.getOriginalFilename();
				filePath =session.getServletContext().getRealPath("WEB-INF/imgFiles");
				
				imgName.add(img);
				imgPath.add(filePath);
				
				File file = new File(filePath, img);
				try {
					multipartFile.transferTo(file);
				} catch (IOException e) {
					System.err.println("파일전송실패!!");
					e.printStackTrace();
				}
			}
		}
		
		//이미지파일 정보 dto에 담기▽▽
		dto.setImgName(imgName);
		dto.setImgPath(imgPath);
		//파일및 데이터 dto에 저장.
		for(int i=0;i<dto.getImgName().size();i++) {
			System.out.println("dto 이미지"+dto.getImgName().get(i));
		}
		return dto;
	}
	
	@RequestMapping(value = "/goView.do") // 계획 저장
	public ModelAndView addSubPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute("file") FileUpload upload 
			,@ModelAttribute SubPlanDTO dtoS,@ModelAttribute PlanDTO dtoP) throws Exception {
		
		System.out.println("1");
		PrintWriter writer=arg1.getWriter();
		ModelAndView mav = new ModelAndView();
		
		System.out.println("2");
		//↓addPlan.jsp에서 받아온 데이터를 맵핑 해주는 메소드 
		mappingSubDTO(arg0,arg1,upload,dtoS);
		
		//▽ DAOImpl working..
		int resP =0,resS=0;	
		
		resP=dao.insertPlan(dtoP);
		resS= dao.insertsubPlan(dtoS);
		
		if(resP>0&&resS>0) {
			mav.setViewName("WEB-INF/planning/listPlan.jsp");
		}
		else {
			writer.println("<scrip>alert('게시글 등록을 실패하였습니다.')</script>");
			mav.setViewName("WEB-INF/planning/addPlan.jsp");
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
