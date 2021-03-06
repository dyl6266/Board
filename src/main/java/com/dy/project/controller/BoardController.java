package com.dy.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dy.project.common.Constant.Result;
import com.dy.project.common.paging.Criteria;
import com.dy.project.domain.BoardDTO;
import com.dy.project.domain.CommentDTO;
import com.dy.project.service.BoardService;
import com.dy.project.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/* 해당 클래스가 디스패처(Dispatcher) 역할을 하는 컨트롤러 클래스임을 명시 */
@Controller
public class BoardController {

	/* request 오브젝트 선언 */
	@Autowired
	private HttpServletRequest request;

	/* 서비스 빈(Bean) 선언(연결) */
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value = "idx", required = false) Integer idx, Model model) {

		/* insert의 경우 */
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
			/* update의 경우 */
		} else {
			if (idx < 1) {
				return "redirect:" + request.getContextPath() + "/board/list.do";
			}

			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null) {
				return "redirect:" + request.getContextPath() + "/board/list.do";
			}
			model.addAttribute("board", board);
		}

		return "board/write";
	}

	@GetMapping(value = "/board/list.do")
	public String openBoardList(@ModelAttribute("params") BoardDTO params, Model model) {

		List<BoardDTO> boardList = boardService.getBoardList(params);
		model.addAttribute("boardList", boardList);

		return "board/list";
	}

	@GetMapping(value = "/board/view.do")
	public String openBoardView(@RequestParam(value = "idx", required = false) Integer idx, Model model) {

		if (idx == null || idx < 1) {
			return "redirect:/board/list.do";
		}

		/* 게시글 상세 정보 */
		BoardDTO board = boardService.getBoardDetail(idx);
		model.addAttribute("board", board);

		/* 댓글 리스트 */
		CommentDTO params = new CommentDTO();
		params.setBoardIdx(idx);
		List<CommentDTO> commentList = commentService.getCommentList(params);
		model.addAttribute("commentList", commentList);

		return "board/view";
	}

	@PostMapping(value = "/boards")
	@ResponseBody
	public JsonObject registerBoard(@RequestBody @Valid final BoardDTO params, BindingResult bindingResult) {

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.FAIL.getsecondValue());

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			jsonObj.addProperty("message", fieldError.getDefaultMessage());
			return jsonObj;
		}

		try {
			if (boardService.registerBoard(params) == false) {
				jsonObj.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
				return jsonObj;
			}
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
			return jsonObj;
		} catch (Exception e) {
			jsonObj.addProperty("message", "알 수 없는 오류가 발생하였습니다. 다시 시도해 주세요.");
			return jsonObj;
		}

		jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.OK.getsecondValue());
		return jsonObj;
	}
	// end of method

	@GetMapping(value = "/boards")
	@ResponseBody
	public JsonObject selectBoardList(final BoardDTO board) {

		JsonObject jsonObj = new JsonObject();

		List<BoardDTO> boardList = boardService.getBoardList(board);
		if (CollectionUtils.isEmpty(boardList) == false) {
			JsonArray jsonArr = new Gson().toJsonTree(boardList).getAsJsonArray();
			jsonObj.add("boardList", jsonArr);
		}

		return jsonObj;
	}
	// end of method

	@PatchMapping(value = "/boards/{idx}")
	@ResponseBody
	public JsonObject deleteBoard(@Valid @PathVariable("idx") final Integer idx) {

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.FAIL.getsecondValue());

		if (idx == null || idx < 1) {
			jsonObj.addProperty("message", "올바르지 않은 접근입니다.");
			return jsonObj;
		}

		try {
			if (boardService.deleteBoard(idx) == false) {
				jsonObj.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
				return jsonObj;
			}
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
			return jsonObj;
		} catch (Exception e) {
			jsonObj.addProperty("message", "알 수 없는 오류가 발생하였습니다. 다시 시도해 주세요.");
			return jsonObj;
		}

		jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.OK.getsecondValue());
		return jsonObj;
	}

}
