package com.dy.project.board.dto;

import java.util.Date;

import com.dy.project.common.Constant.YesNo;

import lombok.Data;

@Data
public class BoardDTO {

	/** PK */
	private Integer idx;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 작성자 */
	private String writer;

	/** 사용 여부 */
	private YesNo useYn;

	/** 공지글 여부 */
	private YesNo noticeYn;

	/** 비밀글 여부 */
	private YesNo secretYn;

	/** 조회 수 */
	private int viewCnt;

	/** 등록일 */
	private Date insertTime;

	/** 수정일 */
	private Date updateTime;

}
