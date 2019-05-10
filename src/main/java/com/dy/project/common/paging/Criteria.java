package com.dy.project.common.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	/** 현재 페이지 번호 */
	private Integer currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	private Integer recordCountPerPage;

	/** 하단에 출력할 페이지 사이즈 */
	private Integer pageSize;

	/** 전체 데이터 개수 */
	private int totalRecordCount;

	/** 검색 타입 */
	private String searchType;

	/** 검색 키워드 */
	private String searchKeyword;

	public void setCurrentPageNo(Integer currentPageNo) {
		if (currentPageNo == null || currentPageNo < 1) {
			this.currentPageNo = 1;
			return;
		}
		this.currentPageNo = currentPageNo;
	}

	public void setRecordCountPerPage(Integer recordCountPerPage) {
		if (recordCountPerPage == null || recordCountPerPage < 5 || recordCountPerPage > 50) {
			this.recordCountPerPage = 15;
			return;
		}
		this.recordCountPerPage = recordCountPerPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 5 || pageSize > 50) {
			this.pageSize = 10;
			return;
		}
		this.pageSize = pageSize;
	}

}
