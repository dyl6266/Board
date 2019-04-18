package com.dy.project.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dy.project.board.dto.BoardDTO;
import com.dy.project.board.service.BoardService;

/* 해당 클래스가 디스패처(Dispatcher) 역할을 하는 컨트롤러 클래스임을 명시 */
@Controller
public class BoardController {

	/* 서비스 빈(Bean) 선언(연결) */
	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/write.do")
	public ModelAndView openBoardWrite() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/write");

		return mav;
	}

	@PostMapping(value = "/board/register.do")
	public String registerBoard(@RequestParam(value = "type", required = false) String type, BoardDTO params) {

		if (StringUtils.isEmpty(type) || ("insert".equals(type) == false && "update".equals(type) == false)) {
			// 리다이렉트 처리
		}

		if (boardService.registerBoard(params) == false) {
			// 리다이렉트 처리
		}

		return "redirect:/board/list.do";
	}

	@GetMapping(value = "/board/list.do")
	public ModelAndView openBoardList() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");

		List<BoardDTO> boardList = boardService.getBoardList();
		mav.addObject("boardList", boardList);

		return mav;
	}

}
