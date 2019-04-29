package com.dy.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dy.project.board.dto.BoardDTO;
import com.dy.project.board.mapper.BoardMapper;
import com.dy.project.common.Constant.YesNo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private BoardMapper boardMapper;

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
			dto.setWriter(i + "번 작성자입니다.");
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

}
