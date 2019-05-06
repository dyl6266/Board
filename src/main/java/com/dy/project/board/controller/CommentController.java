package com.dy.project.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dy.project.comment.dto.CommentDTO;
import com.dy.project.comment.service.CommentService;
import com.dy.project.common.Constant.Result;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/* 해당 클래스가 데이터 자체를 반환하는 레스트 컨트롤러 클래스임을 명시 */
@RestController
public class CommentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* 서비스 빈(Bean) 선언(연결) */
	@Autowired
	private CommentService commentService;

	@PostMapping(value = "/comments")
	public JsonObject registerComment(@RequestBody @Valid final CommentDTO params, BindingResult bindingResult) {

		JsonObject result = new JsonObject();
		result.addProperty("result", Result.FAIL.getsecondValue());

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			result.addProperty("message", fieldError.getDefaultMessage());
			return result;
		}

		try {
			if (commentService.registerComment(params) == false) {
				result.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
				return result;
			}
		} catch (DataAccessException e) {
			result.addProperty("message", "DB 처리 과정에 문제가 발생하였습니다. 다시 시도해 주세요.");
			return result;
		} catch (Exception e) {
			result.addProperty("message", "알 수 없는 오류가 발생하였습니다. 다시 시도해 주세요.");
			return result;
		}

		result = new JsonObject();
		result.addProperty("result", Result.OK.getsecondValue());
		return result;
	}

	@GetMapping(value = "/comments/{boardIdx}")
	public JsonObject getCommentList(@PathVariable("boardIdx") Integer boardIdx) {

		JsonObject result = new JsonObject();
		result.addProperty("result", Result.FAIL.getsecondValue());

		if (boardIdx == null || boardIdx < 1) {
			result.addProperty("message", "올바르지 않은 접근입니다.");
			return result;
		}

		CommentDTO params = new CommentDTO();
		params.setBoardIdx(boardIdx);
		List<CommentDTO> commentList = commentService.getCommentList(params);

		/* commentList를 Json 문자열 형태로 변환 (null일 수 있음) */
		String jsonString = new Gson().toJson(commentList);

		result = new JsonObject();
		result.addProperty("result", Result.OK.getsecondValue());
		result.addProperty("commentList", jsonString);

		return result;
	}

}
