package notice.dao;

import java.util.List;

import notice.dto.NoticeDTO;

public interface NoticeDAO {
	public int insertNotice(NoticeDTO dto);
	public int updateNotice(NoticeDTO dto);
	public int deleteNotice(int no, String pwd);
	public List<NoticeDTO> listNotice() ;
	public List<NoticeDTO>  findNotice(String search,String searchString);
	public List<NoticeDTO> rankNotice();
	public NoticeDTO getNoticeBoard(int parseInt, String string);
}
