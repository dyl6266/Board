package com.dy.project.common.dto;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dy.project.common.Constant.YesNo;
import com.dy.project.common.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO {

	/** 사용 여부 */
	private YesNo useYn;

	/** 등록일 */
	@CreationTimestamp
	private Date insertTime;

	/** 수정일 */
	@UpdateTimestamp
	private Date updateTime;

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;

}
