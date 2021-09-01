package com.stein.myenergi.api.calls;

import com.stein.myenergi.api.calls.dto.MyenergiCallInput;
import com.stein.myenergi.api.calls.dto.MyenergiCallOutput;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractMyEnergiCall<I extends MyenergiCallInput, O extends MyenergiCallOutput> {

    private final RestTemplate myEnergyRestTemplate;
    private final Class<O> outputClass;

    public AbstractMyEnergiCall(RestTemplate myEnergyRestTemplate, Class<O> outputClass) {
        this.myEnergyRestTemplate = myEnergyRestTemplate;
        this.outputClass = outputClass;
    }

    @Retryable(maxAttempts = 5, value = RestClientException.class)
    public O fire(I input) {
        if(input.getParameters() != null && input.getParameters().length > 0) {
            return myEnergyRestTemplate.getForObject(getCommand(input), outputClass, input.getParameters());
        }
        return myEnergyRestTemplate.getForObject(getCommand(input), outputClass);
    }

    abstract String getCommand(I input);
}
