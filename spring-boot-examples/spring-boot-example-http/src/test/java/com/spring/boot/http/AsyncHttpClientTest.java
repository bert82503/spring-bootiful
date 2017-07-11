package com.spring.boot.http;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.boot.test.testng.AbstractTestNGIntegrationContextTests;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Integration test of {@link AsyncHttpClient}.
 *
 * @author dannong
 * @since 2017年07月11日 21:34
 */
public class AsyncHttpClientTest extends AbstractTestNGIntegrationContextTests {

    private static final Logger logger = LoggerFactory.getLogger(AsyncHttpClientTest.class);


    @Resource(name = "asyncHttpClient")
    private AsyncHttpClient asyncHttpClient;

    @Test
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
        assertThat(response.getStatusCode()).isEqualTo(200);
        String responseBody = response.getResponseBody();
        assertThat(responseBody).contains("百度一下，你就知道");
    }
}
