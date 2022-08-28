package com.stein.myenergi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.ProxyBits;
import org.springframework.nativex.hint.SerializationHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.interceptor.Retryable;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.stein.myenergi.api.calls.DayCall;
import com.stein.myenergi.api.calls.StatusCall;
import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;

// AOT proxy for retryable
@AotProxyHint(
	targetClass=DayCall.class, 
	interfaces={Retryable.class}, 
	proxyFeatures = ProxyBits.IS_STATIC
)
@AotProxyHint(
	targetClass=StatusCall.class, 
	interfaces={Retryable.class}, 
	proxyFeatures = ProxyBits.IS_STATIC
)
// The combined-key HistoryId and it's related classes need to be serializable for hibernate. 
@SerializationHint(types = {HistoryId.class, HistoryDay.class, HistoryEntity.class, String.class})
// ClassNotFoundException at runtime when this isn't hinted for the native image
@TypeHint(types = {SqlDateSerializer.class})
@SpringBootApplication
@EnableRetry
@EnableScheduling
public class MyEnergiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEnergiApplication.class, args);
	}

}
