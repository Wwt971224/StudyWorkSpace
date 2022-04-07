package com.ipinyou.other.spring;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeService {

    @Autowired
    private SingletonService singletonService;

    @SneakyThrows
    public synchronized String getService() {
        Thread.sleep(1000);
        singletonService.getService();
        return this.toString();
    }

}
