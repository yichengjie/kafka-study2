package com.yicj.study.kafka.config;

import com.yicj.study.kafka.properties.KafkaProducerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author yicj
 * @Since 2024/1/21 16:05
 */
@Component
public class KafkaProducerRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String bootstrapServer = "192.168.99.51:8989" ;
        Map<String, Object> properties = new KafkaProducerProperties().init(bootstrapServer);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(DefaultKafkaProducerFactory.class)
                .addConstructorArgValue(properties)
                .getBeanDefinition();
        registry.registerBeanDefinition("hdkKafkaProducerFactory", beanDefinition);
        //
        AbstractBeanDefinition hdkKafkaTemplate = BeanDefinitionBuilder.genericBeanDefinition(KafkaTemplate.class)
                .addConstructorArgReference("hdkKafkaProducerFactory")
                .getBeanDefinition();
        registry.registerBeanDefinition("hdkKafkaTemplate", hdkKafkaTemplate);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
