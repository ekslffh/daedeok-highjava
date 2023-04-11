package service;

import java.util.List;
import vo.BoardVO;

public interface BoardServiceIfs {
	
	public int insert(BoardVO bv);
	public List<BoardVO> searchAll();
	public BoardVO searchById(String id);
	public int update(BoardVO bv);
	public int deleteById(String id);
	
}
