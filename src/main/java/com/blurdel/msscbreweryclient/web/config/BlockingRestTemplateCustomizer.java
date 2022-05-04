package com.blurdel.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

	private final Integer maxTotalConnections;
	private final Integer defaultMaxPerRoute;
	private final Integer connectionRequstTimeout;
	private final Integer socketTimeout;
	
	
	public BlockingRestTemplateCustomizer(
			@Value("${sfg.maxtotalconnections}") Integer maxTotalConnections, 
			@Value("${sfg.defaultmaxperroute}") Integer defaultMaxPerRoute,
			@Value("${sfg.connectionrequesttimeout}") Integer connectionRequstTimeout, 
			@Value("${sfg.sockettimeout}") Integer socketTimeout) {
		super();
		this.maxTotalConnections = maxTotalConnections;
		this.defaultMaxPerRoute = defaultMaxPerRoute;
		this.connectionRequstTimeout = connectionRequstTimeout;
		this.socketTimeout = socketTimeout;
	}
	
	
	public ClientHttpRequestFactory clientRequestFactory() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequstTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.setRequestFactory(this.clientRequestFactory());
	}

}
