package com.dy.project.comment.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dy.project.common.Constant.YesNo;
import com.dy.project.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends CommonDTO {

	/** 댓글 번호 (PK) */
	private Integer idx;

	/** 게시글 번호 (FK) */
	@NotNull(message = "게시글 번호를 입력해 주세요.")
	@Min(value = 1, message = "게시긃 번호는 1보다 작을 수 없습니다.")
	private Integer boardIdx;

	/** 내용 */
	@NotBlank(message = "내용을 입력해 주세요.")
	@Size(max = 300, message = "내용은 300자 미만으로 입력해 주세요.")
	private String content;

	/** 작성자 */
	@NotBlank(message = "이름을 입력해 주세요.")
	@Size(max = 10, message = "이름은 10자 미만으로 입력해 주세요.")
	private String writer;

	/** 비밀글 여부 */
	private YesNo secretYn;

}
