package com.yicj.study.kafka.config;

import com.yicj.study.kafka.properties.KafkaConsumerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yicj
 * @Since 2024/1/22 22:01
 */
@Component
public class KafkaConsumerRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //
        //this.doRegister(registry, "111");
        this.doRegister(registry, "222");
    }

    private void doRegister(BeanDefinitionRegistry registry, String suffix){
        String bootstrapServer = "192.168.99.51:8989" ;
        Map<String, Object> props = new KafkaConsumerProperties().init(bootstrapServer);
        String consumerFactoryName = "consumerFactory" + suffix ;
        AbstractBeanDefinition kafkaConsumerFactory = BeanDefinitionBuilder.genericBeanDefinition(DefaultKafkaConsumerFactory.class)
                .addConstructorArgValue(props)
                .getBeanDefinition();
        registry.registerBeanDefinition(consumerFactoryName, kafkaConsumerFactory);
        //
        String containerFactoryName = "containerFactory" + suffix ;
        AbstractBeanDefinition containerFactory = BeanDefinitionBuilder.genericBeanDefinition(ConcurrentKafkaListenerContainerFactory.class)
                .addPropertyReference("consumerFactory", consumerFactoryName)
                .getBeanDefinition();
        registry.registerBeanDefinition(containerFactoryName, containerFactory);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
