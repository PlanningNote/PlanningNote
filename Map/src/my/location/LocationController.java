package my.location;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocationController {
	
	@Autowired
	private LocationDAO locationDAO;
	
	@RequestMapping(value="/list.do")
	public ModelAndView list(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<LocationDTO> list = locationDAO.listContent();
		ModelAndView mav = new ModelAndView();
		mav.addObject("contentList", list);
		mav.setViewName("WEB-INF/map/list.jsp");
		return mav;		
	}
	
	@RequestMapping(value="/insertForm.do")
	public ModelAndView insertForm(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/map/insertForm.jsp");
	}
	
	
	@RequestMapping(value="/insert.do")
	public ModelAndView insertContent(HttpServletRequest arg0, @ModelAttribute LocationDTO dto, BindingResult result) throws Exception {
		if(result.hasErrors()) { 
			dto.setNo(0);			
		}
		
		String location = dto.getLocation().substring(1, dto.getLocation().length()-1);
		String a[] = location.split("\\s*,\\s*");
		location = "lat: "+a[0]+", lng: "+a[1];
		dto.setLocation(location);
		
		locationDAO.insertContent(dto);
		return new ModelAndView("redirect:list.do");	
	}

}