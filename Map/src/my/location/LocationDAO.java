package my.location;

import java.util.*;

public interface LocationDAO {
	public int insertContent(LocationDTO dto);
	public List<LocationDTO> listContent();

}
