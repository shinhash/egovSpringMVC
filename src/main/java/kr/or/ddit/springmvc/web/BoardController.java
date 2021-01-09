package kr.or.ddit.springmvc.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;


import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.or.ddit.springmvc.board.BoardVO;
import kr.or.ddit.springmvc.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	
	@Resource(name = "boardService")
	private BoardService boardService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/board/retrievePagingList.do")
	public String selectSampleList(BoardVO boardVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		boardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		boardVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		boardVO.preparePagenationInfo(paginationInfo);

		List<BoardVO> sampleList = boardService.selectBoardList(boardVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = boardService.selectBoardListTotCnt(boardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		
		logger.debug("*********************************************************");
		logger.debug("boardVO info : {}", boardVO);
		logger.debug("*********************************************************");

		return "board/list";
	}

	

	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.GET)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	
	
	
	@RequestMapping("/board/insertBoardView.do")
	public String insertBoardView(BoardVO boardVO, Model model, HttpSession session) throws Exception {
		String saveToken = UUID.randomUUID().toString();
		boardVO = new BoardVO();
		// jsp화면에 저장
		boardVO.setSaveToken(saveToken);
		
		// 세션에 저장
		session.setAttribute("SAVE_TOKEN", saveToken);
		model.addAttribute("boardVO", boardVO);
		return "board/eddit";
	}
	
	
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardVO boardVO, Model model, HttpSession session) throws Exception {
		String sessionSaveToken = (String) session.getAttribute("SAVE_TOKEN");
		String saveToken = boardVO.getSaveToken();
		// !GenericValidator.isBlankOrNull(x) ==> x != null && !x.equals("")
		if(GenericValidator.isBlankOrNull(sessionSaveToken)
				|| GenericValidator.isBlankOrNull(saveToken)
				|| !sessionSaveToken.equals(saveToken)) {
		}else {
			boardService.insertBoard(boardVO);
			session.removeAttribute("SAVE_TOKEN");
		}
		return "forward:/board/retrievePagingList.do";
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/board/updateBoardView.do")
	public String updateBoardView(BoardVO boardVO, Model model) throws Exception {
		
		BoardVO retrieveBoardVO = boardService.selectBoardOne(boardVO);

		// 에러 나옴 수정
		retrieveBoardVO.copySearchConditionInfo(boardVO);
		System.out.println("test after");
		
		model.addAttribute("boardVO", retrieveBoardVO);
		return "board/eddit";
	}
	
	
	
	
	@RequestMapping("/board/updateBoard.do")
	public String update(BoardVO boardVO, Model model) throws Exception {
		int upCnt = boardService.updateBoard(boardVO);
		return "forward:/board/retrievePagingList.do";
	}


	
	@RequestMapping("/board/createBoardView.do")
	public String createBoardView(BoardVO boardVO, Model model) throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "board/eddit";
	}
	
	
	
	@RequestMapping("/board/createBoard.do")
	public String createBoard(BoardVO boardVO, Model model) throws Exception {
		boardService.insertBoard(boardVO);
		return "forward:/board/retrievePagingList.do";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
//		return boardService.selectSample(sampleVO);
		return sampleVO;
	}

	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

//		boardService.updateSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status) throws Exception {
//		boardService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}
}
