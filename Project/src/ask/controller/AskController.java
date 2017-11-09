package ask.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ask.dao.AskDAO;
import ask.dto.AskDTO;


@Controller
public class AskController {

	@Autowired
	private AskDAO askDAO;
	
	@RequestMapping(value="/ask_list.do")
	public ModelAndView listNotice(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<AskDTO> list = askDAO.listAsk(); // 가져오는거
		ModelAndView mav = new ModelAndView();
		mav.addObject("askList", list); 
		mav.setViewName("WEB-INF/AskBoard/ask_list.jsp");
		return mav;
	}
	
	@RequestMapping(value= "/ask_write.do", method=RequestMethod.GET)
	protected ModelAndView writeFormBoard(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/AskBoard/ask_writeForm.jsp");
	}
	
	@RequestMapping(value= "/ask_write.do",method=RequestMethod.POST)
	protected ModelAndView writeProBoard(HttpServletRequest arg0, @ModelAttribute AskDTO dto, BindingResult result)
			throws Exception {
		//이제 arg2로 dto 한번에 값 못받아온다.		
		if(result.hasErrors()) { //에러가 발생하는 이유 중 하나가 String으로 받아왔는데 null값이 들어왔는데 그값을 int형으로 자동형변형 시키면서 오류가 발생한다.
			dto.setNo(0);		
			dto.setCount(0);
		}
		askDAO.insertAsk(dto);
		return new ModelAndView("redirect:ask_list.do");
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













