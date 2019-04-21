package com.dy.project.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.project.board.dto.BoardDTO;
import com.dy.project.board.mapper.BoardMapper;
import com.dy.project.common.Constant.Result;

/* 해당 클래스가 비즈니스 로직을 수행하는 서비스 클래스임을 명시 */
@Service
public class BoardServiceImpl implements BoardService {

	/* DAO 빈(Bean) 선언(연결) */
	@Autowired
	private BoardMapper boardMapper;

	/**
	 * 게시글을 등록한다. (insert or update)
	 * 
	 * @param params - 게시글 등록에 사용할 파라미터들이 담긴 클래스
	 * @return 등록 성공 여부
	 */
	@Override
	public boolean registerBoard(BoardDTO params) {

		if (params.getIdx() == null) {
			int result = boardMapper.insertBoard(params);
			System.out.println(Result.OK.getResult());
			System.out.println(Result.FAIL.getResult());
			if (result == Result.FAIL.getResult()) {
				return false;
			}
		} else {
			int result = boardMapper.updateBoard(params);
			System.out.println(Result.OK.getResult());
			System.out.println(Result.FAIL.getResult());
			if (result == Result.FAIL.getResult()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 게시글 리스트를 조회한다.
	 * 
	 * @return 게시글 리스트
	 */
	@Override
	public List<BoardDTO> getBoardList() {

		List<BoardDTO> boardList = null;

		int totalCnt = boardMapper.selectTotalCnt();
		if (totalCnt > 0) {
			boardList = boardMapper.selectBoardList();
		}

		return boardList;
	}

}
