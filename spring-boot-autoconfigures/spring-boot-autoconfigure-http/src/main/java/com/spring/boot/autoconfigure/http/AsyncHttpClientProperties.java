package com.spring.boot.autoconfigure.http;

import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 异步HTTP客户端配置选项。
 *
 * @author dannong
 * @since 2017年07月11日 18:16
 */
@ConfigurationProperties(prefix = "com.spring.http")
public class AsyncHttpClientProperties {

    // 异步HTTP客户端配置
    private DefaultAsyncHttpClientConfig.Builder config;

    public Builder getConfig() {
        return config;
    }

    public AsyncHttpClientProperties setConfig(Builder config) {
        this.config = config;
        return this;
    }
}
