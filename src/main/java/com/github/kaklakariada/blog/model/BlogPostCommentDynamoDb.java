package com.github.kaklakariada.blog.model;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "blogpostcomments")
public class BlogPostCommentDynamoDb {

	private UUID commentId;
	private int blogPostId;

	private String email;
	private String title;
	private String content;

	public BlogPostCommentDynamoDb(int blogPostId, BlogPostComment comment) {
		this.blogPostId = blogPostId;
		this.email = comment.getEmail();
		this.title = comment.getTitle();
		this.content = comment.getContent();
	}

	@DynamoDBHashKey(attributeName = "CommentId")
	@DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
	public UUID getCommentId() {
		return commentId;
	}

	public void setCommentId(UUID commentId) {
		this.commentId = commentId;
	}

	@DynamoDBRangeKey(attributeName = "BlogPostId")
	public int getBlogPostId() {
		return blogPostId;
	}

	public void setBlogPostId(int blogPostId) {
		this.blogPostId = blogPostId;
	}

	@DynamoDBAttribute(attributeName = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBAttribute(attributeName = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DynamoDBAttribute(attributeName = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BlogPostCommentDynamoDb [commentId=" + commentId + ", blogPostId=" + blogPostId + ", email=" + email
				+ ", title=" + title + ", content=" + content + "]";
	}
}
