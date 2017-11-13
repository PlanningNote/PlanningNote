package plan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

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
import subplan.dto.SubPlanDTO;

@org.springframework.stereotype.Controller
public class PlanController{
	private static final Log LOG = LogFactory.getLog(PlanController.class);
	private File destinationDir;
	
	/** 
	 * 파일업로드를 위한 빈 설정의 property로 지정해준 
	 * destinationDir setter injection
	 */
	public void setDestinationDir(File destinationDir) {
        this.destinationDir = destinationDir;
    }
	@Autowired
	private PlanDAO dao;
	
	@RequestMapping(value = "/plan.do") // 계획적는 페이지로 이동.
	public ModelAndView plan(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/addPlan.jsp");
		return mav;
	}
	@RequestMapping(value = "/goView.do") // 계획 저장
	public ModelAndView addPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute SubPlanDTO dto) throws Exception {
		
		/*//전달 받은 Request값을 MultipartHttpServletRequest로 바인딩 시킨다.
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) arg0;
				
				//request의 "file"을 찾아 file객체에 세팅한다.
				MultipartFile file = multipartRequest.getFile("file");
				String fileName = file.getOriginalFilename();
				File destination = File.createTempFile("file", fileName, destinationDir);
				
				//파일카피
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
				
				//새로운 파일 속성 세팅
				SubPlanDTO vo = (SubPlanDTO) command; ==>Object command
				vo.setFilePath(destination.getAbsolutePath());
				vo.setName(file.getOriginalFilename());
				vo.setSize(file.getSize());
				vo.setFile(file);
				helloworldService.getMessage1(vo);
				return new ModelAndView("result", "message", vo)*/
		//이 위는 사진 업로드용 코딩. 
		////----------이미지 업로드 구현중----------
				
		int res =0;	
		for(int i=0;i<dto.targets.size();i++) {
		System.out.println("Controller: "+dto.targets.get(i).getContent());
		}
		res = dao.insertsubPlan(dto);
		ModelAndView mav = new ModelAndView();
		PrintWriter writer=arg1.getWriter();
		
		if(res<0) {
			writer.println("<scrip>alert('게시글 등록을 실패하였습니다.')</script>");
			mav.setViewName("WEB-INF/plan/addPlan.jsp");
		}
		else {
			mav.setViewName("WEB-INF/plan/listPlan.jsp");
		}
		return mav;
	}
	@RequestMapping(value="/list.do")//계획목록 페이지로 이동.
	public ModelAndView list(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");
		return mav;
	}
	@RequestMapping(value="/updatePlan.do")//계획수정 페이지로 이동.
	public ModelAndView updatePlan(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/update.jsp");
		return mav;
	}
}
