package mode;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="news_table")

public class News {
	private int newsId;
	private String newsTile;
	private String news_address;
	private String news_content;
	private String author;
	private NewsCategory category;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getNewsId() {
		return newsId;
	}
	
	@Column
	public String getNewsTile() {
		return newsTile;
	}
	
	@Column
	public String getNews_address() {
		return news_address;
	}
	
	@Column
	public String getNews_content() {
		return news_content;
	}
	
	@Column
	public String getAuthor() {
		return author;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	public NewsCategory getCategory() {
		return category;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public void setNewsTile(String newsTile) {
		this.newsTile = newsTile;
	}
	public void setNews_address(String news_address) {
		this.news_address = news_address;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCategory(NewsCategory category) {
		this.category = category;
	}
	
	
}