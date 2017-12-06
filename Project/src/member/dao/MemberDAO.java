package member.dao;

import java.util.List;

import member.dto.MemberDTO;

public interface MemberDAO {
	public void insertMember(MemberDTO dto);
	public int updateMember(String pwd, String nickname);
	public int deleteMember(int no);
	public List<MemberDTO> listMember();
	public String findPwd(String email);
	public boolean checkMember(String email, String pwd);
	public boolean checkEmail(String email);
	public boolean duplicateEmailCheck(String email);
	public boolean checkNickname(String nickname);
	public boolean duplicateNicknameCheck(String nickname);
	public String getNickname(String email);
	public List<MemberDTO> fineMember(String search, String searchString);
	public boolean checkPwd(String nickname, String pwd);
	public void sendEmail(String email,String pwd);
	public int delete(String nickname);
	
	public String getEmail(String nickname);
}