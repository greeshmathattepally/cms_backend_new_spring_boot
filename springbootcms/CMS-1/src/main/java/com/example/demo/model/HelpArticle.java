package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


import jakarta.websocket.Decoder.Binary;
@Document(collection = "helpArticles")
public class HelpArticle {
	@Id
	private Long id;
	private String title;
	private String subtitle;
	private String paragraph;
	
	public HelpArticle() {
		super();
	}
	
	public HelpArticle(Long id, String title, String subtitle, String paragraph) {
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.paragraph = paragraph;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	
	
	
	
}
