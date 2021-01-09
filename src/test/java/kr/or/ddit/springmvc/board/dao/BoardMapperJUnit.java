package kr.or.ddit.springmvc.board.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.springmvc.board.BoardVO;
import kr.or.ddit.springmvc.dao.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/egovframework/spring/context-common.xml"
	  , "/egovframework/spring/context-datasource.xml"
	  , "/egovframework/spring/context-mapper.xml"
})
public class BoardMapperJUnit {

	private static final Logger logger = LoggerFactory.getLogger(BoardMapperJUnit.class);
	
	@Resource(name = "boardMapper")
	private BoardMapper boardMapper;
	
	
	
	
	@Test
	public void boardMapperSelectListTest() throws Exception {
		/***Given***/
		BoardVO boardVO = new BoardVO();

		/***When***/
		List<BoardVO> boardList = boardMapper.boardList(boardVO);
		for(BoardVO board : boardList) {
			logger.info(board.getTitle());
//			logger.info("board : ", board.getBoardSn());
		}

		/***Then***/
		assertTrue(boardList.size() > 0);
	}
	
	
	
	
	@Test
	public void boardMapperInsertTest() throws Exception {
		/***Given***/
		BoardVO boardVO = new BoardVO();

		/***When***/
		for(int i=0; i<300; i++) {
			boardVO.setTitle((i+1)+"번째 제목");
			boardVO.setContents((i+1)+"번째 내용");
			boardVO.setWriter("user001");
			boardVO.setUserPassword("1234");
			boardVO.setOpenYn("Y");
			boardMapper.insertBoard(boardVO);
		}
		
		/***Then***/
		assertNotNull(boardVO.getContents());
	}
	
	
	
	
	
	
	
	@Test
	public void boardMapperUpdateTest() throws Exception {
		/***Given***/
		BoardVO boardVO = new BoardVO();

		/***When***/
		boardVO.setBoardSn("10");
		boardVO.setTitle("수정 제목");
		boardVO.setContents("수정 내용");
		boardVO.setWriter("user001");
		boardVO.setUserPassword("1234");
		boardVO.setOpenYn("Y");
		boardMapper.updateBoard(boardVO);
		
		/***Then***/
		assertNotNull(boardVO.getContents());
	}
	
	
	
	
	@Test
	public void boardMapperDeleteTest() throws Exception {
		/***Given***/
		BoardVO boardVO = new BoardVO();

		/***When***/
		boardVO.setBoardSn("5");
		boardMapper.deleteBoard(boardVO);
		
		/***Then***/
		assertNotNull(boardVO.getBoardSn());
	}
	
	
	
	@Test
	public void boardMapperSelectOneTest() throws Exception {
		/***Given***/
		BoardVO boardVO = new BoardVO();

		/***When***/
		boardVO.setBoardSn("6");
		BoardVO dbBoard = boardMapper.selectBoardOne(boardVO);
		
		/***Then***/
		assertNotNull(dbBoard.getContents());
	}
	
	
	
	
	
}
