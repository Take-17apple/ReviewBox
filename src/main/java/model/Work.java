package model;

import java.io.Serializable;

public class Work implements Serializable {
	//タイトル、作者、ジャンル、タイプ、値段、内容、評価、IDを格納するBeansクラス
	private int workId;
	private String title;
	private String creator;
	private String genre;
	private String type;
	private int price;
	private String plot;
	private int rating;
	private int reviewerId;
	
	
	public Work() {
		
	}
	public Work(int workId, String title, String creator, String genre, String type, 
				int price, String plot, int rating, int reviewerId) {
		this.workId = workId;
		this.title = title;
		this.creator = creator;
		this.genre = genre;
		this.type = type;
		this.price = price;
		this.plot = plot;
		this.rating = rating;
		this.reviewerId = reviewerId;
	}
	public Work(String title, String creator, String genre, 
			int price, String plot, int rating, int reviewerId) {
	this.title = title;
	this.creator = creator;
	this.genre = genre;
	this.price = price;
	this.plot = plot;
	this.rating = rating;
	this.reviewerId = reviewerId;
}
	public int getWorkId() {
		return workId;
	}
	public String getTitle() {
		return title;
	}

	public String getCreator() {
		return creator;
	}

	public String getGenre() {
		return genre;
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public String getPlot() {
		return plot;
	}

	public int getRating() {
		return rating;
	}
	public int getReviewerId() {
		return reviewerId;
	}
	@Override
	public String toString() {
	    return this.title;
	}

}
