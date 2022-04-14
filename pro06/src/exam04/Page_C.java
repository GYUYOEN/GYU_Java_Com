package exam04;

public class Page_C {
	private int pageNumber;
	private int limitPageNumber;
	
	public Page_C(int limitPageNumber) {
		this.limitPageNumber = limitPageNumber;
	}
	
	public int getPageNumber() {
		return this.pageNumber;
	}
	
	public void nextPage() {
		this.nextPage(1);
	}
	
	public void nextPage(int number) {
		if(this.existsNextPage()) {
			this.pageNumber += number;
		} else {
			System.out.println("더이상 패이지를 넘길수 없습니다.");
		}
	}
	
	public void prevPage() {
		this.prevPage(1);
	}
	
	public void prevPage(int number) {
		if(this.existsPrevPage()) {
			this.pageNumber -= number;
		} else {
			System.out.println("더이상 페이지를 넘길 수 없습니다.");
		}
	}
	
	public boolean existsNextPage() {
		return this.existsNextPage(1);
	}
	
	public boolean existsNextPage(int number) {
		if(this.pageNumber + number > this.limitPageNumber) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean existsPrevPage() {
		return this.existsPrevPage(1);
	}
	
	public boolean existsPrevPage(int number) {
		if(this.pageNumber - number < 1) {
			return false;
		} else {
			return true;
		}
	}
}
