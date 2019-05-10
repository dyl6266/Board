package com.dy.project.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
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
import com.google.gson.JsonArray;
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

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.FAIL.getsecondValue());

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			jsonObj.addProperty("message", fieldError.getDefaultMessage());
			return jsonObj;
		}

		try {
			if (commentService.registerComment(params) == false) {
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

	@GetMapping(value = "/comments/{boardIdx}")
	public JsonObject getCommentList(@PathVariable("boardIdx") Integer boardIdx) {

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.FAIL.getsecondValue());

		if (boardIdx == null || boardIdx < 1) {
			jsonObj.addProperty("message", "올바르지 않은 접근입니다.");
			return jsonObj;
		}

		CommentDTO params = new CommentDTO();
		params.setBoardIdx(boardIdx);
		List<CommentDTO> commentList = commentService.getCommentList(params);

		/* commentList를 JsonArray로 변환 */
		JsonArray jsonArr = null;
		if (CollectionUtils.isEmpty(commentList) == false) {
			jsonArr = new Gson().toJsonTree(commentList).getAsJsonArray();
		}

		jsonObj = new JsonObject();
		jsonObj.addProperty("result", Result.OK.getsecondValue());
		jsonObj.add("commentList", jsonArr);

		return jsonObj;
	}

}
