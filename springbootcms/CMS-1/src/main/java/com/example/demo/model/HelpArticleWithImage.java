package com.example.demo.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "helpArticlesWithImages")
public class HelpArticleWithImage {
	@Id
	private Long id;
	private String title;
	private String subtitle;
	private Binary imageFile;
	private String altText;
	private String imageURL;
	private String paragraph;
	
	public HelpArticleWithImage() {
		super();
	}

	public HelpArticleWithImage(Long id, String title, String subtitle, String altText, String imageURL,
			String paragraph) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.altText = altText;
		this.imageURL = imageURL;
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

	public Binary getImageFile() {
		return imageFile;
	}

	public void setImageFile(Binary imageFile) {
		this.imageFile = imageFile;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

}
