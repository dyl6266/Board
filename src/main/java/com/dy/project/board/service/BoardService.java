package com.dy.project.board.service;

import java.util.List;

import com.dy.project.board.dto.BoardDTO;

public interface BoardService {

	/**
	 * 게시글을 등록한다. (insert or update)
	 * 
	 * @param params - 게시글 등록에 사용할 파라미터들이 담긴 클래스
	 * @return 등록 성공 여부
	 */
	public boolean registerBoard(BoardDTO params);

	/**
	 * 게시글을 조회한다.
	 * 
	 * @param idx
	 * @return 게시글 상세 정보
	 */
	public BoardDTO getBoardDetail(Integer idx);

	/**
	 * 게시글을 삭제한다.
	 * 
	 * @param idx - 게시글 번호 (PK)
	 * @return 삭제 성공 여부
	 */
	public boolean deleteBoard(Integer idx);

	/**
	 * 게시글 리스트를 조회한다.
	 * 
	 * @param params - 페이징에 사용할 파라미터들이 담긴 클래스
	 * @return 게시글 리스트
	 */
	public List<BoardDTO> getBoardList(BoardDTO params);

}
