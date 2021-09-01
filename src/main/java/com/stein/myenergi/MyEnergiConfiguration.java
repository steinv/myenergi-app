package com.stein.myenergi;

import java.time.Duration;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyEnergiConfiguration {

    @Value("${myenergi.hub.serial}")
    private String hubSerial;

    @Value("${myenergi.password}")
    private String password;


    @Bean
    public RestTemplate myEnergiRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        final String hostUri = String.format("https://s%s.myenergi.net/", hubSerial.substring(hubSerial.length() - 1));
        HttpHost host = new HttpHost(hostUri);
        CloseableHttpClient client =
                HttpClientBuilder
                        .create()
                        .setDefaultCredentialsProvider(provider())
                        .useSystemProperties()
                        .build();

        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
        return restTemplateBuilder
                .requestFactory(() -> {
                    requestFactory.setHttpContextFactory(((httpMethod, uri) -> {
                        AuthCache authCache = new BasicAuthCache();
                        DigestScheme digestAuth = new DigestScheme();
                        authCache.put(host, digestAuth);
                        BasicHttpContext localcontext = new BasicHttpContext();
                        localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
                        return localcontext;
                    }));
                    return requestFactory;
                })
                .rootUri(hostUri)
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofMinutes(3)) // looks like we get a 524 after 100 secs
                .build();
    }

    private CredentialsProvider provider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(hubSerial, password);
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }
}
