package fileUpload;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {

	@RequestMapping(value="fileUpload_ok.do")
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp) {
		
		
		//파일받기
	MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
	
	//형변환 되려면 꼭 xml에 MultipartResolver 넣어야 됨.
	MultipartFile mf = mr.getFile("filename");
	
	
	//파일 제대로왔는지 확인
	String filename= mf.getOriginalFilename(); //실제 파일이름 올라와짐
	
	if(filename==null || filename.trim().equals(""))return; //파일업로드가안되는거 
	
	//파일이 받아졌다면 경로지정 session? request ? 
	
	HttpSession session = req.getSession();
	String upPath = session.getServletContext().getRealPath("/files"); //파일즈라는 폴더를 하나만들겠다.
	
	
	//서버에 파일을 옮겨 적기 . (파일쓰기)
	File file = new File(upPath, filename);
	
	try{
		mf.transferTo(file); //실제 파일 전송
		System.out.println("파일전송 성공! ");
	}catch(IOException e) {
		System.out.println("파일전송실패ㅠㅠ ");
		e.printStackTrace();
	}
	
	
	
		
	}
}
