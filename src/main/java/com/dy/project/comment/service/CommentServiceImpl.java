package com.dy.project.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.project.comment.dto.CommentDTO;
import com.dy.project.comment.mapper.CommentMapper;
import com.dy.project.common.Constant.Result;

/* 해당 클래스가 비즈니스 로직을 수행하는 서비스 클래스임을 명시 */
@Service
public class CommentServiceImpl implements CommentService {

	/* DAO 빈(Bean) 선언(연결) */
	@Autowired
	private CommentMapper commentMapper;

	/**
	 * 댓글을 등록한다. (insert or update)
	 * 
	 * @param params - 댓글 등록에 사용할 파라미터들이 담긴 클래스
	 * @return 등록 성공 여부
	 */
	@Override
	public boolean registerComment(CommentDTO params) {

		if (params.getIdx() == null) {
			/* insert 쿼리 실행 결과 */
			int result = commentMapper.insertComment(params);
			if (result == Result.FAIL.getFirstValue()) {
				return false;
			}
		} else {
			/* update 쿼리 실행 결과 */
			int result = commentMapper.updateComment(params);
			if (result == Result.FAIL.getFirstValue()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param idx - 댓글 번호 (PK)
	 * @param boardIdx - 게시글 번호 (PK)
	 * @return 삭제 성공 여부
	 */
	@Override
	public boolean deleteComment(Integer idx, Integer boardIdx) {

		CommentDTO comment = commentMapper.selectCommentDetail(idx, boardIdx);
		if (comment == null || "N".equals(String.valueOf(comment.getUseYn()))) {
			return false;
		}

		/* delete 쿼리 실행 결과 */
		int result = commentMapper.deleteComment(idx, boardIdx);
		if (result == Result.FAIL.getFirstValue()) {
			return false;
		}

		return true;
	}

	/**
	 * 댓글 리스트를 조회한다.
	 * 
	 * @param params - 댓글 리스팅에 사용할 파라미터들이 담긴 클래스
	 * @return 댓글 리스트
	 */
	@Override
	public List<CommentDTO> getCommentList(CommentDTO params) {

		List<CommentDTO> commentList = null;

		int totalCnt = commentMapper.selectTotalCnt(params);
		if (totalCnt > 0) {
			commentList = commentMapper.selectCommentList(params);
		}

		return commentList;
	}

}
