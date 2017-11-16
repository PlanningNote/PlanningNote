package subplan.dto;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	private List<MultipartFile> file;
	
	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
}
