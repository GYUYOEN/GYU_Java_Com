package common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Paging {
	private int offset; // 보여질 데이터 위치
	private int limit; // 보여질 데이터 갯수
	private List<Integer> pages; // 페이지 번호 정보
	private List<Object> pageDatas; // 해당 페이지에 있는 데이터
	private int currentPage; // 현재 페이지
	private int nextPage;
	private int prevPage;
	
	public Paging(int page, int limit, int totalRows) {
		this.offset = limit * (page - 1);
		this.limit = limit;
		this.currentPage = page;
		this.nextPage = page + 1;
		this.prevPage = page - 1;
		this.pages = new ArrayList<Integer>();
		page = 1;
		for(int i = 0; i < totalRows; i += limit) {
			this.pages.add(page++);
		}
	}
	
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	public List<Integer> getPages() {
		return pages;
	}
	public List<Integer> getPages(int start, int end) { // 페이지 번호
		start = start < 1 ? 1 : start;
		end = end > pages.size() ? pages.size() : end;
		return pages.subList(start - 1, end);
	}
	public List<Object> getPageDatas() {
		return pageDatas;
	}
	public void setPageDatas(Iterator<Object> iter) {
		this.pageDatas = new ArrayList<Object>();
		while(iter.hasNext()) {
			pageDatas.add(iter.next());
		}
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	
	public boolean hasNextPage() { // 다음페이지가 있는지
		return this.nextPage > this.pages.size() ? false : true;
	}
	
	public boolean hasPrevPage() { // 이전 페이지가 있는지
		return this.prevPage <= 0 ? false : true;
	}
	
}
