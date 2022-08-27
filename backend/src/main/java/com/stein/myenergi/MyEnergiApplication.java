package com.stein.myenergi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;

import org.springframework.nativex.hint.ProxyBits;
import org.springframework.nativex.hint.SerializationHint;

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
@SerializationHint(types = {HistoryId.class, HistoryDay.class, HistoryEntity.class, String.class})
@SpringBootApplication
@EnableRetry
@EnableScheduling
public class MyEnergiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEnergiApplication.class, args);
	}

}
