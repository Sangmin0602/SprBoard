package spr.board.utils;

public class Pagenation {

	private int currentPage = 0;
	private int rowsPerPage ;
	private int totalRows ;
	/**
	 * 
	 * @param curPage 요청 페이지 인덱스(zero-base)
	 * @param rpp     한 페이지당 게시물의 갯수
	 * @param total 전체 게시물 갯수
	 */
	public Pagenation (int curPage, int rpp, int total) {
		currentPage = curPage;
		rowsPerPage = rpp;
		totalRows = total;
	}
	
	/**
	 * 전체 페이지 숫자
	 * @return
	 */
	public int getTotalPage() {
		// 12 : 4 => 4 4 4 
		return totalRows / rowsPerPage + ( totalRows % rowsPerPage > 0 ? 1 :0);
	}
	
	public int getOffset() {
		return currentPage * rowsPerPage;
	}
	
	/**
	 * 한 페이지당 게시물 갯수( Rows Per Page )
	 * @return
	 */
	public int getRPP() {
		return rowsPerPage;
	}
}
