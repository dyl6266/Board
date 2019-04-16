package com.dy.project.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dy.project.board.dto.BoardDTO;

/* 해당 클래스가 디스패처(Dispatcher) 역할을 하는 컨트롤러 클래스임을 명시 */
@Controller
public class BoardController {

	/* 서비스 빈(Bean) 선언(연결) */
	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/list.do")
	public ModelAndView openBoardList() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/list");

		List<BoardDTO> boardList = boardService.getBoardList();
		mav.addObject("boardList", boardList);

		return mav;
	}

}
