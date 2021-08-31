package com.stein.myenergi.api.calls;

import com.stein.myenergi.api.calls.dto.MyenergiCallInput;
import com.stein.myenergi.api.calls.dto.MyenergiCallOutput;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractMyEnergiCall<I extends MyenergiCallInput, O extends MyenergiCallOutput> {

    private final RestTemplate myEnergyRestTemplate;
    private final Class<O> outputClass;

    public AbstractMyEnergiCall(RestTemplate myEnergyRestTemplate, Class<O> outputClass) {
        this.myEnergyRestTemplate = myEnergyRestTemplate;
        this.outputClass = outputClass;
    }

    public O fire(I input) {
        if(input.getParameters() != null && input.getParameters().length > 0) {
            return myEnergyRestTemplate.getForObject(getCommand(input), outputClass, input.getParameters());
        }
        return myEnergyRestTemplate.getForObject(getCommand(input), outputClass);
    }

    abstract String getCommand(I input);
}
