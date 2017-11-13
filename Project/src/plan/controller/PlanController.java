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
	 * ���Ͼ��ε带 ���� �� ������ property�� �������� 
	 * destinationDir setter injection
	 */
	public void setDestinationDir(File destinationDir) {
        this.destinationDir = destinationDir;
    }
	@Autowired
	private PlanDAO dao;
	
	@RequestMapping(value = "/plan.do") // ��ȹ���� �������� �̵�.
	public ModelAndView plan(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/addPlan.jsp");
		return mav;
	}
	@RequestMapping(value = "/goView.do") // ��ȹ ����
	public ModelAndView addPlan(HttpServletRequest arg0, HttpServletResponse arg1,
			@ModelAttribute SubPlanDTO dto) throws Exception {
		
		/*//���� ���� Request���� MultipartHttpServletRequest�� ���ε� ��Ų��.
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) arg0;
				
				//request�� "file"�� ã�� file��ü�� �����Ѵ�.
				MultipartFile file = multipartRequest.getFile("file");
				String fileName = file.getOriginalFilename();
				File destination = File.createTempFile("file", fileName, destinationDir);
				
				//����ī��
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
				
				//���ο� ���� �Ӽ� ����
				SubPlanDTO vo = (SubPlanDTO) command; ==>Object command
				vo.setFilePath(destination.getAbsolutePath());
				vo.setName(file.getOriginalFilename());
				vo.setSize(file.getSize());
				vo.setFile(file);
				helloworldService.getMessage1(vo);
				return new ModelAndView("result", "message", vo)*/
		//�� ���� ���� ���ε�� �ڵ�. 
		////----------�̹��� ���ε� ������----------
				
		int res =0;	
		for(int i=0;i<dto.targets.size();i++) {
		System.out.println("Controller: "+dto.targets.get(i).getContent());
		}
		res = dao.insertsubPlan(dto);
		ModelAndView mav = new ModelAndView();
		PrintWriter writer=arg1.getWriter();
		
		if(res<0) {
			writer.println("<scrip>alert('�Խñ� ����� �����Ͽ����ϴ�.')</script>");
			mav.setViewName("WEB-INF/plan/addPlan.jsp");
		}
		else {
			mav.setViewName("WEB-INF/plan/listPlan.jsp");
		}
		return mav;
	}
	@RequestMapping(value="/list.do")//��ȹ��� �������� �̵�.
	public ModelAndView list(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/listPlan.jsp");
		return mav;
	}
	@RequestMapping(value="/updatePlan.do")//��ȹ���� �������� �̵�.
	public ModelAndView updatePlan(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/planning/update.jsp");
		return mav;
	}
}
