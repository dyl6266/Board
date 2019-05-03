package com.dy.project.common.dto;

import java.util.Date;

import com.dy.project.common.Constant.YesNo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO {

	/** 사용 여부 */
	private YesNo useYn;

	/** 등록일 */
	private Date insertTime;

	/** 수정일 */
	private Date updateTime;

}
