package board.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utility.Paging;

@Service
public class BoardDao {
	
	public BoardDao() {
		System.out.println("BoardDao()");
	}
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "board.Board";

	public int getTotalCount(Map<String, String> map) {
		int count = -1;
		count = sqlSessionTemplate.selectOne(namespace+".getTotalCount",map);
		System.out.println("board_totalCount: "+count);
		return count;
	}

	public List<BoardBean> getAllLists(Paging pageInfo, Map<String, String> map) {
		RowBounds rb = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<BoardBean> blists = new ArrayList<BoardBean>();
		blists = sqlSessionTemplate.selectList(namespace+".getAllLists", map, rb);
		System.out.println("board_blists.size(): "+blists.size());
		return blists;
	}

	public void insertBoard(BoardBean bd) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertBoard",bd);
		System.out.println("board_insert: "+cnt);
	}

	public BoardBean getBoardByNum(int num) {
		BoardBean board = null;
		board = sqlSessionTemplate.selectOne(namespace+".getBoardByNum", num);
		return board;
	}

	public void updateReadcount(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".updateReadcount", num);
		System.out.println("board_updateReadcount: "+cnt);
	}

	public void deleteBoard(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace+".deleteBoard", num);
		System.out.println("board_deleteBoard: "+cnt);
	}

	public void updateBoard(BoardBean board, int num) {
		board.setNum(num);
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".updateBoard",board);
		System.out.println("board_updateBoard: "+cnt);
	}

	public void updateReStep(BoardBean bd) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".updateReStep",bd);
		System.out.println("board_updateReStep: "+cnt);
	}

	public void insertReply(BoardBean bd) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertReply",bd);
		System.out.println("board_insertReply: "+cnt);
	}
}
