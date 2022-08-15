package com.stein.myenergi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.nativex.hint.ProxyBits;

@AotProxyHint(
	targetClass=com.stein.myenergi.api.calls.DayCall.class, 
	interfaces={org.springframework.retry.interceptor.Retryable.class}, 
	proxyFeatures = ProxyBits.IS_STATIC
)
@AotProxyHint(
	targetClass=com.stein.myenergi.api.calls.StatusCall.class, 
	interfaces={org.springframework.retry.interceptor.Retryable.class}, 
	proxyFeatures = ProxyBits.IS_STATIC
)
@SpringBootApplication
@EnableRetry
@EnableScheduling
public class MyEnergiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEnergiApplication.class, args);
	}

}
