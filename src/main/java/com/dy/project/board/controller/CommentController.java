package com.dy.project.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dy.project.comment.service.CommentService;
import com.google.gson.JsonObject;

/* 해당 클래스가 데이터 자체를 반환하는 레스트 컨트롤러 클래스임을 명시 */
@RestController
public class CommentController {

	/* 서비스 빈(Bean) 선언(연결) */
	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/replies/{boardIdx}")
	public JsonObject getCommentList(@PathVariable("boardIdx") Integer boardIdx) {

		JsonObject result = new JsonObject();
		return result;
	}

}
