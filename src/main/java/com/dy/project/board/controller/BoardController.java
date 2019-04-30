package com.dy.project.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dy.project.board.dto.BoardDTO;
import com.dy.project.board.service.BoardService;
import com.dy.project.common.Constant.Result;
import com.google.gson.JsonObject;

/* 해당 클래스가 디스패처(Dispatcher) 역할을 하는 컨트롤러 클래스임을 명시 */
@Controller
public class BoardController {

	/* 서비스 빈(Bean) 선언(연결) */
	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value = "type", defaultValue = "insert") String type, Model model) {

		if (StringUtils.isEmpty(type) || ("insert".equals(type) == false && "update".equals(type) == false)) {
			return "redirect:/board/list.do";
		}

		model.addAttribute("type", type);
		return "board/write";
	}

	@GetMapping(value = "/board/list.do")
	public ModelAndView openBoardList() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");

		List<BoardDTO> boardList = boardService.getBoardList();
		mav.addObject("boardList", boardList);

		return mav;
	}

	@PostMapping(value = "/boards")
	@ResponseBody
	public JsonObject registerBoard(@RequestParam(value = "type", defaultValue = "insert") String type,
			@Valid final BoardDTO params, BindingResult bindingResult) {

		JsonObject result = new JsonObject();
		result.addProperty("result", Result.FAIL.getsecondValue());

		if (StringUtils.isEmpty(type) || ("insert".equals(type) == false && "update".equals(type) == false)) {
			result.addProperty("message", "올바르지 않은 접근입니다.");
		} else if (bindingResult.hasErrors() == true) {
			FieldError fieldError = bindingResult.getFieldError();
			result.addProperty("message", fieldError.getDefaultMessage());
		} else {
			try {
				if (boardService.registerBoard(params) == false) {
					result.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
				}
			} catch (DataAccessException e) {
				result.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
			} catch (Exception e) {
				result.addProperty("message", "알 수 없는 오류가 발생하였습니다. 다시 시도해 주세요.");
			}

			result = new JsonObject();
			result.addProperty("messege", "성공적으로 저장되었습니다.");
			result.addProperty("result", Result.OK.getsecondValue());
		}

		return result;
	}

}
