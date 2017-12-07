package plan.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import plan.dto.PlanDTO;
import subplan.dto.SubPlanDTO;
import tag.dto.TagDTO;

import com.ibatis.common.resources.Resources;

import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.sql.SQLException;

/**
 * This is not a best practices class. It's just an example to give you an idea
 * of how iBATIS works. For a more complete example, see JPetStore 5.0 at
 * http://www.ibatis.com.
 */
public class PlanMapper {

	/**
	 * SqlMapClient instances are thread safe, so you only need one. In this case,
	 * we'll use a static singleton. So sue me. ;-)
	 */
	private static SqlSessionFactory sqlMapper;

	/**
	 * It's not a good idea to put code that can fail in a class initializer, but
	 * for sake of argument, here's how you configure an SQL Map.
	 */
	static {
		try {
			String resource = "plan/ibatis/SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}

	public static int insertPlan(TagDTO dtoT, PlanDTO dtoP, SubPlanDTO dtoS) {
		SqlSession session = null;
		int index = 0, resT = 0, resP = 0, resS = 0;
		try {
			session = sqlMapper.openSession(); // 세션 열기
			resT = session.insert("tagPlan", dtoT);
			resP = session.insert("insertPlan", dtoP);
			for (int i = 0; i < dtoS.getTargets().size(); i++) {
				resS = session.insert("insertsubPlan", dtoS.getTargets().get(i));
			}
			session.commit();// mybatis는 자동commit을 안해줌. inert할때는 특히나 더 이런거 써줘야함.
			index = resT + resP + resS;
			
			if(index<3) {
				return index=-1;
			}
			else {
				return index = session.selectOne("sequence");
			}
			
		} finally {
			session.close();
		}
	}

	public static List<PlanDTO> listPlanA() {
		SqlSession session = null;
		List<PlanDTO> dtoP = null;
		try {
			session = sqlMapper.openSession(); // 세션 열기
			dtoP = session.selectList("listPlanA");
		} finally {
			session.close();
		}
		return dtoP;
	}

	public static List<PlanDTO> searchPlanA(String mode,String searching){
		SqlSession session = null;
		List<PlanDTO> dtoP = null;
		if(mode.equals("나라")) {
			
			try {
				session = sqlMapper.openSession(); // 세션 열기
				dtoP = session.selectList("searchCountry",searching);
			} finally {
				session.close();
			}
		}else if(mode.equals("작성자")) {
			try {
				session = sqlMapper.openSession(); // 세션 열기
				dtoP = session.selectList("searchWriter",searching);
			} finally {
				session.close();
			}
		}else if(mode.equals("시기")) {
			try {
				session = sqlMapper.openSession(); // 세션 열기
				dtoP = session.selectList("searchSeasion",searching);
			} finally {
				session.close();
			}
		}else if(mode.equals("테마")) {
			try {
				session = sqlMapper.openSession(); // 세션 열기
				dtoP = session.selectList("searchTheme",searching);
			} finally {
				session.close();
			}
		}
		//System.out.println("찾기: "+dtoP.get(0).getCountry());
		
		return dtoP;
	}
	
	public static TagDTO tagList(int group_no) {
		SqlSession session = null;
		TagDTO dtoT = null;
		try {
			session = sqlMapper.openSession(); // 세션 열기
			dtoT = session.selectOne("tagList",group_no);
		} finally {
			session.close();
		}
		return dtoT;
	}

	protected static void plusReadCount(int group_no) {
		SqlSession session = null;
		try {
			session = sqlMapper.openSession();
			session.selectOne("plusReadCount", group_no);
		} finally {
			session.close();
		}
	}
	public static PlanDTO listPlan(int group_no) {
		SqlSession session = null;
		PlanDTO dtoP = null;
		plusReadCount(group_no);// 조회수 1증가
		try {
			session = sqlMapper.openSession();
			dtoP = session.selectOne("listPlan", group_no);
		} finally {
			session.close();
		}
		return dtoP;
	}

	public static List<SubPlanDTO> subList(int group_no) {
		SqlSession session = null;
		List<SubPlanDTO> dtoS = null;
		try {
			session = sqlMapper.openSession();
			dtoS = session.selectList("subList", group_no);
		} finally {
			session.close();
		}
		return dtoS;
	}

	public static SubPlanDTO subContent(int board_num) {
		SqlSession session = null;
		SubPlanDTO dtoS = null;
		try {
			session = sqlMapper.openSession();
			dtoS = session.selectOne("subContent", board_num);
		} finally {
			session.close();
		}
		return dtoS;
	}

	public static int updatePlan(TagDTO dtoT, PlanDTO dtoP, SubPlanDTO dtoS) {
		SqlSession session = null;
		int index = 0, resT = 0, resP = 0, resS = 0;
		try {
			session = sqlMapper.openSession(); // 세션 열기
			resT = session.update("updateTag", dtoT);
			resP = session.update("updatePlan", dtoP);
			for (int i = 0; i < dtoS.getTargets().size(); i++) {
				resS = session.update("updateSubPlan", dtoS.getTargets().get(i));
			}
			session.commit();// mybatis는 자동commit을 안해줌. inert할때는 특히나 더 이런거 써줘야함.
			index = resT + resP + resS;
			return index;
		} finally {
			session.close();
		}
	}

	
	public static int deletePlan(int group_no) {
		SqlSession session = null;
		int index = 0, resT = 0, resP = 0, resS = 0;
		try {
			session = sqlMapper.openSession(); // 세션 열기
			resP = session.delete("deletePlan", group_no);
			resS = session.delete("deleteSub", group_no);
			resT = session.delete("deleteTag", group_no);
			session.commit();// mybatis는 자동commit을 안해줌. inert할때는 특히나 더 이런거 써줘야함.
			index = resT + resP + resS;
			return index;
		} finally {
			session.close();
		}
	}
	
	public static List<PlanDTO> mylistAPlan() {
		SqlSession session = null;
		List<PlanDTO> dtoP = null;
		try {
			session = sqlMapper.openSession(); // 세션 열기
			dtoP = session.selectList("mylistAPlan");
		} finally {
			session.close();
		}
		return dtoP;
	}

	
	
	
}
