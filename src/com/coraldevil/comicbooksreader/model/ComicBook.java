package com.coraldevil.comicbooksreader.model;

public class ComicBook {
	private String url;
	private String bookName;
	private String thumbnailUrl;
	private int pageNumber;
	private int currentPage;
	
	public ComicBook(){
		url = "";
		bookName = "";
		thumbnailUrl = "No direction!";
		pageNumber = 0;
		currentPage = 0;
	}
	
	
	public ComicBook(String name, String name2, String path) {
		// TODO Auto-generated constructor stub
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ComicBook) {
			ComicBook comic = (ComicBook) o;
			return url.equals(comic.getUrl());
		}
		else{
			return false;
		}
	}
}
