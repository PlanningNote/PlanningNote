package fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	@RequestMapping("/upload.do")
    public ModelAndView mybatistest(HttpServletRequest request) throws IOException{
        
        System.out.println("파일업로드테스트");
        
        ModelAndView mav = new ModelAndView();
        
        MultipartHttpServletRequest multi = (MultipartHttpServletRequest) request;
        MultipartFile file = multi.getFile("img");//writeForm 에서 input 이미지이름
        HttpSession session = request.getSession();       
       
        String path=session.getServletContext().getRealPath("files/askImg");//사진 저장위치
         UUID randomeUUID = UUID.randomUUID();
        
         String filename = file.getOriginalFilename();// 실제 파일 이름 가져오기
         
         if(file!=null){
        
          System.out.println("파라미터명" + file.getName());
          System.out.println("파일크기" + file.getSize());
          System.out.println("파일 존재" + file.isEmpty());
          System.out.println("오리지날 파일 이름" + file.getOriginalFilename());
        
          
          path = "files/askImg";//    		  "D:\\imgs"; //내컴퓨터에저장
          InputStream inputStream = null;
          OutputStream outputStream = null;
          
          String organizedfilePath="";
          File f = new File(path, filename);
			try {
				
				file.transferTo(f);
			} catch (IOException e) {
				System.err.println("sub파일전송실패!!");
				e.printStackTrace();
			}
			
          try {
              
 
              if (file.getSize() > 0) {
                  inputStream = file.getInputStream();
                  File realUploadDir = new File(path);
                  
                  if (!realUploadDir.exists()) {
                      realUploadDir.mkdirs();//폴더생성.
                  }
                  
                  
                  organizedfilePath = path + randomeUUID + "_" + file.getOriginalFilename();
                  System.out.println(organizedfilePath);//파일이 저장된경로 + 파일 명
                  
                  outputStream = new FileOutputStream(organizedfilePath);
 
                  int readByte = 0;
                  byte[] buffer = new byte[20000];//8192
 
                  while ((readByte = inputStream.read(buffer, 0, 20000)) != -1) {
                      outputStream.write(buffer, 0, readByte); //파일 생성 ! 
                      
                  }
            
                  
              }
              
          } catch (Exception e) {
              // TODO: handle exception
              e.printStackTrace();
 
          } finally {
 
              outputStream.close();
              inputStream.close();
          }
          
      
                 
         }    
          mav.setViewName("test3.jsp");
        return mav;
                
    }

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
	String upPath = session.getServletContext().getRealPath("/files/askimg"); //파일즈라는 폴더를 하나만들겠다.
	
	
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
