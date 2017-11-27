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
        
        System.out.println("���Ͼ��ε��׽�Ʈ");
        
        ModelAndView mav = new ModelAndView();
        
        MultipartHttpServletRequest multi = (MultipartHttpServletRequest) request;
        MultipartFile file = multi.getFile("img");//writeForm ���� input �̹����̸�
        HttpSession session = request.getSession();       
       
        String path=session.getServletContext().getRealPath("files/askImg");//���� ������ġ
         UUID randomeUUID = UUID.randomUUID();
        
         String filename = file.getOriginalFilename();// ���� ���� �̸� ��������
         
         if(file!=null){
        
          System.out.println("�Ķ���͸�" + file.getName());
          System.out.println("����ũ��" + file.getSize());
          System.out.println("���� ����" + file.isEmpty());
          System.out.println("�������� ���� �̸�" + file.getOriginalFilename());
        
          
          path = "files/askImg";//    		  "D:\\imgs"; //����ǻ�Ϳ�����
          InputStream inputStream = null;
          OutputStream outputStream = null;
          
          String organizedfilePath="";
          File f = new File(path, filename);
			try {
				
				file.transferTo(f);
			} catch (IOException e) {
				System.err.println("sub�������۽���!!");
				e.printStackTrace();
			}
			
          try {
              
 
              if (file.getSize() > 0) {
                  inputStream = file.getInputStream();
                  File realUploadDir = new File(path);
                  
                  if (!realUploadDir.exists()) {
                      realUploadDir.mkdirs();//��������.
                  }
                  
                  
                  organizedfilePath = path + randomeUUID + "_" + file.getOriginalFilename();
                  System.out.println(organizedfilePath);//������ ����Ȱ�� + ���� ��
                  
                  outputStream = new FileOutputStream(organizedfilePath);
 
                  int readByte = 0;
                  byte[] buffer = new byte[20000];//8192
 
                  while ((readByte = inputStream.read(buffer, 0, 20000)) != -1) {
                      outputStream.write(buffer, 0, readByte); //���� ���� ! 
                      
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
		
		
		//���Ϲޱ�
	MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
	
	//����ȯ �Ƿ��� �� xml�� MultipartResolver �־�� ��.
	MultipartFile mf = mr.getFile("filename");
	
	
	//���� ����οԴ��� Ȯ��
	String filename= mf.getOriginalFilename(); //���� �����̸� �ö����
	
	if(filename==null || filename.trim().equals(""))return; //���Ͼ��ε尡�ȵǴ°� 
	
	//������ �޾����ٸ� ������� session? request ? 
	
	HttpSession session = req.getSession();
	String upPath = session.getServletContext().getRealPath("/files/askimg"); //�������� ������ �ϳ�����ڴ�.
	
	
	//������ ������ �Ű� ���� . (���Ͼ���)
	File file = new File(upPath, filename);
	
	try{
		mf.transferTo(file); //���� ���� ����
		System.out.println("�������� ����! ");
	}catch(IOException e) {
		System.out.println("�������۽��ФФ� ");
		e.printStackTrace();
	}
	
	
	
		
	}
}
