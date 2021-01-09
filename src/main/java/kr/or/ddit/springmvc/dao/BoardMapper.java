package kr.or.ddit.springmvc.dao;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.or.ddit.springmvc.board.BoardVO;

@Mapper("boardMapper")
public interface BoardMapper {

	List<BoardVO> boardList(BoardVO boardVO) throws Exception;
	
	int insertBoard(BoardVO boardVO) throws Exception;
	
	int updateBoard(BoardVO boardVO) throws Exception;
	
	int deleteBoard(BoardVO boardVO) throws Exception;

	BoardVO selectBoardOne(BoardVO boardVO) throws Exception;

	List<BoardVO> selectBoardList(BoardVO boardVO);

	int selectBoardListTotCnt(BoardVO boardVO);
	
}
