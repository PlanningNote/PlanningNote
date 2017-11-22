package subplan.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SubPlanDTO {
	private int group_no;
	private int board_num;
	private String s_subject;
	private String img;//이미지 이름 1개
	private String content;
	private int price;
	private String traffic;
	private  List<SubPlanDTO> targets;
	private List<String> imgName = new ArrayList<String>();//이미지 이름 여러개
	private List<String> imgPath = new ArrayList<String>();//이미지 경로 여러개
	
	
	public List<String> getImgPath() {
		return imgPath;
	}
	public void setImgPath(List<String> imgPath) {
		this.imgPath = imgPath;
	}
	public List<String> getImgName() {
		return imgName;
	}
	public void setImgName(List<String> imgList) {
		this.imgName = imgList;
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
	
	public String getS_subject() {
		return s_subject;
	}
	public void setS_subject(String s_subject) {
		this.s_subject = s_subject;
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
	public List<SubPlanDTO> getTargets() {
		return targets;
	}
	public void setTargets(List<SubPlanDTO> targets) {
		this.targets = targets;
	}
	
}
