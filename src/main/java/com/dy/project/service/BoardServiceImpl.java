package com.dy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.project.common.Constant.Result;
import com.dy.project.common.paging.PaginationInfo;
import com.dy.project.domain.BoardDTO;
import com.dy.project.mapper.BoardMapper;

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
			/* insert 쿼리 실행 결과 */
			int result = boardMapper.insertBoard(params);
			if (result == Result.FAIL.getFirstValue()) {
				return false;
			}
		} else {
			/* update 쿼리 실행 결과 */
			int result = boardMapper.updateBoard(params);
			if (result == Result.FAIL.getFirstValue()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 게시글을 조회한다.
	 * 
	 * @param idx - 게시글 번호 (PK)
	 * @return 게시글 상세 정보
	 */
	@Override
	public BoardDTO getBoardDetail(Integer idx) {

		BoardDTO board = boardMapper.selectBoardDetail(idx);
		if (board != null) {
			/* 조회 수 증가 쿼리 실행 결과 */
			int result = boardMapper.updateViewCnt(idx);
			if (result == Result.FAIL.getFirstValue()) {
				return null;
			}
		}

		return board;
	}

	/**
	 * 게시글을 삭제한다.
	 * 
	 * @param idx - 게시글 번호 (PK)
	 * @return 삭제 성공 여부
	 */
	@Override
	public boolean deleteBoard(Integer idx) {

		BoardDTO board = boardMapper.selectBoardDetail(idx);
		if (board == null || "N".equals(String.valueOf(board.getUseYn()))) {
			return false;
		}

		/* delete 쿼리 실행 결과 */
		int result = boardMapper.deleteBoard(idx);
		if (result == Result.FAIL.getFirstValue()) {
			return false;
		}

		return true;
	}

	/**
	 * 게시글 리스트를 조회한다.
	 * 
	 * @param params - 게시글 리스트 조회 & 페이징에 사용할 파라미터들이 담긴 클래스
	 * @return 게시글 리스트
	 */
	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {

		List<BoardDTO> boardList = null;

		int totalCnt = boardMapper.selectTotalCnt(params);
		if (totalCnt > 0) {
			params.setTotalRecordCount(totalCnt);
			PaginationInfo paginationInfo = new PaginationInfo(params);
			params.setPaginationInfo(paginationInfo);
			boardList = boardMapper.selectBoardList(params);
		}

		return boardList;
	}

}
