package com.dy.project.board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dy.project.board.dto.BoardDTO;

/*
 * MyBatis의 Mapper 인터페이스임을 명시
 * MyBatis는 DAO 클래스를 만드는 것보다 SqlSessionDaoSupports 또는 SqlSessionTemplate을 사용하기를 권장
 */
@Mapper
public interface BoardMapper {

	/**
	 * 게시글을 등록한다.
	 * 
	 * @param params - 게시글 등록에 사용할 파라미터들이 담긴 클래스
	 * @return 쿼리 실행 수
	 */
	public int insertBoard(BoardDTO params);

	/**
	 * 게시글을 조회한다.
	 * 
	 * @param idx - 게시글 번호 (PK)
	 * @return 게시글 상세 정보
	 */
	public BoardDTO selectBoardDetail(Integer idx);

	/**
	 * 게시글을 수정한다.
	 * 
	 * @param params - 게시글 수정에 사용할 파라미터들이 담긴 클래스
	 * @return 쿼리 실행 수
	 */
	public int updateBoard(BoardDTO params);

	/**
	 * 게시글을 삭제한다.
	 * 
	 * @param idx - 게시글 번호 (PK)
	 * @return 쿼리 실행 수
	 */
	public int deleteBoard(Integer idx);

	/**
	 * 게시글 전체 개수를 카운팅한다.
	 * 
	 * @param params - 페이징에 사용할 파라미터들이 담긴 HashMap
	 * @return 전체 데이터 수
	 */
	public int selectTotalCnt(HashMap<String, Object> params);

	/**
	 * 게시글 리스트를 조회한다.
	 * 
	 * @param params - 페이징에 사용할 파라미터들이 담긴 HashMap
	 * @return 게시글 리스트
	 */
	public List<BoardDTO> selectBoardList(HashMap<String, Object> params);

	/**
	 * 게시글 조회 수를 증가시킨다.
	 * 
	 * @param idx
	 * @return 쿼리 실행 수
	 */
	public int updateViewCnt(Integer idx);

}
