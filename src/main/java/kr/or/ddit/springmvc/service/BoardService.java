package kr.or.ddit.springmvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.springmvc.board.BoardVO;
import kr.or.ddit.springmvc.dao.BoardMapper;

@Service("boardService")
public class BoardService {
	
	@Resource(name = "boardMapper")
	private BoardMapper boardMapper;

	public List<BoardVO> boardList(BoardVO boardVO) throws Exception {
		return boardMapper.boardList(boardVO);
	}
	
	public int insertBoard(BoardVO boardVO) throws Exception {
		return boardMapper.insertBoard(boardVO);
	}
	
	public int updateBoard(BoardVO boardVO) throws Exception {
		return boardMapper.updateBoard(boardVO);
	}
	
	public int deleteBoard(BoardVO boardVO) throws Exception {
		return boardMapper.deleteBoard(boardVO);
	}

	public BoardVO selectBoardOne(BoardVO boardVO) throws Exception {
		return boardMapper.selectBoardOne(boardVO);
	}

	public List<BoardVO> selectBoardList(BoardVO boardVO) {
		return boardMapper.selectBoardList(boardVO);
	}

	public int selectBoardListTotCnt(BoardVO boardVO) {
		return boardMapper.selectBoardListTotCnt(boardVO);
	}
	
	
}
