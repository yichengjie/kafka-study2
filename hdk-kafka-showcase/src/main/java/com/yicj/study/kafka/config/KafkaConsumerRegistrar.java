package com.yicj.study.kafka.config;

import com.yicj.study.kafka.properties.KafkaConsumerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

/**
 * @author yicj
 * @Since 2024/1/22 22:55
 */
@Slf4j
public class KafkaConsumerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        this.doRegister(registry, "", true);
        this.doRegister(registry, "222", false);
    }

    private void doRegister(BeanDefinitionRegistry registry, String suffix, boolean flag){
        String bootstrapServer = "192.168.99.51:8989" ;
        Map<String, Object> props = new KafkaConsumerProperties().init(bootstrapServer);
        String consumerFactoryName = "consumerFactory" + suffix ;
        AbstractBeanDefinition kafkaConsumerFactory =
                BeanDefinitionBuilder.genericBeanDefinition(DefaultKafkaConsumerFactory.class)
                .setPrimary(flag)
                .addConstructorArgValue(props)
                .getBeanDefinition();
        registry.registerBeanDefinition(consumerFactoryName, kafkaConsumerFactory);
        //
        String containerFactoryName = "containerFactory" + suffix ;
        AbstractBeanDefinition containerFactory =
                BeanDefinitionBuilder.genericBeanDefinition(ConcurrentKafkaListenerContainerFactory.class)
                .addPropertyReference("consumerFactory", consumerFactoryName)
                .getBeanDefinition();
        registry.registerBeanDefinition(containerFactoryName, containerFactory);
    }
}
