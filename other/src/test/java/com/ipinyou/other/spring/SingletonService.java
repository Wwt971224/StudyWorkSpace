package com.ipinyou.other.spring;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SingletonService {

    @SneakyThrows
    public synchronized String getService() {

        Thread.sleep(1000);

        return this.toString();
    }

}
