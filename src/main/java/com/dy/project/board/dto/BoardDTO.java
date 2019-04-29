package com.dy.project.board.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dy.project.common.Constant.YesNo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

	/** PK */
	private Integer idx;

	/** 제목 */
	@NotBlank(message = "제목을 입력해 주세요.")
	@Size(max = 30, message = "제목은 30자 미만으로 입력해 주세요.")
	private String title;

	/** 내용 */
	@NotBlank(message = "내용을 입력해 주세요.")
	@Size(max = 1000, message = "내용은 1000자 미만으로 입력해 주세요.")
	private String content;

	/** 작성자 */
	@NotBlank(message = "작성자를 입력해 주세요.")
	@Size(max = 10, message = "작성자는 10자 미만으로 입력해 주세요.")
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
