//package com.dy.project.member.dto;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
//import com.dy.project.common.dto.CommonDTO;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class MemberDTO extends CommonDTO {
//
//	/** 회원 번호 (PK) */
//	private Integer idx;
//
//	/** 이메일 (PK) */
//	@NotBlank(message = "이메일을 입력해 주세요.")
//	@Pattern(regexp = "/^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$/i", message = "이메일을 올바른 형식으로 입력해 주세요.")
//	private String email;
//
//	/** 비밀번호 */
//	private String password;
//
//	/** 이름 */
//	@NotBlank(message = "이름을 입력해 주세요.")
//	@Size(min = 2, max = 4, message = "이름을 2~4 글자 사이로 입력해 주세요.")
//	@Pattern(regexp = "/^[가-힣]+$/", message = "이름을 올바른 형식으로 입력해 주세요.")
//	private String name;
//
//	/** 전화번호 */
//	@NotBlank(message = "전화번호를 입력해 주세요.")
//	@Pattern(regexp = "/^\\d{3}-\\d{3,4}-\\d{4}$/", message = "전화번호를 올바른 형식으로 입력해 주세요.")
//	private String phone;
//
//}
