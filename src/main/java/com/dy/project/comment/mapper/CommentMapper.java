package com.dy.project.comment.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dy.project.comment.dto.CommentDTO;

/*
 * MyBatis의 Mapper 인터페이스임을 명시
 * MyBatis는 DAO 클래스를 만드는 것보다 SqlSessionDaoSupports 또는 SqlSessionTemplate을 사용하기를 권장
 */
@Mapper
public interface CommentMapper {

	/**
	 * 댓글을 등록한다.
	 * 
	 * @param params - 댓글 등록에 사용할 파라미터들이 담긴 클래스
	 * @return 쿼리 실행 수
	 */
	public int insertComment(CommentDTO params);

	/**
	 * 댓글을 조회한다.
	 * 
	 * @param params - 댓글 조회에 사용할 파라미터들이 담긴 Map
	 * @return 댓글 상세 정보
	 */
	public CommentDTO selectCommentDetail(HashMap<String, Object> params);

	/**
	 * 댓글을 수정한다.
	 * 
	 * @param params - 댓글 수정에 사용할 파라미터들이 담긴 클래스
	 * @return 쿼리 실행 수
	 */
	public int updateComment(CommentDTO params);

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param params 댓글 삭제에 사용할 파라미터들이 담긴 Map
	 * @return 쿼리 실행 수
	 */
	public int deleteComment(HashMap<String, Object> params);

	/**
	 * 댓글 전체 개수를 카운팅한다.
	 * 
	 * @param params - 댓글 카운팅에 사용할 파라미터들이 담긴 클래스
	 * @return
	 */
	public int selectTotalCnt(CommentDTO params);

	/**
	 * 댓글 리스트를 조회한다.
	 * 
	 * @param params - 댓글 리스팅에 사용할 파라미터들이 담긴 클래스
	 * @return 댓글 리스트
	 */
	public List<CommentDTO> selectCommentList(CommentDTO params);

}
