package notice.dao;

import java.util.List;

import notice.dto.NoticeDTO;

public interface NoticeDAO {
	public int insertNotice(NoticeDTO dto);
	public int updateNotice(int no);
	public int deleteNotice(int no);
	public List<NoticeDTO> listNotice() ;
	public NoticeDTO findNotice(String search,String searchString);
	public List<NoticeDTO> rankNotice();


}
