package com.dy.project.comment.service;

import java.util.List;

import com.dy.project.comment.dto.CommentDTO;

public interface CommentService {

	/**
	 * 댓글을 등록한다. (insert or update)
	 * 
	 * @param params - 댓글 등록에 사용할 파라미터들이 담긴 클래스
	 * @return 등록 성공 여부
	 */
	public boolean registerComment(CommentDTO params);

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param idx - 댓글 번호 (PK)
	 * @param boardIdx - 게시글 번호 (PK)
	 * @return 삭제 성공 여부
	 */
	public boolean deleteComment(Integer idx, Integer boardIdx);

	/**
	 * 댓글 리스트를 조회한다.
	 * 
	 * @param params - 댓글 리스팅에 사용할 파라미터들이 담긴 클래스
	 * @return 댓글 리스트
	 */
	public List<CommentDTO> getCommentList(CommentDTO params);

}
