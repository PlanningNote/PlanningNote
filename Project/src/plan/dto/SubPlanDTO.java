package plan.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SubPlanDTO {
	private int group_no;
	private int board_num;
	private String subject;
	private String img;//이미지 이름
	private String content;
	private int price;
	private String traffic;
	public List<SubPlanDTO> targets;
	private MultipartFile file;
	private Long size;//이미지 사이즈
	private String filePath;//이미지 경로
	
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<SubPlanDTO> getTargets() {
		return targets;
	}
	public void setTargets(List<SubPlanDTO> targets) {
		this.targets = targets;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	
	
}
