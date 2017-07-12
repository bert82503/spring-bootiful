package com.spring.boot.autoconfigure.http;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类概述。
 *
 * @author dannong
 * @since 2017年07月11日 18:27
 */
@Configuration
@ConditionalOnClass({
        AsyncHttpClientConfig.class, DefaultAsyncHttpClientConfig.class,
        AsyncHttpClientConfigDefaults.class,
        AsyncHttpClient.class, DefaultAsyncHttpClient.class,
        Dsl.class
})
@EnableConfigurationProperties({
        AsyncHttpClientProperties.class
})
@SuppressWarnings("unused")
public class AsyncHttpClientAutoConfiguration {

    private final AsyncHttpClientProperties properties;

    public AsyncHttpClientAutoConfiguration(AsyncHttpClientProperties properties) {
        this.properties = properties;
    }

    @Bean(name = "asyncHttpClient", destroyMethod = "close")
    @ConditionalOnMissingBean({
            AsyncHttpClient.class
    })
    public AsyncHttpClient asyncHttpClient() {
        Builder configBuilder = properties.getConfig()
                .setIoThreadsCount( // Netty IO线程数量，默认为CPU核数的两倍
                        Runtime.getRuntime().availableProcessors() -1)
                ;

        return Dsl.asyncHttpClient(configBuilder);
    }

}
