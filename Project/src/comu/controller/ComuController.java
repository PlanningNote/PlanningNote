package comu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import comu.dao.ComuDAO;
import comu.dto.ComuDTO;

@Controller
public class ComuController {

	@Autowired
	private ComuDAO comuDAO;
	
	@RequestMapping(value="/comu_list.do")
	public ModelAndView listComu(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<ComuDTO> list = comuDAO.listComu(); // 가져오는거
		ModelAndView mav = new ModelAndView();
		mav.addObject("comuList", list);
		mav.setViewName("WEB-INF/ComuBoard/comu_list.jsp");
		return mav;
	}
	

	
	@RequestMapping(value= "/comu_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/ComuBoard/comu_writeForm.jsp");
	}
	
	@RequestMapping(value= "/comu_write.do",method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpServletRequest arg0, @ModelAttribute ComuDTO dto, BindingResult result)
			throws Exception {
		//이제 arg2로 dto 한번에 값 못받아온다.		
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);		
			dto.setCount(0);
		}
		comuDAO.insertComu(dto);
		return new ModelAndView("redirect:comu_list.do");
	}
	
	@RequestMapping(value= "/comu_content.do")
	public ModelAndView contentComu(@RequestParam String no) throws Exception {
		//@RequestParam Map<String, String> params
		// Set<Entry<String, String>> set = params.entrySet();
		// for(Entry<String,String> entry = set){
		// System.out.println(entry.getKey() + "=" + entry.getValues());
		// }
		if(no == null || no.trim().equals("")) {
			return null;
		}
		
		ComuDTO dto = comuDAO.getComuBoard(Integer.parseInt(no), "content");
		
		ModelAndView mav = new ModelAndView("WEB-INF/ComuBoard/comu_content.jsp");
		mav.addObject("getComuBoard", dto);
		return mav;
	}
	
	@RequestMapping(value= "/comu_update.do", method=RequestMethod.GET)
	protected ModelAndView updateBoard(@RequestParam String no) throws Exception {
		if(no == null || no.trim().equals("")) {
			return null;
		}
		ComuDTO dto = comuDAO.getComuBoard(Integer.parseInt(no), "update");
		ModelAndView mav = new ModelAndView("WEB-INF/ComuBoard/comu_updateForm.jsp","getComuBoard",dto); //값 하나일때 가능
		return mav;
	}
	
	@RequestMapping(value= "/comu_update.do", method=RequestMethod.POST)
	protected ModelAndView updateProBoard(HttpServletRequest arg0, @ModelAttribute ComuDTO dto, BindingResult result)	throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		int res =comuDAO.updateComu(dto);
		
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:comu_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", dto.getNo());
			mav.setViewName("comu_update.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", dto.getNo());
			mav.setViewName("comu_content.do");
		}
		return mav;
	}
	
	@RequestMapping(value= "/comu_delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/ComuBoard/comu_delete.jsp");
	}
	

	@RequestMapping(value= "/comu_main.do", method=RequestMethod.GET)
	public ModelAndView MainForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/ComuBoard/comu_main.jsp");
	}
	
	@RequestMapping(value= "/comu_delete.do", method=RequestMethod.POST)
	protected ModelAndView deleteProBoard(@RequestParam String no, @RequestParam String pwd) throws Exception {
		if(no == null || pwd ==null || no.trim().equals("") || pwd.trim().equals("")) {
			return null;
		}
		
		int res = comuDAO.deleteComu(Integer.parseInt(no), pwd);
		ModelAndView mav = new ModelAndView();
		if(res>0) {
			return new ModelAndView("redirect:comu_list.do");
		}else if(res==-1) {
			JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.다시 입력해 주세요");
			mav.addObject("no", no);
			mav.setViewName("comu_delete.do");
		}else {
			JOptionPane.showMessageDialog(null, "오류발생");
			mav.addObject("no", no);
			mav.setViewName("comu_content.do");
		}		
		return mav;
	}
	/*
	@RequestMapping(value="/board_list.do")
	public ModelAndView listBoard(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		List<BoardDBBean> list = boardDAO.listBoard();
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", list);
		mav.setViewName("WEB-INF/board/list.jsp");
		return mav;
	}
	
	@RequestMapping(value="/board_content.do")
	public ModelAndView contentBoard(@RequestParam String num) throws Exception {
		//(@RequestParam Map<String, String> params)
		//Set<Entry<String, String>> set = params.entrySet();
		//for(Entry<String, String> entry : set){
		//	System.out.println(entry.getKey() + " = " + entry.getValues());
		//}
		if (num==null || num.trim().equals("")) {
			return null;
		}
				
		BoardDBBean dto = boardDAO.getBoard(Integer.parseInt(num));
		
		ModelAndView mav = new ModelAndView("WEB-INF/board/content.jsp");
		mav.addObject("getBoard", dto);
		return mav;
	}
	
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, 
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/board/writeForm.jsp");
	}
	
	@RequestMapping(value="/board_write.do", method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpServletRequest arg0, 
			@ModelAttribute BoardDBBean dto, BindingResult result) throws Exception {
		//BoardDBBean dto = (BoardDBBean)arg2;
		if (result.hasErrors()) {
			dto.setNum(0);
		}
		dto.setIp(arg0.getRemoteAddr());
		
		boardDAO.insertBoard(dto);
		return new ModelAndView("redirect:board_list.do");
	}
*/
}













