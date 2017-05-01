package com.github.kaklakariada.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.github.kaklakariada.aws.lambda.LambdaRequestHandler;
import com.github.kaklakariada.aws.lambda.request.ApiGatewayRequest;
import com.github.kaklakariada.blog.model.BlogPostComment;
import com.github.kaklakariada.blog.model.BlogPostCommentDynamoDb;

public class PostCommentHandler extends LambdaRequestHandler<BlogPostComment, Void> {

	private static final Logger LOG = LoggerFactory.getLogger(PostCommentHandler.class);

	public PostCommentHandler() {
		super(BlogPostComment.class);
	}

	@Override
	public Void handleRequest(ApiGatewayRequest request, BlogPostComment body, Context context) {
		LOG.info("Request: {}", request);
		LOG.info("Body: {}", body);

		final AmazonDynamoDB dynamoDB = AmazonDynamoDBClient.builder().build();
		final DynamoDBMapper dbMapper = new DynamoDBMapper(dynamoDB);
		final int blogPostId = 42;
		final BlogPostCommentDynamoDb entry = new BlogPostCommentDynamoDb(blogPostId, body);
		dbMapper.save(entry);
		LOG.info("New db entry: {}", entry);
		return null;
	}
}
