package com.dy.project.common.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	/** 현재 페이지 번호 */
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
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
