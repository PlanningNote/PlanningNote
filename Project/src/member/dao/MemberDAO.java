package member.dao;

import java.util.List;

import member.dto.MemberDTO;

public interface MemberDAO {
	public void insertMember(MemberDTO dto);
	public int updateMember(int no);
	public int deleteMember(int no);
	public List<MemberDTO> listMember();
	public String findPwd(String nickname,String email);
	public boolean checkMember(String email, String pwd);
	public boolean checkEmail(String email);
	public boolean duplicateEmailCheck(String email);
}