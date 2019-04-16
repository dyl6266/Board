package com.dy.project.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.project.board.dto.BoardDTO;

/* 해당 클래스가 비즈니스 로직을 수행하는 서비스 클래스임을 명시 */
@Service
public class BoardServiceImpl implements BoardService {

	/* DAO 빈(Bean) 선언(연결) */
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = null;
		boardList = boardDAO.selectBoardList();

		return boardList;
	}

}
