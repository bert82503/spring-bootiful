package com.spring.boot.http;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 类概述。
 *
 * @author dannong
 * @since 2017年07月11日 19:56
 */
@Component("asyncHttpClientService")
public class AsyncHttpClientRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AsyncHttpClientRunner.class);


    @Resource(name = "asyncHttpClient")
    private AsyncHttpClient asyncHttpClient;

    @Override
    public void run(String... args) throws Exception {
        asyncGet();
    }

    public void asyncGet() throws ExecutionException, InterruptedException {
        CompletableFuture<Response> future = asyncHttpClient
                .prepareGet("http://www.baidu.com")
                .setRequestTimeout(2000)
                .addHeader("Accept-Encoding", "gzip,deflate")
                .execute()
                .toCompletableFuture()
                .exceptionally(t -> {
                    logger.error("'asyncGet' fail", t);
                    return null;
                })
                .thenApplyAsync(response -> response);
        future.join();

        Response response = future.get();
        assert response.getStatusCode() == 200;
        String responseBody = response.getResponseBody();
        assert responseBody.contains("百度一下，你就知道");
    }

}
