package dao;

import java.util.List;
import dao.comm.MyBatisDao;
import vo.BoardVO;

public class BoardDAOImpl extends MyBatisDao implements DAOIfs<BoardVO> {

	private static DAOIfs<BoardVO> boardDao;
	
	private BoardDAOImpl() {}
	
	public static DAOIfs<BoardVO> getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDAOImpl();
		}
		return boardDao;
	}
	
	@Override
	public List<BoardVO> findAll() {
		return selectList("board.getAll");
	}

	@Override
	public BoardVO findById(String id) {
		return selectOne("board.get", id);
	}

	@Override
	public int insert(BoardVO vo) {
		return insert("board.insert", vo);
	}

	@Override
	public int update(BoardVO vo) {
		return update("board.update", vo);
	}

	@Override
	public int deleteById(String id) {
		return delete("board.delete", id);
	}

}
