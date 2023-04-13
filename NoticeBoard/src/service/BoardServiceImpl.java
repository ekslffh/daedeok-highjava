package service;

import java.util.List;
import dao.BoardDAOImpl;
import dao.DAOIfs;
import vo.BoardVO;

public class BoardServiceImpl implements BoardServiceIfs {

	private DAOIfs<BoardVO> boardDao;
	private static BoardServiceIfs boardService;
	
	private BoardServiceImpl() {
		boardDao = BoardDAOImpl.getInstance();
	}
	
	public static BoardServiceIfs getInstance() {
		if (boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	@Override
	public int insert(BoardVO bv) {
		return boardDao.insert(bv);
	}

	@Override
	public List<BoardVO> searchAll() {
		return boardDao.findAll();
	}

	@Override
	public BoardVO searchById(String id) {
		return boardDao.findById(id);
	}

	@Override
	public int update(BoardVO bv) {
		return boardDao.update(bv);
	}

	@Override
	public int deleteById(String id) {
		return boardDao.deleteById(id);
	}

}
