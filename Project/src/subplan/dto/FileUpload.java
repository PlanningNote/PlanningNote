package subplan.dto;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	private List<MultipartFile> imgfile;

	public List<MultipartFile> getImgfile() {
		return imgfile;
	}

	public void setImgfile(List<MultipartFile> imgfile) {
		this.imgfile = imgfile;
	}
	
	
}
