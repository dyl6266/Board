package com.dy.project;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dy.project.board.dto.BoardDTO;
import com.dy.project.board.mapper.BoardMapper;
import com.dy.project.board.service.BoardService;
import com.dy.project.comment.dto.CommentDTO;
import com.dy.project.comment.service.CommentService;
import com.dy.project.common.Constant.YesNo;
import com.dy.project.common.paging.Criteria;
import com.dy.project.common.paging.PaginationInfo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private BoardService boardService;

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
		if (CollectionUtils.isEmpty(list)) {
			gson = new Gson();
		} else {
			String jsonStr = new Gson().toJson(list);
			System.out.println(jsonStr);
		}

	}

	@Test
	public void 페이징() throws Exception {
		
		int totalCount = boardMapper.selectTotalCnt(new BoardDTO());
		BoardDTO params = new BoardDTO();
		
		Criteria criteria = new Criteria(3, 15, 10, totalCount);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCriteria(criteria);

		params.setPaginationInfo(paginationInfo);
		List<BoardDTO> boardList = boardMapper.selectBoardList(params);
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
	
	@Test
	public void testUri() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/{a}/{b}/{c}")
				.queryParam("currentPageNo", 1)
				.queryParam("recordCountPerPage", 15)
				.build()
				.expand("test1", "test2", "test3")
				.encode();
		
		System.out.println("encoding1 ::::: " + uriComponents.toUriString());
		System.out.println("encoding2 ::::: " + uriComponents.encode().toString());
		System.out.println("encoding3 ::::: " + uriComponents.encode().toUriString());
	}

}
