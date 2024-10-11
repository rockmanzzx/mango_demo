package org.example.mango_consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;

@Configuration
public class RetryConfiguration {

    private static Logger log = LoggerFactory.getLogger(RetryConfiguration.class);

    //@ConditionalOnMissingBean:当 BeanFactory 中没有名为 configServerRetryInterceptor 的 bean 时才匹配此 Bean，对应到bean名称，此为细粒度控制
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    @Bean
    public RetryOperationsInterceptor configServerRetryInterceptor(){

        log.info(String.format(
                "configServerRetryInterceptor: Changing backOffOptions " +
                        "to initial: %s, multiplier: %s, maxInterval: %s",
                1000, 1.2, 5000));
        return RetryInterceptorBuilder.stateless()
                //#最初重试间隔为 1000 毫秒 #每次重试失败后，重试间隔所增加的倍数 #最长重试间隔为 5000 毫秒
                .backOffOptions(1000,1.2,5000)
                //#最多重试 10 次
                .maxAttempts(10)
                .build();
    }
}
