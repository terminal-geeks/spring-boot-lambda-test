package com.geeks.terminal.handler;

import java.time.Instant;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.geeks.terminal.SpringBootLambdaTestApplication;

@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class StreamLambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
	
	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

	static {
		System.out.println("static method::::::::");
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SpringBootLambdaTestApplication.class);
			// Only using the SpringBootProxyHandlerBuilder for initialization
			handler = (SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>) ((SpringBootProxyHandlerBuilder) new SpringBootProxyHandlerBuilder()
						.defaultProxy()
						.asyncInit(Instant.now().toEpochMilli()))
			            .springBootApplication(SpringBootLambdaTestApplication.class)
			            .asyncInit()
			            .buildAndInitialize();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not initialize Spring Boot application");
		}
	}

	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest input, Context context) {
		return handler.proxy(input, context);
	}
}