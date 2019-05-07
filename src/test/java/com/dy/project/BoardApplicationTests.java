package com.dy.project;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.dy.project.board.dto.BoardDTO;
import com.dy.project.board.mapper.BoardMapper;
import com.dy.project.comment.dto.CommentDTO;
import com.dy.project.comment.service.CommentService;
import com.dy.project.common.Constant.YesNo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private CommentService commentService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSqlSession() throws Exception {
		System.out.println(sqlSession.toString());
	}

	@Test
	public void testInsert() {
		for (int i = 0; i <= 50; i++) {
			BoardDTO dto = new BoardDTO();
			dto.setTitle(i + "번 제목입니다.");
			dto.setContent(i + "번 내용입니다.");
			dto.setWriter(i + "번 작성자");
			dto.setUseYn(YesNo.Y);
			dto.setNoticeYn(YesNo.N);
			dto.setSecretYn(YesNo.N);
			if (i % 10 == 0) {
				dto.setNoticeYn(YesNo.Y);
				dto.setSecretYn(YesNo.Y);
			}
			dto.setViewCnt(0);
			boardMapper.insertBoard(dto);
		}
	}

	@Test
	public void testCommentInsert() {
		for (int i = 0; i < 50; i++) {
			CommentDTO dto = new CommentDTO();
			dto.setBoardIdx(62);
			dto.setContent(i + "번 내용입니당당당");
			dto.setWriter("도영 도영 0" + i);
			commentService.registerComment(dto);
		}
	}
	
	@Test
	public void testCommentDelete() {
		boolean result = commentService.deleteComment(2, 62);
		System.out.println(result);
	}
	
	@Test
	public void testCOmmenList() {
		CommentDTO params = new CommentDTO();
		params.setBoardIdx(61);
		List<CommentDTO> list = commentService.getCommentList(params);
		Gson gson = null;
		if ( CollectionUtils.isEmpty(list)) {
			gson = new Gson();
		} else {
			String jsonStr = new Gson().toJson(list);
			System.out.println(jsonStr);
		}

	}
	
//	@Test
//	public void 암호화() throws Exception {
//		try {
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String password = "vpsxkvpsxkvpsxk123!@#!@#";
//			String encodedPassword = passwordEncoder.encode(password);
//			System.out.println(encodedPassword);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
