package com.dy.project.common.paging;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	public Criteria(int currentPageNo, int recordCountPerPage, int pageSize, int totalRecordCount) {
		this.currentPageNo = currentPageNo;
		this.recordCountPerPage = recordCountPerPage;
		this.pageSize = pageSize;
		this.totalRecordCount = totalRecordCount;
	}

	/** 현재 페이지 번호 */
	@Min(value = 1, message = "페이지 번호는 1보다 작을 수 없습니다.")
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	@Min(value = 5, message = "출력 데이터 개수는 최소 5개까지 설정 가능합니다.")
	@Max(value = 50, message = "출력 데이터 개수는 최대 50개까지 설정 가능합니다.")
	private int recordCountPerPage;

	/** 하단에 출력할 페이지 사이즈 */
	private int pageSize;

	/** 전체 데이터 개수 */
	private int totalRecordCount;

	/** 검색 타입 */
	private String searchType;

	/** 검색 키워드 */
	private String searchKeyword;

}
