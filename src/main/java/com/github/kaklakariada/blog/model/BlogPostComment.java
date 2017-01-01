package com.github.kaklakariada.blog.model;

public class BlogPostComment {
	private String email;
	private String title;
	private String content;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BlogPostComment [email=" + email + ", title=" + title + ", content=" + content + "]";
	}
}
