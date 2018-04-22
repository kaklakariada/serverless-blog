package com.github.kaklakariada.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.lambda.runtime.Context;
import com.github.kaklakariada.aws.lambda.LambdaRequestHandler;
import com.github.kaklakariada.aws.lambda.controller.LambdaController;
import com.github.kaklakariada.aws.lambda.controller.RequestHandlerMethod;
import com.github.kaklakariada.aws.lambda.model.request.ApiGatewayRequest;
import com.github.kaklakariada.blog.model.BlogPostComment;
import com.github.kaklakariada.blog.model.BlogPostCommentDynamoDb;

public class PostCommentHandler extends LambdaRequestHandler {

	private static final Logger LOG = LoggerFactory.getLogger(PostCommentHandler.class);

	public PostCommentHandler() {
		super(new Controller());
	}

	private static class Controller implements LambdaController {
		@RequestHandlerMethod
		public Void handleRequest(ApiGatewayRequest request, BlogPostComment body, Context context) {
			LOG.info("Request: {}", request);
			LOG.info("Body: {}", body);

			final DynamoDBMapper dbMapper = createDbMapper(request);
			final int blogPostId = 42;
			final BlogPostCommentDynamoDb entry = new BlogPostCommentDynamoDb(blogPostId, body);
			dbMapper.save(entry);
			LOG.info("New db entry: {}", entry);
			return null;
		}

		private DynamoDBMapper createDbMapper(ApiGatewayRequest request) {
			final AmazonDynamoDB dynamoDB = AmazonDynamoDBClient.builder().build();
			final String tableNamePrefix = request.getRequestContext().getStage() + "_";
			LOG.info("Using table prefix {}", tableNamePrefix);
			final DynamoDBMapperConfig config = getMapperConfig(tableNamePrefix);
			return new DynamoDBMapper(dynamoDB, config);
		}

		private DynamoDBMapperConfig getMapperConfig(String tableNamePrefix) {
			return DynamoDBMapperConfig.builder()
					.withTableNameOverride(TableNameOverride.withTableNamePrefix(tableNamePrefix)).build();
		}
	}
}
