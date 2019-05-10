package com.dy.project.common.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationInfo {

	public PaginationInfo(Criteria criteria) {
		this.criteria = criteria;

		calculation();
	}

	/** 페이징 계산에 필요한 파라미터들이 담긴 클래스 */
	private Criteria criteria;

	/** 전체 페이지 개수 */
	private int totalPageCount;

	/** 페이지 리스트의 첫 페이지 번호 */
	private int firstPageNoOnPageList;

	/** 페이지 리스트의 마지막 페이지 번호 */
	private int lastPageNoOnPageList;

	/** SQL의 조건절에 사용되는 첫 RNUM */
	private int firstRecordIndex;

	/** SQL의 조건절에 사용되는 마지막 RNUM */
	private int lastRecordIndex;

	/**
	 * 페이징 정보를 계산한다.
	 */
	private void calculation() {

		/* 전체 페이지 개수 */
		totalPageCount = ((criteria.getTotalRecordCount() - 1) / criteria.getRecordCountPerPage()) + 1;

		/* 페이지 리스트의 첫 페이지 번호 */
		firstPageNoOnPageList = ((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;

		/* 페이지 리스트의 마지막 페이지 번호 */
		lastPageNoOnPageList = getFirstPageNoOnPageList() + criteria.getPageSize() - 1;
		if (lastPageNoOnPageList > getTotalPageCount()) {
			lastPageNoOnPageList = getTotalPageCount();
		}

		/* SQL의 조건절에 사용되는 첫 RNUM */
		firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordCountPerPage();

		/* SQL의 조건절에 사용되는 마지막 RNUM */
		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordCountPerPage();
	}

}
