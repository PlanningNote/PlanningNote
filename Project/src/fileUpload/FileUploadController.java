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
		
		
		//���Ϲޱ�
	MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
	
	//����ȯ �Ƿ��� �� xml�� MultipartResolver �־�� ��.
	MultipartFile mf = mr.getFile("filename");
	
	
	//���� ����οԴ��� Ȯ��
	String filename= mf.getOriginalFilename(); //���� �����̸� �ö����
	
	if(filename==null || filename.trim().equals(""))return; //���Ͼ��ε尡�ȵǴ°� 
	
	//������ �޾����ٸ� ������� session? request ? 
	
	HttpSession session = req.getSession();
	String upPath = session.getServletContext().getRealPath("/files"); //�������� ������ �ϳ�����ڴ�.
	
	
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
